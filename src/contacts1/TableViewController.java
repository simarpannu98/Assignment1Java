/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts1;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static java.util.Collections.list;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author simarjit singh pannu
 */
public class TableViewController implements Initializable {
    
@FXML private TableView<Contact> ContactTable;
@FXML private TableColumn<Contact , Integer> c_ID;
@FXML private TableColumn<Contact , String> firstName;
@FXML private TableColumn<Contact , String> lastName;
@FXML private TableColumn<Contact , String> state;
@FXML private TableColumn<Contact , String> phoneNumber;
@FXML private Label search;


    /**
     * Initializes the controller class.
     */
//this is the search button which will filter all the results

//FilteredList filter = new FilteredList(Contact,e->true);



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        c_ID.setCellValueFactory(new PropertyValueFactory<Contact , Integer>("c_ID"));
        firstName.setCellValueFactory(new PropertyValueFactory<Contact , String>("firstName"));
        lastName.setCellValueFactory(new PropertyValueFactory<Contact , String>("lastName"));
        state.setCellValueFactory(new PropertyValueFactory<Contact , String>("state"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<Contact , String>("phoneNumber"));

        try{
            LoadContact();
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
        }
        // TODO
    }    
    
    //FilteredList filter = new FilteredList(Contact,e->true);

    /**
     * this method displays all the contacts from the database 
     * @throws SQLException 
     */
    public void LoadContact() throws SQLException
    {
        ObservableList< Contact> Contacts = FXCollections.observableArrayList();
       
        Connection conn = null; 
        Statement statement = null;
        ResultSet resultSet = null;
        
        try {
            //connecting to the database
            conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es/"

                    + "gc200359541", "gc200359541", "wl3tDZWsQf");
            
            statement = conn.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM Contact");
            
            while(resultSet.next())
                    {
                        Contact newContact = new Contact(resultSet.getString("firstName"),
                                                         resultSet.getString("lastName"),
                                                          resultSet.getString("state"),
                                                          resultSet.getString("phoneNumber"),
                                                          resultSet.getString("address"));
                                
                                newContact.setC_ID(resultSet.getInt("c_ID"));
                     Contacts.add(newContact);   
                     
                    }
            ContactTable.getItems().addAll(Contacts);
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        
        finally{
            if( conn != null)
                conn.close();
       if (statement != null)
           statement.close();
        if( resultSet != null)
            resultSet.close();
        }
    }
    
   /**
    * this method changes the scene when the user clicks on the create button and loads FXMLDocument.fxml
    * @param event
    * @throws IOException 
    */
    public void CreateButton(ActionEvent event) throws IOException 
    {
        
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage box = (Stage)((Node)event.getSource()).getScene().getWindow();  
        
        box.setScene(tableViewScene);
        box.show();
    }
    
    
    
}
