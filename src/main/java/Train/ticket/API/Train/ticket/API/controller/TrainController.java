package Train.ticket.API.Train.ticket.API.controller;

import Train.ticket.API.Train.ticket.API.entity.Ticket;
import Train.ticket.API.Train.ticket.API.entity.User;
import Train.ticket.API.Train.ticket.API.service.TrainService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/train")
@RequiredArgsConstructor
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping("/purchase")
    public ResponseEntity<Ticket> purchaseTicket(@RequestBody User user) {
        Ticket ticket = null;
        if(user!=null) {
             ticket = trainService.purchaseTicket(user);
        }
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/receipt/{email}")
    public ResponseEntity<Ticket> getReceipt(@PathVariable String email) {
        Ticket ticket = trainService.getReceipt(email);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/users/{section}")
    public ResponseEntity<Map<String, Ticket>> getUsersBySection(@PathVariable String section) {
        Map<String, Ticket> usersBySection = trainService.getUsersBySection(section);
        return ResponseEntity.ok(usersBySection);
    }

    @DeleteMapping("/remove/{email}")
    public ResponseEntity<Void> removeUser(@PathVariable String email) {
        trainService.removeUser(email);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/modify-seat")
    public ResponseEntity<Void> modifySeat(@RequestParam String email, @RequestParam String newSeat) {
        trainService.modifySeat(email, newSeat);
        return ResponseEntity.noContent().build();
    }
}
