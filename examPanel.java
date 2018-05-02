package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class examPanel extends JPanel {
	GameWindow gw;
	//acting ac = new acting();
	JLabel s, p;
	JButton yes;
	int c = 0;
	static int end_count = 0;
	String sta=null;

	examPanel(GameWindow gw) {
		this.gw = gw;
		setLayout(null);
		setBounds(400, 260, 200, 80);
		setBackground(Color.LIGHT_GRAY);

		c = 50 + (gw.study / 2);

		if (gw.week == 6) {
			s = new JLabel(gw.charname + "님의 중간고사 성적은");
			s.setBounds(10, 5, 200, 20);
			p = new JLabel(c + "점 입니다!");
			p.setBounds(10, 25, 200, 20);
		} else {
			s = new JLabel(gw.charname + "님의 기말고사 성적은");
			s.setBounds(10, 5, 200, 20);
			p = new JLabel(c + "점 입니다!");
			p.setBounds(10, 25, 200, 20);
		}
		yes = new JButton("확인");
		yes.setBounds(70, 50, 60, 25);
		yesListen Ybtn = new yesListen();
		yes.addActionListener(Ybtn);

		try {
			//Statement co = Database.conn.createStatement();
			String s;
			if (gw.week == 6) {
				s = "update:"+"update user set db_mid_exam='" + c + "'where db_id='" + gw.id + "';";
			} else {
				s = "update:"+"update user set db_final_exam='" + c + "'where db_id='" + gw.id + "';";
			}
			gw.gm.writer.println(s);
			gw.gm.writer.flush();
			
			s="runing:"+gw.id+",week";
			gw.gm.writer.println(s);
			gw.gm.writer.flush();
			
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				sta = gw.sta;
				System.out.println("시험쪽 "+sta);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			if(sta.equals("End") && gw.grade==4){
				end_count=1;
			}
			//ac.running(gw.id, "week");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		add(s);
		add(p);
		add(yes);
	}

	class yesListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			gw.examC = 1;
			setVisible(false);
			removeAll();
			if (end_count == 1) {
				go_end();
			}
		}
	}

	public void go_end() {
		try {
			gw.gm.show_ending(gw.id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
