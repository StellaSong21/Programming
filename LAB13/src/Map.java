import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Map extends Application {
    private int[][] mapIndex = {
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 0, 1, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 1, 1, 1, 0, 1, 1, 1, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1},
    };
    private int x = 1;
    private int y = 1;
    private GridPane root = new GridPane();
    private Scene scene = new Scene(root, 1005, 530);
    private Image img = null;

    public static void main(String[] args) {
        launch();
    }

    private static int[][] move = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public void start(Stage primaryStage) {

        for (int i = 0; i < mapIndex.length; i++) {
            for (int j = 0; j < mapIndex[0].length; j++) {

                if (mapIndex[i][j] == 0) {
                    img = new Image(getClass().getResourceAsStream("space.png"));
                } else if (mapIndex[i][j] == 1) {
                    img = new Image(getClass().getResourceAsStream("wall.png"));
                } else {
                    img = new Image(getClass().getResourceAsStream("hero.png"));
                }
                ImageView imv = new ImageView();
                imv.setImage(img);
                root.add(imv, j, i);
            }
        }

        scene.setOnKeyPressed(event -> {
            move(event.getCode());
        });

        primaryStage.setScene(scene);
        root.requestFocus();
        primaryStage.show();
    }

    public void move(KeyCode event) {
        switch (event) {
            case W:
                move(0);
                break;
            case A:
                move(1);
                break;
            case S:
                move(2);
                break;
            case D:
                move(3);
                break;
            case UP:
                move(0);
                break;
            case LEFT:
                move(1);
                break;
            case DOWN:
                move(2);
                break;
            case RIGHT:
                move(3);
                break;
        }
    }

    void move(int i) {
        mapIndex[x][y] = 0;
        int x0 = x + move[i][0];
        int y0 = y + move[i][1];
        if (x0 >= 0 && y0 >= 0 && x0 <= mapIndex.length - 1 && y0 <= mapIndex[0].length - 1 && mapIndex[x0][y0] != 1 ) {
            x = x0;
            y = y0;
        }
        mapIndex[x][y] = 2;
        refresh();

    }

    void refresh() {
        root.getChildren().clear();
        for (int i = 0; i < mapIndex.length; i++) {
            for (int j = 0; j < mapIndex[0].length; j++) {
                if (mapIndex[i][j] == 0) {
                    img = new Image(getClass().getResourceAsStream("space.png"));
                } else if (mapIndex[i][j] == 1) {
                    img = new Image(getClass().getResourceAsStream("wall.png"));
                } else {
                    img = new Image(getClass().getResourceAsStream("hero.png"));
                }
                ImageView imv = new ImageView();
                imv.setImage(img);
                root.add(imv, j, i);
            }
        }

    }
}
