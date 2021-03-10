package application;

import java.io.File;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;

public class Student {
	
	private SimpleIntegerProperty studentId;
	private File imageFile;
	private Image userImage;
	
	
	
	
	public Student(Image userImage) {
		super();
		this.userImage = userImage;
	}



	public Student(Integer studentId, File imageFile) {
		super();
		this.studentId = new SimpleIntegerProperty(studentId);
		this.imageFile = imageFile;
	}

	
	
	public Student(File imageFile) {
		super();
		this.imageFile = imageFile;
	}



	public Integer getStudentId() {
		return studentId.get();
	}
	
	public File getImageFile() {
		return imageFile;
	}



	public Image getUserImage() {
		return userImage;
	}
	
	

}
