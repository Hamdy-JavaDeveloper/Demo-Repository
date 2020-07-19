package com.demo.view;

import java.util.ResourceBundle;

public enum FxmlView {

	ADDNEWITEM {
        @Override
		public String getTitle() {return getStringFromResourceBundle("AddNewItem.title");}
        @Override
		public String getFxmlFile() {return "/fxml/AddNewItem.fxml";}
    			}, 
    ITEMS{
        @Override
		public String getTitle() {return getStringFromResourceBundle("items.title");}
        @Override
		public String getFxmlFile() {return "/fxml/items.fxml";}

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
    },	TEST{
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("testTb.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Emp.fxml";
            
        }
	
        },
		ADDNEWUNIT{
            @Override
    		public String getTitle() {
                return getStringFromResourceBundle("AddNewUnit.title");
            }

            @Override
    		public String getFxmlFile() {
                return "/fxml/AddNewUnit.fxml";
                
            }
        };
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
