package co.edu.tk.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.tk.model.Evento;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
}
