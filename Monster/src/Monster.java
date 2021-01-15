public class Monster implements IsMovable {
    private int ATK;
    private int DEF;
    private int HP;
    public static int number;

    @Override
    public void move() {
    }

    ;

    void goStraight() {
    };

    void turnLeft(){};

    public void attack() {
    }

    ;

    public void setATK(int ATK) {
        this.ATK = ATK;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getDEF() {
        return DEF;
    }

    public int getHP() {
        return HP;
    }

    public int getATK() {
        return ATK;
    }
}
