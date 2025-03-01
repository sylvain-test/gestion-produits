package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Order;
import com.example.inventorymanagement.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final StockService stockService; // si on veut mettre à jour le stock
    private final BillingIntegrationService billingIntegrationService;


    public OrderService(OrderRepository orderRepository, StockService stockService) {
        this.orderRepository = orderRepository;
        this.stockService = stockService;
        this.billingIntegrationService = billingIntegrationService;

    }

    public Order createOrder(Order order) {
        // Ex: si c'est une commande de type "SALE" (vente), on peut décrémenter le stock
        if ("SALE".equalsIgnoreCase(order.getType())) {
            // stockService.decreaseStock(order.getProduct(), locationId, order.getQuantity());
            // ou un autre mécanisme pour sélectionner l'emplacement
        }
        order.setStatus("EN_COURS");
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commande introuvable (ID : " + id + ")"));
    }

    public Order updateOrder(Long id, Order updatedOrder) {
        Order existing = getOrderById(id);
        existing.setType(updatedOrder.getType());
        existing.setQuantity(updatedOrder.getQuantity());
        existing.setStatus(updatedOrder.getStatus());
        existing.setOrderDate(updatedOrder.getOrderDate());
        existing.setDeliveryDate(updatedOrder.getDeliveryDate());
        existing.setCustomer(updatedOrder.getCustomer());
        existing.setSupplier(updatedOrder.getSupplier());
        existing.setProduct(updatedOrder.getProduct());
        return orderRepository.save(existing);
    }

    public void deleteOrder(Long id) {
        Order ord = getOrderById(id);
        orderRepository.delete(ord);
    }
    public Order makeOrder(Order order) {
        if ("SALE".equalsIgnoreCase(order.getType())) {
            // On décrémente le stock...
            // Puis on génère la facture
            billingIntegrationService.createInvoiceForSale(order);
        }
        // ...
    }

    /**
     * Exemple : changer le statut et déclencher maj stock si nécessaire.
     */
    public Order updateOrderStatus(Long id, String newStatus) {
        Order existing = getOrderById(id);
        existing.setStatus(newStatus);
        // Si newStatus == "LIVRE" et type == "PURCHASE", on peut incrémenter le stock, etc.
        return orderRepository.save(existing);
    }
}