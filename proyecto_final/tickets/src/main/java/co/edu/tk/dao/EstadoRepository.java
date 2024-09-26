package co.edu.tk.dao;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.tk.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
	
}
