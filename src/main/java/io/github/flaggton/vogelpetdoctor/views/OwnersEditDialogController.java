package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class OwnersEditDialogController {
    @FXML
    private TextField firstNameTextField;
    @FXML
    private TextField lastNameTextField;
    @FXML
    private TextField eMailTextField;
    @FXML
    private DatePicker dateOfBirthDatePicker;
    private TableView<Owner> tableViewOwners;
    private Owner currentlySelectedOwner;

    public void init(TableView<Owner> tableViewOwners){
        this.tableViewOwners = tableViewOwners;
        currentlySelectedOwner = tableViewOwners.getSelectionModel().getSelectedItem();
        firstNameTextField.setText(currentlySelectedOwner.getFirstName());
        lastNameTextField.setText(currentlySelectedOwner.getLastName());
        eMailTextField.setText(currentlySelectedOwner.getEmail());
        dateOfBirthDatePicker.setValue(currentlySelectedOwner.getDateOfBirth());
    }

    public void onEditButtonClick(){
        try {
            currentlySelectedOwner.setFirstName(firstNameTextField.getText());
            currentlySelectedOwner.setLastName(lastNameTextField.getText());
            currentlySelectedOwner.setEmail(eMailTextField.getText());
            currentlySelectedOwner.setDateOfBirth(dateOfBirthDatePicker.getValue());
            HibernateQueryUtil.Updater.updateOne(currentlySelectedOwner);
            ObservableList<Owner> owners = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll());
            tableViewOwners.setItems(owners);
            ((Stage)firstNameTextField.getScene().getWindow()).close();
        } catch (Exception e) {
            JfxDialogUtil.createErrorDialog("Could not edit selected owner", e).showAndWait();
        }
    }
}