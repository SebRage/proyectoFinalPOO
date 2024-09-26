package co.edu.tk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import co.edu.tk.model.Estado;
import co.edu.tk.model.Prioridad;
import co.edu.tk.model.Ticket;
import co.edu.tk.service.TicketService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class TicketController {

    @Autowired
    private TicketService ticketService;

    @GetMapping("/tickets")
    public List<Ticket> obtenerTickets() {
        return ticketService.obtenerTodosLosTickets();
    }

    @PostMapping(value = "/crearTicket", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Ticket crearTicket(@RequestBody Ticket ticket) {
        // Set the default state
        Estado estadoPredeterminado = ticketService.obtenerEstadoPredeterminado();
        ticket.setEstado(estadoPredeterminado);

        // Fetch the Prioridad object based on the ID received
        if (ticket.getPrioridad() != null && ticket.getPrioridad().getIdPrioridad() > 0) {
            Prioridad prioridad = ticketService.obtenerPrioridadPorId(ticket.getPrioridad().getIdPrioridad());
            if (prioridad != null) {
                ticket.setPrioridad(prioridad);
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prioridad no encontrada");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Prioridad inválida");
        }

        // Save the ticket
        return ticketService.guardarTicket(ticket);
    }

    @GetMapping(value = "/tickets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket obtenerTicketPorId(@PathVariable int id) {
        Ticket ticket = ticketService.obtenerTicketPorId(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket no encontrado");
        }
        return ticket;
    }

    @DeleteMapping(value = "/tickets/{id}")
    public void eliminarTicket(@PathVariable int id) {
        Ticket ticket = ticketService.obtenerTicketPorId(id);
        if (ticket == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Ticket no encontrado");
        }
        ticketService.eliminarTicket(id);
    }
}
