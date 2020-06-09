/**
 * Sample Skeleton for 'Food.fxml' Controller Class
 */

package it.polito.tdp.food;

import java.net.URL;

import java.util.ResourceBundle;
import it.polito.tdp.food.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FoodController {
	
	private Model model;

    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtCalorie"
    private TextField txtCalorie; // Value injected by FXMLLoader

    @FXML // fx:id="txtPassi"
    private TextField txtPassi; // Value injected by FXMLLoader

    @FXML // fx:id="btnAnalisi"
    private Button btnAnalisi; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorrelate"
    private Button btnCorrelate; // Value injected by FXMLLoader

    @FXML // fx:id="btnCammino"
    private Button btnCammino; // Value injected by FXMLLoader

    @FXML // fx:id="boxPorzioni"
    private ComboBox<String> boxPorzioni; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doCammino(ActionEvent event) {
    	txtResult.clear();
    	int passi;
    	
    	try {
    		
    		passi = Integer.parseInt(this.txtPassi.getText());
    	
    	}catch(NumberFormatException e){
    		txtResult.appendText("Inserire un valore numerico nel form \"Passi\"");
    		return;
    	}
    	
    	String porzionePartenza;
    	try {
    		
    		porzionePartenza = this.boxPorzioni.getValue();
    	
    	}catch(Exception e){
    		txtResult.appendText("Scegliere un valore dal menù a tendina!");
    		return;
    	}

		try {
			
			this.txtResult.appendText(this.model.cercaPercorsoMax(porzionePartenza, passi));

		} catch (Exception e) {
			txtResult.appendText("Errore, nessun percorso trovato!");
			return;
		}
    	
    	
    	
    }

    @FXML
    void doCorrelate(ActionEvent event) {
    	txtResult.clear();
    	try {
    		
    		txtResult.appendText(this.model.getAdiacenti(this.boxPorzioni.getValue()));
    		
    	}catch(Exception e) {
    		txtResult.appendText("Inserire una opzione dal menù a tendina!");
    		return;
    	}
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	double calories;
    	
		try {

			calories = Double.parseDouble(this.txtCalorie.getText());
			
			if (calories > 0) {
				this.model.creaGrafo(calories);
				if (this.model.getNumVertex() > 0) {
					txtResult.appendText("Grafo creato!\nNumero vertici: " + this.model.getNumVertex()
							+ "\nNumero archi: " + this.model.getNumEdges());
					
					this.boxPorzioni.getItems().clear();
					this.boxPorzioni.getItems().addAll(this.model.getVertici());
				} else {
					txtResult.appendText("Attenzione! Impossibile creare un grafo con le calorie inserite!");
				}
			} else {
				txtResult.appendText("Attenzione! Inserire un valore numerico nel form \"Calorie\" maggiore di zero!");
			}
    	}catch(Exception e){
    		
    		txtResult.appendText("Attenzione! Inserire un valore numerico nel form \"Calorie\"");
    		return;
    	}
    	
    	
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtCalorie != null : "fx:id=\"txtCalorie\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtPassi != null : "fx:id=\"txtPassi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnAnalisi != null : "fx:id=\"btnAnalisi\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCorrelate != null : "fx:id=\"btnCorrelate\" was not injected: check your FXML file 'Food.fxml'.";
        assert btnCammino != null : "fx:id=\"btnCammino\" was not injected: check your FXML file 'Food.fxml'.";
        assert boxPorzioni != null : "fx:id=\"boxPorzioni\" was not injected: check your FXML file 'Food.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Food.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
}
