/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contacts1;

//import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.PreparedStatement;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *This is class in which all the variables are defined and instantiated
 * @author simarjit singh pannu
 */
public class Contact {
    
     private String firstName;
    private String lastName;
    private String state;
    private String phoneNumber;
    private String address;
private File imageFile;
private int c_ID;

    public Contact(String firstName, String lastName, String state, String phoneNumber, String address) {
        setFirstName(firstName);
        setLastName(lastName);
       setState(state);
        setPhoneNumber(phoneNumber);
        setAddress(address);
       // setImageFile(new File("./Desktop/default.jpg"));
    }

    public Contact(int c_ID) {
        this.c_ID = c_ID;
    }

    public int getC_ID() {
        return c_ID;
    }

    public void setC_ID(int c_ID) {
        if(c_ID >= 0)
                {
                    this.c_ID = c_ID;
                }
        
        else{
            throw new IllegalArgumentException("contact ID must be greater than zero");
        }
    }

    
    


     
    
    
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
        
        
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber.matches("[2-9]\\d{2}[-.]?\\d{3}[-.]\\d{4}"))
        this.phoneNumber = phoneNumber;
        else
            throw new IllegalArgumentException("The phone number should be in a NXX-XXX-XXXX pattern");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    public String Tostring()
    {
        return String.format("%s %s " , firstName , lastName);
    }
    
    /**
     * This method will put the values in the database
     */
    public void insertIntoDB() throws SQLException
    {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        
        try{
            //connecting to the database 
           conn = DriverManager.getConnection("jdbc:mysql://aws.computerstudi.es/"

                    + "gc200359541", "gc200359541", "wl3tDZWsQf");

            
      //string to hold values
     // System.out.println("There was an error connecting the database");
      
      String sql = "INSERT INTO Contact (firstName ,lastName , state , phoneNumber , address)" 
              + "VALUES (?,?,?,?,?)";
        
      
      preparedStatement = (PreparedStatement) conn.prepareStatement(sql);
      
      preparedStatement.setString(1,firstName);
      preparedStatement.setString(2,lastName);
      preparedStatement.setString(3,state);
      preparedStatement.setString(4,phoneNumber);
      preparedStatement.setString(5,address);
      
      preparedStatement.executeUpdate();
      
        }
        catch(Exception e)
        {
            System.err.println(e.getMessage());
        }
        finally
        {
            if(preparedStatement != null)
                preparedStatement.close();
            
            if(conn != null)
            conn.close();
               
    }
    }
}
