package client;

import javafx.scene.control.*;
import server.Server;

import java.io.File;

class MyMenuBar extends MenuBar {
    MyMenuBar(Server server, Main app) {
        super();
        Menu menuFile = new Menu("File");
        Menu loadItem = new Menu("Load");
        File dir = new File("." +
                // "/Project2_17302010079" +
                "/save");
        String[] fileNames = dir.list();
        assert fileNames != null;
        for (String fileName : fileNames) {
            MenuItem menuItem = new MenuItem("./save/" + fileName);
            loadItem.getItems().add(menuItem);
            menuItem.setOnAction(event -> {
                server.loadGame(menuItem.getText());
                app.createAndShowGameView();
            });
        }

        MenuItem saveItem = new MenuItem("Save");
        saveItem.setOnAction(event -> {
            server.saveGame();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("Saved.");
            alert.showAndWait();
        });

        MenuItem chartsItem = new MenuItem("View Charts");
        chartsItem.setOnAction(event ->
                app.showCharts()
        );

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(event -> System.exit(0));

        menuFile.getItems().addAll(loadItem, saveItem, chartsItem, exit);

        // add settings menu
        Menu menuSetting = new Menu("Settings");
        Menu themeMenu = new Menu("Theme");
        ToggleGroup themeGroup = new ToggleGroup();
        RadioMenuItem snowTheme = new RadioMenuItem("snow");
        snowTheme.setToggleGroup(themeGroup);
        snowTheme.setOnAction(event -> {
            app.changeTheme("snow");
        });
        RadioMenuItem grassTheme = new RadioMenuItem("grass");
        grassTheme.setToggleGroup(themeGroup);
        grassTheme.setOnAction(event -> {
            app.changeTheme("grass");
        });
        themeMenu.getItems().addAll(snowTheme, grassTheme);
        menuSetting.getItems().add(themeMenu);
        // add help menu
        Menu menuHelp = new Menu("Help");
        MenuItem howToPlay = new MenuItem("How to Play");
        howToPlay.setOnAction(event ->
                app.showHelp()
        );
        menuHelp.getItems().addAll(howToPlay);
        this.getMenus().addAll(menuFile, menuSetting, menuHelp);

    }
}
