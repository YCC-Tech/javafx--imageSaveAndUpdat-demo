package application;


import java.io.File;
import java.io.FileNotFoundException;
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
    private Button btnBrowse;//to browse file 
    @FXML
    private Button btnSave;//to save new student data
    @FXML
    private Button btnDtail;//to get student with given studentId
    @FXML
    private Button btnUpdate;//to update the student info(in this case, user image)

    
    @FXML
    private ImageView ivImage;//to show user image
    
    @FXML
    private TextArea taImagePath;  //just testing image path
    
    private ImageUtil imageUtil = new ImageUtil();//utility object
   
    File imageFile;//to accept chosen image file 
    
    private Image userImage;   //to accept image of chosen image file 
    
    private Student student ;
    
    private Integer updateStudentId =4;//this is your studentId to be updated(this may be get after clicking 'detail' link)
    
   

    @FXML
    void processBrowse(ActionEvent event) {
    	
    	//choose a file of image
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.getExtensionFilters().add(new ExtensionFilter("Images", "*.jpg","*.jpeg","*.png","*.ico"));
    	this.imageFile = fileChooser.showOpenDialog(null);
    	if(imageFile!=null) {

    		//setting image file path to textarea
    	taImagePath.setText(this.imageFile.getAbsolutePath());

    	userImage = new Image(this.imageFile.toURI().toString());
    	
    	ivImage.setImage(userImage);
   }
    }
    
       /* save student information to db (in this case , use image)*/
    @FXML
    void processSave(ActionEvent event) throws FileNotFoundException, SQLException {
    	student = new Student(this.imageFile);
    	imageUtil.insertStudent(student);

    }
    
    
    
    /* update student information to db (in this case , use image)*/
    @FXML
    void processUpdate(ActionEvent event) throws FileNotFoundException, SQLException {
    	
    	student = new Student(updateStudentId, this.imageFile);
    	imageUtil.updateStudent(student);
    
    }
    
    
    /*get information of specified student*/
    @FXML
    void processDetail(ActionEvent event) throws FileNotFoundException, SQLException {
    	student = imageUtil.getStudent(updateStudentId);
    	
    	ivImage.setImage(student.getUserImage());
   
    }

}
