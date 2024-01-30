package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Pet;
import io.github.flaggton.vogelpetdoctor.enums.AnimalType;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;

public class PetsEditDialogController {
    @FXML
    private TextField ownerIdTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private ChoiceBox<AnimalType> animalTypeChoiceBox;
    private TableView<Pet> tableViewPet;
    private Pet currentlySelectedPet;

    public void init(TableView<Pet> tableViewPet) {
        this.tableViewPet = tableViewPet;
        animalTypeChoiceBox.setItems(FXCollections.observableList(List.of(AnimalType.values())));
        animalTypeChoiceBox.setConverter(HelperFunctions.createAnimalTypeChoiceBoxConverter());

        currentlySelectedPet = tableViewPet.getSelectionModel().getSelectedItem();
        ownerIdTextField.setText(String.valueOf(currentlySelectedPet.getOwnerId()));
        nameTextField.setText(currentlySelectedPet.getName());
        animalTypeChoiceBox.getSelectionModel().select(currentlySelectedPet.getAnimalType());
    }


    public void onEditButtonClick() {
        try {
            currentlySelectedPet.setOwnerId(Long.parseLong(ownerIdTextField.getText()));
            currentlySelectedPet.setName(nameTextField.getText());
            currentlySelectedPet.setAnimalType(animalTypeChoiceBox.getValue());
            HibernateQueryUtil.Updater.updateOne(currentlySelectedPet);
            ObservableList<Pet> l = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
            tableViewPet.setItems(l);
            tableViewPet.refresh();
            Stage stage = (Stage) ownerIdTextField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            JfxDialogUtil.createErrorDialog("Could not edit selected pet", e).showAndWait();
        }
    }
}
