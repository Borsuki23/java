import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ClickCounter extends Application {
    private int count = 0;

    @Override
    public void start(Stage primaryStage) {
        Text counterText = new Text("Натиснуто: 0");
        Button button = new Button("Натисни мене!");

        button.setOnAction(e -> {
            count++;
            counterText.setText("Натиснуто: " + count);
        });

        VBox layout = new VBox(10, counterText, button);
        layout.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(layout, 300, 200);
        primaryStage.setTitle("Лічильник");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
