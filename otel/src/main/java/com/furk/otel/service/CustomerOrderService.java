package com.furk.otel.service;

import com.furk.otel.exception.ResourceNotFoundException;
import com.furk.otel.entity.CustomerOrder;
import com.furk.otel.entity.Customer;
import com.furk.otel.repository.CustomerOrderRepository;
import com.furk.otel.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public CustomerOrder createOrder(CustomerOrder order) {
        Customer customer = customerRepository.findById(order.getCustomer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id " + order.getCustomer().getId()));
        order.setCustomer(customer);
        return customerOrderRepository.save(order);
    }

    public CustomerOrder getOrder(Long id) {
        return customerOrderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id " + id));
    }

    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    public CustomerOrder updateOrder(Long id, CustomerOrder orderDetails) {
        CustomerOrder order = getOrder(id);
        order.setOrderDate(orderDetails.getOrderDate());
        order.setStatus(orderDetails.getStatus());
        return customerOrderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        CustomerOrder order = getOrder(id);
        customerOrderRepository.delete(order);
    }
}

