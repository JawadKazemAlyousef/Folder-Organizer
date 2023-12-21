import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Optional;
import java.util.Scanner;




public class OrganizeFolderMainUI extends FolderOrganizerDriver{

	

	
	
	// Integer key for Light (0) and Dark (1) theme 
	private final double VERSION = 1.0;
	private int themeMode = 0;
	private String[] themes = {"Light", "Dark"};
	
	// Integer keys for Standard (0) and Recommended (1) and Customized (2) type modes
	private int typeMode = 0;
	private String[] types = {"Standard", "Recommended", "Customized"};
	
	//Integer keys for English (0) and Arabic (1) Languages
	private int languageMode = 0;
	private String[] languages = {"English", "Arabic"};
	
	
	private String runFileSettings;
	private boolean firstTime = true;
	private Stage primaryStage = new Stage();
	private BorderPane mainPane = new BorderPane();
	private Scene mainScene = new Scene(getMainPain(), 500, 500);
	
	private Button standardBtn = buttonProperty("Standard");
    private Button recommendedBtn = buttonProperty("Recommended");
    private Button customizedBtn = buttonProperty("Customized");
	
	private Button lightDarkModeBtn = themeOrLanguageModeButtonProperty();
	private Button languageBtn = themeOrLanguageModeButtonProperty();
	
    private Menu file = new Menu("File");
    private MenuBar menuBar = new MenuBar();

	private MenuItem open = menuItemProperty(0);
	private MenuItem save = menuItemProperty(1);
	private MenuItem saveAs = menuItemProperty(2);
	private MenuItem help = menuItemProperty(3);
	private MenuItem about = menuItemProperty(4);
	
	private TextField pathFolder = new TextField();
	
	//for open dialog first time
	private boolean isOpen = true;
	
	public boolean getIsOpen() {
		return this.isOpen;
		
	}
	public void setIsOpen(boolean isOpen) {
		this.isOpen = isOpen;
		
	}
	
	//for consistent changes of theme, saved content between various pages in one run 
	private boolean isChangeTheme = false;
	private int inittialSavedSetting = 0;
	private boolean successfullyOpen = true;
	private boolean successfullySaveAs = true;
	
	
	public boolean getIsChangeTheme() {
		return this.isChangeTheme;
	}
	
	public void setIsChangeTheme(boolean isChangeTheme) {
		this.isChangeTheme = isChangeTheme;
	}
	
	public int getInitialSavedSetting() {
		return this.inittialSavedSetting;
	}
	
	
	public void setInitialSavedSetting(int mode) {
		this.inittialSavedSetting = mode;
	}
	
	public boolean getSuccessfullyOpen() {
		return this.successfullyOpen;
	}
	
	public void setSuccessfullyOpen(boolean open) {
		this.successfullyOpen = open;
	}
	public boolean getSuccessfullySaveAs() {
		return this.successfullySaveAs;
	}
	
	public void setSuccessfullySaveAs(boolean saveAs) {
		this.successfullySaveAs = saveAs;
	}
	
	//
	
	public double getVersion() {
		return this.VERSION;
	}
	
	public int  getThemeMode() {
		return this.themeMode;
	}
	
	public void setThemeMode(int mode) {
		this.themeMode = mode;
	}
	
	public String[] getThemeNames( ) {
		return this.themes;
	}
	
	public int  getTypeMode() {
		return this.typeMode;
	}
		
	public void setTypeMode(int mode) {
		this.typeMode = mode;
	}
	
	public String[] getTypesNames( ) {
		return this.types;
	}
	
	public int  getLanguageMode() {
		return this.languageMode;
	}
		
	public void setLanguageMode(int mode) {
		this.languageMode = mode;
	}
	
	public String[] getLanguageNames( ) {
		return this.languages;
	}
	
	public String  getRunFileSettings() {
		return this.runFileSettings;
	}
	
	public void setRunFileSettings(String runFileSettings) {
		this.runFileSettings = runFileSettings;
	}
	
	public boolean getFirstTime() {
		return this.firstTime ;
	}
	
	public void setFirstTime(boolean firstTime) {
		 this.firstTime = firstTime;
	}
	
	
	public Stage  getPrimaryStage() {
		return this.primaryStage;
	}
	
	public Scene  getMainScene() {
		return this.mainScene;
	}
	
	public BorderPane getMainPain() {
		return this.mainPane;
	}
	
	
	// Buttons
	public Button getLightDarkMode() {
		return this.lightDarkModeBtn;
	}
	
	public Button getLanguageButton() {
		return this.languageBtn;
	}
	
	public Button getStandardBtn() {
		return this.standardBtn;
	}
	
	public Button getRecommendedBtn() {
		return this.recommendedBtn;
	}
	
	public Button getCustomizedBtn() {
		return this.customizedBtn;
	}
	
	
	public Menu getFile() {
		return this.file;
	}
	
	public MenuBar getMenuBar() {
		return this.menuBar;
	}
	public MenuItem getOpen() {
		return this.open;
	}
	
	public MenuItem getSave() {
		return this.save;
	}
	
	public MenuItem getSaveAs() {
		return this.saveAs;
	}
	
	public MenuItem getHelp() {
		return this.help;
	}
	
	public MenuItem getAbout() {
		return this.about;
	}
	
	public TextField getPathFolder(){
		return this.pathFolder;
	}
	
	
	
	
	
		
	// main UI
    public void MainUI(OrganizeFolderMainUI mainObj, boolean isBack) {
    	// to open saved settings in default path
    	//System.out.println(mode);
    	//System.out.println(getThemeMode());
    	
    	
         
      
        if(isBack) {
        		
             	 setTypeMode(0);
	             HBox hBoxCenter = new HBox();
	             hBoxCenter.setSpacing(15);
	             hBoxCenter.getChildren().addAll(getStandardBtn(), getRecommendedBtn(), getCustomizedBtn());
	             hBoxCenter.setAlignment(Pos.CENTER);
	                 
	             HBox hBoxTopButtons = new HBox();
	             hBoxTopButtons.getChildren().addAll(getLightDarkMode(), getLanguageButton());
	             hBoxTopButtons.setAlignment(Pos.TOP_RIGHT);
	                
	             VBox vhBoxTop = new VBox();
	             vhBoxTop.getChildren().addAll(getMenuBar(), hBoxTopButtons);
	             vhBoxTop.setAlignment(Pos.TOP_RIGHT);
	                
	             getMainPain().setCenter(hBoxCenter);
	             getMainPain().setTop(vhBoxTop);
	             
                  if(!getIsChangeTheme()){
 //               	 System.out.println(2);
                 	 getMainPain().getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
                 	 getMainPain().getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());         	
                  }
  
	             
             	 getPrimaryStage().setScene(getMainScene());
        	 }
         
	         
	         else if(getFirstTime()){
	        	 
		            getStandardBtn().setOnAction(e -> typeModeChoice(0, mainObj));
		            getRecommendedBtn().setOnAction(e -> typeModeChoice(1, mainObj));
		            getCustomizedBtn().setOnAction(e -> typeModeChoice(2, mainObj));
		                 
		            getLightDarkMode().setOnAction(e -> changeMode(false, false, getMainScene()));
		            getLanguageButton().setOnAction(e -> changeLanguage());
		         		
		         	getOpen().setOnAction(e -> openSetting(getMainScene(), false, mainObj));
		            getSave().setOnAction(e -> savedSetting());
		            getSaveAs().setOnAction(e -> savedAsSetting(getMainScene(), mainObj));
		            getHelp().setOnAction(e -> helpSetting());
		            getAbout().setOnAction(e -> aboutSetting());
	             
	        	 
	              	runSetting(false,  getMainScene());
	              	
	              	changeLanguage();
	              	
	              
	             
	              	getFile().getItems().addAll(getOpen(), getSave(),  getSaveAs(), getHelp(), getAbout());
	                         
	              	getMenuBar().getMenus().add(file);
	              	
	              	HBox hBoxCenter = new HBox();
	                hBoxCenter.setSpacing(15);
	                hBoxCenter.getChildren().addAll(getStandardBtn(), getRecommendedBtn(), getCustomizedBtn());
	                hBoxCenter.setAlignment(Pos.CENTER);
	                 
	                HBox hBoxTopButtons = new HBox();
	                hBoxTopButtons.getChildren().addAll(getLightDarkMode(), getLanguageButton());
	                hBoxTopButtons.setAlignment(Pos.TOP_RIGHT);
	                
	                VBox vhBoxTop = new VBox();
	                vhBoxTop.getChildren().addAll(getMenuBar(), hBoxTopButtons);
	                vhBoxTop.setAlignment(Pos.TOP_RIGHT);
	                
	                getMainPain().setCenter(hBoxCenter);
	                getMainPain().setTop(vhBoxTop);
	                 
	                Image programIcon = new Image("Resources/Images/Program icon.png");
	                
	               
	                getPrimaryStage().setResizable(false);
	                getPrimaryStage().getIcons().add(programIcon);
	                 
	                
	                
	            	if(getFirstTime() && getTypeMode() == 1) {
	            		typeModeChoice(getTypeMode(), mainObj);
		              	setFirstTime(false);       		
	            	} else if(getFirstTime() && getTypeMode() == 2) {
	            		typeModeChoice(getTypeMode(), mainObj);
		              	setFirstTime(false);       		

	            	}else {
		              	 setFirstTime(false);       		
		              	 getPrimaryStage().setScene(getMainScene()); 	
		                 getPrimaryStage().show();
	            	}
	            	
	     	}
	        else if(!getFirstTime()) {
	        	
	     		if(getTypeMode() == 1) {
            		typeModeChoice(getTypeMode(), mainObj);
            	} else if(getTypeMode() == 2) {
            		typeModeChoice(getTypeMode(), mainObj);      		
            	}else {
            		
            	 HBox hBoxCenter = new HBox();
   	             hBoxCenter.setSpacing(15);
   	             hBoxCenter.getChildren().addAll(getStandardBtn(), getRecommendedBtn(), getCustomizedBtn());
   	             hBoxCenter.setAlignment(Pos.CENTER);
   	                 
   	             HBox hBoxTopButtons = new HBox();
   	             hBoxTopButtons.getChildren().addAll(getLightDarkMode(), getLanguageButton());
   	             hBoxTopButtons.setAlignment(Pos.TOP_RIGHT);
   	                
   	             VBox vhBoxTop = new VBox();
   	             vhBoxTop.getChildren().addAll(getMenuBar(), hBoxTopButtons);
   	             vhBoxTop.setAlignment(Pos.TOP_RIGHT);
   	                
   	             getMainPain().setCenter(hBoxCenter);
   	             getMainPain().setTop(vhBoxTop);
   	             
                     if(!getIsChangeTheme()){
    //               	 System.out.println(2);
                    	 getMainPain().getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
                    	 getMainPain().getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());         	
                     }
     
   	             
                getPrimaryStage().setScene(getMainScene());
            	}
	     		 
	     	}
        }
       
    	
    
    
    
    
	public Button buttonProperty(String title) {
    	Button btn = new Button();
    	
    	
	    	btn.setText(title);
	    	btn.setMinSize(150, 50);
	        btn.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");
    	
    	return btn;
    }
	
	public void typeModeChoice(int typeModeChoice,  OrganizeFolderMainUI mainObj) {
		if(typeModeChoice == 0) {
			StandardUI standdardObj = new StandardUI();
			standdardObj.runStandardUI(mainObj);

			
		}
		else if(typeModeChoice == 1) {
			RecommendedUI recommendedObj = new RecommendedUI();
			recommendedObj.runRecommendedUI(mainObj);
					
		}
		else if(typeModeChoice == 2) {
			CustomizedUI customizedObj = new CustomizedUI();
			customizedObj.runCustomizedUI(mainObj);	
		
		}
	}
	
    public MenuItem menuItemProperty(int menuChoice) {
    	if(menuChoice == 0) {
    		MenuItem openMenu = new MenuItem("Open");
    		return openMenu;
    	}
    	else if(menuChoice == 1) {
    		MenuItem saveMenu = new MenuItem("Save");
    		return saveMenu;
    	}
    	else if(menuChoice == 2) {
    		MenuItem saveAsMenu = new MenuItem("Save As");
    		return saveAsMenu;
    	}
    	else if(menuChoice == 3) {
    		MenuItem help = new MenuItem("Help");
    		return help;
    	}
    	else {
    		MenuItem about = new MenuItem("About");
    		return about;

    	}
		
    }
    
    public Button themeOrLanguageModeButtonProperty() {
    	Button themeModeBtn = new Button();
        themeModeBtn.setMinSize(50, 50);
		themeModeBtn.setPrefSize(50, 50);
		themeModeBtn.setStyle("-fx-background-color:transparent;");
		return themeModeBtn;
    }
    
    public void changeMode(boolean byOpenSave, boolean sameInfo,  Scene scene){
    	if(!getFirstTime() && getThemeMode()==0 && !byOpenSave ) {
    		setThemeMode(1);
    	}
    		
    	else if(!getFirstTime() && getThemeMode()==1 && !byOpenSave ) {
    		setThemeMode(0);
    	}
    	
    	if(byOpenSave) {
//    		System.out.println("Test");
        	ImageView darkLightLogo = new ImageView();
        	darkLightLogo.fitHeightProperty().bind(getLightDarkMode().heightProperty());
    		darkLightLogo.fitWidthProperty().bind(getLightDarkMode().widthProperty());
    		darkLightLogo.setPreserveRatio(true);
        	if(getThemeMode()==0 && !sameInfo) {
//        		System.out.println("Test1");
        		darkLightLogo.setImage(new Image("Resources/Images/Dark mode.png"));
        		getLightDarkMode().setGraphic(darkLightLogo);
        		scene.getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
        	}	
        	else if(getThemeMode()==1 && !sameInfo){
//        		System.out.println("Test2");
        		darkLightLogo.setImage(new Image("Resources/Images/Light mode.png"));
        		getLightDarkMode().setGraphic(darkLightLogo);
        		scene.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
        	}
    	}
    	else {
//    		System.out.println("Check");
        	ImageView darkLightLogo = new ImageView();
        	darkLightLogo.fitHeightProperty().bind(getLightDarkMode().heightProperty());
    		darkLightLogo.fitWidthProperty().bind(getLightDarkMode().widthProperty());
    		darkLightLogo.setPreserveRatio(true);
        	if(getThemeMode()==0) {
        		darkLightLogo.setImage(new Image("Resources/Images/Dark mode.png"));
        		getLightDarkMode().setGraphic(darkLightLogo);
        		scene.getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
        	}	
        	else if(getThemeMode()==1){
        		darkLightLogo.setImage(new Image("Resources/Images/Light mode.png"));
        		getLightDarkMode().setGraphic(darkLightLogo);
        		scene.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
        	}
    	}

   
    }
    
    public void changeLanguage() {
    	if(!getFirstTime() && getLanguageMode()==0) {
    		setLanguageMode(1);
    	}
    		
    	else if(!getFirstTime() && getLanguageMode()==1) {
    		setLanguageMode(0);
    	}
    	
    	ImageView languageLogo = new ImageView();
    	languageLogo.fitHeightProperty().bind(getLightDarkMode().heightProperty());
		languageLogo.fitWidthProperty().bind(getLightDarkMode().widthProperty());
		languageLogo.setPreserveRatio(true);
		
		if(getLanguageMode() == 0) {
			languageLogo.setImage(new Image("Resources/Images/Arabic logo.png"));
    		getLanguageButton().setGraphic(languageLogo);
    		
    		getPrimaryStage().setTitle("Folder Organizer");
    		
    		getStandardBtn().setText("Standard");
            getRecommendedBtn().setText("Recommended");
            getCustomizedBtn().setText("Customized");
           
            getFile().setText("File");
            
            getOpen().setText("Open");
            getSave().setText("Save");
            getSaveAs().setText("Save As");
            getHelp().setText("Help");
            getAbout().setText("About");
            
            getPrimaryStage().hide();
            getPrimaryStage().show();
            
    		}	
    	else if(getLanguageMode() == 1){
    		
    		languageLogo.setImage(new Image("Resources/Images/English logo.png"));
    		getLanguageButton().setGraphic(languageLogo);
    		
    		getPrimaryStage().setTitle("منظم الملفات");

    		getStandardBtn().setText("العادي");
            getRecommendedBtn().setText("الموصى");
            getCustomizedBtn().setText("المخصص");
           
            getFile().setText("ملف");
            
            getOpen().setText("فتح");
            getSave().setText("حفظ");
            getSaveAs().setText("حقظ كـ");
            getHelp().setText("مساعدة");
            getAbout().setText("حول");
            
            getPrimaryStage().hide();
            getPrimaryStage().show();
    	}
    }
    
    public int[] fileValues(Scanner scan){
    	 	int count = 0;
	 	    int[] fileValues = {0, 0, 1, 0}; //{theme Mode, type Mode, error in content, language type}
	 	    
	 	    while(scan.hasNext()) {
	 	    	if(scan.hasNextInt() && count == 0) {
	 	    		fileValues[0] =scan.nextInt();
	 	    		++count;
	 	    	}
	 	    	else if(scan.hasNextInt() && count == 1) {
	 	    		fileValues[1] = scan.nextInt();
	 	    		++count;
	 	    		
	 	    	} else if(scan.hasNextInt() && count ==2) {
	 	    		fileValues[3] = scan.nextInt();
	 	    		++count;
	 	    		if(fileValues[0] == 0 || fileValues[0] == 1)  {
	 	    			if(fileValues[1] == 0 || fileValues[1] == 1 || fileValues[1] == 2) {
	 	    				if(fileValues[3] == 0 || fileValues[3] == 1)
	 	    					fileValues[2] = 0;
	 	    			}
	 	    		}
	 	    		else{
		 	    		fileValues[2] = 1;

	 	    		}
	 	    	}
	 	    	else if(count == 3) {
	 	    		fileValues[2] = 1;
	 	    		break;
	 	    	}
	 	    	else {
	 	    		
	 	    		scan.next();
	 	    	}
	 	   
	 	    }
	 	    return fileValues;
    }

   
    public void runSetting(boolean sameInfo, Scene scene) {
	    try {
	    	if(getFirstTime()) {
	    		String runPath = "";
	    		if(getIsOpen()) {
	    			setIsOpen(false);
	    			
	    			 File recommendedPath = new File("src/Resources/Mode settings");
	    	 	 	 FileChooser openController = new FileChooser();
	    	 	 	 openController.setTitle("Open | فتح");
	    	 	 	 openController.getExtensionFilters().addAll(new ExtensionFilter("fo", "*.fo*"));;
	    	 	 	 openController.setInitialDirectory(recommendedPath);
	    	 	 	 Image programIcon = new Image("Resources/Images/Program icon.png");
	    	 		
		             getPrimaryStage().setTitle("");
		             getPrimaryStage().setResizable(false);
		             getPrimaryStage().getIcons().add(programIcon);
		             
		             BorderPane forOpenLogo = new BorderPane();
		             Scene  forOpenLogoScene = new Scene(forOpenLogo, 500, 500);
		 
		             
		             
		             getPrimaryStage().setScene(forOpenLogoScene);
		             getPrimaryStage().show();
		             
	    	 	 	 File openDialog = openController.showOpenDialog(getPrimaryStage());
	    	 	 	 if(openDialog == null) {
	    		    	runPath = "src/Resources/Mode settings/Saved settings.fo";
	    		    	File runSetting = new File(runPath);
 	    	   			if(!runSetting.isFile()) {
 	    	   				setRunFileSettings(runSetting.getAbsolutePath());
 	    	   				savedSetting();
 	    	   			} else {
 	    	   				Scanner scan= new Scanner(runSetting);
 	    	   				int[] fileValues = fileValues(scan);
 	    	   				if(fileValues[2] == 1) {
 	    	   					setRunFileSettings(runSetting.getAbsolutePath());
 	    	   					savedSetting();
 	    	   				} else {
 	    	   					setRunFileSettings(runSetting.getAbsolutePath());
 	    	   					setTypeMode(fileValues[1]);
 	    	   					setThemeMode(fileValues[0]);
 	    	   					setLanguageMode(fileValues[3]);
 	    	   					setInitialSavedSetting(fileValues[1]);
 	    	   				}
 	    	   			}
 	    	   			
	    	 	 	 } else {
	    		    	runPath = openDialog.getAbsolutePath();
	    		    	
	    		    	File runSetting = new File(runPath);
//			    	    //System.out.println(openSetting.getAbsolutePath());
//			    	    //System.out.println(openSetting.canRead());
			    		if(!runSetting.isFile()) {
			 	    	    setRunFileSettings(runSetting.getAbsolutePath());
			    			savedSetting();
			    		}else {
			    			Scanner scan= new Scanner(runSetting);
			 	    	   	int[] fileValues = fileValues(scan);
			 	    	   	if(fileValues[2] == 1) {
			 	    	   		Alert confirmAlert = new Alert(AlertType.CONFIRMATION);
			 	    	   		confirmAlert.setTitle("Confirm | تأكيد");
			 	    	   		confirmAlert.setHeaderText("");
			 	    	   		DialogPane confirmDialog  = confirmAlert.getDialogPane();
	
			 	    	   		Text content = new Text("Are you sure? : Your chosen saved file is invalid saved file!\n\n"+
			 	    	   								"Do you want to replace your chosen file's content to the defualt settings\n\n"+
			 	    	   								"Or open the default saved file?\n\n"+
			 	    	   								"_____________________________________________________________________________________");
			 	    	   		content.setStyle("-fx-font-size:20; direction: rtl;");
			 	    	   		
			 	    	   		Text contentArabic = new Text("هل أنت متأكد: تخزينتك المختارة غير صالحة!\n\n "+
			 	    	   									  "هل تريد أنت تستبدل محتوى هذه التخزينة بالإعدادات الافتراضية\n\n"+
			 	    	   									  "أو تريد أن تفتح التخزينة الافتراضية؟");
			 	    	   		contentArabic.setStyle("-fx-font-size:20; direction: ltr;");
			 	    	   		
			 	 	    	
			 	    	   		VBox vBox = new VBox();
			 	    	   		content.setTextAlignment(TextAlignment.CENTER);
			 	    	   		vBox.getChildren().addAll(content, contentArabic);
			 	    	   		vBox.setAlignment(Pos.BOTTOM_CENTER);
			 	    	   		confirmDialog.setContent(vBox);
			 	    	   		ButtonType openButton = new ButtonType("Yes | نعم");
			 	    	   		ButtonType cancelButton = new ButtonType("No | لا");
			 	    	   		
			 	    	   		confirmAlert.getButtonTypes().setAll(openButton, cancelButton);
			 	    	   		
				 	    	   	Window window = confirmAlert.getDialogPane().getScene().getWindow();
			 	    	   		
				 	    	   	ButtonBar buttonbar = (ButtonBar) confirmAlert.getDialogPane().lookup(".button-bar");
				 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
				 	    	   	
			 	    	   		window.setOnCloseRequest(e -> {
			 	    	   			try {
			 	    	   				confirmAlert.close();
				 	    	   			String runPathC = "src/Resources/Mode settings/Saved settings.fo";
				 	    	   			File runSettingC = new File(runPathC);
				 	    	   			if(!runSettingC.isFile()) {
			 	    	   				setRunFileSettings(runSettingC.getAbsolutePath());
			 	    	   				savedSetting();
				 	    	   			} else {
				 	    	   				Scanner scanC= new Scanner(runSettingC);
				 	    	   				int[] fileValuesC = fileValues(scanC);
				 	    	   				if(fileValuesC[2] == 1) {
				 	    	   					setRunFileSettings(runSettingC.getAbsolutePath());
				 	    	   					savedSetting();
				 	    	   				} else {
			 	    	   					setRunFileSettings(runSettingC.getAbsolutePath());
			 	    	   					setTypeMode(fileValuesC[1]);
			 	    	   					setThemeMode(fileValuesC[0]);
			 	    	   					setLanguageMode(fileValuesC[3]);
			 	    	   					setInitialSavedSetting(fileValuesC[1]);
				 	    	   				}
				 	    	   			}
				 	    	   			
			 	    	   			}
			 	    	   			catch(IOException ec) {
			 	    	   			System.out.println("An error occurred.");
			 	    	   			ec.printStackTrace();
			 	    	   			}
			 	    	   			
			 	    	   			
			 	    	   		});
			 	    	   		Stage stageIcon = (Stage) confirmAlert.getDialogPane().getScene().getWindow();
			 	    	   		stageIcon.getIcons().add(programIcon);
			 	    	   		Optional<ButtonType> result = confirmAlert.showAndWait();
			 	    	   		
			 	    	   		if(result.isPresent() && result.get() == openButton) {
			 	    	   			setRunFileSettings(runSetting.getAbsolutePath());
			 	    	   			savedSetting();
			 	    	   		} else {
			 	    	   			runPath = "src/Resources/Mode settings/Saved settings.fo";
			 	    	   			runSetting = new File(runPath);
			 	    	   			if(!runSetting.isFile()) {
			 	    	   				setRunFileSettings(runSetting.getAbsolutePath());
			 	    	   				savedSetting();
			 	    	   			} else {
			 	    	   				scan= new Scanner(runSetting);
			 	    	   				fileValues = fileValues(scan);
			 	    	   				if(fileValues[2] == 1) {
			 	    	   					setRunFileSettings(runSetting.getAbsolutePath());
			 	    	   					savedSetting();
			 	    	   				} else {
			 	    	   					setRunFileSettings(runSetting.getAbsolutePath());
			 	    	   					setTypeMode(fileValues[1]);
			 	    	   					setThemeMode(fileValues[0]);
			 	    	   					setLanguageMode(fileValues[3]);
			 	    	   					setInitialSavedSetting(fileValues[1]);
			 	    	   				}
			 	    	   			}
			 	    	   			
			 	    	   			
			 	    	   		}
	   
			 	    	   	}
			 	    	   	else {
				 	    	    setRunFileSettings(runSetting.getAbsolutePath());
				 	    	    setTypeMode(fileValues[1]);
				 	    	    setThemeMode(fileValues[0]);
				 	    	    setLanguageMode(fileValues[3]);
				 	    	    setInitialSavedSetting(fileValues[1]);
			 	    	   	}
			 	    	    
			    		}
	    		    	
	    	 	 	 }
	    			
	    			
	    		}
	    		changeMode(false, false, scene); 

	    	    
	        } 
	    	else {
	    		changeMode(true, sameInfo, scene);
	        }
	    }catch (IOException e) {
	   	  System.out.println("An error occurred.");
	   	  e.printStackTrace();
	    }	
   }
    	
    // Arabic + Arabic Buttons in all pop up windows ( open in startup and confirmation) 
    public void openSetting(Scene scene, boolean bySaveAs, OrganizeFolderMainUI mainObj){
    	 try {
    		 String openPath = "";
    		 if(!bySaveAs) {
    			 File recommendedPath = new File("src/Resources/Mode settings");
    	 	 	 FileChooser openController = new FileChooser();
    	 	 	 openController.setTitle("Open");
    	 	 	 openController.getExtensionFilters().addAll(new ExtensionFilter("fo", "*.fo*"));;
    	 	 	 openController.setInitialDirectory(recommendedPath);
    	 	 	 File openDialog = openController.showOpenDialog(getPrimaryStage());
    	 	 	 if(openDialog == null) {
    	 	 		setSuccessfullyOpen(false);
    		    	return;
    		    }
    		    	
    	 	 	openPath = openDialog.toString();
    		 }
    		 
    		 else {
    			 openPath = getRunFileSettings();
    		 }
 	    	
 	 	    //System.out.println(openPath);
 	    	File openSetting = new File(openPath);
// 	 	    System.out.println(openSetting.getAbsolutePath());
 	 	    //System.out.println(openSetting.canRead());
 	 	    Scanner scan= new Scanner(openSetting);
 	 	    
 	 	   
 	 	    int[] fileValues = fileValues(scan);
 	 	    
 	 	    if(fileValues[2] == 1) {
 	 	    	Alert errorAlert = new Alert(AlertType.ERROR);
 	 	    	Text content = new Text("");
 	 	    	if(getLanguageMode() == 0) {
 	 	    		errorAlert.setTitle("Error!");
 	 	 	    	errorAlert.setHeaderText("");
 	 	 	    	content = new Text("Error: Invalid Saved file!");
 	 	 	    	content.setStyle("-fx-font-size:20;");
 	 	 	    
	 	  	   		
		 	  	   	ButtonType OkButton = new ButtonType("OK");
	 	  	   		errorAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = errorAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> errorAlert.close());
	 	  	   		
	 	 	 	   	DialogPane errorDialog  = errorAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    errorDialog.setContent(vBox);
	 	 		    errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
	 	 		    
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 	 		    if(getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	errorDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    Image programIcon = new Image("Resources/Images/Program icon.png");
	 	 		    Stage stageIcon = (Stage) window;
	 	 		    stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   		Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   		if(result.isPresent() && result.get() == OkButton) {
	 	  	   			errorAlert.close();
	 	  	   			
	 	  	   			
	 	  	   		}
 	 	    	} 
 	 	    	else if(getLanguageMode() == 1) {
 	 	    		errorAlert.setTitle("!خطأ");
 	 	 	    	errorAlert.setHeaderText("");
 	 	 	    	content = new Text("خطأ: التخزينة غير صالحة!");
 	 			    content.setStyle("-fx-font-size:20; direction: ltr;");
 	 			    
	 	 			ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		errorAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = errorAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> errorAlert.close());
	 	  	   		
	 	 	 	   	DialogPane errorDialog  = errorAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    errorDialog.setContent(vBox);
	 	 		    errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	errorDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    Image programIcon = new Image("Resources/Images/Program icon.png");
	 	 		    Stage stageIcon = (Stage) window;
	 	 		    stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   		Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   		if(result.isPresent() && result.get() == OkButton) {
	 	  	   			errorAlert.close();
	 	  	   			
	 	  	   			
	 	  	   		}
 	 			    
 	 	    	}
	 	 		setSuccessfullyOpen(false); 	    	
 	 	    	return;
 	 	    }

 	 	
 	 		setSuccessfullyOpen(true);
 	 	    boolean sameInfo = false;
	     	setLanguageMode(fileValues[3]);
	     	setInitialSavedSetting(fileValues[1]);
	     	
	     	setFirstTime(true);
	     	changeLanguage();
	     	setFirstTime(false);
 	 	   
 			if(!openPath.equals(getRunFileSettings())) {
 					getPathFolder().setText("");
   	 	     		getPathFolder().setDisable(true);
   	 	     		setRunFileSettings(openPath);
   	 	     	
 		 	 	   if(fileValues[0] != getThemeMode()) {
 		 	 		   setThemeMode(fileValues[0]);
	 	 			   
 		 	 		   if(fileValues[1] != getTypeMode()) {
 		 	 			   setTypeMode(fileValues[1]);
 		 	 			   

	 		 	 		   runSetting(sameInfo, scene);
 		 	 			   MainUI(mainObj, false);
 		 	 		   }else {
 	 		 	 		   runSetting(sameInfo, scene);
 		 	 		   }
 		 	 	    }
 		 	 	    else {
 		 	 	       sameInfo = true;
 		 	 	       if(fileValues[1] != getTypeMode()) {
		 	 			   setTypeMode(fileValues[1]);
		 	 			   MainUI(mainObj, false);
		 	 		   }else {
	 		 	 		   runSetting(sameInfo, scene);
		 	 		   }

 		 	 	    }

 				
	 		} else {
	 			if(fileValues[0] != getThemeMode()) {
		 	 		   setThemeMode(fileValues[0]);
		 	 		   
	 	 			   
		 	 		   if(fileValues[1] != getTypeMode()) {
		 	 			   setTypeMode(fileValues[1]);
		 	 			   
 	
	 		 	 		   runSetting(sameInfo, scene);
		 	 			   MainUI(mainObj, false);
		 	 		   }else {
	 		 	 		   runSetting(sameInfo, scene);
		 	 		   }
		 	 	    }
	 			else {
		 	 	       sameInfo = true;
		 	 	       if(fileValues[1] != getTypeMode()) {
		 	 	    	   setTypeMode(fileValues[1]);
		 	 			   MainUI(mainObj, false);
		 	 		   }else {
	 		 	 		   runSetting(sameInfo, scene);
		 	 		   }

		 	 	    }
	 		}
 			
	 	 	    
 	 	    

 		    
 		    System.out.println("Open file successfully");
 	      } catch (IOException e) {
 	    	  System.out.println("An error occurred.");
 	    	  e.printStackTrace();
	      }
    }
    
    // fo means folder organizer and ifoi means important folder organizer information
    public void savedSetting() {
    try {
    	FileWriter writer = new FileWriter(getRunFileSettings());
    	System.out.println(getTypeMode());
        writer.write("Theme: "+getThemeNames()[getThemeMode()]+"\nTheme mode: "+getThemeMode()+
        			 "\nType: "+getTypesNames()[getTypeMode()]+"\nType mode: "+getTypeMode()+
        			 "\nLanguage: "+getLanguageNames()[getLanguageMode()]+"\nLanguage mode: "+getLanguageMode());
        
       
        writer.close(); 
        
        setInitialSavedSetting(getTypeMode());
        
        String savedInfoPath = getRunFileSettings().replace(".fo", ".ifoi");
        File savedInfoPathFile = new File(savedInfoPath);
        
        if(getTypeMode() ==  0 || getTypeMode() == 1) {
        	if(savedInfoPathFile.exists()) {
        		savedInfoPathFile.delete();
        	}
        } 
        else if(getTypeMode() == 2) {
        	if(!savedInfoPathFile.exists()) {
                savedInfoPathFile.createNewFile();
        	}
        }
        
        
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    
   public void savedAsSetting(Scene scene, OrganizeFolderMainUI mainObj) {
	    try {
	    	File recommendedPath = new File("src/Resources/Mode settings");
	    	FileChooser savedController = new FileChooser();
	    	savedController.setTitle("Save As");
	    	savedController.getExtensionFilters().addAll(new ExtensionFilter(".fo", "*.fo*"));;
	    	savedController.setInitialFileName("Saved settings.fo");
	    	savedController.setInitialDirectory(recommendedPath);
	    	File savedDialog = savedController.showSaveDialog(getPrimaryStage());
	    	if(savedDialog == null) {
	    		setSuccessfullySaveAs(false);
	    		return;
	    	}
 	 		setSuccessfullyOpen(true);
	    	String savedPath = savedDialog.toString();
	    	if(savedPath.endsWith(".fo") && savedPath.length() - savedPath.replace(".", "").length() != 1) {
    			savedPath = savedPath.substring(0, savedPath.indexOf(".")) + ".fo";
	    	} 
	    	else if(!savedPath.endsWith(".fo")) {
	    		if(!savedPath.contains(".")) {
	    			savedPath = savedPath + ".fo";
	    		} else {
	    			savedPath = savedPath.substring(0, savedPath.indexOf(".")) + ".fo";
	    		}
	    	}
	    	
	    		
    		//System.out.println(savedPath);
	        FileWriter writer = new FileWriter(savedPath);
	    	
	        writer.write("Theme: "+getThemeNames()[getThemeMode()]+"\nTheme mode: "+getThemeMode()+
       			 "\nType: "+getTypesNames()[getTypeMode()]+"\nType mode: "+getTypeMode()+
       			 "\nLanguage: "+getLanguageNames()[getLanguageMode()]+"\nLanguage mode: "+getLanguageMode());
	       
	        writer.close();
	        
	      
	        
	        setRunFileSettings(savedPath);
	        openSetting(scene, true, mainObj);
	        
	        String savedInfoPath = getRunFileSettings().replace(".fo", ".ifoi");
	        File savedInfoPathFile = new File(savedInfoPath);
	        
	        if(getTypeMode() ==  0 || getTypeMode() == 1) {
	        	if(savedInfoPathFile.exists()) {
	        		savedInfoPathFile.delete();
	        	}
	        } 
	        else if(getTypeMode() == 2) {
	        	if(!savedInfoPathFile.exists()) {
	                savedInfoPathFile.createNewFile();
	        	}
	        }
	        
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

   }
   public void helpSetting() {
	   	Alert helpAlert = new Alert(AlertType.CONFIRMATION);
    	TextFlow flow = new TextFlow();
	    if(getLanguageMode() == 0) {
	    	helpAlert.setTitle("Help");
		    helpAlert.setHeaderText("Help to recognize Folder Organizer program");
		    
		    Text sMode = new Text("Standard mode: ");
		    Text rMode = new Text("Recommended mode: ");
		    Text cMode = new Text("Customized mode: ");
		    sMode.setStyle("-fx-font-weight: bold;");
		    rMode.setStyle("-fx-font-weight: bold;");
		    cMode.setStyle("-fx-font-weight: bold;");
		    
		    Text partOne = new Text("The purpose of the program is to organize the files of the chosen folder based on three type modes:\n\n");
		    Text partTwo = new Text("Organize the files of the chosen folder based on their extension formats\n\n");
		    Text partThree = new Text("Organize the files of the chosen folder based on my recommended choice\n\n");
		    Text partFour = new Text("Organize the files of the chosen folder based on your customized choice");

		    partOne.setStyle("-fx-font-weight: normal;");
		    partTwo.setStyle("-fx-font-weight: normal;");
		    partThree.setStyle("-fx-font-weight: normal;");
		    partFour.setStyle("-fx-font-weight: normal;");
		    
		    flow.getChildren().addAll(partOne, sMode, partTwo, rMode, partThree, cMode, partFour);
		    flow.setTextAlignment(TextAlignment.CENTER);
		    
		    ButtonType OkButton = new ButtonType("OK");
 	   		helpAlert.getButtonTypes().setAll(OkButton);
 	   		
	    	Window window = helpAlert.getDialogPane().getScene().getWindow();
 	   		
 	   		window.setOnCloseRequest(e -> helpAlert.close());
 	   		
	 	   	DialogPane helpDialog  = helpAlert.getDialogPane();
		    VBox vBox = new VBox();
		    vBox.getChildren().add(flow);
		    helpDialog.setContent(vBox);
		    helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");
		    
		    ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
		    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
		    
		    if(getThemeMode() == 1) {
		    	sMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	
		    	helpDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
		    	helpDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image("Resources/Images/Program icon.png");
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = helpAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			helpAlert.close();
 	   			
 	   			
 	   		}
	    }
	    else if(getLanguageMode() == 1) {
	    	helpAlert.setTitle("مساعدة");
		    helpAlert.setHeaderText("مساعدة بالتعرف على برنامج منظم الملفات");


	    	Text sMode = new Text("الوضع العادي: ");
		    Text rMode = new Text("الوضع الموصى: ");
		    Text cMode = new Text("الوضع المخصص: ");
		    sMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    rMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    cMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    
		    Text partOne = new Text("الغرض من البرنامج تنظيم ملفات المجلد المختار حسب ثلاثة أوضاع: \n\n");
		    Text partTwo = new Text("ينظم ملفات المجلد المختار حسب صيغهم\n\n");
		    Text partThree = new Text("ينظم ملفات المجلد المختار حسب ما استحسنه\n\n");
		    Text partFour = new Text("ينظم ملفات المجلد المختار حسب ما تخصصه");

		    partOne.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partTwo.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partThree.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partFour.setStyle("-fx-font-weight: normal; direction: ltr;");
		    
		    flow.getChildren().addAll(partOne, sMode, partTwo, rMode, partThree, cMode, partFour);
		    flow.setTextAlignment(TextAlignment.CENTER);
		    
		    ButtonType OkButton = new ButtonType("حسنا");
 	   		helpAlert.getButtonTypes().setAll(OkButton);
 	   		
	    	Window window = helpAlert.getDialogPane().getScene().getWindow();
 	   		
 	   		window.setOnCloseRequest(e -> helpAlert.close());
 	   		
	 	   	DialogPane helpDialog  = helpAlert.getDialogPane();
		    VBox vBox = new VBox();
		    vBox.getChildren().add(flow);
		    helpDialog.setContent(vBox);
		    helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");
		    
		    ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
		    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
		    
		    if(getThemeMode() == 1) {
		    	sMode.setStyle("-fx-font-weight: bold; -fx-fill: white; direction: ltr;");
		    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white; direction: ltr;");
		    	cMode.setStyle("-fx-font-weight: bold; -fx-fill: white; direction: ltr;");
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white; direction: ltr;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white; direction: ltr;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white; direction: ltr;");
		    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white; direction: ltr;");
		    	
		    	helpDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
		    	helpDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image("Resources/Images/Program icon.png");
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = helpAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			helpAlert.close();
 	   			
 	   			
 	   		}
	    }
	     	
	    	
   }
   
   public void aboutSetting() {
	   	Alert aboutAlert = new Alert(AlertType.INFORMATION);
	   	Text content = new Text("");
	   	if(getLanguageMode() == 0) {
	   		aboutAlert.setTitle("About");
		    aboutAlert.setHeaderText("About Folder Organizer program");
		    content = new Text("The program is programmed by Jawad Alyousef\n\n"+
		    					"The program is open-sorce, you can use it and modify it if you want to improve it\n\n"+
		    					"Don't forget to give me credit in the original work \n\n"+
		    					"Version: "+getVersion()+
		    					"\n\nContact me: Jawadkazemalyousef@gmail.com\n\n"+
					  			"Thank you");
		    content.setStyle("-fx-font-size:20; -fx-font-weight: normal;");
		    
		    ButtonType OkButton = new ButtonType("OK");
 	   		aboutAlert.getButtonTypes().setAll(OkButton);
	 	   	DialogPane aboutDialog  = aboutAlert.getDialogPane();

	    	Window window = aboutDialog.getScene().getWindow();
 	   		
 	   		window.setOnCloseRequest(e -> aboutAlert.close());
 	   		
		    VBox vBox = new VBox();
		    content.setTextAlignment(TextAlignment.CENTER);
		    vBox.getChildren().add(content);
		    aboutDialog.setContent(vBox);
		    aboutDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");
		    
		    ButtonBar buttonbar = (ButtonBar) aboutDialog.lookup(".button-bar");
		    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
		    
		    if(getThemeMode() == 1) {
		    	content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
		    	aboutDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
		    	aboutDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image("Resources/Images/Program icon.png");
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = aboutAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			aboutAlert.close();
 	   			
 	   			
 	   		}
	   	}
	   	else if(getLanguageMode() == 1){
	   		aboutAlert.setTitle("حول");
		    aboutAlert.setHeaderText("حول منظم الملفات");
		    content = new Text("مبرمج البرنامج جواد اليوسف\n\n"+
		    					"البرنامج مفتوح المصدر، يمكنك استخدامه وتعديل الكود إن أردت تحسينه\n\n"+
		    					"لا تنس أن تنسب الفضل لي في العمل الأصلي\n\n"+
		    					"الإصدار: "+getVersion()+
		    					"\n\nللتواصل معي:  Jawadkazemalyousef@gmail.com\n\n"+
					  			"شكرا لكم");
		    content.setStyle("-fx-font-size:20; direction: ltr; -fx-font-weight: normal;");
		    
		    ButtonType OkButton = new ButtonType("حسنا");
 	   		aboutAlert.getButtonTypes().setAll(OkButton);
	 	   	DialogPane aboutDialog  = aboutAlert.getDialogPane();

 	   		
	    	Window window = aboutDialog.getScene().getWindow();
 	   		
 	   		window.setOnCloseRequest(e -> aboutAlert.close());
 	   		
		    VBox vBox = new VBox();
		    content.setTextAlignment(TextAlignment.CENTER);
		    vBox.getChildren().add(content);
		    aboutDialog.setContent(vBox);
		    aboutDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");
		    
		    ButtonBar buttonbar = (ButtonBar) aboutDialog.lookup(".button-bar");
		    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
		    
		    if(getThemeMode() == 1) {
		    	content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
		    	aboutDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
		    	aboutDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image("Resources/Images/Program icon.png");
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = aboutAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			aboutAlert.close();
 	   			
 	   			
 	   		}
 	   	
	   	}
	    
   }
   public void BackSetting(OrganizeFolderMainUI mainObj) {
		
	}
   
  
 
}


