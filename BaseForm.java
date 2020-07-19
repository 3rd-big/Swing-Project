package project;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JList;
import javax.swing.JTextPane;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.DropMode;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.ScrollPane;
import java.awt.Scrollbar;

public class BaseForm extends JFrame {

   private JPanel contentPane;
   private JTextField textField_Title;
   private JScrollPane scrollPane;
   
   /**
    * Launch the application.
    */
   public static void main(String[] args) {
      EventQueue.invokeLater(new Runnable() {
         public void run() {
            try {
               BaseForm frame = new BaseForm();
               	frame.setVisible(true);
               	frame.setResizable(false);
       			frame.setLocationRelativeTo(null);
            } catch (Exception e) {
               e.printStackTrace();
            }
         }
      });
   }

   /**
    * Create the frame.
    */
   public BaseForm() {
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
      BrandColumn.setFont(new Font("굴림", Font.BOLD, 25));
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
      headerPanel.setBounds(0, 31, 1073, 79);
      headerPanel.setBackground(new Color(110, 89, 222));
      contentPanel.add(headerPanel);
      headerPanel.setLayout(null);
      
      JLabel headerText = new JLabel("Project Register");
      headerText.setBounds(40, 10, 238, 57);
      headerText.setFont(new Font("Segoe UI", Font.PLAIN, 30));
      headerText.setForeground(Color.WHITE);
      headerPanel.add(headerText);
      
      JPanel mpanel = new JPanel();
      mpanel.setBounds(0, 108, 1073, 552);
      contentPanel.add(mpanel);
      mpanel.setLayout(null);
      
      textField_Title = new JTextField();
      textField_Title.setBounds(170, 104, 680, 34);
      mpanel.add(textField_Title);
      textField_Title.setColumns(10);
      
      JButton btn_Attached = new JButton("");
      btn_Attached.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	}
      });
      btn_Attached.setIcon(new ImageIcon("C:\\test\\FS.jpg"));
      btn_Attached.setBounds(875, 104, 43, 34);
      mpanel.add(btn_Attached);
      
      JTextPane txtpnTitle = new JTextPane();
      txtpnTitle.setBackground(SystemColor.control);
      txtpnTitle.setText("Title");
      txtpnTitle.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      txtpnTitle.setBounds(79, 104, 65, 34);
      mpanel.add(txtpnTitle);
      
      JTextPane txtpnContent = new JTextPane();
      txtpnContent.setBackground(SystemColor.control);
      txtpnContent.setFont(new Font("Segoe UI", Font.PLAIN, 18));
      txtpnContent.setText("Content");
      txtpnContent.setBounds(79, 161, 85, 34);
      mpanel.add(txtpnContent);
      
      JButton btn_Post = new JButton("Post");
      btn_Post.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	}
      });
      btn_Post.setForeground(Color.WHITE);
      btn_Post.setBackground(new Color(102, 205, 170));
      btn_Post.setFont(new Font("Segoe UI", Font.BOLD, 15));
      btn_Post.setBounds(563, 475, 74, 34);
      mpanel.add(btn_Post);
      
      JButton btn_Back = new JButton("Back");
      btn_Back.addActionListener(new ActionListener() {
      	public void actionPerformed(ActionEvent e) {
      		
      	}
      });
      btn_Back.setForeground(Color.WHITE);
      btn_Back.setFont(new Font("Segoe UI", Font.PLAIN, 15));
      btn_Back.setBackground(new Color(102, 205, 170));
      btn_Back.setBounds(465, 475, 74, 34);
      mpanel.add(btn_Back);
      
      JTextArea textArea_Content = new JTextArea();
      scrollPane = new JScrollPane(textArea_Content, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
      scrollPane.setBounds(170, 161, 748, 297);
      mpanel.add(scrollPane);
      
      JPanel mainPanel = new JPanel();
      mainPanel.setBounds(0, 0, 1395, 800);
      contentPane.add(mainPanel);
   }
}