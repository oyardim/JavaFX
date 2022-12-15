package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileNotFoundException;

public class Main extends Application {
    public void start() throws FileNotFoundException{
        Bits b = new Bits();
        Stage stage = new Stage();
        Scene scene = new Scene(b.root,400.0,400.0);
        scene.setFill(Color.GREEN);
        new Bits();
        b.root.getChildren().addAll(b.butRoll,b.winlab, b.player,b.computer);
        stage.setScene(scene);
        stage.setTitle("Dice Roll");
        stage.show();
        stage.setResizable(false);
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
           start();
    }
}
