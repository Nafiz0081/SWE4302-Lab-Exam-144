public class Sprinkles extends Topping{

    private Double price=0.30;
    @Override
    public String getName() {
        return "Sprinkles";
    }
    @Override
    public double getPrice() {
        return price;
    }
}
