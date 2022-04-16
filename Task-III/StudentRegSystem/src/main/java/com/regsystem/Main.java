// Sarunas Griskus, 2110556
package com.regsystem;
	
import java.io.IOException;

import com.regsystem.data.Student;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	
	public Student[] studentsArray;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage stage) throws IOException {
		Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
		Scene scene = new Scene(root);
		stage.setTitle("Student Registration System");
		stage.setScene(scene);
		stage.show();
	}
}
