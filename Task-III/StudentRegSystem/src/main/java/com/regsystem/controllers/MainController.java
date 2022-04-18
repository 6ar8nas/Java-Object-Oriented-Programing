package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.regsystem.data.DataSet;
import com.regsystem.io.Exporter;
import com.regsystem.io.ExporterCSV;
import com.regsystem.io.ExporterPDF;
import com.regsystem.io.ExporterXLSX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MainController extends SceneController {
	@FXML
	private TextField fileNameTextField;
	
	@FXML
	private ChoiceBox<String> fileTypeChoiceBox;
	
	@FXML
	private CheckBox appendDataCheckBox;
	
	@FXML
	private void openEnterManually(ActionEvent event) throws IOException {
		switchScenes(event, "EnterManually");
	}
	
	@FXML
	private void resetSystem(ActionEvent event) throws IOException {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setContentText("Are you sure you want to delete all the data in the system?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			DataSet.getInstance().deleteList();
			SceneController.showAlert("Success","The system has been successfully reset.");
		}
	}
	
	@FXML
	private void openFillFile(ActionEvent event) throws IOException {
		switchScenes(event, "FillFile");
	}
	
	@FXML
	private void openAttendance(ActionEvent event) throws IOException {
		switchScenes(event, "Attendance");
	}
	
	@FXML
	private void openData(ActionEvent event) throws IOException {
		switchScenes(event, "Data");
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
	}

	@Override
	protected void submit(ActionEvent event) throws IOException {
		String fileType = fileTypeChoiceBox.getValue();
		String fileName = fileNameTextField.getText();
		if(fileName == "") 
			fileName = "output";
		Boolean append =  appendDataCheckBox.isSelected();
		Exporter exp = null;
		switch(fileType) {
		case ".csv":
		{
			exp = new ExporterCSV(fileName + fileType, append);
			break;
		}
		case ".xlsx":
		{
			exp = new ExporterXLSX(fileName + fileType);
			break;
		}
		case ".pdf":
		{
			if(DataSet.getInstance().isDataFiltered()) {
				exp = new ExporterPDF(fileName + fileType, DataSet.getInstance().getFilter1(), DataSet.getInstance().getFilter2());
			} else {
				exp = new ExporterPDF(fileName + fileType);
			}
			break;
		}
		}
		exp.export(DataSet.getInstance().getList());
		fileTypeChoiceBox.setValue(".csv");
		fileNameTextField.clear();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		fileTypeChoiceBox.getItems().addAll(".csv", ".xlsx", ".pdf");
		fileTypeChoiceBox.setValue(".csv");

	}
}