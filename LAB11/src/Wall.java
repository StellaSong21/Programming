public class Wall extends Place {

    @Override
    public boolean isWalkable() {
        return false;
    }

    @Override
    public char getIcon() {
        return '\u2587';
    }

}
