package com.furk.otel.controller;

import com.furk.otel.entity.CustomerOrder;
import com.furk.otel.service.CustomerService;
import com.furk.otel.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class CustomerOrderController {
    @Autowired
    private CustomerOrderService customerOrderService;
    @Autowired
    private CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerOrder> createOrder(@RequestBody CustomerOrder order) {
        return customerService.getCustomer(order.getCustomer().getId())
                .map(customer -> {
                    order.setCustomer(customer);
                    return ResponseEntity.ok(customerOrderService.saveOrder(order));
                })
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerOrder> getOrderById(@PathVariable Long id) {
        return customerOrderService.getOrder(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CustomerOrder> getAllOrders() {
        return customerOrderService.getAllOrders();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerOrder> updateOrder(@PathVariable Long id, @RequestBody CustomerOrder orderDetails) {
        return customerOrderService.getOrder(id)
                .map(order -> {
                    order.setOrderDate(orderDetails.getOrderDate());
                    order.setStatus(orderDetails.getStatus());
                    return ResponseEntity.ok(customerOrderService.saveOrder(order));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        customerOrderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }
}

