package server;

import core.Direction;
import core.Icon;
import core.Map;

import java.io.*;
import java.util.Scanner;

import static core.Icon.*;

public class Server {

    private Icon[][] map;
    private int[] pos = {1, 1}; // y,x

    public void createGame() {
        map = new Map().getMap();
    }

    public Icon[][] getGameView() {
        Icon[][] tmp = new Icon[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, tmp[i], 0, map[i].length);
        }
        tmp[pos[0]][pos[1]] = Icon.HERO;
        return tmp;
    }

    public boolean movePlayer(Direction d) {
        switch (d) {
            case NORTH:
                if (map[pos[0] - 1][pos[1]] == GRASS)
                    pos[0]--;
                break;
            case SOUTH:
                if (map[pos[0] + 1][pos[1]] == GRASS)
                    pos[0]++;
                break;
            case EAST:
                if (map[pos[0]][pos[1] + 1] == GRASS)
                    pos[1]++;
                break;
            case WEST:
                if (map[pos[0]][pos[1] - 1] == GRASS)
                    pos[1]--;
                break;
        }
        return true;
    }

    public void saveGame() {
        try {
            checkDirectory("save");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("./save/1.save"));
            oos.writeObject(map);
            oos.writeObject(pos);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkDirectory(String name) {
        File file = new File(name);
        if (!file.exists() || file.isFile())
            while (!file.mkdir()) {
                System.out.println("can not create directory: " + name);
            }
    }

    public void loadGame() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream("./save/1.save"));
            try {
                map = (Icon[][]) ois.readObject();
                pos = (int[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadExternalMap(File file) {
        try {
            Scanner in = new Scanner(file);
            int height = in.nextInt();
            int width = in.nextInt();
            Icon[][] map = new Icon[height][width];
            int[] pos = new int[]{1, 1};
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int num = in.nextInt();
                    switch (num) {
                        case 1:
                            map[i][j] = WALL;
                            break;
                        case 0:
                            map[i][j] = GRASS;
                            break;
                        case 2:
                            map[i][j] = TREASURE;
                            break;
                        case 3:
                            map[i][j] = MONSTER;
                            break;
                        case 4:
                            map[i][j] = END;
                            break;
                        case 5:
                            map[i][j] = GRASS;
                            pos = new int[]{i, j};
                            break;
                    }
                }
            }
            this.map = map;
            this.pos = pos;
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

//    public static void main(String[] args) throws IOException {
//        // output map
//        BufferedWriter bw = new BufferedWriter(new FileWriter(new File("./map/4.map")));
//        Icon[][] map = Map.map4;
//        bw.write(map.length + " " + map[0].length + "\n");
//        for (int i = 0; i < map.length; i++) {
//            for (int j = 0; j < map[i].length; j++) {
//                int num = 0;
//                switch (map[i][j]) {
//                    case WALL:
//                        num = 1;
//                        break;
//                    case EMPTY:
//                        num = 0;
//                        break;
//                    case TREASURE:
//                        num = 2;
//                        break;
//                    case MONSTER:
//                        num = 3;
//                        break;
//                    case END:
//                        num = 4;
//                        break;
//                    case HERO:
//                        num = 5;
//                        break;
//                }
//                bw.write(num + " ");
//            }
//            bw.write("\n");
//        }
//        bw.close();
//    }
