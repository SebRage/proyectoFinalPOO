package co.edu.tk.dao;

import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.tk.model.Ticket;

@Repository
public interface TicketRepository extends JpaRepository <Ticket, Integer> {
}
