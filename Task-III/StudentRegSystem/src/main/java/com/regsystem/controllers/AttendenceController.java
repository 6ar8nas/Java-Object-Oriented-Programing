package com.regsystem.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;

public class AttendenceController extends SceneController {
	
	@Override
	protected void submit(ActionEvent event) throws IOException {
		// 
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void getBack(ActionEvent event) throws IOException {
		switchScenes(event, "Main");
	}
}