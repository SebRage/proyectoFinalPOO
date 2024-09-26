package co.edu.tk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.tk.dao.TicketRepository;
import co.edu.tk.dao.EstadoRepository; // Importa el nuevo repositorio
import co.edu.tk.dao.PrioridadRepository; // Importa el repositorio de Prioridad
import co.edu.tk.model.Estado;
import co.edu.tk.model.Prioridad; // Importa la clase Prioridad
import co.edu.tk.model.Ticket;

import java.util.List;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private EstadoRepository estadoRepository; // Inyección del repositorio de estado

    @Autowired
    private PrioridadRepository prioridadRepository; // Inyección del repositorio de Prioridad

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

    // Método para obtener la Prioridad por su ID
    public Prioridad obtenerPrioridadPorId(int id) {
        return prioridadRepository.findById(id).orElse(null); // Cambia esto si tienes una lógica diferente
    }
}
