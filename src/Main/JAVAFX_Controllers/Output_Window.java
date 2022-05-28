package JAVAFX_Controllers;

import components.API;
import components.Field;
import components.ObjectField;
import components.Service;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.text.Font;

import java.util.ArrayList;


public class Output_Window {
    //------------------------------------------------FX IDs-----------------------------------------//

    @FXML
    private TextArea FieldName_Area;
    @FXML
    private TextArea AllowedValues_Area;
    @FXML
    private TextArea Mandatory_Area;
    @FXML
    private ListView<API> API_NAMES;

    @FXML
    private ListView<Field> RequestField_Names;
    @FXML
    private ListView<Field> ResponseField_Names;
    @FXML
    private Label ComponentsLabel;
    @FXML
    private Label RequestLabel;

    @FXML
    private Label ResponseLabel;

    @FXML
    private Label API_Label;


    //------------------------------------------------Initialization-----------------------------------------//
    public void initialize() {
        // Setting the Label fonts here because importing the font doesn't work in css stylesheet
        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 24);
        ComponentsLabel.setFont(LabelFont);
        RequestLabel.setFont(LabelFont);
        ResponseLabel.setFont(LabelFont);
        API_Label.setFont(LabelFont);


        // Get the returned service from the Utility class
        Service ReturnedService = User_Input.getService1();
        assert ReturnedService != null;

        // fills the API names listview with the array list from returned service
        populateAPINames(ReturnedService);


        API_NAMES.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<API>() {
            @Override
            public void changed(ObservableValue<? extends API> observableValue, API api, API t1) {
                if (t1 != null || api != null) {
                    API selectedAPI = API_NAMES.getSelectionModel().getSelectedItem();
                    FieldName_Area.setText("Current API:   " + API_NAMES.getSelectionModel().getSelectedItem().getName() + "\n");
                    AllowedValues_Area.setText("Operation:   " + (API_NAMES.getSelectionModel().getSelectedItem().getOperation()) + "\n");
                    Mandatory_Area.setText("URL:   " + API_NAMES.getSelectionModel().getSelectedItem().getURL() + "\n");
                    populateRequestObjects(selectedAPI);
                    populateResponseObjects(selectedAPI);
                }
            }
        }); // Listener that adjust changes in the API names' List

        RequestField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
            @Override
            public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
                if (t1 != null) {
                    Field item = RequestField_Names.getSelectionModel().getSelectedItem();
                    populateAllTextAreas(item);


                }
            }
        });// Listener that adjust changes in the Request fields names' List

        ResponseField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
            @Override
            public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
                if (t1 != null) {
                    Field item = ResponseField_Names.getSelectionModel().getSelectedItem();
                    populateAllTextAreas(item);


                }
            }
        });// Listener that adjust changes in the API names' List

    }

    //------------------------------------------------Helper Methods for Initialization-----------------------------------------//

    // These two function will allow me to print on text Area some messages instead of  just the to string method
    private String AllowedValues_print(ArrayList<String> AllowedValues) {
        return AllowedValues.isEmpty() ? "All values allowed" : AllowedValues.toString();
    }

    private String Mandatory_Convert(Boolean mandatory) {
        return mandatory ? "YES" : "NO";


    }

    public String toTextArea(Field field) {


        ArrayList<Field> childrenFields;
        String FieldNames = null, AllowedValues = null, Mandatory = null;

        String result = null;
        if (field.getIo() == 'I') {
            FieldNames = "Current object:   " + RequestField_Names.getSelectionModel().getSelectedItem().getName() + "\n";
            AllowedValues = "Allowed values:   " + AllowedValues_print(RequestField_Names.getSelectionModel().getSelectedItem().getAllowedValues()) + "\n";
            Mandatory = "Mandatory:   " + Mandatory_Convert(RequestField_Names.getSelectionModel().getSelectedItem().isMandatory()) + "\n";
        } else if (field.getIo() == 'O') {
            FieldNames = "Current object:   " + ResponseField_Names.getSelectionModel().getSelectedItem().getName() + "\n";
            AllowedValues = "Allowed values:   " + AllowedValues_print(ResponseField_Names.getSelectionModel().getSelectedItem().getAllowedValues()) + "\n";
            Mandatory = "Mandatory:   " + Mandatory_Convert(ResponseField_Names.getSelectionModel().getSelectedItem().isMandatory()) + "\n";
        }


        if (field instanceof ObjectField) {

            childrenFields = ((ObjectField) field).getChildrenFields();

            for (int i = 0; i < childrenFields.size(); i++) {
                FieldNames += childrenFields.get(i).getName() + "\n";
                AllowedValues += "Allowed values:   " + AllowedValues_print(childrenFields.get(i).getAllowedValues()) + "\n";
                Mandatory += "Mandatory:   " + Mandatory_Convert(childrenFields.get(i).isMandatory()) + "\n";
            }


        }

        result = FieldNames + "//" + AllowedValues + "//" + Mandatory;
        return result;
    }// This function Stores the Components into a string to be shown on the textArea

    private void populateAllTextAreas(Field SelectedItem) {
        String AllText = toTextArea(SelectedItem);
        if (AllText != null) {
            // to split the string into the three text Areas
            String[] Extraction = AllText.split("//");

            FieldName_Area.setText(Extraction[Extraction.length - 3]);
            AllowedValues_Area.setText(Extraction[Extraction.length - 2]);
            Mandatory_Area.setText(Extraction[Extraction.length - 1]);

        } else {
            FieldName_Area.clear();
            AllowedValues_Area.clear();
            Mandatory_Area.clear();
        }

    }  // Shows the stored String on the three text Areas

    //------------------------------------------------ListViews' population-----------------------------------------//
    private void populateAPINames(Service service) {
        // Populate the listview with the API arraylist in the service
        API_NAMES.getItems().setAll(service.getAPIs());

        // Only on element in the list view can be selected at a time
        API_NAMES.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    private void populateRequestObjects(API SelectedAPI) {
        RequestField_Names.getItems().clear();
        RequestField_Names.getItems().setAll(SelectedAPI.getRequestObjects());
    }// Fills the Request fields list with the fields

    private void populateResponseObjects(API SelectedAPI) {
        ResponseField_Names.getItems().clear();

        ResponseField_Names.getItems().setAll(SelectedAPI.getResponseObjects());

    }// Fills the Response fields list with the fields


}

