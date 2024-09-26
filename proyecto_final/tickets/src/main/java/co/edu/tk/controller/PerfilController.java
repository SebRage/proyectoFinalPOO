package co.edu.tk.controller;

import co.edu.tk.model.Perfil;
import co.edu.tk.service.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping("/perfiles")
    public List<Perfil> obtenerPerfiles() {
        return perfilService.obtenerPerfiles();
    }
}
