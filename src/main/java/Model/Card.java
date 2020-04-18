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

    @Override
    public void effect(GameParticipant part) {
        part.points += value;
        /*if(part.points >= part.max_points){
            EndTurn();
            ou
            player.turn_over = true;
        }*/
    }
}
