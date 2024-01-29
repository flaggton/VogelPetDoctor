package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Pet;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PetsCreateNewDialogController {
    @FXML
    private TextField ownerIdTextField;
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField animalTypeTextField;

    @FXML
    private TableView<Pet> tableViewPet;

    public void init(TableView<Pet> tableViewPet) {
        this.tableViewPet = tableViewPet;
    }

    public void onCreateNewPetButtonClick() {
        try {
            //neues Objekt
            Pet p = new Pet(
                    null,
                    Long.parseLong(ownerIdTextField.getText()),
                    nameTextField.getText(),
                    animalTypeTextField.getText());

            //Datenbank
            HibernateQueryUtil.Inserter.insertOne(p);

            //Ansicht aktualisieren
            ObservableList<Pet> l = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
            tableViewPet.setItems(l);

            //Dialog schließen
            Stage stage = (Stage) ownerIdTextField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
