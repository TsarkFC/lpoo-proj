package model;

public class SpecialCard {
    private int cost;
    private char symbol;
    private String cardInfo;

    public SpecialCard(int cost, char symbol, String cardInfo){
        this.cost = cost;
        this.symbol = symbol;
        this.cardInfo = cardInfo;
    }

    public int getCost() {
        return cost;
    }

    public char getSymbol() {
        return symbol;
    }

    public String getCardInfo() {
        return cardInfo;
    }
}
