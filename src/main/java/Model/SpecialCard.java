package Model;

public class SpecialCard implements AnyCard{
    private int cost;

    public SpecialCard(int cost){
        this.cost = cost;
    }

    @Override
    public void effect(GameParticipant part) {

    }
}
