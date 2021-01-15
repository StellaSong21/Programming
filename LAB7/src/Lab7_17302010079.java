import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Lab7_17302010079 {


    /**
     * 生命游戏入口
     *
     * @param cells 表示细胞图的二位数组
     */
    public static void start(int[][] cells) {
        Scanner scanner = new Scanner(System.in);
        int generation = 1;
        while (true) {
            System.out.println("generation:" + generation);
            printCells(cells);
            System.out.println("输入任意键产生下一代。。。");
            String input = scanner.nextLine();
            cells = transform(cells);
            generation++;
        }
    }

    /**
     * 统计cells[x][y]细胞周围活着的个数
     *
     * @param cells 细胞结构数组
     * @param x     横坐标
     * @param y     纵坐标
     * @return cells[x][y]细胞周围活着的个数
     */
    public static int findLifedNum(int[][] cells, int x, int y) {
        int num = 0;
        //左边
        if(y != 0){
            num += cells[x][y-1];
        }
        //左上角
        if(x != 0 && y != 0){
            num += cells[x-1][y-1];
        }
        //上边
        if(x != 0){
            num += cells[x-1][y];
        }
        //右上
        if(x != 0 && y < cells[0].length - 1){
            num += cells[x-1][y+1];
        }
        //右边
        if(y != cells[0].length - 1){
            num += cells[x][y+1];
        }
        //右下
        if(x != cells.length - 1 && y != cells[0].length - 1){
            num += cells[x+1][y+1];
        }
        //下边
        if(x != cells.length - 1){
            num += cells[x+1][y];
        }
        //左下
        if(x < cells.length - 1 && y != 0){
            num += cells[x+1][y-1];
        }
        return num;
    }

    /**
     * 产生下一代
     *
     * @param cells 细胞结构数组
     * @return 新一代的细胞结构
     */
    public static int[][] transform(int[][] cells) {
        int[][] nextCells=new int[cells.length][cells[0].length];
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[0].length; y++) {
                int nearNum= findLifedNum(cells,x,y);
                //等于3，则下一状态总是活
                if(nearNum==3) {
                    nextCells[x][y] = 1;
                }
                //等于2，则与上一状态一样
                else if(nearNum==2){
                    nextCells[x][y]=cells[x][y];
                }
            }
        }
        cells = nextCells;
        return nextCells;
    }


    /**
     * 在控制台输出细胞结构
     *
     * @param cells 表示细胞图的二位数组
     */
    public static void printCells(int[][] cells) {
        for (int[] line : cells) {
            for (int cell : line) {
                System.out.print(cell == 0 ? "□" : "█");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        int width = scanner.nextInt();
        int height = scanner.nextInt();
        int[][] cells = new int[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                cells[i][j] = scanner.nextInt();
            }
        }
        scanner.close();
        start(cells);
    }
}
