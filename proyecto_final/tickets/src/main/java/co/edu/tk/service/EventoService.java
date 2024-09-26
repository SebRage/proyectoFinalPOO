package co.edu.tk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.tk.dao.EventoRepository;
import co.edu.tk.model.Evento;

import java.util.List;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> obtenerTodosLosEventos() {
        return eventoRepository.findAll();
    }

    public Evento guardarEvento(Evento evento) {
        return eventoRepository.save(evento);
    }

    public Evento obtenerEventoPorId(int id) {
        return eventoRepository.findById(id).orElse(null);
    }

    public void eliminarEvento(int id) {
        eventoRepository.deleteById(id);
    }

}
