package io.github.flaggton.vogelpetdoctor.helper;

import io.github.flaggton.vogelpetdoctor.MainApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.function.Consumer;

public class HelperFunctions {

    public static void loadFxmlContentIntoMainStageCenter(URL absoluteFxmlFileUrl, @SuppressWarnings("rawtypes") Consumer initMethodOfController) throws IOException {
        FXMLLoader loader = new FXMLLoader(absoluteFxmlFileUrl);
        BorderPane borderPane = (BorderPane) MainApplication.mainStage.getScene().getRoot();
        borderPane.setCenter(loader.load());
        Object viewController = loader.getController();
        if (initMethodOfController != null) {
            //noinspection unchecked
            initMethodOfController.accept(viewController);
        }
    }
}
