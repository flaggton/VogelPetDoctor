package io.github.flaggton.vogelpetdoctor.helper;

import io.github.flaggton.vogelpetdoctor.MainApplication;
import io.github.flaggton.vogelpetdoctor.enums.AnimalType;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;

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
    public static <S, T> void addColumn(TableView<S> tableView, String columnName, String propertyValue) {
        TableColumn<S, T> column = new TableColumn<>(columnName);
        column.setCellValueFactory(new PropertyValueFactory<>(propertyValue));
        tableView.getColumns().add(column);
    }

    public static StringConverter<AnimalType> createAnimalTypeChoiceBoxConverter() {
        return new StringConverter<>() {
            @Override
            public String toString(AnimalType object) {
                //tenärer Operator. Bedeutet if (object == null) return "" else return uiText
                return object == null ? "" : object.getUiText();
            }

            @Override
            public AnimalType fromString(String string) {
                // Convert String back to AnimalType
                for (AnimalType type : AnimalType.values()) {
                    if (type.getUiText().equals(string)) {
                        return type;
                    }
                }
                return null; // Return null if not found (you might want to handle this case better)
            }
        };
    }

}
