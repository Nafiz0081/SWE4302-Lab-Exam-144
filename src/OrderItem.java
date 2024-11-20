import java.util.List;

public class OrderItem {
    private Flavor flavor;
    private int scoops;
    private List<Topping> toppings;

    public OrderItem(Flavor flavor, int scoops, List<Topping> toppings) {
        this.flavor = flavor;
        this.scoops = scoops;
        this.toppings = toppings;
    }

    public double calculatePrice() {
        double total = flavor.getPricePerScoop() * scoops;
        for (Topping topping : toppings) {
            total += topping.getPrice();
        }
        return total;
    }

    public String getDescription() {
        StringBuilder description = new StringBuilder();
        description.append(flavor.getName()).append(" - ").append(scoops)
                .append(" scoop: $").append(String.format("%.2f", flavor.getPricePerScoop() * scoops));
        for (Topping topping : toppings) {
            description.append("\n").append(topping.getName()).append(" - 1 time: $")
                    .append(String.format("%.2f", topping.getPrice()));
        }
        return description.toString();
    }
}
