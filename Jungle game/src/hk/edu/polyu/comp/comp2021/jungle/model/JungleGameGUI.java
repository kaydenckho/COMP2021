package hk.edu.polyu.comp.comp2021.jungle.model;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;


import java.util.Optional;

public class JungleGameGUI extends Application {

    private JungleGame game = new JungleGame();
    private int row_num = 7, col_num = 9;
    private Image[] images = new Image[12];
    private static int clicked;
    Position[] temp = new Position[2];

    Alert alertX = new Alert(Alert.AlertType.INFORMATION);
    Alert alertY = new Alert(Alert.AlertType.INFORMATION);
    Alert invalid = new Alert(Alert.AlertType.INFORMATION);

    // 2D array of Buttons with value of 9,7
    private final Button[][] btn = new Button[row_num][col_num];
    private final Position[][] positions = {
            {Position.A9,Position.A8,Position.A7,Position.A6,Position.A5,Position.A4,Position.A3,Position.A2,Position.A1},
            {Position.B9,Position.B8,Position.B7,Position.B6,Position.B5,Position.B4,Position.B3,Position.B2,Position.B1},
            {Position.C9,Position.C8,Position.C7,Position.C6,Position.C5,Position.C4,Position.C3,Position.C2,Position.C1},
            {Position.D9,Position.D8,Position.D7,Position.D6,Position.D5,Position.D4,Position.D3,Position.D2,Position.D1},
            {Position.E9,Position.E8,Position.E7,Position.E6,Position.E5,Position.E4,Position.E3,Position.E2,Position.E1},
            {Position.F9,Position.F8,Position.F7,Position.F6,Position.F5,Position.F4,Position.F3,Position.F2,Position.F1},
            {Position.G9,Position.G8,Position.G7,Position.G6,Position.G5,Position.G4,Position.G3,Position.G2,Position.G1},
    };
    private final GridPane gridPane = new GridPane();

    private String[] GetName(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Welcome To Jungle Game!!\nPlease Enter your Names.");
        dialog.setGraphic(new ImageView(this.getClass().getResource("/hk/edu/polyu/comp/comp2021/jungle/img/JungleGame.gif").toString()));

        ButtonType OKButtonType = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(OKButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField PlayerX = new TextField();
        PlayerX.setPromptText("Player X");
        TextField PlayerY = new TextField();
        PlayerY.setPromptText("Player Y");

        grid.add(new Label("Player X:"), 0, 0);
        grid.add(PlayerX, 1, 0);
        grid.add(new Label("Player Y"), 0, 1);
        grid.add(PlayerY, 1, 1);

        Node OKButton = dialog.getDialogPane().lookupButton(OKButtonType);
        OKButton.setDisable(true);

        PlayerX.textProperty().addListener((observable1, oldValue1, newValue1) -> {
            PlayerY.textProperty().addListener((observable2, oldValue2, newValue2) -> {
                OKButton.setDisable(newValue1.trim().isEmpty() && newValue2.trim().isEmpty());
            });
        });

        dialog.getDialogPane().setContent(grid);

        // Return pair of names when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == OKButtonType) {
                return new Pair<>(PlayerX.getText(), PlayerY.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(names -> { names.getKey();names.getValue(); });
        String[] names = new String[2];
        names[0] = result.get().getKey();
        names[1] = result.get().getValue();
        return names;
    }

    private void CreateImages(){
        images[0]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/cat.gif"));
        images[1]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/dog.gif"));
        images[2]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/wolf.gif"));
        images[3]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/lion.gif"));
        images[4]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/tiger.gif"));
        images[5]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/rat.gif"));
        images[6]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/leopard.gif"));
        images[7] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/elephant.gif"));
        images[8]  = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/river.gif"));
        images[9] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/trap.gif"));
        images[10] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/den.gif"));
        images[11] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/grass.gif"));

        //Set all grass map
        for(int i=0; i<row_num; i++) {
            for (int j = 0; j < col_num; j++) {
                btn[i][j].setGraphic(new ImageView(images[11]));
            }
        }

        // Set X's animals
        btn[0][0].setGraphic(new ImageView(images[3]));
        btn[6][0].setGraphic(new ImageView(images[4]));
        btn[5][1].setGraphic(new ImageView(images[0]));
        btn[1][1].setGraphic(new ImageView(images[1]));
        btn[4][2].setGraphic(new ImageView(images[2]));
        btn[0][2].setGraphic(new ImageView(images[5]));
        btn[2][2].setGraphic(new ImageView(images[6]));
        btn[6][2].setGraphic(new ImageView(images[7]));
        // Set X's trap
        btn[2][0].setGraphic(new ImageView(images[9]));
        btn[4][0].setGraphic(new ImageView(images[9]));
        btn[3][1].setGraphic(new ImageView(images[9]));
        //Set X's den
        btn[3][0].setGraphic(new ImageView(images[10]));

        // Set Y's animals
        btn[6][8].setGraphic(new ImageView(images[3]));
        btn[0][8].setGraphic(new ImageView(images[4]));
        btn[1][7].setGraphic(new ImageView(images[0]));
        btn[5][7].setGraphic(new ImageView(images[1]));
        btn[2][6].setGraphic(new ImageView(images[2]));
        btn[6][6].setGraphic(new ImageView(images[5]));
        btn[4][6].setGraphic(new ImageView(images[6]));
        btn[0][6].setGraphic(new ImageView(images[7]));
        // Set Y's trap
        btn[3][7].setGraphic(new ImageView(images[9]));
        btn[4][8].setGraphic(new ImageView(images[9]));
        btn[2][8].setGraphic(new ImageView(images[9]));
        //Set Y's den
        btn[3][8].setGraphic(new ImageView(images[10]));

        //Set rivers
        for(int i=1; i<=5; i++) {
            for (int j=3;j <= 5; j++) {
                if (i!=3){
                    btn[i][j].setGraphic(new ImageView(images[8]));
                }
            }
        }
    }

    private void SetupButtons(){
        for(int i=0; i<row_num; i++) {
            for (int j = 0; j <col_num; j++) {
                btn[i][j].setUserData(positions[i][j]);
                btn[i][j].setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event1) {
                        Button n = (Button) event1.getSource();
                        temp[clicked] = (Position) n.getUserData();
                        System.out.println(temp[clicked]);
                        clicked++;
                        if (clicked >= 2){
                            clicked = 0;
                            if (game.turn) {
                                boolean valid = game.board.step(game.playerX, temp[0], temp[1]);
                                if (valid) {
                                    // move the icon of the button
                                    Node img = btn[temp[0].x][8 - temp[0].y].getGraphic();
                                    btn[temp[1].x][8 - temp[1].y].setGraphic(img);
                                    btn[temp[0].x][8 - temp[0].y].setGraphic(null);
                                    // fill rivers
                                    for(int i=1; i<=5; i++) {
                                        for (int j = 3; j <= 5; j++) {
                                            if (i != 3 && btn[i][j].getGraphic()==null) {
                                                btn[i][j].setGraphic(new ImageView(images[8]));
                                            }
                                        }
                                    }
                                    // fill traps
                                    if (btn[2][0].getGraphic()==null)btn[2][0].setGraphic(new ImageView(images[9]));
                                    if (btn[4][0].getGraphic()==null)btn[4][0].setGraphic(new ImageView(images[9]));
                                    if (btn[3][1].getGraphic()==null)btn[3][1].setGraphic(new ImageView(images[9]));
                                    if (btn[3][7].getGraphic()==null)btn[3][7].setGraphic(new ImageView(images[9]));
                                    if (btn[4][8].getGraphic()==null)btn[4][8].setGraphic(new ImageView(images[9]));
                                    if (btn[2][8].getGraphic()==null)btn[2][8].setGraphic(new ImageView(images[9]));

                                    // fill grass
                                    for(int i=0; i<row_num; i++) {
                                        for (int j = 0; j < col_num; j++) {
                                            if (btn[i][j].getGraphic()==null)
                                                btn[i][j].setGraphic(new ImageView(images[11]));
                                        }
                                    }
                                    game.turn = false;
                                    alertY.showAndWait();
                                }
                                else {
                                    invalid.setHeaderText("Your Move Is Not Possible, " + game.playerX.name + " ~");
                                    invalid.showAndWait();
                                }
                            }
                            else{
                                boolean valid = game.board.step(game.playerY, temp[0], temp[1]);
                                if (valid) {
                                    // move the icon of the button
                                    Node img = btn[temp[0].x][8 - temp[0].y].getGraphic();
                                    btn[temp[1].x][8 - temp[1].y].setGraphic(img);
                                    btn[temp[0].x][8 - temp[0].y].setGraphic(null);
                                    // fill rivers
                                    for(int i=1; i<=5; i++) {
                                        for (int j = 3; j <= 5; j++) {
                                            if (i != 3 && btn[i][j].getGraphic()==null) {
                                                btn[i][j].setGraphic(new ImageView(images[8]));
                                            }
                                        }
                                    }
                                    // fill traps
                                    if (btn[2][0].getGraphic()==null)btn[2][0].setGraphic(new ImageView(images[9]));
                                    if (btn[4][0].getGraphic()==null)btn[4][0].setGraphic(new ImageView(images[9]));
                                    if (btn[3][1].getGraphic()==null)btn[3][1].setGraphic(new ImageView(images[9]));
                                    if (btn[3][7].getGraphic()==null)btn[3][7].setGraphic(new ImageView(images[9]));
                                    if (btn[4][8].getGraphic()==null)btn[4][8].setGraphic(new ImageView(images[9]));
                                    if (btn[2][8].getGraphic()==null)btn[2][8].setGraphic(new ImageView(images[9]));

                                    // fill grass
                                    for(int i=0; i<row_num; i++) {
                                        for (int j = 0; j < col_num; j++) {
                                            if (btn[i][j].getGraphic()==null)
                                                btn[i][j].setGraphic(new ImageView(images[11]));
                                        }
                                    }
                                    game.turn = true;
                                    alertX.showAndWait();
                                }
                                else{
                                    invalid.setHeaderText("Your Move Is Not Possible, " + game.playerY.name + " ~");
                                    invalid.showAndWait();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Jungle Game");

        BorderPane root = new BorderPane();

        MenuBar menubar = new MenuBar();
        Menu menu = new Menu("Menu");
        MenuItem New = new MenuItem("New");
        MenuItem Open = new MenuItem("Open");
        MenuItem Save = new MenuItem("Save");
        MenuItem Surrender = new MenuItem("Surrender");
        // Do action for "New" here
        New.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try {
                    String[] names = GetName();   // names[0]: player X's name ; names[1]: player Y's name;
                    if (names[0] != null && names[1] != null) {     // If dialog window closed(pressed cancel or Alt+F4)
                        CreateImages();
                        game.StartNewGame(names[0],names[1]);
                        SetupButtons();
                        alertX.setTitle("Notice");
                        alertX.setHeaderText("It's Your Turn - " + names[0] + " ~");
                        alertX.setContentText("GOOD LUCK!! :D");
                        alertX.setGraphic(new ImageView(this.getClass().getResource("/hk/edu/polyu/comp/comp2021/jungle/img/alert.gif").toString()));
                        alertX.showAndWait();

                        alertY.setTitle("Notice");
                        alertY.setHeaderText("It's Your Turn - " + names[1] + " ~");
                        alertY.setContentText("GOOD LUCK!! :D");
                        alertY.setGraphic(new ImageView(this.getClass().getResource("/hk/edu/polyu/comp/comp2021/jungle/img/alert.gif").toString()));

                        invalid.setTitle("Warning");
                        invalid.setContentText("Please Try Again!! :D");
                        invalid.setGraphic(new ImageView(this.getClass().getResource("/hk/edu/polyu/comp/comp2021/jungle/img/invalid.gif").toString()));
                    }
                }
                catch (Exception e){
                    //e.printStackTrace();
                }
            }
        });
        // Do action for "Open" here
        Open.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
                try{
                    if(game.OpenSavedGame()){
                        String[] names = {game.playerX.name, game.playerY.name};
                        //initailize images according to board
                        SetupButtons();
                    }
                }
                catch(Exception e){
                    //e.printStackTrace();
                }
            }
        });
        // Do action for "Save" here
        Save.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

            }
        });
        // Do action for "Surrender" here
        Surrender.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

            }
        });
        menu.getItems().addAll(New, Open, Save, Surrender);
        menubar.getMenus().add(menu);
        root.setTop(menubar);

        root.setCenter(gridPane);

        for(int i=0; i<row_num; i++){
            for(int j=0; j<col_num;j++){

                btn[i][j] = new Button();
                btn[i][j].setMinSize(64, 64);
                gridPane.add(btn[i][j], i,j);
            }
        }

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
