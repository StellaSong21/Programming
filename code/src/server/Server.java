package server;

import client.Main;
import core.Direction;
import core.Icon;
import core.Map;
import core.Monster;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import javax.swing.*;
import java.io.*;
import java.util.Arrays;

import static core.Icon.*;

public class Server {
    private Icon[][] map;
    private int ATK = 5;
    private int DEF = 5;
    private int HP = 100;
    private Trace foot;
    private int numOfTrace;
    private int numOfMonster;
    private int numOfTreasure;

    //创建游戏
    public void createGame(int level) {
        foot = new Trace();
        Map maze = new Map(level);
        map = maze.getMap(level);

    }

    public Icon[][] getGameView(int level) {
        return map;
    }

    private int[] position(Icon[][] maze) {
        int[] position = new int[2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {
                if (maze[i][j] == HERO) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }

        return position;
    }

    //人物的移动
    public void movePlayer(Direction d) {
        int[] pos = position(map);
        int x = pos[0];
        int y = pos[1];
        switch (d) {
            case NORTH:
                if (x != 0) {
                    x--;
                    if (map[x][y] == MONSTER) {
                        HP--;
                    }
                    if (map[x][y] == GRASS || map[x][y] == FOOTPRINT || map[x][y] == END) {
                        foot.push(pos);
                        pos[0]--;
                        int[] last0 = foot.peek(2);
                        int[] last1 = foot.peek(3);
                        int[] last2 = foot.peek(4);
                        map[last2[0]][last2[1]] = GRASS;
                        map[last1[0]][last1[1]] = FOOTPRINT;
                        map[last0[0]][last0[1]] = FOOTPRINT;
                        map[x][y] = HERO;
                        numOfTrace++;
                    }
                }
                break;
            case WEST:
                if (y != 0) {
                    y--;
                    if (map[x][y] == MONSTER) {
                        HP--;
                    }
                    if (map[x][y] == GRASS || map[x][y] == FOOTPRINT || map[x][y] == END) {
                        foot.push(pos);
                        pos[1]--;
                        int[] last0 = foot.peek(2);
                        int[] last1 = foot.peek(3);
                        int[] last2 = foot.peek(4);
                        map[last2[0]][last2[1]] = GRASS;
                        map[last1[0]][last1[1]] = FOOTPRINT;
                        map[last0[0]][last0[1]] = FOOTPRINT;
                        map[x][y] = HERO;
                        numOfTrace++;
                    }
                }
                break;
            case SOUTH:
                if (x != map.length - 1) {
                    x++;
                    if (map[x][y] == MONSTER) {
                        HP--;
                    }
                    if (map[x][y] == GRASS || map[x][y] == FOOTPRINT || map[x][y] == END) {
                        foot.push(pos);
                        pos[0]++;
                        int[] last0 = foot.peek(2);
                        int[] last1 = foot.peek(3);
                        int[] last2 = foot.peek(4);
                        map[last2[0]][last2[1]] = GRASS;
                        map[last1[0]][last1[1]] = FOOTPRINT;
                        map[last0[0]][last0[1]] = FOOTPRINT;
                        map[x][y] = HERO;
                        numOfTrace++;
                    }
                }
                break;
            case EAST:
                if (y != map[0].length - 1) {
                    y++;
                    if (map[x][y] == MONSTER) {
                        HP--;
                    }
                    if (map[x][y] == GRASS || map[x][y] == FOOTPRINT || map[x][y] == END) {
                        foot.push(pos);
                        pos[1]++;
                        int[] last0 = foot.peek(2);
                        int[] last1 = foot.peek(3);
                        int[] last2 = foot.peek(4);
                        map[last2[0]][last2[1]] = GRASS;
                        map[last1[0]][last1[1]] = FOOTPRINT;
                        map[last0[0]][last0[1]] = FOOTPRINT;
                        map[x][y] = HERO;
                        numOfTrace++;
                    }
                }
                break;
            case BACK:
                if (!foot.isEmpty()) {
                    lastPosition(x, y, map);
                    numOfTrace++;
                }
                numOfTrace++;
                break;
            case KILL:
                x = pos[0];
                y = pos[1];
                MediaPlayer mediaPlayer;
                if (map[x - 1][y] == MONSTER || map[x + 1][y] == MONSTER || map[x][y - 1] == MONSTER || map[x][y + 1] == MONSTER) {
                    Monster[] monsters = Map.getMonsters();
                    for (int i = 0; i < 6; i++) {
                        Monster monster = monsters[i];
                        if (Arrays.equals(new int[]{x - 1, y}, monster.getPlace())) {
                            if (ATK >= monster.getDEF()) {
                                monster.setHP(0);
                            } else {
                                monster.setHP(monster.getHP() - 1);
                            }
                            HP--;
                            mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/files/download/sound1/201203/1150.wav"));
                            mediaPlayer.play();
                        } else if (Arrays.equals(new int[]{x + 1, y}, monster.getPlace())) {
                            if (ATK >= monster.getDEF()) {
                                monster.setHP(0);
                            } else {
                                monster.setHP(monster.getHP() - 1);
                            }
                            HP--;
                            mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/files/download/sound1/201203/1150.wav"));
                            mediaPlayer.play();
                        } else if (Arrays.equals(new int[]{x, y - 1}, monster.getPlace())) {
                            if (ATK >= monster.getDEF()) {
                                monster.setHP(0);
                            } else {
                                monster.setHP(monster.getHP() - 1);
                            }
                            HP--;
                            mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/files/download/sound1/201203/1150.wav"));
                            mediaPlayer.play();
                        } else if (Arrays.equals(new int[]{x, y + 1}, monster.getPlace())) {
                            if (ATK >= monster.getDEF()) {
                                monster.setHP(0);
                            } else {
                                monster.setHP(monster.getHP() - 1);
                            }
                            HP--;
                            mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/files/download/sound1/201203/1150.wav"));
                            mediaPlayer.play();
                        }
                        if (monster.getHP() == 0) {
                            mediaPlayer = new MediaPlayer(new Media("http://xmyd.sc.chinaz.com/Files/DownLoad/sound1/201709/9162.wav"));
                            mediaPlayer.play();
                            map[monster.getPlace()[0]][monster.getPlace()[1]] = TREASURE;
                            monster.setHP(-1);
                            numOfMonster++;
                        }
                    }
                }
                break;
            case PICK:
                int i = pos[0];
                int j = pos[1];
                if (map[i - 1][j] == TREASURE) {
                    ATK += 5;
                    DEF += 10;
                    HP += 25;
                    map[i - 1][j] = GRASS;
                    numOfTreasure++;
                    mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201711/9439.wav"));
                    mediaPlayer.play();
                } else if (map[i + 1][j] == TREASURE) {
                    ATK += 5;
                    DEF += 10;
                    HP += 25;
                    map[i + 1][j] = GRASS;
                    numOfTreasure++;
                    mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201711/9439.wav"));
                    mediaPlayer.play();
                }
                if (map[i][j - 1] == TREASURE) {
                    ATK += 5;
                    DEF += 10;
                    HP += 25;
                    map[i][j - 1] = GRASS;
                    numOfTreasure++;
                    mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201711/9439.wav"));
                    mediaPlayer.play();
                }
                if (map[i][j + 1] == TREASURE) {
                    ATK += 5;
                    DEF += 10;
                    HP += 25;
                    map[i][j + 1] = GRASS;
                    numOfTreasure++;
                    mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201711/9439.wav"));
                    mediaPlayer.play();
                }
                break;
            case QUIT:
                JOptionPane.showMessageDialog(null, "即将退出游戏...");
                System.exit(0);
                break;
            case EXIT:
                JOptionPane.showMessageDialog(null, "即将退出游戏...");
                System.exit(0);
                break;
        }
    }

    private void lastPosition(int x, int y, Icon[][] maze) {
        foot.pop();
        int[] last0 = foot.peek(1);
        int[] last1 = foot.peek(2);
        int[] last2 = foot.peek(3);
        maze[last2[0]][last2[1]] = FOOTPRINT;
        maze[last1[0]][last1[1]] = FOOTPRINT;
        maze[last0[0]][last0[1]] = HERO;
        maze[x][y] = GRASS;
    }

    public void loadGame(String fileName) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName));
            try {
                map = (Icon[][]) ois.readObject();
                int[] pos = position(map);
                pos = (int[]) ois.readObject();
                numOfTrace = (int) ois.readObject();
                numOfMonster = (int) ois.readObject();
                numOfTreasure = (int) ois.readObject();
                HP = (int) ois.readObject();
                ATK = (int) ois.readObject();
                DEF = (int) ois.readObject();
                foot = (Trace) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        try {
            checkDirectory();
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./save/" + Main.getName() + ".save"));
            oos.writeObject(map);
            oos.writeObject(position(map));
            oos.writeObject(numOfTrace);
            oos.writeObject(numOfMonster);
            oos.writeObject(numOfTreasure);
            oos.writeObject(HP);
            oos.writeObject(ATK);
            oos.writeObject(DEF);
            oos.writeObject(foot);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkDirectory() {
        File file = new File("save");
        if (!file.exists() || file.isFile())
            while (!file.mkdir()) {
                System.out.println("can not create directory: " + "save");
            }
    }

    public int getHP() {
        int[] pos = position(map);
        int x = pos[0];
        int y = pos[1];
        if (map[x - 1][y] == MONSTER || map[x][y - 1] == MONSTER || map[x + 1][y] == MONSTER || map[x][y + 1] == MONSTER)
            for (int i = 0; i < 6; i++) {
                Monster[] monsters = Map.getMonsters();
                Monster monster = monsters[i];
                if (Arrays.equals(new int[]{x, y}, monster.getPlace())) {
                    if (DEF < monster.getATK())
                        HP--;
                }
            }
        return HP;
    }

    public int getDEF() {
        return DEF;
    }

    public int getATK() {
        return ATK;
    }

    public int getNumOfMonster() {
        return numOfMonster;
    }

    public int getNumOfTrace() {
        return numOfTrace;
    }

    public int getNumOfTreasure() {
        return numOfTreasure;
    }


}

class Trace implements Serializable {
    private int[][] stack;
    private int size;
    private static final int CAPACITY = 16;

    Trace() {
        this(CAPACITY);
    }

    private Trace(int capacity) {
        stack = new int[capacity][2];
    }

    void push(int[] trace) {
        if (size >= stack.length) {
            int[][] temp = new int[stack.length * 2][2];
            for (int i = 0; i < stack.length; i++) {
                System.arraycopy(stack[i], 0, temp[i], 0, 2);
            }
            stack = temp;
        }
        stack[size++] = trace;
    }

    int[] peek(int i) {
        if (size >= i) {
            return stack[size - i];
        } else
            return new int[]{1, 1};
    }

    void pop() {
        if (size > 0) {
            size--;
        }
    }

    boolean isEmpty() {
        return size == 0;
    }
}
