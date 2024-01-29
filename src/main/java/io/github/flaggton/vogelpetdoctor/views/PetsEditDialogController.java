package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PetsEditDialogController {
    @FXML
    private TextField ownerIdTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField animalTypeTextField;
    private TableView<Pet> tableViewPet;
    private Pet currentlySelectedPet;

    public void init(TableView<Pet> tableViewPet) {
        this.tableViewPet = tableViewPet;
        currentlySelectedPet = tableViewPet.getSelectionModel().getSelectedItem();
        ownerIdTextField.setText(String.valueOf(currentlySelectedPet.getOwnerId()));
        nameTextField.setText(currentlySelectedPet.getName());
        animalTypeTextField.setText(currentlySelectedPet.getAnimalType());
    }

    public void onEditButtonClick() {
        try {
            currentlySelectedPet.setOwnerId(Long.parseLong(ownerIdTextField.getText()));
            currentlySelectedPet.setName(nameTextField.getText());
            currentlySelectedPet.setAnimalType(animalTypeTextField.getText());
            HibernateQueryUtil.Updater.updateOne(currentlySelectedPet);
            ObservableList<Pet> l = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
            tableViewPet.setItems(l);

            //Aktualisieren geht nicht?
            Stage stage = (Stage) ownerIdTextField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            JfxDialogUtil.createErrorDialog("Could not edit selected pet", e).showAndWait();
        }
    }
}
