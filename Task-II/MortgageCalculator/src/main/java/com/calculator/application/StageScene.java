package com.calculator.application;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;

public class StageScene extends Scene {
	public GridPane grid;
	
	public StageScene(GridPane grid) {
		super(grid, 1000, 600);
		this.grid = grid;
		this.grid.setAlignment(Pos.CENTER);
		this.grid.setHgap(10);
		this.grid.setVgap(10);
	}
}