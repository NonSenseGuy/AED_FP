package controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class WormholesController implements Initializable{

    @FXML
    private JFXButton createGraphBut;

    @FXML
    private JFXListView<?> grahList;

    @FXML
    private JFXComboBox<?> graphReprBox;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		graphReprBox.getItems().add("Adjacency list");
		
		
	}

}
