package project;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;




public class MyTableCellRender extends DefaultTableCellRenderer {

	@Override
	public Component getTableCellRendererComponent(
			JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {
		// TODO Auto-generated method stub
		super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
		
		Component comp = null;
		
		if(column == 1) {
			comp = new JButton();

		}
		return comp;

	}
	
	
}



class TableCell extends AbstractCellEditor implements TableCellEditor, TableCellRenderer {
    JButton jb;

    public TableCell() {
    	//
    	String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
		String DB_USER="c##ora_user";
		String DB_PASSWORD="jang";
		
		Connection conn = null;
		Statement st = null;
		ResultSet rs = null;
		
		
		String query = "SELECT S_PROJECT_IMAGE FROM TB_PROJECT_INFO";
		
		//드라이버 로드
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {

			conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

			st = conn.createStatement();

			rs = st.executeQuery(query);
	
			while(rs.next()) {

				String project_image = rs.getString(1);

				jb = new JButton();
				jb.setIcon(new ImageIcon("C:\\test\\FS.jpg"));
//				jb.setIcon(new ImageIcon(project_image));
//				System.out.println(project_image);
				
				jb.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println("보기");
					}
				});
			}
		}catch(Exception e) { 
			
		}
		
		finally{
			
				try {
					rs.close();
					st.close();
					conn.close();
				} catch (Exception e) {

					e.printStackTrace();
				}

		}
    	//table button click시 실행되는 리스너
		
    	
    	

       
    }

    @Override
    public Object getCellEditorValue() {
        return null;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
            int row, int column) {
        return jb;
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
            int column) {
        return jb;
    }
    
    
    
  
    

}
