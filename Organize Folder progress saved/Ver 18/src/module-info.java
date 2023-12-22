
module folderOrganizer{
	requires transitive javafx.controls;
	requires transitive javafx.graphics;
	requires transitive javafx.base;
	requires javafx.fxml;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	
	opens folder.organizer;
	exports folder.organizer;
}

