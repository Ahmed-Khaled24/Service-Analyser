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
import utility.Utility;

import java.util.ArrayList;


public class Output_Window {

    @FXML
    private TextArea FieldName_Area;
    @FXML
    private TextArea AllowedValues_Area;
    @FXML
    private TextArea Mandatory_Area;
    @FXML
    private ListView<API> API_NAMES;

    private  Service ReturnedService;

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



    public void initialize(){
        // Setting the Label fonts here because importing the font doesnt work in css stylesheet
        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 24);
        ComponentsLabel.setFont(LabelFont);
        RequestLabel.setFont(LabelFont);
        ResponseLabel.setFont(LabelFont);
        API_Label.setFont(LabelFont);



//        Font textFont = Font.loadFont(getClass().getResourceAsStream("Lora-Regular.ttf"), 14);
//        TextArea.setStyle("-fx-font-family: 'Cambria';-fx-font-size: 18px ");
//
//        RequestField_Names.setStyle("-fx-font-family: 'Cambria';-fx-font-size: 16px ");
//
//
//
//        Font.loadFont(ProjectApplication.class.getResource("Lato-Bold.ttf").toExternalForm(), 10);
//        ResponseField_Names.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 16px");
//        API_NAMES.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 16px");



        ReturnedService = Utility.constructService(User_Input.filePath);
        assert ReturnedService != null;

        // Populate the listview with the API arraylist in the service
        API_NAMES.getItems().setAll(ReturnedService.getAPIs());

        // Only on element in the list view can be selected at a time
        API_NAMES.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // The first Item in the list is selected when u open the window for the first time


//        RequestField_Names.getItems().setAll(ReturnedService.getAPIs().get(0).getRequestObjects().get(0));
//        ResponseField_Names.getItems().setAll(ReturnedService.getAPIs().get(0).getResponseObjects().get(0));










//        for (int i=0 ; i<ReturnedService.getAPIs().size() ;i++){
//
//            RequestField_Names.getItems().setAll(ReturnedService.getAPIs().get(i).getRequestObjects());
//            ResponseField_Names.getItems().setAll(ReturnedService.getAPIs().get(i).getResponseObjects());
//





//            ArrayList<Field> childrenFields = new ArrayList<>();
//            for(int j=0 ; j<ReturnedService.getAPIs().get(i).getRequestObjects().size() ; j++){
//
//                Field temp = ReturnedService.getAPIs().get(i).getResponseObjects().get(j);
//
//                if(temp instanceof ObjectField){
//                    childrenFields = ((ObjectField) temp).getChildrenFields();
//                }else{break;}
//
////          RequestArea.appendText(toTextArea(childrenFields,temp));
//
//
//            }
//
//
//            for(int k=0 ; k<ReturnedService.getAPIs().get(i).getRequestObjects().size() ; k++){
//
//                Field temp = ReturnedService.getAPIs().get(i).getRequestObjects().get(k);
//
//                if(temp instanceof ObjectField){
//                    childrenFields = ((ObjectField) temp).getChildrenFields();
//                }else{break;}
//
////          RequestArea.appendText(toTextArea(childrenFields,temp));

//            }



        API_NAMES.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<API>() {
            @Override
            public void changed(ObservableValue<? extends API> observableValue, API api, API t1) {
                if(t1 != null){
                    API selectedAPI = API_NAMES.getSelectionModel().getSelectedItem();
                    populateRequestObjects(selectedAPI);
                    populateResponseObjects(selectedAPI);
                }
            }
        });

   RequestField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
       @Override
       public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
           if(t1 != null){
               Field item = RequestField_Names.getSelectionModel().getSelectedItem();
               populateAllTextAreas(item);


           }
       }
   });

            ResponseField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
                @Override
                public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
                    if (t1 != null){
                        Field item = ResponseField_Names.getSelectionModel().getSelectedItem();
                        populateAllTextAreas(item);


                    }
                }
            });

        }


    // these two function will allow me to print on text Area some messages instead of  just the to string method
    private String AllowedValues_print(ArrayList<String> AllowedValues){
        return AllowedValues.isEmpty()? "All Values Allowed": AllowedValues.toString();
    }
    private String Mandatory_Convert(Boolean mandatory){
        return mandatory? "YES":"NO";
    }

   // This function Stores the Components into a string to be shown on the textArea
    public String toTextArea(Field field ){


        ArrayList<Field> childrenFields;
        String result =null;

         if(field instanceof ObjectField){
             String FieldNames = null , AllowedValues=null,Mandatory=null;

            childrenFields = ((ObjectField) field).getChildrenFields();


              FieldNames = childrenFields.get(0).getName() + "\n";
         AllowedValues = " Allowed Values: " +AllowedValues_print(childrenFields.get(0).getAllowedValues()) +"\n" ;
              Mandatory =  " isMandatory " +Mandatory_Convert(childrenFields.get(0).isMandatory()) + "\n" ;


             for(int i=1 ; i<childrenFields.size() ; i++){
                 FieldNames += childrenFields.get(i).getName() + "\n";
                 AllowedValues += " Allowed Values: " +AllowedValues_print(childrenFields.get(i).getAllowedValues()) +"\n" ;
                 Mandatory +=  " isMandatory " +Mandatory_Convert(childrenFields.get(i).isMandatory()) + "\n" ;
             }

             result = FieldNames + "//" + AllowedValues + "//" + Mandatory ;
         }




         return result;
        }


        // This function shows the stored String on the three text Areas
        private void populateAllTextAreas(Field SelectedItem){
            String AllText = toTextArea(SelectedItem);
            if(AllText != null) {
                // to split the string into the three text Areas
                String[] Extraction = AllText.split("//");

                FieldName_Area.setText(Extraction[Extraction.length - 3]);
                AllowedValues_Area.setText(Extraction[Extraction.length - 2]);
                Mandatory_Area.setText(Extraction[Extraction.length - 1]);

            }else {
                FieldName_Area.clear();
                AllowedValues_Area.clear();
                Mandatory_Area.clear();
            }

        }

        private void populateRequestObjects(API SelectedAPI){
        RequestField_Names.getItems().clear();
            RequestField_Names.getItems().setAll(SelectedAPI.getRequestObjects());
        }
    private void populateResponseObjects(API SelectedAPI){
        ResponseField_Names.getItems().clear();

        ResponseField_Names.getItems().setAll(SelectedAPI.getResponseObjects());

    }



}

