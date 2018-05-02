package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ballonPanel extends JPanel {
	GameWindow gw = null;
	JLabel hwaaa = null;
	String adress = "C:/PM/";
	String mtwtr, mae;
	JLabel workImage;

	ballonPanel(GameWindow gw, int x, String s, int y) {
		this.gw = gw;
		setLayout(null);
		setBounds(300, 100, 400, 500);
		setBackground(Color.LIGHT_GRAY);

		calculation(x);

		JLabel day = new JLabel(new ImageIcon(adress + "day.png"));
		day.setBounds(10, 10, 380, 50);
		JLabel info1 = new JLabel(mtwtr);
		info1.setBounds(60, 10, 190, 50);
		info1.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
		JLabel info2 = new JLabel(mae);
		info2.setBounds(260, 10, 190, 50);
		info2.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));

		if (y == 0) {// 기본상황
			workImage = new JLabel(new ImageIcon(adress + "w_" + s + ".jpg"));
		} else if (y == 1) {// 체력고갈 상황
			workImage = new JLabel(new ImageIcon(adress + "w_sleep.jpg"));
		} else {// 돈부족 상황
			workImage = new JLabel(new ImageIcon(adress + "w_NotMoney.jpg"));
		}
		workImage.setBounds(10, 70, 380, 300);

		JLabel hwaaa = new JLabel(s);
		if (y == 0) {
			hwaaa.setBounds(160, 380, 380, 100);
			hwaaa.setFont(new Font("Dialog", Font.CENTER_BASELINE, 30));
		} else {
			hwaaa.setBounds(30, 380, 380, 100);
			hwaaa.setFont(new Font("Dialog", Font.CENTER_BASELINE, 20));
		}
		

		add(info1);
		add(info2);
		add(workImage);
		add(hwaaa);
		add(day);
	}

	public void calculation(int a) {
		if (a / 3 == 0) { // 월요일
			mtwtr = "월요일";
		} else if (a / 3 == 1) {// 화요일
			mtwtr = "화요일";
		} else if (a / 3 == 2) { // 수요일
			mtwtr = "수요일";
		} else if (a / 3 == 3) {// 목요일
			mtwtr = "목요일";
		} else {// 금요일
			mtwtr = "금요일";
		}
		mae_c(a);
	}

	public void mae_c(int b) {
		if (b % 3 == 0) { // 오전
			mae = "오전";
		} else if (b % 3 == 1) {// 오후
			mae = "오후";
		} else {
			mae = "야간";
		}
	}
}