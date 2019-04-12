package br.com.fiap.orderservice.controller;

import br.com.fiap.orderservice.dao.OrderDao;
import br.com.fiap.orderservice.exception.OrderNotFoundException;
import br.com.fiap.orderservice.exception.OrderNotUpdatedException;
import br.com.fiap.orderservice.model.Order;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;

@Slf4j
@RestController
@Api(value = "Order", description = "a list of orders")
public class OrderController {

    private Order[] orders;
    private OrderDao daoOrder;

    public OrderController() {
        daoOrder = new OrderDao();
        orders = daoOrder.getOrders();

    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> findById(@PathVariable("id") int id){
        Order orderObj = daoOrder.findById(id);

        if (orderObj == null) {
            throw new OrderNotFoundException("ID não encontrado ou é invalido.");
        }
        return new ResponseEntity(orderObj, HttpStatus.OK);
    }

    @PostMapping("/orders")
    public ResponseEntity<Object> save(@RequestBody() Order order){

        Order orderObj = daoOrder.save(order);

        if(orderObj == null){
            return new ResponseEntity("Order já cadastrada", HttpStatus.CONFLICT);
        }

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(orderObj.getId()).toUri();
        return new ResponseEntity(location.toString(), HttpStatus.OK);
    }

    @PutMapping("/orders")
    public ResponseEntity<Order> update(@RequestBody() Order order){
        Order orderObj = daoOrder.update(order);

        if(orderObj == null) {
            throw  new OrderNotUpdatedException("Não foi possivel atualizar: order não encontrada");
        }

        return new ResponseEntity(orderObj, HttpStatus.OK);
    }
    @DeleteMapping("/orders/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){

        if(daoOrder.delete(id)){
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }
}
