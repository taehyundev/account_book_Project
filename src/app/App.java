package app;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableRowSorter;

import controller.*;
import model.*;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextPane;
import javax.swing.RowFilter;

import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Window.Type;
public class App {
	private JFrame frame;
	private JTextField idField;
	private JPasswordField passwordField;
	private JPanel currPanel;
	private JTextField nameInput;
	private JTextField amountInput;

	private DBConnection conn;
	private JTextField t_nickname;
	private JTextField t_username;
	private JPasswordField t_pass;
	private JPasswordField t_passconfirm;
	private UserData user;
	private JTable table;
	private JTextField searchInput;
	private TableData td;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					App window = new App();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public App() {
		//생성자에서 userData 객체생성 및 DB 객체 생성
		user = new UserData();
		conn = new DBConnection();
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		td = new TableData();

		frame = new JFrame();
		frame.setTitle("\uAC00\uACC4\uBD80");
		frame.setType(Type.UTILITY);
		frame.setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// 테두리 하얀색
		LineBorder whiteBorder = new LineBorder(Color.white, 1);
		

		//sum Panel
		ImagePanel sumPanel = new ImagePanel(new ImageIcon("./image/main4.png").getImage());
		frame.getContentPane().add(sumPanel);
		sumPanel.setVisible(false);
		
		//tran Panel
		ImagePanel tranPanel = new ImagePanel(new ImageIcon("./image/main4.png").getImage());
		frame.getContentPane().add(tranPanel, BorderLayout.SOUTH);
		tranPanel.setVisible(false);
		
		//signup Panel
		ImagePanel signupPanel = new ImagePanel(new ImageIcon("./image/signupform.png").getImage());
		frame.getContentPane().add(signupPanel);
		
		signupPanel.setVisible(false);
	
		//login Panel
		ImagePanel loginPanel = new ImagePanel(new ImageIcon("./image/loginform.PNG").getImage());
		currPanel = loginPanel;
		frame.getContentPane().add(loginPanel);
		frame.setSize(loginPanel.getDim());
		frame.setPreferredSize(loginPanel.getDim());

		//id Field
		idField = new JTextField();
		idField.setBorder(whiteBorder);
		idField.setForeground(Color.BLACK);
		idField.setFont(new Font("굴림", Font.PLAIN, 18));
		idField.setBounds(550, 315, 248, 24);
		loginPanel.add(idField);
		idField.setColumns(10);
		
		//pass Field
		passwordField = new JPasswordField();
		passwordField.setBounds(548, 389, 250, 24);
		passwordField.setBorder(whiteBorder);
		loginPanel.add(passwordField);
	
		//sign up
		t_nickname = new JTextField();
		t_nickname.setBorder(whiteBorder);
		t_nickname.setBounds(529, 250, 272, 31);
		signupPanel.add(t_nickname);
		t_nickname.setColumns(10);
		
		t_username = new JTextField();
		t_username.setBorder(whiteBorder);
		t_username.setBounds(529, 310, 272, 28);
		signupPanel.add(t_username);
		t_username.setColumns(10);
		
		t_pass = new JPasswordField();
		t_pass.setBorder(whiteBorder);
		t_pass.setBounds(529, 367, 272, 31);
		signupPanel.add(t_pass);
		
		t_passconfirm = new JPasswordField();	
		t_passconfirm.setBorder(whiteBorder);
		t_passconfirm.setBounds(529, 425, 272, 31);

		signupPanel.add(t_passconfirm);
	
		
		JLabel LoginPageBtn = new JLabel("go Login");
		LoginPageBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				currPanel.setVisible(false);
				loginPanel.setVisible(true);
				currPanel = loginPanel;
			}
		});
		LoginPageBtn.setForeground(Color.WHITE);
		LoginPageBtn.setFont(new Font("Yu Gothic", Font.PLAIN, 22));
		LoginPageBtn.setBounds(39, 25, 106, 35);
		signupPanel.add(LoginPageBtn);
		
		JLabel tran_username = new JLabel();
		tran_username.setForeground(Color.WHITE);
		tran_username.setBounds(1060, 35, 125, 30);
		tranPanel.add(tran_username);
		JLabel sum_username = new JLabel("New label");
		sum_username.setForeground(Color.WHITE);
		sum_username.setBounds(1060, 35, 125, 30);
		sumPanel.add(sum_username);
		
		JLabel lblType = new JLabel("Type");
		lblType.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblType.setBounds(169, 236, 62, 38);
		tranPanel.add(lblType);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblName.setBounds(169, 173, 62, 54);
		tranPanel.add(lblName);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblAmount.setBounds(169, 297, 85, 24);
		tranPanel.add(lblAmount);
		
		JLabel lblmemo = new JLabel("memo");
		lblmemo.setFont(new Font("Sylfaen", Font.PLAIN, 20));
		lblmemo.setBounds(169, 347, 62, 18);
		tranPanel.add(lblmemo);
		
		nameInput = new JTextField();
		nameInput.setBounds(268, 188, 527, 24);
		tranPanel.add(nameInput);
		nameInput.setColumns(10);
		
		String[] typeArr = new String[] {"Deposit","Withdraw"};
		JComboBox typeInput = new JComboBox(typeArr);
		typeInput.setBounds(270, 243, 157, 24);
		tranPanel.add(typeInput);
		typeInput.setBackground(Color.white);
		
		amountInput = new JTextField();
		amountInput.setBounds(268, 294, 527, 24);
		tranPanel.add(amountInput);
		amountInput.setColumns(10);
		
		JTextPane memoInput = new JTextPane();
		memoInput.setBounds(268, 347, 527, 209);
		tranPanel.add(memoInput);
		memoInput.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		//가계부 처리
		JButton tranSubmit = new JButton("");
		tranSubmit.setIcon(new ImageIcon("./image/SignupBtn.PNG"));
		tranSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					String name = nameInput.getText();
					String type = (String)typeInput.getSelectedItem();
					String amount = amountInput.getText();
					String memo = memoInput.getText();
					if(name== "" || type == "" || memo =="" || Integer.parseInt(amount) <-1) {
						JOptionPane.showMessageDialog(null, "There was an error while writeing the data");
						
					}else {
						Calendar cal = Calendar.getInstance();
						int year = cal.get(Calendar.YEAR);
						int month = cal.get(Calendar.MONTH) + 1;
						int day = cal.get(Calendar.DAY_OF_MONTH);
						String dir = "./"+year+"_"+month+"_"+day+"_"+user.getNickname()+".csv";
						boolean fileExists = new File(dir).exists();
				
						FileWriter fw = new FileWriter(dir,true);
						if(!fileExists) {
							fw.append("Name, Type, Amount, Memo, Date\n");
						}
					fw.append(name+","+type+","+amount+","+memo+"\n");
						conn.setTranTable(user.getId(),name,type,amount,memo);
						JOptionPane.showMessageDialog(null, "Data Saved Successfully");
						nameInput.setText("");
						typeInput.setSelectedIndex(0);
						amountInput.setText("");
						memoInput.setText("");
						fw.close();
						td.refresh();
					}
				//	String name = nameInput.
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "There was an error while writeing the data");
				}catch (NumberFormatException e2) {
					// TODO: handle exception	
					JOptionPane.showMessageDialog(null, "There was an error while writeing the data");
					
				}
				
			}
		});
		tranSubmit.setFont(new Font("Sylfaen", Font.PLAIN, 23));
		tranSubmit.setBounds(270, 594, 230, 38);
		tranPanel.add(tranSubmit);
	
		//tranBtn
		JButton tranBtn = new JButton("");
		tranBtn.setBorderPainted(false);
		tranBtn.setContentAreaFilled(false);
		tranBtn.setFocusPainted(false);
		tranBtn.setIcon(new ImageIcon("./image/tranBtn.PNG"));
		tranBtn.setBounds(308, 0, 327, 86);
		tranPanel.add(tranBtn);
		
		JButton tranChangeBtn = new JButton("");
		tranChangeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currPanel.setVisible(false);
				
				sumPanel.setVisible(true);
				currPanel = sumPanel;
			}
		});
		tranChangeBtn.setBorderPainted(false);
		tranChangeBtn.setContentAreaFilled(false);
		tranChangeBtn.setFocusPainted(false);
		tranChangeBtn.setBounds(25, 0, 248, 86);
		tranPanel.add(tranChangeBtn);
		
		//sumBtn
		JButton sumBtn = new JButton("");
		sumBtn.setBorderPainted(false);
		sumBtn.setContentAreaFilled(false);
		sumBtn.setFocusPainted(false);
		sumBtn.setBackground(Color.WHITE);
		sumBtn.setIcon(new ImageIcon("./image/sumBtn.PNG"));
		sumBtn.setBounds(35, 0, 224, 86);
		sumPanel.add(sumBtn);
		
		JButton sumChangeBtn = new JButton("");
		sumChangeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				currPanel.setVisible(false);
				tranPanel.setVisible(true);
				currPanel = tranPanel;
			}
		});
		sumChangeBtn.setBorderPainted(false);
		sumChangeBtn.setContentAreaFilled(false);
		sumChangeBtn.setFocusPainted(false);
		sumChangeBtn.setBounds(302, 0, 316, 86);
		sumPanel.add(sumChangeBtn);
		
		JPanel tp = new JPanel();
		tp.setBounds(67, 198, 1006, 452);
		sumPanel.add(tp);
		tp.setOpaque(false);
		
		searchInput = new JTextField();
		searchInput.setBounds(269, 144, 783, 31);
		sumPanel.add(searchInput);
		searchInput.setColumns(10);
		
		JLabel SearchName = new JLabel("Search :");
		SearchName.setHorizontalAlignment(SwingConstants.RIGHT);
		SearchName.setFont(new Font("SimSun", Font.PLAIN, 35));
		SearchName.setForeground(Color.BLACK);
		SearchName.setBounds(61, 128, 194, 58);
		sumPanel.add(SearchName);
		
		table = new JTable(td);
		table.setBounds(14, 12, 1193, 582);
		table.setRowHeight(30);
		table.setFont(new Font("Sansserif",Font.BOLD, 15));
		table.setPreferredScrollableViewportSize(new Dimension(1155,445));
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(98, 219, 954, 413);
		sumPanel.add(scrollPane);
		
		JTableHeader header = table.getTableHeader();
		searchInput.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				String searchValue = searchInput.getText();
				TableRowSorter<AbstractTableModel> trs = new TableRowSorter<>(td);
				table.setRowSorter(trs);
				trs.setRowFilter(RowFilter.regexFilter(searchValue));
			
			}
		});
		header.setBackground(new Color(92,179,255));
		header.setForeground(new Color(255,255,255));
		//signup Btn
		JButton submitBtn = new JButton("");
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(t_pass.getText().equals(t_passconfirm.getText())) {
					String nickname = t_nickname.getText();
					String userId = t_username.getText();
					String userPwd = t_pass.getText();
					if(conn.signUp(nickname, userId, userPwd)) {
						JOptionPane.showMessageDialog(null, "Signup successfull");
						currPanel.setVisible(false);
						loginPanel.setVisible(true);
						currPanel = loginPanel;
					}else {

						JOptionPane.showMessageDialog(null, "There is a duplicate ID Error");
					}
				}else {
					JOptionPane.showMessageDialog(null, "not equals both password and cofirm password");
				}
				t_nickname.setText("");
				t_username.setText("");
				t_pass.setText("");
			}
		});

		submitBtn.setIcon(new ImageIcon("./image/SignupBtn.PNG"));
		submitBtn.setBounds(498, 487, 220, 35);
		signupPanel.add(submitBtn);
		
		
		//login Btn
		JButton loginBtn = new JButton("");
		loginBtn.setIcon(new ImageIcon("./image/loginBtn.PNG"));
		loginBtn.setPressedIcon(new ImageIcon("./image/loginBtnClick.PNG"));
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userId = idField.getText();
				String userPwd = passwordField.getText();
				System.out.println(userId +", "+userPwd);
				if(conn.isUser(userId, userPwd, user)){
					System.out.println(user.getNickname());
					JOptionPane.showMessageDialog(null, "You are logged in!");
					tran_username.setText("Hello , "+user.getNickname());
					sum_username.setText("Hello , "+user.getNickname());
					td.setUserId(userId);
					td.refresh();
					currPanel.setVisible(false);
					
					sumPanel.setVisible(true);
					currPanel = sumPanel;
				}else {
	
					JOptionPane.showMessageDialog(null, "You are Failed to Log in!");
				}
			}
		});
		loginBtn.setBounds(523, 507, 188, 41);
		loginPanel.add(loginBtn);
		
		JLabel signupBtn = new JLabel("Sign up");
		signupBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				currPanel.setVisible(false);
				signupPanel.setVisible(true);
				currPanel = signupPanel;
			}
		});
		signupBtn.setForeground(Color.WHITE);
		signupBtn.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 22));
		signupBtn.setBounds(1053, 12, 83, 41);
		loginPanel.add(signupBtn);
		
		frame.pack();
	}
}
