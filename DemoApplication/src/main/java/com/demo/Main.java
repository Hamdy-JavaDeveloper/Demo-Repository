	package com.demo;
	
	import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.demo.cfg.StageManager;
import com.demo.view.FxmlView;

import javafx.application.Application;
import javafx.stage.Stage;
import com.demo.controller.AddNewItemController;
	
	@SpringBootApplication
	public class Main extends Application {
	
		protected ConfigurableApplicationContext springContext;
		protected StageManager stageManager;
	
		public static void main(final String[] args) {
			Application.launch(args);
		}
	
		@Override
		public void init() throws Exception {
			springContext = springBootApplicationContext();
		}
	
		@Override
		public void start(Stage stage) throws Exception {
			try {
				stageManager = springContext.getBean(StageManager.class, stage);
				System.out.println("springContext.getBean(StageManager.class, stage)------------------- running>;");
				displayInitialScene();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	
		@Override
		public void stop() throws Exception {
			
			System.out.println("Bye");
			springContext.close();
		}
	
		/**
		 * Useful to override this method by sub-classes wishing to change the first
		 * Scene to be displayed on startup. Example: Functional tests on main
		 * window.
		 */
		protected void displayInitialScene() {
			stageManager.switchScene(FxmlView.MAIN_WINDOW);
			
		}
	
		private ConfigurableApplicationContext springBootApplicationContext() {
			SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
			String[] args = getParameters().getRaw().stream().toArray(String[]::new);
			return builder.run(args);
		}
	
	}
