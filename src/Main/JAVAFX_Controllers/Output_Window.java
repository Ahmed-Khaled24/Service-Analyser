package JAVAFX_Controllers;

import components.API;
import components.Field;
import components.ObjectField;
import components.Service;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import utility.Utility;

import java.util.ArrayList;

public class Output_Window {
    @FXML
    private TextArea RequestArea;
    @FXML
    private TextArea ResponseArea;
    @FXML
    private ListView<API> API_NAMES;

    private  Service ReturnedService;




    public void initialize(){


        ReturnedService = Utility.constructService(User_Input.filePath);
        assert ReturnedService != null;

        // Populate the listview with the API arraylist in the service
        API_NAMES.getItems().setAll(ReturnedService.getAPIs());

        // Only on element in the list view can be selected at a time
        API_NAMES.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        // The first Item in the list is selected when u open the window for the first time
        API_NAMES.getSelectionModel().selectFirst();


        ArrayList<Field> childrenFields = new ArrayList<>();
        String[] tempo = new String[0];
   for (int i=0 ; i<ReturnedService.getAPIs().size() ;i++){

       for(int k=0 ; k<ReturnedService.getAPIs().get(i).getRequestObjects().size() ; k++){
          Field temp = ReturnedService.getAPIs().get(i).getRequestObjects().get(k);

          if(temp instanceof ObjectField){
              childrenFields = ((ObjectField) temp).getChildrenFields();
          }else{break;}

          RequestArea.appendText(toTextArea(childrenFields,temp));

//          for(int j=0 ; j<childrenFields.size() ; j++){
//              int h=1;
//              String temp2 = childrenFields.get(j).getName();
//              tempo[h] = temp2;
//
//
//
//          }

       }
//       RequestArea.setText(namess.toString());
//       System.out.println(namess.toString());
   }









    }

    public String toTextArea(ArrayList<Field> childrenFields , Field requestObject){
        String temp = requestObject.getName() + ": ";
        for(int i=0 ; i<childrenFields.size() ; i++){
            temp +=  childrenFields.get(i).getName() + "/";
        }
        temp += "\n";
        return temp;
    }


}

