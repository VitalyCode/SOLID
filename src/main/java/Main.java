import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[]args){
        Catalog catalog = new InMemoryCatalog();
        ShoppingCart cart = new ShoppingCart();
        PaymentService paymentService = new CreditCardPaymentService();
        DeliveryService deliveryService = new DummyDeliveryService();
        UserService userService = new UserService();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя пользователя:");
        String username = scanner.nextLine();
        System.out.println("Введите пароль:");
        String password = scanner.nextLine();

        if (!userService.isValidUser(username, password)) {
            System.out.println("Неверные учетные данные. Выходящий.");
            return;
        }

        System.out.println("Добро пожаловать в наш интернет-магазин!");

        while (true) {
            System.out.println("\nВыберите действие:");
            System.out.println("1. Просмотр товаров");
            System.out.println("2. Поиск товаров");
            System.out.println("3. Добавить в корзину");
            System.out.println("4. Просмотреть корзину");
            System.out.println("5. Оформить заказ");
            System.out.println("6. Отслеживать заказ");
            System.out.println("7. Выход");

            System.out.print("Введите свой выбор: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    List<Product> allProducts = catalog.getAllProducts();
                    if (allProducts.isEmpty()) {
                        System.out.println("Нет доступных продуктов.");
                    } else {
                        System.out.println("Доступные продукты:");
                        for (Product product : allProducts) {
                            System.out.println(product);
                        }
                    }
                    break;
                case "2":
                    System.out.print("Введите ключевое слово для поиска: ");
                    String keyword = scanner.nextLine();
                    List<Product> searchResults = catalog.searchProducts(keyword);
                    if (searchResults.isEmpty()) {
                        System.out.println("Не найдено товаров, соответствующих вашему запросу.");
                    } else {
                        System.out.println("Результаты поиска:");
                        for (Product product : searchResults) {
                            System.out.println(product);
                        }
                    }
                    break;
                case "3":
                    System.out.print("Введите код товара для добавления в корзину: ");
                    String productId = scanner.nextLine();
                    Product product = catalog.getProductById(productId);
                    if (product == null) {
                        System.out.println("Товар не найден.");
                    } else {
                        System.out.print("Введите количество: ");
                        int quantity = Integer.parseInt(scanner.nextLine());
                        cart.addItem(product, quantity);
                        System.out.println(quantity + " " + product.getName() + "добавлены в корзину.");
                    }
                    break;
                case "4":
                    List<ShoppingCart.CartItem> cartItems = cart.getItems();
                    if (cartItems.isEmpty()) {
                        System.out.println("Ваша корзина пуста.");
                    } else {
                        System.out.println("корзина покупок:");
                        for (ShoppingCart.CartItem item : cartItems) {
                            System.out.println(item.getProduct().getName() + " - Количество: " + item.getQuantity());
                        }
                        System.out.println("Сумма: $" + cart.calculateTotal());
                    }
                    break;
                case "5":
                    if (cart.getItems().isEmpty()) {
                        System.out.println("Ваша корзина пуста. Добавьте товары перед оформлением заказа.");
                    } else {
                        double total = cart.calculateTotal();
                        System.out.println("Общая сумма: $" + total);
                        System.out.print("Введите платежные реквизиты (например, номер кредитной карты).");
                        String paymentDetails = scanner.nextLine();
                        if (paymentService.processPayment(total, paymentDetails)) {
                            System.out.println("Оплата прошла успешно!");
                            Order order = new Order(cart.getItems(), total, username); // Assuming username is the customer ID
                            System.out.println("Заказ успешно размещен. Идентификатор заказа:" + order.getOrderId());
                            cart.clear(); // Clear the cart after successful checkout
                        } else {
                            System.out.println("Оплата не была произведена.");
                        }
                    }
                    break;
                case "6":
                    System.out.print("Введите идентификатор заказа для отслеживания: ");
                    String orderId = scanner.nextLine();
                    String trackingInfo = deliveryService.trackOrder(orderId);
                    System.out.println(trackingInfo);
                    break;
                case "7":
                    System.out.println("Спасибо вам за покупки вместе с нами!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор. Пожалуйста, попробуйте снова.");
            }
        }
    }
}
