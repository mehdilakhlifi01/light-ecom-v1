package org.sid.lightecomv1.web;

import lombok.Data;
import org.sid.lightecomv1.entities.Client;
import org.sid.lightecomv1.entities.Product;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Data
public class OrderForm {
    public Client client=new Client();
    public List<OrderProduct> products=new ArrayList<>();
}
@Data
class OrderProduct{
    private int quantity;
    private double totlaAmount;
    public Product products;

}

