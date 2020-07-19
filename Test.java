package project;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Label;
import java.awt.SystemColor;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.Renderer;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class Test extends JFrame {

   private JPanel contentPane;

   private JTable table;
   private JScrollPane scrollPane; // 테이블 스크롤바 자동으로 생성되게 하기

   private String driver = "oracle.jdbc.driver.OracleDriver";
   String DB_URL = "jdbc:oracle:thin:@localhost:1521:xe";
	String DB_USER="c##ora_user";
	String DB_PASSWORD="jang";


   private String colNames[] = { "project image", "project name" }; // 테이블 컬럼 값들
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
   private Connection conn = null;
   private Statement stat = null;
   private ResultSet rs = null;

   Test() {

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

      JPanel contentPanel = new JPanel();
      contentPanel.setBounds(193, 23, 1073, 660);
      contentPane.add(contentPanel);
      contentPanel.setLayout(null);

      JPanel headerPanel = new JPanel();
      headerPanel.setBackground(new Color(110, 89, 222));
      headerPanel.setBounds(0, 31, 1073, 79);
      contentPanel.add(headerPanel);
      headerPanel.setLayout(null);

      JLabel hederText = new JLabel("Project");
      hederText.setBounds(40, 10, 193, 57);
      hederText.setFont(new Font("Segoe UI", Font.PLAIN, 30));
      hederText.setForeground(Color.WHITE);
      headerPanel.add(hederText);
      
      FileOutputStream fos ;
   
      
      
      
      JPanel ptablepanel = new JPanel();
      ptablepanel.setBounds(12, 156, 1049, 494);
      contentPanel.add(ptablepanel);
      ptablepanel.setLayout(null);
      table = new JTable(model); // 테이블에 모델객체 삽입
      table.addMouseListener(new JTableMouseListener() {
    	  public void mouseClicked(java.awt.event.MouseEvent e) {
              
              table = (JTable)e.getSource();
              int row = table.getSelectedRow();     
              int col = table.getSelectedColumn();        
          
//              System.out.println(model.getValueAt(row, col));   
                  
          }
          public void mouseEntered(java.awt.event.MouseEvent e) {
          }
          public void mouseExited(java.awt.event.MouseEvent e) {    
          }
          public void mousePressed(java.awt.event.MouseEvent e) {
          }
          public void mouseReleased(java.awt.event.MouseEvent e) {
          }
      }); // 테이블에 마우스리스너 연결
      scrollPane = new JScrollPane(table);
      scrollPane.setBounds(59, 22, 897, 445);
      ptablepanel.add(scrollPane);

      table.getTableHeader().setReorderingAllowed(false); // 이동 불가
      table.getTableHeader().setResizingAllowed(false); // 크기 조절 불가
      //
      
      //
      table.setRowHeight(150);
      //
      
      
      table.getColumnModel().getColumn(1).setMaxWidth(400);
      table.getColumnModel().getColumn(1).setMinWidth(400);
      table.getColumnModel().getColumn(1).setWidth(400);
      
      //tble에 DB에 있는 내용 로드
      select();
      getContentPane().setLayout(null); // 레이아웃 배치관리자 삭제
      
      //table에 버튼추가 render
      table.getColumnModel().getColumn(0).setCellRenderer(new TableCell());
      table.getDefaultEditor(String.class).addCellEditorListener(table);
      //
      //table 내용 가운데 정렬 메소드 호출
      tableCellCenter(table);
      //
      
      JPanel mainPanel = new JPanel();
      mainPanel.setBounds(0, 0, 1395, 800);
      contentPane.add(mainPanel);

   }

   private void select() {
      String query = "SELECT S_PROJECT_IMAGE,S_PROJECT_NAME FROM TB_PROJECT_INFO";    
      try {
         Class.forName(driver);
         conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
         stat = conn.createStatement();
         rs = stat.executeQuery(query);// 리턴받아와서 데이터를 사용할 객체생성


         while (rs.next()) { // 각각 값을 가져와서 테이블값들을 추가
            model.addRow(new Object[] { rs.getString("S_PROJECT_IMAGE"),rs.getString("S_PROJECT_NAME")});

            
         }
      } catch (Exception e) {
         System.out.println(e.getMessage());
      } finally {
         try {
            conn.close();
            stat.close();
            rs.close();
         } catch (Exception e2) {
         }
      }
   }
//table 내용 가운데 정렬 메소드
   public void tableCellCenter(JTable t){
	    // 테이블 내용 가운데 정렬하기
	      DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
	      dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로
	     
	      TableColumnModel tcm = t.getColumnModel() ; // 정렬할 테이블의 컬럼모델을 가져옴
	     
	      //전체 열에 지정
	      //for(int i = 0 ; i < tcm.getColumnCount() ; i++){
	      //tcm.getColumn(i).setCellRenderer(dtcr);  
	      // 컬럼모델에서 컬럼의 갯수만큼 컬럼을 가져와 for문을 이용하여
	      // 각각의 셀렌더러를 아까 생성한 dtcr에 set해줌
	      //}
	       
	      //특정 열에 지정
	      
	      tcm.getColumn(1).setCellRenderer(dtcr);
	    }
	
   
   
   public static void main(String[] args) {
   EventQueue.invokeLater(new Runnable() {
      public void run() {
         try {
        	 Test frame = new Test();
      frame.setVisible(true);
            frame.setLocationRelativeTo(null);    // 중앙 배치
            frame.setResizable(false);          // 사이즈 고정
         } catch (Exception e) {
            e.printStackTrace();
         }
      }
   });
   }
}



