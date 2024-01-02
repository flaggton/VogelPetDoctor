package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class OwnersSubviewController {
    @FXML
    TableView<Owner> tableViewOwners;

    public void init() {
        try {
            ObservableList<Owner> owners = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll());

            addColumn(tableViewOwners, "ID", "id");
            addColumn(tableViewOwners, "First Name", "firstName");
            addColumn(tableViewOwners, "Last Name", "lastName");
            addColumn(tableViewOwners, "E-Mail", "email");
            addColumn(tableViewOwners, "Date of birth", "dateOfBirth");
            tableViewOwners.setItems(owners);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        Owner o = new Owner(null, "David", "WebServer", "vgnrejknaek@rever.de", LocalDate.of(1970, 1, 1));
//        try {
//            HibernateQueryUtil.Inserter.insertOne(o);
//        } catch (HibernateQueryUtilException e) {
//            throw new RuntimeException(e);
//        }
    }

    private void addColumn(TableView<Owner> tableView, String columnTitle, String fieldName) {
        TableColumn<Owner,String> firstNameCol = new TableColumn<>(columnTitle);
        firstNameCol.setCellValueFactory(new PropertyValueFactory<>(fieldName));
        tableView.getColumns().add(firstNameCol);
    }
}
