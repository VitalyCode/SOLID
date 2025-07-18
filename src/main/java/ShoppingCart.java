import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private final List<CartItem> items = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        // Check if the product is already in the cart
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                item.increaseQuantity(quantity);
                return;
            }
        }
        items.add(new CartItem(product, quantity));
    }

    public void removeItem(Product product, int quantity) {
        items.removeIf(item -> item.getProduct().equals(product));
    }

    public double calculateTotal() {
        return items.stream().mapToDouble(CartItem::getTotalPrice).sum();
    }

    public List<CartItem> getItems() {
        return new ArrayList<>(items); // Return a copy
    }

    public void clear() {
        items.clear();
    }

    @Override
    public String toString() {
        return "Корзина для покупок{" +
                "Предметы =" + items +
                '}';
    }

    // Inner class for cart items
    public static class CartItem {
        private final Product product;
        private int quantity;

        public CartItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

        public void increaseQuantity(int quantity) {
            this.quantity += quantity;
        }

        public double getTotalPrice() {
            return product.getPrice() * quantity;
        }

        @Override
        public String toString() {
            return "Карточка{" +
                    "Продукты=" + product +
                    ", количество=" + quantity +
                    '}';
        }
    }
}
