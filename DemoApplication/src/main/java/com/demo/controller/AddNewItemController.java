	package com.demo.controller;
	
	
	import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import com.demo.bean.Invoice;
import com.demo.bean.InvoiceItem;
import com.demo.bean.Item;
import com.demo.bean.ItemUnit;
import com.demo.bean.Store;
import com.demo.bean.StoreItem;
import com.demo.cfg.StageManager;
import com.demo.service.InvoiceItemService;
import com.demo.service.InvoiceService;
import com.demo.service.ItemService;
import com.demo.service.ItemUnitService;
import com.demo.service.StoreItemService;
import com.demo.service.StoreService;
import com.demo.util.AlertUtil;
import com.demo.util.ExceptionUtil;
import com.demo.util.InvoiceKind;
import com.demo.view.FxmlView;

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
import javafx.scene.layout.VBox;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.converter.DoubleStringConverter;
	
	@Controller
	public class AddNewItemController  implements Initializable {
		
		@FXML  private VBox AddNewItemVBoxPane;
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
		    private Button btnFirstItem;
		    @FXML
		    private Button btnPreviousItem;
		    @FXML
		    private Button btnNextItem;
		    @FXML
		    private Button btnLastItem;
		    @FXML
		    private Button btnNewItem;
		    
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
		    ObservableList<StoreItem> storeItemData=FXCollections.observableArrayList();
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
		    @Autowired    private Invoice invoice;
		    @Autowired    private InvoiceItem invoiceItem;
		    @Autowired    private ItemUnit itemUnit;
		    @Autowired    private StoreService storeService;
		    @Autowired    private ItemService itemService;
		    @Autowired    private StoreItemService	storeItemService;
		    @Autowired    private ItemUnitService itemUnitService;
		    @Autowired    private InvoiceItemService invoiceItemService;
		    @Autowired    private InvoiceService invoiceService;
		    
		    public void setTxtItemId(String itemId) {
		    	txtItemId.setText(itemId);
		    }
			//this instance will contain Scound stage that will be return from <== stageManager.switchScene2(..)
		   private Stage stage2;
		 
		    private  ObservableList<StoreItem> getStoreNamesList() {
		    	List<Store> stores=storeService.findAllByActiveIs(true);
			
				Iterator<Store> iterator=stores.iterator();
				Item myItem=new Item();
				while(iterator.hasNext())
				{
					StoreItem si=new StoreItem();
					
					si.setStore((Store)iterator.next());
					si.setItem(myItem);
					si.getItem().setAvgCost(0.0);
					
					storeItemData.add(si);
					
				}
				item=myItem;
				return storeItemData;
		    }
		    
		  //  public static final KeyCodeCombination saveShortcut=new 
		    @Override
		    public void initialize(URL location, ResourceBundle resources) {
		    	
		    	//====================================================
		    	//Shortcut Key
		    	AddNewItemVBoxPane.setOnKeyPressed(key ->{
		    		if(key.getCode()==KeyCode.ESCAPE) {
		    			try {
		    			cancle(null);
		    			}catch(IOException ioe) {
		    				
		    			}
		    		}
		    			
		    	});
		    	AddNewItemVBoxPane.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>() {

					@Override
					public void handle(KeyEvent key) {
						//Ctrl+S
						if(key.getCode()==KeyCode.S && key.isShortcutDown()) {
							System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
						}
					}
		    		
		    	});
		    	
	//============================================================================
	//Delete UnitRow 	    	
		    	tbItemUnit.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent keyEvent) {
						if (keyEvent.getCode()==KeyCode.DELETE) {
						ItemUnit itemUnitDeleted=tbItemUnit.getSelectionModel().getSelectedItem();
							itemUnitData.remove(itemUnitDeleted);
						itemUnitListDeleted.add(itemUnitDeleted);
						System.out.println(itemUnitDeleted);
						
						tbItemUnit.setItems(itemUnitData);
						
						}
					}
		    	  	});
		    	
		   			
					  txtItemId.setOnAction(new EventHandler<ActionEvent>() {
					  
					  @Override 
					  public void handle(ActionEvent event) {
					  showItemDetials(txtItemId.getText().trim());
					  }
					   });
					   
					if(location.getPath().contains("AddNewItem.fxml") ==true) {
		    		
			    		
		    		loadItemTableViews();
		    		
		    		//cmbItemUnit.setValue(cmbItemUnit.getPromptText());
		    		cmbItemUnit.setValue(null);
		    		ObservableList<String> unitList=FXCollections.observableArrayList(itemService.findDistinctByUnit());
		    		cmbItemUnit.setItems(unitList);	
		    		ObservableList<String> categroyList1=FXCollections.observableArrayList(itemService.findDistinctByCategroy1());
		    		cmbItemCategroy1.setItems(categroyList1);
		    		storeItemData.clear();
		    		storeItemData=getStoreNamesList();
		    		tbStoreItem.getItems().clear();
		    		tbStoreItem.setItems(storeItemData);
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
		
		/**
		 * This method called from txtItemId TextField
		 * @param itemId
		 */
		protected void showItemDetials(String itemId) {
			
			  if(itemId.isEmpty()==false && Long.parseLong(itemId)>0) { //item Item
				  	clearUI();
					//first we check if Item if exist in database or not
				  	item=itemService.find(Long.parseLong(itemId));
					if (item!=null) {
					txtItemName.setText(String.valueOf(item.getItemName()));
					txtItemPrice1.setText(String.valueOf(item.getPrice1()));
					txtItemPriceMin.setText(String.valueOf(item.getPriceMin()));
					txtItemDiscountPer1.setText(String.valueOf(item.getDiscountPer()));
					txtItemBarcode.setText(item.getBarcode());
					txtItemCode1.setText(item.getCode1());
					txtItemCode2.setText(item.getCode2());
					txtItemMore.setText(item.getMore());
					
					txtItemReorderQty.setText(String.valueOf(item.getReorderQty()));
					
					chkItemStart.setSelected(item.isStarted());
					chkItemDead.setSelected(item.isDead());
					chkItemService.setSelected(item.isService());
					chkItemPriceIncludeTax.setSelected(item.isPriceIncludeTax());
					
					cmbItemCategroy1.setValue(item.getCategroy1());
					cmbItemCategroy2.setValue(item.getCategroy2());
					
					cmbItemUnit.setValue(item.getUnit());
				//	cmbItemTaxType.setPromptText(item.getTaxType());
					
					
					//get Units
					List<ItemUnit> itemUnitList=itemUnitService.findByItem(item); 
					System.out.println("itemUnitList.size="+itemUnitList.size());
					//get StoreItem 
					List<StoreItem> storeItemList =storeItemService.findByItem(item);
					System.out.println("HHHHHHHHHHHHHHHHHHHHHstoreItemList.size="+storeItemList.size());
					//get Invoice kind of OPEN
					Invoice invoice=invoiceService.findByInvoiceId(0);					
					List<InvoiceItem>  invoiceItemList=invoiceItemService.findByInvoiceAndItem(invoice, item);
					System.out.println("**************invoiceItemList.size>="+invoiceItemList.size());
					  int i=0;
					  List<StoreItem> tempStoreItemList=new ArrayList();
					
						  for(InvoiceItem invoiceItem:invoiceItemList) {
						  storeItemList.get(i).setAvgCost(invoiceItem.getCost());
						  i++; 
						  }
					
						  //System.out.println(storeItemList);
						 
					 	/*
					 		i=0;
					 		
					 		
					 	 for(Store store:storeService.findAllByActiveIs(true))
					 	  {
					 		 
					 		  StoreItem si=new StoreItem();
					 		  //if(store.equals(storeItemList.get(i))
					 		  if (!storeItemList.get(i).getStore().equals(store)) {
					 		 //if (!storeItemList.removeAll(c)) {
					 		  System.out.println(store.getStoreName());
					 	//	 System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@storeItemList.contains(store)="+storeItemList.contains(store));
					 		 System.out.println(si.getStore());
					 		  
					 			  
					 			  
					 		  si.setStore(store);
					 		  si.setQty(0);
					 		  si.setAvgCost(0.0);
					 		  si.setItem(item);
					 	
					 		  storeItemList.add(si);
					 		 i++; 
					 		  }
					 		 
					 		   
					 	  }
					 	*/
						 
						  for(Store store:storeService.findAllByActiveIs(true))
					 	  {
					 		 
					 		  StoreItem si=new StoreItem();
					 		  si.setStore(store);
					 		  si.setItem(itemService.find(Long.parseLong(txtItemId.getText())));
					 		  
					 		  tempStoreItemList.add(si);
					 	  }

						  
						 
						  int ii=0;
						
						  System.out.println("storeItemList.size()="+storeItemList.size());
						  System.out.println("tempStoreItemList.size()="+tempStoreItemList.size());
						  if(storeItemList.size()>0) {	//This condition is important in case item does not have qty in storeItem and InvoiceItem th	 
						 // for(StoreItem storeItem:tempStoreItemList){
						  for(int j=0;j<tempStoreItemList.size();j++) {
							  if(storeItemList.get(ii).getStore().getId()==tempStoreItemList.get(j).getStore().getId()){
								  tempStoreItemList.set(j, storeItemList.get(ii));
								  System.out.println("ii="+ii);
								  ii++;
								  if (ii>=storeItemList.size())
									  {
									  ii--;
									  System.out.println("ii------------->"+ii);
									  }
							  }
							  
							  System.out.println("j="+j);
							  
							
						  }
					}//end if
						  
					
						  System.out.println("OOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO");
						  System.out.println("tempStoreItemList="+tempStoreItemList);
						  System.out.println("tempStoreItemList.size()="+tempStoreItemList.size());
						  
						  storeItemList.clear();
						  storeItemList.addAll(tempStoreItemList);
						
					 	  storeItemData.clear();
					 	  storeItemData.setAll(storeItemList); 
					 	// tbStoreItem.setItems(null);
					 	  tbStoreItem.setItems(storeItemData);
					      
					      
					
					      itemUnitData.clear();
					      itemUnitData.setAll(itemUnitList); 
					      tbItemUnit.setItems(null);
					      tbItemUnit.setItems(itemUnitData);
					      //index=Integer.parseInt(itemId);
					
				  }else {
					  AlertUtil.showAlert(AlertType.WARNING,"هذا الصنف غير موجود تاكد من رقم الصنف الذي ادخلته", "هذا الصنف غير موجود تاكد من رقم الصنف الذي ادخلته");
					  Long maxId=itemService.findMaxId(); 
						maxId=maxId+1;
						txtItemId.setText(maxId.toString()); 			//initialize itemId for secound Item
			
						txtItemName.requestFocus();
						
						itemUnitData.clear();     										//clear itemUnitData list
						tbItemUnit.setItems(itemUnitData);  							//clear tableView
						storeItemData.clear();												//clear tbViewData list
						storeItemData=getStoreNamesList();									//load store name from stores table to tbViewData list 
						tbStoreItem.setItems(storeItemData);								//load tbViewData list to tableView
						
						clearUI();
						
						//load units name from items table to combo
						cmbItemUnit.setValue(cmbItemUnit.getPromptText());            
						cmbItemUnit.setItems(FXCollections.observableArrayList(itemService.findDistinctByUnit()));
						//load categroy1 names from items table to combo
						cmbItemCategroy1.setValue(cmbItemCategroy1.getPromptText());            
						cmbItemCategroy1.setItems(FXCollections.observableArrayList(itemService.findDistinctByCategroy1()));
				  }
					//end if
			  }
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
			
			
		
			Item myItem=itemService.find(Long.parseLong(txtItemId.getText()) );
			if(myItem==null)
			saveNewItem();
			else
			updateItem();
			
				Long maxId=itemService.findMaxId(); 
				maxId=maxId+1;
				txtItemId.setText(maxId.toString()); 			//initialize itemId for secound Item
	
				txtItemName.requestFocus();
				
				itemUnitData.clear();     										//clear itemUnitData list
				tbItemUnit.setItems(itemUnitData);  							//clear tableView
				storeItemData.clear();												//clear tbViewData list
				storeItemData=getStoreNamesList();									//load store name from stores table to tbViewData list 
				tbStoreItem.setItems(storeItemData);								//load tbViewData list to tableView
				
				clearUI();
				
				//load units name from items table to combo
				cmbItemUnit.setValue(cmbItemUnit.getPromptText());            
				cmbItemUnit.setItems(FXCollections.observableArrayList(itemService.findDistinctByUnit()));
				//load categroy1 names from items table to combo
				cmbItemCategroy1.setValue(cmbItemCategroy1.getPromptText());            
				cmbItemCategroy1.setItems(FXCollections.observableArrayList(itemService.findDistinctByCategroy1()));
		
				flagSave=false;
			
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
		
		/**
		 * This method checks if item name is already exist in item table 
		 * @param itemName
		 * @return
		 */
		
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
			    txtItemName.requestFocus();
				
				itemUnitData.clear();     										//clear itemUnitData list
				tbItemUnit.setItems(itemUnitData);  							//clear tableView
				storeItemData.clear();												//clear tbViewData list
				storeItemData=getStoreNamesList();									//load store name from stores table to tbViewData list 
				tbStoreItem.setItems(storeItemData);								//load tbViewData list to tableView
				
				clearUI();
			    
			   
		
			}
		
	
	
		
		
		@FXML
	    private void addItem(ActionEvent event) throws IOException{
		
			Item myItem=itemService.find(Long.parseLong(txtItemId.getText()));
			if(myItem==null)
			saveNewItem();
			else
			updateItem();
			cancle(event);
			flagSave=false;
		}
	
	public ObservableList<StoreItem> storeItemListModified=FXCollections.observableArrayList(); 
	public ObservableList<ItemUnit> itemUnitListDeleted=FXCollections.observableArrayList();
		
	public void colQtyOnEdit(Event e) {
			TableColumn.CellEditEvent<StoreItem,Double> ce;
			ce=(CellEditEvent<StoreItem,Double>) e;
			Double qty=ce.getNewValue().doubleValue();
			StoreItem si=ce.getRowValue();
			si.setQty(Double.parseDouble(ce.getNewValue().toString()));
			System.out.println();
			System.out.println("storeItemListModified.contains("+si.getStore().getStoreName()+")="+storeItemListModified.contains(si) );
			if(storeItemListModified.contains(si)==false) {
				
			storeItemListModified.add(si);
			System.out.println("FROM EDIT_____>storeItemListModified.size="+storeItemListModified.size());
			}
			 }
		public void colCostAvgOnEdit(Event e) {
			TableColumn.CellEditEvent<StoreItem,Double> ce;
			ce=(CellEditEvent<StoreItem,Double>) e;
			Double avgCost=ce.getNewValue().doubleValue();
			StoreItem storeItem=ce.getRowValue();
			storeItem.setAvgCost(Double.parseDouble(ce.getNewValue().toString()));
			System.out.println("Edit().getAvgCost="+tbStoreItem.getSelectionModel().getSelectedItem().getItem().getAvgCost());
			System.out.println("storeItemListModified.contains("+storeItem.getStore().getStoreName()+")="+storeItemListModified.contains(storeItem) );
			if(storeItemListModified.contains(storeItem)==false) {
				
				storeItemListModified.add(storeItem);
			 }
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
			public void showNewWindow(ActionEvent event) throws ExceptionUtil{
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
				//fetch data from AddNewUnit window to tableView of units
					if((cmbUnitName2.getSelectionModel().getSelectedItem()!=null)&&(cmbUnitName2.getSelectionModel().getSelectedItem().trim().length()>0))
						itemUnit1.setUnit(cmbUnitName2.getValue().toString());
					else {
						cmbUnitName2.requestFocus();
						throw new ExceptionUtil("ادخل اسم الوحدة الجديدة");
						
					}
					
					double pieces=0;
					if(rdUnitBigger.isSelected()) {
			
							if((txtItemQty2.getText().isEmpty()==false) && ((Double.parseDouble(txtItemQty2.getText().toString()))>0)) 
										pieces=Double.parseDouble(txtItemQty2.getText().trim());
							else {
								txtItemQty2.requestFocus();
								throw new ExceptionUtil("ادخل كمية الوحدة الجديدة بشكل مناسب");
								 }
					}else if(rdUnitSmaller.isSelected()) {
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
			int index;//=goodsController.getItemTableView().getSelectionModel().getSelectedIndex();
			public void setIndex(int index) {
			this.index=index;
			}
			
			@Autowired
			private GoodsController goodsController;
			
			
			
			@FXML
			private void handleAddNewItemActions(ActionEvent event) throws IOException{
				Long itemId=Long.parseLong(txtItemId.getText());
				
			if(event.getSource()==btnItemDeleteUnit) {
				ItemUnit itemUnitDeleted=new ItemUnit();
				itemUnitDeleted=tbItemUnit.getSelectionModel().getSelectedItem();
				itemUnitData.remove(itemUnitDeleted);
				itemUnitListDeleted.add(itemUnitDeleted);
				System.out.println(itemUnitDeleted);
				tbItemUnit.setItems(itemUnitData);
			}
			else if(event.getSource()==btnItemUpdateUnitPrice){
				editUnitPrice();
			}
			
			//==================================================
			else if(event.getSource()==btnFirstItem){
				System.out.println("FisrtItem(Min)");
				//itemId=itemService.findMinItemId();
				index=0;
				itemId=goodsController.getItemTableView().getItems().get(index).getItemId();
				
				if(itemId!=0){
				txtItemId.setText(itemId.toString());
				showItemDetials(txtItemId.getText());
				
				btnFirstItem.setDisable(true);
				btnPreviousItem.setDisable(true);
				btnLastItem.setDisable(false);
				btnNextItem.setDisable(false);
				
				}
							
			}
			else if(event.getSource()==btnNextItem){
			
				
				
//				Item myItem=itemService.find(itemId);
//				while(myItem==null) {
//					itemId++;
//					myItem=itemService.find(itemId);
//					
//				}
				
				
				
				if(itemId<itemService.findMaxId() && index<goodsController.getItemTableView().getItems().size()-1){
				
					//===============
				
					index=index+1;
					System.out.println("index="+index);
					itemId=goodsController.getItemTableView().getItems().get(index).getItemId();
					//goodsController.getItemTableView().getSelectionModel().getSelectionM;
					
					
					//===============
				//itemId=itemId+1;
				
				txtItemId.setText(itemId.toString());
				
				showItemDetials(txtItemId.getText());
				btnFirstItem.setDisable(false);
				btnPreviousItem.setDisable(false);
				btnLastItem.setDisable(false);
				btnNextItem.setDisable(false);
			}else{
				btnFirstItem.setDisable(false);
				btnPreviousItem.setDisable(false);
				btnLastItem.setDisable(true);
				btnNextItem.setDisable(true);
				
			
			}
				
				
				
			}else if(event.getSource()==btnPreviousItem){
				System.out.println("PreviousItem");
				
				//itemId=Long.parseLong(txtItemId.getText());
				
				
				
				

				if(itemId>itemService.findMinItemId() && index>=0){
					
 					 if(index!=0)index=index-1;
 					 System.out.println("index="+index);
 					itemId=goodsController.getItemTableView().getItems().get(index).getItemId();
					
					//itemId=itemId-1;
					
				txtItemId.setText(itemId.toString());
				showItemDetials(txtItemId.getText());
				btnFirstItem.setDisable(false);
				btnPreviousItem.setDisable(false);
				btnLastItem.setDisable(false);
				btnNextItem.setDisable(false);
			}else {
				btnFirstItem.setDisable(true);
				btnPreviousItem.setDisable(true);
				btnLastItem.setDisable(false);
				btnNextItem.setDisable(false);
			}
			}
			else if(event.getSource()==btnLastItem){
				System.out.println("LastItem(Max)");
				 //itemId=itemService.findMaxId();
				index=goodsController.getItemTableView().getItems().size()-1;
				itemId=goodsController.getItemTableView().getItems().get(index).getItemId();
				
				if(itemId!=0){
				txtItemId.setText(itemId.toString());
				showItemDetials(txtItemId.getText());
				btnFirstItem.setDisable(false);
				btnPreviousItem.setDisable(false);
				btnLastItem.setDisable(true);
				btnNextItem.setDisable(true);
				}
			}
			//================================================
			else if(event.getSource()==btnNewItem){
				Long maxId=itemService.findMaxId(); 
				maxId=maxId+1;
				txtItemId.setText(maxId.toString()); 			//initialize itemId for secound Item
				txtItemName.requestFocus();
				itemUnitData.clear();     										//clear itemUnitData list
				tbItemUnit.setItems(itemUnitData);  							//clear tableView
				storeItemData.clear();												//clear tbViewData list
				storeItemData=getStoreNamesList();									//load store name from stores table to tbViewData list 
				tbStoreItem.setItems(storeItemData);								//load tbViewData list to tableView
				clearUI();
				
				//load units name from items table to combo
				cmbItemUnit.setValue(cmbItemUnit.getPromptText());            
				cmbItemUnit.setItems(FXCollections.observableArrayList(itemService.findDistinctByUnit()));
				//load categroy1 names from items table to combo
				cmbItemCategroy1.setValue(cmbItemCategroy1.getPromptText());            
				cmbItemCategroy1.setItems(FXCollections.observableArrayList(itemService.findDistinctByCategroy1()));
		
				flagSave=false;
			}
			}

			
			
	
			private void editUnitPrice() {
				
				TextInputDialog inputDialog=new TextInputDialog();
				inputDialog.setContentText("ادخل سعر الوحدة");
				inputDialog.initModality(Modality.APPLICATION_MODAL);
				inputDialog.initOwner(tbItemUnit.getScene().getWindow());
				inputDialog.initStyle(StageStyle.UTILITY);
				//inputDialog.setContentText(String.valueOf(tbItemUnit.getSelectionModel().getSelectedItem().getPrice()));
				inputDialog.setResult(String.valueOf(tbItemUnit.getSelectionModel().getSelectedItem().getPrice()));
				Optional<String> result=inputDialog.showAndWait();
				result.ifPresent(response ->{
					if(!response.isEmpty()) {
					double unitPrice=Double.parseDouble(response.trim());
					
					tbItemUnit.getSelectionModel().getSelectedItem().setPrice(unitPrice);
					tbItemUnit.refresh();
					
					}
						
			});
	}
			
			
	//==========================================
			private void saveAll(){
	
	
				//save or update 
				try{
					item=getItemFromView();
					//tester object 
					Item item1=itemService.find(item.getItemId());
					if(item1==null) {//if item is not exist
					if (isItemExist(txtItemName.getText().toString())==true){ 
						Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
						result.ifPresent(response ->{
						if (response==ButtonType.OK){
							flagSave=true;
						}else if (response==ButtonType.CANCEL){
							txtItemName.clear();
							txtItemName.requestFocus();
							}});
					
					} 
					else{
					
						flagSave=true;
					}
					}else {
						flagSave=true;
					}
				
					
					
					
					
					if(flagSave==true)
					{
						//txtItemId.setDisable(true);
						double qty=0.0;
						double avgCost=0.0;
						boolean flagHasQtys=false;
						long totalStoreHasQty=0;
						for(StoreItem storeItem:storeItemData){
							 if (storeItem.getQty()>0.0){
								
								 qty=qty+storeItem.getQty();
								 avgCost=avgCost+(storeItem.getAvgCost()*storeItem.getQty());
								 totalStoreHasQty=totalStoreHasQty+1;
								 
						   		flagHasQtys=true;		 
							 }
					
						}
						
								
					if(totalStoreHasQty>0){
						item.setAvgCost(avgCost/qty);
						}
						else
						item.setAvgCost(avgCost);
						item.setQty(qty);
						itemService.save(item);
						if(flagHasQtys==true){
						for(StoreItem storeItem:storeItemData){
							 if (storeItem.getQty()>0.0){
								storeItemService.save(storeItem);
								 }
							
							 
						}
						
						}//end if(flagHasQtys==true)
						
						for(ItemUnit itemUnit :itemUnitData) {
							itemUnit.setItem(item);
							itemUnitService.save(itemUnit);
						}
						invoice= invoiceService.findByInvoiceId(0);
	//    
	//					Iterator iterator=storeItemData.iterator();
	//					while(iterator.hasNext()) {
	//						
	//					}
						
						List<InvoiceItem>  invoiceItemList=invoiceItemService.findByInvoiceAndItem(invoice, item);
	
	
						if(invoiceItemList.size()<=0) {
							System.out.println("NewXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
						for(StoreItem si:storeItemData){
							if (si.getQty()>0.0){
							InvoiceItem invoiceItem=new InvoiceItem();
							invoiceItem.setInvoice(invoice);
							invoiceItem.setKind(InvoiceKind.OPEN);
							invoiceItem.setSn(0);
							invoiceItem.setItem(item);
							invoiceItem.setStore(si.getStore());
							invoiceItem.setQty(si.getQty());
							invoiceItem.setAmount(si.getAvgCost());
							invoiceItem.setTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setQtyIn(si.getQty());
							invoiceItem.setUnit(item.getUnit());
							invoiceItem.setUnitPieces(1);				//
							invoiceItem.setUnitQtyIn(si.getQty());
							invoiceItem.setUnitCost(si.getAvgCost());
							invoiceItem.setTotalCost(si.getQty()*si.getAvgCost());
							invoiceItem.setNetCost(si.getQty()*si.getAvgCost());	//
							invoiceItem.setNetTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setCost(si.getAvgCost());
							invoiceItem.setGrandTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setRealNetCost(si.getQty()*si.getAvgCost());
							invoiceItem.setRealCost(si.getAvgCost());
							invoiceItemService.save(invoiceItem);
							}
							}
						}
							else
						
						 {
							System.out.println("UpdateXXXXXXXXXXXXXXXXXXXXXXXXXXX");
	
							
														
							System.out.println("The storeItemListModified size ="+storeItemListModified.size());
							
							Iterator iterator=storeItemListModified.iterator();
							while(iterator.hasNext())
							{
								
										StoreItem si=(StoreItem)iterator.next();
										InvoiceItem invoiceItem=invoiceItemService.findByStoreItem(si);
									if(invoiceItem==null) {
										InvoiceItem invItem=new InvoiceItem();
										invoiceItem=invItem;
									}
										invoiceItem.setInvoice(invoice);
										invoiceItem.setKind(InvoiceKind.OPEN);
										invoiceItem.setSn(0);
										invoiceItem.setItem(item);
										invoiceItem.setStore(si.getStore());
									

										invoiceItem.setQty(si.getQty());
										
										invoiceItem.setAmount(si.getAvgCost());

										invoiceItem.setTotal(si.getQty()*si.getAvgCost());
										invoiceItem.setQtyIn(si.getQty());
										invoiceItem.setUnit(item.getUnit());
										invoiceItem.setUnitPieces(1);				//
										invoiceItem.setUnitQtyIn(si.getQty());
										invoiceItem.setUnitCost(si.getAvgCost());
										invoiceItem.setTotalCost(si.getQty()*si.getAvgCost());
										invoiceItem.setNetCost(si.getQty()*si.getAvgCost());
										invoiceItem.setNetTotal(si.getQty()*si.getAvgCost());
										invoiceItem.setCost(si.getAvgCost());
										invoiceItem.setGrandTotal(si.getQty()*si.getAvgCost());
										invoiceItem.setRealNetCost(si.getQty()*si.getAvgCost());
										invoiceItem.setRealCost(si.getAvgCost());
										invoiceItemService.save(invoiceItem);
			
										
										
										
								
							}
							storeItemListModified.clear();
							System.out.println("The invoiceItemList size ="+invoiceItemList.size());
							
							
						
						
						}
						AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
					}//end if(flagSave==true)
			
			}catch(ExceptionUtil eu){
				AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
			}
				catch(Exception e){
				AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
				e.printStackTrace();
			}
				
				
			}//end saveAll()
			
			//======================================================================
			private void saveNewItem() {
				try{
					item=getItemFromView();
					
					
						if (isItemExist(txtItemName.getText().toString())==true){ 
						Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
						result.ifPresent(response ->{
						if (response==ButtonType.OK){
							flagSave=true;
						}else if (response==ButtonType.CANCEL){
							txtItemName.clear();
							txtItemName.requestFocus();
							}});
					
					} 
					else{
					
						flagSave=true;
					}
				
			
				if(flagSave==true)
				{
					//txtItemId.setDisable(true);
					double qty=0.0;
					double avgCost=0.0;
					boolean flagHasQtys=false;
					long totalStoreHasQty=0;
					for(StoreItem storeItem:storeItemData){
						 if (storeItem.getQty()>0.0){
							 qty=qty+storeItem.getQty();
							 avgCost=avgCost+(storeItem.getAvgCost()*storeItem.getQty());
							 totalStoreHasQty=totalStoreHasQty+1; 
					   		flagHasQtys=true;		 
						 }
				
					}
					
							
				if(totalStoreHasQty>0){
					item.setAvgCost(avgCost/qty);
					}
					else
					item.setAvgCost(avgCost);
					item.setQty(qty);
					itemService.save(item);
					if(flagHasQtys==true){
					for(StoreItem storeItem:storeItemData){
						 if (storeItem.getQty()>0.0){
							storeItemService.save(storeItem);
							 }
					}
					
					}//end if(flagHasQtys==true)
					
					for(ItemUnit itemUnit :itemUnitData) {
						itemUnit.setItem(item);
						itemUnitService.save(itemUnit);
					}
					invoice= invoiceService.findByInvoiceId(0);
					
					List<InvoiceItem>  invoiceItemList=invoiceItemService.findByInvoiceAndItem(invoice, item);
					if(invoiceItemList.size()<=0) {
					for(StoreItem si:storeItemData){
						if (si.getQty()>0.0){
						InvoiceItem invoiceItem=new InvoiceItem();
						invoiceItem.setInvoice(invoice);
						invoiceItem.setKind(InvoiceKind.OPEN);
						invoiceItem.setSn(0);
						invoiceItem.setItem(item);
						invoiceItem.setStore(si.getStore());
						invoiceItem.setQty(si.getQty());
						invoiceItem.setAmount(si.getAvgCost());
						invoiceItem.setTotal(si.getQty()*si.getAvgCost());
						invoiceItem.setQtyIn(si.getQty());
						invoiceItem.setUnit(item.getUnit());
						invoiceItem.setUnitPieces(1);				//
						invoiceItem.setUnitQtyIn(si.getQty());
						invoiceItem.setUnitCost(si.getAvgCost());
						invoiceItem.setTotalCost(si.getQty()*si.getAvgCost());
						invoiceItem.setNetCost(si.getQty()*si.getAvgCost());	//
						invoiceItem.setNetTotal(si.getQty()*si.getAvgCost());
						invoiceItem.setCost(si.getAvgCost());
						invoiceItem.setGrandTotal(si.getQty()*si.getAvgCost());
						invoiceItem.setRealNetCost(si.getQty()*si.getAvgCost());
						invoiceItem.setRealCost(si.getAvgCost());
						invoiceItemService.save(invoiceItem);
						
		
						}
					
					}
					AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
					storeItemListModified.clear(); // it's necessary to Clear the List after each save operation
					
				}
				
				}//end if(flagSave==true)
				
				}catch(ExceptionUtil eu){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
		}
			catch(Exception e){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
			e.printStackTrace();
		}
		
	}//end saveNewItem
			
	private void updateItem() {
		try{
			
				flagSave=true;

			
			if(flagSave==true)
			{
				item=getItemFromView();
				//txtItemId.setDisable(true);
				double qty=0.0;
				double avgCost=0.0;
				boolean flagHasQtys=false;
				long totalStoreHasQty=0;
				for(StoreItem storeItem:storeItemData){
					 if (storeItem.getQty()>0.0){
						 qty=qty+storeItem.getQty();
						 avgCost=avgCost+(storeItem.getAvgCost()*storeItem.getQty());
					
						 totalStoreHasQty=totalStoreHasQty+1; 
				   		flagHasQtys=true;		 
					 }
			
				}
				
						
			if(totalStoreHasQty>0){
				item.setAvgCost(avgCost/qty);
				}
				else
				item.setAvgCost(avgCost);
				item.setQty(qty);
				itemService.save(item);
				if(flagHasQtys==true){
				for(StoreItem storeItem:storeItemData){
					 if (storeItem.getQty()>0.0){
						storeItemService.save(storeItem);
						 }
					 else if(storeItem.getQty()==0.0) {
						//if qty of item is set 0.0 then delete this item and store from storeItem table
						
						
						 storeItemService.delete(storeItem);
						 System.out.println("***********Delete *************************");
											 
						
						 					
						 }
					
					 }
						 
						
						 
					 
				}
				
				//end if(flagHasQtys==true)
				//save new units if user add new unit
				for(ItemUnit itemUnit :itemUnitData) {
					itemUnit.setItem(item);
					itemUnitService.save(itemUnit);
				}
				invoice= invoiceService.findByInvoiceId(0);
	
				List<InvoiceItem>  invoiceItemList=invoiceItemService.findByInvoiceAndItem(invoice, item);
				
				System.out.println("UpdateXXXXXXXXXXXXXXXXXXXXXXXXXXX");
				
				
				
				System.out.println("~~~~~~~~~~~~~~~~~~The storeItemListModified size ="+storeItemListModified.size());
				
				Iterator iterator=storeItemListModified.iterator();
				while(iterator.hasNext())
				{
					
							StoreItem si=(StoreItem)iterator.next();
							InvoiceItem invoiceItem=invoiceItemService.findByStoreItem(si);
						if(invoiceItem==null) {
							InvoiceItem invItem=new InvoiceItem();
							invoiceItem=invItem;
						}
							invoiceItem.setInvoice(invoice);
							invoiceItem.setKind(InvoiceKind.OPEN);
							invoiceItem.setSn(0);
							invoiceItem.setItem(item);
							invoiceItem.setStore(si.getStore());
						

							invoiceItem.setQty(si.getQty());
							
							invoiceItem.setAmount(si.getAvgCost());

							invoiceItem.setTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setQtyIn(si.getQty());
							invoiceItem.setUnit(item.getUnit());
							invoiceItem.setUnitPieces(1);				//
							invoiceItem.setUnitQtyIn(si.getQty());
							invoiceItem.setUnitCost(si.getAvgCost());
							invoiceItem.setTotalCost(si.getQty()*si.getAvgCost());
							invoiceItem.setNetCost(si.getQty()*si.getAvgCost());
							invoiceItem.setNetTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setCost(si.getAvgCost());
							invoiceItem.setGrandTotal(si.getQty()*si.getAvgCost());
							invoiceItem.setRealNetCost(si.getQty()*si.getAvgCost());
							invoiceItem.setRealCost(si.getAvgCost());
							if(invoiceItem.getQty()==0.0) 
							invoiceItemService.delete(invoiceItem);     		//delete invoiceItem if gty set to 0 to delete it
							else
							invoiceItemService.save(invoiceItem);

							
							

							
					
				}
				//here delete unit from database that user deleted from tableView of unit
				Iterator itemUnitIterator=itemUnitListDeleted.iterator();
				System.out.println("itemUnitListDeleted.size="+itemUnitListDeleted.size()+"@@@@@@@@@@@@@@@@@@@@@@@@@@");
				while(itemUnitIterator.hasNext()) {
					ItemUnit itemUnit=(ItemUnit)itemUnitIterator.next();
					//if(itemUnitService.find(itemUnit.getId())!=null)
					if(itemUnit.getItem()!=null)
					itemUnitService.delete(itemUnit);
				}
				flagSave=false;
				
				storeItemListModified.clear();
				itemUnitListDeleted.clear();
				System.out.println("The invoiceItemList size ="+invoiceItemList.size());

			
		
			AlertUtil.showAlert(AlertType.INFORMATION, "تم تعديل الصنف بنجاح", "");
		}//end if(flagSave==true)
	
	}catch(ExceptionUtil eu){
	AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
	}
	catch(Exception e){
	AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
	e.printStackTrace();
	}		
			
			
			}
	
	
	
	
	private void getAllItems() {
		
		Pageable paging1 = new PageRequest(0, 5);  //Sort.by("itemId")
		
		System.out.println("+++++++++"+paging1.getPageSize());
		//System.out.println("+++++++++"+paging1.next());
		//System.out.println("+++++++++"+paging1.);
		
        Page<Item> pagedResult = itemService.findAll(paging1);
        
        pagedResult.forEach(System.out::println);
        System.out.println("+++++++++pagedResult.getTotalElements()"+pagedResult.getTotalElements());
        System.out.println("+++++++++pagedResult.getNumberOfElements()"+pagedResult.getNumberOfElements());
        System.out.println("+++++++++pagedResult.getSize()"+pagedResult.getSize());
        System.out.println("+++++++++pagedResult.getTotalPages()"+pagedResult.getTotalPages());
        System.out.println("+++++++++pagedResult.getSort()"+pagedResult.getSort());
       
       
        }
        
        

	
	
	}
