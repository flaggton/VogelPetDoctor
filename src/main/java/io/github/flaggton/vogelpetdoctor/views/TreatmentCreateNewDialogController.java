package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Treatment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TreatmentCreateNewDialogController {
    @FXML
    private TextField petIdTextField;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField commentTextField;
    @FXML
    private TableView<Treatment> tableViewTreatment;

    public void init(TableView<Treatment> tableViewTreatment) {
        this.tableViewTreatment = tableViewTreatment;
    }

    public void onCreateNewButtonClick() {
        try {
            Treatment t = new Treatment(
                    null,
                    Long.parseLong(petIdTextField.getText()),
                    dateDatePicker.getValue(),
                    commentTextField.getText());

            HibernateQueryUtil.Inserter.insertOne(t);

            ObservableList<Treatment> l = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Treatment.class).findAll());
            tableViewTreatment.setItems(l);

            Stage stage = (Stage) petIdTextField.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
