public class CreditCardPaymentService implements PaymentService {
    @Override
    public boolean processPayment(double amount, String paymentDetails) {
        // Simulate credit card processing
        System.out.println("Обработка платежей по кредитным картам " + amount);
        return true; // In real implementation, you'd integrate with a payment gateway
    }
}
