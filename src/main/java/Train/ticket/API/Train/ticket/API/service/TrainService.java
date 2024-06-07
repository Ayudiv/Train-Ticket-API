package Train.ticket.API.Train.ticket.API.service;

import Train.ticket.API.Train.ticket.API.entity.Ticket;
import Train.ticket.API.Train.ticket.API.entity.User;
import Train.ticket.API.Train.ticket.API.repository.TicketRepository;
import Train.ticket.API.Train.ticket.API.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class TrainService {

    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private UserRepository userRepository;


    private static final String[] SECTIONS = {"A", "B"};
    private int seatCounter = 1;

    @Transactional
    public Ticket purchaseTicket(User user){
        userRepository.save(user);
        String seat= assignSeat();
        Ticket ticket = new Ticket();
        ticket.setFromLocation("London");
        ticket.setToLocation("France");
        ticket.setUser(user);
        ticket.setPrice(20.0);
        ticket.setSeat(seat);
        ticketRepository.save(ticket);
        return ticket;
    }

    public Ticket getReceipt(String email) {
        return ticketRepository.findByUserEmail(email);
    }

    public Map<String, Ticket> getUsersBySection(String section) {
        Map<String, Ticket> sectionTickets = new HashMap<>();
        for (Ticket ticket : ticketRepository.findAll()) {
            if (ticket.getSeat().startsWith(section)) {
                sectionTickets.put(ticket.getUser().getEmail(), ticket);
            }
        }
        return sectionTickets;
    }

    public void removeUser(String email) {
        Ticket ticket = ticketRepository.findByUserEmail(email);
        if (ticket != null) {
            ticketRepository.delete(ticket);
        }
    }
    public void modifySeat(String email, String newSeat) {
        Ticket ticket = ticketRepository.findByUserEmail(email);
        if (ticket != null) {
            ticket.setSeat(newSeat);
            ticketRepository.save(ticket);
        }
    }

    private String assignSeat() {
        String section = SECTIONS[seatCounter % 2];
        return section + (seatCounter++);
    }
}
