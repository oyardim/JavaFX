package sample;

import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Bits {
    FileInputStream[] DiceImg = new FileInputStream[6];
    Image[] Dice = new Image[6];

    public void diceIn() throws FileNotFoundException {

        DiceImg[0] = new FileInputStream("src/dice/dice1.png");
        DiceImg[1] = new FileInputStream("src/dice/dice2.png");
        DiceImg[2] = new FileInputStream("src/dice/dice3.png");
        DiceImg[3] = new FileInputStream("src/dice/dice4.png");
        DiceImg[4] = new FileInputStream("src/dice/dice5.png");
        DiceImg[5] = new FileInputStream("src/dice/dice6.png");

        for(int i = 0; i < Dice.length; i++){
            Dice[i] = new Image(DiceImg[i]);
        }
    }

    ImageView img = new ImageView();
    ImageView img2 = new ImageView();

    public void Dice()  {
        img.setImage(null);
        img.setFitWidth(80.0);
        img.setX(80);
        img.setY(100);
        img.setPreserveRatio(true);

        img2.setImage(null);
        img2.setFitWidth(80.0);
        img2.setX(200);
        img2.setY(100);
        img2.setPreserveRatio(true);
    }

    Group root = new Group(img, img2);
    Button butRoll = new Button();
    Label winlab = new Label();
    Label computer = new Label("Computer");
    Label player = new Label("Player");


    public Bits() throws FileNotFoundException {
        Dice();
        diceIn();
        rollingButt();
        diceResult();

    }

    public void rollingButt(){
        butRoll.setText("Roll the Dice?");
        butRoll.setOnAction(actionEvent -> {
            int i = randomNumber();
            int i2 = randomNumber();
            img.setImage(Dice[i]);
            img2.setImage(Dice[i2]);
            if(i > i2){
                winlab.setText("You Win!");

            }
            else if(i == i2){
                winlab.setText("Draw!");
            }
            else{
                winlab.setText("You Lose!");


            }
        });
        butRoll.setLayoutX(100);
        butRoll.setLayoutY(200);
        butRoll.setTextFill(Color.YELLOW);
        butRoll.setStyle("-fx-background-color: #ff0000");
    }

    public void diceResult(){
        player.setLayoutX(80);
        player.setLayoutY(50);
        player.setTextFill(Color.BLACK);
        player.setFont(Font.font("Arial", 20));
        computer.setLayoutX(200);
        computer.setLayoutY(50);
        computer.setTextFill(Color.BLACK);
        computer.setFont(Font.font("Arial", 20));
        winlab.setLayoutX(100);
        winlab.setLayoutY(300);
        winlab.setTextFill(Color.RED);
        winlab.setFont(Font.font("Arial", 20));
    }

    public int randomNumber(){
        return (int) (Math.random() * 6);
    }

}
