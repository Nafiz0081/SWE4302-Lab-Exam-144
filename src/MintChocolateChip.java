public class MintChocolateChip extends Flavor {

    private double pricePerScoop=2.80;
    @Override
    public String getName() {
        return "Mint Chocolate Chip";
    }
    @Override
    public double getPricePerScoop() {
        return pricePerScoop;
    }
}