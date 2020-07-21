package project;

import java.sql.*;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class DB {
	String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String DB_USER="c##ora_user";
	String DB_PASSWORD="jang";
	

	
	private String colNames[] = {"내용" };
	private DefaultTableModel model = new DefaultTableModel(colNames, 0) {
	      @Override
	      public boolean isCellEditable(int row, int column) {
	         if (column >= 0) {
	            return false;
	         } else {
	            return true;
	         }
	      }
	   }; // 테이블 데이터 모델
	
	
	
	//signin
	private String UserNo;

	//project
	private String ProjectName;
	private String ProjectDescription;
	private String ProjectImage;
	private String TargetPrice;
	private String filePath;
	//review board
	private String BoardContent;
	private String BoardTitle;
	private int row;
	private int col;
	
	
	
	
	public DB() {

		
	}
	
	
	//project register 생성자
	public DB(String ProjectName, String ProjectDescription, String TargetPrice, String filePath) {
		this.ProjectName = ProjectName;
		this.ProjectDescription = ProjectDescription;
		this.filePath = filePath;
		this.TargetPrice = TargetPrice;
				
	}
	
	
	
	//board 생성자
	public DB(String BoardContent) {
		
		this.BoardContent = BoardContent;
	}

	public DB(String t, JTable table) {
		
	}
	
	
	
	
	//project db에 저장
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

		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(getProjectCountQuery);)
			{	
				while(rs.next()) {
					projectNo = rs.getString(1);
					int tmp = Integer.parseInt(projectNo) + 2001;
					projectNo = Integer.toString(tmp);
				}
			}catch (Exception e) {
				e.getMessage();
				System.out.println("8");
			}
		int tgp = Integer.parseInt(TargetPrice);
		String query = "INSERT INTO TB_PROJECT_INFO ("
			     + "N_PROJECT_NO," 
			     + "S_PROJECT_NAME, "
			     + "N_TARGET_PRICE, "
			     + "N_TOTAL_PRICE, "
			     + "L_DESCRIPTION, "
			     + "S_PROJECT_IMAGE, "
			     + "D_PROJECT_CREATED) "
			     + "VALUES ( " + projectNo + ", '" + ProjectName +"', '" + tgp + "', 0, '" + ProjectDescription 
			     +"','"+ filePath + "', SYSDATE)";
		
		System.out.println(query);
		
		
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(query);)
		{
			stmt.executeUpdate(query);
			
		}catch (Exception e) {
			e.getMessage();
			System.out.println("7");
		}
		 
	}
	public void inputReviewDB() {
		String date = null;
		String boardNo=null;
		String sysdate = null;
		String getDateQuery = "SELECT TO_CHAR(SYSDATE) FROM DUAL";
		String getBoardCountQuery = "select count(*) from TB_BOARD";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("6");
		}
		
		//
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
			System.out.println("5");
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
				System.out.println("4");
			}
		
		String ReviewContenQquery = "INSERT INTO TB_BOARD ( "
			     + "N_BOARD_NO, " 
			     + "L_BOARD_CONTENT, "
			     + "D_BOARD_CREATED) "
			     + "VALUES ( " + boardNo +", '" + BoardContent +"', " + sysdate + ")";
		
		System.out.println(ReviewContenQquery);
		
		
	
		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			PreparedStatement stmt = conn.prepareStatement(ReviewContenQquery);)
		{
			stmt.executeUpdate(ReviewContenQquery);
			System.out.println("load완료");
			
		}catch (Exception e) {

		}
	}
	
//	public boolean isAttached(String project_image) {
//		if(project_image!=null) {
//			return true;
//		}else {
//			return false;
//		}
//	}
	
	
	
	public void add() {
        //최근 입력된 리뷰내용의 boardno검색
      String CntQuery = "select count(*) from TB_BOARD";
      String boardno = null;
      try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	         Statement stat = conn.createStatement();
    	         ResultSet rs = stat.executeQuery(CntQuery);) {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         
         while(rs.next()) {
        	 boardno = rs.getString(1);
         }
         
         String query = "SELECT L_BOARD_CONTENT FROM TB_BOARD WHERE N_BOARD_NO="+boardno;
         try(Connection conn2 = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	         Statement stat2 = conn.createStatement();
    	         ResultSet rs2 = stat.executeQuery(query);) {
        	 
	         Class.forName("oracle.jdbc.driver.OracleDriver");
	         
	         
	         while(rs.next()) {
	        	 model.addRow(new Object[] { rs.getString("L_BOARD_CONTENT")});
	         }
         }catch(Exception e) { 
        	 
         }

         
      } catch (Exception e) {
         System.out.println(e.getMessage());
         System.out.println("3");
      }
   }
	
	
	public void DeleteReview(String t, JTable table) {
		String deleteQuery = "DELETE FROM TB_BOARD WHERE N_BOARD_NO="+t;

		try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			Statement stmt = conn.createStatement();) {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			stmt.executeUpdate(deleteQuery);
			conn.commit();
			
			
		} catch (Exception e) { }
		
		
	}
	
	
	
	
	
	
}