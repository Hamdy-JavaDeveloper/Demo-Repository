package com.demo.cfg;

import static org.slf4j.LoggerFactory.getLogger;

import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;

import com.demo.view.FxmlView;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Manages switching Scenes on the Primary Stage
 */
public class StageManager {

    private static final Logger LOG = getLogger(StageManager.class);
    private final Stage primaryStage;
    private final SpringFXMLLoader springFXMLLoader;

    public StageManager(SpringFXMLLoader springFXMLLoader, Stage stage) {
        this.springFXMLLoader = springFXMLLoader;
        this.primaryStage = new Stage();
        
    }

    public void switchScene(final FxmlView view) {
        Parent viewRootNodeHierarchy = loadViewNodeHierarchy(view.getFxmlFile());
        show(viewRootNodeHierarchy, view.getTitle());
        
    }
    
    private void show(final Parent rootnode, String title) {
        Scene scene = prepareScene(rootnode);
      // scene.getStylesheets().add("/style/style.css");
		//scene.getStylesheets().add(getClass().getResource("/styles/Styles.css").toExternalForm());
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setTitle(title);
        primaryStage.setScene(scene);
        primaryStage.sizeToScene();
        primaryStage.centerOnScreen();
        primaryStage.setFullScreenExitKeyCombination(new KeyCodeCombination(KeyCode.E));
        primaryStage.setFullScreen(true);
        
        
        
        
        try {
            primaryStage.show();
        } catch (Exception exception) {
            logAndExit ("Unable to show scene for title" + title,  exception);
        }
    }
    
    private Scene prepareScene(Parent rootnode){
        Scene scene = primaryStage.getScene();
    	

        //if (scene == null) {
            scene = new Scene(rootnode);
      // }
        scene.setRoot(rootnode);
        return scene;
    }

    /**
     * Loads the object hierarchy from a FXML document and returns to root node
     * of that hierarchy.
     *
     * @return Parent root node of the FXML document hierarchy
     */
    public Parent loadViewNodeHierarchy(String fxmlFilePath) {
        Parent rootNode = null;
        try {
            rootNode = springFXMLLoader.load(fxmlFilePath);
            Objects.requireNonNull(rootNode, "A Root FXML node must not be null");
        } catch (Exception exception) {
            logAndExit("Unable to load FXML view" + fxmlFilePath, exception);
        }
        return rootNode;
    }
    
    
    private void logAndExit(String errorMsg, Exception exception) {
        LOG.error(errorMsg, exception, exception.getCause());
        Platform.exit();
    }

    public Stage switchScene2(ApplicationContext applicationContext, final FxmlView view) {

		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view.getFxmlFile()));
		fxmlLoader.setControllerFactory(applicationContext::getBean);
		
		Parent root1 = null;
		try {
			
			root1 = fxmlLoader.load();
			
		} catch (IOException e) {
			logAndExit("Unable to load FXML view" + view.getFxmlFile(), e);

		}
		Stage stage = new Stage();
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle(view.getTitle());
		stage.setScene(new Scene(root1));
		
		stage.show();
		
		return stage;
	}


}
