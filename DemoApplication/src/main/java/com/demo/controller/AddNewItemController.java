package com.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.bean.Store;
import com.demo.bean.StoreItem;
import com.demo.cfg.StageManager;
import com.demo.service.ItemService;
import com.demo.service.ItemUnitService;
import com.demo.service.StoreItemService;
import com.demo.service.StoreService;
import com.demo.util.AlertUtil;
import com.demo.util.ExceptionUtil;
import com.demo.view.FxmlView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;

@Controller
public class AddNewItemController  implements Initializable {
	
		@FXML
	    private TextField txtItemId; 
	 	@FXML
	    private TextField txtItemName;
	    @FXML
	    private TextField txtItemPrice1;
	    @FXML
	    private TextField txtItemDiscountPer1;
	    @FXML
	    private TextField txtItemPriceMin;
	    @FXML
	    private ComboBox<String> cmbItemCategroy1;
	    @FXML
	    private ComboBox<String> cmbItemCategroy2;
	    @FXML
	    private TextField txtItemCode1;
	    @FXML
	    private TextField txtItemCode2;
	    @FXML
	    private TextField txtItemBarcode;
	    @FXML
	    private ComboBox<String> cmbItemTaxType;
	    @FXML
	    private CheckBox chkItemPriceIncludeTax;
	    @FXML
	    private CheckBox chkItemService;
	    @FXML
	    private CheckBox chkItemStart;
	    @FXML
	    private CheckBox chkItemDead;
	    @FXML
	    private TextField txtItemReorderQty;
	     @FXML
	    private Button btnItemAddUnit;
	    @FXML
	    private Button btnItemDeleteUnit;
	    @FXML
	    private Button btnItemUpdateUnitPrice;
	    @FXML
	    private ComboBox<String> cmbItemUnit;
	    @FXML
	    private TextArea txtItemMore;
	    @FXML
	    private Button btnItemSaveAndNew;
	    @FXML
	    private Button btnItemSave;
	    @FXML
	    private Button btnItemCancel;
	    @FXML
	    private TextField txtFirst; 
	 	@FXML
	    private TextField txtScound;
	    
		@FXML   private TableView<ItemUnit> tbItemUnit;
		@FXML   private TableColumn<ItemUnit, String> colUnitName;
		@FXML   private TableColumn<ItemUnit, Double> colUnitQty;
		@FXML   private TableColumn<ItemUnit, Double> colUnitPrice;
		ObservableList<ItemUnit> itemUnitData=FXCollections.observableArrayList();  
		

	    @FXML   private TableView<StoreItem> tbStoreItem;
	    @FXML   private TableColumn<StoreItem, Long> colStoreId;
	    @FXML   private TableColumn<StoreItem, String> colStoreName;
	    @FXML   private TableColumn<StoreItem, Double> colItemQty;
	    @FXML   private TableColumn<StoreItem, Double> colItemAvgCost;
	    ObservableList<StoreItem> tbViewData=FXCollections.observableArrayList();
		//AddNewUnit window ===========================================

	    @FXML   private Label lblItemUnitName;
	    @FXML   private TextField txtUnitPrice;
	    @FXML   private TextField txtItemQty2;
	    @FXML   private Button btnUnitCancel;
	    @FXML   private ComboBox<String> cmbUnitName2;
	    @FXML   private Button btnAddNewUnit;
	    @FXML   private TextField txtUnitPices;
	    @FXML   private RadioButton rdUnitSmaller;
	    @FXML   private RadioButton rdUnitBigger;
	    @FXML   private Label lblItemQty2;
	    @FXML   private Label lblUnitPices;
	    //this instance will be use if we want launch scound stage 
	    @Autowired
		private ApplicationContext applicationContext;
		 @Lazy
		 @Autowired
		 private StageManager stageManager;
		 
		@Autowired	  private Item item;
	    @Autowired    private StoreItem storeItem;
	    @Autowired    private Store store;
	    @Autowired    private StoreService storeService;
	    @Autowired    private ItemService itemService;
	    @Autowired    private StoreItemService	storeItemService;
	    @Autowired    private ItemUnit itemUnit;
	    @Autowired    private ItemUnitService itemUnitService;
		//this instance will contain Scound stage that will be return from <== stageManager.switchScene2(..)
	   private Stage stage2;
	 
	    private  ObservableList<StoreItem> getStoreNameObserverableList() {
	    	List<Store> stores=storeService.findAllByActiveIs(true);
		
			Iterator<Store> iterator=stores.iterator();
			
			while(iterator.hasNext())
			{
				StoreItem si=new StoreItem();
				
				si.setStore((Store)iterator.next());
				si.setItem(item);
				si.getItem().setAvgCost(0.0);
				
				tbViewData.add(si);
				
			}
			return tbViewData;
	    }
	  
	  
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	
	    	
	    	tbItemUnit.setOnKeyPressed(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent keyEvent) {
					if (keyEvent.getCode()==KeyCode.DELETE) {
					itemUnitData.remove(tbItemUnit.getSelectionModel().getSelectedItem());
					tbItemUnit.setItems(itemUnitData);
					}
				}
	    	  	});	
	    	
	    	
	    	if(location.getPath().contains("AddNewItem.fxml") ==true) {
	    		
		    		
	    		loadItemTableViews();
	    		
	    		//cmbItemUnit.setValue(cmbItemUnit.getPromptText());
	    		cmbItemUnit.setValue(null);
	    		ObservableList<String> unitNames=FXCollections.observableArrayList(itemService.findDistinctByUnit());
	    		cmbItemUnit.setItems(unitNames);	
	    	
	    		tbViewData=getStoreNameObserverableList();
	    		tbStoreItem.getItems().clear();
	    		tbStoreItem.setItems(tbViewData);
	    		Long maxId=itemService.findMaxId()+1L;
	    		txtItemId.setText(maxId.toString());
	    	
	    	}
	    	
	    	if(location.getPath().contains("AddNewUnit.fxml") ==true) {
	    		
	    		//fetching all unitnames from item and itemUnit
	    		ObservableList<String> unitNames=FXCollections.observableArrayList(itemUnitService.findDistinctByUnit());
	    		unitNames.addAll(FXCollections.observableArrayList(itemService.findDistinctByUnit()));
	    		cmbUnitName2.setItems(unitNames);
	    		
	    		txtUnitPices.setVisible(false);
	    		lblUnitPices.setVisible(false);
	    		txtItemQty2.setVisible(false);
	    		lblItemQty2.setVisible(false);
	    		    		
	    	}
	}//end initilize method 
	
	
	private Item getItemFromView() throws ExceptionUtil
	{
		
		item.setItemId(Long.parseLong(txtItemId.getText().toString()));
		System.out.println(item.getItemId()+"ID>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
		String itemName=txtItemName.getText().trim().toString();
		if (itemName== null || itemName.trim().length() == 0) {
			throw new ExceptionUtil(" ادخل اسم الصنف ");
		}else 
		{
		item.setItemName(txtItemName.getText().trim().toString());
		}
		//-----------------------------------------
		//System.out.println("hamdi:"+txtItemPrice1.getText()+"   isEmpt:"+  txtItemPrice1.getText().isEmpty()  +  " isNull:"+ (txtItemPrice1.getText().trim().toString()==null));
		if((txtItemPrice1.getText().isEmpty()==false)){  //(txtItemPrice1.getText()!=null)
			try{	
			if((Double.parseDouble(txtItemPrice1.getText().toString()))>0) 
			item.setPrice1(Double.parseDouble(txtItemPrice1.getText().toString()));
				
			}catch (NumberFormatException nfe){
				
				throw new ExceptionUtil("ادخل سعر الصنف بشكل مناسب");
			}
		}//end if
		//-----------------------------------------
		if(!txtItemDiscountPer1.getText().isEmpty()){
			try{
			if(Double.parseDouble(txtItemDiscountPer1.getText())>0) 
						item.setDiscountPer(Double.parseDouble(txtItemDiscountPer1.getText()));
					
		}catch (NumberFormatException nfe){
			throw new ExceptionUtil("ادخل نسبة الخصم بشكل مناسب");
		}
		}//end if
		if(!txtItemPriceMin.getText().isEmpty()){
			try{
			if(Double.parseDouble(txtItemPriceMin.getText())>0) 
				item.setPriceMin(Double.parseDouble(txtItemPriceMin.getText()));
			}catch (NumberFormatException nfe){
				throw new ExceptionUtil("ادخل  اقل سعر بيع بشكل مناسب");
			}
			}//end if
		
		//check if cmbCategroy has selected value if true 
		  if(cmbItemCategroy1.getSelectionModel().getSelectedItem()!=null){
			String categroy=cmbItemCategroy1.getSelectionModel().getSelectedItem().trim();	
				if(categroy!=null || categroy.length()>0)	
					item.setCategroy1(cmbItemCategroy1.getSelectionModel().getSelectedItem().trim());
		}
		  if(cmbItemCategroy2.getSelectionModel().getSelectedItem()!=null){
				String categroy2=cmbItemCategroy2.getSelectionModel().getSelectedItem().trim();	
					if(categroy2!=null || categroy2.length()>0)	
						item.setCategroy2(cmbItemCategroy2.getSelectionModel().getSelectedItem().trim());
			}
		
		if(cmbItemUnit.getSelectionModel().getSelectedItem()!=null){
				String unit=cmbItemUnit.getSelectionModel().getSelectedItem().trim();
					if(unit!=null || unit.length()>0)
						item.setUnit(cmbItemUnit.getSelectionModel().getSelectedItem().trim());
		}
		
		String barcode=txtItemBarcode.getText().trim().toString();
		if(barcode!=null || barcode.length()>0)	item.setBarcode(txtItemBarcode.getText().trim().toString());

		String code1=txtItemBarcode.getText().trim().toString();
		if(code1!=null || code1.length()>0)	item.setCode1(txtItemCode1.getText().trim().toString());
		
		String code2=txtItemBarcode.getText().trim().toString();
		if(code2!=null || code2.length()>0)	item.setCode2(txtItemCode2.getText().trim().toString());

		item.setService(chkItemService.isSelected());
		item.setDead(chkItemDead.isSelected());
		item.setStarted(chkItemStart.isSelected());
		item.setPriceIncludeTax(chkItemPriceIncludeTax.isSelected());
		
	String reorderQty=txtItemReorderQty.getText().toString();
	if (reorderQty.isEmpty()==false || reorderQty.length()>0){
			try{
			if(Double.parseDouble(reorderQty.trim())>0)
						item.setReorderQty(Double.parseDouble(reorderQty.trim()));
			}catch (NumberFormatException nfe){
				throw new ExceptionUtil("ادخل نسبة الخصم بشكل مناسب");
			}
			}//end if
	
	if(txtItemMore.getText()!=null || txtItemMore.getText().isEmpty()==false)
	{
		item.setMore(txtItemMore.getText().toString());
	}
			
			return item;
	}
	

	boolean flagSave=false;
	@FXML
	private void addItemAndNew(ActionEvent event) throws IOException{
		
		try{
			 item=getItemFromView();
			
			if (isItemExist(txtItemName.getText().toString())==true){ 
				Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
				result.ifPresent(response ->{
				if (response==ButtonType.OK){
					flagSave=true;
				}else if (response==ButtonType.CANCEL){
						
					txtItemName.clear();
						
						//txtItemName.requestFocus();
					}
						});
			
			
			
			} else	{
			
				flagSave=true;
						
			}
			
			if(flagSave==true)
			{
				
				//txtItemId.setDisable(true);
				Long maxId=itemService.findMaxId()+1;
				txtItemId.setText(maxId.toString());
				maxId=maxId+1;
				//initialize itemId for secound Item
				txtItemId.setText(maxId.toString());
				double qty=0.0;
				double avgCost=0.0;
			
				boolean flagHasQtys=false;
				
				
				
				long totalStoreHasQty=0;
				for(StoreItem storeItem:tbViewData){
					 if (storeItem.getQty()>0.0){
						 qty=qty+storeItem.getQty();
						 avgCost=avgCost+(storeItem.getAvgCost()*storeItem.getQty());
						 totalStoreHasQty=totalStoreHasQty+1; 
						flagHasQtys=true;		 
					 }
					 
				}
				
						
				if(totalStoreHasQty>0) {
				item.setAvgCost(avgCost/qty);
				}
				else
				item.setAvgCost(avgCost);
				
				item.setQty(qty);
				
				itemService.save(item);
				
				if(flagHasQtys==true){
				for(StoreItem storeItem:tbViewData){
					 if (storeItem.getQty()>0.0){
						storeItemService.save(storeItem);
						 }
				}
				
				
				
				}//end if(flagHasQtys==true)
				
				for(ItemUnit itemUnit :itemUnitData) {
					itemUnit.setItem(item);
					itemUnitService.save(itemUnit);
				}
				AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
				itemUnitData.clear();
				tbItemUnit.setItems(itemUnitData);
				tbViewData.clear();
				tbViewData=getStoreNameObserverableList();
				tbStoreItem.setItems(tbViewData);
				
				clearUI();
				cmbItemUnit.setValue(cmbItemUnit.getPromptText());
				ObservableList<String> unitNames=FXCollections.observableArrayList(itemService.findDistinctByUnit());
	    		cmbItemUnit.setItems(unitNames);	
				flagSave=false;
				txtItemName.requestFocus();
			}//end if(flagSave==true)
		}
		
		catch(ExceptionUtil eu){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
		}
			catch(Exception e){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
			e.printStackTrace();
		}

	
}
	
	private void clearUI(){
		txtItemName.clear();
		txtItemPrice1.clear();
		txtItemDiscountPer1.clear();
		txtItemPriceMin.clear();
		txtItemCode1.clear();
		txtItemBarcode.clear();
		txtItemMore.clear();
		txtItemReorderQty.clear();
		cmbItemCategroy1.setValue(cmbItemCategroy1.getPromptText());
		cmbItemUnit.setValue(cmbItemUnit.getPromptText());
		txtItemName.setFocusTraversable(true);
		chkItemDead.setSelected(false);
		chkItemStart.setSelected(false);
		chkItemService.setSelected(false);
		chkItemPriceIncludeTax.setSelected(false);
		
	}
	private Boolean isItemExist(String itemName){
		boolean isExits=false;
		if (itemService.findItemByItemName(itemName)==true){
			isExits=true;
        }
		return isExits;
	}
	@FXML
	private void cancle(ActionEvent e) throws IOException{

		    
		    Stage stage = (Stage) btnItemCancel.getScene().getWindow();
		  //  stage.close();
		    stage.hide();
		    
		   
	
		}
	


	
	boolean flagSaveSuccess=false;
	@FXML
    private void addItem(ActionEvent event) throws IOException{
		
		try{
				
			 item=getItemFromView();
					
			if (isItemExist(txtItemName.getText().toString())==true){ 
				Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
				result.ifPresent(response ->{
				if (response==ButtonType.OK){
					flagSaveSuccess=true;
					
						
				}else if (response==ButtonType.CANCEL){
						txtItemName.clear();
						txtItemName.requestFocus();
					}
						});
			
			}else{
				flagSaveSuccess=true;
			}
			if (flagSaveSuccess)
			{
				
				double qty=0;
				double avgCost=0;
				boolean flagHasQtys=false;
				long totalStoreHasQty=0;
				
				for(StoreItem storeItem:tbViewData){
					 if (storeItem.getQty()>0.0){
						 qty=qty+storeItem.getQty();
						 avgCost=avgCost+(storeItem.getAvgCost()*storeItem.getQty());
						 
						totalStoreHasQty=totalStoreHasQty+1; 
						flagHasQtys=true;		 
					 }
			
				}
				
				if(totalStoreHasQty>0)
				item.setAvgCost(avgCost/qty);
				else
				item.setAvgCost(avgCost);
				
				item.setQty(qty);
				
				itemService.save(item);
				
				if(flagHasQtys==true){
				for(StoreItem storeItem:tbViewData){
					 if (storeItem.getQty()>0.0){
						storeItemService.save(storeItem);
						 }
				}
				}//end if(flagHasQtys==true)
			
				
				for(ItemUnit itemUnit :itemUnitData) {
					itemUnit.setItem(item);
					itemUnitService.save(itemUnit);
				}
				AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
				cancle(event);
				flagSaveSuccess=false;
			}

		}
		catch(ExceptionUtil eu){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
		}
			catch(Exception e){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
			e.printStackTrace();
		}
		
				
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
		storeItem.setAvgCost(Double.parseDouble(ce.getNewValue().toString()));
		System.out.println("Edit().getAvgCost="+tbStoreItem.getSelectionModel().getSelectedItem().getItem().getAvgCost());
		 }
	
	public void colUnitPriceOnEdit(Event e) {
		TableColumn.CellEditEvent<ItemUnit,Double> ce;
		ce=(CellEditEvent<ItemUnit,Double>) e;
		Double unitPrice=ce.getNewValue().doubleValue();
		ItemUnit iu=ce.getRowValue();
		iu.setPrice(Double.parseDouble(ce.getNewValue().toString()));
				 }
	
	
	
	public void loadItemTableViews() {
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
	
						return param.getValue().getAvgCost();
					}
					
				};
			}
		});
    	colItemAvgCost.setCellFactory(new TextFieldTableCell().forTableColumn(new DoubleStringConverter()));
		colItemAvgCost.setOnEditCommit(e -> colCostAvgOnEdit(e));
		
		colUnitName.setCellValueFactory(new PropertyValueFactory<>("unit"));
    	colUnitQty.setCellValueFactory(new PropertyValueFactory<>("pieces"));
    	
    	colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    	colUnitPrice.setCellFactory(new TextFieldTableCell().forTableColumn(new DoubleStringConverter()));
    	colUnitPrice.setOnEditCommit(e -> colUnitPriceOnEdit(e));
	}

		
		@FXML
		public void showNewWindow(ActionEvent event) throws ExceptionUtil
		{
			if(event.getSource()==btnItemAddUnit){
			
				try{
					if(cmbItemUnit.getValue()==null) 
						throw new ExceptionUtil("ادخل اسم الوحدة الرئيسية اولا");
					else if (cmbItemUnit.getValue().trim().length()<=0)
						throw new ExceptionUtil("ادخل اسم الوحدة الرئيسية اولا");
			
					 stage2=stageManager.switchScene2(applicationContext, FxmlView.ADDNEWUNIT);
					 lblItemUnitName.setText(cmbItemUnit.getValue().trim());
					}catch(ExceptionUtil eu){
						AlertUtil.showAlert(AlertType.ERROR,"رسالة خطأ",eu.getMessage(),"");
					}
			}
		
		}
		
		
		@FXML 
		public void handleAddUnitActions(ActionEvent event) throws IOException,ExceptionUtil
		{
			if(event.getSource()==btnAddNewUnit) {
			try {
				ItemUnit itemUnit1=new ItemUnit();
			//fetch data from AddNewUnit form to tableView of units
				if((cmbUnitName2.getSelectionModel().getSelectedItem()!=null)&&(cmbUnitName2.getSelectionModel().getSelectedItem().trim().length()>0)) {
					System.out.println(cmbUnitName2.getSelectionModel().getSelectedItem().trim()+"<<<<<<<<<<<<<<<<<<<<<");
					itemUnit1.setUnit(cmbUnitName2.getValue().toString());
				}else {
					cmbUnitName2.requestFocus();
					throw new ExceptionUtil("ادخل اسم الوحدة الجديدة");
					
				}
				
							double pieces=0;
							if(rdUnitBigger.isSelected()) {
								
								if((txtItemQty2.getText().isEmpty()==false) && ((Double.parseDouble(txtItemQty2.getText().toString()))>0)){  //(txtItemPrice1.getText()!=null)
									pieces=Double.parseDouble(txtItemQty2.getText().trim());
									}else {
										txtItemQty2.requestFocus();
										throw new ExceptionUtil("ادخل كمية الوحدة الجديدة بشكل مناسب");
									}
							}
						
							else if(rdUnitSmaller.isSelected()) {
								if((txtUnitPices.getText().isEmpty()==false) && ((Double.parseDouble(txtUnitPices.getText().toString()))>0))
									pieces=Double.parseDouble(txtItemQty2.getText().trim())/Double.parseDouble(txtUnitPices.getText().trim());
								else {
									txtItemQty2.requestFocus();	
									throw new ExceptionUtil("ادخل كمية الوحدة الجديدة بشكل مناسب");
								}
							}
							else
								{
								throw new ExceptionUtil("حدد اذا ما كانت الوحدة الجديدة اكبر ام اصغر من الوحدة الرئيسية !");
								}
						
							if((txtUnitPrice.getText().isEmpty()==false) && ((Double.parseDouble(txtUnitPrice.getText().toString()))>0)) 
								itemUnit1.setPrice(Double.parseDouble(txtUnitPrice.getText()));
								
							else {
								txtUnitPrice.requestFocus();
								throw new ExceptionUtil("ادخل سعر الوحدة الجديدة بشكل مناسب");	
							}
						itemUnit1.setPieces(pieces);
						itemUnitData.add(itemUnit1);
						tbItemUnit.setItems(itemUnitData);
																
						stage2.close();
			
			}catch(ExceptionUtil eu) {
				AlertUtil.showAlert(AlertType.ERROR,eu.getMessage(),eu.getMessage());
			}
			
			}else if(event.getSource()==btnUnitCancel) {
				stage2.close();
				
					
			}else if (event.getSource()==rdUnitSmaller) {
				txtUnitPices.setVisible(true);
	    		lblUnitPices.setVisible(true);
	    		txtItemQty2.setVisible(true);
	    		lblItemQty2.setVisible(true);
	    		lblItemQty2.setText("كم كمية هذه الوحدة؟");
				
			}else if(event.getSource()  == rdUnitBigger) {
				txtUnitPices.setVisible(false);
	    		lblUnitPices.setVisible(false);
	    		txtItemQty2.setVisible(true);
	    		lblItemQty2.setVisible(true);
	    		lblItemQty2.setText("كم تحتوي الوحدة الجديدة من الوحدة الرئيسية");
			}
		
		}
		@FXML
		private void handleAddNewItemActions(ActionEvent event) throws IOException{
		
		if(event.getSource()==btnItemDeleteUnit) {
			
			itemUnitData.remove(tbItemUnit.getSelectionModel().getSelectedItem());
			tbItemUnit.setItems(itemUnitData);
		}
		else if(event.getSource()==btnItemUpdateUnitPrice){
			editUnitPrice();
		}
		
		}
		

		private void editUnitPrice() {
			TextInputDialog inputDialog=new TextInputDialog();
			inputDialog.setContentText("ادخل سعر الوحدة");
			inputDialog.initModality(Modality.APPLICATION_MODAL);
			inputDialog.initOwner(tbItemUnit.getScene().getWindow());
			inputDialog.initStyle(StageStyle.UTILITY);
			
			Optional<String> result=inputDialog.showAndWait();
			result.ifPresent(response ->{
				if(!response.isEmpty()) {
				double unitPrice=Double.parseDouble(response.trim());
				
				tbItemUnit.getSelectionModel().getSelectedItem().setPrice(unitPrice);
				}
					
		});
}
}
