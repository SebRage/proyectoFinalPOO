$(document).ready(function () {
    function cargarTickets() {
        $("#ticketTableBody").empty();
        $("#ticketTableBody").append('<tr><td colspan="5">Cargando tickets...</td></tr>');

        $.ajax({
            url: "http://localhost:8080/tickets",
            method: "GET",
            success: function (data) {
                let ticketTableBody = $("#ticketTableBody");
                ticketTableBody.empty();
                window.tickets = data; // Guardar tickets en el contexto global

                data.forEach(function (ticket) {
                    let estado = ticket.estado ? ticket.estado.estado : "N/A";
                    estado = estado === "Activo" ? `<span class="badge bg-success">${estado}</span>` : `<span class="badge bg-danger">${estado}</span>`;

                    let prioridad = ticket.prioridad ? ticket.prioridad.prioridades : "N/A";
                    prioridad = prioridad === "Alta" ? `<span class="badge bg-warning">${prioridad}</span>` :
                        prioridad === "Media" ? `<span class="badge bg-primary">${prioridad}</span>` :
                            `<span class="badge bg-secondary">${prioridad}</span>`;

                    ticketTableBody.append(`
                        <tr id="ticket-${ticket.idTicket}">
                            <td>TKT-${ticket.idTicket}</td>
                            <td>${estado}</td>
                            <td>${prioridad}</td>
                            <td>${ticket.descripcion}</td>
                            <td>
                                <button class="btn btn-danger eliminar-ticket" onclick="eliminarTicket(${ticket.idTicket})">Eliminar</button>
                            </td>
                        </tr>
                    `);
                });
                actualizarEstadisticas(data);
            },
            error: function (error) {
                console.error("Error al cargar los tickets:", error);
                $("#ticketTableBody").empty();
                $("#ticketTableBody").append('<tr><td colspan="5">Error al cargar los tickets. Intenta de nuevo más tarde.</td></tr>');
            }
        });
    }

    function actualizarEstadisticas(tickets) {
        $("#totalTickets").text(tickets.length);
        $("#openTickets").text(tickets.filter(ticket => ticket.estado && ticket.estado.estado === "Activo").length);
        $("#closedTickets").text(tickets.filter(ticket => ticket.estado && ticket.estado.estado === "Inactivo").length);
    }

    function filtrarTickets(estado) {
        $("#ticketTableBody").empty();
        const filtrados = window.tickets.filter(ticket => {
            return estado === 'todos' || (ticket.estado && ticket.estado.estado === estado);
        });

        filtrados.forEach(function (ticket) {
            let estadoTexto = ticket.estado ? ticket.estado.estado : "N/A";
            estadoTexto = estadoTexto === "Activo" ? `<span class="badge bg-success">${estadoTexto}</span>` : `<span class="badge bg-danger">${estadoTexto}</span>`;

            let prioridad = ticket.prioridad ? ticket.prioridad.prioridades : "N/A";
            prioridad = prioridad === "Alta" ? `<span class="badge bg-warning">${prioridad}</span>` :
                prioridad === "Media" ? `<span class="badge bg-primary">${prioridad}</span>` :
                    `<span class="badge bg-secondary">${prioridad}</span>`;

            $("#ticketTableBody").append(`
                <tr id="ticket-${ticket.idTicket}">
                    <td>TKT-${ticket.idTicket}</td>
                    <td>${estadoTexto}</td>
                    <td>${prioridad}</td>
                    <td>${ticket.descripcion}</td>
                    <td>
                        <button class="btn btn-danger eliminar-ticket" onclick="eliminarTicket(${ticket.idTicket})">Eliminar</button>
                    </td>
                </tr>
            `);
        });
    }

    // Event listeners for filter buttons
    $("#filterAll").on("click", function () {
        filtrarTickets('todos');
    });

    $("#filterActive").on("click", function () {
        filtrarTickets('Activo');
    });

    $("#filterInactive").on("click", function () {
        filtrarTickets('Inactivo');
    });

    window.eliminarTicket = function (id) {
        if (confirm("¿Estás seguro de que deseas eliminar este ticket?")) {
            $.ajax({
                url: `http://127.0.0.1:8080/tickets/${id}`,
                method: 'DELETE',
                success: function () {
                    alert('Ticket eliminado exitosamente.');
                    cargarTickets(); // Recargar la lista de tickets después de eliminar
                },
                error: function (xhr) {
                    alert('Error al eliminar el ticket: ' + xhr.responseText);
                }
            });
        }
    };

    // Handle the creation of a new ticket
    $('#submitTicket').on('click', function () {
        // Retrieve values from the form
        const descripcion = $('#descriptionTicket').val();
        const prioridadId = $('#priorityTicket').val();

        // Check if inputs are valid
        if (!descripcion || prioridadId === "0") {
            alert("Por favor, completa todos los campos.");
            return;
        }

        // Construct the ticket object
        const newTicket = {
            descripcion: descripcion,
            prioridad: {
                idPrioridad: parseInt(prioridadId) // Assuming the backend uses this structure
            }
        };

        // Send the POST request to create a new ticket
        $.ajax({
            url: "http://localhost:8080/crearTicket", // Change the URL according to your API endpoint
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify(newTicket),
            success: function (response) {
                // Handle success
                alert("Ticket creado exitosamente.");
                $('#ticketModal').modal('hide'); // Close the modal
                cargarTickets();
            },
            error: function (xhr, status, error) {
                // Handle error
                alert("Error al crear el ticket: " + xhr.responseText);
            }
        });
    });

    cargarTickets();
});
