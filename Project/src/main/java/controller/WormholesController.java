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

public class WormholesController implements Initializable{
	
	private Graph<String> graph;
	
    @FXML
    private JFXButton solveBut;

    @FXML
    private JFXButton backBut;
    
    @FXML
    private JFXButton createGraphBut;

    @FXML
    private JFXListView<String> graphList;

    @FXML
    private JFXComboBox<String> graphReprBox;
    
    @FXML
    private JFXButton addStarSysBut;
    
    @FXML
    private JFXButton addWormholeBut;

    @FXML
    void createGraph(ActionEvent event) {
    	if(graphReprBox.getValue() == null){
    		Alert alert = new Alert(AlertType.ERROR);
    		alert.setContentText("Please select a graph representation first ");
    		alert.showAndWait();
    	}
    	else if(graphReprBox.getValue().equals("Adjacency list")) {
    		graph = new GraphAdjList<>(true, true);
    		graph.addVertex("Solar system");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Adjacency list graph created");
    		alert.showAndWait();
    
    		
    	}else if(graphReprBox.getValue().equals("Adjacency matrix")){
    		int numVertices = Integer.parseInt(createInputDialog("Adjaceny graph matrix ","Amount of star systems that you are going to evaluate")); 
    		graph = new GraphMatrix<>(numVertices, true, true);
    		graph.addVertex("Solar system");
    		Alert alert = new Alert(AlertType.INFORMATION);
    		alert.setContentText("Adjacency matrix graph created");
    		alert.showAndWait();

    	}
    	
    	if(graphReprBox.getValue() != null) {
    		addStarSysBut.setDisable(false);
    		addWormholeBut.setDisable(false);
    		solveBut.setDisable(false);
    		graphReprBox.setDisable(true);
        	createGraphBut.setDisable(true);
    	}
    	updateGUI();
    }
    
    @FXML 
    void solve(ActionEvent event) {
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Solution");
    	alert.setHeaderText("Is it possible to travel in time indefinitely");
    	String res = "";
    	if(!graph.bellmanford(graph.getVertex("Solar system"))) {
    		res = "is possible";
    	}else {
    		res = "not possible";
    	}
    	alert.setContentText(res);
    	alert.showAndWait();
    	
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
    void addStarSystem(ActionEvent event) {
    	String nameVertex = createInputDialog("Add star system", "Star system name");
    	graph.addVertex(nameVertex);
    	updateGUI();
    	
    }

    @FXML
    void addWormhole(ActionEvent event) {
    	String nameVertex1 = createInputDialog("Add a wormhole", "Enter the star system in which the wormhole starts");
    	String nameVertes2 = createInputDialog("Add wormhole", "Enter the star system in wich the wormhole ends");
    	int time = Integer.parseInt(createInputDialog("Add wormhole", "Enter the time it takes to travel through the wormhole"));
    	graph.addEdge(graph.getVertex(nameVertex1), graph.getVertex(nameVertes2), time);
    	updateGUI();
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		graphReprBox.getItems().add("Adjacency list");
		graphReprBox.getItems().add("Adjacency matrix");
		addStarSysBut.setDisable(true);
		addWormholeBut.setDisable(true);
		solveBut.setDisable(true);
		
		
		final Tooltip tooltip = new Tooltip();
		tooltip.setText(
		    "\nHere you can add a star system into the problem\n" +
		    "in other words adds a vertex into the graph\n"  
		);
		Image img = new Image(getClass().getResourceAsStream("/img/starSystem.jpg"), 100, 100, false, false);
		tooltip.setGraphic(new ImageView(img));
		addStarSysBut.setTooltip(tooltip);
		
		final Tooltip wormholeTooltip = new Tooltip();
		wormholeTooltip.setText(
				"\n Here you can add a wormhole that connects two star systems\n" +
				"in other words adds an edge into the grap\n Note: is a one way only edge"
				);
		Image wormholeImg = new Image(getClass().getResourceAsStream("/img/wormhole.jpg"), 100, 100, false, false);
		wormholeTooltip.setGraphic(new ImageView(wormholeImg));
		addWormholeBut.setTooltip(wormholeTooltip);
		
	    graphList.getSelectionModel().selectedItemProperty().addListener(
	            (ObservableValue<? extends String> ov, String old_val, 
	                String new_val) -> {
	                	int endIndex = new_val.indexOf("-");
	                    String vertex = new_val.substring(0, endIndex);
	                    String vertex2 = createInputDialog("Add edge", "Add second vertex");
	                    int w = Integer.parseInt(createInputDialog("Add edge", "Add the weight of the edge"));
	                    graph.addEdge(graph.getVertex(vertex), graph.getVertex(vertex2),w);
	                    updateGUI();
	        });
	}
	
	String createInputDialog(String title, String message) throws IllegalArgumentException{
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
