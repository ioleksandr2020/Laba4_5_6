package MainWindows;

import MainWindows.ShopTavernApp;
import javafx.stage.Stage;

public class OpenSellWindowCommand implements ShopTavernApp.Command {
    private Stage primaryStage;

    public OpenSellWindowCommand(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @Override
    public void execute() {

    }
}