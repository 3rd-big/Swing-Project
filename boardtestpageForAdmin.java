package project;



import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Label;
import java.awt.SystemColor;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.Renderer;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;

public class boardtestpageForAdmin extends JFrame {

   private JPanel contentPane;

   private JTable table;
   private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기

   private String driver = "oracle.jdbc.driver.OracleDriver";
   String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String DB_USER="c##ora_user";
	String DB_PASSWORD="jang";


   private String colNames[] = {"Reviews","BoardNo"}; // 테이블 컬럼 값들
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


   boardtestpageForAdmin() {
	   
	   
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setBounds(100, 100, 1280, 720);
      contentPane = new JPanel();
      contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
      setContentPane(contentPane);
      contentPane.setLayout(null);

      JPanel navPanel = new JPanel();
      navPanel.setBackground(new Color(54, 33, 89));
      navPanel.setBounds(12, 23, 181, 660);
      contentPane.add(navPanel);
      navPanel.setLayout(null);

      JButton BrandColumn = new JButton("BRAND");
      BrandColumn.setForeground(Color.WHITE);
      BrandColumn.setFont(new Font("Segoe UI", Font.BOLD, 25));
      BrandColumn.setBackground(new Color(54, 33, 89));
      BrandColumn.setIcon(null);
      BrandColumn.setBounds(0, 32, 185, 76);
      BrandColumn.setFocusPainted(false);
      BrandColumn.setBorderPainted(false);
      navPanel.add(BrandColumn);

      JButton column1 = new JButton("Project Register");

      column1.setForeground(Color.WHITE);
      column1.setFont(new Font("Segoe UI", Font.BOLD, 12));
      column1.setBackground(new Color(85, 65, 118));
      column1.setBounds(0, 158, 185, 44);
      column1.setBorderPainted(false);
      column1.setFocusPainted(false);

      navPanel.add(column1);

      JButton column2 = new JButton("Review");
      column2.setForeground(Color.WHITE);
      column2.setFont(new Font("Segoe UI", Font.BOLD, 12));
      column2.setFocusPainted(false);
      column2.setBorderPainted(false);
      column2.setBackground(new Color(85, 65, 118));
      column2.setBounds(0, 224, 185, 44);
      navPanel.add(column2);

      JButton column3 = new JButton("My page");
      column3.setForeground(Color.WHITE);
      column3.setFont(new Font("Segoe UI", Font.BOLD, 12));
      column3.setFocusPainted(false);
      column3.setBorderPainted(false);
      column3.setBackground(new Color(85, 65, 118));
      column3.setBounds(0, 292, 185, 44);
      navPanel.add(column3);

      JPanel BoardAdmin_contentPanel = new JPanel();
      BoardAdmin_contentPanel.setBounds(193, 23, 1073, 660);
      contentPane.add(BoardAdmin_contentPanel);
      BoardAdmin_contentPanel.setLayout(null);

      JPanel headerPanel = new JPanel();
      headerPanel.setBackground(new Color(110, 89, 222));
      headerPanel.setBounds(0, 31, 1073, 79);
      BoardAdmin_contentPanel.add(headerPanel);
      headerPanel.setLayout(null);

      JLabel hederText = new JLabel("BoardList");
      hederText.setBounds(40, 10, 193, 57);
      hederText.setFont(new Font("Segoe UI", Font.PLAIN, 30));
      hederText.setForeground(Color.WHITE);
      headerPanel.add(hederText);
      
      FileOutputStream fos ;
   
      
      
      
      JPanel ptablepanel = new JPanel();
      ptablepanel.setBounds(0, 138, 1049, 499);
      BoardAdmin_contentPanel.add(ptablepanel);
      ptablepanel.setLayout(null);
      table = new JTable(model); // 테이블에 모델객체 삽입
      // table.addMouseListener(new JTableMouseListener()); // 테이블에 마우스리스너 연결
      scrollPane = new JScrollPane(table);
      scrollPane.setBounds(57, 21, 944, 389);
      ptablepanel.add(scrollPane);
      
      table.getTableHeader().setReorderingAllowed(false); // 이동 불가
      table.getTableHeader().setResizingAllowed(false); // 크기 조절 불가

      select();
      getContentPane().setLayout(null); // 레이아웃 배치관리자 삭제
      
      JButton btn_Remove = new JButton("Remove");
      btn_Remove.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		int row = table.getSelectedRow();
  		 
      		String t = (String) table.getModel().getValueAt(row, 1);

      		
      		DeleteCheckDialog delchd = new DeleteCheckDialog(t, table, row);
      		delchd.setVisible(true);
      		
      		
      		
      	}
      });
      btn_Remove.setForeground(Color.WHITE);
      btn_Remove.setFont(new Font("Segoe UI", Font.PLAIN, 15));
      btn_Remove.setBackground(new Color(102, 205, 170));
      btn_Remove.setBounds(463, 433, 98, 34);
      ptablepanel.add(btn_Remove);
      
     

   }
//테이블에 TB_BOARD 데이터 뿌리기
   private void select() {
      String query = "SELECT L_BOARD_CONTENT,N_BOARD_NO FROM TB_BOARD";    
      try(Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    	         Statement stat = conn.createStatement();
    	         ResultSet rs = stat.executeQuery(query);) {
         Class.forName(driver);
         

         while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
            model.addRow(new Object[] { rs.getString("L_BOARD_CONTENT"),rs.getString("N_BOARD_NO")});
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } 
   }
   
   
   
   

   public static void main(String[] args) {
   EventQueue.invokeLater(new Runnable() {
      public void run() {
         try {
        	 boardtestpageForAdmin frame = new boardtestpageForAdmin();
      frame.setVisible(true);
            frame.setLocationRelativeTo(null);    // 중앙 배치
            frame.setResizable(false);          // 사이즈 고정
         } catch (Exception e) {
            e.printStackTrace();
            System.out.println("11");
         }
      }
   });
   }
}

class DeleteCheckDialog extends JDialog{
	JLabel pcl = new JLabel("삭제되었습니다.");
	
	JButton Okbtn = new JButton("OK");
	
	DeleteCheckDialog(String t, JTable table, int row){
		
		setLayout(new FlowLayout());
		add(pcl);
		add(Okbtn);
		setSize(200,100);
		setLocationRelativeTo(null);
		
		pcl.setFont(new Font("굴림", Font.PLAIN, 18));
		
		//Ok버튼 리스너
		Okbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				DB db = new DB();
	      		db.DeleteReview(t, table);
	      		
      		DefaultTableModel tm = (DefaultTableModel)table.getModel();
	  		  if(row>=0 && row<table.getRowCount()) {
	  			  System.out.println("삭제");
	  			  tm.removeRow(row);
	  		  }
	  		  
	  		
				
				setVisible(false);

			}
		});
	}
	

}

