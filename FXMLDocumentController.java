/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sadp.pkg2;


import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author VIIMHALAN
 */
public class FXMLDocumentController implements Initializable {
    
    static Connection c = null;
    static ResultSet rs = null;
    static PreparedStatement pst = null;
    
    @FXML private Button login;

    @FXML private Button register;
    
    @FXML private Button adminMain;
    
    @FXML private Button userMain;
    
    @FXML private Button rmain;
    
    @FXML private Button lregister;
    
    @FXML private Button rlogin;

    @FXML private CheckBox adminBox;
    
    @FXML private CheckBox userBox;
    
    @FXML private Pane userPane;
   
    @FXML private Pane adminPane;    
    
    @FXML private TextField adminName;

    @FXML private PasswordField userPassword;
    
    @FXML private TextField userName;

    @FXML private PasswordField adminPassword;
    
    @FXML private CheckBox sportsTopic;
    
    @FXML private TextField userEmail;
    
    @FXML private CheckBox scienceTopic;

    @FXML private CheckBox healthTopic;
    
    
    
    @FXML
    private void loginButtonClicked(ActionEvent login) throws Exception {
        Parent pLogin = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scLogin = new Scene(pLogin);
        Stage stLogin = (Stage)((Node)login.getSource()).getScene().getWindow();
        stLogin.setScene(scLogin);
        stLogin.show();
    }
    
    @FXML
    private void reigsterButtonClicked(ActionEvent register) throws Exception {
        Parent pRegister = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene scRegister = new Scene(pRegister);
        Stage stRegister = (Stage)((Node)register.getSource()).getScene().getWindow();
        stRegister.setScene(scRegister);
        stRegister.show();
    }
    
    @FXML
    private void rloginButtonClicked(ActionEvent login) throws Exception {
        Parent prlogin = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scrlogin = new Scene(prlogin);
        Stage strlogin = (Stage)((Node)login.getSource()).getScene().getWindow();
        strlogin.setScene(scrlogin);
        strlogin.show();
    }
    
    @FXML
    private void lregisterButtonClicked(ActionEvent register) throws Exception {
        Parent plregister = FXMLLoader.load(getClass().getResource("Register.fxml"));
        Scene sclregister = new Scene(plregister);
        Stage stlregister = (Stage)((Node)register.getSource()).getScene().getWindow();
        stlregister.setScene(sclregister);
        stlregister.show();
    }
    
    @FXML
     private void register(ActionEvent event) throws Exception{    
        c = dbConnect.connectDb();
        String sql = "insert into users (userName,userEmail,userPassword,sportsTopic,scienceTopic,healthTopic) values (?,?,?,?,?,?)";
        try {
            pst = c.prepareStatement(sql);
            pst.setString(1, userName.getText());
            pst.setString(2, userEmail.getText());
            pst.setString(3, userPassword.getText());
            if(sportsTopic.isSelected()){
                pst.setString(4, "Subscribed");
            }
            else{
                pst.setString(4, "Not Subscribed");
            }
            if(scienceTopic.isSelected()){
                pst.setString(5, "Subscribed");
            }
            else{
                pst.setString(5, "Not Subscribed");
            }
            if(healthTopic.isSelected()){
                pst.setString(6, "Subscribed");
            }
            else{
                pst.setString(6, "Not Subscribed");
            }
            pst.execute();
            rmain.getScene().getWindow().hide();
            Parent pL = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scL = new Scene(pL);
            Stage stL = new Stage();
            stL.setScene(scL);
            stL.show();
            JOptionPane.showMessageDialog(null, "Registered");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }  
    }
     
    @FXML
    private void adminLogin(ActionEvent event) throws Exception {
        c = dbConnect.connectDb();
        String sql = "Select * From admin where adminName = ? and adminPassword = ?";
            pst = c.prepareStatement(sql);
            pst.setString(1, adminName.getText());
            pst.setString(2, adminPassword.getText());
            rs = pst.executeQuery();
            try {
                if(rs.next()){
                   adminMain.getScene().getWindow().hide();
                   Parent pMain = FXMLLoader.load(getClass().getResource("AdminMain.fxml"));
                   Scene scMain = new Scene(pMain);
                   Stage stMain = new Stage();
                   stMain.setScene(scMain);
                   stMain.show(); 
                }
                else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
    
    @FXML
    private void userLogin(ActionEvent event) throws Exception {
        c = dbConnect.connectDb();
        String sql = "Select * From users where userName = ? and userPassword = ?";
            pst = c.prepareStatement(sql);
            pst.setString(1, userName.getText());
            pst.setString(2, userPassword.getText());
            rs = pst.executeQuery();
            try {
                if(rs.next()){
                   userMain.getScene().getWindow().hide();
                   Parent pMain = FXMLLoader.load(getClass().getResource("UserMain.fxml"));
                   Scene scMain = new Scene(pMain);
                   Stage stMain = new Stage();
                   stMain.setScene(scMain);
                   stMain.show(); 
                }
                else{
                JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                }
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
    }
     
    @FXML 
    private void showPane(ActionEvent event) {
        if(userBox.isSelected()){
            adminBox.setSelected(false);
            userPane.setVisible(true);
            adminPane.setVisible(false);  
           }
        else if(adminBox.isSelected()){
            userBox.setSelected(false);
            userPane.setVisible(false);
            adminPane.setVisible(true); 
           }
        else{
            userPane.setVisible(false);
            adminPane.setVisible(false); 
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
    
}
