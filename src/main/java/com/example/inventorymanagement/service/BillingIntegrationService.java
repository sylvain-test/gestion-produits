package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Order;
import org.springframework.stereotype.Service;


@Service
public class BillingIntegrationService {

    public void createInvoiceForSale(Order saleOrder) {
        // Ex: appel REST vers un système de facturation
        // RestTemplate rest = new RestTemplate();
        // rest.postForObject("http://billing-system/api/invoices", saleOrder, InvoiceResponse.class);

        System.out.println("Facture créée pour la commande " + saleOrder.getId());
    }
}