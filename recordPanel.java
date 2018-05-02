package PM_game;

import java.awt.*;
import javax.swing.*;

public class recordPanel extends JPanel {
	GameWindow gw;

	recordPanel(GameWindow gw) {
		this.gw = gw;
		setLayout(null);
		setBounds(180, 105, 550, 500);
		setBackground(Color.WHITE);

		JLabel record = new JLabel("성적표");
		record.setBounds(210, 0, 150, 50);
		record.setFont(new Font("Dialog", Font.CENTER_BASELINE, 40));

		add(record);

		if (gw.grade11 != 0) { // 1학년
			JLabel gr1 = new JLabel("1학년");
			gr1.setBounds(10, 80, 100, 30);
			gr1.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
			JLabel gr11 = new JLabel("▶1학기 : " + check(gw.grade11));
			gr11.setBounds(10, 110, 150, 30);
			gr11.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr1);
			add(gr11);
		}
		if (gw.grade12 != 0) {
			JLabel gr12 = new JLabel("▶2학기 : " + check(gw.grade12));
			gr12.setBounds(10, 140, 150, 30);
			gr12.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr12);
		}
		if (gw.grade21 != 0) { // 2학년
			JLabel gr2 = new JLabel("2학년");
			gr2.setBounds(10, 170, 100, 30);
			gr2.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
			JLabel gr21 = new JLabel("▶1학기 : " + check(gw.grade21));
			gr21.setBounds(10, 200, 150, 30);
			gr21.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr2);
			add(gr21);
		}
		if (gw.grade22 != 0) {
			JLabel gr22 = new JLabel("▶2학기 : " + check(gw.grade22));
			gr22.setBounds(10, 230, 150, 30);
			gr22.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr22);
		}
		if (gw.grade31 != 0) {// 3학년
			JLabel gr3 = new JLabel("3학년");
			gr3.setBounds(10, 260, 100, 30);
			gr3.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
			JLabel gr31 = new JLabel("▶1학기 : " + check(gw.grade31));
			gr31.setBounds(10, 290, 150, 30);
			gr31.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr3);
			add(gr31);
		}
		if (gw.grade32 != 0) {
			JLabel gr32 = new JLabel("▶2학기 : " + check(gw.grade32));
			gr32.setBounds(10, 320, 150, 30);
			gr32.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr32);
		}
		if (gw.grade41 != 0) {// 4학년
			JLabel gr4 = new JLabel("4학년");
			gr4.setBounds(10, 350, 100, 30);
			gr4.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
			JLabel gr41 = new JLabel("▶1학기 : " + check(gw.grade41));
			gr41.setBounds(10, 380, 150, 30);
			gr41.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr4);
			add(gr41);
		}
		if (gw.grade42 != 0) {
			JLabel gr42 = new JLabel("▶2학기 : " + check(gw.grade42));
			gr42.setBounds(10, 410, 150, 30);
			gr42.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
			add(gr42);
		}
	}

	public String check(int x) {
		String gr;

		if (x >= 90) {
			gr = "A+";
		} else if (x >= 85) {
			gr = "A";
		} else if (x >= 80) {
			gr = "B+";
		} else if (x >= 80) {
			gr = "B";
		} else if (x >= 80) {
			gr = "C+";
		} else if (x >= 80) {
			gr = "C";
		} else if (x >= 80) {
			gr = "D";
		} else {
			gr = "F";
		}

		return gr;
	}
}
