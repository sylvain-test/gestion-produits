package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Customer;
import com.example.inventorymanagement.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        // Vérifier email unique
        customerRepository.findByEmail(customer.getEmail())
                .ifPresent(c -> {
                    throw new RuntimeException("Email déjà utilisé : " + customer.getEmail());
                });
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client introuvable (ID : " + id + ")"));
    }

    public Customer updateCustomer(Long id, Customer updatedCust) {
        Customer existing = getCustomerById(id);

        // Vérifier si on change l'email
        if (!existing.getEmail().equals(updatedCust.getEmail())) {
            customerRepository.findByEmail(updatedCust.getEmail())
                    .ifPresent(c -> {
                        throw new RuntimeException("Email déjà utilisé : " + updatedCust.getEmail());
                    });
        }

        existing.setName(updatedCust.getName());
        existing.setEmail(updatedCust.getEmail());
        existing.setAddress(updatedCust.getAddress());
        return customerRepository.save(existing);
    }

    public void deleteCustomer(Long id) {
        Customer cust = getCustomerById(id);
        customerRepository.delete(cust);
    }
}