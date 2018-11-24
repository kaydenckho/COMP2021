package hk.edu.polyu.comp.comp2021.jungle;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.util.Optional;

public class JungleGameGUI extends Application {

    private int row_num = 7, col_num = 9;
    private Image[] images = new Image[11];
    // 2D array of Buttons with value of 9,7
    private Button[][] btn = new Button[row_num][col_num];

    private String[] GetName(){
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("New Game");
        dialog.setHeaderText("Welcome To Jungle Game!!\nPlease Enter your Names.");
        dialog.setGraphic(new ImageView(this.getClass().getResource("/hk/edu/polyu/comp/comp2021/jungle/img/JungleGameIcon.png").toString()));

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
        result.ifPresent(names -> { System.out.println("Player X is " + names.getKey() + ", Player X is " + names.getValue()); });
        String[] names = new String[2];
        names[0] = result.get().getKey();
        names[1] = result.get().getValue();
        return names;
    }

    private void CreateImages(){
        images[0]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/cat.jpg"));
        images[1]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/dog.jpg"));
        images[2]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/wolf.jpg"));
        images[3]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/lion.jpg"));
        images[4]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/tiger.jpg"));
        images[5]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/rat.jpg"));
        images[6]= new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/leopard.jpg"));
        images[7] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/elephant.jpg"));
        images[8]  = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/river.jpg"));
        images[9] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/trap.jpg"));
        images[10] = new Image(getClass().getResourceAsStream("/hk/edu/polyu/comp/comp2021/jungle/img/den.jpg"));

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
                    }
                }
                catch (Exception e){}
            }
        });
        // Do action for "Open" here
        Open.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {

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

        GridPane gridPane = new GridPane();
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
