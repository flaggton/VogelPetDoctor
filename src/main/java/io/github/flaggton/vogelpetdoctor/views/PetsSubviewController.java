package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Pet;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import java.io.IOException;

public class PetsSubviewController {
    @FXML
    TableView<Pet> tableViewPets;

    public void init() {
        try {
            HelperFunctions.addColumn(tableViewPets, "ID", "id");
            HelperFunctions.addColumn(tableViewPets, "Owner ID", "ownerId");
            HelperFunctions.addColumn(tableViewPets, "Name", "name");
            HelperFunctions.addColumn(tableViewPets, "Animal Type", "animalType");
            ObservableList<Pet> pets = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
            tableViewPets.setItems(pets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void onCreateNewButtonClick() throws IOException {
        JfxDialogUtil.createAndShowFxmlDialog(
                "Create new pet",
                true,
                false,
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/pets-create-new-dialog.fxml"),
                null,
                c -> ((PetsCreateNewDialogController) c).init(tableViewPets),
                null);
    }

    public void onEditButtonClick() throws IOException {
        JfxDialogUtil.createAndShowFxmlDialog("Edit pet",
                true,
                false,
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/pets-edit-dialog.fxml"),
                null,
                c -> ((PetsEditDialogController) c).init(tableViewPets),
                null);
    }

    public void onDeleteButtonClick() {
        boolean okButtonIsClicked = JfxDialogUtil.displayConfirmDialogAndGetResult(
                "Delete current pet?",
                "Are you sure you want to delete this pet?");
        if (okButtonIsClicked) {
            Pet currentlySelectedPet = tableViewPets.getSelectionModel().getSelectedItem();
            try {
                HibernateQueryUtil.Deleter.deleteOne(currentlySelectedPet);
                ObservableList<Pet> pets = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
                tableViewPets.setItems(pets);
            } catch (Exception e) {
                JfxDialogUtil.createErrorDialog("The pet could not be deleted", e).showAndWait();
            }
        }
    }
}