package io.github.flaggton.vogelpetdoctor.views;

import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;

import java.io.IOException;

public class MainViewController {
    public void onDashboardButtonClick() throws IOException {
        HelperFunctions.loadFxmlContentIntoMainStageCenter(
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/dashboard-subview.fxml"),
                controller -> ((DashboardSubviewController)controller).init());
    }
    public void onOwnersButtonClick() throws IOException {
        HelperFunctions.loadFxmlContentIntoMainStageCenter(
                getClass().getResource("/io/github/flaggton/vogelpetdoctor/views/owners-subview.fxml"),
                controller -> ((OwnersSubviewController)controller).init());
    }
    public void onPetsButtonClick() {

    }
    public void onTreatmentsButtonClick() {

    }
}