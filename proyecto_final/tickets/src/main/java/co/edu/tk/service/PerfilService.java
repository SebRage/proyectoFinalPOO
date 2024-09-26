package co.edu.tk.service;

import co.edu.tk.model.Perfil;
import co.edu.tk.dao.PerfilRepository; // Asegúrate de tener un repositorio para manejar los perfiles
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    public List<Perfil> obtenerPerfiles() {
        return perfilRepository.findAll();
    }
}
