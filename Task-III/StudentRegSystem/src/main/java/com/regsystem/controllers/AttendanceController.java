package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

import com.regsystem.data.DataSet;
import com.regsystem.data.DateEntry;
import com.regsystem.data.Student;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AttendanceController extends SceneController {

	@FXML
	private DatePicker newDate, filter1Date, filter2Date;
	@FXML
	private TableView<Student> table;
	@FXML
	private TableColumn<Student, String> nameCol, surnameCol, programmeCol; 
	@FXML
	private TableColumn<Student, Integer> idCol, yearCol, groupCol;
	@FXML
	private RadioButton rbStudent, rbGroup;
	@FXML
	private ChoiceBox<LocalDate> dateChoiceBox;
	@FXML
	private ChoiceBox<Boolean> valueChoiceBox;
	@FXML
	private TextField idTextField, groupIdTextField;


	@FXML
	private void addDate(ActionEvent event) throws IOException {
		LocalDate date = newDate.getValue();
		if(date == null)
			return;
		
		for(Student student : DataSet.getInstance().getList()) {
			student.setAttendanceValues(date, false);
		}
		table.refresh();
		ObservableList<Student> dataList = FXCollections.observableArrayList(DataSet.getInstance().getList());
		table.setItems(dataList);
		SceneController.showAlert("Information","The date has been successfully added.");
		dateChoiceBox.getItems().addAll(date);
		switchScenes(event, "Attendance");
	}
	
	@Override
	protected void submit(ActionEvent event) throws IOException {
		try {
			if(rbStudent.isSelected()) {
				int id = Integer.parseInt("0"+idTextField.getText());
				LocalDate date = dateChoiceBox.getValue();
				Boolean newVal = valueChoiceBox.getValue();
				DataSet.getInstance().findInstanceByID(id).findDate(date).setAttendance(newVal);
			}
			if(rbGroup.isSelected()) {
				int id = Integer.parseInt("0"+groupIdTextField.getText());
				LocalDate date = dateChoiceBox.getValue();
				Boolean newVal = valueChoiceBox.getValue();
				for(Student st : DataSet.getInstance().getList()) {
					if(st.getGroup() == id) {
						st.findDate(date).setAttendance(newVal);
					}
				}
			}
			SceneController.showAlert("Information","The value has been changed.");
		} catch(Exception e) {
			SceneController.showAlert("Information","There was an error. Make sure you have entered all the necessary data and try again.");
		}
		
		table.refresh();
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		ToggleGroup selection = new ToggleGroup();

		rbStudent.setToggleGroup(selection);
		rbGroup.setToggleGroup(selection);
		
		ArrayList<TableColumn<Student, Boolean>> dateColumns = new ArrayList<TableColumn<Student, Boolean>>();
        if(DataSet.getInstance().getList().size() != 0) {
            for(DateEntry dt : DataSet.getInstance().getList().get(0).getAttendanceValues()) {
            	if(DataSet.getInstance().isDataFiltered()) {
            		if(dt.isFiltered(DataSet.getInstance().getFilter1(), DataSet.getInstance().getFilter2()))
            			continue;
            	}
            	TableColumn<Student, Boolean> newEntry = new TableColumn<Student, Boolean>(dt.getDate().toString());
        		dateColumns.add(newEntry);
        	    table.getColumns().add(newEntry);
        	    newEntry.setCellValueFactory(createArrayValueFactory(Student::getAttendanceValues, dt.getDate()));
        	    dateChoiceBox.getItems().add(dt.getDate());
            }
        }
        
		idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
		surnameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("surname"));
		programmeCol.setCellValueFactory(new PropertyValueFactory<Student, String>("studyProgramme"));
		yearCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("year"));
		groupCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("group"));
		
		valueChoiceBox.getItems().addAll(true, false);
		
		ObservableList<Student> dataList = FXCollections.observableArrayList(DataSet.getInstance().getList());
		table.setItems(dataList);
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
	}
	
	@FXML
	private void createFilter(ActionEvent event) throws IOException {
		
		LocalDate date1 = filter1Date.getValue();
		LocalDate date2 = filter2Date.getValue();
		
		SceneController.showAlert("Information","The filter has been applied.");
		DataSet.getInstance().createFilter(date1, date2);
		switchScenes(event, "Attendance");
	}
	
	@FXML
	private void removeFilter(ActionEvent event) throws IOException {
		DataSet.getInstance().removeFilter();
		SceneController.showAlert("Information","The filter has been removed.");
		switchScenes(event, "Attendance");
	}
	
	static <S, T> Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<Boolean>> createArrayValueFactory(Function<Student, ArrayList<DateEntry>> arrayExtractor, LocalDate date) {
	    return cd -> {
	    	ArrayList<DateEntry> array = arrayExtractor.apply((Student) cd.getValue());
	    	for(DateEntry de : array) {
	    		if(de.getDate() == date) {
	    	        return new SimpleObjectProperty<>(de.getAttendance());
	    		}
	    	}
	    	return null;
	    };
	}
}