package com.regsystem.controllers;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.stage.Stage;

public abstract class SceneController implements Initializable {

	@FXML 
	private MenuBar menuBar;
	
	protected void switchScenes(ActionEvent event, String fileName) throws IOException {
		String resourceName = "com/regsystem/" + fileName + ".fxml";
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}	
	
	@FXML
	protected void openAbout(ActionEvent event) throws IOException {
		String resourceName = "com/regsystem/About.fxml";
		Parent root = FXMLLoader.load(getClass().getClassLoader().getResource(resourceName));
		Stage stage = (Stage)menuBar.getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setTitle("Student Registration System | About");
		stage.setScene(scene);
		stage.show();
	}
	
	@FXML
	protected void openGithub(ActionEvent event) throws URISyntaxException, IOException {
		Desktop.getDesktop().browse(new URI("https://github.com/6ar8nas"));
	}
	
	@FXML
	protected void openLinkedin(ActionEvent event) throws URISyntaxException, IOException {
		Desktop.getDesktop().browse(new URI("https://www.linkedin.com/in/sarunas-griskus/"));
	}
	
	@FXML
	protected abstract void getBack(ActionEvent event) throws IOException;
	
	@FXML
	protected abstract void submit(ActionEvent event) throws IOException;
}