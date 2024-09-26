package co.edu.tk.dao;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.tk.model.Prioridad;

@Repository
public interface PrioridadRepository extends JpaRepository<Prioridad, Integer> {
}
