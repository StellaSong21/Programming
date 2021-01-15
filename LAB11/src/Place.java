public abstract class Place implements HasIcon{
    protected int row,col;

    public abstract boolean isWalkable();

    public void setRow(int row){
        this.row = row;
    }

    public void setCol(int col){
        this.col = col;
    }
}
