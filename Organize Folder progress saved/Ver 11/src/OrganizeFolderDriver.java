import javafx.application.Application;
import javafx.stage.Stage;

public class OrganizeFolderDriver extends Application{


	public static void main(String[] args) {
		launch(args);
       
    }

	@Override
	public void start(Stage arg0) throws Exception {
			OrganizeFolderMainUI mainObj = new OrganizeFolderMainUI();
			mainObj.MainUI(mainObj, false);
	}
    
    

}
    
