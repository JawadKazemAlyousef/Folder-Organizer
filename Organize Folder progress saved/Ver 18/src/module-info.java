
module folderOrganizer{
	requires transitive javafx.controls;
	requires javafx.graphics;
	requires javafx.base;
	requires javafx.fxml;
	requires javafx.media;
	requires javafx.swing;
	requires javafx.web;
	
	opens folder.organizer;
	exports folder.organizer;
}

