package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.regsystem.data.DataSet;
import com.regsystem.data.Student;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

public class EnterManuallyController extends SceneController implements Initializable {

	@FXML
	private Spinner<Integer> groupSpinner, yearSpinner;
	
	@FXML
	private ChoiceBox<String> studyChoiceBox;
	
	@FXML
	private TextField nameTextField, surnameTextField;
	
	@Override
	protected void submit(ActionEvent event) throws IOException {
		String name = nameTextField.getText();
		nameTextField.clear();
		String surname = surnameTextField.getText();
		surnameTextField.clear();
		String studies = studyChoiceBox.getValue();
		studyChoiceBox.setValue("");
		int group = groupSpinner.getValue();
		groupSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1));
		int year = yearSpinner.getValue();
		yearSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1));
		
		Student s = new Student(name, surname, studies, year, group);
		DataSet.getInstance().addStudent(s);
		SceneController.showAlert("Success","A student named "+ name + " " +  surname + " was added.");
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        SpinnerValueFactory<Integer> groupSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5, 1);
        groupSpinner.setValueFactory(groupSpinnerValueFactory);
        SpinnerValueFactory<Integer> yearSpinnerValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 4, 1);
        yearSpinner.setValueFactory(yearSpinnerValueFactory);
        studyChoiceBox.getItems().addAll(
        		"Software Engineering", "Cyber Security", "Information Systems", "Data Science", "Mathematics");
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
	}
}