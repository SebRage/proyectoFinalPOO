$(document).ready(function () {

    $('#registroButton').click(function (event) {
        event.preventDefault();

        const name = $('#name').val().trim();
        const documento = $('#documento').val().trim();
        const email = $('#email').val().trim();
        const telefono = $('#telefono').val().trim();
        const password = $('#password').val().trim();

        if (name === '') {
            alert('Por favor, ingresa tu nombre.');
            return;
        }

        if (documento === '' || isNaN(documento)) {
            alert('Por favor, ingresa un documento válido que contenga solo números.');
            return;
        }

        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (email === '' || !emailPattern.test(email)) {
            alert('Por favor, ingresa un correo electrónico válido.');
            return;
        }

        if (telefono === '') {
            alert('Por favor, ingresa tu teléfono.');
            return;
        }

        if (password === '') {
            alert('Por favor, ingresa tu contraseña.');
            return;
        }

        const usuario = {
            nombre: name,
            documento: documento,
            correo: email,
            telefono: telefono,
            contrasena: password
        };

        $.ajax({
            url: 'http://127.0.0.1:8080/registrar_usuario',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(usuario),
            success: function (response) {
                window.location.href = 'http://127.0.0.1:5501/tickets/src/main/resources/templates/';
            },
            error: function (xhr) {
                alert('Error al registrar: ' + xhr.responseText);
            }
        });
    });


    $('#registroUser').click(function (event) {
        event.preventDefault();

        const name = $('#name').val().trim();
        const documento = $('#documento').val().trim();
        const email = $('#email').val().trim();
        const telefono = $('#telefono').val().trim();
        const password = $('#password').val().trim();

        if (name === '') {
            alert('Por favor, ingresa tu nombre.');
            return;
        }

        if (documento === '' || isNaN(documento)) {
            alert('Por favor, ingresa un documento válido que contenga solo números.');
            return;
        }

        const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;
        if (email === '' || !emailPattern.test(email)) {
            alert('Por favor, ingresa un correo electrónico válido.');
            return;
        }

        if (telefono === '') {
            alert('Por favor, ingresa tu teléfono.');
            return;
        }

        if (password === '') {
            alert('Por favor, ingresa tu contraseña.');
            return;
        }

        const usuario = {
            nombre: name,
            documento: documento,
            correo: email,
            telefono: telefono,
            contrasena: password
        };

        $.ajax({
            url: 'http://127.0.0.1:8080/registrar_usuario',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(usuario),
            success: function (response) {
                window.location.href = 'http://127.0.0.1:5501/tickets/src/main/resources/templates/gestion_usuarios.html';
            },
            error: function (xhr) {
                alert('Error al registrar: ' + xhr.responseText);
            }
        });
    });



    // Manejo del inicio de sesión
    $('#loginButton').click(function (event) {
        event.preventDefault();

        const username = $('#username').val().trim();
        const password = $('#password').val().trim();

        if (username === '') {
            alert('Por favor, ingresa tu nombre de usuario.');
            return;
        }

        if (password === '') {
            alert('Por favor, ingresa tu contraseña.');
            return;
        }

        $.ajax({
            url: 'http://127.0.0.1:8080/login',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify({ nombre: username, contrasena: password }),
            success: function (data) {
                window.location.href = 'http://127.0.0.1:5501/tickets/src/main/resources/templates/';
            },
            error: function (xhr) {
                if (xhr.status === 401) {
                    alert('Nombre de usuario o contraseña incorrectos.');
                } else {
                    alert('Ocurrió un error. Intente nuevamente.');
                }
            }
        });
    });

    // Función para obtener eventos
    function obtenerEventos() {
        $.ajax({
            url: 'http://127.0.0.1:8080/obtenerEventos',
            type: 'GET',
            dataType: 'json',
            success: function (eventos) {
                let tableBody = $('#eventTableBody');
                tableBody.empty();

                eventos.forEach(function (evento) {
                    let row = `
                        <tr>
                            <td>
                                <div class="d-flex align-items-center">
                                    <div class="avatar">
                                        <i class="fas fa-user"></i>
                                    </div>
                                    <div class="ms-2">
                                        <div class="fw-bold">${evento.usuario.nombre}</div>
                                    </div>
                                </div>
                            </td>
                            <td>${evento.acciones}</td>
                            <td>${new Date(evento.fecha).toLocaleString()}</td> <!-- Formato de fecha -->
                            <td>${evento.ip}</td>
                        </tr>
                    `;
                    tableBody.append(row);
                });
            },
            error: function (xhr) {
                alert('Error al obtener eventos: ' + xhr.responseText);
            }
        });
    }

    obtenerEventos();

    // Cargar perfiles
    $.ajax({
        url: 'http://127.0.0.1:8080/perfiles',
        type: 'GET',
        success: function (perfiles) {
            perfiles.forEach(function (perfil) {
                $('#perfil').append(
                    `<option value="${perfil.idPerfil}">${perfil.perfil}</option>`
                );
            });
        },
        error: function (xhr, status, error) {
            console.error("Error al cargar los perfiles:", error);
        }
    });

    $.ajax({
        url: 'http://127.0.0.1:8080/perfiles',
        type: 'GET',
        success: function (perfiles) {
            perfiles.forEach(function (perfil) {
                $('#editarPerfil').append(
                    `<option value="${perfil.idPerfil}">${perfil.perfil}</option>`
                );
            });
        },
        error: function (xhr, status, error) {
            console.error("Error al cargar los perfiles:", error);
        }
    });



    function cargarUsuarios() {
        $.ajax({
            url: 'http://127.0.0.1:8080/usuarios',
            method: 'GET',
            success: function (usuarios) {
                $('#usuariosBody').empty();
                usuarios.forEach(function (usuario) {
                    $('#usuariosBody').append(`
                        <tr>
                            <td>${usuario.documento}</td>
                            <td>${usuario.nombre}</td>
                            <td>${usuario.correo}</td>
                            <td>${usuario.telefono}</td>
                            <td class="text-right">
                                <button class="btn btn-warning" onclick="abrirModalEditar(${usuario.idUsuario})">Editar</button>
                                <button class="btn btn-danger" onclick="eliminarUsuario(${usuario.idUsuario})">Eliminar</button>
                            </td>
                        </tr>
                    `);
                });
            },
            error: function (error) {
                console.error('Error al cargar los usuarios', error);
            }
        });
    }


    cargarUsuarios();

    window.abrirModalEditar = function (id) {
        $.ajax({
            url: `http://127.0.0.1:8080/usuarios/${id}`,
            method: 'GET',
            success: function (usuario) {

                $('#editarName').val(usuario.nombre);
                $('#editarDocumento').val(usuario.documento);
                $('#editarEmail').val(usuario.correo);
                $('#editarTelefono').val(usuario.telefono);
                $('#idUsuario').val(usuario.idUsuario);

                $('#editarPerfil').val(usuario.perfil.idPerfil);


                // Mostrar el modal
                $('#editarUsuarioModal').modal('show');
            },
            error: function (error) {
                console.error('Error al cargar el usuario', error);
            }
        });
    };


    $('#editarUsuario').click(function () {
        const id = $('#idUsuario').val();
        const usuarioData = {
            idUsuario: id,
            nombre: $('#editarName').val(),
            documento: $('#editarDocumento').val(),
            correo: $('#editarEmail').val(),
            telefono: $('#editarTelefono').val(),
            contrasena: $('#editarContrasena').val(),
            estado: { idEstado: 1 },
            perfil: { idPerfil: $('#editarPerfil').val() }
        };


        if (usuarioData.nombre === '' || usuarioData.documento === '' || usuarioData.correo === '' || usuarioData.telefono === '' || usuarioData.contrasena === '') {
            alert('Por favor, complete todos los campos.');
            return;
        }

        actualizarUsuario(id, usuarioData);
    });

    function actualizarUsuario(id, usuarioData) {
        $.ajax({
            url: `http://127.0.0.1:8080/usuarios/${id}`,
            method: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(usuarioData),
            success: function (response) {
                $('#editarUsuarioModal').modal('hide');
                alert('Usuario actualizado correctamente.');
                cargarUsuarios();
            },
            error: function (error) {
                console.error('Error al actualizar el usuario', error);
                alert('Error al actualizar el usuario.');
            }
        });
    }

    // Función para eliminar usuario
    window.eliminarUsuario = function (id) {
        if (confirm("¿Estás seguro de que deseas eliminar este usuario?")) {
            $.ajax({
                url: `http://127.0.0.1:8080/usuarios/${id}`,
                method: 'DELETE',
                success: function () {
                    alert('Usuario eliminado exitosamente.');
                    cargarUsuarios();
                },
                error: function (xhr) {
                    alert('Error al eliminar el usuario: ' + xhr.responseText);
                }
            });
        }
    };
});
