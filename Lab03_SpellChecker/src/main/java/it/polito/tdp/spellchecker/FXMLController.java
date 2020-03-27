package it.polito.tdp.spellchecker;

import java.net.URL;
import java.util.ArrayList;

import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class FXMLController {
	private Dictionary dictionary;
	
	private ObservableList<String>list=FXCollections.observableArrayList("English","Italian");

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ChoiceBox<String> ChioceBox;

    @FXML
    private TextArea txtTesto;

    @FXML
    private Button btnSpellCheck;

    @FXML
    private TextArea txtParoleErrate;

    @FXML
    private Label lblTempo;

    @FXML
    private Label lblErrori;

    @FXML
    private Button btnClearText;

    @FXML
    void doClearText(ActionEvent event) {
    	txtParoleErrate.clear();
    	txtTesto.clear();
    	
    }

    @FXML
    void doSpellCheck(ActionEvent event) {
    	long start=System.nanoTime();
    	String result="";
    	List<RichWord>lista=new ArrayList<RichWord>();
    	if(ChioceBox.getValue().equals("English"))
    		this.dictionary.loadDictionary("src/main/resources/English.txt");
    	else
    		this.dictionary.loadDictionary("src/main/resources/Italian.txt");
    	lista.addAll(this.dictionary.spellCheckText(txtTesto.getText()));
    	for(RichWord r: lista) {
    		if(r.isCorretta()==false) {
    			if(result.equals(""))
    				result+= r.getParola();
    			else
    				result+="\n"+r.getParola();
    		}
    	}
    	long start1=System.nanoTime();
    	long start2=start1-start;
    	lblErrori.setText(""+lista.size());
    	lblTempo.setText(""+start2);
    	 txtParoleErrate.appendText(result);
    	
    }

    @FXML
    void initialize() {
        assert ChioceBox != null : "fx:id=\"ChioceBox\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtTesto != null : "fx:id=\"txtTesto\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnSpellCheck != null : "fx:id=\"btnSpellCheck\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtParoleErrate != null : "fx:id=\"txtParoleErrate\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblTempo != null : "fx:id=\"lblTempo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert lblErrori != null : "fx:id=\"lblErrori\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnClearText != null : "fx:id=\"btnClearText\" was not injected: check your FXML file 'Scene.fxml'.";
        ChioceBox.setItems(list);
        ChioceBox.setValue("Italian");
    }
	public void setDictionary(Dictionary dictionary) {
		this.dictionary = dictionary;
	}
}

