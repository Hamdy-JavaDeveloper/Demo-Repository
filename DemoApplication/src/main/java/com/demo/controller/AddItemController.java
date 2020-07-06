	package com.demo.controller;
	
	import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;

import com.demo.bean.Item;
import com.demo.cfg.StageManager;
import com.demo.service.ItemService;
import com.demo.util.AlertUtil;
import com.demo.util.ExceptionUtil;
import com.demo.view.FxmlView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
	
	@Controller
	public class AddItemController  implements Initializable{ 
	@Autowired
	ItemService itemService;
	
	@Autowired
	Item item;
	
	@Lazy
	@Autowired
	private StageManager stageManager;
	
	@FXML private Button btnSaveAndNew;
	@FXML private Button btnAddNewItem;
	@FXML private Button btnCancel;
	@FXML private TextField txtItemId;
	@FXML private TextField txtItemName;
	@FXML private TextField txtPrice1;
	@FXML private TextField txtPriceMin;
	@FXML private TextField txtDiscountPer1;
	@FXML private ComboBox<String> cmbCategroy1;
	@FXML private ComboBox<String> cmbUnit;
	@FXML private TextField txtBarCode;
	@FXML private TextField txtCode1;
	@FXML private TextField txtReOrederQty;
	@FXML private TextArea txtMore;
	@FXML private CheckBox chkService;
	@FXML private CheckBox chkStarted;
	@FXML private CheckBox chkDead;
	/**
	 * Adding Item to DB
	 * 
	 * @param event
	 * @throws IOException
	 * @throws ExceptionUtil 
	 */
		
		
		@FXML
	    private void addItem(ActionEvent event) throws IOException{
			
			try{
				Item item=getItemFromView();
						
				if (isItemExist(txtItemName.getText().toString())==true){ 
					Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
					result.ifPresent(response ->{
					if (response==ButtonType.OK){
							itemService.save(item);
							AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
					}else if (response==ButtonType.CANCEL){
							txtItemName.clear();
						//	txtItemName.setFocusTraversable(true);
							txtItemName.requestFocus();
						}
							});
				
				}else{
					itemService.save(item);
					AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
					cancle(event);
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
	
		
	
	@FXML
	private void get(ActionEvent event) throws IOException{
			stageManager.switchScene(FxmlView.MAIN);
		}
	
	/*private  boolean isValid(Item item, List<String> errorList) {
		boolean isValid = true;
		String itemName=txtItemName.getText().trim();
		if (itemName== null || itemName.trim().length() == 0) {
		errorList.add("Item Title must contain minimum one character.");
		isValid = false;
		}
		double price1=Double.parseDouble(txtPrice1.getText().trim());
		double priceMin=Double.parseDouble(txtPriceMin.getText().trim());
		double discountPer=Double.parseDouble(txtDiscountPer1.getText().trim());
		String categroy= cmbCategroy1.getSelectionModel().getSelectedItem().trim();
		if (categroy== null || categroy.trim().length() == 0) {
			errorList.add("Item Categroy must contain minimum one character.");
			isValid = false;
			}
		String unit=cmbUnit.getSelectionModel().getSelectedItem().trim();
		
		return isValid;
		}
	*/
	
	/**
	 * get Item propertise from GUI,
	 * When the method  throws an error message, we catch it.
	  * We throw a special error message by turning it into a meaningful message.
	 
	 * @return Item
	 * @throws ExceptionUtil
	 */
	
	private Item getItemFromView() throws ExceptionUtil
	{
	item.setItemId(Long.parseLong(txtItemId.getText().toString()));
	String itemName=txtItemName.getText().trim().toString();
		if (itemName== null || itemName.trim().length() == 0) {
			throw new ExceptionUtil(" ادخل اسم الصنف ");
		}else 
		{
		item.setItemName(txtItemName.getText().trim().toString());
		}
		
		if(!txtPrice1.getText().isEmpty() && txtPrice1.getText()!=null){
			try{	
			if(Double.parseDouble( txtPrice1.getText())>0) 
				item.setPrice1(Double.parseDouble( txtPrice1.getText()));
				
			}catch (NumberFormatException nfe){
				
				throw new ExceptionUtil("ادخل سعر الصنف بشكل مناسب");
			}
		}//end if
		
		if(!txtDiscountPer1.getText().isEmpty()){
			try{
			if(Double.parseDouble(txtDiscountPer1.getText())>0) 
						item.setDiscountPer(Double.parseDouble(txtDiscountPer1.getText()));
					
		}catch (NumberFormatException nfe){
			throw new ExceptionUtil("ادخل نسبة الخصم بشكل مناسب");
		}
		}//end if
		if(!txtPriceMin.getText().isEmpty()){
			try{
			if(Double.parseDouble(txtPriceMin.getText())>0) 
				item.setPriceMin(Double.parseDouble(txtPriceMin.getText()));
			}catch (NumberFormatException nfe){
				throw new ExceptionUtil("ادخل  اقل سعر بيع بشكل مناسب");
			}
			}//end if
		
		//check if cmbCategroy has selected value if true 
		if(cmbCategroy1.getSelectionModel().getSelectedItem()!=null){
			String categroy=cmbCategroy1.getSelectionModel().getSelectedItem().trim();
				if(categroy!=null || categroy.length()>0)	
					item.setCategroy1(cmbCategroy1.getSelectionModel().getSelectedItem().trim());
		}
		
		if(cmbUnit.getSelectionModel().getSelectedItem()!=null){
				String unit=cmbUnit.getSelectionModel().getSelectedItem().trim();
					if(unit!=null || unit.length()>0)
						item.setUnit(cmbUnit.getSelectionModel().getSelectedItem().trim());
		}
		
		String barcode=txtBarCode.getText().trim().toString();
		if(barcode!=null || barcode.length()>0)	item.setBarcode(txtBarCode.getText().trim().toString());
		
		item.setService(chkService.isSelected());
		item.setDead(chkDead.isSelected());
		item.setStarted(chkService.isSelected());
		
	
		if (!txtReOrederQty.getText().isEmpty()){
			try{
			if(Double.parseDouble(txtReOrederQty.getText().trim())>0)
						item.setReOrederQty(Double.parseDouble(txtReOrederQty.getText().trim()));
			}catch (NumberFormatException nfe){
				throw new ExceptionUtil("ادخل نسبة الخصم بشكل مناسب");
			}
			}//end if
		
			
	
		
			return item;
	
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.print("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		
	List<Item> items =itemService.findAll();
	
	
	Iterator<Item> iterator=items.iterator();
	List<String> unitName=new ArrayList<String>();
	while(iterator.hasNext()){
		Item i=(Item)iterator.next();
		unitName.add(i.getUnit());
	}
	cmbUnit.getItems().addAll(unitName);
	
	txtItemId.setDisable(true);
	Long maxId=itemService.findMaxId()+1L;
	txtItemId.setText(maxId.toString());
	}
	
		
	
		
	
	@FXML
	private void cancle(ActionEvent e) throws IOException{

		    
		    Stage stage = (Stage) btnCancel.getScene().getWindow();
		    stage.close();
		}
	
	
	
	@FXML
	private void addItemAndNew(ActionEvent event) throws IOException{
		
		try{
			
			Item item=getItemFromView();
			
			if (isItemExist(txtItemName.getText().toString())==true){ 
				Optional<ButtonType> result=AlertUtil.getAlert(AlertType.CONFIRMATION, "تنبية", "اسم هذا الصنف موجود من قبل ,هل انت متاكد من اضافة هذا الصنف", "aaaaaaa").showAndWait();
				result.ifPresent(response ->{
				if (response==ButtonType.OK){
					txtItemId.setDisable(true);
					Long maxId=itemService.findMaxId()+1;
					txtItemId.setText(maxId.toString());
					itemService.save(item);
					//initialize itemId for scound Item
					maxId=maxId+1;
					txtItemId.setText(maxId.toString());
					AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
				}else if (response==ButtonType.CANCEL){
						txtItemName.clear();
						//txtItemName.setFocusTraversable(true);
						txtItemName.requestFocus();
					}
						});
			
			
			
			} else	{
				txtItemId.setDisable(true);
				Long maxId=itemService.findMaxId()+1;
				txtItemId.setText(maxId.toString());
				itemService.save(item);
				//initialize itemId for scound Item
				maxId=maxId+1;
				txtItemId.setText(maxId.toString());
				AlertUtil.showAlert(AlertType.INFORMATION, "تم اضافة الصنف بنجاح", "");
				
			}
			
		}
		catch(ExceptionUtil eu){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", eu.getMessage() );
		}
			catch(Exception e){
			AlertUtil.showAlert(AlertType.INFORMATION, "فشل في عملية الادخال", e.getMessage());
			e.printStackTrace();
		}

		clearUI();
	}
	
	
	private void clearUI(){
		txtItemName.clear();
		txtPrice1.clear();
		txtDiscountPer1.clear();
		txtPriceMin.clear();
		txtCode1.clear();
		txtBarCode.clear();
		txtMore.clear();
		txtReOrederQty.clear();
		cmbCategroy1.setValue(cmbCategroy1.getPromptText());
		cmbUnit.setValue(cmbUnit.getPromptText());
		txtItemName.setFocusTraversable(true);
		
	}
	
	
	private Boolean isItemExist(String itemName){
		boolean isExits=false;
		if (itemService.findItemByItemName(itemName)==true){
			isExits=true;
        }
		return isExits;
	}
	
	
}
	

	
	
	
	