package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.regsystem.io.Importer;
import com.regsystem.io.ImporterCSV;
import com.regsystem.io.ImporterXLSX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FillFileController extends SceneController {
	
	@FXML
	private TextField fileNameTextField;
	
	@FXML
	private ChoiceBox<String> fileTypeChoiceBox;
	
	@Override
	protected void submit(ActionEvent event) throws IOException {
		String fileType = fileTypeChoiceBox.getValue();
		String fileName = fileNameTextField.getText();		
		Importer imp = null;
		
		switch(fileType) {
		case ".csv":
		{
			imp = new ImporterCSV(fileName + fileType);
			break;
		}
		case ".xlsx":
		{
			imp = new ImporterXLSX(fileName + fileType);
			break;
		}
		}
		
		imp.fillData();
		
		fileTypeChoiceBox.setValue(".csv");
		fileNameTextField.clear();
	}
		
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fileTypeChoiceBox.getItems().addAll(".csv", ".xlsx");
		fileTypeChoiceBox.setValue(".csv");
		
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
		
	}

}