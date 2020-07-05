package com.demo.util;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableView;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class AlertUtil {

	
public static void showAlert(AlertType alertType,String headerText,String msg)
{
	Alert alert=new Alert(alertType);
	alert.setHeaderText(headerText);
	alert.setContentText(msg);
	
	alert.initModality(Modality.APPLICATION_MODAL);
	alert.initStyle(StageStyle.DECORATED);
	alert.show();
	
	
}
public static void showAlert(AlertType alertType,String title ,String headerText,String msg)
{
	Alert alert=new Alert(alertType);
	alert.setTitle(title);
	alert.setHeaderText(headerText);
	alert.setContentText(msg);
	alert.initModality(Modality.APPLICATION_MODAL);
	alert.initStyle(StageStyle.DECORATED);
	alert.show();
	}

public static Alert getAlert(AlertType alertType,String title ,String headerText,String msg)
{
	Alert alert=new Alert(alertType);
	alert.setTitle(title);
	alert.setHeaderText(headerText);
	alert.setContentText(msg);
	return alert;
}



}
