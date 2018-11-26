package controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;

import genericGraphs.Graph;
import genericGraphs.GraphAdjList;
import genericGraphs.GraphMatrix;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Tooltip;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TheMysteriousXController implements Initializable{

	private Graph<String> graph;
	
    @FXML
    private JFXButton createGraphBut;

    @FXML
    private JFXButton addCamaradeBut;

    @FXML
    private JFXButton addRelationBut;

    @FXML
    private JFXButton solveBut;

    @FXML
    private JFXButton backBut;

    @FXML
    private JFXListView<String> graphList;

    @FXML
    private JFXComboBox<String> graphReprBox;

    @FXML
    void createGraph(ActionEvent event) {
    	if(graphReprBox.getValue() == null){
    		System.out.println("Please select a graph representation");
    	}
    	else if(graphReprBox.getValue().equals("Adjacency list")) {
    		System.out.println("Adjacency list graph created");
    		graph = new GraphAdjList<>(false, false);

    		
    	}else if(graphReprBox.getValue().equals("Adjacency matrix")){
    		System.out.println("Adjacency matrix graph created");
    		int numVertices = Integer.parseInt(createInputDialog("Adjaceny graph matrix ","Amount of persons in the network")); 
    		graph = new GraphMatrix<>(numVertices, false, false);

    	}
    	
    	if(graphReprBox.getValue() != null) {
    		addCamaradeBut.setDisable(false);
    		addRelationBut.setDisable(false);
    		graphReprBox.setDisable(true);
        	createGraphBut.setDisable(true);
    	}
    	updateGUI();
    }

    @FXML
    void addCamarade(ActionEvent event) {
    	
    }

    @FXML
    void addRelation(ActionEvent event) {

    }

    @FXML
    void goBack(ActionEvent event) {
    	try {
    		Parent root = FXMLLoader.load(getClass().getResource("/view/window.fxml"));
        	Scene scene = new Scene(root);
        	Stage stage = (Stage) backBut.getScene().getWindow();
        	stage.setScene(scene);	
        	stage.show();
    	}catch (Exception e) {
    		e.printStackTrace();
    	}
    }

    @FXML
    void solve(ActionEvent event) {

    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		graphReprBox.getItems().add("Adjacency list");
		graphReprBox.getItems().add("Adjacency matrix");
		addCamaradeBut.setDisable(true);
		addRelationBut.setDisable(true);
		
		
		final Tooltip tooltip = new Tooltip();
		tooltip.setText(
		    "\nHere you can add a person into the X network\n" +
		    "in other words you can add a vertex into the graph\n"  
		);
		Image img = new Image(getClass().getResourceAsStream("/img/user.png"), 100, 100, false, false);
		tooltip.setGraphic(new ImageView(img));
		addCamaradeBut.setTooltip(tooltip);
		
		final Tooltip wormholeTooltip = new Tooltip();
		wormholeTooltip.setText(
				"\n Here you can add an association between persons in the X network\n" +
				"in other words you can add an edge between to vertices of the graph"
				);
		Image wormholeImg = new Image(getClass().getResourceAsStream("/img/user2.png"), 100, 100, false, false);
		wormholeTooltip.setGraphic(new ImageView(wormholeImg));
		addRelationBut.setTooltip(wormholeTooltip);
		
	}
	
	String createInputDialog(String title, String message) {
		TextInputDialog dialog = new TextInputDialog("");
	    dialog.setTitle(title);
	    dialog.setHeaderText(null);
	    dialog.setContentText(message);

	    Optional<String> result = dialog.showAndWait();
	    if (result.isPresent()){
	    	return result.get();
	    }else {
	    	Alert alert = new Alert(AlertType.ERROR);
	    	alert.setContentText("Wrong format, please try again, make sure of not letting any space blank");
	    	alert.showAndWait();
	    	throw new IllegalArgumentException("");
	    }
    }
	void updateGUI() {
		if(graph != null) {
			graphList.getItems().clear();
			graphList.getItems().add(graph.toString());
		}
	}


}
