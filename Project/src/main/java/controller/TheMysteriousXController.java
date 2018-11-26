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
import genericGraphs.Vertex;
import javafx.beans.value.ObservableValue;
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
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Please select a graph representation first ");
    		alert.showAndWait();
    	}
    	else if(graphReprBox.getValue().equals("Adjacency list")) {
    		graph = new GraphAdjList<>(false, false);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Adjacency list graph created");
    		alert.showAndWait();

    		
    	}else if(graphReprBox.getValue().equals("Adjacency matrix")){
    		int numVertices = Integer.parseInt(createInputDialog("Adjaceny graph matrix ","Amount of persons in the network")); 
    		graph = new GraphMatrix<>(numVertices, false, false);
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Adjacency matrix graph created");
    		alert.showAndWait();

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
    	String nameVertex = createInputDialog("Add a camarade", "Camarade name");
    	graph.addVertex(nameVertex);
    	updateGUI();
    }

    @FXML
    void addRelation(ActionEvent event) {
    	String nameVertex1 = createInputDialog("Add a relation", "Enter a camarade name");
    	String nameVertex2 = createInputDialog("Add relation", "Enter a camarade name");
    	graph.addEdge(graph.getVertex(nameVertex1), graph.getVertex(nameVertex2));
    	updateGUI();
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
    	String camarade1 = createInputDialog("Solution", "Digit the name of the camarade who needs help");
    	String camarade2 = createInputDialog("Solution", "Digit the name of the camarade from which the help is needed");
    	graph.bfs(graph.getVertex(camarade1));
    	int res = graph.getVertex(camarade2).getD()-1 ;
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Solution");
    	alert.setContentText("The amount of camarades between " + camarade1 + " and " + camarade2 + " is " + res);
    	alert.showAndWait();
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
		
	    graphList.getSelectionModel().selectedItemProperty().addListener(
	            (ObservableValue<? extends String> ov, String old_val, 
	                String new_val) -> {
	                	int endIndex = new_val.indexOf("-");
	                    String vertex = new_val.substring(0, endIndex);
	                    String vertex2 = createInputDialog("Add edge", "Add second vertex");
	                    graph.addEdge(graph.getVertex(vertex), graph.getVertex(vertex2));
	                    updateGUI();
	        });
		
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
			for(Vertex<String> v: graph.getVertices()) {
				String s = v + "-> " +	graph.getAdjacent(v).toString();
				graphList.getItems().add(s);
			}
		}
	}


}
