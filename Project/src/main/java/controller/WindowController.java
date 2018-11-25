package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class WindowController implements Initializable {

    @FXML
    private JFXButton buttonW;
    
    @FXML
    private JFXButton buttonX;
    
    @FXML
    void wormholes(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/view/wormholes.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = (Stage) buttonW.getScene().getWindow();
        	stage.setScene(scene);
        	stage.show();
    	}catch(IOException e ) {
    		e.printStackTrace();
    	}
    	
    }
    
    @FXML
    void theMysteriousX(ActionEvent event) {
    	
    }

	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}
    
}
