package Model;

public class Card implements AnyCard{
    private int value;

    public Card(int value){
        this.value = value;
    }


    public int getValue() {
        return value;
    }
    public String getValueString(){
        return String.valueOf(value);
    }
}