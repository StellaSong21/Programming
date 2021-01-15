package client;

import core.Direction;
import core.Icon;
import core.Monster;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import server.Server;

import javax.swing.*;
import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main extends Application {
    private static final double TILE_SIZE = 20;
    private static Server server;
    private ImageView[][] mapTiles;
    private BorderPane map;
    private Stage stage;
    private Map<Icon, Image> imageMap = new HashMap<>();
    private static String name;
    private Label lbTrace = new Label("Trace: ");
    private TextField tfTrace = new TextField();
    private Label lbMonster = new Label("Monsters: ");
    private TextField tfMonster = new TextField();
    private Label lbTreasure = new Label("Treasures: ");
    private TextField tfTreasure = new TextField();
    private Label lbHP = new Label("HP: ");
    private TextField tfHP = new TextField();
    private Label lbASK = new Label("ASK: ");
    private TextField tfASK = new TextField();
    private Label lbDEF = new Label("DEF: ");
    private TextField tfDEF = new TextField();
    private int level = 1;
    private Icon[][] icons;
    private int mode;
    private MediaPlayer mediaPlayer = new MediaPlayer(new Media("http://xmlt.sc.chinaz.com/Files/DownLoad/sound1/201701/8208.wav"));
    private int goal;

    public static void main(String[] args) {
        server = new Server();
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("maze game");
        this.stage = primaryStage;

        initGameFrame();
        initResources("grass");
        addKeyControls();

        this.showWelcome();
    }

    //开始界面
    private void showWelcome() {
        Stage stage = new Stage();
        Pane pane = new VBox(10);
        pane.setStyle("-fx-background-image:url(start.png); -fx-background-size: 700 400");
        pane.setPadding(new Insets(150, 300, 150, 300));
        Button btStart = new Button("Start");
        btStart.setOnAction(event -> {
            stage.close();
            this.showStart(stage);
        });
        Button btLoad = new Button("Load");
        btLoad.setOnAction(event -> showLoad(stage));
        Button btHelp = new Button("Help");
        pane.getChildren().addAll(btStart, btLoad, btHelp);
        Scene scene = new Scene(pane, 700, 400);
        btHelp.setOnAction(event -> {
            GridPane pane1 = new GridPane();
            VBox vBox1 = new VBox();
            vBox1.setAlignment(Pos.TOP_LEFT);
            vBox1.getChildren().addAll(new Label("W: "), new Label("A: "), new Label("S: "), new Label("D: "),
                    new Label("K: "), new Label("Q: "), new Label("X: "), new Label("P: "));
            VBox vBox2 = new VBox(10);
            vBox2.getChildren().addAll(new Label("UP"), new Label("LEFT"), new Label("DOWN"), new Label("RIGHT"),
                    new Label("KILL"), new Label("QUIT"), new Label("EXIT"), new Label("PICK"));
            vBox2.setAlignment(Pos.TOP_LEFT);
            pane1.add(vBox1, 1, 9);
            pane1.add(vBox2, 2, 9);
            pane1.add(new Label("游戏说明："), 1, 8);
            Button btBack = new Button("Back");
            btBack.setOnAction(event1 -> {
                stage.close();
                this.showWelcome();
            });
            pane1.add(btBack, 2, 10);
            Scene scene1 = new Scene(pane1);
            stage.setScene(scene1);
            stage.show();
        });
        stage.setScene(scene);
        stage.show();
    }

    void createAndShowGameView() {
        icons = server.getGameView(level);
        int numRows = icons.length;
        int numCols = icons[0].length;
        mapTiles = new ImageView[numRows][numCols];
        map.getChildren().removeAll(map.getChildren());
        GridPane pane = new GridPane();
        for (int r = 0; r < numRows; r++) {
            // maze contents
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c] = newImageView(icons[r][c]);
                pane.add(mapTiles[r][c], c + 1, r + 1);
            }
        }
        map.setLeft(pane);
        map.setCenter(this.information());
        this.stage.show();
        map.requestFocus();
    }

    private void updateGameView() {
        icons = server.getGameView(level);
        int numRows = icons.length;
        int numCols = icons[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c].setImage(iconToImage(icons[r][c]));
            }
        }
        tfHP.setText(server.getHP() + "");
        tfTreasure.setText(server.getNumOfTreasure() + "");
        tfTrace.setText(server.getNumOfTrace() + "");
        tfMonster.setText(server.getNumOfMonster() + "");
        tfDEF.setText(server.getDEF() + "");
        tfASK.setText(server.getATK() + "");
    }

    private void updateGameView1() {
        icons = server.getGameView(level);
        Monster[] monsters = core.Map.getMonsters();
        for (int i = 0; i < 6; i++) {
            monsters[i].rand(icons);
        }
        int numRows = icons.length;
        int numCols = icons[0].length;
        for (int r = 0; r < numRows; r++) {
            for (int c = 0; c < numCols; c++) {
                mapTiles[r][c].setImage(iconToImage(icons[r][c]));
            }
        }
        tfHP.setText(server.getHP() + "");
        tfTreasure.setText(server.getNumOfTreasure() + "");
        tfTrace.setText(server.getNumOfTrace() + "");
        tfMonster.setText(server.getNumOfMonster() + "");
        tfDEF.setText(server.getDEF() + "");
        tfASK.setText(server.getATK() + "");
    }

    //开始界面
    private void showStart(Stage stage) {
        Pane pane = new VBox(20);
        pane.setStyle("-fx-background-image:url(start.png); -fx-background-size: 700 400");
        pane.setPadding(new Insets(100, 200, 100, 200));
        VBox vBox = new VBox(10);
        TextField tfName = new TextField();
        vBox.getChildren().addAll(new Label("Please enter your name: "), tfName);
        Button btOK = new Button("OK");
        btOK.setOnAction(event -> {
            this.setName(tfName);
            stage.close();
            this.showMode(stage);
        });
        ((VBox) pane).setAlignment(Pos.TOP_RIGHT);
        pane.getChildren().addAll(vBox, btOK);
        Scene scene = new Scene(pane, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    //模式界面
    private void showMode(Stage stage) {
        Pane pane = new VBox(20);
        pane.setStyle("-fx-background-image:url(start.png); -fx-background-size: 700 400");
        pane.setPadding(new Insets(100, 200, 100, 200));
        Button btStoryMode = new Button("Story Mode");
        btStoryMode.setOnAction(event -> {
            stage.close();
            mode = 1;
            this.storyMode();
        });
        Button btSandBoxMode = new Button("SandBox Mode");
        Button btBack = new Button("Back");
        btBack.setOnAction(event -> {
            stage.close();
            this.showWelcome();
        });
        btSandBoxMode.setOnAction(event -> {
            stage.close();
            mode = 2;
            this.sandBoxMode();

        });
        pane.getChildren().addAll(new Label("Please choose game mode: "), btStoryMode, btSandBoxMode, btBack);
        ((VBox) pane).setAlignment(Pos.CENTER);
        Scene scene = new Scene(pane, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    //故事模式
    private void storyMode() {
        Stage stage = new Stage();
        StackPane pane = new StackPane();
        Label label = new Label("有一个国王的女儿要出嫁了"
                + "\n全国的勇士都来到这里" + "\n参加选亲" + "\n而你也不例外"
                + "\n我们的故事就从这里开始。。。" + "\n(点击继续)");
        pane.getChildren().add(label);
        pane.setStyle("-fx-background-color: BLACK");
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.show();
        mediaPlayer.play();
        mediaPlayer.setCycleCount(Timeline.INDEFINITE);
        label.setOnMouseClicked(event -> {
            stage.close();
            server.createGame(1);
            this.createAndShowGameView();
        });

    }

    //沙盒模式
    private void sandBoxMode() {
        Pane pane = new VBox(20);
        Stage stage = new Stage();
        pane.setStyle("-fx-background-image:url(start.png); -fx-background-size: 700 400");
        pane.setPadding(new Insets(100, 200, 100, 200));
        Button btFirst = new Button("First");
        Button btSecond = new Button("Second");
        Button btThird = new Button("Third");
        Button btFourth = new Button("Fourth");
        Button btBack = new Button("Back");
        pane.getChildren().addAll(btFirst, btSecond, btThird, btFourth, btBack);
        btBack.setOnAction(event -> {
            stage.close();
            this.showMode(stage);
        });
        btFirst.setOnAction(event -> {
            mediaPlayer.play();
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);
            stage.close();
            server.createGame(1);
            this.level = 1;
            this.createAndShowGameView();
        });
        btSecond.setOnAction(event -> {
            mediaPlayer.play();
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);
            stage.close();
            server.createGame(2);
            this.level = 2;
            this.createAndShowGameView();
        });
        btThird.setOnAction(event -> {
            mediaPlayer.play();
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);
            stage.close();
            server.createGame(3);
            this.level = 3;
            this.createAndShowGameView();
        });
        btFourth.setOnAction(event -> {
            mediaPlayer.play();
            mediaPlayer.setCycleCount(Timeline.INDEFINITE);
            stage.close();
            server.createGame(4);
            this.level = 4;
            this.createAndShowGameView();
            EventHandler<ActionEvent> eventHandler = e -> {
                updateGameView1();
            };
            Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
            timeline.setCycleCount(Animation.INDEFINITE);
            timeline.play();
        });
        Scene scene = new Scene(pane, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    private void setName(TextField tfName) {
        File dir = new File("." +
                // "/Project2_17302010079" +
                "/save");
        String[] fileNames = dir.list();
        name = tfName.getText();
        if (!tfName.getText().trim().isEmpty()) {
            assert fileNames != null;
            if (fileNames.length != 0) {
                for (String fileName : fileNames) {
                    if ((tfName.getText() + ".save").equals(fileName)) {
                        name = tfName.getText() + (int) (Math.random() * 100);
                        JOptionPane.showMessageDialog(null, "You input the " +
                                "user name already exists,\nwe offer a new name: " + name + " for you" +
                                "\nif you don't like it,please enter Back and re-enter");
                    }
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "The name your entered " +
                    "is null,please enter Back and re-enter");
            name = (int) (Math.random() * 100) + "";
        }
    }

    public static String getName() {
        return name;
    }

    private void showLoad(Stage stage) {
        Pane pane = new VBox(20);
        pane.setStyle("-fx-background-image:url(start.png); -fx-background-size: 700 400");
        pane.setPadding(new Insets(100, 250, 100, 250));
        File dir = new File("./save");
        String[] fileNames = dir.list();
        for (String fileName : fileNames) {
            Button button = new Button("./save/" + fileName);
            button.setOnAction(event -> {
                stage.close();
                server.loadGame(button.getText());
                this.createAndShowGameView();
            });
            pane.getChildren().add(button);
        }
        if (fileNames.length == 0) {
            pane.getChildren().addAll(new Label("There is no record"));
        }
        Button btBack = new Button("Back");
        pane.getChildren().add(btBack);
        btBack.setOnAction(event -> {
            stage.close();
            this.showWelcome();
        });
        Scene scene = new Scene(pane, 700, 400);
        stage.setScene(scene);
        stage.show();
    }

    private GridPane information() {
        GridPane pane1 = new GridPane();
        tfDEF.setEditable(false);
        tfASK.setEditable(false);
        tfHP.setEditable(false);
        tfMonster.setEditable(false);
        tfTrace.setEditable(false);
        tfTreasure.setEditable(false);
        tfHP.setText(server.getHP() + "");
        tfTreasure.setText(server.getNumOfTreasure() + "");
        tfTrace.setText(server.getNumOfTrace() + "");
        tfMonster.setText(server.getNumOfMonster() + "");
        tfDEF.setText(server.getDEF() + "");
        tfASK.setText(server.getATK() + "");
        pane1.add(tfTrace, 2, 1);
        pane1.add(tfTreasure, 2, 2);
        pane1.add(tfMonster, 2, 3);
        pane1.add(tfHP, 2, 4);
        pane1.add(tfASK, 2, 5);
        pane1.add(tfDEF, 2, 6);
        pane1.add(lbTrace, 1, 1);
        pane1.add(lbTreasure, 1, 2);
        pane1.add(lbMonster, 1, 3);
        pane1.add(lbHP, 1, 4);
        pane1.add(lbASK, 1, 5);
        pane1.add(lbDEF, 1, 6);
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);
        vBox1.getChildren().addAll(new Label("W: "), new Label("A: "), new Label("S: "), new Label("D: "),
                new Label("K: "), new Label("Q: "), new Label("X: "), new Label("P: "));
        VBox vBox2 = new VBox(10);
        vBox2.getChildren().addAll(new Label("UP"), new Label("LEFT"), new Label("DOWN"), new Label("RIGHT"),
                new Label("KILL"), new Label("QUIT"), new Label("EXIT"), new Label("PICK"));
        vBox2.setAlignment(Pos.TOP_LEFT);
        pane1.add(vBox1, 1, 9);
        pane1.add(vBox2, 2, 9);
        pane1.add(new Label("游戏说明："), 1, 8);
        return pane1;
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
                    server.movePlayer(d);
                    break;
                case A:
                    d = Direction.WEST;
                    server.movePlayer(d);
                    break;
                case S:
                    d = Direction.SOUTH;
                    server.movePlayer(d);
                    break;
                case D:
                    d = Direction.EAST;
                    server.movePlayer(d);
                    break;
                case K:
                    d = Direction.KILL;
                    server.movePlayer(d);
                    break;
                case P:
                    d = Direction.PICK;
                    server.movePlayer(d);
                    break;
                case Q:
                    server.movePlayer(Direction.EXIT);
                    break;
                case B:
                    server.movePlayer(Direction.BACK);
                    break;
                case H:
                    this.showHelp();
                    break;
            }
            updateGameView();

            if (server.getHP() == 0) {
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(new Media("http://jsdx.sc.chinaz.com/Files/DownLoad/sound1/201712/9498.wav"));
                mediaPlayer.play();
                mediaPlayer.setCycleCount(1);
                Stage stage = new Stage();
                Pane pane = new VBox(20);
                pane.setStyle("-fx-background-image:url(grass.png); -fx-background-size: 1500 700");
                pane.setPadding(new Insets(150, 300, 150, 300));
                Label label = new Label("You lose!");
                Button button1 = new Button("YES");
                pane.getChildren().addAll(label, button1);
                Scene scene = new Scene(pane);
                stage.setScene(scene);
                stage.show();
                button1.setOnAction(event ->
                        System.exit(0)
                );
            }

            if (icons[icons.length - 2][icons[0].length - 2] == Icon.HERO) {
                mediaPlayer.stop();
                mediaPlayer = new MediaPlayer(new Media("http://gddx.sc.chinaz.com/Files/DownLoad/sound1/201709/9208.wav"));
                mediaPlayer.play();
                mediaPlayer.setCycleCount(1);
                if (mode == 1) {
                    if (level != 4) {
                        Stage stage = new Stage();
                        Pane pane = new VBox(20);
                        pane.setStyle("-fx-background-image:url(grass.png); -fx-background-size: 1500 700");
                        pane.setPadding(new Insets(150, 300, 150, 300));
                        Label label = new Label("Congratulations,you are a step closer to the princess!" +
                                "\n是否进入下一关？");
                        Button button1 = new Button("YES");
                        Button button2 = new Button("NO");
                        pane.getChildren().addAll(label, button1, button2);
                        Scene scene = new Scene(pane);
                        stage.setScene(scene);
                        stage.show();
                        button1.setOnAction(event -> {
                                    mediaPlayer.stop();
                                    mediaPlayer = new MediaPlayer(new Media("http://xmlt.sc.chinaz.com/Files/DownLoad/sound1/201701/8208.wav"));
                                    mediaPlayer.play();
                                    mediaPlayer.setCycleCount(Timeline.INDEFINITE);
                                    stage.close();
                                    server.createGame(++level);
                                    this.createAndShowGameView();
                                    if (level == 4) {
                                        EventHandler<ActionEvent> eventHandler = event1 -> {
                                            updateGameView1();
                                        };
                                        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), eventHandler));
                                        timeline.setCycleCount(Animation.INDEFINITE);
                                        timeline.play();
                                    }
                                }
                        );
                        button2.setOnAction(event -> {
                            System.exit(0);
                        });
                    } else {
                        Pane pane = new VBox(20);
                        pane.setStyle("-fx-background-image:url(grass.png); -fx-background-size: 1500 700");
                        pane.setPadding(new Insets(150, 300, 150, 300));
                        server.saveGame();
                        goal = server.getNumOfMonster() * 100 + server.getNumOfTreasure() * 100 + 1000 - server.getNumOfTrace();
                        Label label = new Label("Congratulations! You get " + goal + "\nYou are the " + order(goal)
                                + "\n你顺利得到了公主！");
                        Button button1 = new Button("YES");
                        pane.getChildren().addAll(label, button1);
                        button1.setOnAction(event -> {
                            System.exit(0);
                        });
                        Scene scene = new Scene(pane);
                        stage.setScene(scene);
                        stage.show();
                    }
                } else {
                    Pane pane = new VBox(20);
                    pane.setStyle("-fx-background-image:url(grass.png); -fx-background-size: 1500 700");
                    pane.setPadding(new Insets(150, 300, 150, 300));
                    server.saveGame();
                    goal = server.getNumOfMonster() * 100 + server.getNumOfTreasure() * 100 + 1000 - server.getNumOfTrace();
                    Label label = new Label("Congratulations! You get " + goal + "\nYou are the " + order(goal));
                    Button button1 = new Button("YES");
                    pane.getChildren().addAll(label, button1);
                    button1.setOnAction(event -> {
                        System.exit(0);
                    });
                    Scene scene = new Scene(pane);
                    stage.setScene(scene);
                    stage.show();
                }
            }
        });
    }

    private void initGameFrame() {
        VBox root = new VBox();
        MenuBar menuBar = new MyMenuBar(server, this);
        map = new BorderPane();
        root.getChildren().addAll(menuBar, map);
        this.stage.setScene(new Scene(root));
    }

    private void initResources(String path) {

        Image WALL_IMAGE = new Image(path + "/wall.png");
        Image SPACE_IMAGE = new Image(path + "/space.png");
        Image HERO_IMAGE = new Image(path + "/hero.png");
        Image END_IMAGE = new Image(path + "/end.png");
        Image FOOTPRINT_IMAGE = new Image(path + "/footprint.png");
        Image MONSTER_IMAGE = new Image(path + "/monster.png");
        Image TREASURE_IMAGE = new Image(path + "/treasure.png");

        // add to image map
        imageMap.put(Icon.GRASS, SPACE_IMAGE);
        imageMap.put(Icon.WALL, WALL_IMAGE);
        imageMap.put(Icon.HERO, HERO_IMAGE);
        imageMap.put(Icon.FOOTPRINT, FOOTPRINT_IMAGE);
        imageMap.put(Icon.MONSTER, MONSTER_IMAGE);
        imageMap.put(Icon.END, END_IMAGE);
        imageMap.put(Icon.TREASURE, TREASURE_IMAGE);
    }

    void changeTheme(String path) {
        initResources(path);
        updateGameView();
    }

    private int order(int counts) {
        int num = 1;
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File("charts.txt"), true));
            bufferedWriter.append(counts + "\n");
            bufferedWriter.close();
            Scanner input = new Scanner(new File("charts.txt"));
            while (input.hasNext()) {
                int tmp = input.nextInt();
                if (counts < tmp) {
                    num++;
                }
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return num;
    }

    void showCharts() {
        int num = 0;
        int[] charts = new int[1];
        try {
            Scanner input = new Scanner(new File("charts.txt"));
            while (input.hasNext()) {
                if (num >= charts.length) {
                    int[] tmp = new int[charts.length + 1];
                    System.arraycopy(charts, 0, tmp, 0, charts.length);
                    charts = tmp;
                }
                charts[num++] = input.nextInt();
            }
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        for (int i = 0; i < charts.length; i++) {
            pane.add(new Text(charts[i] + "     "), 0, i);
            pane.add(new Text("     " + (i + 1)), 1, i);
        }
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Charts");
        stage.show();
    }

    void showHelp() {
        Stage stage = new Stage();
        GridPane pane = new GridPane();
        VBox vBox1 = new VBox(10);
        vBox1.setAlignment(Pos.TOP_LEFT);
        vBox1.getChildren().addAll(new Label("W: "), new Label("A: "), new Label("S: "), new Label("D: "),
                new Label("K: "), new Label("Q: "), new Label("X: "), new Label("P: "));
        VBox vBox2 = new VBox(10);
        vBox2.getChildren().addAll(new Label("UP"), new Label("LEFT"), new Label("DOWN"), new Label("RIGHT"),
                new Label("KILL"), new Label("QUIT"), new Label("EXIT"), new Label("PICK"));
        vBox2.setAlignment(Pos.TOP_LEFT);
        pane.add(vBox1, 1, 9);
        pane.add(vBox2, 2, 9);
        pane.add(new Label("游戏说明："), 1, 8);
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("Help");
        stage.show();
    }
}
