import com.example.inventorymanagement.entity.Stock;
import com.example.inventorymanagement.repository.StockRepository;
import com.example.inventorymanagement.service.OrderService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockAlertService {

    private final StockRepository stockRepository;
    private final OrderService orderService; // pour éventuellement créer des commandes auto
    // private final EmailService etc. si vous voulez envoyer des mails

    public StockAlertService(StockRepository stockRepository, OrderService orderService) {
        this.stockRepository = stockRepository;
        this.orderService = orderService;
    }

    // Vérification planifiée (ex: toutes les 2h). On peut ajuster la cron expression.
    @Scheduled(cron = "0 0 */2 * * *")
    public void checkStocks() {
        List<Stock> allStocks = stockRepository.findAll();
        for (Stock s : allStocks) {
            if (s.getQuantity() < s.getMinQuantity()) {
                // Exemple d’alerte console ou logger:
                System.out.println("[ALERTE] Stock bas pour le produit "
                        + s.getProduct().getName()
                        + ". Quantité: " + s.getQuantity());

                // Optionnel: créer automatiquement une commande d’achat 
                // à un fournisseur par défaut si besoin
                // orderService.createAutomaticPurchase(s.getProduct(), s.getMinQuantity());
            } else if (s.getQuantity() > s.getMaxQuantity()) {
                System.out.println("[ALERTE] Surstock possible pour "
                        + s.getProduct().getName()
                        + ". Quantité: " + s.getQuantity());
            }
        }
    }
}