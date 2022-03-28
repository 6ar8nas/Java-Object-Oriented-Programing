package com.calculator.application;

import com.calculator.data.DataEntity;
import com.calculator.data.DataSet;
import javafx.application.Application;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Stage stage;
	private StageScene sceneInput, sceneMenu, scenePrint, sceneGraph, sceneDefer, sceneTable;	
	
	private DataSet data;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) {	
		stage = primaryStage;
		stage.setTitle("Mortgage Calculator");
		
		sceneInput = createInputScene();
		sceneMenu = createMenuScene(); 
		
		data = new DataSet();
		
		stage.setScene(sceneInput);
		stage.show();
	}	
	
	private StageScene createInputScene() {
		GridPane grid = new GridPane();
		
		Text title = new Text("Welcome to the Mortgage Calculator");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(title, 0, 0, 3, 1);

		Label loanAmountText = new Label("Loan Amount [Eur]:");
		grid.add(loanAmountText, 0, 1, 3, 1);

		TextField loanAmountUI = new TextField();
		grid.add(loanAmountUI, 0, 2, 3, 1);

		Label loanTermYText = new Label("Loan Term [years]:");
		grid.add(loanTermYText, 0, 3, 3, 1);

		TextField loanTermYUI = new TextField();
		grid.add(loanTermYUI, 0, 4, 3, 1);
		
		Label loanTermMText = new Label("Loan Term [months]:");
		grid.add(loanTermMText, 0, 5, 3, 1);

		TextField loanTermMUI = new TextField();
		grid.add(loanTermMUI, 0, 6, 3, 1);
		
		Label calcMethodText = new Label("Way of calculating");
		grid.add(calcMethodText, 0, 7, 3, 1);

		ChoiceBox <String> calcMethodUI = new ChoiceBox<>();
		calcMethodUI.getItems().addAll("Annuity", "Line");
		grid.add(calcMethodUI, 0, 8, 3, 1);

		Label interestRateText = new Label("Interest Rate [%]:");
		grid.add(interestRateText, 0, 9, 3, 1);

		TextField interestRateUI = new TextField();
		grid.add(interestRateUI, 0, 10, 3, 1);
		
		Button buttonNext = new Button("Continue");
		grid.add(buttonNext, 0, 11, 1, 1);
		
		buttonNext.setOnAction(event -> {
		    data.changeLoanAmount(loanAmountUI.getText());
		    data.changeLoanTerm(loanTermYUI.getText(), loanTermMUI.getText());
		    data.changeCalcMethod(calcMethodUI.getValue());
		    data.changeInterestRate(interestRateUI.getText());
		    data.calculateData();
		    switchScenes(sceneMenu);
		});
		
		StageScene stageScene = new StageScene(grid);

		return stageScene;
	}
	
	private StageScene createMenuScene() {
		GridPane grid = new GridPane();
		
		Text title = new Text("Mortgage Calculator");
		title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 16));
		grid.add(title, 0, 0, 3, 1);
		
		Button buttonInput = new Button("Change the parameters");
		grid.add(buttonInput, 0, 2, 4, 2);
				
		Button buttonTable = new Button("Show the table of the payments");
		grid.add(buttonTable, 0, 4, 4, 2);
		
		Button buttonPrint = new Button("Print contents to file");
		grid.add(buttonPrint, 0, 6, 4, 2);
		
		Button buttonGraph = new Button("Show the graph");
		grid.add(buttonGraph, 0, 8, 4, 2);
		
		Button buttonDefer = new Button("Calculate deferred payments");
		grid.add(buttonDefer, 0, 10, 4, 2);
		
		buttonInput.setOnAction(event -> switchScenes(sceneInput));
		buttonTable.setOnAction(event -> {
			sceneTable = createTableScene();
			switchScenes(sceneTable);
		});
		buttonPrint.setOnAction(event -> {
			scenePrint = createPrintScene();
			switchScenes(scenePrint);
		});
		buttonGraph.setOnAction(event -> {
			sceneGraph = createGraphScene(0, 0);
			switchScenes(sceneGraph);
		});
		buttonDefer.setOnAction(event -> {
			sceneDefer = createDeferScene();
			switchScenes(sceneDefer);
		});
		
		StageScene stageScene = new StageScene(grid);
		
		return stageScene;
	}
	
	private StageScene createPrintScene() {
		GridPane grid = new GridPane();
		
		Label fileNameText = new Label("Enter the file name [including extension]:");
		grid.add(fileNameText, 0, 0, 8, 1);

		TextField fileNameUI = new TextField();
		grid.add(fileNameUI, 0, 1, 6, 1);
		
		Button buttonNext = new Button("Continue");
		grid.add(buttonNext, 0, 2, 3, 1);
				
		Button buttonBack = new Button("Back to menu");
		grid.add(buttonBack, 3, 2, 4, 1);
		
		buttonNext.setOnAction(event -> {
			String fileName = (fileNameUI.getText() == "") ? "output.txt" : fileNameUI.getText();
			
			data.printToFile(fileName);
			
		    switchScenes(sceneMenu);
		});
		
		buttonBack.setOnAction(event -> switchScenes(sceneMenu));
		
		StageScene stageScene = new StageScene(grid);

		return stageScene;		
	}
	
	private StageScene createGraphScene(int filterFloor, int filterCeil) {
		GridPane grid = new GridPane();
		
		double floor = (filterFloor == 0) ? 0 : (double)filterFloor - 1;	
		double ceil = (filterCeil == 0) ? (double)data.howManyTerms() : (double)filterCeil;	
		NumberAxis xAxis = new NumberAxis(floor, ceil + 1, data.howManyTerms()/10);
		NumberAxis yAxis = new NumberAxis();
		xAxis.setLabel("Months");

		LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);
		lineChart.setTitle("Payments visualisation");
		XYChart.Series<Number, Number> series = new XYChart.Series<Number, Number>();
		series.setName("Monthly payment, Eur");
		for(DataEntity dataEntity : data.dataArray)
		{
			series.getData().add(new XYChart.Data<Number, Number>(dataEntity.getMonth(), dataEntity.getPaymentValue()));
		}
		lineChart.getData().add(series);

		grid.add(lineChart, 0, 0, 4, 2);

		Button buttonBack = new Button("Back to menu");
		grid.add(buttonBack, 3, 3, 4, 2);
		
		buttonBack.setOnAction(event -> switchScenes(sceneMenu));		
		
		Label filter1Text = new Label("Filter from: [0 to restore defaults]");
		grid.add(filter1Text, 6, 2, 3, 1);

		TextField filter1UI = new TextField();
		grid.add(filter1UI, 6, 3, 3, 1);

		Label filter2Text = new Label("Filter to: [0 to restore defaults]");
		grid.add(filter2Text, 6, 4, 3, 1);

		TextField filter2UI = new TextField();
		grid.add(filter2UI, 6, 5, 3, 1);
	
		Button buttonUpdate = new Button("Update");
		grid.add(buttonUpdate, 6, 6, 3, 1);
		
		buttonUpdate.setOnAction(event -> {
			int x = Integer.parseInt("0" + filter1UI.getText());
			int y = Integer.parseInt("0" + filter2UI.getText());
			sceneGraph = createGraphScene(x, y);
		    switchScenes(sceneGraph);
		});
		
		StageScene stageScene = new StageScene(grid);		
		return stageScene;
	}
	
	private StageScene createDeferScene() {
		GridPane grid = new GridPane();
		
		Label paymentsStartText = new Label("Defer payments starting which month: ");
		grid.add(paymentsStartText, 0, 0, 2, 1);

		TextField paymentsStartUI = new TextField();
		grid.add(paymentsStartUI, 0, 1, 2, 1);

		Label lengthText = new Label("Enter the delay length: ");
		grid.add(lengthText, 0, 2, 2, 1);

		TextField lengthUI = new TextField();
		grid.add(lengthUI, 0, 3, 2, 1);
		
		Label deferRateText = new Label("Enter the interest rate for the period: ");
		grid.add(deferRateText, 0, 4, 2, 1);

		TextField deferRateUI = new TextField();
		grid.add(deferRateUI, 0, 5, 2, 1);
	
		Button buttonDefer = new Button("Add");
		grid.add(buttonDefer, 0, 6, 2, 1);
		
		buttonDefer.setOnAction(event -> {
			int deferLength = Integer.parseInt("0" + lengthUI.getText());
			int deferStart = Integer.parseInt("0" + paymentsStartUI.getText());
			data.changeDeferRate(deferRateUI.getText());
			data.calculateData(deferStart, deferLength);
			switchScenes(sceneMenu);
		});

		Button buttonBack = new Button("Back to menu");
		grid.add(buttonBack, 8, 8, 4, 2);
		
		buttonBack.setOnAction(event -> switchScenes(sceneMenu));
		
		StageScene stageScene = new StageScene(grid);
		
		return stageScene;
	}
	
	private StageScene createTableScene() {
		GridPane grid = new GridPane();
		
		TableView<DataEntity> tableView = new TableView<>();
		
		TableColumn<DataEntity, int[]> monthNameColumn = new TableColumn<>("Month");
		TableColumn<DataEntity, double[]> principalNameColumn = new TableColumn<>("Principal");
		TableColumn<DataEntity, int[]> interestNameColumn = new TableColumn<>("Interest");
		TableColumn<DataEntity, double[]> loanAmountNameColumn = new TableColumn<>("Loan amount");

		monthNameColumn.setCellValueFactory(new PropertyValueFactory<>("month"));
		principalNameColumn.setCellValueFactory(new PropertyValueFactory<>("principal"));
		interestNameColumn.setCellValueFactory(new PropertyValueFactory<>("interest"));
		loanAmountNameColumn.setCellValueFactory(new PropertyValueFactory<>("loanAmount"));
		
		tableView.getColumns().add(monthNameColumn);
	    tableView.getColumns().add(principalNameColumn);
		tableView.getColumns().add(interestNameColumn);
	    tableView.getColumns().add(loanAmountNameColumn);
		
	    for(DataEntity dataEntity : data.dataArray)
		{
	    	tableView.getItems().add(dataEntity);
		}
	    grid.add(tableView, 0, 0);
	    
		Button buttonBack = new Button("Back to menu");
		grid.add(buttonBack, 5, 5, 4, 2);

		buttonBack.setOnAction(event -> switchScenes(sceneMenu));
		
		StageScene stageScene = new StageScene(grid);
		
		return stageScene;
	}
	
	public void switchScenes(StageScene stageScene) {
		stage.setScene(stageScene);
	}

}