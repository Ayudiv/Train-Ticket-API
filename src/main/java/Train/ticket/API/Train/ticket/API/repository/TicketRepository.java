package Train.ticket.API.Train.ticket.API.repository;

import Train.ticket.API.Train.ticket.API.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Long> {
    Ticket findByUserEmail(String email);
}
