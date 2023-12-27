
module folderOrganizer{
	requires transitive javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	
	opens folder.organizer to javafx.controls;
	exports folder.organizer;
}

