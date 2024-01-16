package io.github.flaggton.vogelpetdoctor.views;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import io.github.flaggton.vogelpetdoctor.data.Pet;
import io.github.flaggton.vogelpetdoctor.helper.HelperFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class PetsSubviewController {
    @FXML
    TableView<Pet> tableViewPets;

    public void init() {
        try {
            HelperFunctions.addColumn(tableViewPets, "ID", "id");
            HelperFunctions.addColumn(tableViewPets, "Owner ID", "ownerId");
            HelperFunctions.addColumn(tableViewPets, "Name", "name");
            HelperFunctions.addColumn(tableViewPets, "Animal Type", "animalType");
            ObservableList<Pet> pets = FXCollections.observableArrayList(HibernateQueryUtil.Finder.findWithBuilder(Pet.class).findAll());
            tableViewPets.setItems(pets);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}