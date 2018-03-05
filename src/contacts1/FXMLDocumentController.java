/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts1;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 *
 * @author simarjit singh pannu
 */
public class FXMLDocumentController implements Initializable {
    
    
    
    @FXML private TextField firstNameTextField;
    @FXML private TextField lastNameTextField;
    @FXML private TextField stateTextField;
    @FXML private TextField phoneNumberTextField;
    @FXML private TextField addressTextField;
    @FXML private Label errorMessg;
    @FXML private ImageView image;

    private File imagefile;

/**
 * This method saves the information which is filled by the user to the database.
 * When the user presses the save button the scene will be automatically changed
 * @param event 
 */    
  public void SaveContactButtonPushed(ActionEvent event)
  {
     
          
          
           try{

                if(firstNameTextField.getText().isEmpty()){

                 errorMessg.setText("First name is mandatory"); 

            }

            else if(lastNameTextField.getText().isEmpty()){

                errorMessg.setText("Last name is mandatory"); 

            }

            else if(phoneNumberTextField.getText().isEmpty()){

                 errorMessg.setText("Phone number is required"); 

            }

            else if(addressTextField.getText().isEmpty()){

                 errorMessg.setText("Address field is required");

            }
          
           Contact s1 = new Contact(firstNameTextField.getText(),lastNameTextField.getText(),stateTextField.getText()
                   ,phoneNumberTextField.getText(),addressTextField.getText());
           
           //errorMessg.setText("");
      s1.insertIntoDB();  //this will insert all the values into the database
      System.out.println("your contact has been saved !");
      
      //This will redirect the users to the main table page after they save their contact information
      Parent tableViewParent = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage box = (Stage)((Node)event.getSource()).getScene().getWindow();  
        
        box.setScene(tableViewScene);
        box.show();
           }
      
        catch (Exception e)

            {

                errorMessg.setText(e.getMessage());

            }
        
      }
      
  
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                 errorMessg.setText("");
    }
    
    /**
     * This method takes the user back to the main page when the go back button is pushed
     * @param event
     * @throws IOException 
     */
    
  
    public void Gobackpushed(ActionEvent event) throws IOException
    {
        
         Parent tableViewParent = FXMLLoader.load(getClass().getResource("TableView.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage box = (Stage)((Node)event.getSource()).getScene().getWindow();  
        
        box.setScene(tableViewScene);
        box.show();
    }
    
}
