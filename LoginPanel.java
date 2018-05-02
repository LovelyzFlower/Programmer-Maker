package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class LoginPanel extends JPanel {
	String adress = "C:/PM/";
	JTextField id;
	JLabel same1, same2, same3;
	JPasswordField pwd;
	GameMain gm = null;

	LoginPanel(GameMain gm) {
		setLayout(null);
		setBounds(360, 300, 280, 150);
		this.gm = gm;
		/*
		 * continue_btn = new JButton(new ImageIcon(adress + "continue.png"));
		 * Continuelistener listener = new Continuelistener();
		 * continue_btn.addActionListener(listener); continue_btn.setBounds(435,
		 * 500, p_width, p_height); continue_btn.setRolloverIcon(new
		 * ImageIcon(adress + "reverse_continue.png"));
		 * continue_btn.setPressedIcon(new ImageIcon(adress +
		 * "reverse_continue.png"));
		 */
		JButton btnlogin = new JButton(new ImageIcon(adress + "login.png"));// 로그인
		btnlogin.setBounds(90, 118, 85, 30);
		loginListen L_listen = new loginListen();
		btnlogin.addActionListener(L_listen);
		btnlogin.setRolloverIcon(new ImageIcon(adress + "reverse_login.png"));
		btnlogin.setPressedIcon(new ImageIcon(adress + "reverse_login.png"));

		JButton btncancel = new JButton(new ImageIcon(adress + "cancel.png"));// 취소
		btncancel.setBounds(185, 118, 85, 30);
		cancelListen C_listen = new cancelListen();
		btncancel.addActionListener(C_listen);
		btncancel.setRolloverIcon(new ImageIcon(adress + "reverse_cancel.png"));
		btncancel.setPressedIcon(new ImageIcon(adress + "reverse_cancel.png"));

		JLabel L_bg = new JLabel(new ImageIcon(adress + "loginwindow.png"));
		L_bg.setBounds(0, 0, 280, 150);

		JLabel idlabel = new JLabel("");// 아이디
		// idlabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		idlabel.setBounds(10, 43, 60, 30);
		JLabel pwdlabel = new JLabel("");// 비밀번호
		// pwdlabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		pwdlabel.setBounds(10, 78, 60, 30);

		id = new JTextField();
		id.setBounds(80, 43, 190, 30);
		pwd = new JPasswordField();
		pwd.setBounds(80, 78, 190, 30);

		same1 = new JLabel("Wrong!");
		same1.setBounds(10, 110, 280, 30);
		same2 = new JLabel("ID Please.");
		same2.setBounds(10, 110, 280, 30);
		same3 = new JLabel("P/W Please.");
		same3.setBounds(10, 110, 280, 30);

		same1.setVisible(false);
		same2.setVisible(false);
		same3.setVisible(false);

		add(idlabel);
		add(pwdlabel);
		add(id);
		add(pwd);
		add(btnlogin);
		add(btncancel);
		add(same1);
		add(same2);
		add(same3);
		add(L_bg);
	}

	class loginListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			login();
		}
	}

	class cancelListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gm.continue_btn.setEnabled(true);
			gm.newstart_btn.setEnabled(true);
			gm.loginPanel.setVisible(false);
			removeAll();
		}
	}

	public void login() {
		if (id.getText().equals("")) {
			System.out.println("ID NO");
			same1.setVisible(false);
			same2.setVisible(true);
			same3.setVisible(false);
		} else if (pwd.getText().equals("")) {
			System.out.println("PWD NO");
			same1.setVisible(false);
			same2.setVisible(false);
			same3.setVisible(true);
		} else {// id.getText().equals("lsy9336") && pwd.getText().equals("aaa")
			String s = "loginn:" + id.getText() + "," + pwd.getText();
			gm.writer.println(s);
			gm.writer.flush();
			
			String i = null;
			try {
				i = gm.reader.readLine();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if (i.equals("1")) {
				System.out.println("로그인됨");
				gm.showgame(id.getText());
			} else {
				same1.setVisible(true);
				same2.setVisible(false);
				same3.setVisible(false);
			}
		}
	}
}
