package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.Supplier;
import com.example.inventorymanagement.repository.SupplierRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.util.List;

@Service
@Transactional
public class SupplierService {

    private final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier createSupplier(Supplier supplier) {
        // Exemple : vérifier si le nom existe déjà
        // Optional<Supplier> existing = supplierRepository.findByName(supplier.getName());
        // ...
        return supplierRepository.save(supplier);
    }

    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    public Supplier getSupplierById(Long id) {
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fournisseur introuvable (ID : " + id + ")"));
    }

    public Supplier updateSupplier(Long id, Supplier updatedSup) {
        Supplier existing = getSupplierById(id);
        existing.setName(updatedSup.getName());
        existing.setContactInfo(updatedSup.getContactInfo());
        return supplierRepository.save(existing);
    }

    public void deleteSupplier(Long id) {
        Supplier sup = getSupplierById(id);
        supplierRepository.delete(sup);
    }
}