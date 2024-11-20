import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<OrderItem> items;
    private IceCreamContainer container;

    public Order(IceCreamContainer container) {
        this.items = new ArrayList<>();
        this.container = container;
    }

    public void addItem(OrderItem item) {
        items.add(item);
    }

    public double calculateSubtotal() {
        double subtotal = container.getPrice();
        for (OrderItem item : items) {
            subtotal += item.calculatePrice();
        }
        return subtotal;
    }

    public double calculateTax() {
        return calculateSubtotal() * 0.08;
    }

    public double calculateTotal() {
        return calculateSubtotal() + calculateTax();
    }

    public List<String> getOrderDetails() {
        List<String> details = new ArrayList<>();
        for (OrderItem item : items) {
            details.add(item.getDescription());
        }
        details.add("Subtotal: $" + String.format("%.2f", calculateSubtotal()));
        details.add("Tax: $" + String.format("%.2f", calculateTax()));
        details.add("Total Amount Due: $" + String.format("%.2f", calculateTotal()));
        return details;
    }
}