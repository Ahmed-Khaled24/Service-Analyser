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
    private TextArea TextArea;
    @FXML
    private TextArea ResponseArea;
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

        Font LabelFont = Font.loadFont(getClass().getResourceAsStream("Lato-Bold.ttf"), 24);
        ComponentsLabel.setFont(LabelFont);
        RequestLabel.setFont(LabelFont);
        ResponseLabel.setFont(LabelFont);
        API_Label.setFont(LabelFont);



        Font textFont = Font.loadFont(getClass().getResourceAsStream("Lora-Regular.ttf"), 14);
        TextArea.setStyle("-fx-font-family: 'Cambria';-fx-font-size: 18px ");

        RequestField_Names.setStyle("-fx-font-family: 'Cambria';-fx-font-size: 16px ");



        Font.loadFont(ProjectApplication.class.getResource("Lato-Bold.ttf").toExternalForm(), 10);
        ResponseField_Names.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 16");
        API_NAMES.setStyle("-fx-font-family: 'Cambria'; -fx-font-size: 16px");



        ReturnedService = Utility.constructService(User_Input.filePath);
        assert ReturnedService != null;

        // Populate the listview with the API arraylist in the service
        API_NAMES.getItems().setAll(ReturnedService.getAPIs());

        // Only on element in the list view can be selected at a time
        API_NAMES.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // The first Item in the list is selected when u open the window for the first time
        API_NAMES.getSelectionModel().selectFirst();








        for (int i=0 ; i<ReturnedService.getAPIs().size() ;i++){

            RequestField_Names.getItems().setAll(ReturnedService.getAPIs().get(i).getRequestObjects());
            ResponseField_Names.getItems().setAll(ReturnedService.getAPIs().get(i).getResponseObjects());


            ArrayList<Field> childrenFields = new ArrayList<>();
            for(int j=0 ; j<ReturnedService.getAPIs().get(i).getRequestObjects().size() ; j++){

                Field temp = ReturnedService.getAPIs().get(i).getResponseObjects().get(j);

                if(temp instanceof ObjectField){
                    childrenFields = ((ObjectField) temp).getChildrenFields();
                }else{break;}

//          RequestArea.appendText(toTextArea(childrenFields,temp));


            }



            for(int k=0 ; k<ReturnedService.getAPIs().get(i).getRequestObjects().size() ; k++){

                Field temp = ReturnedService.getAPIs().get(i).getRequestObjects().get(k);

                if(temp instanceof ObjectField){
                    childrenFields = ((ObjectField) temp).getChildrenFields();
                }else{break;}

//          RequestArea.appendText(toTextArea(childrenFields,temp));


            }




   RequestField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
       @Override
       public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
           if(t1 != null){
               Field item = RequestField_Names.getSelectionModel().getSelectedItem();
               TextArea.setText(NewTextArea(item));

           }
       }
   });

            ResponseField_Names.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Field>() {
                @Override
                public void changed(ObservableValue<? extends Field> observableValue, Field field, Field t1) {
                    if (t1 != null){
                        Field item = ResponseField_Names.getSelectionModel().getSelectedItem();
                        TextArea.setText(NewTextArea(item));
                    }
                }
            });





        }






    }














    public String toTextArea(ArrayList<Field> childrenFields ){
//        String temp = requestObject.getName() + ": ";
        String temp = childrenFields.get(0).getName() + "\t\t"+ " Allowed Values: " + childrenFields.get(0).getAllowedValues().toString()  + "\t\t"
                + " isMandatory " +childrenFields.get(0).isMandatory() + "\n" ;
        for(int i=1 ; i<childrenFields.size() ; i++){
            temp += childrenFields.get(i).getName() + "\t\t"+ " Allowed Values: " + childrenFields.get(i).getAllowedValues().toString() + "\t\t"
                    + " isMandatory " +childrenFields.get(i).isMandatory() +"\n";
        }
        return temp;
    }

    public String NewTextArea(Field field){
        String result = null;
        ArrayList<Field> temp;
        if(field instanceof ObjectField){
            temp = ((ObjectField) field).getChildrenFields();
            result = toTextArea(temp);
        }
        return result;
    }


}

