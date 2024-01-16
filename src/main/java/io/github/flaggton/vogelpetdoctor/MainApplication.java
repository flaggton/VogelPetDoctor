package io.github.flaggton.vogelpetdoctor;

import io.github.flaggton.vogelpetdoctor.data.DatabaseInitializer;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import io.github.flaggton.vogelpetdoctor.views.DashboardSubviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static Stage mainStage;

    @Override
    public void start(Stage stage) throws IOException {
        MainApplication.mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("views/main-view.fxml")); // relative classpath
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setWidth(500);
        stage.setHeight(500);
        stage.show();
        HelperFunctions.loadFxmlContentIntoMainStageCenter(
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/dashboard-subview.fxml"),
                controller -> ((DashboardSubviewController) controller).init());
        //        Alternativ:
        //        ((MainViewController) fxmlLoader.getController()).onDashboardButtonClick();

        DatabaseInitializer.init();
    }

    public static void main(String[] args) {
        launch(args);
    }
}