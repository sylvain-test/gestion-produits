import com.example.inventorymanagement.entity.AuditLog;
import com.example.inventorymanagement.service.AuditLogService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/audit-logs")
public class AuditLogController {

    private final AuditLogService auditLogService;

    public AuditLogController(AuditLogService auditLogService) {
        this.auditLogService = auditLogService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        return ResponseEntity.ok(auditLogService.getAllLogs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuditLog> getLogById(@PathVariable Long id) {
        return ResponseEntity.ok(auditLogService.getLogById(id));
    }

    // Si on souhaite créer un audit log manuellement
    @PostMapping
    public ResponseEntity<AuditLog> createAuditLog(@RequestBody AuditLog log) {
        return ResponseEntity.ok(auditLogService.createLog(log));
    }

    // Souvent, on n'autorise pas la suppression ni la mise à jour d'un audit log
}