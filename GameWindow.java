package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.sql.*;

public class GameWindow extends JPanel {
	GameMain gm = null;
	RightmenuPanel RP = null;
	// acting ac = new acting();
	// Database db;
	String sql;
	JLabel ministate = null;
	JLabel background = null;
	JLabel end = null;
	JFrame chatFrame=null;
	JPanel ballonwindow = null;
	JPanel rightPanel = null;
	JPanel exitwindow = null;
	JPanel newswindow = null;
	JPanel statewindow = null;
	JPanel calendwindow = null;
	JPanel recordwindow = null;
	JPanel examwindow = null;
	JLabel wk, gr, te;
	String adress = "C:/PM/";
	static String sta=null;

	static String id, pw, charname, email;
	static int grade, term;

	int money, goodwill, stress, study, hp, hobby, game;
	int week, grade11, grade12, grade21, grade22, grade31, grade32, grade41, grade42;
	static int midexam, finalexam;

	static int newsC = 1, stateC = 1, calendC = 1, recordC = 1, examC = 1;

	GameWindow(GameMain gm) {
		chatFrame = new chat(this);
		this.gm = gm;
		setLayout(null);
		setBounds(0, 0, gm.width, gm.height);
		this.id = gm.id;
		bring();
		try {
	         Thread.sleep(1000);
	      } catch (InterruptedException e) {
	         e.printStackTrace();
	      }
		/*
		 * continue_btn = new JButton(new ImageIcon(adress + "continue.png"));
		 * Continuelistener listener = new Continuelistener();
		 * continue_btn.addActionListener(listener); continue_btn.setBounds(435,
		 * 500, p_width, p_height); continue_btn.setRolloverIcon(new
		 * ImageIcon(adress + "reverse_continue.png"));
		 * continue_btn.setPressedIcon(new ImageIcon(adress +
		 * "reverse_continue.png"));
		 * 
		 * bg = new JLabel(new ImageIcon(adress + "main_bg.png"));
		 * bg.setBounds(0, 0, width, height);
		 * 
		 */
		rightPanel = new RightmenuPanel(this);
		add(rightPanel);
		show_ministate();
		show_background();
		setVisible(true);
	}

	public void showgrade() {
		if (grade11 == 0) {
			grade = 1;
			term = 1;
			return;
		} else if (grade12 == 0) {
			grade = 1;
			term = 2;
			return;
		} else if (grade21 == 0) {
			grade = 2;
			term = 1;
			return;
		} else if (grade22 == 0) {
			grade = 2;
			term = 2;
			return;
		} else if (grade31 == 0) {
			grade = 3;
			term = 1;
			return;
		} else if (grade32 == 0) {
			grade = 3;
			term = 2;
			return;
		} else if (grade41 == 0) {
			grade = 4;
			term = 1;
			return;
		} else if (grade42 == 0) {
			grade = 4;
			term = 2;
			return;
		}
	}

	public void show_news() {
		if (newsC == 1) {
			newsC = 2;
			if (statewindow != null) {
				statewindow.setVisible(false);
				stateC = 1;
				remove(statewindow);
			}
			if (calendwindow != null) {
				calendwindow.setVisible(false);
				calendC = 1;
				remove(calendwindow);
			}
			if (recordwindow != null) {
				recordwindow.setVisible(false);
				recordC = 1;
				remove(recordwindow);
			}
			if (examwindow != null) {
				examwindow.setVisible(false);
				examC = 1;
				remove(examwindow);
			}

			newswindow = new newsPanel(this);
			add(newswindow, 10, 0);
			newswindow.repaint();
		} else {
			newswindow.setVisible(false);
			remove(newswindow);
			newsC = 1;
		}
	}

	public void show_state() {
		if (stateC == 1) {
			stateC = 2;
			if (newswindow != null) {
				newswindow.setVisible(false);
				newsC = 1;
				remove(newswindow);
			}
			if (calendwindow != null) {
				calendwindow.setVisible(false);
				calendC = 1;
				remove(calendwindow);
			}
			if (recordwindow != null) {
				recordwindow.setVisible(false);
				recordC = 1;
				remove(recordwindow);
			}
			if (examwindow != null) {
				examwindow.setVisible(false);
				examC = 1;
				remove(examwindow);
			}
			bring();
			statewindow = new statePanel(this);
			add(statewindow, 10, 0);
			statewindow.repaint();
		} else {
			statewindow.setVisible(false);
			remove(statewindow);
			stateC = 1;
		}
	}

	public void show_calend() {
		if (calendC == 1) {
			if (statewindow != null) {
				statewindow.setVisible(false);
				stateC = 1;
				remove(statewindow);
			}
			if (newswindow != null) {
				newswindow.setVisible(false);
				newsC = 1;
				remove(newswindow);
			}
			if (recordwindow != null) {
				recordwindow.setVisible(false);
				recordC = 1;
				remove(recordwindow);
			}
			if (examwindow != null) {
				examwindow.setVisible(false);
				examC = 1;
				remove(examwindow);
			}
			if (week == 6 || week == 12) {// 6주차 12주차 시험일때
				examC = 2;
				examwindow = new examPanel(this);
				add(examwindow, 10, 0);
				examwindow.repaint();

				bring();
				showgrade();
				if (week == 12 && grade == 4 && term == 2) {
					RP.news.setEnabled(false);
					RP.state.setEnabled(false);
					RP.calend.setEnabled(false);
					RP.chatting.setEnabled(false);
					RP.exit_btn.setEnabled(false);
					RP.record.setEnabled(false);
				}
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				ministate.setVisible(false);
				remove(ministate);
				show_ministate();
			} else {
				calendC = 2;
				calendwindow = new calendPanel(this);
				add(calendwindow, 10, 0);
				calendwindow.repaint();
			}
		} else {
			calendwindow.setVisible(false);
			remove(calendwindow);
			calendC = 1;
		}
	}

	public void show_record() {
		if (recordC == 1) {
			recordC = 2;
			if (statewindow != null) {
				statewindow.setVisible(false);
				stateC = 1;
				remove(statewindow);
			}
			if (newswindow != null) {
				newswindow.setVisible(false);
				newsC = 1;
				remove(newswindow);
			}
			if (calendwindow != null) {
				calendwindow.setVisible(false);
				calendC = 1;
				remove(calendwindow);
			}
			if (examwindow != null) {
				examwindow.setVisible(false);
				examC = 1;
				remove(examwindow);
			}
			recordwindow = new recordPanel(this);
			add(recordwindow, 10, 0);
			recordwindow.repaint();
		} else {
			recordC = 1;
			recordwindow.setVisible(false);
			remove(recordwindow);
		}
	}

	public void show_exit(RightmenuPanel RP) {
		this.RP = RP;
		RP.news.setEnabled(false);
		RP.state.setEnabled(false);
		RP.calend.setEnabled(false);
		RP.record.setEnabled(false);
		RP.exit_btn.setEnabled(false);
		RP.chatting.setEnabled(false);

		if (newswindow != null) {
			newswindow.setVisible(false);
			newswindow = null;
			newsC = 1;
		}
		if (statewindow != null) {
			statewindow.setVisible(false);
			statewindow = null;
			stateC = 1;
		}
		if (calendwindow != null) {
			calendwindow.setVisible(false);
			calendwindow = null;
			calendC = 1;
		}
		if (recordwindow != null) {
			recordwindow.setVisible(false);
			recordwindow = null;
			recordC = 1;
		}
		if (examwindow != null) {
			examwindow.setVisible(false);
			examC = 1;
			remove(examwindow);
		}

		exitwindow = new JPanel();
		exitwindow.setLayout(null);
		exitwindow.setBounds(400, 260, 200, 80);
		end = new JLabel(new ImageIcon(adress + "end.png"));
		end.setBounds(0, 0, 200, 80);
		end.setVisible(true);

		JButton yes = new JButton("예");
		yes.setBounds(15, 45, 80, 30);
		yesListen Ybtn = new yesListen();
		yes.addActionListener(Ybtn);
		JButton no = new JButton("아니요");
		no.setBounds(105, 45, 80, 30);
		noListen Nbtn = new noListen();
		no.addActionListener(Nbtn);

		exitwindow.add(yes);
		exitwindow.add(no);
		exitwindow.add(end);
		add(exitwindow, 10, 0);
		exitwindow.repaint();
	}

	public void visible() {
		RP.news.setEnabled(true);
		RP.state.setEnabled(true);
		RP.calend.setEnabled(true);
		RP.record.setEnabled(true);
		RP.exit_btn.setEnabled(true);
		if (RP.ch_count == 0) {
			RP.chatting.setEnabled(true);
		}
	}

	public void re_ministate() {
		bring();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ministate.setVisible(false);
		remove(ministate);
		show_ministate();
		ministate.repaint();
	}

	public void show_ministate() {
		ministate = new JLabel(new ImageIcon(adress + "Date.png"));
		ministate.setBounds(10, 10, 240, 200); // Date 크기
		showgrade();
		gr = new JLabel("" + grade + "");
		gr.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
		gr.setBounds(55, 82, 130, 30);

		te = new JLabel(term + "");
		te.setBounds(168, 82, 130, 30);
		te.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));

		wk = new JLabel(week + "주차");
		wk.setBounds(81, 157, 130, 30);
		wk.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));

		ministate.add(te);
		ministate.add(gr);
		ministate.add(wk);

		add(ministate, 12, 0);
		ministate.setVisible(true);
	}

	public void show_background() {
		background = new JLabel(new ImageIcon(adress + "background.jpg"));
		background.setBounds(0, 0, 1000, 650);
		add(background);
		background.setVisible(true);
	}

	public void show_chats() {
		chatFrame.setVisible(true);
	}

	public void show_ballon(int x, String s, int y) { // y가 0이면 기본상황, 1이면 체력고갈,
														// 2이면 돈 부족
		if (ballonwindow != null) {
			ballonwindow.setVisible(false);
			remove(ballonwindow);
		}
		ballonwindow = new ballonPanel(this, x, s, y);
		add(ballonwindow, 10, 0);
		ballonwindow.repaint();
	}

	public void close_ballon() {
		ballonwindow.setVisible(false);
		remove(ballonwindow);
	}

	class yesListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// save(); //나가기전 저장
			System.exit(0);
		}
	}

	class noListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			visible();
			exitwindow.setVisible(false);
			remove(exitwindow);
		}
	}

	public void bring() {
		String sql = "bringg:" + gm.id;
		System.out.println("뭐야 "+sql);
		gm.writer.println(sql);
		gm.writer.flush();
	}
	
	/*public void re_bring(String info){//근데 나눠서 어짜게 이거 리브링 아님
		//String info = null;
		try {
			info = gm.reader.readLine();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}*/
	
	public void re_bring(String info){
		int i = info.indexOf(",");
		charname = info.substring(0, i);
		info = info.substring(i + 1);
		i = info.indexOf(",");
		email = info.substring(0, i);
		info = info.substring(i + 1);
		i = info.indexOf(",");
		money = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		goodwill = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		stress = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		study = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		hp = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		hobby = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		game = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		week = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		midexam = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		finalexam = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade11 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade12 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade21 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade22 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade31 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade32 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		i = info.indexOf(",");
		grade41 = Integer.parseInt(info.substring(0, i));
		info = info.substring(i + 1);
		grade42 = Integer.parseInt(info);
	}
}