package Train.ticket.API.Train.ticket.API.entity;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "\"ticket\"")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLocation;
    private String toLocation;
    @OneToOne
    @JoinColumn(name = "user_id", unique = true ,referencedColumnName = "id")
    @JsonManagedReference
    private User user;
    private double price;
    private String seat;
}
