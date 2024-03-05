package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Treatment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class TreatmentEditDialogController {
    @FXML
    private TextField petIdTextField;
    @FXML
    private DatePicker dateDatePicker;
    @FXML
    private TextField commentTextField;
    private TableView<Treatment> tableViewTreatments;
    private Treatment currentlySelectedTreatment;

    public void init(TableView<Treatment> tableViewTreatments) {
        this.tableViewTreatments = tableViewTreatments;
        currentlySelectedTreatment = tableViewTreatments.getSelectionModel().getSelectedItem();
        petIdTextField.setText(String.valueOf(currentlySelectedTreatment.getPetId()));
        dateDatePicker.setValue(currentlySelectedTreatment.getDate());
        commentTextField.setText(currentlySelectedTreatment.getComment());
    }

    public void onEditTreatmentButtonClick() {
        try {
            currentlySelectedTreatment.setPetId(Long.parseLong(petIdTextField.getText()));
            currentlySelectedTreatment.setDate(dateDatePicker.getValue());
            currentlySelectedTreatment.setComment(commentTextField.getText());
            HibernateQueryUtil.Updater.updateOne(currentlySelectedTreatment);
            ObservableList<Treatment> treatments = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Treatment.class).findAll());
            tableViewTreatments.setItems(treatments);
            tableViewTreatments.refresh();
            ((Stage) petIdTextField.getScene().getWindow()).close();
        } catch (Exception e) {
            JfxDialogUtil.createErrorDialog("Could not edit Treatment", e).showAndWait();
        }
    }
}