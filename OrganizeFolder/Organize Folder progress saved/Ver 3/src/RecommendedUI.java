import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class RecommendedUI {
	
	private BorderPane recommendedPane = new BorderPane();
	private Scene recommendedScene = new Scene(getRecommendedPain(), 500, 500);
	
	
	public Scene  getRecommendedScene() {
		return this.recommendedScene;
	}
	
	public BorderPane getRecommendedPain() {
		return this.recommendedPane;
	}

	public void runRecommendedUI(OrganizeFolderMainUI mainObj) {
		
		getRecommendedPain().getChildren().clear();


		
		mainObj.setIsChange(false);
		mainObj.setIsChangeTheme(false);
		


		mainObj.getPathFolder().setText("");
		

		
    	mainObj.runSetting(false, true, false, getRecommendedScene());
    	mainObj.getLightDarkMode().setOnAction(e -> changeMode(mainObj));
        
        
    	Button back = backOrHelpButtonProperty(0);
        Button help = backOrHelpButtonProperty(1);
        
        back.setOnAction(e -> backsetting(mainObj));
        help.setOnAction(e -> helpSetting(mainObj));
        
        Button chooseFolder = mainObj.buttonProperty("Choose");
        Button organize = mainObj.buttonProperty("Organize");
        
        chooseFolder.setOnAction(e -> chooseSetting(mainObj));
        
        mainObj.getPathFolder().setMaxWidth(350);
        mainObj.getPathFolder().setStyle("-fx-background-radius: 90px; -fx-alignment: center");
        mainObj.getPathFolder().setDisable(true);
        
        mainObj.getOpen().setOnAction(e -> openCheck(mainObj));
        mainObj.getSave().setOnAction(e -> mainObj.savedSetting());
        mainObj.getSaveAs().setOnAction(e -> saveAsCheck(mainObj));
        mainObj.getHelp().setOnAction(e -> mainObj.helpSetting());
        mainObj.getAbout().setOnAction(e -> mainObj.aboutSetting());
        
        
        	                
        FlowPane flowPaneButton = new FlowPane();
        flowPaneButton.getChildren().addAll(help, back);
        
        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().addAll(help, mainObj.getLightDarkMode(), back);
        hBoxTop.setAlignment(Pos.TOP_RIGHT);
        
        VBox vBoxTop = new VBox();
        vBoxTop.getChildren().addAll(mainObj.getMenuBar(), hBoxTop);
        
        VBox vBoxCenter = new VBox();
        vBoxCenter.getChildren().addAll(chooseFolder, mainObj.getPathFolder(), organize);
        vBoxCenter.setSpacing(10);
        vBoxCenter.setAlignment(Pos.CENTER);
        
       
        getRecommendedPain().setTop(vBoxTop);
        getRecommendedPain().setCenter(vBoxCenter);
        mainObj.getPrimaryStage().setScene(getRecommendedScene());


        if(mainObj.getFirstTime()) {
        	mainObj.getPrimaryStage().show();
        }
		mainObj.setTypeMode(1);
        
     
	}
	
	public Button backOrHelpButtonProperty(int whichButton) {
		Button btn = new Button();
		btn.setMinSize(50, 50);
		btn.setPrefSize(50, 50);
		btn.setStyle("-fx-background-color:transparent;");
		
		ImageView btnLogo = new ImageView();
		btnLogo.fitHeightProperty().bind(btn.heightProperty());
		btnLogo.fitWidthProperty().bind(btn.widthProperty());
		btnLogo.setPreserveRatio(true);
		if(whichButton == 0) {
			btnLogo.setImage(new Image("Resources/Images/Back logo.png"));
			btn.setGraphic(btnLogo);
		}
		else if(whichButton == 1) {
			btnLogo.setImage(new Image("Resources/Images/Help Logo.jpg"));
			btn.setGraphic(btnLogo);
		}
        
		return btn;
	}
	
	public void changeMode(OrganizeFolderMainUI mainObj){
		if(mainObj.getIsChangeTheme()) {
			mainObj.setIsChangeTheme(false);
		} else {
			mainObj.setIsChangeTheme(true);
		}
		mainObj.changeMode(false, false, getRecommendedScene());
	}
	
	
	public void openCheck(OrganizeFolderMainUI mainObj){
		if(!mainObj.getIsChange()) {
			mainObj.setIsChange(true);
		} 
		mainObj.openSetting(getRecommendedScene(), false);
		if(mainObj.getIntialMainTheme() == mainObj.getThemeMode()) {
//			System.out.println("check");
			mainObj.setIsChangeTheme(false);
		}
		else {
			mainObj.setIsChangeTheme(true);
		}
		
	}
	
	public void saveAsCheck(OrganizeFolderMainUI mainObj){
		if(!mainObj.getIsChange()) {
			mainObj.setIsChange(true);
		}
		String checkPath = mainObj.getRunFileSettings();
		mainObj.savedAsSetting(getRecommendedScene());
		
		if(!checkPath.equals(mainObj.getRunFileSettings() )) {
			mainObj.getPathFolder().setText("");
	     	mainObj.getPathFolder().setDisable(true);
		}
		
		if(mainObj.getIntialMainTheme() == mainObj.getThemeMode()) {
			System.out.println("check");
			mainObj.setIsChangeTheme(false);
		}
		else {
			mainObj.setIsChangeTheme(true);
		}
	}
	
	public void backsetting(OrganizeFolderMainUI mainObj) {
		
		mainObj.MainUI(mainObj, true);
	}
	
	public void helpSetting(OrganizeFolderMainUI mainObj) {
		Alert helpAlert = new Alert(AlertType.CONFIRMATION);
	    helpAlert.setTitle("Help");
	    helpAlert.setHeaderText("How to use recommended mode");
	    Text content = new Text("Recommended mode: Organize the files based on my recommended choice\\n\\n"+
	    						"Instruction: \n\n"+
	    						"1. Choose the path location (the folder) that you want to organize its filees\n\n"+
	    						"2. Press Organize to organize the folder's files based on my recommended choice");
	    content.setStyle("-fx-font-size:20;");
	    helpAlert.getButtonTypes().setAll(ButtonType.OK);
	    DialogPane helpDialog  = helpAlert.getDialogPane();
	    VBox vBox = new VBox();
	    content.setTextAlignment(TextAlignment.CENTER);
	    vBox.getChildren().add(content);
	    helpDialog.setContent(vBox);
	    helpDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	    if(mainObj.getThemeMode() == 1) {
	    	content.setStyle("-fx-fill: white;");
	    	helpDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	    	helpDialog.getStyleClass().add("Dark mode theme");

	    }
	    Image programIcon = new Image("Resources/Images/Program icon.png");
	    Stage stageIcon = (Stage) helpDialog.getScene().getWindow();
	    stageIcon.getIcons().add(programIcon);
	    	
	    helpAlert.showAndWait(); 	 	    	
	}
	
	public void chooseSetting(OrganizeFolderMainUI mainObj) {
		DirectoryChooser chooseFolder = new DirectoryChooser();
		chooseFolder.setTitle("Choose");
	 	 File chooseDialog = chooseFolder.showDialog(mainObj.getPrimaryStage());
	 	if(chooseDialog == null) {
	    	return;
	    }
	 	
	 	String choosePath = chooseDialog.toString();
	 	
	 	mainObj.getPathFolder().setText(choosePath);
	 	mainObj.getPathFolder().setDisable(false);
	 	mainObj.getPathFolder().setEditable(false);


	}
}
