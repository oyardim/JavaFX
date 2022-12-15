package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GridPane root = new GridPane();
        root.setPrefSize(500, 400);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setStyle("-fx-background-image: url(image/weather.jpg); -fx-background-repeat: no-repeat; -fx-background-position: center center; -fx-background-size: 400 400;");
        Label city = new Label("City: ");
        root.add(city, 0, 1);
        Label lbl = new Label();
        root.add(lbl, 1, 1);
        Label time = new Label("Time: ");
        root.add(time, 0, 2);
        Label lbl1 = new Label();
        root.add(lbl1, 1, 2);
        Label temp = new Label("Temperatur: ");
        root.add(temp, 0, 3);
        Label lbl2 = new Label();
        root.add(lbl2, 1, 3);

        TextField cityText = new TextField();
        root.add(cityText, 0, 4);

        Button btn = new Button("Login");
        root.add(btn, 0, 5);

        btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try{
                    WetterLoad wetterLoad = WetterLoad.getInstance();
                    WetterInfo[] wetterInfos = wetterLoad.loads(cityText.getText());
                    for (int x = 0; x < wetterInfos.length; x++) {
                        WetterInfo wetterInfo = wetterInfos[x];
                        lbl.setText(cityText.getText());
                        lbl1.setText(wetterInfos[0].getTimestamp());
                        long temp1 = Math.round(Double.parseDouble(wetterInfo.getTemperature()) - 273.15);
                        lbl2.setText(Double.toString(temp1) + " °C");

                    }
                }catch(Exception e){
                }
            }
        });
        Scene scene = new Scene(root, 400,400);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
