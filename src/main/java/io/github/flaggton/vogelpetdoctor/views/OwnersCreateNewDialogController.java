package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OwnersCreateNewDialogController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField eMailTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    private TableView<Owner> tableViewOwners;

    public void init(TableView<Owner> tableViewOwners) {
        this.tableViewOwners = tableViewOwners;
    }

    public void onCreateOwnerButtonClick() {
        try {

            // Owner/Object erstellen
            Owner o = new Owner(null,
                    firstNameTextField.getText(),
                    lastNameTextField.getText(),
                    eMailTextField.getText(),
                    dateOfBirthDatePicker.getValue());

            // Owner/Object in die Datenbank speichern
            HibernateQueryUtil.Inserter.insertOne(o);

            // Tabelle aktualisieren
            ObservableList<Owner> l = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll());
            tableViewOwners.setItems(l);

            // Dialog schließen
            Stage stage = (Stage) firstNameTextField.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
