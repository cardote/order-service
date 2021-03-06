package br.com.fiap.orderservice.dao;

import br.com.fiap.orderservice.exception.InternalErrorException;
import br.com.fiap.orderservice.model.Order;
import br.com.fiap.orderservice.model.Payment;
import br.com.fiap.orderservice.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDao {

    private Order[] orders = new Order[200];

    public OrderDao() {

        for(int k = 0; k < 100; k++){
            Order order = new Order();

            order.setId(k);
            order.setFullName("Nome Exemplo");
            order.setEmail("exemplo@exemplo.com");
            order.setShippingAddress("Rua Exemplo, 23, Exemplo-Ex");
            order.setDate("22/02/1993");
            order.setStatus("Aprovado");

            Payment payment = new Payment();
            payment.setId(k);
            payment.setCardNumber("1234 4321 4567 7654");
            payment.setExpiringDate("02/1922");
            payment.setCardFlag("Visa");

            order.setPayment(payment);

            Product products[] = new Product[4];

            BigDecimal total = new BigDecimal(0);

            for(int i = 0; i < products.length; i++){
                products[i] = new Product();
                products[i].setIdOrder(k);
                products[i].setDescription("Item A"+ i);
                products[i].setAmount(1);
                products[i].setValue(new BigDecimal(52.33*(i+1)).setScale(2, BigDecimal.ROUND_HALF_EVEN));
                total = total.add(products[i].getValue().setScale(2, BigDecimal.ROUND_HALF_EVEN));
            }
            order.setTotal(total);
            order.setProducts(products);
            orders[k] = order;
        }

    }

    public Order findById(int id) {
        try {
            return orders[id];
        } catch (Exception e){
            throw new InternalErrorException("Error: " + e);
        }

    }

    public Order save(Order order) {
        if(this.findById(order.getId()) != null){
            return null;
        } else {
            orders[order.getId()] = order;
            return order;
        }
    }

    public Order update(Order order) {
        int id = order.getId();
        if(this.findById(id) != null){
            orders[id].setFullName(order.getFullName());
            orders[id].setEmail(order.getEmail());
            orders[id].setDate(order.getDate());
            orders[id].setStatus(order.getStatus());
            orders[id].setShippingAddress(order.getShippingAddress());

            orders[id].setPayment(order.getPayment());
            orders[id].setProducts(order.getProducts());
            return orders[id];
        }
        return null;
    }

    public boolean delete(int id){
        if(this.findById(id) != null){
            orders[id] = null;
            return true;
        }
        return false;
    }

    public Order[] getOrders(){
        return this.orders;
    }
}
