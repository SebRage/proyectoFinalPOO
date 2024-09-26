package co.edu.tk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import co.edu.tk.model.Estado;
import co.edu.tk.model.Perfil;
import co.edu.tk.model.Usuario;
import co.edu.tk.service.UsuarioService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://127.0.0.1:5501")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping(value = "/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Usuario> obtenerUsuarios() {
        return usuarioService.obtenerTodosLosUsuarios();
    }

    @GetMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        Usuario usuario = usuarioService.buscarPorId(id); 
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PostMapping("/registrar_usuario")
    public ResponseEntity<?> registrarUsuario(@RequestBody Usuario usuario) {
        try {
            Estado estadoPredeterminado = new Estado();
            estadoPredeterminado.setIdEstado(1);

            Perfil perfilPredeterminado = new Perfil();
            perfilPredeterminado.setIdPerfil(1); 

            // Establecer el estado y perfil en el usuario
            usuario.setEstado(estadoPredeterminado);
            usuario.setPerfil(perfilPredeterminado);

            // Guardar el usuario
            usuarioService.guardarUsuario(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(usuario);
        } catch (Exception e) {
            e.printStackTrace(); // Imprime la traza de la excepción
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar el usuario: " + e.getMessage());
        }
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        Usuario usuarioEncontrado = usuarioService.buscarPorNombreYContrasena(usuario.getNombre(),
                usuario.getContrasena());
        if (usuarioEncontrado != null) {
            return ResponseEntity.ok(usuarioEncontrado);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PutMapping(value = "/usuarios/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario actualizarUsuario(@PathVariable int id, @RequestBody Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioService.obtenerUsuarioPorId(id); 
        if (usuarioExistente != null) {
            // Actualizar los campos del usuario
            usuarioExistente.setNombre(usuarioActualizado.getNombre());
            usuarioExistente.setDocumento(usuarioActualizado.getDocumento());
            usuarioExistente.setCorreo(usuarioActualizado.getCorreo());
            usuarioExistente.setTelefono(usuarioActualizado.getTelefono());
            usuarioExistente.setContrasena(usuarioActualizado.getContrasena());
            usuarioExistente.setPerfil(usuarioActualizado.getPerfil());
            usuarioExistente.setEstado(usuarioActualizado.getEstado());
            return usuarioService.guardarUsuario(usuarioExistente); 
        }
        return null; // Manejar el caso en que el usuario no exista
    }

    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable int id) {
        usuarioService.eliminarUsuario(id);
        return ResponseEntity.noContent().build(); // Respuesta 204 No Content
    }

}
