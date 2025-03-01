import com.example.inventorymanagement.entity.StockMovement;
import com.example.inventorymanagement.service.StockMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;

    public StockMovementController(StockMovementService stockMovementService) {
        this.stockMovementService = stockMovementService;
    }

    @GetMapping
    public ResponseEntity<List<StockMovement>> getAllMovements() {
        return ResponseEntity.ok(stockMovementService.getAllMovements());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMovement> getMovementById(@PathVariable Long id) {
        return ResponseEntity.ok(stockMovementService.getMovementById(id));
    }

    @PostMapping
    public ResponseEntity<StockMovement> createMovement(@RequestBody StockMovement movement) {
        StockMovement created = stockMovementService.createMovement(movement);
        return ResponseEntity.ok(created);
    }

    // Souvent, on ne met pas de updateMovement, car un mouvement historique ne se modifie pas
    // Mais vous pouvez en ajouter un si nécessaire

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovement(@PathVariable Long id) {
        stockMovementService.deleteMovement(id);
        return ResponseEntity.noContent().build();
    }
}