package org.sid.lightecomv1.web;

import org.sid.lightecomv1.entities.Client;
import org.sid.lightecomv1.entities.Order;
import org.sid.lightecomv1.entities.OrderItem;
import org.sid.lightecomv1.entities.Product;
import org.sid.lightecomv1.repository.ClientRepository;
import org.sid.lightecomv1.repository.OrderItemRepository;
import org.sid.lightecomv1.repository.OrderRepository;
import org.sid.lightecomv1.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@CrossOrigin("*")
@RestController
public class OrderController {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @PostMapping("/orders")
    public Order saveOrder(@RequestBody OrderForm orderForm){

        System.out.println(orderForm);


        Client client=new Client();
        client.setName(orderForm.getClient().getName());
        client.setEmail(orderForm.getClient().getEmail());
        client.setAddress(orderForm.getClient().getAddress());
        client.setPhoneNumber(orderForm.getClient().getPhoneNumber());
        client.setUsername(orderForm.getClient().getUsername());
        client=clientRepository.save(client);
        System.out.println(client.getId());

        Order order=new Order();
        order.setClient(client);
        order.setId(client.getId());
        order.setDate(new Date());
        System.out.println(order);
        order=orderRepository.save(order);
        double total=0;
       for(OrderProduct p:orderForm.getProducts()){
            OrderItem orderItem=new OrderItem();
            orderItem.setOrder(order);
           System.out.println(p.products.getId());
             Product product=productRepository.findById(p.products.getId()).get();
            orderItem.setProduct(product);
            orderItem.setTotalAmount(product.getCurrentPrice());
            orderItem.setQuantity(p.getQuantity());
            orderItemRepository.save(orderItem);
            total+=p.getQuantity()*product.getCurrentPrice();
        }
        order.setTotalAmount(total);
        return orderRepository.save(order);
    }
}
