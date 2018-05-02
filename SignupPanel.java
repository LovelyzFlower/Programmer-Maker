package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

import PM_game.GameMain.Continuelistener;

public class SignupPanel extends JPanel {
	private JTextField id, charname, email;
	private JPasswordField password;
	JFrame complete_window;
	JButton btnsignup, btncancel;
	JLabel S_bg;
	JLabel same1, same2;
	GameMain gm = null;
	String adress = "C:/PM/";

	// Database db = new Database();
	// MemberManagementDB memberDB;

	SignupPanel(GameMain gm) {
		this.gm = gm;
		setLayout(null);
		setBounds(350, 250, 300, 220);

		// memberDB = new MemberManagementDB(db);

		JLabel idlabel = new JLabel("");// 아이디
		idlabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		idlabel.setBounds(10, 40, 60, 30);
		JLabel pwdlabel = new JLabel("");// 비밀번호
		pwdlabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		pwdlabel.setBounds(10, 75, 60, 30);
		JLabel namelabel = new JLabel("");// 닉네임
		namelabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		namelabel.setBounds(10, 110, 60, 30);
		JLabel emaillabel = new JLabel("");// 이메일
		emaillabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		emaillabel.setBounds(10, 145, 60, 30);

		id = new JTextField();
		id.setBounds(81, 40, 210, 30);
		password = new JPasswordField();
		password.setBounds(81, 78, 210, 30);
		charname = new JTextField();
		charname.setBounds(81, 116, 210, 30);
		email = new JTextField();
		email.setBounds(81, 154, 210, 30);

		S_bg = new JLabel(new ImageIcon(adress + "signupwindow.png"));
		S_bg.setBounds(0, 0, 300, 220);

		/*
		 * continue_btn = new JButton(new ImageIcon(adress + "continue.png"));
		 * Continuelistener listener = new Continuelistener();
		 * continue_btn.addActionListener(listener); continue_btn.setBounds(435,
		 * 500, p_width, p_height); continue_btn.setRolloverIcon(new
		 * ImageIcon(adress + "reverse_continue.png"));
		 * continue_btn.setPressedIcon(new ImageIcon(adress +
		 * "reverse_continue.png"));
		 */
		btnsignup = new JButton(new ImageIcon(adress + "register.png"));
		btnsignup.setBounds(115, 190, 85, 29);
		signupListen S_listen = new signupListen();
		btnsignup.addActionListener(S_listen);
		btnsignup.setRolloverIcon(new ImageIcon(adress + "reverse_register.png"));
		btnsignup.setRolloverIcon(new ImageIcon(adress + "reverse_register.png"));

		btncancel = new JButton(new ImageIcon(adress + "cancel.png"));
		btncancel.setBounds(205, 190, 85, 29);
		CancelListen c_listen = new CancelListen();
		btncancel.addActionListener(c_listen);
		btncancel.setRolloverIcon(new ImageIcon(adress + "reverse_cancel.png"));
		btncancel.setRolloverIcon(new ImageIcon(adress + "reverse_cancel.png"));

		same1 = new JLabel("BLANKS!");
		same1.setBounds(10, 185, 200, 30);

		same2 = new JLabel("Duplicate ID!");
		same2.setBounds(10, 185, 200, 30);

		same1.setVisible(false);
		same2.setVisible(false);

		add(idlabel);
		add(pwdlabel);
		add(namelabel);
		add(emaillabel);
		add(id);
		add(password);
		add(charname);
		add(email);
		add(btnsignup);
		add(btncancel);
		add(same1);
		add(same2);
		add(S_bg);
	}

	class signupListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			String db_id = id.getText();
			String db_pw = password.getText();
			String db_charname = charname.getText();
			String db_email = email.getText();

			if (id.getText().equals("") || password.getText().equals("") || charname.getText().equals("")
					|| email.getText().equals("")) {
				System.out.println("빈칸을 채워주세요.");
				same1.setVisible(true);
				same2.setVisible(false);

			} else {
				String s = "signup:" + db_id;
				gm.writer.println(s);
				gm.writer.flush();
				String i = null;
				try {
					i = gm.reader.readLine();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				if (i.equals("no")) {
					System.out.println("이미 존재하는 아이디");
					same1.setVisible(false);
					same2.setVisible(true);
				} else if(i.equals("ok")){
					addBook(db_id, db_pw, db_charname, db_email);
				}
			}

		}
	}

	private void addBook(String db_id, String db_pw, String db_charname, String db_email) {
		try {

			// Statement co = Database.conn.createStatement();
			String order = "insert:" + "insert into user(db_id, db_pw, db_charname, db_email) values";
			order += "('" + db_id + "','" + db_pw + "','" + db_charname + "','" + db_email + "')";

			gm.writer.println(order);
			gm.writer.flush();

			String i = gm.reader.readLine();
			if (i.equals("yes")) {
				show_complete();
			} else {
				System.out.println("회원등록 실패");
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void show_complete() {
		complete_window = new JFrame();
		complete_window.setTitle("띠링!");
		complete_window.setLayout(null);
		complete_window.setSize(200, 100);
		complete_window.setResizable(false);
		complete_window.setDefaultCloseOperation(close_com());

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dem = tk.getScreenSize();
		int xx = (int) (dem.getWidth() / 2 - 200 / 2);
		int yy = (int) (dem.getHeight() / 2 - 100 / 2);
		complete_window.setLocation(xx, yy);

		JLabel com = new JLabel("캐릭터 생성 완료!");
		com.setBounds(45, 0, 200, 40);

		JButton yes = new JButton("확인");
		yeslisten Y_btn = new yeslisten();
		yes.addActionListener(Y_btn);
		yes.setBounds(70, 40, 60, 25);

		complete_window.add(com);
		complete_window.add(yes);

		btnsignup.setEnabled(false);
		btncancel.setEnabled(false);
		complete_window.setVisible(true);
	}

	class yeslisten implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			complete_window.setVisible(false);
			remove(complete_window);
		}
	}

	public int close_com() {
		gm.newstart_btn.setEnabled(true);
		gm.continue_btn.setEnabled(true);
		setVisible(false);
		remove(this);
		return 1;
	}

	class CancelListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gm.continue_btn.setEnabled(true);
			gm.newstart_btn.setEnabled(true);
			gm.signupPanel.setVisible(false);
			removeAll();
		}
	}
}
