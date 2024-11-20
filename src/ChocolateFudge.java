
public class ChocolateFudge extends Flavor {

    private double pricePerScoop=3.00;
    @Override
    public String getName() {
        return "Chocolate Fudge";
    }
    @Override
    public double getPricePerScoop() {
        return pricePerScoop;
    }
}
