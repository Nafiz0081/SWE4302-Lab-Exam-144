import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;

public class TestIceCreamShop{

    @Test
    public void testSingleFlavorNoToppings() {
        Order order = new Order(new PaperCup());
        order.addItem(new OrderItem(new ChocolateFudge(), 2, Arrays.asList()));

        double expectedSubtotal = 3.00 * 2; // 2 scoops of Chocolate Fudge
        double expectedTax = expectedSubtotal * 0.08;
        double expectedTotal = expectedSubtotal + expectedTax;

        assertEquals(expectedSubtotal, order.calculateSubtotal(), 0.01);
        assertEquals(expectedTax, order.calculateTax(), 0.01);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }

    @Test
    public void testMultipleFlavorsWithToppings() {
        Order order = new Order(new WaffleCone());
        order.addItem(new OrderItem(new MintChocolateChip(), 1, Arrays.asList(new Sprinkles())));
        order.addItem(new OrderItem(new PistachioDelight(), 2, Arrays.asList(new CrushedOreo(), new Marshmallow())));

        double expectedSubtotal =
                5.00 + // Waffle Cone
                        (2.80 * 1) +
                        (3.25 * 2) +
                        0.30 + 0.85 + 0.70;
        double expectedTax = expectedSubtotal * 0.08;
        double expectedTotal = expectedSubtotal + expectedTax;

        assertEquals(expectedSubtotal, order.calculateSubtotal(), 0.01);
        assertEquals(expectedTax, order.calculateTax(), 0.01);
        assertEquals(expectedTotal, order.calculateTotal(), 0.01);
    }

    @Test
    public void testTaxCalculation() {
        Order order = new Order(new PaperCup());
        order.addItem(new OrderItem(new StrawberrySwirl(), 3, Arrays.asList(new FreshStrawberries())));

        double expectedSubtotal = (2.75 * 3) + 1.00;
        double expectedTax = expectedSubtotal * 0.08;

        assertEquals(expectedTax, order.calculateTax(), 0.01);
    }

    @Test
    public void testContainerPricing() {
        Order paperCupOrder = new Order(new PaperCup());
        paperCupOrder.addItem(new OrderItem(new ChocolateFudge(), 1, Arrays.asList()));

        Order waffleConeOrder = new Order(new WaffleCone());
        waffleConeOrder.addItem(new OrderItem(new ChocolateFudge(), 1, Arrays.asList()));

        assertEquals(0.00, paperCupOrder.calculateSubtotal() - (3.00), 0.01);
        assertEquals(5.00, waffleConeOrder.calculateSubtotal() - (3.00), 0.01);
    }

    @Test
    public void testInvoiceGeneration() {
        Order order = new Order(new PaperCup());
        order.addItem(new OrderItem(new MintChocolateChip(), 1, Arrays.asList(new ChocolateChips())));
        String fileName = "test_invoice.txt";

        try {
            InvoiceGenerator.generateInvoice(order, fileName);

         
            List<String> lines = Files.readAllLines(Paths.get(fileName));
            assertTrue(lines.contains("Mint Chocolate Chip - 1 scoop: $2.80"));
            assertTrue(lines.contains("Chocolate Chips - 1 time: $0.50"));
            assertTrue(lines.contains("Subtotal: $3.30"));
            assertTrue(lines.stream().anyMatch(line -> line.startsWith("Tax")));
            assertTrue(lines.stream().anyMatch(line -> line.startsWith("Total Amount Due")));
        } catch (IOException e) {
            fail("Invoice generation failed: " + e.getMessage());
        }
    }
}
