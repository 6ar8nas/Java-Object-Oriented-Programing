package com.regsystem.controllers;
import com.regsystem.data.DataSet;
import com.regsystem.data.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class DataController extends SceneController{
	@FXML
	private TableView<Student> table;
	@FXML
	private TableColumn<Student, String> nameCol, surnameCol, programmeCol; 
	@FXML
	private TableColumn<Student, Integer> idCol, yearCol, groupCol;
	@FXML
	private ChoiceBox<String> fieldChoiceBox;
	@FXML
	private TextField newValueTextField, idTextField;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		programmeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("studyProgramme"));
		yearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("year"));
		groupCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
		
		ObservableList<Student> dataList = FXCollections.observableArrayList(DataSet.getInstance().getList());
		table.setItems(dataList);
		
		fieldChoiceBox.getItems().addAll("Name", "Surname", "Programme", "Year", "Group");
	}
	
	@Override
	protected void submit(ActionEvent event) throws IOException {
		int idToChange = Integer.parseInt("0" + idTextField.getText());
		String fieldToChange = fieldChoiceBox.getValue();
		Object newValue;
		
		if(DataSet.getInstance().findInstanceByID(idToChange) != null) {
			switch(fieldToChange) {
			case "Name":
			{
				newValue = newValueTextField.getText();
				DataSet.getInstance().findInstanceByID(idToChange).setName((String)newValue);
				break;
			}
			case "Surname":
			{
				newValue = newValueTextField.getText();
				DataSet.getInstance().findInstanceByID(idToChange).setSurname((String)newValue);
				break;
			}
			case "Programme":
			{
				newValue = newValueTextField.getText();
				DataSet.getInstance().findInstanceByID(idToChange).setStudyProgramme((String)newValue);
				break;
			}
			case "Year":
			{
				newValue = Integer.parseInt("0" + newValueTextField.getText());
				DataSet.getInstance().findInstanceByID(idToChange).setYear((Integer)newValue);
				break;
			}
			case "Group":
			{
				newValue = Integer.parseInt("0" + newValueTextField.getText());
				DataSet.getInstance().findInstanceByID(idToChange).setGroup((Integer)newValue);
				break;
			}
			}
			SceneController.showAlert("Success","The selected value on student (ID: " + idToChange + ") was successfully edited.");
		}
		else {
			SceneController.showAlert("Information","There is no such student (ID: " + idToChange + ").");
		}
		newValueTextField.clear();
		idTextField.clear();
		fieldChoiceBox.setValue("");
		table.refresh();
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
	}
	
	private void updateTable() {
		ObservableList<Student> dataList = FXCollections.observableArrayList(DataSet.getInstance().getList());
		table.setItems(dataList);
	}
	
	@FXML
	protected void deleteStudentEntry(ActionEvent event) throws IOException {
		int idToChange = Integer.parseInt("0" + idTextField.getText());
		if(DataSet.getInstance().findInstanceByID(idToChange) != null) {
			DataSet.getInstance().removeStudent(idToChange);
			updateTable();
			SceneController.showAlert("Success","The student (ID: " + idToChange + ") was successfully deleted from the list.");
		}
		else {
			SceneController.showAlert("Information","There is no such student (ID: " + idToChange + ").");
		}
		idTextField.clear();

	}
}