public class Space extends Place{

    @Override
    public boolean isWalkable(){
        return true;
    }

    @Override
    public char getIcon(){
        return '\u00A0';
    }

}
