package co.edu.tk.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.tk.model.Estado;
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
        // Establece el estado predeterminado aquí
        Estado estadoPredeterminado = ticketService.obtenerEstadoPredeterminado();
        ticket.setEstado(estadoPredeterminado);

        // Guarda el ticket
        return ticketService.guardarTicket(ticket);
    }

    @GetMapping(value = "/tickets/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Ticket obtenerTicketPorId(@PathVariable int id) {
        return ticketService.obtenerTicketPorId(id);
    }

    @DeleteMapping(value = "/tickets/{id}")
    public void eliminarTicket(@PathVariable int id) {
        ticketService.eliminarTicket(id);
    }

}
