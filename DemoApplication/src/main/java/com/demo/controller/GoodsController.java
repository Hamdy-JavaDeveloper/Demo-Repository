package com.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.demo.bean.Item;
import com.demo.cfg.StageManager;
import com.demo.service.ItemService;
import com.demo.util.AlertUtil;
import com.demo.util.ExceptionUtil;
import com.demo.view.FxmlView;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
@Controller
public class GoodsController implements Initializable{

	@FXML    private TableView<Item> tbItems;
	public TableView<Item> getItemTableView(){
		return tbItems;
	
	}
	@FXML private BorderPane goodsBorderPane;
	@FXML	 private TableColumn<Item,Double> colItemPrice;
    @FXML    private TableColumn<Item, Double> colItemDiscount;
    
    @FXML    private ComboBox<String> combSearchItem;
    @FXML    private TableColumn<Item, String> colItemUnit;
    @FXML    private TableColumn<Item, Double> colItemTotalQty;
    @FXML    private TableColumn<Item, String> colItemCode;
    @FXML    private TableColumn<Item,Long> colItemId;
    @FXML    private TableColumn<Item,String> colItemName;
    
    @FXML    private TableColumn<Item,String> colItemBarcode;
    @FXML    private TableColumn<Item, Double> colItemPriceMin;
    @FXML    private TableColumn<Item,LocalDate> colItemLastDatePurchese;
    @FXML    private ComboBox<String> combCategroyItem;
    @FXML    private TableColumn<Item, String> colItemCategroy;
    @FXML    private CheckBox chkShowItemsLessQty;
    @FXML    private CheckBox chkShowItemsNotUsed;
	
    @FXML
    private Button btnNewItem;
    @FXML
    private Button btnUpdateItem;
    @FXML
    private Button btnDeleteItem;
    @Autowired
    private ItemService itemService;
    @Lazy
    @Autowired private StageManager stageManager;
    @Autowired private ApplicationContext ac;
    @Autowired private AddNewItemController addNewItemController;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		colItemId.setCellValueFactory(new PropertyValueFactory<>("itemId"));
		colItemId.setMinWidth(30);
		colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
		colItemName.setMinWidth(200);
		colItemTotalQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
		colItemTotalQty.setMinWidth(100);
		colItemUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
		colItemUnit.setMinWidth(100);
		colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price1"));
		colItemPrice.setMinWidth(70);
		colItemPriceMin.setCellValueFactory(new PropertyValueFactory<>("priceMin"));
		colItemPriceMin.setMinWidth(70);
		colItemLastDatePurchese.setCellValueFactory(new PropertyValueFactory<>("lastPurchased"));
		colItemLastDatePurchese.setMinWidth(150);
		colItemDiscount.setCellValueFactory(new PropertyValueFactory<>("discountPer"));
		colItemDiscount.setMinWidth(70);
		colItemBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
		colItemBarcode.setMinWidth(100);
		colItemCode.setCellValueFactory(new PropertyValueFactory<>("code1"));
		colItemCode.setMinWidth(100);
		colItemCategroy.setCellValueFactory(new PropertyValueFactory<>("categroy1") );
		colItemCategroy.setMinWidth(100);
		ObservableList<Item> itemsList=FXCollections.observableArrayList(itemService.findAll());
		
		combCategroyItem.setItems(FXCollections.observableArrayList(itemService.findDistinctByCategroy1()));
		
		combCategroyItem.setOnAction(e ->{
			itemsList.clear();
			itemsList.setAll(itemService.findByText(combSearchItem.getValue(),combCategroyItem.getValue()));
			tbItems.setItems(itemsList);
				
			
		});

		
		tbItems.setItems(itemsList);
		
		
		combSearchItem.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				itemsList.clear();
				itemsList.setAll(itemService.findByText(combSearchItem.getValue(),combCategroyItem.getValue()));
				tbItems.setItems(itemsList);
				
				
				
			}
		
			
		});
		
		
		combSearchItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				itemsList.clear();
				itemsList.setAll(itemService.findByText(combSearchItem.getValue(),combCategroyItem.getValue()));
				tbItems.setItems(itemsList);
				
				System.out.println("ActionEvent");
				
			}
			
		});
		tbItems.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				if (key.getCode()==KeyCode.ENTER) {
					stageManager.switchScene2(ac,FxmlView.ADDNEWITEM).show();
					//s.show();
					
					String itemId=String.valueOf(tbItems.getSelectionModel().getSelectedItem().getItemId());
					addNewItemController.setIndex(tbItems.getSelectionModel().getSelectedIndex());
					System.out.println("indexxxxxxxxxxxxxxxx="+tbItems.getSelectionModel().getSelectedIndex());
					addNewItemController.setTxtItemId(itemId);
					addNewItemController.showItemDetials(itemId);
									
				}
				if (key.getCode()==KeyCode.F5) {
					itemsList.clear();
					itemsList.setAll(itemService.findAll());
					tbItems.setItems(itemsList);
				}
				
				if (key.getCode()==KeyCode.DELETE) {
					String itemId=String.valueOf(tbItems.getSelectionModel().getSelectedItem().getItemId());
					Alert alert=AlertUtil.getAlert(AlertType.WARNING,"حذف صنف ", "هل متاكد من هذه العملية؟","سيتم حذف الصنف"+tbItems.getSelectionModel().getSelectedItem().getItemName());
					Optional<ButtonType> result=alert.showAndWait();
					if(result.get()==ButtonType.OK){
						System.out.println(tbItems.getSelectionModel().getSelectedItem().getItemName()+"is Deleted...o_O");
						itemService.delete(tbItems.getSelectionModel().getSelectedItem());
					}
					//Refresh
					itemsList.clear();
					itemsList.setAll(itemService.findAll());
					tbItems.setItems(itemsList);
					}
			}
		});
		
		
		goodsBorderPane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent key) {
				if(key.getCode()==KeyCode.F5) {
					itemsList.clear();
					itemsList.setAll(itemService.findAll());
					tbItems.setItems(itemsList);
				
			}else if(key.getCode()==KeyCode.F11) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						tbItems.requestFocus();
//						tbItems.getSelectionModel().select(0);
//						tbItems.getFocusModel().focus(0);
					}	
				});
		
			}
		}
	});
		
	}
	Stage stage2;
	@FXML
	private void handleGoodsAction(ActionEvent e) throws ExceptionUtil{
		if (e.getSource()==btnNewItem) {
			stage2=stageManager.switchScene2(ac,FxmlView.ADDNEWITEM);
			stage2.show();
			
			 stage2.setOnCloseRequest(event->{
				 try {
					 addNewItemController.cancle(null);
			  }catch(IOException ioe) {	  }			  
			  });
			 
				/*
				 * stage2.setOnCloseRequest(new EventHandler<WindowEvent>() {
				 * 
				 * @Override public void handle(WindowEvent arg0) { try { System.out.println(
				 * "MMMMMMMMMMMMMMMMMMXXXXXXXXXXXXXXXXZZZZZZZZZZZZZZZ77777777777777777733333333333333333AAAAAAAAAAAAAAAAAAZZZZZZZZ"
				 * ); addNewItemController.cancle(null); } catch(IOException ioe) {
				 * 
				 * }
				 * 
				 * }});
				 */
			
		}else if (e.getSource()==btnUpdateItem){
			
			
			stage2=stageManager.switchScene2(ac,FxmlView.ADDNEWITEM);
			stage2.show();
			
			 stage2.setOnCloseRequest(event->{
				 try {
					 addNewItemController.cancle(null);
			  }catch(IOException ioe) {	  }			  
			  });
			 
			String itemId=String.valueOf(tbItems.getSelectionModel().getSelectedItem().getItemId());
			addNewItemController.setTxtItemId(itemId);
			addNewItemController.showItemDetials(itemId);
			
			System.out.println("btnUpdateItem");
		}else if(e.getSource()==btnDeleteItem) {
			
			
		}
			
		
	}
		
	
	

}
