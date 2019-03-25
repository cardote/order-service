package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.dao.OrderDao;
import br.com.fiap.orderservice.model.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@RestController
public class OrderController {

    private Order[] orders;
    private OrderDao daoOrder;

    public OrderController() {
        daoOrder = new OrderDao();
        orders = daoOrder.getOrders();

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") int id){
        return(ResponseEntity.ok(daoOrder.findById(id)));
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> save(@RequestBody() Order order){

        try {
            URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(daoOrder.save(order).getId()).toUri();
            return ResponseEntity.ok(location);
        } catch (Exception e) {
            return ResponseEntity.ok("Order already registered");
        }

    }

    @PutMapping("/orders")
    public ResponseEntity<Order> update(@RequestBody() Order order){
        return ResponseEntity.ok(daoOrder.update(order));
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") int id){
        return ResponseEntity.ok(daoOrder.delete(id));
    }

}
