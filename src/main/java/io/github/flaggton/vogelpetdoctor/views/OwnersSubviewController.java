package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class OwnersSubviewController {
    @FXML
    TableView<Owner> tableViewOwners;

    public void init() {
        try {
            HelperFunctions.addColumn(tableViewOwners, "ID", "id");
            HelperFunctions.addColumn(tableViewOwners, "First Name", "firstName");
            HelperFunctions.addColumn(tableViewOwners, "Last Name", "lastName");
            HelperFunctions.addColumn(tableViewOwners, "E-Mail", "email");
            HelperFunctions.addColumn(tableViewOwners, "Date of birth", "dateOfBirth");
            ObservableList<Owner> owners = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll());
            tableViewOwners.setItems(owners);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onCreateNewButtonClick() throws IOException {
        JfxDialogUtil.createAndShowFxmlDialog(
                "Create new owner",
                true,
                false,
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/owners-create-new-dialog.fxml"),
                null,
                c -> ((OwnersCreateNewDialogController) c).init(tableViewOwners),
                null);
    }

    public void onEditButtonClick() {

    }

    public void onDeleteButtonClick() {
        boolean okButtonIsClicked = JfxDialogUtil.displayConfirmDialogAndGetResult(
                "Delete current owner?",
                "Are you sure you want to delete this owner?");
        if (okButtonIsClicked) {
            Owner currentlySelectedOwner = tableViewOwners.getSelectionModel().getSelectedItem();
            try {
                HibernateQueryUtil.Deleter.deleteOne(currentlySelectedOwner);
                ObservableList<Owner> owners = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll());
                tableViewOwners.setItems(owners);
            } catch (Exception e) {
                JfxDialogUtil.createErrorDialog("The owner could not be deleted", e).showAndWait();
            }
        }
    }
}
