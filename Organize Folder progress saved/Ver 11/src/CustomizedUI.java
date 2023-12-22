import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

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
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;


public class CustomizedUI {
	
	
	private BorderPane customizedPane = new BorderPane();
	private Scene customizedScene = new Scene(getCustomizedPain(), 500, 500);
	
	private Button back = backOrHelpButtonProperty(0);
    private Button help = backOrHelpButtonProperty(1);
    
    Button chooseFolder = buttonProperty("Choose");
    Button custom = buttonProperty("Custom");
    Button organize = buttonProperty("Organize");
	
	public Scene  getCustomizedScene() {
		return this.customizedScene;
	}
	
	public BorderPane getCustomizedPain() {
		return this.customizedPane;
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
	
	public Button getCustomBtn() {
		return this.custom;
	}
	
	public Button getOrganizeBtn() {
		return this.organize;
	}

	public void runCustomizedUI(OrganizeFolderMainUI mainObj) {
		
		getCustomizedPain().getChildren().clear();


		mainObj.setIsChangeTheme(false);
		


		
		mainObj.getPathFolder().setText("");
		
		changeTheme(mainObj, false);
    	mainObj.getLightDarkMode().setOnAction(e -> changeTheme(mainObj, true));
    	mainObj.getLanguageButton().setOnAction(e -> changeLanguage(mainObj, true));

        
        

        
        getBackBtn().setOnAction(e -> backsetting(mainObj));
        getHelpBtn().setOnAction(e -> helpSetting(mainObj));
        
        
        getOrganizeBtn().setDisable(true);
        
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
        hBoxTop.getChildren().addAll(getHelpBtn(), mainObj.getLightDarkMode(), mainObj.getLanguageButton(),getBackBtn());
        hBoxTop.setAlignment(Pos.TOP_RIGHT);
        
        VBox vBoxTop = new VBox();
        vBoxTop.getChildren().addAll(mainObj.getMenuBar(), hBoxTop);
        
        HBox buttonCenter = new HBox();
        buttonCenter.getChildren().addAll(getCustomBtn(), getOrganizeBtn());
        buttonCenter.setSpacing(15);
        buttonCenter.setAlignment(Pos.CENTER);
        
        VBox vBoxCenter = new VBox();
        vBoxCenter.getChildren().addAll(getChooseFolderBtn(), mainObj.getPathFolder(), buttonCenter);
        vBoxCenter.setSpacing(10);
        vBoxCenter.setAlignment(Pos.CENTER);
        
       
        getCustomizedPain().setTop(vBoxTop);
        getCustomizedPain().setCenter(vBoxCenter);
        mainObj.getPrimaryStage().setScene(getCustomizedScene());
        
        if(mainObj.getFirstTime()) {
        	mainObj.getPrimaryStage().show();
        }
		mainObj.setTypeMode(2);

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
	
	public void changeTheme(OrganizeFolderMainUI mainObj, boolean byButton){
		if(byButton) {
			mainObj.changeMode(false, false, mainObj.getMainScene());
		}
		mainObj.setFirstTime(true);
		mainObj.changeMode(false, false, getCustomizedScene());
		mainObj.setFirstTime(false);
	}
	
	public void changeLanguage(OrganizeFolderMainUI mainObj, boolean byButton) {
		if(byButton) {
			mainObj.changeLanguage();
		}
		if(mainObj.getLanguageMode() == 0) {
			chooseFolder.setText("Choose");
        	custom.setText("Custom");
        	organize.setText("Organize");
		}
		else if(mainObj.getLanguageMode() == 1) {
			chooseFolder.setText("اختر");
        	custom.setText("خصص");
        	organize.setText("نظم");
		}
	}
	
	
	public void openCheck(OrganizeFolderMainUI mainObj){
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();
//		System.out.println(checkLanguage);
//		System.out.println(checkTheme);
		mainObj.openSetting(getCustomizedScene(), false, mainObj);
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
		mainObj.savedAsSetting(getCustomizedScene(), mainObj);
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
		    helpAlert.setHeaderText("How to use customized mode");
		    
		    Text cMode = new Text("Customized mode: ");
		    Text bChoose = new Text("Choose button ");
		    Text bOrganize = new Text("Organize button ");
		    Text bCustom = new Text("Custom button");
		    
		    cMode.setStyle("-fx-font-weight: bold;");
		    bChoose.setStyle("-fx-font-weight: bold;");
		    bOrganize.setStyle("-fx-font-weight: bold;");
		    bCustom.setStyle("-fx-font-weight: bold;");
		    
		    Text partOne = new Text("Organize the files of the chosen folder based on your customized choice\n\n"+
					 "Instruction: \n\n"+
					 "1. In progress ");
		    Text partTwo = new Text(" \n\n"+
					 "2. In progress \n\n"+"3. In progress ");
		    Text partThree = new Text(" ");
	

		    partOne.setStyle("-fx-font-weight: normal;");
		    partTwo.setStyle("-fx-font-weight: normal;");
		    partThree.setStyle("-fx-font-weight: normal;");
		    
		    flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bOrganize, partThree, bCustom);
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
		    if(mainObj.getThemeMode() == 1) {
		    	cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	
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
		else if(mainObj.getLanguageMode() == 1) {
			helpAlert.setTitle("مساعدة");
		    helpAlert.setHeaderText("كيف تستخدم الوضع المخصص");
		    
		    Text cMode = new Text("الوضع المخصص: ");
		    Text bChoose = new Text("زر اختر ");
		    Text bOrganize = new Text("زر نظم ");
		    Text bCustom = new Text("زر خصص ");
		    
		    cMode.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bChoose.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bOrganize.setStyle("-fx-font-weight: bold; direction: ltr;");
		    bCustom.setStyle("-fx-font-weight: bold;");

		    
		    Text partOne = new Text("ينظم ملفات المجلد المختار حسب ما تخصصه\n\n"+
					 "التعليمات: \n\n"+
					 "1. يعالج حاليا  ");
		    Text partTwo = new Text(" \n\n"+
					 "2. يعالج حاليا \n\n"+
					 "3.يعالج حاليا ");
		    Text partThree = new Text(" ");
	

		    partOne.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partTwo.setStyle("-fx-font-weight: normal; direction: ltr;");
		    partThree.setStyle("-fx-font-weight: normal; direction: ltr;");
		    
		    flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bOrganize, partThree, bCustom);
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
		    if(mainObj.getThemeMode() == 1) {
		    	cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
		    	partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
		    	
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
	 	 		if(mainObj.getThemeMode() == 1) {
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
 	 	    	 else if(mainObj.getLanguageMode() == 1) {
 	 	    		errorAlert.setTitle("!خطأ");
 	 	 	    	errorAlert.setHeaderText("");
 	 	 	    	content = new Text("خطأ: رجاء، تأكد من الحفظ أو الحفظ كـ على هذا الوضع لتستكمل العملية!");
 	 			    content.setStyle("-fx-font-size:20; direction: ltr;");
 	 			    
	 	 			ButtonType OkButton = new ButtonType("حسنا");
	 	  	   		errorAlert.getButtonTypes().setAll(OkButton);
	 	  	   		
	 	 	    	Window window = errorAlert.getDialogPane().getScene().getWindow();
	 	  	   		
	 	  	   		window.setOnCloseRequest(e -> errorAlert.close());
	 	  	   		
	 	 	 	   	DialogPane aboutDialog  = errorAlert.getDialogPane();
	 	 		    VBox vBox = new VBox();
	 	 		    content.setTextAlignment(TextAlignment.CENTER);
	 	 		    vBox.getChildren().add(content);
	 	 		    aboutDialog.setContent(vBox);
	 	 		    aboutDialog.setStyle("-fx-font-size:20; -fx-align: center;");
	 	 		    if(mainObj.getThemeMode() == 1) {
	 	 		    	content.setStyle("-fx-fill: white;");
	 	 		    	aboutDialog.getStylesheets().add(getClass().getResource("Resources/Styles/Dark mode theme.css").toExternalForm());
	 	 		    	aboutDialog.getStyleClass().add("Dark mode theme");
	 	 	
	 	 		    }
	 	 		    Image programIcon = new Image("Resources/Images/Program icon.png");
	 	 		    Stage stageIcon = (Stage) window;
	 	 		    stageIcon.getIcons().add(programIcon);
	 	 	 	   		
	 	  	   		Optional<ButtonType> result = errorAlert.showAndWait();
	 	  	   		
	 	  	   		if(result.isPresent() && result.get() == OkButton) {
	 	  	   			errorAlert.close();
	 	  	   			
	 	  	   			
	 	  	   		}
 	 			    
 	 	    	}
 	 	    		 	    	
 	 	    	return;
	 			    
	 	    }
	 	    		 	    	
	 	    	
			
			// extract info from the saved info file
			ArrayList<String> folderNames = new ArrayList<>();
			ArrayList<ArrayList<String>> extenstionsForEachFolder = new ArrayList<>();
			File saved = new File("C:\\Users\\Aboaljod59\\Downloads\\Video\\New Text Document.txt");
			
			try (Scanner scan = new Scanner(saved)) {
				while(scan.hasNextLine()){
					StringBuffer extenstion = new StringBuffer();
					ArrayList<String> extenstions = new ArrayList<>();
					String checkFirstString = scan.next();
					if(checkFirstString.charAt(0) == '{') {
						
						extenstion.append(checkFirstString.substring(1, checkFirstString.length() - 1));
						extenstions.add(extenstion.toString());
						
						if(checkFirstString.charAt(checkFirstString.length()-1) != '}') {
							System.out.print(checkFirstString+" ");
							StringBuffer checkRemainingExtenstion = new StringBuffer();
							for(; scan.hasNext();) {
								checkRemainingExtenstion.append(scan.next());
//								System.out.println(checkExtenstion);
								if(checkRemainingExtenstion.charAt(checkRemainingExtenstion.length()-1) == '}') {
									extenstion.replace(0, extenstion.length(), checkRemainingExtenstion.substring(0, checkRemainingExtenstion.length() - 1));
									extenstions.add(extenstion.toString());
									System.out.println(checkRemainingExtenstion+" ");
									break;
								}
								else {
									extenstion.replace(0, extenstion.length(),checkRemainingExtenstion.substring(0, checkRemainingExtenstion.length() - 1));
									extenstions.add(extenstion.toString());
									System.out.print(checkRemainingExtenstion+" ");

									
								}
								checkRemainingExtenstion.replace(0, checkRemainingExtenstion.length(), "");
								
							}
							
//							System.out.println(extenstions.toString());
							System.out.println();
							
						}
						else {
							System.out.println(checkFirstString);
//							System.out.println(extenstions.toString());
							System.out.println();

						}
						

						extenstionsForEachFolder.add(extenstions);
						
							
					}
					else {
						for(; scan.hasNext();) {
							if(checkFirstString.charAt(checkFirstString.length()-1) == ':') {
								extenstion.append(checkFirstString.replace(":", ""));
								break;
							} 
							else {
								extenstion.append(checkFirstString+" ");
								checkFirstString = scan.next();
							}
						
					}
					if(!scan.hasNext()) {
						extenstion.append(checkFirstString.replace(".", ""));
					}
					System.out.println("F: "+extenstion.toString());
					folderNames.add(extenstion.toString());
					
					
				}
			}
			System.out.println("Folder Names: "+folderNames.toString());
			System.out.println("Extenstion for each folder: "+extenstionsForEachFolder.toString());
			
			File organizedFolder = new File(mainObj.getPathFolder().getText());
			String[] folderContents = organizedFolder.list();
			
			boolean done = true;
			for (int i=0; i< folderNames.toArray().length ; ++i) {
				System.out.println(new File(mainObj.getPathFolder().getText()+"/"+folderNames.toArray()[i]).mkdirs());
				
//				for(int j=0; j< folderContents.length; ++j) {
//					File organizedChecker = new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]);
//					if(organizedChecker.isFile() && folderContents[j].substring(folderContents[j].indexOf(".")+1).equals(folderExtentionFormat.toArray()[i])) {
//						File targetPath = new File(mainObj.getPathFolder().getText()+"/"+folderExtentionFormat.toArray()[i]+"/"+folderContents[j]);
//						System.out.println(targetPath.getAbsolutePath());
//						organizedChecker.renameTo(targetPath);
//						if(new File(mainObj.getPathFolder().getText()+"/"+folderContents[j]).exists()) {
//							done = false;
//						}
//
//					}
//					
				}
				System.out.println("PASS");
				System.out.println();
			}
			
		
			catch(IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
	
		
	}
	
	
}
