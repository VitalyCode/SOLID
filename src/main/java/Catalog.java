import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public interface Catalog {
    List<Product> searchProducts(String keyword);
    List<Product> filterProductsByPrice(double minPrice, double maxPrice);
    List<Product> filterProductsByManufacturer(String manufacturer);
    Product getProductById(String id); // Добавлено
    List<Product> getAllProducts(); // Добавлено
}
