package client;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import core.Direction;
import core.Icon;
import server.Server;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {
    private static final double TILE_SIZE = 20;
    private static Server server;
    private ImageView[][] mapTiles;
    private GridPane map;
    private Stage stage;
    private Map<Icon, Image> imageMap = new HashMap<>();

    public static void main(String[] args) {
        server = new Server();
        server.createGame();

        launch(args);
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("maze game");
        this.stage = primaryStage;

        // prepare resources
        initGameFrame();
        initResources();
        addKeyControls();

        // show game view
        createAndShowGameView();
    }

    void createAndShowGameView() {
        Icon[][] icons = server.getGameView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        mapTiles = new ImageView[numRows][numCols];
        map.getChildren().removeAll(map.getChildren());
        for (int r = 0; r < numRows; r++) {
            // maze contents
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c] = newImageView(icons[r][c]);
                map.add(mapTiles[r][c], c + 1, r + 1);
            }
        }
        this.stage.show();
        map.requestFocus();
    }

    void updateGameView() {
        Icon[][] icons = server.getGameView();
        int numRows = icons.length;
        int numCols = icons[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c].setImage(iconToImage(icons[r][c]));
            }
        }
    }

    private ImageView newImageView(Icon icon) {
        ImageView imageView = new ImageView(iconToImage(icon));
        imageView.setFitWidth(TILE_SIZE);
        imageView.setFitHeight(TILE_SIZE);
        return imageView;
    }

    private Image iconToImage(Icon icon) {
        Image image = imageMap.get(icon);
        if (image == null)
            throw new IllegalArgumentException(icon.toString());
        return image;
    }

    private void addKeyControls() {
        map.setOnKeyPressed(e -> {
            Direction d = null;
            switch (e.getCode()) {
                case W:
                    d = Direction.NORTH;
                    break;
                case A:
                    d = Direction.WEST;
                    break;
                case S:
                    d = Direction.SOUTH;
                    break;
                case D:
                    d = Direction.EAST;
                    break;
            }
            if (d != null)
                server.movePlayer(d);
            // repaint gui
            updateGameView();
        });
    }

    private void initGameFrame() {
        VBox root = new VBox();
        MenuBar menuBar = new MyMenuBar(server, this);
        map = new GridPane();
        root.getChildren().addAll(menuBar, map);
        this.stage.setScene(new Scene(root));
    }

    private void initResources() {
        Image WALL_IMAGE = new Image("wall.png");
        Image SPACE_IMAGE = new Image("space.png");
        Image HERO_IMAGE = new Image("hero.png");
        Image END_IMAGE = new Image("end.png");
        Image FOOTPRINT_IMAGE = new Image("footprint.png");
        Image MONSTER_IMAGE = new Image("monster.png");
        Image TREASURE_IMAGE = new Image("treasure.png");

        // add to image map
        imageMap.put(Icon.GRASS, SPACE_IMAGE);
        imageMap.put(Icon.WALL, WALL_IMAGE);
        imageMap.put(Icon.HERO, HERO_IMAGE);
        imageMap.put(Icon.FOOTPRINT, FOOTPRINT_IMAGE);
        imageMap.put(Icon.MONSTER, MONSTER_IMAGE);
        imageMap.put(Icon.END, END_IMAGE);
        imageMap.put(Icon.TREASURE, TREASURE_IMAGE);
    }
}
