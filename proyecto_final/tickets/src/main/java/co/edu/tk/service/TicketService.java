package co.edu.tk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.tk.dao.TicketRepository;
import co.edu.tk.dao.EstadoRepository; // Importa el nuevo repositorio
import co.edu.tk.model.Estado;
import co.edu.tk.model.Ticket;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EstadoRepository estadoRepository; // Inyección del repositorio de estado

    public List<Ticket> obtenerTodosLosTickets() {
        return ticketRepository.findAll();
    }

    public Ticket guardarTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket obtenerTicketPorId(int id) {
        return ticketRepository.findById(id).orElse(null);
    }

    public void eliminarTicket(int id) {
        ticketRepository.deleteById(id);
    }

    public Estado obtenerEstadoPredeterminado() {
        return estadoRepository.findById(1).orElse(null); // Cambia '1' por el ID correcto del estado predeterminado
    }
}
