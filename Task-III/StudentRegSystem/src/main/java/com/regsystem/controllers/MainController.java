package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.regsystem.data.DataSet;
import com.regsystem.io.Exporter;
import com.regsystem.io.ExporterCSV;
import com.regsystem.io.ExporterPDF;
import com.regsystem.io.ExporterXLSX;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class MainController extends SceneController {
	@FXML
	private TextField fileNameTextField;
	
	@FXML
	private ChoiceBox<String> fileTypeChoiceBox;
	
	@FXML
	private void openEnterManually(ActionEvent event) throws IOException {
		switchScenes(event, "EnterManually");
	}
	
	@FXML
	private void openFillFile(ActionEvent event) throws IOException {
		switchScenes(event, "FillFile");
	}
	
	@FXML
	private void openAttendence(ActionEvent event) throws IOException {
		switchScenes(event, "Attendence");
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
		Exporter exp = null;
		switch(fileType) {
		case ".csv":
		{
			exp = new ExporterCSV(fileName + " " + fileType);
			break;
		}
		case ".xlsx":
		{
			exp = new ExporterXLSX(fileName + " " + fileType);
			break;
		}
		case ".pdf":
		{
			exp = new ExporterPDF(fileName + " " + fileType);
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