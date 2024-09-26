package co.edu.tk.dao;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.tk.model.Perfil;

@Repository
public interface PerfilRepository extends JpaRepository<Perfil, Integer> {
	
}