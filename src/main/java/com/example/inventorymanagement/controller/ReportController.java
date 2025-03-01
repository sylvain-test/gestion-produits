import com.example.inventorymanagement.entity.Product;
import com.example.inventorymanagement.repository.ProductRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    private final ProductRepository productRepository;

    public ReportController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping(value="/products/csv", produces="text/csv")
    public void exportProductsToCsv(HttpServletResponse response) throws IOException {
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"products.csv\"");

        List<Product> products = productRepository.findAll();
        PrintWriter writer = response.getWriter();
        writer.println("ID,Name,SKU,Quantity");

        for (Product p : products) {
            // Si vous avez un getQuantity quelque part (via StockService) ou un calcul...
            // Sinon, on affiche juste ID, name, SKU
            writer.println(p.getId() + "," + p.getName() + "," + p.getSku() + ", ...");
        }
        writer.flush();
        writer.close();
    }
}