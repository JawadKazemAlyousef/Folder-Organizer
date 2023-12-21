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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;



public class StandardUI{
	
	private BorderPane standardPane = new BorderPane();
	private Scene standardScene = new Scene(getStanddardPain(), 500, 500);
	
	private Button back = backOrHelpButtonProperty(0);
    private Button help = backOrHelpButtonProperty(1);
    
    Button chooseFolder = buttonProperty("Choose");
    Button organize = buttonProperty("Organize");
	
	public Scene  getStandardScene() {
		return this.standardScene;
	}
	
	public BorderPane getStanddardPain() {
		return this.standardPane;
	}
	
	public Button getBackBtn() {
		return this.back;
	}
	
	public Button getHelpBtn() {
		return this.help;
	}
	
	public Button getChooseFolderBtn() {
		return this.chooseFolder;
	}
	
	public Button getOrganizeBtn() {
		return this.organize;
	}
	
	

	public void runStandardUI(OrganizeFolderMainUI mainObj) {
		
		mainObj.setTypeMode(0);
		
		getStanddardPain().getChildren().clear();
		
	
		mainObj.setIsChange(false);
		mainObj.setIsChangeTheme(false);
		

		
		
		mainObj.getPathFolder().setText("");
	
    	mainObj.runSetting(false, true, false, getStandardScene());
    	mainObj.getLightDarkMode().setOnAction(e -> changeMode(mainObj));
    	mainObj.getLanguageButton().setOnAction(e -> changeLanguage(mainObj, true));
        
    	
        
       
        
        getBackBtn().setOnAction(e -> backsetting(mainObj));
        getHelpBtn().setOnAction(e -> helpSetting(mainObj));
        
        
        
       
        changeLanguage(mainObj, false);
        
        
        getChooseFolderBtn().setOnAction(e -> chooseSetting(mainObj));
        
        mainObj.getPathFolder().setMaxWidth(350);
        mainObj.getPathFolder().setStyle("-fx-background-radius: 90px; -fx-alignment: center");
        mainObj.getPathFolder().setDisable(true);
        
        
        
        mainObj.getOpen().setOnAction(e -> openCheck(mainObj));
        mainObj.getSave().setOnAction(e -> mainObj.savedSetting());
        mainObj.getSaveAs().setOnAction(e -> saveAsCheck(mainObj));
        mainObj.getHelp().setOnAction(e -> mainObj.helpSetting());
        mainObj.getAbout().setOnAction(e -> mainObj.aboutSetting());
        
        
        		
               
       
        
        
        
        
   
        
        HBox hBoxTop = new HBox();
        hBoxTop.getChildren().addAll(getHelpBtn(), mainObj.getLightDarkMode(), mainObj.getLanguageButton(), getBackBtn());
        hBoxTop.setAlignment(Pos.TOP_RIGHT);
        
        VBox vBoxTop = new VBox();
        vBoxTop.getChildren().addAll(mainObj.getMenuBar(), hBoxTop);
        
        VBox vBoxCenter = new VBox();
        vBoxCenter.getChildren().addAll(getChooseFolderBtn(), mainObj.getPathFolder(),  getOrganizeBtn());
        vBoxCenter.setSpacing(10);
        vBoxCenter.setAlignment(Pos.CENTER);
        
       
        getStanddardPain().setTop(vBoxTop);
        getStanddardPain().setCenter(vBoxCenter);

        mainObj.getPrimaryStage().setScene(getStandardScene());
		
	}
	
	public Button buttonProperty(String title) {
    	Button btn = new Button();
    	
    	
	    	btn.setText(title);
	    	btn.setMinSize(150, 50);
	        btn.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");
    	
    	return btn;
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
		mainObj.changeMode(false, false, getStandardScene());
	}
	
	public void changeLanguage(OrganizeFolderMainUI mainObj, boolean byButton) {
		if(byButton) {
			mainObj.changeLanguage();
		}
		if(mainObj.getLanguageMode() == 0) {
			chooseFolder.setText("Choose");
        	organize.setText("Organize");
		}
		else if(mainObj.getLanguageMode() == 1) {
			chooseFolder.setText("اختر الملجد");
        	organize.setText("نظم");
		}
	}
	
	public void openCheck(OrganizeFolderMainUI mainObj){
		if(!mainObj.getIsChange()) {
			mainObj.setIsChange(true);
		} 
		
		int checkLanguage = mainObj.getLanguageMode();
//		System.out.println(checkLanguage);
		mainObj.openSetting(getStandardScene(), false);
//		System.out.println(mainObj.getLanguageMode());
		if(mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if(mainObj.getIntialMainTheme() == mainObj.getThemeMode()) {
			System.out.println("check");
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
//		System.out.println(checkPath);
		mainObj.savedAsSetting(getStandardScene());
//		System.out.println(mainObj.getRunFileSettings());
		
		if(!checkPath.equals(mainObj.getRunFileSettings() )) {
			mainObj.getPathFolder().setText("");
	     	mainObj.getPathFolder().setDisable(true);
		}
		
		if(mainObj.getIntialMainTheme() == mainObj.getThemeMode()) {
//			System.out.println("check");
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
	    helpAlert.setHeaderText("How to use standard mode");
	    Text content = new Text("Standard mode: Organize the files based on their extension format\n\n"+
				 "Instruction: \n\n"+
				 "1. Choose the path location (the folder) that you want to organize its filees\n\n"+
				 "2. Press Organize to organize the folder's files based on their extension format");
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
