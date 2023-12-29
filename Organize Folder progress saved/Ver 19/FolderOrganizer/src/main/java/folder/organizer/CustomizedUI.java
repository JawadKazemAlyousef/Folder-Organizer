package folder.organizer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.stage.FileChooser.ExtensionFilter;

public class CustomizedUI {

	private BorderPane customizedPane = new BorderPane();
	private Scene customizedScene = new Scene(getCustomizedPain(), 500, 500);

	private Button back = backOrHelpButtonProperty(0);
	private Button help = backOrHelpButtonProperty(1);

	Button chooseFolder = buttonProperty("Choose");
	Button custom = buttonProperty("Customize");
	Button upload = buttonProperty("Upload");
	Button organize = buttonProperty("Organize");

	boolean isUpdateInfoViaCustom = false;
	private ArrayList<String> folderNames = new ArrayList<String>();
	private ArrayList<ArrayList<String>> extensionsForEachFolder = new ArrayList<ArrayList<String>>();

	public Scene getCustomizedScene() {
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

	public Button getUploadBtn() {
		return this.upload;
	}

	public boolean getIsUpdateInfoViaCustom() {
		return this.isUpdateInfoViaCustom;
	}

	public void setIsUpdateInfoViaCustom(boolean mode) {
		this.isUpdateInfoViaCustom = mode;
	}

	public ArrayList<String> getFolderNames() {
		return this.folderNames;
	}

	public ArrayList<ArrayList<String>> getExtensionsForEachFolder() {
		return this.extensionsForEachFolder;
	}

	public void runCustomizedUI(OrganizeFolderMainUI mainObj) {

		getCustomizedPain().getChildren().clear();
		mainObj.setTypeMode(2);
		extractInfo(mainObj, "");

		mainObj.setIsChangeTheme(false);

		mainObj.getPathFolder().setText("");

		changeTheme(mainObj, false);
		mainObj.getLightDarkMode().setOnAction(e -> changeTheme(mainObj, true));
		mainObj.getLanguageButton().setOnAction(e -> changeLanguage(mainObj, true));

		getBackBtn().setOnAction(e -> backsetting(mainObj));
		getHelpBtn().setOnAction(e -> helpSetting(mainObj));

		getOrganizeBtn().setDisable(true);
		getCustomBtn().setDisable(true);
		getUploadBtn().setDisable(true);

		changeLanguage(mainObj, false);

		getChooseFolderBtn().setOnAction(e -> chooseSetting(mainObj));
		getCustomBtn().setOnAction(e -> customSetting(mainObj));

		getUploadBtn().setOnAction(e -> uploadSetting(mainObj));

		getOrganizeBtn().setOnAction(e -> organizeSetting(mainObj));

		mainObj.getPathFolder().setMaxWidth(350);
		mainObj.getPathFolder().setStyle("-fx-background-radius: 90px; -fx-alignment: center");
		mainObj.getPathFolder().setDisable(true);

		mainObj.getOpen().setOnAction(e -> openCheck(mainObj));
		mainObj.getSave().setOnAction(e -> savedSetting(mainObj));
		mainObj.getSaveAs().setOnAction(e -> saveAsCheck(mainObj));
		mainObj.getHelp().setOnAction(e -> mainObj.helpSetting());
		mainObj.getAbout().setOnAction(e -> mainObj.aboutSetting());

		HBox hBoxTop = new HBox();
		hBoxTop.getChildren().addAll(getHelpBtn(), mainObj.getLightDarkMode(), mainObj.getLanguageButton(),
				getBackBtn());
		hBoxTop.setAlignment(Pos.TOP_RIGHT);

		VBox vBoxTop = new VBox();
		vBoxTop.getChildren().addAll(mainObj.getMenuBar(), hBoxTop);

		HBox buttonCenter = new HBox();
		buttonCenter.getChildren().addAll(getCustomBtn(), getUploadBtn(), getOrganizeBtn());
		buttonCenter.setSpacing(15);
		buttonCenter.setAlignment(Pos.CENTER);

		VBox vBoxCenter = new VBox();
		vBoxCenter.getChildren().addAll(getChooseFolderBtn(), mainObj.getPathFolder(), buttonCenter);
		vBoxCenter.setSpacing(10);
		vBoxCenter.setAlignment(Pos.CENTER);

		getCustomizedPain().setTop(vBoxTop);
		getCustomizedPain().setCenter(vBoxCenter);
		mainObj.getPrimaryStage().setScene(getCustomizedScene());

		if (mainObj.getFirstTime()) {
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
		if (whichButton == 0) {
<<<<<<< HEAD
			btnLogo.setImage(new Image(new File("resources/Images/Back logo.png").toURI().toString()));
			btn.setGraphic(btnLogo);
		} else if (whichButton == 1) {
			btnLogo.setImage(new Image(new File("resources/Images/Help Logo.jpg").toURI().toString()));
			btn.setGraphic(btnLogo);
		}

		return btn;
	}

	public void changeTheme(OrganizeFolderMainUI mainObj, boolean byButton) {
		if (byButton) {
			mainObj.changeMode(false, false, mainObj.getMainScene());
		}
		mainObj.setFirstTime(true);
		mainObj.changeMode(false, false, getCustomizedScene());
		mainObj.setFirstTime(false);
	}

	public void changeLanguage(OrganizeFolderMainUI mainObj, boolean byButton) {
		if (byButton) {
			mainObj.changeLanguage();
		}
		if (mainObj.getLanguageMode() == 0) {
			getChooseFolderBtn().setText("Choose");
			getCustomBtn().setText("Customize");
			getOrganizeBtn().setText("Organize");
			getUploadBtn().setText("Upload");
		} else if (mainObj.getLanguageMode() == 1) {
			getChooseFolderBtn().setText("اختر");
			getCustomBtn().setText("خصص");
			getOrganizeBtn().setText("نظم");
			getUploadBtn().setText("ارفع");
		}
	}

	public void openCheck(OrganizeFolderMainUI mainObj) {
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();
//		System.out.println(checkLanguage);
//		System.out.println(checkTheme);
		mainObj.openSetting(getCustomizedScene(), false, mainObj);
//		System.out.println(mainObj.getLanguageMode());
//		System.out.println(mainObj.getThemeMode());
		if (mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if (checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		} else {
//			System.out.println("Test");
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);

		}
		if (mainObj.getPathFolder().getText().equals("")) {
			getOrganizeBtn().setDisable(true);
		}
		if (mainObj.getSuccessfullyOpen()) {
			extractInfo(mainObj, "");
			setIsUpdateInfoViaCustom(false);
		}

	}

	public void savedSetting(OrganizeFolderMainUI mainObj) {
		mainObj.savedSetting();

		String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");

		try (FileWriter writer = new FileWriter(savedInfoPath)) {
			writer.write("");

			for (int i = 0; i < getFolderNames().size(); ++i) {
				writer.append(getFolderNames().get(i) + ":\n");
				if (getExtensionsForEachFolder().get(i).size() == 0) {
					if (i == getFolderNames().size() - 1) {
						writer.append("{}");
					} else {
						writer.append("{}\n");

					}
				}
				for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
					if (j == 0 && getExtensionsForEachFolder().get(i).size() == 1) {
						if (i == getFolderNames().size() - 1) {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}");
						} else {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}\n");

						}
					} else if (j == 0) {
						writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + ", ");
					} else if (j == getExtensionsForEachFolder().get(i).size() - 1
							&& i == getFolderNames().size() - 1) {
						writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
					} else if (j == getExtensionsForEachFolder().get(i).size() - 1) {
						if (i == getFolderNames().size() - 1) {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
						} else {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}\n");

						}

					} else {
						writer.append(getExtensionsForEachFolder().get(i).get(j) + ", ");
					}
				}

			}

			setIsUpdateInfoViaCustom(false);
			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void saveAsCheck(OrganizeFolderMainUI mainObj) {
		String checkPath = mainObj.getRunFileSettings();
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();

//		System.out.println(checkPath);
		mainObj.savedAsSetting(getCustomizedScene(), mainObj);
//		System.out.println(mainObj.getRunFileSettings());

		if (!checkPath.equals(mainObj.getRunFileSettings())) {
			mainObj.getPathFolder().setText("");
			mainObj.getPathFolder().setDisable(true);
			getOrganizeBtn().setDisable(true);

		}

		if (mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if (checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		} else {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);

		}

		if (mainObj.getSuccessfullySaveAs()) {
			String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");

			try (FileWriter writer = new FileWriter(savedInfoPath)) {
				writer.write("");

				for (int i = 0; i < getFolderNames().size(); ++i) {
					writer.append(getFolderNames().get(i) + ":\n");
					if (getExtensionsForEachFolder().get(i).size() == 0) {
						if (i == getFolderNames().size() - 1) {
							writer.append("{}");
						} else {
							writer.append("{}\n");

						}
					}
					for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
						if (j == 0 && getExtensionsForEachFolder().get(i).size() == 1) {
							if (i == getFolderNames().size() - 1) {
								writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}");
							} else {
								writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}\n");

							}
						} else if (j == 0) {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + ", ");
						} else if (j == getExtensionsForEachFolder().get(i).size() - 1
								&& i == getFolderNames().size() - 1) {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
						} else if (j == getExtensionsForEachFolder().get(i).size() - 1) {
							if (i == getFolderNames().size() - 1) {
								writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
							} else {
								writer.append(getExtensionsForEachFolder().get(i).get(j) + "}\n");

							}

						} else {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + ", ");
						}
					}

				}

				setIsUpdateInfoViaCustom(false);
				System.out.println("Successfully wrote to the file.");

			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		}

	}

	public void backsetting(OrganizeFolderMainUI mainObj) {
		mainObj.MainUI(mainObj, true);
	}

	public void helpSetting(OrganizeFolderMainUI mainObj) {
		Alert helpAlert = new Alert(AlertType.CONFIRMATION);
		TextFlow flow = new TextFlow();
		if (mainObj.getLanguageMode() == 0) {
			helpAlert.setTitle("Help");
			helpAlert.setHeaderText("How to use customized mode");

			Text cMode = new Text("Customized mode: ");
			Text bChoose = new Text("Choose button ");
			Text bOrganize = new Text("Organize button ");
			Text bCustom = new Text("Customize button");
			Text bOK = new Text("OK button ");
			Text mFile = new Text("File menu");
			Text mSave = new Text("Save option ");
			Text mSaveAs = new Text("Save As option ");
			Text bUpload = new Text("Upload button ");
			Text note = new Text("Note: ");

			cMode.setStyle("-fx-font-weight: bold;");
			bChoose.setStyle("-fx-font-weight: bold;");
			bOrganize.setStyle("-fx-font-weight: bold;");
			bCustom.setStyle("-fx-font-weight: bold;");
			bOK.setStyle("-fx-font-weight: bold;");
			mFile.setStyle("-fx-font-weight: bold;");
			mSave.setStyle("-fx-font-weight: bold;");
			mSaveAs.setStyle("-fx-font-weight: bold;");
			bUpload.setStyle("-fx-font-weight: bold;");
			note.setStyle("-fx-font-weight: bold;");

			Text partOne = new Text("Organize the files of the chosen folder based on your customized choice\n\n"
					+ "Instruction: \n\n" + "1. Click ");
			Text partTwo = new Text("to select the path (the folder location)\n\n"
					+ "2. Choose the path (the folder location) that you want to organize\n\n"
					+ "3. You can either manually customize your organization or upload your customized saved file\n\n"
					+ "A. By clicking ");
			Text partThree = new Text(",  you can manually customize your organization\n" + "Make sure to click ");
			Text partFour = new Text("to update the new changes\n\n" + "B. By clicking ");
			Text partFive = new Text(",  you can upload a customized saved file\n"
					+ "Make sure to choose the saved file in .ifoi extension format and the contents be something like: \n\r"
					+ "Folder_name:\n" + "{Extension_name, Extension_name}\n" + "Folder_name:\n" + "{Extension_name}\n"
					+ "Folder_name:\n" + "{}\n"
					+ "Folder_name must be a valid name, and it can be multiple words\n"
					+ "Extension_name must be a valid name without a dot (.), and it can be multiple words\n\n"
					+ "4. Make sure to save the settings at this specific mode (at the same window) by clicking ");
			Text partSix = new Text(", and then ");
			Text partSeven = new Text("or ");
			Text partEight = new Text("if you want to save in the new saved setting\n\n" + "5. Click ");
			Text partNine = new Text("to organize the folder's files based on your customized choice\n\n");
			Text partTen = new Text(
					"If there are files' extension formats that are not in your customized list, they will be automatically organized based on their extension formats");

			partOne.setStyle("-fx-font-weight: normal;");
			partTwo.setStyle("-fx-font-weight: normal;");
			partThree.setStyle("-fx-font-weight: normal;");
			partFour.setStyle("-fx-font-weight: normal;");
			partFive.setStyle("-fx-font-weight: normal;");
			partSix.setStyle("-fx-font-weight: normal;");
			partSeven.setStyle("-fx-font-weight: normal;");
			partEight.setStyle("-fx-font-weight: normal;");
			partNine.setStyle("-fx-font-weight: normal;");
			partTen.setStyle("-fx-font-weight: normal;");

			flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bCustom, partThree, bOK, partFour, bUpload,
					partFive, mFile, partSix, mSave, partSeven, mSaveAs, partEight, bOrganize, partNine, note, partTen);
			flow.setTextAlignment(TextAlignment.CENTER);

			ButtonType OkButton = new ButtonType("OK");
			helpAlert.getButtonTypes().setAll(OkButton);

			Window window = helpAlert.getDialogPane().getScene().getWindow();

			window.setOnCloseRequest(e -> helpAlert.close());

			DialogPane helpDialog = helpAlert.getDialogPane();
			VBox vBox = new VBox();
			vBox.getChildren().add(flow);
			helpDialog.setContent(vBox);
			helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

			ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
			buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

			if (mainObj.getThemeMode() == 1) {
				cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOK.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bUpload.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				note.setStyle("-fx-font-weight: bold; -fx-fill: white;");

				partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				;
				partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partEight.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partNine.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTen.setStyle("-fx-font-weight: normal; -fx-fill: white;");

				helpDialog.getStylesheets()
						.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
				helpDialog.getStyleClass().add("Dark mode theme");

			}
			Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
			Stage stageIcon = (Stage) window;
			stageIcon.getIcons().add(programIcon);

			Optional<ButtonType> result = helpAlert.showAndWait();

			if (result.isPresent() && result.get() == OkButton) {
				helpAlert.close();

			}
		} else if (mainObj.getLanguageMode() == 1) {

			helpAlert.setTitle("مساعدة");
			helpAlert.setHeaderText("كيف تستخدم الوضع المخصص");

			Text cMode = new Text("الوضع المخصص: ");
			Text bChoose = new Text("زر اختر ");
			Text bOrganize = new Text("زر نظم ");
			Text bCustom = new Text("زر خصص");
			Text bOK = new Text("زر حسنا ");
			Text mFile = new Text("قائمة ملف ");
			Text mSave = new Text("خيار حفظ ");
			Text mSaveAs = new Text("خيار حفظ كـ ");
			Text bUpload = new Text("زر ارفع ");
			Text note = new Text("ملاحظة: ");

			cMode.setStyle("-fx-font-weight: bold;");
			bChoose.setStyle("-fx-font-weight: bold;");
			bOrganize.setStyle("-fx-font-weight: bold;");
			bCustom.setStyle("-fx-font-weight: bold;");
			bOK.setStyle("-fx-font-weight: bold;");
			mFile.setStyle("-fx-font-weight: bold;");
			mSave.setStyle("-fx-font-weight: bold;");
			mSaveAs.setStyle("-fx-font-weight: bold;");
			bUpload.setStyle("-fx-font-weight: bold;");
			note.setStyle("-fx-font-weight: bold;");

			Text partOne = new Text("ينظم ملفات المجلد المختار حسب ما تخصصه\n\n" + "التعليمات: \n\n" + "1. انقر ");
			Text partTwo = new Text(
					"لتختار المسار (موقع المجلد)\n\n" + "2. اختر المسار (موقع المجلد) الذي تريد أن ترتب ملفاته\n\n"
							+ "3. أنت تستطيع تخصيص تنظيمك يدويا، أو رفع تخزينتك المخصصة\n\n" + "أ. بالنقر على ");
			Text partThree = new Text("،  أنت تستطيع تخصيص تنظيمك يدويا \n" + "تأكد من نقر ");
			Text partFour = new Text("لتحديث تغيراتك الجديدة\n\n" + "ب. بالنقر على ");
			Text partFive = new Text("،  أنت تستطيع رفع تخزينتك المخصصة\n"
					+ "تأكد من اختيار تخزينتك بصيغة .ifoi ويكون المحتوى كهذا:  \n\r" + "اسم_المجلد:\n"
					+ "{اسم_الصيغة, اسم_الصيغة}\n" + "اسم_المجلد:\n" + "{اسم_الصيغة}\n" + "اسم_المجلد:\n"
					+ "{}\n" 
					+ "اسم_المجلد يجب أن يكون مسموحا، وممكن أن يكون من كلمات متعددة\n"
					+ "اسم_الصيغة يجب أن يكون مسموحا دون نقطة (.)، وممكن أن يكون من كلمات متعددة\n\n"
					+ "4. تأكد من أنك حفظت الإعدادات بهذا الوضع (بنفس هذه النافذة) وهذا عن طريق نقر ");
			Text partSix = new Text("، ثم ");
			Text partSeven = new Text("أو ");
			Text partEight = new Text("إذا كنت تريد أن تحفظ الإعدادات بتخزينة جديدة\n\n" + "5. انقر ");
			Text partNine = new Text("لتنظم ملفات المجلد المختار حسب ما تخصصه\n\n");
			Text partTen = new Text("إذا كان هناك صيغ ملفات غير موجودة في قائمتك المخصصة، سترتب تلقائيا حسب صيغهم");

			partOne.setStyle("-fx-font-weight: normal;");
			partTwo.setStyle("-fx-font-weight: normal;");
			partThree.setStyle("-fx-font-weight: normal;");
			partFour.setStyle("-fx-font-weight: normal;");
			partFive.setStyle("-fx-font-weight: normal;");
			partSix.setStyle("-fx-font-weight: normal;");
			partSeven.setStyle("-fx-font-weight: normal;");
			partEight.setStyle("-fx-font-weight: normal;");
			partNine.setStyle("-fx-font-weight: normal;");
			partTen.setStyle("-fx-font-weight: normal;");

			flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bCustom, partThree, bOK, partFour, bUpload,
					partFive, mFile, partSix, mSave, partSeven, mSaveAs, partEight, bOrganize, partNine, note, partTen);
			flow.setTextAlignment(TextAlignment.CENTER);

			ButtonType OkButton = new ButtonType("حسنا");
			helpAlert.getButtonTypes().setAll(OkButton);

			Window window = helpAlert.getDialogPane().getScene().getWindow();

			window.setOnCloseRequest(e -> helpAlert.close());

			DialogPane helpDialog = helpAlert.getDialogPane();
			VBox vBox = new VBox();
			vBox.getChildren().add(flow);
			helpDialog.setContent(vBox);
			helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

			ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
			buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

			if (mainObj.getThemeMode() == 1) {
				cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOK.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bUpload.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				note.setStyle("-fx-font-weight: bold; -fx-fill: white;");

				partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				;
				partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partEight.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partNine.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTen.setStyle("-fx-font-weight: normal; -fx-fill: white;");

				helpDialog.getStylesheets()
						.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
				helpDialog.getStyleClass().add("Dark mode theme");

			}
			Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
			Stage stageIcon = (Stage) window;
			stageIcon.getIcons().add(programIcon);

			Optional<ButtonType> result = helpAlert.showAndWait();

			if (result.isPresent() && result.get() == OkButton) {
				helpAlert.close();

			}

		}

	}

	public void chooseSetting(OrganizeFolderMainUI mainObj) {
		DirectoryChooser chooseFolder = new DirectoryChooser();
		chooseFolder.setTitle("Choose");
		File chooseDialog = chooseFolder.showDialog(mainObj.getPrimaryStage());
		if (chooseDialog == null) {
			return;
		}

		String choosePath = chooseDialog.toString();

		mainObj.getPathFolder().setText(choosePath);
		mainObj.getPathFolder().setDisable(false);
		mainObj.getPathFolder().setEditable(false);

		getOrganizeBtn().setDisable(false);
		getCustomBtn().setDisable(false);
		getUploadBtn().setDisable(false);

	}

	public void uploadSetting(OrganizeFolderMainUI mainObj) {

		File recommendedPath = new File("resources/Mode settings");
		System.out.println(recommendedPath);
		FileChooser uploadController = new FileChooser();
		uploadController.setTitle("Upload");
		uploadController.getExtensionFilters().addAll(new ExtensionFilter("ifoi", "*.ifoi*"));
		uploadController.setInitialDirectory(recommendedPath);
		
		
		File uploadDialog = uploadController.showOpenDialog(mainObj.getPrimaryStage());
		if (uploadDialog == null) {
			return;

		} else {
			extractInfo(mainObj, uploadDialog.toString());

			Alert cautionAlert = new Alert(AlertType.INFORMATION);
			Text content = new Text("");
			if (mainObj.getLanguageMode() == 0) {
				cautionAlert.setTitle("Caution!");
				cautionAlert.setHeaderText("");
				content = new Text(
						"Caution: Make sure to Save or Save As to save your customization for the next time\n"
								+ "Your cutomization will be canceled if your press back button, open saved file, or close the program without save your customization");
				content.setStyle("-fx-font-size:20; -fx-font-weight: normal;");

				ButtonType OkButton = new ButtonType("OK");
				cautionAlert.getButtonTypes().setAll(OkButton);
				DialogPane cautionDialog = cautionAlert.getDialogPane();

				Window window = cautionDialog.getScene().getWindow();

				window.setOnCloseRequest(ee -> cautionAlert.close());

				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				cautionDialog.setContent(vBox);
				cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

				ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
					cautionDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					cautionDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = cautionAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					cautionAlert.close();

				}
			} else if (mainObj.getLanguageMode() == 1) {
				cautionAlert.setTitle("تنبيه!");
				cautionAlert.setHeaderText("");
				content = new Text("تنبيه: تأكد من الحفظ أو الحفظ كـ لحفظ تخصيصك للمرة القادمة\n"
						+ "تخصيصك سيلغى إذا ضغظت على زر الخلف أو فتحت تخزينة أو أغلقت البرنامج دون أن تحفظ تخصيصك");
				content.setStyle("-fx-font-size:20; direction: ltr; -fx-font-weight: normal;");

				ButtonType OkButton = new ButtonType("حسنا");
				cautionAlert.getButtonTypes().setAll(OkButton);
				DialogPane cautionDialog = cautionAlert.getDialogPane();

				Window window = cautionDialog.getScene().getWindow();

				window.setOnCloseRequest(ee -> cautionAlert.close());

				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				cautionDialog.setContent(vBox);
				cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

				ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
					cautionDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					cautionDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = cautionAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					cautionAlert.close();

				}

			}

		}
	}

	// extract info from the saved info file
	public void extractInfo(OrganizeFolderMainUI mainObj, String uploadPath) {
		try {
			String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");
			File saved = new File(savedInfoPath);
			if (!uploadPath.isEmpty()) {
				saved = new File(uploadPath);
			}

			if (!saved.exists()) {
				saved.createNewFile();
			}
			getFolderNames().clear();
			getExtensionsForEachFolder().clear();

			// for checking content format and then read it
			boolean isValid = true;
			try (Scanner scanCheck = new Scanner(saved)) {
				int j = 0;
				for (int i = 0; scanCheck.hasNextLine(); ++i) {
					String check = scanCheck.nextLine().trim();
//						System.out.println(check);
//						System.out.println(check);					
					// for even check folder names
					if (i % 2 == 0) {
						if (check.isEmpty() || check.trim().charAt(check.length() - 1) != ':') {
							check = check.replaceAll(" ", "");
//								System.out.println("Error 1: "+check.trim().charAt(check.length()-1));
							isValid = false;
							break;
						} else {
							check = check.substring(0, check.length() - 1);
							if (!isValidName(check)) {
								isValid = false;
								break;
							} else {
								getFolderNames().add(check);
								getExtensionsForEachFolder().add(new ArrayList<String>());
							}
						}

					}

					// for odd check extension names
					else if (i % 2 == 1) {
						if (check.isEmpty() || check.trim().charAt(0) != '{'
								|| check.trim().charAt(check.length() - 1) != '}') {
//								System.out.println("Error 2: "+ check.trim().charAt(0)+" "+check.trim().charAt(check.length()-1));
							isValid = false;
							break;
						} else {
							if (check.contains(".") || !isValidName(check)) {
								isValid = false;
								break;
							} else {
								boolean doneExtension = true;
								String extension = check.trim().substring(1, check.length() - 1).trim();
//									System.out.println(extension);
								while (doneExtension) {
									if (extension.contains(", ")) {
										String extensionAdd = extension.substring(0, extension.indexOf(", ")).trim();
										getExtensionsForEachFolder().get(j).add(extensionAdd);
										extension = extension.substring(extension.indexOf(", ") + 1).trim();
									} else {
										getExtensionsForEachFolder().get(j).add(extension.trim());
										doneExtension = false;
									}

								}
								++j;
							}

						}
					}
				}
//					System.out.println("Folder Names : "+getFolderNames().toString());
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			if (!isValid) {
				System.out.println("Error");

				Alert errorAlert = new Alert(AlertType.ERROR);
				Text content = new Text("");
				if (mainObj.getLanguageMode() == 0) {
					errorAlert.setTitle("Error!");
					errorAlert.setHeaderText("");
					content = new Text("Error: Invalid customization saved file!");
					content.setStyle("-fx-font-size:20;");

					ButtonType OkButton = new ButtonType("OK");
					errorAlert.getButtonTypes().setAll(OkButton);

					Window window = errorAlert.getDialogPane().getScene().getWindow();

					window.setOnCloseRequest(e -> errorAlert.close());

					DialogPane errorDialog = errorAlert.getDialogPane();
					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					errorDialog.setContent(vBox);
					errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

					ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white;");
						errorDialog.getStylesheets()
								.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
						errorDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = errorAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						errorAlert.close();
					}
				} else if (mainObj.getLanguageMode() == 1) {
					errorAlert.setTitle("!خطأ");
					errorAlert.setHeaderText("");
					content = new Text("خطأ: هناك خطأ بتخزينة التخصيص!");
					content.setStyle("-fx-font-size:20; direction: ltr;");

					ButtonType OkButton = new ButtonType("حسنا");
					errorAlert.getButtonTypes().setAll(OkButton);

					Window window = errorAlert.getDialogPane().getScene().getWindow();

					window.setOnCloseRequest(e -> errorAlert.close());

					DialogPane errorDialog = errorAlert.getDialogPane();
					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					errorDialog.setContent(vBox);
					errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

					ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white;");
						errorDialog.getStylesheets()
								.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
						errorDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = errorAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						errorAlert.close();

					}

				}

			} else {
				System.out.println("Folder Names: " + getFolderNames().toString());
				System.out.println("Extenstion for each folder: " + getExtensionsForEachFolder().toString());
				System.out.println();
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static boolean isValidName(String text) {
		Pattern pattern = Pattern.compile(
				"# Match a valid Windows filename (unspecified file system).          \n"
						+ "^                                # Anchor to start of string.        \n"
						+ "(?!                              # Assert filename is not: CON, PRN, \n"
						+ "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n"
						+ "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n"
						+ "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n"
						+ "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n"
						+ "  (?:\\.[^.]*)?                  # followed by optional extension    \n"
						+ "  $                              # and end of string                 \n"
						+ ")                                # End negative lookahead assertion. \n"
						+ "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n"
						+ "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n"
						+ "$                                # Anchor to end of string.            ",
				Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
		Matcher matcher = pattern.matcher(text);
		boolean isMatch = matcher.matches();
		return isMatch;
	}

	public void customSetting(OrganizeFolderMainUI mainObj) {

		setIsUpdateInfoViaCustom(false);

		Stage customStage = new Stage();
		BorderPane customMainPane = new BorderPane();
		Scene customScene = null;
		if(mainObj.getLanguageMode() == 0) {
			customScene = new Scene(customMainPane, 825, 725);
		}
		else if(mainObj.getLanguageMode() == 1) {
			customScene = new Scene(customMainPane, 890, 725);
		}

		// Folder section
		ListView<String> foldersList = new ListView<String>();
		ArrayList<String> foldersNames = new ArrayList<String>(getFolderNames());

//		 System.out.println(foldersNames.toArray());
//		 System.out.println(getFolderNames().toArray());

		ObservableList<String> foldersItems = FXCollections.observableArrayList(foldersNames);
		foldersList.setItems(foldersItems);

		VBox foldersVBox = new VBox();
		Label foldersLabel = new Label("Folders");

		Button foldersAddButton = new Button("Add");
		Button foldersRenameButton = new Button("Rename");
		Button foldersClearButton = new Button("Clear");
		Button foldersClearAllButton = new Button("Clear All");
		

		TextField foldersTextField = new TextField();
		Label validateTextFolderError = new Label("");
		validateTextFolderError.setVisible(false);

		foldersAddButton.setDisable(true);
		foldersRenameButton.setDisable(true);
		foldersClearButton.setDisable(true);
		if(foldersNames.isEmpty()) {
			foldersClearAllButton.setDisable(true);

		}
		else {
			foldersClearAllButton.setDisable(false);

		}
		foldersAddButton.setStyle("-fx-background-radius: 90px;");
		foldersRenameButton.setStyle("-fx-background-radius: 90px;");
		foldersClearButton.setStyle("-fx-background-radius: 90px;");
		foldersClearAllButton.setStyle("-fx-background-radius: 90px;");

		HBox foldersTextFieldAndButton = new HBox();
		foldersTextFieldAndButton.setSpacing(10);
		foldersTextFieldAndButton.setAlignment(Pos.CENTER);
		foldersTextFieldAndButton.getChildren().addAll(foldersTextField, foldersAddButton, foldersRenameButton, foldersClearButton,
				foldersClearAllButton);

		foldersVBox.setSpacing(10);
		foldersVBox.setAlignment(Pos.CENTER);

		foldersVBox.getChildren().addAll(foldersLabel, foldersList, foldersTextFieldAndButton, validateTextFolderError);

		// Extension section
		ListView<String> extensionsList = new ListView<String>();
		ArrayList<ArrayList<String>> extensionsForEachFolder = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < getExtensionsForEachFolder().size(); ++i) {
			extensionsForEachFolder.add(new ArrayList<String>());
			for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
				extensionsForEachFolder.get(i).add(getExtensionsForEachFolder().get(i).get(j));
			}
		}

//		 System.out.println(extensionsForEachFolder.get(0).toArray());
//		 System.out.println(getExtensionsForEachFolder().get(0).toArray());

		ObservableList<String> extensionsItems = FXCollections.observableArrayList();
		extensionsList.setItems(extensionsItems);
		extensionsList.setDisable(true);

		VBox extensionsVBox = new VBox();
		Label extensionsLabel = new Label("Extensions");

		Button extensionsAddButton = new Button("Add");
		Button extensionsRenameButton = new Button("Rename");
		Button extensionsClearButton = new Button("Clear");
		Button extensionsClearAllButton = new Button("Clear All");

		TextField extensionsTextField = new TextField();
		Label validateTextExtensionError = new Label("");
		validateTextExtensionError.setVisible(false);
		extensionsTextField.setDisable(true);

		extensionsAddButton.setDisable(true);
		extensionsRenameButton.setDisable(true);
		extensionsClearButton.setDisable(true);
		extensionsClearAllButton.setDisable(true);
		extensionsAddButton.setStyle("-fx-background-radius: 90px;");
		extensionsRenameButton.setStyle("-fx-background-radius: 90px;");
		extensionsClearButton.setStyle("-fx-background-radius: 90px;");
		extensionsClearAllButton.setStyle("-fx-background-radius: 90px;");

		HBox extensionsTextFieldAndButton = new HBox();
		extensionsTextFieldAndButton.setSpacing(10);
		extensionsTextFieldAndButton.setAlignment(Pos.CENTER);
		extensionsTextFieldAndButton.getChildren().addAll(extensionsTextField, extensionsAddButton, extensionsRenameButton,
				extensionsClearButton, extensionsClearAllButton);

		extensionsVBox.setSpacing(10);
		extensionsVBox.setAlignment(Pos.CENTER);

		extensionsVBox.getChildren().addAll(extensionsLabel, extensionsList, extensionsTextFieldAndButton,
				validateTextExtensionError);

		// folder section actions
		foldersTextField.setOnKeyTyped(e -> {
//			 System.out.println(mainObj.getPathFolder().getText());
			if (!isValidName(foldersTextField.getText())
					|| (mainObj.getPathFolder() + "\\" + foldersTextField.getText()).length() > 261) {
				System.out.println("Error");
				foldersAddButton.setDisable(true);
				foldersRenameButton.setDisable(true);
				if (!foldersTextField.getText().trim().equals("")) {
					validateTextFolderError.setVisible(true);
					validateTextFolderError.setText("Invalid folder's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextFolderError.setText("اسم المجلد غير مسموح");
					}
					validateTextFolderError.setTextFill(Color.RED);
					foldersTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					validateTextFolderError.setVisible(false);
					validateTextFolderError.setText("");

					foldersTextField.setStyle("");

				}

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < foldersItems.size(); i++) {
					if (foldersTextField.getText().toLowerCase().equals(foldersItems.get(i).toLowerCase())) {
						isDuplicate = true;
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("There is the folder with the same name");
					foldersAddButton.setDisable(true);
					foldersRenameButton.setDisable(true);
					validateTextFolderError.setVisible(true);
					validateTextFolderError.setText("There is an identical folder's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextFolderError.setText("هناك اسم مجلد متطابق");
					}
					validateTextFolderError.setTextFill(Color.RED);

					foldersTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					System.out.println("Correct");
					foldersAddButton.setDisable(false);
					if(!foldersList.getSelectionModel().isEmpty()) {
						foldersRenameButton.setDisable(false);
					}
					else {
						foldersRenameButton.setDisable(true);
					}

					validateTextFolderError.setVisible(false);
					validateTextFolderError.setText("");

					foldersTextField.setStyle("");

				}

			}
		});
		
		foldersTextField.setOnAction(e ->{
			if(!foldersAddButton.isDisabled()) {
				foldersNames.add(foldersTextField.getText());
				foldersItems.setAll(foldersNames);
				extensionsForEachFolder.add(new ArrayList<String>());

				setIsUpdateInfoViaCustom(true);
				foldersTextField.setText("");
				foldersAddButton.setDisable(true);
				foldersClearAllButton.setDisable(false);
				
				foldersRenameButton.setDisable(true);
			}
		});

		foldersAddButton.setOnAction(e -> {

			foldersNames.add(foldersTextField.getText());
			foldersItems.setAll(foldersNames);
			extensionsForEachFolder.add(new ArrayList<String>());

			setIsUpdateInfoViaCustom(true);
			foldersTextField.setText("");
			foldersAddButton.setDisable(true);
			foldersClearAllButton.setDisable(false);
			
			foldersRenameButton.setDisable(true);

		});
		
		foldersRenameButton.setOnAction(e ->{
			
			foldersNames.set(foldersList.getSelectionModel().getSelectedIndex(), foldersTextField.getText());
			foldersItems.setAll(foldersNames);
			setIsUpdateInfoViaCustom(true);
			foldersTextField.setText("");
			foldersAddButton.setDisable(true);			
			foldersRenameButton.setDisable(true);
			
		});

		foldersClearButton.setOnAction(e -> {
			if (!foldersList.getSelectionModel().isEmpty()) {
				extensionsForEachFolder.remove(foldersList.getSelectionModel().getSelectedIndex());
				foldersNames.remove(foldersList.getSelectionModel().getSelectedIndex());
				foldersItems.setAll(foldersNames);
				extensionsItems.setAll();
				extensionsTextField.setDisable(true);
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);
				extensionsClearButton.setDisable(true);
				extensionsClearAllButton.setDisable(true);
				extensionsList.setDisable(true);

				foldersClearButton.setDisable(true);
				foldersRenameButton.setDisable(true);


				setIsUpdateInfoViaCustom(true);
				
				if(foldersList.getItems().isEmpty()) {
					foldersClearAllButton.setDisable(true);
					foldersTextField.setText(foldersTextField.getText());
				}
				

			}
		});

		foldersClearAllButton.setOnAction(e -> {
			foldersNames.clear();
			extensionsForEachFolder.clear();
			extensionsItems.setAll();
			foldersItems.setAll();
			extensionsTextField.setDisable(true);
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
			extensionsClearButton.setDisable(true);
			extensionsClearAllButton.setDisable(true);
			extensionsList.setDisable(true);

			foldersClearButton.setDisable(true);
			foldersClearAllButton.setDisable(true);
			foldersRenameButton.setDisable(true);


			setIsUpdateInfoViaCustom(true);

		});

		foldersList.setOnMouseClicked(e -> {
//	    	 System.out.println(foldersList.getSelectionModel().isEmpty());
			if (!foldersList.getSelectionModel().isEmpty()) {
				foldersClearButton.setDisable(false);
				extensionsRenameButton.setDisable(true);
				extensionsClearButton.setDisable(true);

				
				System.out.println(
						"your selection: " + foldersItems.get(foldersList.getSelectionModel().getSelectedIndex()));
				if (extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).isEmpty()) {
					extensionsItems.setAll();
				} else if (extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).get(0).trim()
						.equals("")) {
					extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).remove(0);
					extensionsItems.setAll();

				} else {
					extensionsItems
							.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

				}

				extensionsTextField.setDisable(false);
				extensionsList.setDisable(false);
				
				if(extensionsList.getItems().isEmpty()) {
					extensionsClearAllButton.setDisable(true);
				}
				else {
					extensionsClearAllButton.setDisable(false);
				}
				
//					 System.out.println(mainObj.getPathFolder().getText());
				if (!isValidName(foldersTextField.getText())
						|| (mainObj.getPathFolder() + "\\" + foldersTextField.getText()).length() > 261) {
//						System.out.println("Error");
						foldersRenameButton.setDisable(true);
						

				} else {
						boolean isDuplicate = false;
						for (int i = 0; i < foldersItems.size(); i++) {
							if (foldersTextField.getText().toLowerCase().equals(foldersItems.get(i).toLowerCase())) {
								isDuplicate = true;
								break;
							}
						}
						if (isDuplicate) {
//							System.out.println("There is the folder with the same name");
							foldersRenameButton.setDisable(true);

						} else {
//							System.out.println("Correct");
							if(!foldersList.getSelectionModel().isEmpty()) {
								foldersRenameButton.setDisable(false);
							}
							else {
								foldersRenameButton.setDisable(true);
							}
						}

				}
			}

		});

		// extension section actions
		extensionsTextField.setOnKeyTyped(e -> {
			if (!isValidName(extensionsTextField.getText()) || extensionsTextField.getText().length() > 20) {
				System.out.println("Error");
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);

				if (!extensionsTextField.getText().trim().equals("")) {
					validateTextExtensionError.setVisible(true);
					validateTextExtensionError.setText("Invalid extension's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextExtensionError.setText("اسم الصيغة غير مسموح");
					}
					validateTextExtensionError.setTextFill(Color.RED);
					extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					validateTextExtensionError.setVisible(false);
					validateTextExtensionError.setText("");

					extensionsTextField.setStyle("");

				}

			} else if (extensionsTextField.getText().contains(".")) {
				System.out.println("Contain .");
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);

				validateTextExtensionError.setVisible(true);
				validateTextExtensionError.setText("The extension's name contains dot (.)");
				if (mainObj.getLanguageMode() == 1) {
					validateTextExtensionError.setText("اسم الصيغة يحتوي على نقطة (.)");
				}
				validateTextExtensionError.setTextFill(Color.RED);
				extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j)
						if (extensionsTextField.getText().toLowerCase()
								.equals(extensionsForEachFolder.get(i).get(j).toLowerCase())) {
							isDuplicate = true;
							break;
						}
					if (isDuplicate) {
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("There is same extenstion in somewhere else");
					extensionsAddButton.setDisable(true);
					extensionsRenameButton.setDisable(true);


					validateTextExtensionError.setVisible(true);
					validateTextExtensionError.setText("There is an identical extenstion's name in these lists");
					if (mainObj.getLanguageMode() == 1) {
						validateTextExtensionError.setText("هناك اسم صيغة متطابق في هذه القوائم");
					}
					validateTextExtensionError.setTextFill(Color.RED);

					extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
				} else {
					System.out.println("Correct");
					extensionsAddButton.setDisable(false);
					
					if(!extensionsList.getSelectionModel().isEmpty()) {
						extensionsRenameButton.setDisable(false);
					}
					else {
						extensionsRenameButton.setDisable(true);
					}

					validateTextExtensionError.setVisible(false);
					validateTextExtensionError.setText("");

					extensionsTextField.setStyle("");

				}

			}
		});
		
		extensionsTextField.setOnAction(e ->{
			if(!extensionsAddButton.isDisabled()) {
				extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
					.add(extensionsTextField.getText().trim());
				extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

				setIsUpdateInfoViaCustom(true);
				extensionsTextField.setText("");
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);
				extensionsClearAllButton.setDisable(false);
			}
		});

		extensionsAddButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
					.add(extensionsTextField.getText().trim());
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			setIsUpdateInfoViaCustom(true);
			extensionsTextField.setText("");
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
			extensionsClearAllButton.setDisable(false);

		});
		
		extensionsRenameButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
			.set(extensionsList.getSelectionModel().getSelectedIndex(),extensionsTextField.getText().trim());
			
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			setIsUpdateInfoViaCustom(true);
			extensionsTextField.setText("");
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
		});

		extensionsClearButton.setOnAction(e -> {
			if (!extensionsList.getSelectionModel().isEmpty()) {
				extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
						.remove(extensionsList.getSelectionModel().getSelectedIndex());
				extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

				extensionsClearButton.setDisable(true);
				extensionsRenameButton.setDisable(true);
				setIsUpdateInfoViaCustom(true);
				
				if(extensionsList.getItems().isEmpty()) {
					extensionsClearAllButton.setDisable(true);
				}

			}

		});

		extensionsClearAllButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).clear();
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			extensionsClearButton.setDisable(true);
			setIsUpdateInfoViaCustom(true);
			extensionsClearAllButton.setDisable(true);
			extensionsRenameButton.setDisable(true);


		});
		
		extensionsList.setOnMouseClicked(e -> {
			extensionsClearButton.setDisable(false);
			
			if (!isValidName(extensionsTextField.getText()) || extensionsTextField.getText().length() > 20) {
//				System.out.println("Error");
				extensionsRenameButton.setDisable(true);

			} else if (extensionsTextField.getText().contains(".")) {
//				System.out.println("Contain .");
				extensionsRenameButton.setDisable(true);

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j)
						if (extensionsTextField.getText().toLowerCase()
								.equals(extensionsForEachFolder.get(i).get(j).toLowerCase())) {
							isDuplicate = true;
							break;
						}
					if (isDuplicate) {
						break;
					}
				}
				if (isDuplicate) {
//					System.out.println("There is same extenstion in somewhere else");
					extensionsRenameButton.setDisable(true);
					
				} else {
//					System.out.println("Correct");
					
					if(!extensionsList.getSelectionModel().isEmpty()) {
						extensionsRenameButton.setDisable(false);
					}
					else {
						extensionsRenameButton.setDisable(true);
					}

				}

			}
		});
		
		

		HBox centerBox = new HBox();
		centerBox.getChildren().addAll(foldersVBox, extensionsVBox);
		centerBox.setSpacing(15);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setPadding(new Insets(15, 15, 15, 15));

		HBox downButtons = new HBox();

		Button ok = new Button("OK");
		ok.setPrefWidth(100);
		ok.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");

		ok.setOnAction(e -> {
			if (getIsUpdateInfoViaCustom() == true) {
				getFolderNames().clear();
				getFolderNames().addAll(foldersNames);

				getExtensionsForEachFolder().clear();
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					getExtensionsForEachFolder().add(new ArrayList<String>());
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j) {
						getExtensionsForEachFolder().get(i).add(extensionsForEachFolder.get(i).get(j));
					}
				}

				Alert cautionAlert = new Alert(AlertType.INFORMATION);
				Text content = new Text("");
				if (mainObj.getLanguageMode() == 0) {
					cautionAlert.setTitle("Caution!");
					cautionAlert.setHeaderText("");
					content = new Text(
							"Caution: Make sure to Save or Save As to save your customization for the next time\n"
									+ "Your cutomization will be canceled if your press back button, open saved file, or close the program without save your customization");
					content.setStyle("-fx-font-size:20; -fx-font-weight: normal;");

					ButtonType OkButton = new ButtonType("OK");
					cautionAlert.getButtonTypes().setAll(OkButton);
					DialogPane cautionDialog = cautionAlert.getDialogPane();

					Window window = cautionDialog.getScene().getWindow();

					window.setOnCloseRequest(ee -> cautionAlert.close());

					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					cautionDialog.setContent(vBox);
					cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

					ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
						cautionDialog.getStylesheets()
								.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
						cautionDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = cautionAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						cautionAlert.close();

					}
				} else if (mainObj.getLanguageMode() == 1) {
					cautionAlert.setTitle("تنبيه!");
					cautionAlert.setHeaderText("");
					content = new Text("تنبيه: تأكد من الحفظ أو الحفظ كـ لحفظ تخصيصك للمرة القادمة\n"
							+ "تخصيصك سيلغى إذا ضغظت على زر الخلف أو فتحت تخزينة أو أغلقت البرنامج دون أن تحفظ تخصيصك");
					content.setStyle("-fx-font-size:20; direction: ltr; -fx-font-weight: normal;");

					ButtonType OkButton = new ButtonType("حسنا");
					cautionAlert.getButtonTypes().setAll(OkButton);
					DialogPane cautionDialog = cautionAlert.getDialogPane();

					Window window = cautionDialog.getScene().getWindow();

					window.setOnCloseRequest(ee -> cautionAlert.close());

					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					cautionDialog.setContent(vBox);
					cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

					ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
						cautionDialog.getStylesheets()
								.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
						cautionDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = cautionAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						cautionAlert.close();

					}

				}

			}
			customStage.close();

		});

		Button cancel = new Button("Cancel");
		cancel.setPrefWidth(100);
		cancel.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");
		cancel.setOnAction(e -> {
			customStage.close();

		});

		customStage.setOnCloseRequest(e -> {
			customStage.close();

		});

		downButtons.getChildren().addAll(ok, cancel);
		downButtons.setPadding(new Insets(15, 15, 75, 15));
		downButtons.setAlignment(Pos.CENTER);
		downButtons.setSpacing(15);

		customMainPane.setCenter(centerBox);
		customMainPane.setBottom(downButtons);

		Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());

		customStage.setResizable(false);
		customStage.getIcons().add(programIcon);
		customStage.setScene(customScene);
		customStage.setTitle("Custom");

		customStage.initModality(Modality.APPLICATION_MODAL);

		if (mainObj.getThemeMode() == 1) {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, customScene);
			mainObj.setFirstTime(false);
		}
		if (mainObj.getLanguageMode() == 1) {
			// folders
			foldersLabel.setText("المجلدات");
			foldersAddButton.setText("أضف");
			foldersRenameButton.setText("اعد التسمية");
			foldersClearButton.setText("احذف");
			foldersClearAllButton.setText("احذف الكل");

			// extensions
			extensionsLabel.setText("الصيغ");
			extensionsAddButton.setText("أضف");
			extensionsRenameButton.setText("اعد التسمية");
			extensionsClearButton.setText("احذف");
			extensionsClearAllButton.setText("احذف الكل");

			// others
			ok.setText("حسنا");
			cancel.setText("إلغ");
			customStage.setTitle("تخصيص");

		}

		customStage.showAndWait();

	}

	public void organizeSetting(OrganizeFolderMainUI mainObj) {

		if (mainObj.getInitialSavedSetting() != 2) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			Text content = new Text("");
			if (mainObj.getLanguageMode() == 0) {
				errorAlert.setTitle("Error!");
				errorAlert.setHeaderText("");
				content = new Text(
						"Error: Please, make sure to save or save as at this type mode to move on in the process");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				errorAlert.getButtonTypes().setAll(OkButton);

				Window window = errorAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> errorAlert.close());

				DialogPane errorDialog = errorAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				errorDialog.setContent(vBox);
				errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					errorDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					errorDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = errorAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					errorAlert.close();
				}
			} else if (mainObj.getLanguageMode() == 1) {
				errorAlert.setTitle("!خطأ");
				errorAlert.setHeaderText("");
				content = new Text("خطأ: رجاء، تأكد من الحفظ أو الحفظ كـ على هذا الوضع لتستكمل العملية!");
				content.setStyle("-fx-font-size:20; direction: ltr;");

				ButtonType OkButton = new ButtonType("حسنا");
				errorAlert.getButtonTypes().setAll(OkButton);

				Window window = errorAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> errorAlert.close());

				DialogPane errorDialog = errorAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				errorDialog.setContent(vBox);
				errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					errorDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					errorDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = errorAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					errorAlert.close();

				}

			}

			return;

		}

		File organizedFolder = new File(mainObj.getPathFolder().getText());
		String[] folderContents = organizedFolder.list();
		boolean done = true;
		for (int i = 0; i < getFolderNames().size(); ++i) {

			System.out.println(new File(mainObj.getPathFolder().getText() + "/" + getFolderNames().get(i)).mkdirs());

			for (int j = 0; j < folderContents.length; ++j) {
				File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]);
				for (int k = 0; k < getExtensionsForEachFolder().get(i).size(); ++k) {
					if (organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".") + 1)
							.toLowerCase().equals(getExtensionsForEachFolder().get(i).get(k).toLowerCase())) {
						File targetPath = new File(mainObj.getPathFolder().getText() + "/" + getFolderNames().get(i)
								+ "/" + folderContents[j]);
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
		for (int i = 0; i < folderContents.length; ++i) {
			File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[i]);
			if (organizedChecker.isFile()) {
				System.out.println("File " + count + ": " + folderContents[i]);
				System.out.println("Extention: "
						+ folderContents[i].substring(folderContents[i].lastIndexOf(".") + 1).toLowerCase());
				folderExtentionFormat
						.add(folderContents[i].substring(folderContents[i].lastIndexOf(".") + 1).toLowerCase());
				count++;
				System.out.println();
			}
		}

		for (int i = 0; i < folderExtentionFormat.toArray().length; ++i) {
			System.out.println(
					new File(mainObj.getPathFolder().getText() + "/" + folderExtentionFormat.toArray()[i]).mkdirs());

			for (int j = 0; j < folderContents.length; ++j) {
				File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]);
				if (organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".") + 1)
						.toLowerCase().equals(folderExtentionFormat.toArray()[i])) {
					File targetPath = new File(mainObj.getPathFolder().getText() + "/"
							+ folderExtentionFormat.toArray()[i] + "/" + folderContents[j]);
					System.out.println(targetPath.getAbsolutePath());
					organizedChecker.renameTo(targetPath);
					if (new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]).exists()) {
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
		if (done) {
			if (mainObj.getLanguageMode() == 0) {
				doneAlert.setTitle("Fully Organized");
				doneAlert.setHeaderText("");
				content = new Text("The files are fully organized");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
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

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					doneAlert.close();

				}
			}
		} else {
			if (mainObj.getLanguageMode() == 0) {
				doneAlert.setTitle("Partially Organized");
				doneAlert.setHeaderText("");
				content = new Text(
						"The files are patially organized\n\nMaybe unorganized files are in use, open, protected, or there is a file with the same name inside the folder that is transferred to");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					doneAlert.close();

				}

			} else {
				doneAlert.setTitle("نظم جزئيا");
				doneAlert.setHeaderText("");
				content = new Text(
						"الملفات نظمت جزئيا\n\nمن الممكن أن الملفات غير المنتظمة قيد الاستخدام، او مفتوحة، أو محمية، أو هناك ملف بنفس الاسم داخل الملجد المنقول إليه ");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("حسنا");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(new File("resources/Styles/Dark mode theme.css").toURI().toString());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(new File("resources/Images/Program icon.png").toURI().toString());
=======
			btnLogo.setImage(new Image(getClass().getResource("/resources/Images/Back logo.png").toExternalForm()));
			btn.setGraphic(btnLogo);
		} else if (whichButton == 1) {
			btnLogo.setImage(new Image(getClass().getResource("/resources/Images/Help Logo.jpg").toExternalForm()));
			btn.setGraphic(btnLogo);
		}

		return btn;
	}

	public void changeTheme(OrganizeFolderMainUI mainObj, boolean byButton) {
		if (byButton) {
			mainObj.changeMode(false, false, mainObj.getMainScene());
		}
		mainObj.setFirstTime(true);
		mainObj.changeMode(false, false, getCustomizedScene());
		mainObj.setFirstTime(false);
	}

	public void changeLanguage(OrganizeFolderMainUI mainObj, boolean byButton) {
		if (byButton) {
			mainObj.changeLanguage();
		}
		if (mainObj.getLanguageMode() == 0) {
			getChooseFolderBtn().setText("Choose");
			getCustomBtn().setText("Customize");
			getOrganizeBtn().setText("Organize");
			getUploadBtn().setText("Upload");
		} else if (mainObj.getLanguageMode() == 1) {
			getChooseFolderBtn().setText("اختر");
			getCustomBtn().setText("خصص");
			getOrganizeBtn().setText("نظم");
			getUploadBtn().setText("ارفع");
		}
	}

	public void openCheck(OrganizeFolderMainUI mainObj) {
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();
//		System.out.println(checkLanguage);
//		System.out.println(checkTheme);
		mainObj.openSetting(getCustomizedScene(), false, mainObj);
//		System.out.println(mainObj.getLanguageMode());
//		System.out.println(mainObj.getThemeMode());
		if (mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if (checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		} else {
//			System.out.println("Test");
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);

		}
		if (mainObj.getPathFolder().getText().equals("")) {
			getOrganizeBtn().setDisable(true);
		}
		if (mainObj.getSuccessfullyOpen()) {
			extractInfo(mainObj, "");
			setIsUpdateInfoViaCustom(false);
		}

	}

	public void savedSetting(OrganizeFolderMainUI mainObj) {
		mainObj.savedSetting();

		String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");

		try (FileWriter writer = new FileWriter(savedInfoPath)) {
			writer.write("");

			for (int i = 0; i < getFolderNames().size(); ++i) {
				writer.append(getFolderNames().get(i) + ":\n");
				if (getExtensionsForEachFolder().get(i).size() == 0) {
					if (i == getFolderNames().size() - 1) {
						writer.append("{}");
					} else {
						writer.append("{}\n");

					}
				}
				for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
					if (j == 0 && getExtensionsForEachFolder().get(i).size() == 1) {
						if (i == getFolderNames().size() - 1) {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}");
						} else {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}\n");

						}
					} else if (j == 0) {
						writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + ", ");
					} else if (j == getExtensionsForEachFolder().get(i).size() - 1
							&& i == getFolderNames().size() - 1) {
						writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
					} else if (j == getExtensionsForEachFolder().get(i).size() - 1) {
						if (i == getFolderNames().size() - 1) {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
						} else {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}\n");

						}

					} else {
						writer.append(getExtensionsForEachFolder().get(i).get(j) + ", ");
					}
				}

			}

			setIsUpdateInfoViaCustom(false);
			System.out.println("Successfully wrote to the file.");

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public void saveAsCheck(OrganizeFolderMainUI mainObj) {
		String checkPath = mainObj.getRunFileSettings();
		int checkLanguage = mainObj.getLanguageMode();
		int checkTheme = mainObj.getThemeMode();

//		System.out.println(checkPath);
		mainObj.savedAsSetting(getCustomizedScene(), mainObj);
//		System.out.println(mainObj.getRunFileSettings());

		if (!checkPath.equals(mainObj.getRunFileSettings())) {
			mainObj.getPathFolder().setText("");
			mainObj.getPathFolder().setDisable(true);
			getOrganizeBtn().setDisable(true);

		}

		if (mainObj.getLanguageMode() != checkLanguage) {
			mainObj.setFirstTime(true);
			changeLanguage(mainObj, true);
			mainObj.setFirstTime(false);
		}
		if (checkTheme == mainObj.getThemeMode()) {
			mainObj.setIsChangeTheme(false);
		} else {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, mainObj.getMainScene());
			mainObj.setFirstTime(false);

		}

		if (mainObj.getSuccessfullySaveAs()) {
			String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");

			try (FileWriter writer = new FileWriter(savedInfoPath)) {
				writer.write("");

				for (int i = 0; i < getFolderNames().size(); ++i) {
					writer.append(getFolderNames().get(i) + ":\n");
					if (getExtensionsForEachFolder().get(i).size() == 0) {
						if (i == getFolderNames().size() - 1) {
							writer.append("{}");
						} else {
							writer.append("{}\n");

						}
					}
					for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
						if (j == 0 && getExtensionsForEachFolder().get(i).size() == 1) {
							if (i == getFolderNames().size() - 1) {
								writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}");
							} else {
								writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + "}\n");

							}
						} else if (j == 0) {
							writer.append("{" + getExtensionsForEachFolder().get(i).get(j) + ", ");
						} else if (j == getExtensionsForEachFolder().get(i).size() - 1
								&& i == getFolderNames().size() - 1) {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
						} else if (j == getExtensionsForEachFolder().get(i).size() - 1) {
							if (i == getFolderNames().size() - 1) {
								writer.append(getExtensionsForEachFolder().get(i).get(j) + "}");
							} else {
								writer.append(getExtensionsForEachFolder().get(i).get(j) + "}\n");

							}

						} else {
							writer.append(getExtensionsForEachFolder().get(i).get(j) + ", ");
						}
					}

				}

				setIsUpdateInfoViaCustom(false);
				System.out.println("Successfully wrote to the file.");

			} catch (IOException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

		}

	}

	public void backsetting(OrganizeFolderMainUI mainObj) {
		mainObj.MainUI(mainObj, true);
	}

	public void helpSetting(OrganizeFolderMainUI mainObj) {
		Alert helpAlert = new Alert(AlertType.CONFIRMATION);
		TextFlow flow = new TextFlow();
		if (mainObj.getLanguageMode() == 0) {
			helpAlert.setTitle("Help");
			helpAlert.setHeaderText("How to use customized mode");

			Text cMode = new Text("Customized mode: ");
			Text bChoose = new Text("Choose button ");
			Text bOrganize = new Text("Organize button ");
			Text bCustom = new Text("Customize button");
			Text bOK = new Text("OK button ");
			Text mFile = new Text("File menu");
			Text mSave = new Text("Save option ");
			Text mSaveAs = new Text("Save As option ");
			Text bUpload = new Text("Upload button ");
			Text note = new Text("Note: ");

			cMode.setStyle("-fx-font-weight: bold;");
			bChoose.setStyle("-fx-font-weight: bold;");
			bOrganize.setStyle("-fx-font-weight: bold;");
			bCustom.setStyle("-fx-font-weight: bold;");
			bOK.setStyle("-fx-font-weight: bold;");
			mFile.setStyle("-fx-font-weight: bold;");
			mSave.setStyle("-fx-font-weight: bold;");
			mSaveAs.setStyle("-fx-font-weight: bold;");
			bUpload.setStyle("-fx-font-weight: bold;");
			note.setStyle("-fx-font-weight: bold;");

			Text partOne = new Text("Organize the files of the chosen folder based on your customized choice\n\n"
					+ "Instruction: \n\n" + "1. Click ");
			Text partTwo = new Text("to select the path (the folder location)\n\n"
					+ "2. Choose the path (the folder location) that you want to organize\n\n"
					+ "3. You can either manually customize your organization or upload your customized saved file\n\n"
					+ "A. By clicking ");
			Text partThree = new Text(",  you can manually customize your organization\n" + "Make sure to click ");
			Text partFour = new Text("to update the new changes\n\n" + "B. By clicking ");
			Text partFive = new Text(",  you can upload a customized saved file\n"
					+ "Make sure to choose the saved file in .ifoi extension format and the contents be something like: \n\r"
					+ "Folder_name:\n" + "{Extension_name, Extension_name}\n" + "Folder_name:\n" + "{Extension_name}\n"
					+ "Folder_name:\n" + "{}\n"
					+ "Folder_name must be a valid name, and it can be multiple words\n"
					+ "Extension_name must be a valid name without a dot (.), and it can be multiple words\n\n"
					+ "4. Make sure to save the settings at this specific mode (at the same window) by clicking ");
			Text partSix = new Text(", and then ");
			Text partSeven = new Text("or ");
			Text partEight = new Text("if you want to save in the new saved setting\n\n" + "5. Click ");
			Text partNine = new Text("to organize the folder's files based on your customized choice\n\n");
			Text partTen = new Text(
					"If there are files' extension formats that are not in your customized list, they will be automatically organized based on their extension formats");

			partOne.setStyle("-fx-font-weight: normal;");
			partTwo.setStyle("-fx-font-weight: normal;");
			partThree.setStyle("-fx-font-weight: normal;");
			partFour.setStyle("-fx-font-weight: normal;");
			partFive.setStyle("-fx-font-weight: normal;");
			partSix.setStyle("-fx-font-weight: normal;");
			partSeven.setStyle("-fx-font-weight: normal;");
			partEight.setStyle("-fx-font-weight: normal;");
			partNine.setStyle("-fx-font-weight: normal;");
			partTen.setStyle("-fx-font-weight: normal;");

			flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bCustom, partThree, bOK, partFour, bUpload,
					partFive, mFile, partSix, mSave, partSeven, mSaveAs, partEight, bOrganize, partNine, note, partTen);
			flow.setTextAlignment(TextAlignment.CENTER);

			ButtonType OkButton = new ButtonType("OK");
			helpAlert.getButtonTypes().setAll(OkButton);

			Window window = helpAlert.getDialogPane().getScene().getWindow();

			window.setOnCloseRequest(e -> helpAlert.close());

			DialogPane helpDialog = helpAlert.getDialogPane();
			VBox vBox = new VBox();
			vBox.getChildren().add(flow);
			helpDialog.setContent(vBox);
			helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

			ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
			buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

			if (mainObj.getThemeMode() == 1) {
				cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOK.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bUpload.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				note.setStyle("-fx-font-weight: bold; -fx-fill: white;");

				partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				;
				partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partEight.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partNine.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTen.setStyle("-fx-font-weight: normal; -fx-fill: white;");

				helpDialog.getStylesheets()
						.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
				helpDialog.getStyleClass().add("Dark mode theme");

			}
			Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
			Stage stageIcon = (Stage) window;
			stageIcon.getIcons().add(programIcon);

			Optional<ButtonType> result = helpAlert.showAndWait();

			if (result.isPresent() && result.get() == OkButton) {
				helpAlert.close();

			}
		} else if (mainObj.getLanguageMode() == 1) {

			helpAlert.setTitle("مساعدة");
			helpAlert.setHeaderText("كيف تستخدم الوضع المخصص");

			Text cMode = new Text("الوضع المخصص: ");
			Text bChoose = new Text("زر اختر ");
			Text bOrganize = new Text("زر نظم ");
			Text bCustom = new Text("زر خصص");
			Text bOK = new Text("زر حسنا ");
			Text mFile = new Text("قائمة ملف ");
			Text mSave = new Text("خيار حفظ ");
			Text mSaveAs = new Text("خيار حفظ كـ ");
			Text bUpload = new Text("زر ارفع ");
			Text note = new Text("ملاحظة: ");

			cMode.setStyle("-fx-font-weight: bold;");
			bChoose.setStyle("-fx-font-weight: bold;");
			bOrganize.setStyle("-fx-font-weight: bold;");
			bCustom.setStyle("-fx-font-weight: bold;");
			bOK.setStyle("-fx-font-weight: bold;");
			mFile.setStyle("-fx-font-weight: bold;");
			mSave.setStyle("-fx-font-weight: bold;");
			mSaveAs.setStyle("-fx-font-weight: bold;");
			bUpload.setStyle("-fx-font-weight: bold;");
			note.setStyle("-fx-font-weight: bold;");

			Text partOne = new Text("ينظم ملفات المجلد المختار حسب ما تخصصه\n\n" + "التعليمات: \n\n" + "1. انقر ");
			Text partTwo = new Text(
					"لتختار المسار (موقع المجلد)\n\n" + "2. اختر المسار (موقع المجلد) الذي تريد أن ترتب ملفاته\n\n"
							+ "3. أنت تستطيع تخصيص تنظيمك يدويا، أو رفع تخزينتك المخصصة\n\n" + "أ. بالنقر على ");
			Text partThree = new Text("،  أنت تستطيع تخصيص تنظيمك يدويا \n" + "تأكد من نقر ");
			Text partFour = new Text("لتحديث تغيراتك الجديدة\n\n" + "ب. بالنقر على ");
			Text partFive = new Text("،  أنت تستطيع رفع تخزينتك المخصصة\n"
					+ "تأكد من اختيار تخزينتك بصيغة .ifoi ويكون المحتوى كهذا:  \n\r" + "اسم_المجلد:\n"
					+ "{اسم_الصيغة, اسم_الصيغة}\n" + "اسم_المجلد:\n" + "{اسم_الصيغة}\n" + "اسم_المجلد:\n"
					+ "{}\n" 
					+ "اسم_المجلد يجب أن يكون مسموحا، وممكن أن يكون من كلمات متعددة\n"
					+ "اسم_الصيغة يجب أن يكون مسموحا دون نقطة (.)، وممكن أن يكون من كلمات متعددة\n\n"
					+ "4. تأكد من أنك حفظت الإعدادات بهذا الوضع (بنفس هذه النافذة) وهذا عن طريق نقر ");
			Text partSix = new Text("، ثم ");
			Text partSeven = new Text("أو ");
			Text partEight = new Text("إذا كنت تريد أن تحفظ الإعدادات بتخزينة جديدة\n\n" + "5. انقر ");
			Text partNine = new Text("لتنظم ملفات المجلد المختار حسب ما تخصصه\n\n");
			Text partTen = new Text("إذا كان هناك صيغ ملفات غير موجودة في قائمتك المخصصة، سترتب تلقائيا حسب صيغهم");

			partOne.setStyle("-fx-font-weight: normal;");
			partTwo.setStyle("-fx-font-weight: normal;");
			partThree.setStyle("-fx-font-weight: normal;");
			partFour.setStyle("-fx-font-weight: normal;");
			partFive.setStyle("-fx-font-weight: normal;");
			partSix.setStyle("-fx-font-weight: normal;");
			partSeven.setStyle("-fx-font-weight: normal;");
			partEight.setStyle("-fx-font-weight: normal;");
			partNine.setStyle("-fx-font-weight: normal;");
			partTen.setStyle("-fx-font-weight: normal;");

			flow.getChildren().addAll(cMode, partOne, bChoose, partTwo, bCustom, partThree, bOK, partFour, bUpload,
					partFive, mFile, partSix, mSave, partSeven, mSaveAs, partEight, bOrganize, partNine, note, partTen);
			flow.setTextAlignment(TextAlignment.CENTER);

			ButtonType OkButton = new ButtonType("حسنا");
			helpAlert.getButtonTypes().setAll(OkButton);

			Window window = helpAlert.getDialogPane().getScene().getWindow();

			window.setOnCloseRequest(e -> helpAlert.close());

			DialogPane helpDialog = helpAlert.getDialogPane();
			VBox vBox = new VBox();
			vBox.getChildren().add(flow);
			helpDialog.setContent(vBox);
			helpDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

			ButtonBar buttonbar = (ButtonBar) helpDialog.lookup(".button-bar");
			buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

			if (mainObj.getThemeMode() == 1) {
				cMode.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bChoose.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOrganize.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bCustom.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bOK.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mFile.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSave.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				mSaveAs.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				bUpload.setStyle("-fx-font-weight: bold; -fx-fill: white;");
				note.setStyle("-fx-font-weight: bold; -fx-fill: white;");

				partOne.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTwo.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partThree.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFour.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partFive.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				;
				partSix.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partSeven.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partEight.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partNine.setStyle("-fx-font-weight: normal; -fx-fill: white;");
				partTen.setStyle("-fx-font-weight: normal; -fx-fill: white;");

				helpDialog.getStylesheets()
						.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
				helpDialog.getStyleClass().add("Dark mode theme");

			}
			Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
			Stage stageIcon = (Stage) window;
			stageIcon.getIcons().add(programIcon);

			Optional<ButtonType> result = helpAlert.showAndWait();

			if (result.isPresent() && result.get() == OkButton) {
				helpAlert.close();

			}

		}

	}

	public void chooseSetting(OrganizeFolderMainUI mainObj) {
		DirectoryChooser chooseFolder = new DirectoryChooser();
		chooseFolder.setTitle("Choose");
		File chooseDialog = chooseFolder.showDialog(mainObj.getPrimaryStage());
		if (chooseDialog == null) {
			return;
		}

		String choosePath = chooseDialog.toString();

		mainObj.getPathFolder().setText(choosePath);
		mainObj.getPathFolder().setDisable(false);
		mainObj.getPathFolder().setEditable(false);

		getOrganizeBtn().setDisable(false);
		getCustomBtn().setDisable(false);
		getUploadBtn().setDisable(false);

	}

	public void uploadSetting(OrganizeFolderMainUI mainObj) {

		File recommendedPath = new File("src/main/resources/Mode settings");
		FileChooser uploadController = new FileChooser();
		uploadController.setTitle("Upload");
		uploadController.getExtensionFilters().addAll(new ExtensionFilter("ifoi", "*.ifoi*"));
		uploadController.setInitialDirectory(recommendedPath);
		
		
		File uploadDialog = uploadController.showOpenDialog(mainObj.getPrimaryStage());
		if (uploadDialog == null) {
			return;

		} else {
			extractInfo(mainObj, uploadDialog.toString());

			Alert cautionAlert = new Alert(AlertType.INFORMATION);
			Text content = new Text("");
			if (mainObj.getLanguageMode() == 0) {
				cautionAlert.setTitle("Caution!");
				cautionAlert.setHeaderText("");
				content = new Text(
						"Caution: Make sure to Save or Save As to save your customization for the next time\n"
								+ "Your cutomization will be canceled if your press back button, open saved file, or close the program without save your customization");
				content.setStyle("-fx-font-size:20; -fx-font-weight: normal;");

				ButtonType OkButton = new ButtonType("OK");
				cautionAlert.getButtonTypes().setAll(OkButton);
				DialogPane cautionDialog = cautionAlert.getDialogPane();

				Window window = cautionDialog.getScene().getWindow();

				window.setOnCloseRequest(ee -> cautionAlert.close());

				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				cautionDialog.setContent(vBox);
				cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

				ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
					cautionDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					cautionDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = cautionAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					cautionAlert.close();

				}
			} else if (mainObj.getLanguageMode() == 1) {
				cautionAlert.setTitle("تنبيه!");
				cautionAlert.setHeaderText("");
				content = new Text("تنبيه: تأكد من الحفظ أو الحفظ كـ لحفظ تخصيصك للمرة القادمة\n"
						+ "تخصيصك سيلغى إذا ضغظت على زر الخلف أو فتحت تخزينة أو أغلقت البرنامج دون أن تحفظ تخصيصك");
				content.setStyle("-fx-font-size:20; direction: ltr; -fx-font-weight: normal;");

				ButtonType OkButton = new ButtonType("حسنا");
				cautionAlert.getButtonTypes().setAll(OkButton);
				DialogPane cautionDialog = cautionAlert.getDialogPane();

				Window window = cautionDialog.getScene().getWindow();

				window.setOnCloseRequest(ee -> cautionAlert.close());

				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				cautionDialog.setContent(vBox);
				cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

				ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
					cautionDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					cautionDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = cautionAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					cautionAlert.close();

				}

			}

		}
	}

	// extract info from the saved info file
	public void extractInfo(OrganizeFolderMainUI mainObj, String uploadPath) {
		try {
			String savedInfoPath = mainObj.getRunFileSettings().replace(".fo", ".ifoi");
			File saved = new File(savedInfoPath);
			if (!uploadPath.isEmpty()) {
				saved = new File(uploadPath);
			}

			if (!saved.exists()) {
				saved.createNewFile();
			}
			getFolderNames().clear();
			getExtensionsForEachFolder().clear();

			// for checking content format and then read it
			boolean isValid = true;
			try (Scanner scanCheck = new Scanner(saved)) {
				int j = 0;
				for (int i = 0; scanCheck.hasNextLine(); ++i) {
					String check = scanCheck.nextLine().trim();
//						System.out.println(check);
//						System.out.println(check);					
					// for even check folder names
					if (i % 2 == 0) {
						if (check.isEmpty() || check.trim().charAt(check.length() - 1) != ':') {
							check = check.replaceAll(" ", "");
//								System.out.println("Error 1: "+check.trim().charAt(check.length()-1));
							isValid = false;
							break;
						} else {
							check = check.substring(0, check.length() - 1);
							if (!isValidName(check)) {
								isValid = false;
								break;
							} else {
								getFolderNames().add(check);
								getExtensionsForEachFolder().add(new ArrayList<String>());
							}
						}

					}

					// for odd check extension names
					else if (i % 2 == 1) {
						if (check.isEmpty() || check.trim().charAt(0) != '{'
								|| check.trim().charAt(check.length() - 1) != '}') {
//								System.out.println("Error 2: "+ check.trim().charAt(0)+" "+check.trim().charAt(check.length()-1));
							isValid = false;
							break;
						} else {
							if (check.contains(".") || !isValidName(check)) {
								isValid = false;
								break;
							} else {
								boolean doneExtension = true;
								String extension = check.trim().substring(1, check.length() - 1).trim();
//									System.out.println(extension);
								while (doneExtension) {
									if (extension.contains(", ")) {
										String extensionAdd = extension.substring(0, extension.indexOf(", ")).trim();
										getExtensionsForEachFolder().get(j).add(extensionAdd);
										extension = extension.substring(extension.indexOf(", ") + 1).trim();
									} else {
										getExtensionsForEachFolder().get(j).add(extension.trim());
										doneExtension = false;
									}

								}
								++j;
							}

						}
					}
				}
//					System.out.println("Folder Names : "+getFolderNames().toString());
			} catch (FileNotFoundException e) {
				System.out.println("An error occurred.");
				e.printStackTrace();
			}

			if (!isValid) {
				System.out.println("Error");

				Alert errorAlert = new Alert(AlertType.ERROR);
				Text content = new Text("");
				if (mainObj.getLanguageMode() == 0) {
					errorAlert.setTitle("Error!");
					errorAlert.setHeaderText("");
					content = new Text("Error: Invalid customization saved file!");
					content.setStyle("-fx-font-size:20;");

					ButtonType OkButton = new ButtonType("OK");
					errorAlert.getButtonTypes().setAll(OkButton);

					Window window = errorAlert.getDialogPane().getScene().getWindow();

					window.setOnCloseRequest(e -> errorAlert.close());

					DialogPane errorDialog = errorAlert.getDialogPane();
					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					errorDialog.setContent(vBox);
					errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

					ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white;");
						errorDialog.getStylesheets()
								.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
						errorDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = errorAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						errorAlert.close();
					}
				} else if (mainObj.getLanguageMode() == 1) {
					errorAlert.setTitle("!خطأ");
					errorAlert.setHeaderText("");
					content = new Text("خطأ: هناك خطأ بتخزينة التخصيص!");
					content.setStyle("-fx-font-size:20; direction: ltr;");

					ButtonType OkButton = new ButtonType("حسنا");
					errorAlert.getButtonTypes().setAll(OkButton);

					Window window = errorAlert.getDialogPane().getScene().getWindow();

					window.setOnCloseRequest(e -> errorAlert.close());

					DialogPane errorDialog = errorAlert.getDialogPane();
					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					errorDialog.setContent(vBox);
					errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

					ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white;");
						errorDialog.getStylesheets()
								.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
						errorDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = errorAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						errorAlert.close();

					}

				}

			} else {
				System.out.println("Folder Names: " + getFolderNames().toString());
				System.out.println("Extenstion for each folder: " + getExtensionsForEachFolder().toString());
				System.out.println();
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

	public static boolean isValidName(String text) {
		Pattern pattern = Pattern.compile(
				"# Match a valid Windows filename (unspecified file system).          \n"
						+ "^                                # Anchor to start of string.        \n"
						+ "(?!                              # Assert filename is not: CON, PRN, \n"
						+ "  (?:                            # AUX, NUL, COM1, COM2, COM3, COM4, \n"
						+ "    CON|PRN|AUX|NUL|             # COM5, COM6, COM7, COM8, COM9,     \n"
						+ "    COM[1-9]|LPT[1-9]            # LPT1, LPT2, LPT3, LPT4, LPT5,     \n"
						+ "  )                              # LPT6, LPT7, LPT8, and LPT9...     \n"
						+ "  (?:\\.[^.]*)?                  # followed by optional extension    \n"
						+ "  $                              # and end of string                 \n"
						+ ")                                # End negative lookahead assertion. \n"
						+ "[^<>:\"/\\\\|?*\\x00-\\x1F]*     # Zero or more valid filename chars.\n"
						+ "[^<>:\"/\\\\|?*\\x00-\\x1F\\ .]  # Last char is not a space or dot.  \n"
						+ "$                                # Anchor to end of string.            ",
				Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE | Pattern.COMMENTS);
		Matcher matcher = pattern.matcher(text);
		boolean isMatch = matcher.matches();
		return isMatch;
	}

	public void customSetting(OrganizeFolderMainUI mainObj) {

		setIsUpdateInfoViaCustom(false);

		Stage customStage = new Stage();
		BorderPane customMainPane = new BorderPane();
		Scene customScene = null;
		if(mainObj.getLanguageMode() == 0) {
			customScene = new Scene(customMainPane, 825, 725);
		}
		else if(mainObj.getLanguageMode() == 1) {
			customScene = new Scene(customMainPane, 890, 725);
		}

		// Folder section
		ListView<String> foldersList = new ListView<String>();
		ArrayList<String> foldersNames = new ArrayList<String>(getFolderNames());

//		 System.out.println(foldersNames.toArray());
//		 System.out.println(getFolderNames().toArray());

		ObservableList<String> foldersItems = FXCollections.observableArrayList(foldersNames);
		foldersList.setItems(foldersItems);

		VBox foldersVBox = new VBox();
		Label foldersLabel = new Label("Folders");

		Button foldersAddButton = new Button("Add");
		Button foldersRenameButton = new Button("Rename");
		Button foldersClearButton = new Button("Clear");
		Button foldersClearAllButton = new Button("Clear All");
		

		TextField foldersTextField = new TextField();
		Label validateTextFolderError = new Label("");
		validateTextFolderError.setVisible(false);

		foldersAddButton.setDisable(true);
		foldersRenameButton.setDisable(true);
		foldersClearButton.setDisable(true);
		if(foldersNames.isEmpty()) {
			foldersClearAllButton.setDisable(true);

		}
		else {
			foldersClearAllButton.setDisable(false);

		}
		foldersAddButton.setStyle("-fx-background-radius: 90px;");
		foldersRenameButton.setStyle("-fx-background-radius: 90px;");
		foldersClearButton.setStyle("-fx-background-radius: 90px;");
		foldersClearAllButton.setStyle("-fx-background-radius: 90px;");

		HBox foldersTextFieldAndButton = new HBox();
		foldersTextFieldAndButton.setSpacing(10);
		foldersTextFieldAndButton.setAlignment(Pos.CENTER);
		foldersTextFieldAndButton.getChildren().addAll(foldersTextField, foldersAddButton, foldersRenameButton, foldersClearButton,
				foldersClearAllButton);

		foldersVBox.setSpacing(10);
		foldersVBox.setAlignment(Pos.CENTER);

		foldersVBox.getChildren().addAll(foldersLabel, foldersList, foldersTextFieldAndButton, validateTextFolderError);

		// Extension section
		ListView<String> extensionsList = new ListView<String>();
		ArrayList<ArrayList<String>> extensionsForEachFolder = new ArrayList<ArrayList<String>>();

		for (int i = 0; i < getExtensionsForEachFolder().size(); ++i) {
			extensionsForEachFolder.add(new ArrayList<String>());
			for (int j = 0; j < getExtensionsForEachFolder().get(i).size(); ++j) {
				extensionsForEachFolder.get(i).add(getExtensionsForEachFolder().get(i).get(j));
			}
		}

//		 System.out.println(extensionsForEachFolder.get(0).toArray());
//		 System.out.println(getExtensionsForEachFolder().get(0).toArray());

		ObservableList<String> extensionsItems = FXCollections.observableArrayList();
		extensionsList.setItems(extensionsItems);
		extensionsList.setDisable(true);

		VBox extensionsVBox = new VBox();
		Label extensionsLabel = new Label("Extensions");

		Button extensionsAddButton = new Button("Add");
		Button extensionsRenameButton = new Button("Rename");
		Button extensionsClearButton = new Button("Clear");
		Button extensionsClearAllButton = new Button("Clear All");

		TextField extensionsTextField = new TextField();
		Label validateTextExtensionError = new Label("");
		validateTextExtensionError.setVisible(false);
		extensionsTextField.setDisable(true);

		extensionsAddButton.setDisable(true);
		extensionsRenameButton.setDisable(true);
		extensionsClearButton.setDisable(true);
		extensionsClearAllButton.setDisable(true);
		extensionsAddButton.setStyle("-fx-background-radius: 90px;");
		extensionsRenameButton.setStyle("-fx-background-radius: 90px;");
		extensionsClearButton.setStyle("-fx-background-radius: 90px;");
		extensionsClearAllButton.setStyle("-fx-background-radius: 90px;");

		HBox extensionsTextFieldAndButton = new HBox();
		extensionsTextFieldAndButton.setSpacing(10);
		extensionsTextFieldAndButton.setAlignment(Pos.CENTER);
		extensionsTextFieldAndButton.getChildren().addAll(extensionsTextField, extensionsAddButton, extensionsRenameButton,
				extensionsClearButton, extensionsClearAllButton);

		extensionsVBox.setSpacing(10);
		extensionsVBox.setAlignment(Pos.CENTER);

		extensionsVBox.getChildren().addAll(extensionsLabel, extensionsList, extensionsTextFieldAndButton,
				validateTextExtensionError);

		// folder section actions
		foldersTextField.setOnKeyTyped(e -> {
//			 System.out.println(mainObj.getPathFolder().getText());
			if (!isValidName(foldersTextField.getText())
					|| (mainObj.getPathFolder() + "\\" + foldersTextField.getText()).length() > 261) {
				System.out.println("Error");
				foldersAddButton.setDisable(true);
				foldersRenameButton.setDisable(true);
				if (!foldersTextField.getText().trim().equals("")) {
					validateTextFolderError.setVisible(true);
					validateTextFolderError.setText("Invalid folder's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextFolderError.setText("اسم المجلد غير مسموح");
					}
					validateTextFolderError.setTextFill(Color.RED);
					foldersTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					validateTextFolderError.setVisible(false);
					validateTextFolderError.setText("");

					foldersTextField.setStyle("");

				}

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < foldersItems.size(); i++) {
					if (foldersTextField.getText().toLowerCase().equals(foldersItems.get(i).toLowerCase())) {
						isDuplicate = true;
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("There is the folder with the same name");
					foldersAddButton.setDisable(true);
					foldersRenameButton.setDisable(true);
					validateTextFolderError.setVisible(true);
					validateTextFolderError.setText("There is an identical folder's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextFolderError.setText("هناك اسم مجلد متطابق");
					}
					validateTextFolderError.setTextFill(Color.RED);

					foldersTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					System.out.println("Correct");
					foldersAddButton.setDisable(false);
					if(!foldersList.getSelectionModel().isEmpty()) {
						foldersRenameButton.setDisable(false);
					}
					else {
						foldersRenameButton.setDisable(true);
					}

					validateTextFolderError.setVisible(false);
					validateTextFolderError.setText("");

					foldersTextField.setStyle("");

				}

			}
		});

		foldersAddButton.setOnAction(e -> {

			foldersNames.add(foldersTextField.getText());
			foldersItems.setAll(foldersNames);
			extensionsForEachFolder.add(new ArrayList<String>());

			setIsUpdateInfoViaCustom(true);
			foldersTextField.setText("");
			foldersAddButton.setDisable(true);
			foldersClearAllButton.setDisable(false);
			
			foldersRenameButton.setDisable(true);

		});
		
		foldersRenameButton.setOnAction(e ->{
			
			foldersNames.set(foldersList.getSelectionModel().getSelectedIndex(), foldersTextField.getText());
			foldersItems.setAll(foldersNames);
			setIsUpdateInfoViaCustom(true);
			foldersTextField.setText("");
			foldersAddButton.setDisable(true);			
			foldersRenameButton.setDisable(true);
			
		});

		foldersClearButton.setOnAction(e -> {
			if (!foldersList.getSelectionModel().isEmpty()) {
				extensionsForEachFolder.remove(foldersList.getSelectionModel().getSelectedIndex());
				foldersNames.remove(foldersList.getSelectionModel().getSelectedIndex());
				foldersItems.setAll(foldersNames);
				extensionsItems.setAll();
				extensionsTextField.setDisable(true);
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);
				extensionsClearButton.setDisable(true);
				extensionsClearAllButton.setDisable(true);
				extensionsList.setDisable(true);

				foldersClearButton.setDisable(true);
				foldersRenameButton.setDisable(true);


				setIsUpdateInfoViaCustom(true);
				
				if(foldersList.getItems().isEmpty()) {
					foldersClearAllButton.setDisable(true);
					foldersTextField.setText(foldersTextField.getText());
				}
				

			}
		});

		foldersClearAllButton.setOnAction(e -> {
			foldersNames.clear();
			extensionsForEachFolder.clear();
			extensionsItems.setAll();
			foldersItems.setAll();
			extensionsTextField.setDisable(true);
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
			extensionsClearButton.setDisable(true);
			extensionsClearAllButton.setDisable(true);
			extensionsList.setDisable(true);

			foldersClearButton.setDisable(true);
			foldersClearAllButton.setDisable(true);
			foldersRenameButton.setDisable(true);


			setIsUpdateInfoViaCustom(true);

		});

		foldersList.setOnMouseClicked(e -> {
//	    	 System.out.println(foldersList.getSelectionModel().isEmpty());
			if (!foldersList.getSelectionModel().isEmpty()) {
				foldersClearButton.setDisable(false);
				extensionsRenameButton.setDisable(true);
				extensionsClearButton.setDisable(true);
				System.out.println(
						"your selection: " + foldersItems.get(foldersList.getSelectionModel().getSelectedIndex()));
				if (extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).isEmpty()) {
					extensionsItems.setAll();
				} else if (extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).get(0).trim()
						.equals("")) {
					extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).remove(0);
					extensionsItems.setAll();

				} else {
					extensionsItems
							.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

				}

				extensionsTextField.setDisable(false);
				extensionsList.setDisable(false);
				
				if(extensionsList.getItems().isEmpty()) {
					extensionsClearAllButton.setDisable(true);
				}
				else {
					extensionsClearAllButton.setDisable(false);
				}
				
//					 System.out.println(mainObj.getPathFolder().getText());
				if (!isValidName(foldersTextField.getText())
						|| (mainObj.getPathFolder() + "\\" + foldersTextField.getText()).length() > 261) {
//						System.out.println("Error");
						foldersRenameButton.setDisable(true);
						

				} else {
						boolean isDuplicate = false;
						for (int i = 0; i < foldersItems.size(); i++) {
							if (foldersTextField.getText().toLowerCase().equals(foldersItems.get(i).toLowerCase())) {
								isDuplicate = true;
								break;
							}
						}
						if (isDuplicate) {
//							System.out.println("There is the folder with the same name");
							foldersRenameButton.setDisable(true);

						} else {
//							System.out.println("Correct");
							if(!foldersList.getSelectionModel().isEmpty()) {
								foldersRenameButton.setDisable(false);
							}
							else {
								foldersRenameButton.setDisable(true);
							}
						}

				}
			}

		});

		// extension section actions
		extensionsTextField.setOnKeyTyped(e -> {
			if (!isValidName(extensionsTextField.getText()) || extensionsTextField.getText().length() > 20) {
				System.out.println("Error");
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);

				if (!extensionsTextField.getText().trim().equals("")) {
					validateTextExtensionError.setVisible(true);
					validateTextExtensionError.setText("Invalid extension's name");
					if (mainObj.getLanguageMode() == 1) {
						validateTextExtensionError.setText("اسم الصيغة غير مسموح");
					}
					validateTextExtensionError.setTextFill(Color.RED);
					extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

				} else {
					validateTextExtensionError.setVisible(false);
					validateTextExtensionError.setText("");

					extensionsTextField.setStyle("");

				}

			} else if (extensionsTextField.getText().contains(".")) {
				System.out.println("Contain .");
				extensionsAddButton.setDisable(true);
				extensionsRenameButton.setDisable(true);

				validateTextExtensionError.setVisible(true);
				validateTextExtensionError.setText("The extension's name contains dot (.)");
				if (mainObj.getLanguageMode() == 1) {
					validateTextExtensionError.setText("اسم الصيغة يحتوي على نقطة (.)");
				}
				validateTextExtensionError.setTextFill(Color.RED);
				extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j)
						if (extensionsTextField.getText().toLowerCase()
								.equals(extensionsForEachFolder.get(i).get(j).toLowerCase())) {
							isDuplicate = true;
							break;
						}
					if (isDuplicate) {
						break;
					}
				}
				if (isDuplicate) {
					System.out.println("There is same extenstion in somewhere else");
					extensionsAddButton.setDisable(true);
					extensionsRenameButton.setDisable(true);


					validateTextExtensionError.setVisible(true);
					validateTextExtensionError.setText("There is an identical extenstion's name in these lists");
					if (mainObj.getLanguageMode() == 1) {
						validateTextExtensionError.setText("هناك اسم صيغة متطابق في هذه القوائم");
					}
					validateTextExtensionError.setTextFill(Color.RED);

					extensionsTextField.setStyle("-fx-text-box-border: red; -fx-focus-color: red;");
				} else {
					System.out.println("Correct");
					extensionsAddButton.setDisable(false);
					
					if(!extensionsList.getSelectionModel().isEmpty()) {
						extensionsRenameButton.setDisable(false);
					}
					else {
						extensionsRenameButton.setDisable(true);
					}

					validateTextExtensionError.setVisible(false);
					validateTextExtensionError.setText("");

					extensionsTextField.setStyle("");

				}

			}
		});

		extensionsAddButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
					.add(extensionsTextField.getText().trim());
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			setIsUpdateInfoViaCustom(true);
			extensionsTextField.setText("");
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
			extensionsClearAllButton.setDisable(false);

		});
		
		extensionsRenameButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
			.set(extensionsList.getSelectionModel().getSelectedIndex(),extensionsTextField.getText().trim());
			
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			setIsUpdateInfoViaCustom(true);
			extensionsTextField.setText("");
			extensionsAddButton.setDisable(true);
			extensionsRenameButton.setDisable(true);
		});

		extensionsClearButton.setOnAction(e -> {
			if (!extensionsList.getSelectionModel().isEmpty()) {
				extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex())
						.remove(extensionsList.getSelectionModel().getSelectedIndex());
				extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

				extensionsClearButton.setDisable(true);
				extensionsRenameButton.setDisable(true);
				setIsUpdateInfoViaCustom(true);
				
				if(extensionsList.getItems().isEmpty()) {
					extensionsClearAllButton.setDisable(true);
				}

			}

		});

		extensionsClearAllButton.setOnAction(e -> {
			extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()).clear();
			extensionsItems.setAll(extensionsForEachFolder.get(foldersList.getSelectionModel().getSelectedIndex()));

			extensionsClearButton.setDisable(true);
			setIsUpdateInfoViaCustom(true);
			extensionsClearAllButton.setDisable(true);
			extensionsRenameButton.setDisable(true);


		});
		
		extensionsList.setOnMouseClicked(e -> {
			extensionsClearButton.setDisable(false);
			
			if (!isValidName(extensionsTextField.getText()) || extensionsTextField.getText().length() > 20) {
//				System.out.println("Error");
				extensionsRenameButton.setDisable(true);

			} else if (extensionsTextField.getText().contains(".")) {
//				System.out.println("Contain .");
				extensionsRenameButton.setDisable(true);

			} else {
				boolean isDuplicate = false;
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j)
						if (extensionsTextField.getText().toLowerCase()
								.equals(extensionsForEachFolder.get(i).get(j).toLowerCase())) {
							isDuplicate = true;
							break;
						}
					if (isDuplicate) {
						break;
					}
				}
				if (isDuplicate) {
//					System.out.println("There is same extenstion in somewhere else");
					extensionsRenameButton.setDisable(true);
					
				} else {
//					System.out.println("Correct");
					
					if(!extensionsList.getSelectionModel().isEmpty()) {
						extensionsRenameButton.setDisable(false);
					}
					else {
						extensionsRenameButton.setDisable(true);
					}

				}

			}
		});
		
		

		HBox centerBox = new HBox();
		centerBox.getChildren().addAll(foldersVBox, extensionsVBox);
		centerBox.setSpacing(15);
		centerBox.setAlignment(Pos.CENTER);
		centerBox.setPadding(new Insets(15, 15, 15, 15));

		HBox downButtons = new HBox();

		Button ok = new Button("OK");
		ok.setPrefWidth(100);
		ok.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");

		ok.setOnAction(e -> {
			if (getIsUpdateInfoViaCustom() == true) {
				getFolderNames().clear();
				getFolderNames().addAll(foldersNames);

				getExtensionsForEachFolder().clear();
				for (int i = 0; i < extensionsForEachFolder.size(); ++i) {
					getExtensionsForEachFolder().add(new ArrayList<String>());
					for (int j = 0; j < extensionsForEachFolder.get(i).size(); ++j) {
						getExtensionsForEachFolder().get(i).add(extensionsForEachFolder.get(i).get(j));
					}
				}

				Alert cautionAlert = new Alert(AlertType.INFORMATION);
				Text content = new Text("");
				if (mainObj.getLanguageMode() == 0) {
					cautionAlert.setTitle("Caution!");
					cautionAlert.setHeaderText("");
					content = new Text(
							"Caution: Make sure to Save or Save As to save your customization for the next time\n"
									+ "Your cutomization will be canceled if your press back button, open saved file, or close the program without save your customization");
					content.setStyle("-fx-font-size:20; -fx-font-weight: normal;");

					ButtonType OkButton = new ButtonType("OK");
					cautionAlert.getButtonTypes().setAll(OkButton);
					DialogPane cautionDialog = cautionAlert.getDialogPane();

					Window window = cautionDialog.getScene().getWindow();

					window.setOnCloseRequest(ee -> cautionAlert.close());

					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					cautionDialog.setContent(vBox);
					cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

					ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
						cautionDialog.getStylesheets()
								.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
						cautionDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = cautionAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						cautionAlert.close();

					}
				} else if (mainObj.getLanguageMode() == 1) {
					cautionAlert.setTitle("تنبيه!");
					cautionAlert.setHeaderText("");
					content = new Text("تنبيه: تأكد من الحفظ أو الحفظ كـ لحفظ تخصيصك للمرة القادمة\n"
							+ "تخصيصك سيلغى إذا ضغظت على زر الخلف أو فتحت تخزينة أو أغلقت البرنامج دون أن تحفظ تخصيصك");
					content.setStyle("-fx-font-size:20; direction: ltr; -fx-font-weight: normal;");

					ButtonType OkButton = new ButtonType("حسنا");
					cautionAlert.getButtonTypes().setAll(OkButton);
					DialogPane cautionDialog = cautionAlert.getDialogPane();

					Window window = cautionDialog.getScene().getWindow();

					window.setOnCloseRequest(ee -> cautionAlert.close());

					VBox vBox = new VBox();
					content.setTextAlignment(TextAlignment.CENTER);
					vBox.getChildren().add(content);
					cautionDialog.setContent(vBox);
					cautionDialog.setStyle("-fx-font-size:20; -fx-align: center; -fx-font-weight: bold;");

					ButtonBar buttonbar = (ButtonBar) cautionDialog.lookup(".button-bar");
					buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

					if (mainObj.getThemeMode() == 1) {
						content.setStyle("-fx-fill: white; -fx-font-weight: normal;");
						cautionDialog.getStylesheets()
								.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
						cautionDialog.getStyleClass().add("Dark mode theme");

					}
					Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
					Stage stageIcon = (Stage) window;
					stageIcon.getIcons().add(programIcon);

					Optional<ButtonType> result = cautionAlert.showAndWait();

					if (result.isPresent() && result.get() == OkButton) {
						cautionAlert.close();

					}

				}

			}
			customStage.close();

		});

		Button cancel = new Button("Cancel");
		cancel.setPrefWidth(100);
		cancel.setStyle("-fx-background-radius: 90px; -fx-font-size:20;");
		cancel.setOnAction(e -> {
			customStage.close();

		});

		customStage.setOnCloseRequest(e -> {
			customStage.close();

		});

		downButtons.getChildren().addAll(ok, cancel);
		downButtons.setPadding(new Insets(15, 15, 75, 15));
		downButtons.setAlignment(Pos.CENTER);
		downButtons.setSpacing(15);

		customMainPane.setCenter(centerBox);
		customMainPane.setBottom(downButtons);

		Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());

		customStage.setResizable(false);
		customStage.getIcons().add(programIcon);
		customStage.setScene(customScene);
		customStage.setTitle("Custom");

		customStage.initModality(Modality.APPLICATION_MODAL);

		if (mainObj.getThemeMode() == 1) {
			mainObj.setFirstTime(true);
			mainObj.changeMode(false, false, customScene);
			mainObj.setFirstTime(false);
		}
		if (mainObj.getLanguageMode() == 1) {
			// folders
			foldersLabel.setText("المجلدات");
			foldersAddButton.setText("أضف");
			foldersRenameButton.setText("اعد التسمية");
			foldersClearButton.setText("احذف");
			foldersClearAllButton.setText("احذف الكل");

			// extensions
			extensionsLabel.setText("الصيغ");
			extensionsAddButton.setText("أضف");
			extensionsRenameButton.setText("اعد التسمية");
			extensionsClearButton.setText("احذف");
			extensionsClearAllButton.setText("احذف الكل");

			// others
			ok.setText("حسنا");
			cancel.setText("إلغ");
			customStage.setTitle("تخصيص");

		}

		customStage.showAndWait();

	}

	public void organizeSetting(OrganizeFolderMainUI mainObj) {

		if (mainObj.getInitialSavedSetting() != 2) {
			Alert errorAlert = new Alert(AlertType.ERROR);
			Text content = new Text("");
			if (mainObj.getLanguageMode() == 0) {
				errorAlert.setTitle("Error!");
				errorAlert.setHeaderText("");
				content = new Text(
						"Error: Please, make sure to save or save as at this type mode to move on in the process");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				errorAlert.getButtonTypes().setAll(OkButton);

				Window window = errorAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> errorAlert.close());

				DialogPane errorDialog = errorAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				errorDialog.setContent(vBox);
				errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					errorDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					errorDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = errorAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					errorAlert.close();
				}
			} else if (mainObj.getLanguageMode() == 1) {
				errorAlert.setTitle("!خطأ");
				errorAlert.setHeaderText("");
				content = new Text("خطأ: رجاء، تأكد من الحفظ أو الحفظ كـ على هذا الوضع لتستكمل العملية!");
				content.setStyle("-fx-font-size:20; direction: ltr;");

				ButtonType OkButton = new ButtonType("حسنا");
				errorAlert.getButtonTypes().setAll(OkButton);

				Window window = errorAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> errorAlert.close());

				DialogPane errorDialog = errorAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				errorDialog.setContent(vBox);
				errorDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) errorDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					errorDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					errorDialog.getStyleClass().add("Dark mode theme");

				}
				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = errorAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					errorAlert.close();

				}

			}

			return;

		}

		File organizedFolder = new File(mainObj.getPathFolder().getText());
		String[] folderContents = organizedFolder.list();
		boolean done = true;
		for (int i = 0; i < getFolderNames().size(); ++i) {

			System.out.println(new File(mainObj.getPathFolder().getText() + "/" + getFolderNames().get(i)).mkdirs());

			for (int j = 0; j < folderContents.length; ++j) {
				File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]);
				for (int k = 0; k < getExtensionsForEachFolder().get(i).size(); ++k) {
					if (organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".") + 1)
							.toLowerCase().equals(getExtensionsForEachFolder().get(i).get(k).toLowerCase())) {
						File targetPath = new File(mainObj.getPathFolder().getText() + "/" + getFolderNames().get(i)
								+ "/" + folderContents[j]);
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
		for (int i = 0; i < folderContents.length; ++i) {
			File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[i]);
			if (organizedChecker.isFile()) {
				System.out.println("File " + count + ": " + folderContents[i]);
				System.out.println("Extention: "
						+ folderContents[i].substring(folderContents[i].lastIndexOf(".") + 1).toLowerCase());
				folderExtentionFormat
						.add(folderContents[i].substring(folderContents[i].lastIndexOf(".") + 1).toLowerCase());
				count++;
				System.out.println();
			}
		}

		for (int i = 0; i < folderExtentionFormat.toArray().length; ++i) {
			System.out.println(
					new File(mainObj.getPathFolder().getText() + "/" + folderExtentionFormat.toArray()[i]).mkdirs());

			for (int j = 0; j < folderContents.length; ++j) {
				File organizedChecker = new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]);
				if (organizedChecker.isFile() && folderContents[j].substring(folderContents[j].lastIndexOf(".") + 1)
						.toLowerCase().equals(folderExtentionFormat.toArray()[i])) {
					File targetPath = new File(mainObj.getPathFolder().getText() + "/"
							+ folderExtentionFormat.toArray()[i] + "/" + folderContents[j]);
					System.out.println(targetPath.getAbsolutePath());
					organizedChecker.renameTo(targetPath);
					if (new File(mainObj.getPathFolder().getText() + "/" + folderContents[j]).exists()) {
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
		if (done) {
			if (mainObj.getLanguageMode() == 0) {
				doneAlert.setTitle("Fully Organized");
				doneAlert.setHeaderText("");
				content = new Text("The files are fully organized");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
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

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					doneAlert.close();

				}
			}
		} else {
			if (mainObj.getLanguageMode() == 0) {
				doneAlert.setTitle("Partially Organized");
				doneAlert.setHeaderText("");
				content = new Text(
						"The files are patially organized\n\nMaybe unorganized files are in use, open, protected, or there is a file with the same name inside the folder that is transferred to");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("OK");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					doneAlert.close();

				}

			} else {
				doneAlert.setTitle("نظم جزئيا");
				doneAlert.setHeaderText("");
				content = new Text(
						"الملفات نظمت جزئيا\n\nمن الممكن أن الملفات غير المنتظمة قيد الاستخدام، او مفتوحة، أو محمية، أو هناك ملف بنفس الاسم داخل الملجد المنقول إليه ");
				content.setStyle("-fx-font-size:20;");

				ButtonType OkButton = new ButtonType("حسنا");
				doneAlert.getButtonTypes().setAll(OkButton);

				Window window = doneAlert.getDialogPane().getScene().getWindow();

				window.setOnCloseRequest(e -> doneAlert.close());

				DialogPane doneDialog = doneAlert.getDialogPane();
				VBox vBox = new VBox();
				content.setTextAlignment(TextAlignment.CENTER);
				vBox.getChildren().add(content);
				doneDialog.setContent(vBox);
				doneDialog.setStyle("-fx-font-size:20; -fx-align: center;");

				ButtonBar buttonbar = (ButtonBar) doneDialog.lookup(".button-bar");
				buttonbar.getButtons().forEach(b -> b.setStyle("-fx-background-radius: 90px; -fx-font-size:20;"));

				if (mainObj.getThemeMode() == 1) {
					content.setStyle("-fx-fill: white;");
					doneDialog.getStylesheets()
							.add(getClass().getResource("/resources/Styles/Dark mode theme.css").toExternalForm());
					doneDialog.getStyleClass().add("Dark mode theme");

				}

				Image programIcon = new Image(getClass().getResource("/resources/Images/Program icon.png").toExternalForm());
>>>>>>> refs/heads/master
				Stage stageIcon = (Stage) window;
				stageIcon.getIcons().add(programIcon);

				Optional<ButtonType> result = doneAlert.showAndWait();

				if (result.isPresent() && result.get() == OkButton) {
					doneAlert.close();

				}
			}
		}

	}

}
