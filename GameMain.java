package PM_game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.*;

@SuppressWarnings("serial")
public class GameMain extends JFrame {
	int width = 1000;// ��ü ����
	int height = 650;// ��ü ����
	int p_width = 130;// ��ư ����
	int p_height = 50;// ��ư ����
	String adress = "C:/PM/";

	static BufferedReader reader;
	static PrintWriter writer;
	static Socket sock;
	
	//Database db = new Database();
	//MemberManagementDB memberDB;

	JFrame window = new JFrame();
	JFrame chatFrame =null;
	JLabel bg, L_bg, G_bg;
	JButton newstart_btn, continue_btn, exit_btn;
	JPanel mainPanel = null; // 메인화면
	JPanel gamePanel = null; // 게임화면
	JPanel endPanel =null; //엔딩화면

	JPanel loginPanel = null;
	JPanel signupPanel = null;

	String id;

	public GameMain() {
		setTitle("Dong-a Pro-grammer Maker Game");
		setResizable(false);
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//memberDB = new MemberManagementDB(db);
		setLayout(null);
		setSize(width, height);
		setLocation();

		//Database.connectionDB();
		//Database.makeUserTable();
		setUpNetworking();

		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBounds(0, 0, width, height);

		bg = new JLabel(new ImageIcon(adress + "main_bg.png"));
		bg.setBounds(0, 0, width, height);

		newstart_btn = new JButton(new ImageIcon(adress + "newstart.png"));
		Newlistener newlisten = new Newlistener();
		newstart_btn.addActionListener(newlisten);
		newstart_btn.setBounds(235, 500, p_width, p_height);
		newstart_btn.setRolloverIcon(new ImageIcon(adress + "reverse_new.png"));
		newstart_btn.setPressedIcon(new ImageIcon(adress + "reverse_new.png"));

		continue_btn = new JButton(new ImageIcon(adress + "continue.png"));
		Continuelistener listener = new Continuelistener();
		continue_btn.addActionListener(listener);
		continue_btn.setBounds(435, 500, p_width, p_height);
		continue_btn.setRolloverIcon(new ImageIcon(adress + "reverse_continue.png"));
		continue_btn.setPressedIcon(new ImageIcon(adress + "reverse_continue.png"));

		exit_btn = new JButton(new ImageIcon(adress + "exit.png"));
		Exitlistener exitlisten = new Exitlistener();
		exit_btn.addActionListener(exitlisten);
		exit_btn.setBounds(635, 500, p_width, p_height);
		exit_btn.setRolloverIcon(new ImageIcon(adress + "reverse_exit.png"));
		exit_btn.setPressedIcon(new ImageIcon(adress + "reverse_exit.png"));

		mainPanel.add(newstart_btn);
		mainPanel.add(continue_btn);
		mainPanel.add(exit_btn);
		mainPanel.add(bg);
		add(mainPanel);

		setVisible(true);
	}

	class Continuelistener implements ActionListener { // 이어하기 누를때
		public void actionPerformed(ActionEvent e) {
			continue_btn.setEnabled(false);
			newstart_btn.setEnabled(false);
			showLoginWindow();
		}
	}

	class Newlistener implements ActionListener { // 새로하기 누를때
		public void actionPerformed(ActionEvent e) {
			continue_btn.setEnabled(false);
			newstart_btn.setEnabled(false);
			showSignupWindow();
		}
	}

	class Exitlistener implements ActionListener { // 종료하기 누를때
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

	private void setLocation() {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dem = tk.getScreenSize();
		int xx = (int) (dem.getWidth() / 2 - width / 2);
		int yy = (int) (dem.getHeight() / 2 - height / 2);
		this.setLocation(xx, yy);
	}

	public void showLoginWindow() {
		if (loginPanel != null) {
			remove(loginPanel);
		}
		if (signupPanel != null) {
			remove(signupPanel);
		}
		loginPanel = new LoginPanel(this);
		mainPanel.add(loginPanel);
		loginPanel.repaint();

	}

	public void showSignupWindow() {
		if (signupPanel != null) {
			remove(signupPanel);
		}
		if (loginPanel != null) {
			remove(loginPanel);
		}
		signupPanel = new SignupPanel(this);
		mainPanel.add(signupPanel);
		signupPanel.repaint();
	}
	public void showgame(String id) {
		this.id = id;
		mainPanel.setVisible(false);
		remove(mainPanel);
		gamePanel = new GameWindow(this);
		add(gamePanel);
	}
	public void show_ending(String id) throws SQLException{
		gamePanel.setVisible(false);
		remove(gamePanel);
		endPanel = new ending(id);
		add(endPanel);
	}
	private void setUpNetworking() {
		try {
			sock = new Socket("121.146.90.50", 15326);
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);

			writer = new PrintWriter(sock.getOutputStream());

			System.out.println("networking established");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	} // close setUpNetworking
	public static void main(String[] args) {
		GameMain gm = new GameMain();
	}
}
