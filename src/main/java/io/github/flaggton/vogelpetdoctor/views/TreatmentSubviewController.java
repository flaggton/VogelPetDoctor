package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.jfxDialogs.JfxDialogUtil;
import io.github.flaggton.vogelpetdoctor.data.Treatment;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

import java.io.IOException;

public class TreatmentSubviewController {
    @FXML
    private TableView<Treatment> tableViewTreatment;

    public void init() {
        try {
            HelperFunctions.addColumn(tableViewTreatment, "ID", "treatmentId");
            HelperFunctions.addColumn(tableViewTreatment, "Pet ID", "petId");
            HelperFunctions.addColumn(tableViewTreatment, "Date", "date");
            HelperFunctions.addColumn(tableViewTreatment, "Comment", "comment");
            ObservableList<Treatment> treatment = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Treatment.class).findAll());
            tableViewTreatment.setItems(treatment);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onCreateNewButtonClick() throws IOException {
        JfxDialogUtil.createAndShowFxmlDialog(
                "Create new Treatment",
                true,
                false,
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/treatment-create-new-dialog.fxml"),
                null,
                c -> ((TreatmentCreateNewDialogController) c).init(tableViewTreatment),
                null);
    }

    public void onEditButtonClick() throws IOException {
        if (tableViewTreatment.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        JfxDialogUtil.createAndShowFxmlDialog(
                "Edit this Treatment",
                true,
                false,
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/treatment-edit-dialog.fxml"),
                null,
                c -> ((TreatmentEditDialogController) c).init(tableViewTreatment),
                null);
    }

    public void onDeleteButtonClick() {
        if (tableViewTreatment.getSelectionModel().getSelectedItem() == null) {
            return;
        }
        boolean okButtonIsClicked = JfxDialogUtil.displayConfirmDialogAndGetResult(
                "Delete currently selected Treatment?",
                "Are you sure you want to delete this treatment?");
        if (okButtonIsClicked) {
            Treatment currentlySelectedTreatment = tableViewTreatment.getSelectionModel().getSelectedItem();
            try {
                HibernateQueryUtil.Deleter.deleteOne(currentlySelectedTreatment);
                ObservableList<Treatment> treatments = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Treatment.class).findAll());
                tableViewTreatment.setItems(treatments);
            } catch (Exception e) {
                JfxDialogUtil.createErrorDialog("The Treatment could not be deleted", e).showAndWait();
            }
        }
    }
}