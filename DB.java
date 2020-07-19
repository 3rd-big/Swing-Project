package test;

import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class DB {
	String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String DB_USER="c##ora_user";
	String DB_PASSWORD="jang";
	
	//signin
	private String UserNo;

	//project
	private String ProjectName;
	private String ProjectDescription;
	private String ProjectImage;
	private String TargetPrice;
	//review board
	private String BoardContent;
	private String BoardTitle;
	
	
	//project register 생성자
	public DB(String ProjectName, String ProjectDescription, String TargetPrice) {
		this.ProjectName = ProjectName;
		this.ProjectDescription = ProjectDescription;
		
		this.TargetPrice = TargetPrice;
				
	}
	
	
	//board 생성자
	public DB(String BoardContent) {
		
		this.BoardContent = BoardContent;
	}

	
	
	public void inputProjectDB() {
		String date = null;
		String projectNo=null;
		String getProjectImageQuery = "SELECT S_PROJECT_IMAGE FROM TB_PROJECT_INFO";
		String getDateQuery = "SELECT TO_CHAR(SYSDATE) FROM DUAL";
		String getProjectCountQuery = "select count(*) from TB_PROJECT_INFO";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		// Oracle 현재시간 얻어오기
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getDateQuery);)
		{	
			while(rs.next()) {
				date = rs.getString(1);
				System.out.println(date);
			}
		}catch (Exception e) {
			e.getMessage();
		}
		
		try {

			Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			Statement stmt = conn.createStatement();

			ResultSet rs = stmt.executeQuery(getProjectImageQuery);
	
			while(rs.next()) {

				String get_project_image = rs.getString(1);
				ProjectImage = get_project_image;

			}
		}catch(Exception e) { 
			
		}
		
		
		
		
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getProjectCountQuery);)
			{	
				while(rs.next()) {
					projectNo = rs.getString(1);
					int tmp = Integer.parseInt(projectNo) + 1;
					projectNo = Integer.toString(tmp);
				}
			}catch (Exception e) {
				e.getMessage();
			}
		
		String query = "INSERT INTO TB_PROJECT_INFO("
			     + "N_PROJECT_NO," 
			     + "S_PROJECT_NAME"
			     + "N_TARGET_PRICE"
			     + "N_TOTAL_PRICE"
			     + "L_DESCRIPTION"
			     + "S_PROJECT_IMAGE"
			     + "D_PROJECT_CREATED"
			     + "VALUES ( '" + projectNo + "', '" + ProjectName +"', '" + TargetPrice + "', '0', '" + ProjectDescription 
			     +"','"+ ProjectImage + "', '" + date + "')";
		
		System.out.println(query);
		
		
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);)
		{
			stmt.executeUpdate(query);
			
		}catch (Exception e) {
			e.getMessage();
		}
		 
	}
	public void inputReviewDB() {
		String date = null;
		String boardNo=null;
		
		String getDateQuery = "SELECT TO_CHAR(SYSDATE) FROM DUAL";
		String getBoardCountQuery = "select count(*) from TB_BOARD";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(getDateQuery);)
		{	
			while(rs.next()) {
				date = rs.getString(1);
				System.out.println(date);
			}
		}catch (Exception e) {
			e.getMessage();
		}
		
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getBoardCountQuery);)
			{	
				while(rs.next()) {
					boardNo = rs.getString(1);
					int tmp = Integer.parseInt(boardNo) + 1;
					boardNo = Integer.toString(tmp);
				}
			}catch (Exception e) {
				e.getMessage();
			}
		
		String query = "INSERT INTO TB_BOARD("
			     + "N_BOARD_NO," 
			     + "S_BOARD_SUBJECT"
			     + "S_BOARD_CONTENT"
			     + "D_BOARD_CREATED"
			     + "N_USER_NO"
			     + "VALUES ( '" + boardNo + "', '" + BoardTitle +"', '" + BoardContent +"', '" + date + "','"+UserNo
			     +"')";
		
		System.out.println(query);
		
	
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);)
		{
			stmt.executeUpdate(query);
			
		}catch (Exception e) {
			e.getMessage();
		}
	}
	
	
}