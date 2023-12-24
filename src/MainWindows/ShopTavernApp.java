package MainWindows;

import javafx.application.Application;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.geometry.Pos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.Stop;
import javafx.scene.text.FontWeight;

public class ShopTavernApp extends Application {


    public Scene mainScene;
    private Stage primaryStage;


    public static void main(String[] args) {
        launch(args);
    }
    public interface Command {
        void execute();
    }

    @Override
    public void start(Stage primaryStage) {
        ShopTavernApp.Command openBuyWindow = new OpenBuyWindowCommand(primaryStage);
        ShopTavernApp.Command openSellWindow = new OpenSellWindowCommand(primaryStage);
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Tavern");


        Button buyButton = new Button("Buy");
        Button sellButton = new Button("Sell");
        Button exitButton = new Button("Close");


        buyButton.setTextFill(Color.PURPLE);
        sellButton.setTextFill(Color.PURPLE);
        exitButton.setTextFill(Color.PURPLE);
        Font buttonFont = Font.font("Arial", FontWeight.BOLD, 24);
        buyButton.setFont(buttonFont);
        sellButton.setFont(buttonFont);
        exitButton.setFont(buttonFont);


        buyButton.setMinSize(100, 40);
        sellButton.setMinSize(100, 40);
        exitButton.setMinSize(100, 40);


        buyButton.setStyle("-fx-background-color: #000000;");
        sellButton.setStyle("-fx-background-color: #000000;");
        exitButton.setStyle("-fx-background-color: #000000;");


        Text text1 = new Text("Welcome ");
        Text text2 = new Text("to ");
        Text text3 = new Text("Shop ");
        Text text4 = new Text("Tavern");

        Font font = Font.font("Arial", FontWeight.BOLD, 28);
        text1.setFont(font);
        text2.setFont(font);
        text3.setFont(font);
        text4.setFont(font);

        Stop[] stops = new Stop[]{new Stop(0, Color.PURPLE), new Stop(0.5, Color.BLUE), new Stop(1, Color.RED)};
        LinearGradient linearGradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);

        text1.setFill(linearGradient);
        text2.setFill(linearGradient);
        text3.setFill(linearGradient);
        text4.setFill(linearGradient);

        TextFlow welcomeText = new TextFlow(text1, text2, text3, text4);


        buyButton.setOnAction(e -> openBuyWindow.execute());
        sellButton.setOnAction(e -> openSellWindow.execute());
        exitButton.setOnAction(e -> primaryStage.close());


        VBox vbox = new VBox(10);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(welcomeText, buyButton, sellButton,   exitButton);


        StackPane root = new StackPane();
        root.getChildren().addAll(vbox);


        root.setStyle("-fx-background-color: #000000;");


        mainScene = new Scene(root, 400, 200);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }

}
