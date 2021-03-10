package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import database.DBConnection;
import javafx.scene.image.Image;

public class ImageUtil {
	private Connection connection;
	private PreparedStatement pStmt;
	private Statement stmt;
	private ResultSet rs;
	private FileInputStream fis;
	
	
	public int insertStudent(Student student) throws SQLException, FileNotFoundException {
		
		connection = DBConnection.getConnection();
		var rowUpdated = 0;
		pStmt = connection.prepareStatement("INSERT INTO `students` (`photo_url`) VALUES (?);");		
		fis = new FileInputStream(student.getImageFile());
		pStmt.setBinaryStream(1, (InputStream)fis, (int)student.getImageFile().length());	
		rowUpdated =pStmt.executeUpdate();
		return rowUpdated;
	}
	
	
	public int updateStudent(Student student) throws SQLException, FileNotFoundException {
		connection = DBConnection.getConnection();
		var rowUpdated = 0;
		pStmt = connection.prepareStatement("UPDATE `students` SET "

				+ "`photo_url` = ? " + "WHERE (`studentId` = ?);");

		fis = new FileInputStream(student.getImageFile());
		pStmt.setBinaryStream(1, (InputStream) fis, (int) student.getImageFile().length());
		pStmt.setInt(2, student.getStudentId());
		rowUpdated = pStmt.executeUpdate();
		return rowUpdated;
	}

	//get Specified student with id (get from page of showing all student)
	public Student getStudent(Integer studentId) throws SQLException, FileNotFoundException{
		
		Student student ;
		connection = DBConnection.getConnection();
		stmt = connection.createStatement();
		rs = stmt.executeQuery("select * from students where studentId='"+studentId+"';");
		
		while(rs.next()) {	
			
			InputStream is = rs.getBinaryStream("photo_url");
			OutputStream os = new FileOutputStream(new File("photo.jpg"));
			byte[] content = new byte[1024];
			int size = 0;
			try {
				while((size= is.read(content))!=-1) {
					os.write(content, 0, size);
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			try {
				os.close();
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		Image image = new Image("file:photo.jpg");
		student= new Student(image);
		return student;
}

}
