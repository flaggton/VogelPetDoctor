package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Owner;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

import java.util.List;

public class OwnersSubviewController {

    @FXML
    ListView<Owner> listViewOwners;

    public void init() {
        try {
            List<Owner> ownerList = HibernateQueryUtil.Finder.findWithBuilder(Owner.class).findAll();
            listViewOwners.getItems().addAll(ownerList);
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
}
