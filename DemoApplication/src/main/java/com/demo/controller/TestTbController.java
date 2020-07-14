/*	package com.demo.controller;
	
	
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.bean.Item;
import com.demo.bean.Store;
import com.demo.bean.StoreItem;
import com.demo.service.ItemService;
import com.demo.service.StoreItemService;
import com.demo.service.StoreService;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
	
	
	
	
	@Controller
	public class TestTbController implements Initializable{
	
		
		
		
		@FXML    private TableView<StoreItem> tbStoreItem;
	
	    @FXML
	    private TableColumn<StoreItem, Long> colStoreId;
	
	    @FXML
	    private TableColumn<StoreItem, String> colStoreName;
	
	    @FXML
	    private TableColumn<StoreItem, Double> colItemQty;
	
	    @FXML
	    private TableColumn<StoreItem,Double> colItemAvgCost;
	    @FXML private Button btnAdd;
	    @FXML
	    private TextField txxItemId;
		
	    @Autowired
	    Item item;
	    @Autowired
	      Store store;
	    @Autowired
	    ItemService itemService;
	    @Autowired
	    StoreService storeService;
	    @Autowired
	    StoreItem storeItem;
	    @Autowired
	    StoreItemService storeItemService;
	    ObservableList<StoreItem> tbViewData=FXCollections.observableArrayList();
	    
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
			Platform.runLater(new Runnable() {
			@Override
			public void run() {
			tbvItemFirstQty.requestFocus();
			tbvItemFirstQty.getSelectionModel().select(0);
			tbvItemFirstQty.getFocusModel().focus(0);
				
			}
		});
	
		
	    	
		colStoreId.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StoreItem,Long>, ObservableValue<Long>>() {
		
			
			@Override
			public ObservableValue<Long> call(CellDataFeatures<StoreItem, Long> param) {
				
				return new ObservableValue<Long>() {
	
					@Override
					public void addListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void addListener(ChangeListener<? super Long> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(ChangeListener<? super Long> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public Long getValue() {
						
						return param.getValue().getStore().getId();
					}
				};
			}
		});
		colStoreName.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StoreItem,String>, ObservableValue<String>>() {
	
	
			@Override
			public ObservableValue<String> call(CellDataFeatures<StoreItem, String> param) {
				
				return new ObservableValue<String>() {
	
					@Override
					public void addListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void addListener(ChangeListener<? super String> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(ChangeListener<? super String> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public String getValue() {
						
						return param.getValue().getStore().getStoreName();
						
					}
				};
			}
		
		
		
		});
		
		colItemQty.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StoreItem,Double>, ObservableValue<Double>>() {
	
	
			@Override
			public ObservableValue<Double> call(CellDataFeatures<StoreItem, Double> param) {
				// TODO Auto-generated method stub
				return new ObservableValue<Double>() {
	
					@Override
					public void addListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void addListener(ChangeListener<? super Double> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(ChangeListener<? super Double> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public Double getValue() {
	
						return param.getValue().getQty();
					}
					
				};
			}
		
		
		
		});
		colItemQty.setCellFactory(new TextFieldTableCell().forTableColumn(new DoubleStringConverter()));
		colItemQty.setOnEditCommit(e -> colQtyOnEdit(e));
			
		colItemAvgCost.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<StoreItem,Double>, ObservableValue<Double>>() {
			
	
			@Override
			public ObservableValue<Double> call(CellDataFeatures<StoreItem, Double> param) {
				
				return new ObservableValue<Double>() {
	
					@Override
					public void addListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(InvalidationListener listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void addListener(ChangeListener<? super Double> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public void removeListener(ChangeListener<? super Double> listener) {
						// TODO Auto-generated method stub
						
					}
	
					@Override
					public Double getValue() {
	
						return param.getValue().getItem().getAvgCost();
					}
					
				};
			}
		});
		colItemAvgCost.setCellFactory(new TextFieldTableCell().forTableColumn(new DoubleStringConverter()));
		colItemAvgCost.setOnEditCommit(e -> colCostAvgOnEdit(e));
		    
		
		
		
		List<Store> stores=storeService.findAll();
		
		Iterator<Store> iterator=stores.iterator();
		
		while(iterator.hasNext())
		{
			StoreItem si=new StoreItem();
		
			si.setStore((Store)iterator.next());
			si.setItem(item);
			tbViewData.add(si);
			System.out.println("storeName:="+si.getStore().getStoreName());
		}
		tbStoreItem.setItems(tbViewData);
		
		
		
		
		
		

	
	
	
	
	
			
	
	
	
	}
	
	    public void colQtyOnEdit(Event e) {
			TableColumn.CellEditEvent<StoreItem,Double> ce;
			ce=(CellEditEvent<StoreItem,Double>) e;
			Double qty=ce.getNewValue().doubleValue();
			StoreItem si=ce.getRowValue();
			si.setQty(Double.parseDouble(ce.getNewValue().toString()));
			 }
		public void colCostAvgOnEdit(Event e) {
			TableColumn.CellEditEvent<StoreItem,Double> ce;
			ce=(CellEditEvent<StoreItem,Double>) e;
			Double avgCost=ce.getNewValue().doubleValue();
			StoreItem storeItem=ce.getRowValue();
			storeItem.getItem().setAvgCost(Double.parseDouble(ce.getNewValue().toString()));
			 }
	
		double qty=0.0;
		double avgCost=0.0;
		@FXML
	    private void addToDB(ActionEvent event) throws IOException{
			item.setItemName(txxItemId.getText().trim());	
			for(StoreItem storeItem:tbViewData){
				 if (storeItem.getQty()>0.0){
					 qty=qty+storeItem.getQty();
					 avgCost=avgCost+storeItem.getItem().getAvgCost();
					 
				 }
			
			}
			System.out.println("tbViewData.size=:"+tbViewData.size()+"-->" +avgCost/tbViewData.size());
			item.setAvgCost(avgCost/tbViewData.size()); //this want re-check becouse it's not completed.. 
			item.setQty(qty);
			itemService.save(item);
			for(StoreItem storeItem:tbViewData){
				 if (storeItem.getQty()>0.0){
					storeItemService.save(storeItem);
					 
				 }
			}	
			
				 
				 
			
		}
			
		}
	
	
*/