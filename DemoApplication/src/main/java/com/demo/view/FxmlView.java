package com.demo.view;

import java.util.ResourceBundle;

public enum FxmlView {

	MAIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("emp.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Emp.fxml";
        }
    }, 
    ITEMS{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("items.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/items.fxml";
        }

    },
		ADDITEM{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("addItem.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/AddItem.fxml";
            

    }

        };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
