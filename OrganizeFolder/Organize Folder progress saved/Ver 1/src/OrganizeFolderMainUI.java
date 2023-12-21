import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;




public class OrganizeFolderMainUI extends OrganizeFolderDriver{

	

	
	
	// Integer key for Light (0) and Dark (1) mode theme
	private final double VERSION = 1.0;
	private int themeMode = 0;
	private int typeMode = 0;
	private String runFileSettings;
	private boolean firstTime = true;
	private Stage primaryStage = new Stage();
	private BorderPane mainPane = new BorderPane();
	private Scene mainScene = new Scene(getMainPain(), 500, 500);
	
	private Button lightDarkMode = themeModeButtonProperty();
	
    private Menu file = new Menu("File");
    private MenuBar menuBar = new MenuBar();

	private MenuItem open = menuItemProperty(0);
	private MenuItem save = menuItemProperty(1);
	private MenuItem saveAs = menuItemProperty(2);
	private MenuItem help = menuItemProperty(3);
	private MenuItem about = menuItemProperty(4);
	
	private TextField pathFolder = new TextField();
	
	//for consistent (work on to solve theme inconsistent problem))
	private boolean isChangeTheme = false;
	private boolean isChange = false;
	private int initialMainTheme = -1;
	
	public boolean getIsChangeTheme() {
		return this.isChangeTheme;
	}
	
	public void setIsChangeTheme(boolean isChangeTheme) {
		this.isChangeTheme = isChangeTheme;
	}
	
	public boolean getIsChange() {
		return this.isChange;
	}
	
	public void setIsChange(boolean isChange) {
		this.isChange = isChange;
	}
	public int getIntialMainTheme() {
		return this.initialMainTheme;
	}
	public void setIntialMainTheme(int theme) {
		this.initialMainTheme = theme;
	}
	//sycr between main and other windows
	
	public double getVersion() {
		return this.VERSION;
	}
	
	public int  getThemeMode() {
		return this.themeMode;
	}
	
	public void setThemeMode(int mode) {
		this.themeMode = mode;
	}
	
	public int  getTypeMode() {
		return this.typeMode;
	}
		
	public void setTypeMode(int mode) {
		this.typeMode = mode;
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
	
	public Button getLightDarkMode() {
		return lightDarkMode;
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
    	
    	Button standardBtn = buttonProperty("Standard");
        Button recommendedBtn = buttonProperty("Recommended");
        Button customizedBtn = buttonProperty("Customized");
            
        standardBtn.setOnAction(e -> typeModeChoice(0, mainObj));
        recommendedBtn.setOnAction(e -> typeModeChoice(1, mainObj));
        customizedBtn.setOnAction(e -> typeModeChoice(2, mainObj));
            
        getLightDarkMode().setOnAction(e -> changeMode(false, false, getMainScene()));
    		
    	getOpen().setOnAction(e -> openSetting(getMainScene(), false));
        getSave().setOnAction(e -> savedSetting());
        getSaveAs().setOnAction(e -> savedAsSetting(getMainScene()));
        getHelp().setOnAction(e -> helpSetting());
        getAbout().setOnAction(e -> aboutSetting());
        
        if(isBack) {
            	 
             	setTypeMode(0);
             	HBox hBoxCenter = new HBox();
                 hBoxCenter.setSpacing(15);
                 hBoxCenter.getChildren().addAll(standardBtn, recommendedBtn, customizedBtn);
                 hBoxCenter.setAlignment(Pos.CENTER);
                     
                 VBox vhBoxTop = new VBox();
                 vhBoxTop.getChildren().addAll(getMenuBar(), getLightDarkMode());
                 vhBoxTop.setAlignment(Pos.TOP_RIGHT);
                    
                 getMainPain().setCenter(hBoxCenter);
                 getMainPain().setTop(vhBoxTop);
                 if(getIsChangeTheme() && !getIsChange()) {
                	 System.out.println(1);
                      runSetting(true, false, getIsChangeTheme(), getMainScene());
                  }
                  else if(!getIsChangeTheme() && !getIsChange()){
                	System.out.println(2);
                 	 getMainPain().getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
                 	 getMainPain().getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());         	
                  }
                  else if(getIsChangeTheme() && getIsChange()){ 
                  	System.out.println(3);
                       runSetting(true, false, getIsChangeTheme(), getMainScene());
                  }
                  else if(!getIsChangeTheme() && getIsChange()) {
                  	System.out.println(4);
                 	 getMainPain().getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
                 	 getMainPain().getStylesheets().remove(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());


                  }
                     
             	 getPrimaryStage().setScene(getMainScene());
        	 }
         
	         
	         else {
	        	 
	              	runSetting(false, false, false, getMainScene());
	              
	             
	              	getFile().getItems().addAll(getOpen(), getSave(),  getSaveAs(), getHelp(), getAbout());
	                         
	              	getMenuBar().getMenus().add(file);
	              	
	              	HBox hBoxCenter = new HBox();
	                hBoxCenter.setSpacing(15);
	                hBoxCenter.getChildren().addAll(standardBtn, recommendedBtn, customizedBtn);
	                hBoxCenter.setAlignment(Pos.CENTER);
	                 
	                VBox vhBoxTop = new VBox();
	                vhBoxTop.getChildren().addAll(getMenuBar(), getLightDarkMode());
	                vhBoxTop.setAlignment(Pos.TOP_RIGHT);
	                
	                getMainPain().setCenter(hBoxCenter);
	                getMainPain().setTop(vhBoxTop);
	                 
	                Image programIcon = new Image("Resources/Images/Program icon.png");
	
	                getPrimaryStage().setTitle("Organize Folder");
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
        }
       
    	
    
   // for sycr between windows
    // public void firstRecommendedOpen(OrganizeFolderMainUI mainObj) {
    // 	RecommendedUI recommendedObj = new RecommendedUI();
	// 	recommendedObj.runRecommendedUI(mainObj);
    // }
    
    // public void firstCustomizedOpen(OrganizeFolderMainUI mainObj) {
    // 	CustomizedUI customizedObj = new CustomizedUI();
	// 	customizedObj.runCustomizedUI(mainObj);	
    // }
    
    
    
    // //
    
    
    
	public Button buttonProperty(String title) {
    	Button btn = new Button();
    	
    	
	    	btn.setText(title);
	    	btn.setMinSize(150, 50);
	        btn.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");
    	
    	return btn;
    }
	
	public void typeModeChoice(int typeModeChoice,  OrganizeFolderMainUI mainObj) {
		if(typeModeChoice == 0) {
			setIntialMainTheme(getThemeMode());
			StandardUI standdardObj = new StandardUI();
			standdardObj.runStandardUI(mainObj);

			
		}
		else if(typeModeChoice == 1) {
			setIntialMainTheme(getThemeMode());
			RecommendedUI recommendedObj = new RecommendedUI();
			recommendedObj.runRecommendedUI(mainObj);
					
		}
		else if(typeModeChoice == 2) {
			setIntialMainTheme(getThemeMode());
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
    
    public Button themeModeButtonProperty() {
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
    
    public int[] fileValues(Scanner scan){
    	 	int count = 0;
	 	    int[] fileValues = {0, 0, 1};
	 	    
	 	    while(scan.hasNext()) {
	 	    	if(scan.hasNextInt() && count == 0) {
	 	    		fileValues[0] =scan.nextInt();
	 	    		++count;
	 	    	}
	 	    	else if(scan.hasNextInt() && count == 1) {
	 	    		fileValues[1] = scan.nextInt();
	 	    		++count;
	 	    		if(fileValues[0] == 0 || fileValues[0] == 1)  {
	 	    			if(fileValues[1] == 0 || fileValues[1] == 1 || fileValues[1] == 2) {
	 	    				fileValues[2] = 0;
	 	    			}
	 	    		}
	 	    		else{
		 	    		fileValues[2] = 1;

	 	    		}
	 	    	}
	 	    	else if(count == 2) {
	 	    		fileValues[2] = 1;
	 	    		break;
	 	    	}
	 	    	else {
	 	    		
	 	    		scan.next();
	 	    	}
	 	   
	 	    }
	 	 
	 	   
	 	    return fileValues;
    }

    public void runSetting(boolean sameInfo, boolean isNewscene, boolean isChangeTheme, Scene scene) {
	    try {
	    	if(getFirstTime()) {
	    		File runSetting = new File("src/Resources/Mode settings/Saved settings.txt");
	    	    //System.out.println(openSetting.getAbsolutePath());
	    	    //System.out.println(openSetting.canRead());
	    		if(!runSetting.isFile()) {
	 	    	    setRunFileSettings(runSetting.getAbsolutePath());
	    			savedSetting();
	    		}else {
	    			Scanner scan= new Scanner(runSetting);
	 	    	   	int[] fileValues = fileValues(scan);
	 	    	   	if(fileValues[2] == 1) {
		 	    	    setRunFileSettings(runSetting.getAbsolutePath());
	 	    	   		setTypeMode(0);
	 	    	   		savedSetting();
	 	    	   	}
	 	    	   	else {
		 	    	    setTypeMode(fileValues[1]);
	 	    	   	}
	 	    	    setThemeMode(fileValues[0]);
	 	    	    setRunFileSettings(runSetting.getAbsolutePath());
	 	    	    
	    		}
	    		changeMode(false, false, scene); 
	    	    
	        } 
	    	else if(isNewscene) {
	    		changeMode(isNewscene, false, scene);
	    	}
	    	else if(isChangeTheme){
    			changeMode(true, !isChangeTheme , scene);
	    		
	    	}
	    	else {
	    		changeMode(true, sameInfo, scene);
	        }
	    }catch (IOException e) {
	   	  System.out.println("An error occurred.");
	   	  e.printStackTrace();
	    }	
   }
    	
    
    public void openSetting(Scene scene, boolean bySaveAs){
    	 try {
    		 String openPath = "";
    		 if(!bySaveAs) {
    			 File recommendedPath = new File("src/Resources/Mode settings");
    	 	 	 FileChooser openController = new FileChooser();
    	 	 	 openController.setTitle("Open");
    	 	 	 openController.getExtensionFilters().addAll(new ExtensionFilter("txt", "*.txt*"));;
    	 	 	 openController.setInitialDirectory(recommendedPath);
    	 	 	 File openDialog = openController.showOpenDialog(getPrimaryStage());
    	 	 	 if(openDialog == null) {
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
// 	 	    System.out.println(openSetting.canRead());
 	 	    Scanner scan= new Scanner(openSetting);
 	 	    
 	 	   
 	 	    int[] fileValues = fileValues(scan);
 	 	    
 	 	    if(fileValues[2] == 1) {
 	 	    	Alert errorAlert = new Alert(AlertType.ERROR);
 	 	    	errorAlert.setTitle("Error!");
 	 	    	
 	 	    	
 	 	    	errorAlert.setHeaderText("");
 	 	    	DialogPane errorDialog  = errorAlert.getDialogPane();
 	 	    	Text content = new Text("Error: Invalid format content!");
 	 	    	content.setStyle("-fx-font-size:20;");
 	 	    	
 	 	    	VBox vBox = new VBox();
 	 	    	content.setTextAlignment(TextAlignment.CENTER);
 	 		    vBox.getChildren().add(content);
 	 		    vBox.setAlignment(Pos.BOTTOM_CENTER);
 	 		    errorDialog.setContent(vBox);
 	 	        
 	 	    	if(getThemeMode() == 1) {
 	 	    		content.setStyle("-fx-fill: white; -fx-font-size:20;");
 	 	    		errorDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
 	 	    		errorDialog.getStyleClass().add("Dark mode theme");

 	 	    	}
 	 	        Image programIcon = new Image("Resources/Images/Program icon.png");
 	 	    	Stage stageIcon = (Stage) errorDialog.getScene().getWindow();
 	 	    	stageIcon.getIcons().add(programIcon);
 	 	    	errorAlert.showAndWait(); 	 	    	
 	 	    	return;
 	 	    }

 	 	
 	  
 	 	    boolean sameInfo = false;
 	 	   
 			if(!openPath.equals(getRunFileSettings())) {
 					getPathFolder().setText("");
   	 	     		getPathFolder().setDisable(true);
 		 	 	   setRunFileSettings(openPath);
 		 	 	   if(fileValues[0] != getThemeMode()) {
 		 	 		   setThemeMode(fileValues[0]);
 		 	 		   runSetting(sameInfo, false, false, scene);
 		 	 	    }
 		 	 	    else {
 		 	 	       sameInfo = true;
 		 	 		   runSetting(sameInfo, false, false, scene);

 		 	 	    }

 				
	 		} else {
	 			if(fileValues[0] != getThemeMode()) {
		 	 		   setThemeMode(fileValues[0]);
		 	 		   runSetting(sameInfo, false, false, scene);
		 	 	    }
		 	 	    else {
		 	 	       sameInfo = true;
		 	 		   runSetting(sameInfo, false, false, scene);
		 	 	    }
	 		}
 			
	 	 	    
 	 	    

 		    
 		    System.out.println("Open file successfully");
 	      } catch (IOException e) {
 	    	  System.out.println("An error occurred.");
 	    	  e.printStackTrace();
	      }
    }
    
    public void savedSetting() {
    try {
    	FileWriter Writer = new FileWriter(getRunFileSettings());
    	System.out.println(getTypeMode());
        if(getThemeMode()== 0) {
        	Writer.write("Theme mode: "+getThemeMode()+"\nType theme: Light mode\nType mode = "+getTypeMode());
        }else if(getThemeMode() == 1) {
        	Writer.write("Theme mode: "+getThemeMode()+"\nType theme: Dark mode\nType mode = "+getTypeMode());
        }
       
        Writer.close(); 
        System.out.println("Successfully wrote to the file.");
      } catch (IOException e) {
        System.out.println("An error occurred.");
        e.printStackTrace();
      }
    }
    
   public void savedAsSetting(Scene scene) {
	    try {
	    	File recommendedPath = new File("src/Resources/Mode settings");
	    	FileChooser savedController = new FileChooser();
	    	savedController.setTitle("Save As");
	    	savedController.getExtensionFilters().addAll(new ExtensionFilter("All Files", "*.*"));;
	    	savedController.setInitialFileName("Saved settings.txt");
	    	savedController.setInitialDirectory(recommendedPath);
	    	File savedDialog = savedController.showSaveDialog(getPrimaryStage());
	    	if(savedDialog == null) {
	    		return;
	    	}
	    	String savedPath = savedDialog.toString();
	    	if(!savedPath.endsWith(".txt"))
	    		savedPath = savedPath + ".txt";
    		//System.out.println(savedPath);
	        FileWriter Writer = new FileWriter(savedPath);
	    	
	        if(getThemeMode()==0) {
	        	Writer.write("Theme mode: "+getThemeMode()+"\nType theme: Light mode\nType mode = "+getTypeMode());
	        }else if(getThemeMode() == 1) {
	        	Writer.write("Theme mode: "+getThemeMode()+"\nType theme: Dark mode\nType mode = "+getTypeMode());
	        }
	       
	        Writer.close();
	        setRunFileSettings(savedPath);
	        openSetting(scene, true);
	        
	        
	        System.out.println("Successfully wrote to the file.");
	      } catch (IOException e) {
	        System.out.println("An error occurred.");
	        e.printStackTrace();
	      }

   }
   public void helpSetting() {
	   	Alert helpAlert = new Alert(AlertType.CONFIRMATION);
	    helpAlert.setTitle("Help");
	    helpAlert.setHeaderText("Help to use the program");
	    Text content = new Text("The purpose of the program is to organize the files of the specifc folder based on three type modes:\n\n"+
	    						"Standard mode: Organize the files based on their extension format\n\n"+
	    						"Recommended mode: Organize the files based on my recommended choice\n\n"+
				 				"Customized mode: Organize the files based on your customized choice");
	    content.setStyle("-fx-font-size:20;");
	    helpAlert.getButtonTypes().setAll(ButtonType.OK);
	    DialogPane helpDialog  = helpAlert.getDialogPane();
	    VBox vBox = new VBox();
	    content.setTextAlignment(TextAlignment.CENTER);
	    vBox.getChildren().add(content);
	    helpDialog.setContent(vBox);
	    helpDialog.setStyle("-fx-font-size:20;");
	    if(getThemeMode() == 1) {
	    	content.setStyle("-fx-fill: white;");
	    	helpDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	    	helpDialog.getStyleClass().add("Dark mode theme");

	    }
	    Image programIcon = new Image("Resources/Images/Program icon.png");
	    Stage stageIcon = (Stage) helpDialog.getScene().getWindow();
	    stageIcon.getIcons().add(programIcon);
	    	
	    helpAlert.showAndWait(); 	 	    	
	    	
   }
   
   public void aboutSetting() {
	   	Alert aboutAlert = new Alert(AlertType.INFORMATION);
	    aboutAlert.setTitle("About");
	    aboutAlert.setHeaderText("About the program");
	    Text content = new Text("The program is programmed by Jawad Alyousef\n\n"+
	    						"The program is open-sorce, you can use it and modify it if you want to improve it\n\n"+
	    						"Don't forget to mention me in the work\n\n"+
	    						"Version: "+getVersion()+
	    						"\n\nContact me via Jawadkazemalyousef@gmail.com\n\n"+
				  				"Thank you");
	    content.setStyle("-fx-font-size:20;");
	    DialogPane aboutDialog  = aboutAlert.getDialogPane();
	    VBox vBox = new VBox();
	    content.setTextAlignment(TextAlignment.CENTER);
	    vBox.getChildren().add(content);
	    aboutDialog.setContent(vBox);
	    aboutDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	    if(getThemeMode() == 1) {
	    	content.setStyle("-fx-fill: white;");
	    	aboutDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	    	aboutDialog.getStyleClass().add("Dark mode theme");

	    }
	    Image programIcon = new Image("Resources/Images/Program icon.png");
	    Stage stageIcon = (Stage) aboutDialog.getScene().getWindow();
	    stageIcon.getIcons().add(programIcon);
	    aboutAlert.showAndWait(); 
   }
   public void BackSetting(OrganizeFolderMainUI mainObj) {
		
	}
   
  
 
}


