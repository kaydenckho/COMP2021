import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JungleGameGUI extends Application {

    private int row_num = 7, col_num = 9;
    private Image[] images = new Image[11];
    // 2D array of Buttons with value of 9,7
    private Button[][] btn = new Button[row_num][col_num];

    private void CreateImages(){
        images[0]= new Image(getClass().getResourceAsStream("/animals/cat.jpg"));
        images[1]= new Image(getClass().getResourceAsStream("/animals/dog.jpg"));
        images[2]= new Image(getClass().getResourceAsStream("/animals/wolf.jpg"));
        images[3]= new Image(getClass().getResourceAsStream("/animals/lion.jpg"));
        images[4]= new Image(getClass().getResourceAsStream("/animals/tiger.jpg"));
        images[5]= new Image(getClass().getResourceAsStream("/animals/rat.jpg"));
        images[6]= new Image(getClass().getResourceAsStream("/animals/leopard.jpg"));
        images[7] = new Image(getClass().getResourceAsStream("/animals/elephant.jpg"));
        images[8]  = new Image(getClass().getResourceAsStream("/animals/river.jpg"));
        images[9] = new Image(getClass().getResourceAsStream("/animals/trap.jpg"));
        images[10] = new Image(getClass().getResourceAsStream("/animals/den.jpg"));

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
        New.setOnAction((ActionEvent event) -> CreateImages());

        menu.getItems().addAll(New,Open,Save,Surrender);
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
