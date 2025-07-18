import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Order {
    private String orderId;
    private List<ShoppingCart.CartItem> items;
    private double totalAmount;
    private String customerId; // Or User object
    private LocalDateTime orderDate;
    private String status; // e.g., "Pending", "Shipped", "Delivered"

    public Order(List<ShoppingCart.CartItem> items, double totalAmount, String customerId) {
        this.orderId = UUID.randomUUID().toString(); // Unique order ID
        this.items = items;
        this.totalAmount = totalAmount;
        this.customerId = customerId;
        this.orderDate = LocalDateTime.now();
        this.status = "Ожидаемый";
    }

    // Getters and setters for all fields
    public String getOrderId() {
        return orderId;
    }

    public List<ShoppingCart.CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", items=" + items +
                ", totalAmount=" + totalAmount +
                ", customerId='" + customerId + '\'' +
                ", orderDate=" + orderDate +
                ", status='" + status + '\'' +
                '}';
    }
}
