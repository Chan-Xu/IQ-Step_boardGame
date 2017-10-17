package comp1110.ass2.gui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import javax.script.Bindings;
import java.io.File;

/**
 * A very simple viewer for piece placements in the steps game.
 *
 * NOTE: This class is separate from your main game class.  This
 * class does not play a game, it just illustrates various piece
 * placements.
 */
public class Viewer extends Application {

    /* board layout */
    private static final int SQUARE_SIZE = 60;
    private static final int PIECE_IMAGE_SIZE = (int) ((3 * SQUARE_SIZE) * 1.33);
    private static final int VIEWER_WIDTH = 750;
    private static final int VIEWER_HEIGHT = 500;

    private static final String URI_BASE = "assets/";

    private final Group root = new Group();
    private final Group controls = new Group();
    TextField textField;

    class Piece extends ImageView{
        Piece(char a1, char a2){
//            char[] string =a.toCharArray();
            if(!(a1>='A'&&a1<='H')){
                throw new IllegalArgumentException("no this picture");
            }else if (a2=='A'||a2=='E'){
                setImage(new Image((getClass().getResource("assets/"+a1+a2+".png").toString())));
            }
            else {           throw new IllegalArgumentException("no this picture");
            }  setFitHeight(PIECE_IMAGE_SIZE);
            setFitWidth(PIECE_IMAGE_SIZE);
        }

        Piece(char a1,char a2,char pos){
            this(a1,a2);

//            if(pos<'A'||pos>'Y'||pos<'a'||pos>'y'){
//                throw new IllegalArgumentException("not the right postion");
//            }
//            int x = 7;
//            int y = 7;
          setLayoutX(pos);
            setLayoutY(pos);
        }
    }
    /**
     * Draw a placement in the window, removing any previously drawn one
     *
     * @param placement A valid placement string
     */

    void makePlacement(String placement) {
////        // FIXME Task 4: implement the simple placement viewer
        // Authorship details: Task4 is written by Yiwen Peng (u6071714);

        char[] string = placement.toCharArray();
        controls.getChildren().add(new Piece(string[0], string[1], string[2]));

        controls.getChildren().removeAll();
        Button clear = new Button("clear");

    }


//
//


        /**
         * Create a basic text field for input and a refresh button.
         */

    private void makeControls() {
        Label label1 = new Label("Placement:");
        textField = new TextField();
        textField.setPrefWidth(300);
        Button button = new Button("Refresh");
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                makePlacement(textField.getText());
                textField.clear();

            }
        });

        HBox hb = new HBox();
        hb.getChildren().addAll(label1, textField, button);
        hb.setSpacing(10);
        hb.setLayoutX(130);
        hb.setLayoutY(VIEWER_HEIGHT - 50);
        controls.getChildren().add(hb);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("StepsGame Viewer");
        Scene scene = new Scene(root, VIEWER_WIDTH, VIEWER_HEIGHT);

        root.getChildren().add(controls);

        makeControls();

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}



