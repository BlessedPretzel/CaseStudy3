package se233.chapter3;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application{
    public static Stage stage;
    public static HostServices hostServices;
    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        hostServices = getHostServices();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        this.stage.setTitle("Indexer");
        this.stage.setScene(scene);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}