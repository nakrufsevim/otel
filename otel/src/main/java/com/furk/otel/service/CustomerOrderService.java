package com.furk.otel.service;

import com.furk.otel.entity.CustomerOrder;
import com.furk.otel.repository.CustomerOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerOrderService {
    @Autowired
    private CustomerOrderRepository customerOrderRepository;

    public CustomerOrder saveOrder(CustomerOrder order) {
        return customerOrderRepository.save(order);
    }

    public Optional<CustomerOrder> getOrder(Long id) {
        return customerOrderRepository.findById(id);
    }

    public List<CustomerOrder> getAllOrders() {
        return customerOrderRepository.findAll();
    }

    public void deleteOrder(Long id) {
        customerOrderRepository.deleteById(id);
    }
}
