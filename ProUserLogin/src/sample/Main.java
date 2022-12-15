package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main extends Application {
    String id_Data;
    String username_Data;
    String password_Data;
    Alert alert = new Alert(Alert.AlertType.ERROR);
    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
    Stage stage = new Stage();
    public Text message = new Text("");


    public GridPane Gridpane() {
        GridPane root = new GridPane();
        root.setPrefSize(500, 400);
        root.setAlignment(Pos.CENTER);
        root.setHgap(10);
        root.setVgap(10);
        root.setPadding(new Insets(25, 25, 25, 25));
        return root;
    }
    public void Loginform(GridPane root) {

        Text scenetitle = new Text("Login Form");
        scenetitle.setFont(Font.font("Arial", 20));
        root.add(scenetitle, 0, 0, 2, 1);
        Label username = new Label("Username");
        root.add(username, 0, 1);
        TextField ustext = new TextField();
        Tooltip tt = new Tooltip("Bitte Username eingeben!!!");
        ustext.setTooltip(tt);
        root.add(ustext, 0, 2);
        Label password = new Label("Password");
        root.add(password, 0, 3);
        PasswordField psw = new PasswordField();
        Tooltip tt1 = new Tooltip("Bitte Password eingeben!!!");
        psw.setTooltip(tt1);
        root.add(psw, 0, 4);

        Button btn = new Button("LOGIN");
        btn.setStyle("-fx-background-color: #00CCFF");
        HBox hbtn = new HBox(20.0);
        hbtn.setAlignment(Pos.CENTER);
        hbtn.getChildren().add(btn);
        root.add(hbtn, 0, 5);

        Label lbl = new Label("Forgot Password?");
        lbl.setAlignment(Pos.CENTER);
        root.add(lbl, 0, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String userNameLog = ustext.getText();
                String userPassLog = psw.getText();
                try {
                    String url = "jdbc:mysql://localhost:3306/user_login";
                    String user = "root";
                    String passwort = "";
                    Connection conn = DriverManager.getConnection(url, user, passwort);
                    PreparedStatement st;
                    ResultSet rs;
                    String sql = ("SELECT * FROM info" + " WHERE username = " + "'" + userNameLog + "'");
                    st = conn.prepareStatement(sql);
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        id_Data = rs.getString(1);
                        username_Data = rs.getString(2);
                        password_Data = rs.getString(3);
                    }
                    if (userNameLog.equals(username_Data) && userPassLog.equals(password_Data)
                            && ustext.getText().isEmpty() != true && psw.getText().isEmpty() != true) {
                        LoginMenuBar();
                    } else if (!userNameLog.equals(username_Data)) {
                        alert.setHeaderText("Username ist falsch");
                        alert.showAndWait();
                    } else if (!userPassLog.equals(password_Data)) {
                        alert.setHeaderText("Password ist falsch");
                        alert.showAndWait();
                    } else {
                        alert.setHeaderText("Username und Password sind falsch");
                        alert.showAndWait();
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Exception Occur :" + ex);
                }
            }
        });
    }
    public void LoginMenuBar() {
        GridPane gridPane = Gridpane();
        MenuBar menuBar = new MenuBar();

        Menu profile = new Menu("Profile");
        Menu rechner = new Menu("Rechner");
        Menu spiele = new Menu("Spiele");
        Menu media = new Menu("Media");
        Menu hilfe = new Menu("Hilfe");

        //SubMenu Profile
        MenuItem einstellung = new MenuItem("Einstellung");
        MenuItem abmelden = new MenuItem("Abmelden");
        MenuItem beenden = new MenuItem("Beenden");

        //SubMenu Rechner
        MenuItem conversion = new MenuItem("Konversion Calc");

        //SubMenu Media
        MenuItem weather = new MenuItem("Weather App");
        MenuItem photos = new MenuItem("Photo Album");

        //SubMenu Spiele
        MenuItem sps = new MenuItem("StonePaperScissors");
        MenuItem dice = new MenuItem("Dice Roll");

        //SubMenu Hilfe
        MenuItem app = new MenuItem("über App22");

        //MenuItem Add
        profile.getItems().addAll(einstellung, abmelden, beenden);
        rechner.getItems().add(conversion);
        spiele.getItems().addAll(sps,dice);
        media.getItems().addAll(weather,photos);
        hilfe.getItems().addAll(app);

        //MenuListe an MenuBar add
        menuBar.getMenus().addAll(profile, rechner, spiele, media, hilfe);

        //Menu icon
        ImageView imgView1 = new ImageView("image/868681.png");
        imgView1.setFitWidth(20);
        imgView1.setFitHeight(20);

        ImageView imgView2 = new ImageView("image/2106415.png");
        imgView2.setFitWidth(20);
        imgView2.setFitHeight(20);

        ImageView imgView3 = new ImageView("image/images.png");
        imgView3.setFitWidth(20);
        imgView3.setFitHeight(20);

        ImageView imgView4 = new ImageView("image/download.png");
        imgView4.setFitWidth(20);
        imgView4.setFitHeight(20);

        ImageView imgView5 = new ImageView("image/mediaicon.png");
        imgView5.setFitWidth(20);
        imgView5.setFitHeight(20);

        hilfe.setGraphic(imgView1);
        rechner.setGraphic(imgView2);
        profile.setGraphic(imgView3);
        spiele.setGraphic(imgView4);
        media.setGraphic(imgView5);

        einstellung.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                TableView(gridPane);
            }
        });
        conversion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Conversion(gridPane);
            }
        });
        photos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Photo_Album(gridPane);
            }
        });
        profile.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
            }
        });
        rechner.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
            }
        });
        spiele.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
            }
        });

        media.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
            }
        });
        hilfe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                gridPane.getChildren().clear();
            }
        });

        VBox root = new VBox();
        root.getChildren().addAll(menuBar,gridPane);
        Scene scene = new Scene(root, 500.0, 400.0);
        stage.getIcons().add(new Image("image/889057.png"));
        stage.setScene(scene);
        stage.show();
    }
    public void TableView(GridPane root) {
        TableView<User> table = new TableView();
        final ObservableList<User> data = FXCollections.observableArrayList();
        table.setEditable(true);

        TableColumn id = new TableColumn("ID");
        id.setMinWidth(100);
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn userNameCol = new TableColumn("Username");
        userNameCol.setMinWidth(100);
        userNameCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        TableColumn PasswordCol = new TableColumn("Password");
        PasswordCol.setMinWidth(100);
        PasswordCol.setCellValueFactory(new PropertyValueFactory<>("password"));

        table.getColumns().addAll(id, userNameCol, PasswordCol);
        table.setTableMenuButtonVisible(true);
        BorderPane.setMargin(table,new Insets(0,10,10,0));

                try {
                    String url = "jdbc:mysql://localhost:3306/user_login";
                    String user = "root";
                    String passwort = "";
                    Connection conn = DriverManager.getConnection(url, user, passwort);
                    PreparedStatement st;
                    ResultSet rs;
                    String sql = "SELECT * FROM info";
                    st = conn.prepareStatement(sql);
                    rs = st.executeQuery(sql);
                    while (rs.next()) {
                        data.add(new User(
                                rs.getString("id"),
                                rs.getString("username"),
                                rs.getString("password")
                        ));
                        table.setItems(data);
                    }
                    st.close();
                    rs.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

        HBox hBox = new HBox(5);
        hBox.getChildren().addAll(table);

        root.getChildren().add(hBox);

    }
    public void Photo_Album(GridPane root){
        stage.setTitle("Photo Album");
        ScrollPane layout = new ScrollPane();
        TilePane tile = new TilePane();
        layout.setStyle("-fx-background-color: DAE6F3;");
        tile.setPadding(new Insets(35, 35, 35, 35));
        tile.setHgap(15);

        String path = "C:\\Users\\Yardim\\images";
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();

        for (final File file : listOfFiles) {
            ImageView imageView;
            imageView = createImageView(file);
            tile.getChildren().add(imageView);
        }
        layout.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        layout.setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
        layout.setFitToWidth(true);
        layout.setContent(tile);
        root.getChildren().addAll(layout);
    }
    private ImageView createImageView(final File imageFile)
    {
        ImageView imageView = null;
        try
        {
            final Image image = new Image(new FileInputStream(imageFile), 150, 0, true,true);
            imageView = new ImageView(image);
            imageView.setFitWidth(150);
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>()
            {
                public void handle(MouseEvent mouseEvent)
                {
                    if(mouseEvent.getButton().equals(MouseButton.PRIMARY))
                    {
                        if(mouseEvent.getClickCount() == 2)
                        {
                            try
                            {
                                BorderPane borderPane = new BorderPane();
                                ImageView imageView = new ImageView();
                                Image image = new Image(new FileInputStream(imageFile));
                                imageView.setImage(image);
                                imageView.setStyle("-fx-background-color: BLACK");
                                imageView.setFitHeight(stage.getHeight() - 20);
                                imageView.setPreserveRatio(true);
                                imageView.setSmooth(true);
                                imageView.setCache(true);
                                borderPane.setCenter(imageView);
                                Stage newStage = new Stage();
                                newStage.setWidth(stage.getWidth());
                                newStage.setHeight(stage.getHeight());
                                newStage.setTitle(imageFile.getName());
                                Scene scene = new Scene(borderPane);
                                newStage.setScene(scene);
                                newStage.show();
                            }
                            catch (FileNotFoundException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            });
        }
        catch (FileNotFoundException ex)
        {
            ex.printStackTrace();
        }
        return imageView;
    }
    public void Conversion(GridPane root){
        //Conversion Machine Action
        Text scenetitle = new Text("Metric");
        scenetitle.setFont(Font.font("Arial", 20));
        root.add(scenetitle, 0, 0, 2, 1);
        Label mLabel = new Label("Meter");
        root.add( mLabel,0,1);
        mLabel.setPadding(new Insets(0,0,0,10));
        TextField mTextField = new TextField();
        root.add( mTextField,0,2);

        Label cmLabel = new Label("Centimeter");
        root.add( cmLabel,0,3);
        cmLabel.setPadding(new Insets(0,0,0,10));
        TextField cmTextField = new TextField();
        root.add(cmTextField,0,4);

        Label dmLabel = new Label("Decimeter");
        root.add( dmLabel,0,5);
        dmLabel.setPadding(new Insets(0,0,0,10));
        TextField dmTextField = new TextField();
        root.add(dmTextField,0,6);

        Button clear = new Button("Clear");
        clear.setStyle("-fx-background-color: #00CCFF");
        HBox hbtn1 = new HBox(20.0);
        hbtn1.setAlignment(Pos.CENTER_RIGHT);
        hbtn1.getChildren().add(clear);
        root.add(hbtn1, 0, 7);

        mTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Double cm = Double.parseDouble(mTextField.getText());
                    cmTextField.setText(Double.toString(cm * 100));

                    Double dm = Double.parseDouble(mTextField.getText());
                    dmTextField.setText(Double.toString(dm * 10));
                }catch (Exception e) {
                }
            }
        });
        cmTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Double m = Double.parseDouble(cmTextField.getText());
                    mTextField.setText(Double.toString(m / 100));

                    Double dm = Double.parseDouble(cmTextField.getText());
                    dmTextField.setText(Double.toString(dm / 10));
                }catch (Exception e) {
                }
            }
        });
        dmTextField.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    Double m = Double.parseDouble(dmTextField.getText());
                    mTextField.setText(Double.toString(m / 10));

                    Double cm = Double.parseDouble(dmTextField.getText());
                    cmTextField.setText(Double.toString(cm * 10));
                }catch (Exception e) {
                }
            }
        });

        clear.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mTextField.clear();
                cmTextField.clear();
                dmTextField.clear();
            }
        });
    }
    public void Register(GridPane root) {
        Text scenetitle = new Text("Registration Form");
        scenetitle.setFont(Font.font("Arial", 20));
        root.add(scenetitle, 0, 0, 2, 1);

        Label username = new Label("Username");
        root.add(username, 0, 1);
        TextField ustext = new TextField();
        root.add(ustext, 0, 2);
        Label email = new Label("EMail");
        root.add(email, 0, 3);
        TextField etext = new TextField();
        root.add(etext, 0, 4);
        Label password = new Label("Password");
        root.add(password, 0, 5);
        PasswordField psw = new PasswordField();
        root.add(psw, 0, 6);
        Label password1 = new Label("Password wiederholen");
        root.add(password1, 0, 7);
        PasswordField psw1 = new PasswordField();
        root.add(psw1, 0, 8);

        Button btn = new Button("SUBMIT");
        btn.setStyle("-fx-background-color: #00CCFF");
        HBox hbtn = new HBox(20.0);
        hbtn.setAlignment(Pos.CENTER);
        hbtn.getChildren().add(btn);
        root.add(hbtn, 0, 9);

        btn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try{
                    if (ustext.getText().isEmpty()) {
                        alert.setHeaderText("Username eingeben");
                        alert.showAndWait();
                        return;
                    }
                    else if (checkUsername(ustext.getText())==true) {
                        alert.setHeaderText("Biite geben Sie einen anderen username ein");
                        alert.showAndWait();
                        return;
                    }
                    else if (emailCheck(etext.getText())) {
                        alert.setHeaderText("ist keine gültige EMail Adresse");
                        alert.showAndWait();
                        return;
                    }
                    else if (passwordCheck(psw.getText())) {
                        alert.setHeaderText("Minimum 8 Zeichen, min. 1 Großbuchstaben,\n" +
                                "    min. 1 Kleinbuchstaben, min. 1 Zahl,\n" +
                                "    min. 1 Sonderzeichen");
                        alert.showAndWait();
                        return;
                    }
                    else if (!psw.getText().equals(psw1.getText())) {
                        alert.setHeaderText("Password ist falsch");
                        alert.showAndWait();
                        return;
                    }
                }catch (Exception e){

                }
                String uss = ustext.getText();
                String pss = psw.getText();

                try {
                    createInfo(uss, pss);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                alert1.setHeaderText("Successful");
                alert1.showAndWait();
                ustext.clear();
                etext.clear();
                psw.clear();
                psw1.clear();
            }
        });
    }
    public boolean checkUsername(String usname) throws SQLException {
        try {
            String url = "jdbc:mysql://localhost:3306/user_login";
            String user = "root";
            String passwort = "";
            Connection conn = DriverManager.getConnection(url, user, passwort);
            String sql = "SELECT * FROM info WHERE username = '" + usname + "'";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (SQLException throwables) {
            return false;
        }

    }
    public boolean emailCheck(String email) {
        Pattern pu = Pattern.compile(("(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"));
        Matcher mu = pu.matcher(email);
        if (mu.matches()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean passwordCheck(String password){
        Pattern pu = Pattern.compile("(^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$)");
        Matcher mu = pu.matcher(password);
        if (mu.matches()) {
            return false;
        } else {
            return true;
        }
    }
    public boolean createInfo(String uss, String pss) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/user_login";
        String user = "root";
        String password = "";
        try {
            Connection conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO info(username,password)" + " VALUES " + "('" + uss + "', '" + pss + "')";
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate(sql);

        } catch (SQLException e) {
        }
        return false;
    }
    public boolean DBConnect(){
        String url = "jdbc:mysql://localhost:3306/user_login";
        String user = "root";
        String password = "";
        try{
            Connection conn = DriverManager.getConnection(url, user, password);
            return true;

        } catch (SQLException exception) {
            return false;
        }
    }
    @Override
    public void start(Stage stage) throws Exception{
        FlowPane flowPane = new FlowPane();
        flowPane.setHgap(5);
        flowPane.setVgap(5);
        Scene scene = new Scene(flowPane,400.0,400.0, Color.LIGHTBLUE);

        BorderPane meinPane = new BorderPane();
        TabPane tabPane = new TabPane();
        Tab tabA = new Tab();
        tabA.setText("Login");
        GridPane root = Gridpane();
        Loginform(root);
        tabA.setContent(root);
        tabA.setClosable(false);

        Tab tabB = new Tab();
        tabB.setText("Registration");
        GridPane root1 = Gridpane();
        Register(root1);
        tabB.setContent(root1);
        tabB.setClosable(false);

        //DB Action
        Circle circle = new Circle(40,40,10);
        circle.setFill(Color.RED);

        if (DBConnect()){
            tabPane.setDisable(false);
            circle.setFill(Color.GREEN);
            message.setText("Database Connected");
            message.setFill(Color.GREEN);
        }
        else{
            tabPane.setDisable(true);
            circle.setFill(Color.RED);
            message.setText("Database Disconnected");
            message.setFill(Color.RED);
        }

        tabPane.getTabs().addAll(tabA,tabB);

        meinPane.setCenter(tabPane);
        meinPane.prefHeightProperty().bind(scene.heightProperty());
        meinPane.prefWidthProperty().bind(scene.widthProperty());
        flowPane.getChildren().addAll(circle,message,meinPane);


        stage.getIcons().add(new Image("image/889057.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
