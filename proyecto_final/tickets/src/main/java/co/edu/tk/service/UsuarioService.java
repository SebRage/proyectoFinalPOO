package co.edu.tk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.tk.model.Usuario;
import co.edu.tk.dao.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario); 
    }

    public Usuario buscarPorNombreYContrasena(String nombre, String contrasena) {
        return usuarioRepository.findByNombreAndContrasena(nombre, contrasena);
    }

    public Usuario buscarPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    public void eliminarUsuario(int id) {
        usuarioRepository.deleteById(id);
    }

    public Usuario actualizarUsuario(int id, Usuario usuario) {
        if (usuarioRepository.existsById(id)) {
            usuario.setIdUsuario(id); 
            return usuarioRepository.save(usuario); 
        } else {
            return null; 
        }
    }

    public Usuario obtenerUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }

}
