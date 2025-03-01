package com.example.inventorymanagement.service;

import com.example.inventorymanagement.entity.AuditLog;
import com.example.inventorymanagement.repository.AuditLogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.inventorymanagement.exception.ResourceNotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class AuditLogService {

    private final AuditLogRepository auditLogRepository;

    public AuditLogService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public AuditLog createLog(String action, String details, Long userId) {
        AuditLog log = new AuditLog();
        log.setAction(action);
        log.setDateAction(LocalDateTime.now());
        log.setDetails(details);
        // setDoneBy(...) si vous voulez lier à un utilisateur existant
        return auditLogRepository.save(log);
    }

    public AuditLog createLog(AuditLog log) {
        // ou version plus brute
        if (log.getDateAction() == null) {
            log.setDateAction(LocalDateTime.now());
        }
        return auditLogRepository.save(log);
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    public AuditLog getLogById(Long id) {
        return auditLogRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log introuvable (ID : " + id + ")"));
    }

    // Souvent on évite de mettre update ou delete pour un audit log,
    // car c'est supposé être un enregistrement immuable.
}