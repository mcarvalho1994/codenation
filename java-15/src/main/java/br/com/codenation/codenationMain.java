package br.com.codenation;

import br.com.codenation.model.OrderItem;
import br.com.codenation.service.OrderServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class codenationMain {
    public static void main(String[] args) {

        OrderServiceImpl order = new OrderServiceImpl();
        List<OrderItem> items = new ArrayList<>();
        items.add(new OrderItem(1l, 3l));
        items.add(new OrderItem(2l, 2l));
        System.out.println(order.calculateOrderValue(items));
    }
}
