package Train.ticket.API.Train.ticket.API;

import Train.ticket.API.Train.ticket.API.entity.User;
import Train.ticket.API.Train.ticket.API.service.TrainService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
	@AutoConfigureMockMvc
	public class TrainTicketApiApplicationTests {

		@Autowired
		private MockMvc mockMvc;

		@Autowired
		private TrainService trainService;

		@Test
		@Transactional
		public void purchaseTicketTest() throws Exception {
			String userJson = "{\"firstName\":\"Ayush\",\"lastName\":\"Kumar\",\"email\":\"ayushsj17@gmail.com\"}";
			mockMvc.perform(MockMvcRequestBuilders.post("/api/train/purchase")
							.contentType(MediaType.APPLICATION_JSON)
							.content(userJson))
					.andExpect(status().isOk())
					.andExpect(jsonPath("$.user.firstName").value("Ayush"));
		}

	@Test
	public void getReceiptTest() throws Exception {
		// Create a user and purchase a ticket before the test
		User user = new User();
		user.setFirstName("Jane");
		user.setLastName("Doe");
		user.setEmail("jane.doe@example.com");
		trainService.purchaseTicket(user);

		// Fetch the receipt
		mockMvc.perform(MockMvcRequestBuilders.get("/api/train/receipt/jane.doe@example.com"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.user.firstName").value("Jane"))
				.andExpect(jsonPath("$.user.ticket").doesNotExist());
	}

		@Test
		public void removeUserTest() throws Exception {
			User user = new User();
			user.setFirstName("Hitesh");
			user.setLastName("Mehar");
			user.setEmail("hmehar98@gmail.com");
			trainService.purchaseTicket(user);

			mockMvc.perform(MockMvcRequestBuilders.delete("/api/train/remove/hmehar98@gmail.com"))
					.andExpect(status().isNoContent());
		}

		@Test
		public void modifySeatTest() throws Exception {
			User user = new User();
			user.setFirstName("Ayush");
			user.setLastName("Kumar");
			user.setEmail("ayushsj17@gmail.com");
			trainService.purchaseTicket(user);

			mockMvc.perform(MockMvcRequestBuilders.put("/api/train/modify-seat")
							.param("email", "ayushsj17@gmail.com")
							.param("newSeat", "B1"))
					.andExpect(status().isNoContent());
		}
	}
