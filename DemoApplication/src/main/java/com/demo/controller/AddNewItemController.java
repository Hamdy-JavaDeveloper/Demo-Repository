package com.demo.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.bean.Store;
import com.demo.bean.StoreItem;
import com.demo.cfg.StageManager;
import com.demo.service.ItemService;
import com.demo.service.StoreItemService;
import com.demo.service.StoreService;
import com.demo.util.AlertUtil;
import com.demo.util.ExceptionUtil;
import com.demo.view.FxmlView;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;



@Controller
public class AddNewItemController  implements Initializable {
	@FXML
	private Button btnAddNewUnit;
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
	    
	    
		@FXML   private TableView<ItemUnit> tbItemUnit;
		@FXML   private TableColumn<ItemUnit, String> colUnitName;
		@FXML   private TableColumn<ItemUnit, Double> colUnitQty;
		@FXML   private TableColumn<ItemUnit, Double> colUnitPrice;
		ObservableList<ItemUnit> unitsData=FXCollections.observableArrayList();  
		

	    @FXML   private TableView<StoreItem> tbStoreItem;
	    @FXML   private TableColumn<StoreItem, Long> colStoreId;
	    @FXML   private TableColumn<StoreItem, String> colStoreName;
	    @FXML   private TableColumn<StoreItem, Double> colItemQty;
	    @FXML   private TableColumn<StoreItem, Double> colItemAvgCost;
	    ObservableList<StoreItem> tbViewData=FXCollections.observableArrayList();
		
		 @Lazy
		 @Autowired private StageManager stageManager;
		 
		@Autowired	    Item item;
	    
	    @Autowired    StoreItem storeItem;
	    @Autowired    Store store;
	    @Autowired    StoreService storeService;
	    @Autowired    ItemService itemService;
	    @Autowired    StoreItemService	storeItemService;
	    
	  
	 
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
				System.out.println("storeName:="+si.getStore().getStoreName());
			}
			return tbViewData;
	    }
	    
	    @Override
	    public void initialize(URL location, ResourceBundle resources) {
	    	
	    	loadItemTableViews();
	    	
			/*
			 * Platform.runLater(new Runnable() {
			 * 
			 * @Override public void run() { tbStoreItem.requestFocus();
			 * tbStoreItem.getSelectionModel().select(0);
			 * tbStoreItem.getFocusModel().focus(0);
			 * 
			 * } });
			 */

					
			tbViewData=getStoreNameObserverableList();
			tbStoreItem.setItems(tbViewData);
			Long maxId=itemService.findMaxId()+1L;
			txtItemId.setText(maxId.toString());
		
	}//end initilize method 
	    
	    
	   /**
	    * This method is to test the connection between two fxml files 
	    * @param e
	    * @throws IOException
	    */
	    @FXML public void addUnit(ActionEvent e) throws IOException {
	    	
	    	    	  stageManager.switchScene(FxmlView.TEST);
	    	  
	    	  }
	    
	    
		
	
	
	
	
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
				//initialize itemId for scound Item
				txtItemId.setText(maxId.toString());
				double qty=0.0;
				double avgCost=0.0;
			
				boolean flagHasQtys=false;
				
				
				
				long totalStoreHasQty=0;
				for(StoreItem storeItem:tbViewData){
					 if (storeItem.getQty()>0.0){
						 qty=qty+storeItem.getQty();
						 avgCost=avgCost+storeItem.getItem().getAvgCost();
						totalStoreHasQty=totalStoreHasQty+1; 
						flagHasQtys=true;		 
					 }
			
				}
				
				System.out.println("tbViewData.size=:"+tbViewData.size());
				
				
				if(totalStoreHasQty>0)
				item.setAvgCost(avgCost/totalStoreHasQty);
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
				AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
				
				tbViewData.clear();
				tbViewData=getStoreNameObserverableList();
				tbStoreItem.setItems(tbViewData);
				
				clearUI();
		
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
		    stage.close();
		    
		   
	
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
				itemService.save(item);
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
		storeItem.getItem().setAvgCost(Double.parseDouble(ce.getNewValue().toString()));
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
	
						return param.getValue().getItem().getAvgCost();
					}
					
				};
			}
		});
    	colItemAvgCost.setCellFactory(new TextFieldTableCell().forTableColumn(new DoubleStringConverter()));
		colItemAvgCost.setOnEditCommit(e -> colCostAvgOnEdit(e));
		
		colUnitName.setCellValueFactory(new PropertyValueFactory<>("unit"));
    	colUnitQty.setCellValueFactory(new PropertyValueFactory<>("pieces"));
    	colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
	}

}
