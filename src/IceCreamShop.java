import java.io.IOException;
import java.util.Arrays;

public class IceCreamShop {
    public static void main(String[] args) {

        Order order = new Order(new WaffleCone());


        order.addItem(new OrderItem(new ChocolateFudge(), 1, Arrays.asList(new ChocolateChips(), new FreshStrawberries())));
        order.addItem(new OrderItem(new MintChocolateChip(), 1, Arrays.asList()));


        System.out.println("Total: $" + String.format("%.2f", order.calculateTotal()));


        try {
            InvoiceGenerator.generateInvoice(order, "invoice.txt");
            System.out.println("Invoice generated successfully.");
        } catch (IOException e) {
            System.out.println("Failed to generate invoice.");
        }
    }
}
