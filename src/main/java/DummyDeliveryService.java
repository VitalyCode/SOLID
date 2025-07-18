public class DummyDeliveryService implements DeliveryService {
    @Override
    public String trackOrder(String orderId) {
        return "Заказ " + orderId + " находится в пути.";
    }
}

