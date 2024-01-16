package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

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
}
