package br.com.fiap.orderservice;

import br.com.fiap.orderservice.controller.OrderController;
import br.com.fiap.orderservice.dao.OrderDao;
import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.model.Payment;
import br.com.fiap.orderservice.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderController.class)
public class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mvc;
	@MockBean
	private OrderDao repository;

	@Test
	public void foundOrder() throws Exception {
		int id = 1;
		Order order = new Order();
		order.setId(1);
		when(this.repository.findById(id)).thenReturn(order);
		mvc.perform(get("/orders/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(response -> {
					String json = response.getResponse().getContentAsString();
					Order orderObj = new ObjectMapper().readValue(json, Order.class);
					assertThat(order.getId()).isEqualToComparingFieldByField(orderObj.getId());
				});
	}

	@Test
	public void notFoundOrder() throws Exception {
		int id = 0;
		Order order = new Order();
		order.setId(1);
		when(this.repository.findById(id)).thenReturn(order);
		mvc.perform(get("/order/" + id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
	}

	@Test
	public void createOrder() throws Exception {
		Order order = this.getOrderForTest(150);

		when(this.repository.save(order)).thenReturn(order);
		mvc.perform(
				post("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk())
				.andExpect(content().string("http://localhost/orders/"+ order.getId()));;
	}

	@Test
	public void failCreateOrder() throws Exception {
		Order order = this.getOrderForTest(1);

		when(repository.findById(order.getId())).thenReturn(order);

		mvc.perform(
				post("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isConflict());
	}

	@Test
	public void updateOrder() throws Exception {
		Order order = this.getOrderForTest(1);

		//when(this.repository.save(order)).thenReturn(order);
		mvc.perform(
				put("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isOk());
	}

	@Test
	public void failUpdateOrder() throws Exception {
		Order order = this.getOrderForTest(190);

		when(this.repository.update(order)).thenReturn(null);
		mvc.perform(
				put("/orders")
						.contentType(MediaType.APPLICATION_JSON)
						.content(new ObjectMapper().writeValueAsString(order)))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void deleteOrder() throws Exception {
		Order order = new Order();
		order.setId(44);

		mvc.perform(
				delete("/orders/{id}", order.getId()))
				.andExpect(status().isOk());
	}

	@Test
	public void failDeleteOrder() throws Exception {
		Order order = new Order();
		order.setId(105);

		when(repository.findById(order.getId())).thenReturn(null);

		mvc.perform(
				delete("/orders/{id}", order.getId()))
				.andExpect(status().isNotFound());
	}

	private Order getOrderForTest(int id){
		Order order = new Order();
		order.setId(id);
		order.setFullName("Edgar Cardote");
		order.setEmail("exemplo@exemplo.com");
		order.setShippingAddress("Rua Exemplo, 23, Exemplo-Ex");
		order.setDate("22/02/1993");
		order.setStatus("Aprovado");

		Payment payment = new Payment();
		payment.setId(id);
		payment.setCardNumber("1234 4321 4567 7654");
		payment.setExpiringDate("02/1922");
		payment.setCardFlag("Visa");

		order.setPayment(payment);

		Product products[] = new Product[4];

		BigDecimal total = new BigDecimal(0);

		for(int i = 0; i < products.length; i++){
			products[i] = new Product();
			products[i].setIdOrder(id);
			products[i].setDescription("Item A"+ i);
			products[i].setAmount(1);
			products[i].setValue(new BigDecimal(52.33*(i+1)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
			total = total.add(products[i].getValue().setScale(2, BigDecimal.ROUND_HALF_EVEN));
		}
		order.setTotal(total);
		order.setProducts(products);

		return order;
	}
}
