import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryCatalog implements Catalog {

    private final List<Product> products = new ArrayList<>();

    public InMemoryCatalog() {
        products.add(new Product("1", "Ноутбук", 1200.00, "Высокопроизводительный ноутбук", "Dell"));
        products.add(new Product("2", "Мышка", 25.00, "gaming mouse", "Logitech"));
        products.add(new Product("3", "Клавиатура", 75.00, "Механическая клавиатура", "Corsair"));
    }


    @Override
    public List<Product> searchProducts(String keyword) {
        return products.stream()
                .filter(product -> product.getName().toLowerCase().contains(keyword.toLowerCase()) ||
                        product.getDescription().toLowerCase().contains(keyword.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterProductsByPrice(double minPrice, double maxPrice) {
        return products.stream()
                .filter(product -> product.getPrice() >= minPrice && product.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> filterProductsByManufacturer(String manufacturer) {
        return products.stream()
                .filter(product -> product.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    @Override
    public Product getProductById(String id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null); // Or throw an exception if product not found
    }

    @Override
    public List<Product> getAllProducts() {
        return new ArrayList<>(products); // Return a copy to prevent external modification
    }
}
