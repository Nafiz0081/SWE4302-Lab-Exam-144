public class ChocolateChips extends Topping{

    private Double price=0.50;
    @Override
    public String getName() {
        return "Chocolate Chips";
    }
    @Override
    public double getPrice() {
        return price;
    }

}
