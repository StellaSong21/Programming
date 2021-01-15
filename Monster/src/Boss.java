public class Boss extends Monster {
    @Override
    public void move() {
        goStraight();
        turnLeft();
        goStraight();
        turnLeft();
    }

    public void attack() {
    }

    ;

}
