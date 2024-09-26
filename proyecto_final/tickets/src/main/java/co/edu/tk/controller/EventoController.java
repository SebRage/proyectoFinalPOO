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

import co.edu.tk.model.Evento;
import co.edu.tk.service.EventoService;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping(value="/obtenerEventos", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Evento> obtenerEventos() {
        return eventoService.obtenerTodosLosEventos();
    }

    @PostMapping(value="/crearEvento", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Evento crearEvento(@RequestBody Evento evento) {
        return eventoService.guardarEvento(evento);
    }

    @GetMapping(value="evento/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Evento obtenerEventoPorId(@PathVariable int id) {
        return eventoService.obtenerEventoPorId(id);
    }

    @DeleteMapping(value="evento/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void eliminarEvento(@PathVariable int id) {
        eventoService.eliminarEvento(id);
    }
}
