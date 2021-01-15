public class Player extends Mob {
    private char order;
    private static int x = 1, y = 1;

    public void setOrder(char order) {
        this.order = order;
    }

    @Override
    public void move() {
        switch (order) {
            case 'w':
                x--;
                break;
            case 'a':
                y--;
                break;
            case 's':
                x++;
                break;
            case 'd':
                y++;
                break;
            default:

        }
    }

    @Override
    public void handleEvent() {

    }

    @Override
    public char getIcon() {
        return '\u2605';
    }

    void position() {

    }

    public static void main(String[] args) {

    }

}
