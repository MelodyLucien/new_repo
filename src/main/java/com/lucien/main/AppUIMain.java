package com.lucien.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

// the ui framework document
// https://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm#JFXSG107
public class AppUIMain extends Application {

    private static final String TITLE = "appTitle";
    private static final String FXML = "main.fxml";
    private static Stage mainStage;

    @Override
    public void start(Stage primaryStage) {
        mainStage = primaryStage;
        mainStage.setTitle(TITLE);
        mainStage.setScene(getScene(FXML));
        mainStage.show();
        // use this method if load data in controller
        // startWithData(primaryStage);
    }

    private Scene getScene(String fxml){
        Scene res = null;
        try {
            res = new Scene(FXMLLoader.load(AppUIMain.class.getResource(fxml)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    public static void main(String[] args) {
        launch(args);
    }

    //----------------------------------------------------------------------------------------------start with data
    //region start with init data,maybe from database
    private void startWithData(Stage primaryStage){
        FXMLLoader fxmlLoader = getFxmlLoader(FXML);
        // AppController mainController = fxmlLoader.getController();
        // configController.initHostInfo();
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(fxmlLoader.getRoot(), 300, 300));
        primaryStage.show();
    }

    private FXMLLoader getFxmlLoader(String fxml){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return loader;
    }
    //endregion


    public static Stage getMainStage() {
        return mainStage;
    }
}
