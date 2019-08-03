package com.Asika.ExcelTest.DataBaseTest;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.Asika.ExcelTest.Util.Parse;
import com.Asika.ExcelTest.bean.TestFile;
public class DataBaseHandler {
               private static final String DRIVE_CLASS="com.mysql.cj.jdbc.Driver";
               private static final String URL = "jdbc:mysql://localhost:3306/test?useSSL=false&characterEncoding=utf-8&serverTimezone=GMT&allowPublicKeyRetrieval=true";
               private static final String USERNAME="root";
               private static final String PASSWORD="88233";
               private Connection connection = null;
               public void openConnection()  throws Exception{
				Class.forName(DRIVE_CLASS).newInstance();
				connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
               public void closeConnection() {
            	   if(connection!=null) {
            		   try {
						connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            	   }
			}
               public ResultSet queryAllFile()throws Exception {
				String sql="select * from file";
			    java.sql.Statement statement =connection.createStatement();
			    ResultSet rs = statement.executeQuery(sql);
			    return rs;
			}
               public ResultSet queryFileByTitle(String title)throws Exception {
   				String sql="select * from file where title= ?";
   				PreparedStatement preparedStatement= connection.prepareStatement(sql);
   			    preparedStatement.setString(1, title);
   			    ResultSet rs = preparedStatement.executeQuery();
   			 if(rs.next()){
   			  return rs;
   			}
   			 else {
   				return null;
   			 }
				
}
               public void insertFile(TestFile i) throws Exception {
            	   String sql= "INSERT IGNORE INTO file(title, num, date) values(?, ?, ?)";
            	   PreparedStatement stmt = connection.prepareStatement(sql); 
				   try {  
					    stmt.setString(1, i.getTitle());
					    stmt.setInt(2, i.getNum());
				        stmt.setDate(3, Parse.stringToDate(i.getTime()));
				        stmt.executeUpdate();
				       }  
				       catch (Exception ex) {  
				        System.out.println(ex.getMessage());  
				       }
			}
               public ResultSet queryFileByDate(Date start,Date end) throws SQLException{
            	   String sql = "SELECT * FROM file WHERE date BETWEEN ? AND ?";
            	   PreparedStatement stmt = connection.prepareStatement(sql);
            	   stmt.setDate(1, start);
            	   stmt.setDate(2, end);
            	   ResultSet rs = stmt.executeQuery();
            	   return rs;
               }
}