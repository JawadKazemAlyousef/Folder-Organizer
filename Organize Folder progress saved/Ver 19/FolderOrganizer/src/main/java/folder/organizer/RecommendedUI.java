package folder.organizer;
import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
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
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;

public class RecommendedUI {
	
	private BorderPane recommendedPane = new BorderPane();
	private Scene recommendedScene = new Scene(getRecommendedPain(), 500, 500);
	
	private Button back = backOrHelpButtonProperty(0);
    private Button help = backOrHelpButtonProperty(1);
	
    
    Button chooseFolder = buttonProperty("Choose");
    Button organize = buttonProperty("Organize");
    
    
	public Scene  getRecommendedScene() {
		return this.recommendedScene;
	}
	
	public BorderPane getRecommendedPain() {
		return this.recommendedPane;
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

	public void runRecommendedUI(OrganizeFolderMainUI mainObj) {
		
		getRecommendedPain().getChildren().clear();
		mainObj.setTypeMode(1);


		
		mainObj.setIsChangeTheme(false);
		
        getOrganizeBtn().setDisable(true);


		mainObj.getPathFolder().setText("");
		

		
		changeTheme(mainObj, false);
    	mainObj.getLightDarkMode().setOnAction(e -> changeTheme(mainObj, true));
    	mainObj.getLanguageButton().setOnAction(e -> changeLanguage(mainObj, true));

        
        
 
        
        getBackBtn().setOnAction(e -> backsetting(mainObj));
        getHelpBtn().setOnAction(e -> helpSetting(mainObj));
        
        
        
        changeLanguage(mainObj, false);

        
        getChooseFolderBtn().setOnAction(e -> chooseSetting(mainObj));
        getOrganizeBtn().setOnAction(e -> organizeSetting(mainObj));

        
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
        vBoxCenter.getChildren().addAll(getChooseFolderBtn(), mainObj.getPathFolder(), getOrganizeBtn());
        vBoxCenter.setSpacing(10);
        vBoxCenter.setAlignment(Pos.CENTER);
        
       
        getRecommendedPain().setTop(vBoxTop);
        getRecommendedPain().setCenter(vBoxCenter);
        mainObj.getPrimaryStage().setScene(getRecommendedScene());


        if(mainObj.getFirstTime()) {
        	mainObj.getPrimaryStage().show();
        }
        
     
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
<<<<<<< HEAD
			btnLogo.setImage(new Image(new File("resources/Images/Back logo.png").toURI().toString()));
			btn.setGraphic(btnLogo);
		}
		else if(whichButton == 1) {
			btnLogo.setImage(new Image(new File("resources/Images/Help Logo.jpg").toURI().toString()));
			btn.setGraphic(btnLogo);
		}
        
		return btn;
	}
	
	public void changeTheme(OrganizeFolderMainUI mainObj, boolean byButton){
		if(byButton) {
			mainObj.changeMode(false, false, mainObj.getMainScene());
		}
		mainObj.setFirstTime(true);
		mainObj.changeMode(false, false, getRecommendedScene());
		mainObj.setFirstTime(false);
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
			chooseFolder.setText("اختر");
        	organize.setText("نظم");
		}
	}
	
	
	public void openCheck(OrganizeFolderMainUI mainObj){
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();
//		System.out.println(checkLanguage);
//		System.out.println(checkTheme);
		mainObj.openSetting(getRecommendedScene(), false, mainObj);
//		System.out.println(mainObj.getLanguageMode());
//		System.out.println(mainObj.getThemeMode());
		if(mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if(checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		}
		else {
//			System.out.println("Test");
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);
			
		}
		if(mainObj.getPathFolder().getText().equals("")) {
	        getOrganizeBtn().setDisable(true);
		}
		
	}
	
	public void saveAsCheck(OrganizeFolderMainUI mainObj){
		String checkPath = mainObj.getRunFileSettings();
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();

//		System.out.println(checkPath);
		mainObj.savedAsSetting(getRecommendedScene(), mainObj);
//		System.out.println(mainObj.getRunFileSettings());
		
		if(!checkPath.equals(mainObj.getRunFileSettings() )) {
			mainObj.getPathFolder().setText("");
	     	mainObj.getPathFolder().setDisable(true);
	        getOrganizeBtn().setDisable(true);

		}
		
		if(mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if(checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		}
		else {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);
			
		}
		
	}
	
	public void backsetting(OrganizeFolderMainUI mainObj) {
		
		mainObj.MainUI(mainObj, true);
	}
	
	public void helpSetting(OrganizeFolderMainUI mainObj) {
		Alert helpAlert = new Alert(AlertType.CONFIRMATION);
    	TextFlow flow = new TextFlow();
		if(mainObj.getLanguageMode() == 0) {
			 	helpAlert.setTitle("Help");
			    helpAlert.setHeaderText("How to use Recommended mode");
			    
			    Text rMode = new Text("Recommended mode: ");
			    Text bChoose = new Text("Choose button ");
			    Text mFile = new Text("File menu");
			    Text mSave = new Text("Save option ");
			    Text mSaveAs = new Text("Save As option ");
			    Text bOrganize = new Text("Organize button ");
			    Text note = new Text("Note: ");

			    
			    rMode.setStyle("-fx-font-weight: bold;");
			    bChoose.setStyle("-fx-font-weight: bold;");
			    mFile.setStyle("-fx-font-weight: bold;");
			    mSave.setStyle("-fx-font-weight: bold;");
			    mSaveAs.setStyle("-fx-font-weight: bold;");
			    bOrganize.setStyle("-fx-font-weight: bold;");
			    note.setStyle("-fx-font-weight: bold;");
			    
			    Text partOne = new Text("Organize the files of the chosen folder based on my recommended choice\n\n"+
						 "Instruction: \n\n"+
						 "1. Click ");
			    Text partTwo = new Text("to select the path (the folder location)\n\n"+
						 "2. Choose the path (the folder location) that you want to organize\n\n"+
			    		 "3. Choose the language that you want folders' names based on\n\n"+
						 "4. Make sure to save the settings at this specific mode (at the same window) by clicking ");
			    Text partThree = new Text(", and then ");
			    Text partFour = new Text("or ");
			    Text partFive = new Text("if you want to save in the new saved setting\n\n"+"5. Click ");
			    Text partSix = new Text("to organize the folder's files based on my recommended choice\n\n");
			    Text partSeven = new Text("If there are files' extension formats that are not in the recommended mode list, they will be automatically organized based on their extension formats");
		

			    partOne.setStyle("-fx-font-weight: normal;");
			    partTwo.setStyle("-fx-font-weight: normal;");
			    partThree.setStyle("-fx-font-weight: normal;");
			    partFour.setStyle("-fx-font-weight: normal;");
			    partFive.setStyle("-fx-font-weight: normal;");
			    partSix.setStyle("-fx-font-weight: normal;");
			    partSeven.setStyle("-fx-font-weight: normal;");
			    
			    flow.getChildren().addAll(rMode, partOne, bChoose, partTwo, mFile, partThree, mSave, partFour, mSaveAs, partFive, bOrganize, partSix, note, partSeven);
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
			    
			    if(mainObj.getThemeMode() == 1) {
			    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	note.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	
			    	
			    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	
			    	helpDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
			    	helpDialog.getStyleClass().add("Dark mode theme");
		
			    }
			    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
			    Stage stageIcon = (Stage) window;
			    stageIcon.getIcons().add(programIcon);
		 	   		
	 	   		Optional<ButtonType> result = helpAlert.showAndWait();
	 	   		
	 	   		if(result.isPresent() && result.get() == OkButton) {
	 	   			helpAlert.close();
	 	   			
	 	   			
	 	   		}
		}
		else if(mainObj.getLanguageMode() == 1) {
			helpAlert.setTitle("مساعدة");
		    helpAlert.setHeaderText("كيف تستخدم الوضع الموصى");
		    
		    Text rMode = new Text("الوضع الموصى: ");
		    Text bChoose = new Text("زر اختر ");
		    Text mFile = new Text("قائمة ملف");
		    Text mSave = new Text("خيار حفظ ");
		    Text mSaveAs = new Text("خيار حفظ كـ ");
		    Text bOrganize = new Text("زر نظم ");
		    Text note = new Text("ملاحظة: ");
		    
		    rMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bChoose.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mFile.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mSave.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mSaveAs.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bOrganize.setStyle("-fx-font-weight: bold; direction: ltr;");
		    note.setStyle("-fx-font-weight: bold; direction: ltr;");
		    


		     
		    Text partOne = new Text("ينظم ملفات المجلد المختار حسب ما استحسنه\n\n"+
					 "التعليمات: \n\n"+
					 "1. انقر  ");
		    Text partTwo = new Text("لتختار المسار (موقع المجلد)\n\n"+
					 "2. اختر المسار (موقع المجلد) الذي تريد أن ترتب ملفاته\n\n"+
					 "3. اختر اللغة التي تريد أن تكون أسماء المجلدات بها\n\n"+
					 "4. تأكد من أنك حفظت الإعدادات بهذا الوضع (بنفس هذه النافذة) وهذا عن طريق نقر ");
		    Text partThree = new Text("، ثم ");
		    Text partFour = new Text("أو ");
		    Text partFive = new Text("إذا كنت تريد أن تحفظ الإعدادات بتخزينة جديدة\n\n"+"5. انقر ");
		    Text partSix = new Text("لتنظم ملفات المجلد المختار حسب ما استحسنه\n\n");
		    Text partSeven = new Text("إذا كان هناك صيغ ملفات غير موجودة في قائمة الموصى بها، سترتب تلقائيا حسب صيغهم");
	
		    partOne.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partTwo.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partThree.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partFour.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partFive.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partSix.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partSeven.setStyle("-fx-font-weight: normal; direction: ltr;");
		    
		 
		    
		    flow.getChildren().addAll(rMode, partOne, bChoose, partTwo, mFile, partThree, mSave, partFour, mSaveAs, partFive, bOrganize, partSix, note, partSeven);
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
		    
		    if(mainObj.getThemeMode() == 1) {
		    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	note.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	
		    	
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	
		    	helpDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
		    	helpDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = helpAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			helpAlert.close();
 	   			
 	   			
 	   		}
		}
	    	 	    	
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
	 	
	 	getOrganizeBtn().setDisable(false);



	}
	
	public void organizeSetting(OrganizeFolderMainUI mainObj) {
		
			
		if(mainObj.getInitialSavedSetting() != 1) {
			Alert errorAlert = new Alert(AlertType.ERROR);
 	 	    Text content = new Text("");
 	 	    if(mainObj.getLanguageMode() == 0) {
 	 	    	errorAlert.setTitle("Error!");
 	 	 	    errorAlert.setHeaderText("");
 	 	 	    content = new Text("Error: Please, make sure to save or save as at this type mode to move on in the process");
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
			    
	 	 		if(mainObj.getThemeMode() == 1) {
	 	 		    content.setStyle("-fx-fill: white;");
	 	 		    errorDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		 }
	 	 		 Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
	 	 		 Stage stageIcon = (Stage) window;
	 	 		 stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   	 Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   	 if(result.isPresent() && result.get() == OkButton) {
	 	  	   		 errorAlert.close();	
	 	  	   		}
 	 	    	 } 
 	 	    	 else if(mainObj.getLanguageMode() == 1) {
 	 	    		errorAlert.setTitle("!خطأ");
 	 	 	    	errorAlert.setHeaderText("");
 	 	 	    	content = new Text("خطأ: رجاء، تأكد من الحفظ أو الحفظ كـ على هذا الوضع لتستكمل العملية!");
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
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	errorDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    	errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
	 	 		    Stage stageIcon = (Stage) window;
	 	 		    stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   		Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   		if(result.isPresent() && result.get() == OkButton) {
	 	  	   			errorAlert.close();
	 	  	   			
	 	  	   			
	 	  	   		}
 	 			    
 	 	    	}
 	 	    		 	    	
 	 	    	return;
	 			    
	 	    }
	 	    		 	    	
	 	    
			
			// Fixed info
			String[] folderNamesR = {"Images", "Videos", "Sounds & Musics", "Documents", "Compressed", "Programs", "Programming Languages"};
			String[][] extenstionsForEachFolderR = { 
													{"png", "jpg", "gif", "apng", "avif", "jpeg", "jfif", "pjpeg", "pjp", "svg", "webp", "bmp", "ico", "cur", "tif",
													 "tiff", "jif", "jpe", "jfi", "psd", "raw", "arw", "cr2", "nrw", "k25", "dib", "heif", "heic", "svgz", "ai", "eps",
													 "jp2", "j2k", ".jpf", "jpx", "jpm", "mj2", "art", "pam", "blp", "act",  "dds", "hdr", "mng", "jps", "nef", "orf",
													 "vsdx", "xar", "pat", "drw", "cdr", "exr", "pcx", "pbm","pcd", "pfm", "pes", "pgm", "pnm", "picon", "ppm", "raf",
													 "ras", "ra2", "tga", "wbmp", "x3f", "xcf", "toast"},
													
													{"avi", "mkv", "mp4", "webm", "flv", "vob", "ogv",  "drc", "gifv", "mng", "MTS", "M2TS", "m2ts",
													 "TS", "mov", "qt", "wmv", "yuv", "rm", "rmvb", "viv", "asf", "amv", "m4v", "mpg", "mp2", "ogg", "mogg",
													 "mpeg", "mpe", "mpv", "m2v", "m4v", "svi", "3gp", "3g2", "mxf", "roq", "nsv", "f4v", "f4p", "f4a", "f4b"},
													 
													{"mp3", "3gp", "aa", "aac", "aax", "aiff", "alac", "amr", "ape", "au", "awb", "dss", "dvf", "flac",
												     "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf", "movpkg", "mpc", "msv", "nmf",  "oga", "mid", "midi", 
												     "opus", "ra", "raw", "wav", "rf64", "sln", "tta", "voc", "vox", "wma", "wv", "8svx", "cda"},
													
													{"txt", "docx", "pdf", "xlsx", "doc", "dot", "wbk", "ooxml", "docm", "dotx", "dotm", "docb", "wll", "wwl", "xls",
												     "xlt", "xlm", "xlsm", "xltx", "xltm", "xlsb", "xla", "xlam", "xll", "xlw", "xll_", "xla_", "xla5", "xla8", "ppt",
												     "pot", "pps", "ppa", "pptm", "potx", "potm", "ppam", "ppsx", "ppsm", "sldx", "sldm", "one", "ecf", "pub", "odt",
												     "ott", "fodt", "uot", "xml", "rtf", "ots", "ods", "fods", "uos", "dif", "dbf", "slk", "csv", "ppts", "ppmx",
												     "pomx", "pom", "pptx", "md", "pages", "eml", "log", "msg", "wbs", "jpynb", "srt", "ssa", "ttml", "sbv",
												     "dfxp", "vtt", "itt", "scc", "ass", "fo", "ifoi"},
													
													{"rar", "zip", "7z", "s7z", "ace", "afa", "alz",  "arc", "ark", "cdx", "arj", "b1", "b6z", "ba", "bh",
													 "cab", "car", "cfs", "cpt", "dar", "dd", "dgc", "dmg",  "gca", "genozip", "ha", "hki", "ice", 
													 "kgb", "lzh", "lha", "lzx", "pak", "partimg", "paq6", ".paq7", "paq8", "pea", "phar", "pim", "pit", "qda",
													 "rk", "sda", "sea", "sen", "sfx", "shk", "sit", "sitx", "sqx", "tgz", "tbz2", "tlz", "txz", "uc", "uc0",
													 "uc2", "ucn", "ur2", "ue2", "uca", "uha",  "wim",  "xp3", "yz1", "zipx", "zoo", "zpaq", "zz",
													 "a", "ar", "cpio", "shar", "LBR", "iso", "lbr", "mar", "sbx", "tar", "br", "bz2", "F", "f", "?XF", "xf", "?xf", "genozip", "gz",
													 "lz", "lz4", "lzma", "lzo", "rz", "sfark", "sz", "q", "xz", "z", "Z", "zst"},
													
													{"exe", "lnk", "msi", "apk", "jar", "war", "ear", "app", "appx",  "deb", "hap", "ipa", "msix", "prm", "xap", "xbap",
												     "com", "bin", "bat", "wsf", "sys"},
													
													{"java", "py", "accda", "accdb", "accde", "accdr", "accdt", "accdu", "htm", "html", "xhtml", "css", "rss", "cer", "asp",
												     "aspx", "c", "cc", "abap", "php", "cs", "swift", "vb", "c#", "sql", "js", "ts", "net", "r", "asm", "i", "mac", "verilog",
												     "4th", "abc", "acd", "addin", "ads", "afphoto", "agi", "aia", "aidl", "alb", "aml", "ane", "apa", "apks", "jrxml", "jsp", 
												     "appxbundle", "appxupload", "aps", "arsc", "as", "as2proj", "as3proj", "asc", "asi", "asm", "asvf", "au3", "awk", "c3",
												     "caf", "cd", "cjs", "class", "cmake", "config", "cpp", "cs", "csproj", "csx", "cxx", "dart", "diff", "egg", "erb", "ex",
												     "exp", "fs", "g4", "cmd", "gmd", "go", "groovy", "h", "haml", "hh", "hpp", "hs", "hta", "ici", "in", "inc", "ino", "ipr",
												      "jav", "jspe", "kt", "lgo", "lua", "s", "m", "make", "mel", "mf", "ml", "mm", "mrc", "msix", "nupkg", "nut", "nx", "pas",
												      "pdb", "pde", "pl", "pyd", "pyi", "pym", "pyw", "pyx", "rb", "rbxl", "rbxm", "res", "rs", "rst", "scala", "scm", "script",
												      "sh", "sln", "tcl", "toml", "unity", "vbproj", "vbs", "vcproj", "vcxproj", "xds", "yaml", "yml", "ypr", "xps", "php3", "cfm",
												      "cfml", "jsx", "db", "nsf", "fp7", "te", "mar", "mts", "ctts", "json", "mjs", "cjs", "tsx"}
												};
			
			
//			System.out.println( extenstionsForEachFolder[0][1]);
			
			File organizedFolder = new File(mainObj.getPathFolder().getText());
			String[] folderContents = organizedFolder.list();
			boolean done = true;
			for (int i=0; i< folderNamesR.length; ++i) {
				
				if(mainObj.getLanguageMode() == 1) {
					switch (i) {
						case 0: {
						
							folderNamesR[0] = "الصور";
						}
						case 1: {
							
							folderNamesR[1] = "الفيديوهات";
						}
						case 2: {
							
							folderNamesR[2] = "الأصوات والموسيقى";
						}
						case 3: {
							
							folderNamesR[3] = "المستندات";
						}
						case 4: {
							
							folderNamesR[4] = "المضغوطات";
						}
						case 5: {
							
							folderNamesR[5] = "البرامج";
						}
						case 6: {
							
							folderNamesR[6] = "اللغات البرمجية";
						}
						
					}
				
			
				}
				
				System.out.println(new File(mainObj.getPathFolder().getText()+"/"+folderNamesR[i]).mkdirs());
				
				for(int j=0; j< folderContents.length; ++j) {
					File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]);
					for(int k=0; k< extenstionsForEachFolderR[i].length; ++k) {
						if(organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".")+1).toLowerCase().equals(extenstionsForEachFolderR[i][k])){
							File targetPath = new File(mainObj.getPathFolder().getText()+"/"+folderNamesR[i]+"/"+folderContents[j]);
							System.out.println(targetPath.getAbsolutePath());
							organizedChecker.renameTo(targetPath);
						}

					}
					System.out.println("PASS");
					System.out.println();
						
				}
					
			}
					
			Set<String> folderExtentionFormat = new HashSet<String>();

			
			int count = 1;
			for (int i = 0;  i < folderContents.length; ++i) {
				File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[i]);
				if(organizedChecker.isFile()) {
					System.out.println("File "+count+": "+folderContents[i]);
					System.out.println("Extention: "+folderContents[i].substring(folderContents[i].lastIndexOf(".")+1).toLowerCase());
					folderExtentionFormat.add(folderContents[i].substring(folderContents[i].lastIndexOf(".")+1).toLowerCase());
					count++;
					System.out.println();
				}
			}
		
			for (int i=0; i< folderExtentionFormat.toArray().length ; ++i) {
				System.out.println(new File(mainObj.getPathFolder().getText()+"/"+folderExtentionFormat.toArray()[i]).mkdirs());
				
				for(int j=0; j< folderContents.length; ++j) {
					File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]);
					if(organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".")+1).toLowerCase().equals(folderExtentionFormat.toArray()[i])) {
						File targetPath = new File(mainObj.getPathFolder().getText()+"/"+folderExtentionFormat.toArray()[i]+"/"+folderContents[j]);
						System.out.println(targetPath.getAbsolutePath());
						organizedChecker.renameTo(targetPath);
						if(new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]).exists()) {
							done = false;
						}

					}
					
				}
				System.out.println("PASS");
				System.out.println();
			}
			
			
			System.out.println(done);
			
			
			
			
			Alert doneAlert = new Alert(AlertType.INFORMATION);
			Text content = new Text("");
			if(done) {
				if(mainObj.getLanguageMode() == 0) {
					doneAlert.setTitle("Fully Organized");
					doneAlert.setHeaderText("");
					content = new Text("The files are fully organized");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("OK");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
					
				} else {
					doneAlert.setTitle("نظم كليا");
					doneAlert.setHeaderText("");
					content = new Text("الملفات نظمت كليا");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar)doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
				}
			} else {
				if(mainObj.getLanguageMode() == 0) {
					doneAlert.setTitle("Partially Organized");
					doneAlert.setHeaderText("");
					content = new Text("The files are patially organized\n\nMaybe unorganized files are in use, open, protected, or there is a file with the same name inside the folder that is transferred to");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("OK");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		    Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
					
				} else {
					doneAlert.setTitle("نظم جزئيا");
					doneAlert.setHeaderText("");
					content = new Text("الملفات نظمت جزئيا\n\nمن الممكن أن الملفات غير المنتظمة قيد الاستخدام، او مفتوحة، أو محمية، أو هناك ملف بنفس الاسم داخل الملجد المنقول إليه ");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		  Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
=======
			btnLogo.setImage(new Image(getClass().getResource("/resources/Images/Back logo.png").toExternalForm()));
			btn.setGraphic(btnLogo);
		}
		else if(whichButton == 1) {
			btnLogo.setImage(new Image(getClass().getResource("/resources/Images/Help Logo.jpg").toExternalForm()));
			btn.setGraphic(btnLogo);
		}
        
		return btn;
	}
	
	public void changeTheme(OrganizeFolderMainUI mainObj, boolean byButton){
		if(byButton) {
			mainObj.changeMode(false, false, mainObj.getMainScene());
		}
		mainObj.setFirstTime(true);
		mainObj.changeMode(false, false, getRecommendedScene());
		mainObj.setFirstTime(false);
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
			chooseFolder.setText("اختر");
        	organize.setText("نظم");
		}
	}
	
	
	public void openCheck(OrganizeFolderMainUI mainObj){
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();
//		System.out.println(checkLanguage);
//		System.out.println(checkTheme);
		mainObj.openSetting(getRecommendedScene(), false, mainObj);
//		System.out.println(mainObj.getLanguageMode());
//		System.out.println(mainObj.getThemeMode());
		if(mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if(checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		}
		else {
//			System.out.println("Test");
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);
			
		}
		if(mainObj.getPathFolder().getText().equals("")) {
	        getOrganizeBtn().setDisable(true);
		}
		
	}
	
	public void saveAsCheck(OrganizeFolderMainUI mainObj){
		String checkPath = mainObj.getRunFileSettings();
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();

//		System.out.println(checkPath);
		mainObj.savedAsSetting(getRecommendedScene(), mainObj);
//		System.out.println(mainObj.getRunFileSettings());
		
		if(!checkPath.equals(mainObj.getRunFileSettings() )) {
			mainObj.getPathFolder().setText("");
	     	mainObj.getPathFolder().setDisable(true);
	        getOrganizeBtn().setDisable(true);

		}
		
		if(mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if(checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		}
		else {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);
			
		}
		
	}
	
	public void backsetting(OrganizeFolderMainUI mainObj) {
		
		mainObj.MainUI(mainObj, true);
	}
	
	public void helpSetting(OrganizeFolderMainUI mainObj) {
		Alert helpAlert = new Alert(AlertType.CONFIRMATION);
    	TextFlow flow = new TextFlow();
		if(mainObj.getLanguageMode() == 0) {
			 	helpAlert.setTitle("Help");
			    helpAlert.setHeaderText("How to use Recommended mode");
			    
			    Text rMode = new Text("Recommended mode: ");
			    Text bChoose = new Text("Choose button ");
			    Text mFile = new Text("File menu");
			    Text mSave = new Text("Save option ");
			    Text mSaveAs = new Text("Save As option ");
			    Text bOrganize = new Text("Organize button ");
			    Text note = new Text("Note: ");

			    
			    rMode.setStyle("-fx-font-weight: bold;");
			    bChoose.setStyle("-fx-font-weight: bold;");
			    mFile.setStyle("-fx-font-weight: bold;");
			    mSave.setStyle("-fx-font-weight: bold;");
			    mSaveAs.setStyle("-fx-font-weight: bold;");
			    bOrganize.setStyle("-fx-font-weight: bold;");
			    note.setStyle("-fx-font-weight: bold;");
			    
			    Text partOne = new Text("Organize the files of the chosen folder based on my recommended choice\n\n"+
						 "Instruction: \n\n"+
						 "1. Click ");
			    Text partTwo = new Text("to select the path (the folder location)\n\n"+
						 "2. Choose the path (the folder location) that you want to organize\n\n"+
			    		 "3. Choose the language that you want folders' names based on\n\n"+
						 "4. Make sure to save the settings at this specific mode (at the same window) by clicking ");
			    Text partThree = new Text(", and then ");
			    Text partFour = new Text("or ");
			    Text partFive = new Text("if you want to save in the new saved setting\n\n"+"5. Click ");
			    Text partSix = new Text("to organize the folder's files based on my recommended choice\n\n");
			    Text partSeven = new Text("If there are files' extension formats that are not in the recommended mode list, they will be automatically organized based on their extension formats");
		

			    partOne.setStyle("-fx-font-weight: normal;");
			    partTwo.setStyle("-fx-font-weight: normal;");
			    partThree.setStyle("-fx-font-weight: normal;");
			    partFour.setStyle("-fx-font-weight: normal;");
			    partFive.setStyle("-fx-font-weight: normal;");
			    partSix.setStyle("-fx-font-weight: normal;");
			    partSeven.setStyle("-fx-font-weight: normal;");
			    
			    flow.getChildren().addAll(rMode, partOne, bChoose, partTwo, mFile, partThree, mSave, partFour, mSaveAs, partFive, bOrganize, partSix, note, partSeven);
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
			    
			    if(mainObj.getThemeMode() == 1) {
			    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	note.setStyle("-fx-font-weight: bold; -fx-fill: white;");
			    	
			    	
			    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
			    	
			    	helpDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
			    	helpDialog.getStyleClass().add("Dark mode theme");
		
			    }
			    Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
			    Stage stageIcon = (Stage) window;
			    stageIcon.getIcons().add(programIcon);
		 	   		
	 	   		Optional<ButtonType> result = helpAlert.showAndWait();
	 	   		
	 	   		if(result.isPresent() && result.get() == OkButton) {
	 	   			helpAlert.close();
	 	   			
	 	   			
	 	   		}
		}
		else if(mainObj.getLanguageMode() == 1) {
			helpAlert.setTitle("مساعدة");
		    helpAlert.setHeaderText("كيف تستخدم الوضع الموصى");
		    
		    Text rMode = new Text("الوضع الموصى: ");
		    Text bChoose = new Text("زر اختر ");
		    Text mFile = new Text("قائمة ملف");
		    Text mSave = new Text("خيار حفظ ");
		    Text mSaveAs = new Text("خيار حفظ كـ ");
		    Text bOrganize = new Text("زر نظم ");
		    Text note = new Text("ملاحظة: ");
		    
		    rMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bChoose.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mFile.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mSave.setStyle("-fx-font-weight: bold; direction: ltr;");
		    mSaveAs.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bOrganize.setStyle("-fx-font-weight: bold; direction: ltr;");
		    note.setStyle("-fx-font-weight: bold; direction: ltr;");
		    


		     
		    Text partOne = new Text("ينظم ملفات المجلد المختار حسب ما استحسنه\n\n"+
					 "التعليمات: \n\n"+
					 "1. انقر  ");
		    Text partTwo = new Text("لتختار المسار (موقع المجلد)\n\n"+
					 "2. اختر المسار (موقع المجلد) الذي تريد أن ترتب ملفاته\n\n"+
					 "3. اختر اللغة التي تريد أن تكون أسماء المجلدات بها\n\n"+
					 "4. تأكد من أنك حفظت الإعدادات بهذا الوضع (بنفس هذه النافذة) وهذا عن طريق نقر ");
		    Text partThree = new Text("، ثم ");
		    Text partFour = new Text("أو ");
		    Text partFive = new Text("إذا كنت تريد أن تحفظ الإعدادات بتخزينة جديدة\n\n"+"5. انقر ");
		    Text partSix = new Text("لتنظم ملفات المجلد المختار حسب ما استحسنه\n\n");
		    Text partSeven = new Text("إذا كان هناك صيغ ملفات غير موجودة في قائمة الموصى بها، سترتب تلقائيا حسب صيغهم");
	
		    partOne.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partTwo.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partThree.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partFour.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partFive.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partSix.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partSeven.setStyle("-fx-font-weight: normal; direction: ltr;");
		    
		 
		    
		    flow.getChildren().addAll(rMode, partOne, bChoose, partTwo, mFile, partThree, mSave, partFour, mSaveAs, partFive, bOrganize, partSix, note, partSeven);
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
		    
		    if(mainObj.getThemeMode() == 1) {
		    	rMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	note.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	
		    	
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	
		    	helpDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
		    	helpDialog.getStyleClass().add("Dark mode theme");
	
		    }
		    Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
		    Stage stageIcon = (Stage) window;
		    stageIcon.getIcons().add(programIcon);
	 	   		
 	   		Optional<ButtonType> result = helpAlert.showAndWait();
 	   		
 	   		if(result.isPresent() && result.get() == OkButton) {
 	   			helpAlert.close();
 	   			
 	   			
 	   		}
		}
	    	 	    	
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
	 	
	 	getOrganizeBtn().setDisable(false);



	}
	
	public void organizeSetting(OrganizeFolderMainUI mainObj) {
		
			
		if(mainObj.getInitialSavedSetting() != 1) {
			Alert errorAlert = new Alert(AlertType.ERROR);
 	 	    Text content = new Text("");
 	 	    if(mainObj.getLanguageMode() == 0) {
 	 	    	errorAlert.setTitle("Error!");
 	 	 	    errorAlert.setHeaderText("");
 	 	 	    content = new Text("Error: Please, make sure to save or save as at this type mode to move on in the process");
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
			    
	 	 		if(mainObj.getThemeMode() == 1) {
	 	 		    content.setStyle("-fx-fill: white;");
	 	 		    errorDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		 }
	 	 		 Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
	 	 		 Stage stageIcon = (Stage) window;
	 	 		 stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   	 Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   	 if(result.isPresent() && result.get() == OkButton) {
	 	  	   		 errorAlert.close();	
	 	  	   		}
 	 	    	 } 
 	 	    	 else if(mainObj.getLanguageMode() == 1) {
 	 	    		errorAlert.setTitle("!خطأ");
 	 	 	    	errorAlert.setHeaderText("");
 	 	 	    	content = new Text("خطأ: رجاء، تأكد من الحفظ أو الحفظ كـ على هذا الوضع لتستكمل العملية!");
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
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	errorDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	errorDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
	 	 		    Stage stageIcon = (Stage) window;
	 	 		    stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   		Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   		if(result.isPresent() && result.get() == OkButton) {
	 	  	   			errorAlert.close();
	 	  	   			
	 	  	   			
	 	  	   		}
 	 			    
 	 	    	}
 	 	    		 	    	
 	 	    	return;
	 			    
	 	    }
	 	    		 	    	
	 	    
			
			// Fixed info
			String[] folderNamesR = {"Images", "Videos", "Sounds & Musics", "Documents", "Compressed", "Programs", "Programming Languages"};
			String[][] extenstionsForEachFolderR = { 
													{"png", "jpg", "gif", "apng", "avif", "jpeg", "jfif", "pjpeg", "pjp", "svg", "webp", "bmp", "ico", "cur", "tif",
													 "tiff", "jif", "jpe", "jfi", "psd", "raw", "arw", "cr2", "nrw", "k25", "dib", "heif", "heic", "svgz", "ai", "eps",
													 "jp2", "j2k", ".jpf", "jpx", "jpm", "mj2", "art", "pam", "blp", "act",  "dds", "hdr", "mng", "jps", "nef", "orf",
													 "vsdx", "xar", "pat", "drw", "cdr", "exr", "pcx", "pbm","pcd", "pfm", "pes", "pgm", "pnm", "picon", "ppm", "raf",
													 "ras", "ra2", "tga", "wbmp", "x3f", "xcf", "toast"},
													
													{"avi", "mkv", "mp4", "webm", "flv", "vob", "ogv",  "drc", "gifv", "mng", "MTS", "M2TS", "m2ts",
													 "TS", "mov", "qt", "wmv", "yuv", "rm", "rmvb", "viv", "asf", "amv", "m4v", "mpg", "mp2", "ogg", "mogg",
													 "mpeg", "mpe", "mpv", "m2v", "m4v", "svi", "3gp", "3g2", "mxf", "roq", "nsv", "f4v", "f4p", "f4a", "f4b"},
													 
													{"mp3", "3gp", "aa", "aac", "aax", "aiff", "alac", "amr", "ape", "au", "awb", "dss", "dvf", "flac",
												     "gsm", "iklax", "ivs", "m4a", "m4b", "m4p", "mmf", "movpkg", "mpc", "msv", "nmf",  "oga", "mid", "midi", 
												     "opus", "ra", "raw", "wav", "rf64", "sln", "tta", "voc", "vox", "wma", "wv", "8svx", "cda"},
													
													{"txt", "docx", "pdf", "xlsx", "doc", "dot", "wbk", "ooxml", "docm", "dotx", "dotm", "docb", "wll", "wwl", "xls",
												     "xlt", "xlm", "xlsm", "xltx", "xltm", "xlsb", "xla", "xlam", "xll", "xlw", "xll_", "xla_", "xla5", "xla8", "ppt",
												     "pot", "pps", "ppa", "pptm", "potx", "potm", "ppam", "ppsx", "ppsm", "sldx", "sldm", "one", "ecf", "pub", "odt",
												     "ott", "fodt", "uot", "xml", "rtf", "ots", "ods", "fods", "uos", "dif", "dbf", "slk", "csv", "ppts", "ppmx",
												     "pomx", "pom", "pptx", "md", "pages", "eml", "log", "msg", "wbs", "jpynb", "srt", "ssa", "ttml", "sbv",
												     "dfxp", "vtt", "itt", "scc", "ass", "fo", "ifoi"},
													
													{"rar", "zip", "7z", "s7z", "ace", "afa", "alz",  "arc", "ark", "cdx", "arj", "b1", "b6z", "ba", "bh",
													 "cab", "car", "cfs", "cpt", "dar", "dd", "dgc", "dmg",  "gca", "genozip", "ha", "hki", "ice", 
													 "kgb", "lzh", "lha", "lzx", "pak", "partimg", "paq6", ".paq7", "paq8", "pea", "phar", "pim", "pit", "qda",
													 "rk", "sda", "sea", "sen", "sfx", "shk", "sit", "sitx", "sqx", "tgz", "tbz2", "tlz", "txz", "uc", "uc0",
													 "uc2", "ucn", "ur2", "ue2", "uca", "uha",  "wim",  "xp3", "yz1", "zipx", "zoo", "zpaq", "zz",
													 "a", "ar", "cpio", "shar", "LBR", "iso", "lbr", "mar", "sbx", "tar", "br", "bz2", "F", "f", "?XF", "xf", "?xf", "genozip", "gz",
													 "lz", "lz4", "lzma", "lzo", "rz", "sfark", "sz", "q", "xz", "z", "Z", "zst"},
													
													{"exe", "lnk", "msi", "apk", "jar", "war", "ear", "app", "appx",  "deb", "hap", "ipa", "msix", "prm", "xap", "xbap",
												     "com", "bin", "bat", "wsf", "sys"},
													
													{"java", "py", "accda", "accdb", "accde", "accdr", "accdt", "accdu", "htm", "html", "xhtml", "css", "rss", "cer", "asp",
												     "aspx", "c", "cc", "abap", "php", "cs", "swift", "vb", "c#", "sql", "js", "ts", "net", "r", "asm", "i", "mac", "verilog",
												     "4th", "abc", "acd", "addin", "ads", "afphoto", "agi", "aia", "aidl", "alb", "aml", "ane", "apa", "apks", "jrxml", "jsp", 
												     "appxbundle", "appxupload", "aps", "arsc", "as", "as2proj", "as3proj", "asc", "asi", "asm", "asvf", "au3", "awk", "c3",
												     "caf", "cd", "cjs", "class", "cmake", "config", "cpp", "cs", "csproj", "csx", "cxx", "dart", "diff", "egg", "erb", "ex",
												     "exp", "fs", "g4", "cmd", "gmd", "go", "groovy", "h", "haml", "hh", "hpp", "hs", "hta", "ici", "in", "inc", "ino", "ipr",
												      "jav", "jspe", "kt", "lgo", "lua", "s", "m", "make", "mel", "mf", "ml", "mm", "mrc", "msix", "nupkg", "nut", "nx", "pas",
												      "pdb", "pde", "pl", "pyd", "pyi", "pym", "pyw", "pyx", "rb", "rbxl", "rbxm", "res", "rs", "rst", "scala", "scm", "script",
												      "sh", "sln", "tcl", "toml", "unity", "vbproj", "vbs", "vcproj", "vcxproj", "xds", "yaml", "yml", "ypr", "xps", "php3", "cfm",
												      "cfml", "jsx", "db", "nsf", "fp7", "te", "mar", "mts", "ctts", "json", "mjs", "cjs", "tsx"}
												};
			
			
//			System.out.println( extenstionsForEachFolder[0][1]);
			
			File organizedFolder = new File(mainObj.getPathFolder().getText());
			String[] folderContents = organizedFolder.list();
			boolean done = true;
			for (int i=0; i< folderNamesR.length; ++i) {
				
				if(mainObj.getLanguageMode() == 1) {
					switch (i) {
						case 0: {
						
							folderNamesR[0] = "الصور";
						}
						case 1: {
							
							folderNamesR[1] = "الفيديوهات";
						}
						case 2: {
							
							folderNamesR[2] = "الأصوات والموسيقى";
						}
						case 3: {
							
							folderNamesR[3] = "المستندات";
						}
						case 4: {
							
							folderNamesR[4] = "المضغوطات";
						}
						case 5: {
							
							folderNamesR[5] = "البرامج";
						}
						case 6: {
							
							folderNamesR[6] = "اللغات البرمجية";
						}
						
					}
				
			
				}
				
				System.out.println(new File(mainObj.getPathFolder().getText()+"/"+folderNamesR[i]).mkdirs());
				
				for(int j=0; j< folderContents.length; ++j) {
					File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]);
					for(int k=0; k< extenstionsForEachFolderR[i].length; ++k) {
						if(organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".")+1).toLowerCase().equals(extenstionsForEachFolderR[i][k])){
							File targetPath = new File(mainObj.getPathFolder().getText()+"/"+folderNamesR[i]+"/"+folderContents[j]);
							System.out.println(targetPath.getAbsolutePath());
							organizedChecker.renameTo(targetPath);
						}

					}
					System.out.println("PASS");
					System.out.println();
						
				}
					
			}
					
			Set<String> folderExtentionFormat = new HashSet<String>();

			
			int count = 1;
			for (int i = 0;  i < folderContents.length; ++i) {
				File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[i]);
				if(organizedChecker.isFile()) {
					System.out.println("File "+count+": "+folderContents[i]);
					System.out.println("Extention: "+folderContents[i].substring(folderContents[i].lastIndexOf(".")+1).toLowerCase());
					folderExtentionFormat.add(folderContents[i].substring(folderContents[i].lastIndexOf(".")+1).toLowerCase());
					count++;
					System.out.println();
				}
			}
		
			for (int i=0; i< folderExtentionFormat.toArray().length ; ++i) {
				System.out.println(new File(mainObj.getPathFolder().getText()+"/"+folderExtentionFormat.toArray()[i]).mkdirs());
				
				for(int j=0; j< folderContents.length; ++j) {
					File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]);
					if(organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".")+1).toLowerCase().equals(folderExtentionFormat.toArray()[i])) {
						File targetPath = new File(mainObj.getPathFolder().getText()+"/"+folderExtentionFormat.toArray()[i]+"/"+folderContents[j]);
						System.out.println(targetPath.getAbsolutePath());
						organizedChecker.renameTo(targetPath);
						if(new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]).exists()) {
							done = false;
						}

					}
					
				}
				System.out.println("PASS");
				System.out.println();
			}
			
			
			System.out.println(done);
			
			
			
			
			Alert doneAlert = new Alert(AlertType.INFORMATION);
			Text content = new Text("");
			if(done) {
				if(mainObj.getLanguageMode() == 0) {
					doneAlert.setTitle("Fully Organized");
					doneAlert.setHeaderText("");
					content = new Text("The files are fully organized");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("OK");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		  Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
					
				} else {
					doneAlert.setTitle("نظم كليا");
					doneAlert.setHeaderText("");
					content = new Text("الملفات نظمت كليا");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar)doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		  Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
				}
			} else {
				if(mainObj.getLanguageMode() == 0) {
					doneAlert.setTitle("Partially Organized");
					doneAlert.setHeaderText("");
					content = new Text("The files are patially organized\n\nMaybe unorganized files are in use, open, protected, or there is a file with the same name inside the folder that is transferred to");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("OK");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		  Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
					
				} else {
					doneAlert.setTitle("نظم جزئيا");
					doneAlert.setHeaderText("");
					content = new Text("الملفات نظمت جزئيا\n\nمن الممكن أن الملفات غير المنتظمة قيد الاستخدام، او مفتوحة، أو محمية، أو هناك ملف بنفس الاسم داخل الملجد المنقول إليه ");
					content.setStyle("-fx-font-size:20;");
					
					ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		doneAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = doneAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> doneAlert.close());
					
					DialogPane doneDialog  = doneAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    doneDialog.setContent(vBox);
	 	 		    doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    
	 	 		    ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
	 			    buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;")) ;
	 			    
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	doneDialog.getStylesheets().add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	doneDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    	
	 	 		  Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
>>>>>>> refs/heads/master
		 		    Stage stageIcon = (Stage) window;
		 		    stageIcon.getIcons().add(programIcon);
		 	 	   		
		  	   		Optional<ButtonType> result = doneAlert.showAndWait();
		  	   		
		  	   		if(result.isPresent() && result.get() == OkButton) {
		  	   			doneAlert.close();
		  	   			
		  	   			
		  	   		}
				}
			}
	
	
		
	}
	
}
