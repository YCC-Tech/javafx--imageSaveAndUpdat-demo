package application;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ImageUploadController {

    @FXML
    private Button btnBrowse;
    
    @FXML
    private ImageView ivImage;
    
    @FXML
    private Button btnSave;
    
    @FXML
    private Button btnDtail;
    
    @FXML
    private Label lblImagePath;
    
    @FXML
    private TextArea taImagePath;   
    
    private Image userImage;    
    private ImageUtil imageUtil = new ImageUtil();
   
    File imageFile;
    
    private Student student ;
    
    private Integer updateStudentId =1;
    
   

    @FXML
    void processBrowse(ActionEvent event) {
    	
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.jpg","*.jpeg","*.png","*.ico"));
    	this.imageFile = fileChooser.showOpenDialog(null);
    	if(imageFile!=null) {

    	taImagePath.setText(this.imageFile.getAbsolutePath());

    	userImage = new Image(this.imageFile.toURI().toString());
    	
    	ivImage.setImage(userImage);
    	
    	
    	
    	
    	}
	
    }

    @FXML
    void processSave(ActionEvent event) throws FileNotFoundException, SQLException {
    	student = new Student(this.imageFile);
    	imageUtil.insertStudent(student);

    }
    
    
    
    
    @FXML
    void processUpdate(ActionEvent event) throws FileNotFoundException, SQLException {
    	
    	student = new Student(updateStudentId, this.imageFile);
    	imageUtil.updateStudent(student);
    
    }
    
    @FXML
    void processDetail(ActionEvent event) throws FileNotFoundException, SQLException {
    	
    	//first call to util method
    	//insert into image view is here
    	
    	student = imageUtil.getStudent(updateStudentId);
    	
    	ivImage.setImage(student.getUserImage());
    	
    	
    	
    	

    }

}
