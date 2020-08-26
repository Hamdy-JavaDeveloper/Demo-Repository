package com.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.demo.cfg.StageManager;
import com.demo.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

@Controller
public class MainWindowController implements Initializable{
	 @FXML
	    private MenuItem menuItemAddItem;

	    @FXML
	    private MenuItem menuItemGoods;

	    @FXML
	    private AnchorPane viewPane;
	    @FXML
	    private MenuItem menuItemQuit;
	   @Lazy
	    @Autowired
		 private StageManager stageManager;
	   @Autowired
		private ApplicationContext applicationContext;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println("MainWindowController......................................");
		
	}
	@FXML Button btnShow;
	@FXML
	private void handleMenuActions(ActionEvent event) throws IOException
	{
		if (event.getSource()==menuItemGoods){
			


			viewPane.getChildren().setAll((BorderPane)stageManager.loadViewNodeHierarchy(FxmlView.GOODS.getFxmlFile()));

			
			

			
			
			
		}
		else if (event.getSource()==menuItemAddItem){
			

			viewPane.getChildren().setAll((VBox)stageManager.loadViewNodeHierarchy(FxmlView.ADDNEWITEM.getFxmlFile()));
			
		
	
		}

}

}
