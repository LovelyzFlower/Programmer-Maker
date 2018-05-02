package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class calendPanel extends JPanel {
	GameWindow gw;
	// acting ac = new acting();
	Timer timer = new Timer();
	repeat r = new repeat();
	String adress = "C:/PM/";
	RightmenuPanel RP;
	JPanel table;
	JLabel blank, week, error;
	JLabel morning, afternoon, evening;
	JButton work1, work2, work3, work4, work5, work6, work7;
	JButton reset, run;
	int count = 0;
	int i = 0;
	String sta = null;

	JLabel space[] = new JLabel[15];
	String work[] = new String[15];

	static int sleep_count = 0;
	static int money_count = 0;

	calendPanel(GameWindow gw) {
		this.gw = gw;
		setLayout(null);
		setBounds(120, 170, 600, 340);
		setBackground(Color.PINK);

		table = new JPanel();
		table.setLayout(null);
		table.setBounds(30, 30, 540, 280);
		table.setBackground(Color.WHITE);

		blank = new JLabel(new ImageIcon(adress + "blank.png"));
		blank.setBounds(0, 0, 40, 40);
		week = new JLabel(new ImageIcon(adress + "week.png"));
		week.setBounds(40, 0, 350, 40);

		morning = new JLabel(new ImageIcon(adress + "morning.png"));
		morning.setBounds(0, 40, 40, 80);
		afternoon = new JLabel(new ImageIcon(adress + "afternoon.png"));
		afternoon.setBounds(0, 120, 40, 80);
		evening = new JLabel(new ImageIcon(adress + "evening.png"));
		evening.setBounds(0, 200, 40, 80);

		space();

		work1 = new JButton("수업듣기");
		lessonListen lesson_btn = new lessonListen();
		work1.addActionListener(lesson_btn);
		work1.setBounds(395, 5, 140, 30);

		work2 = new JButton("공부하기");
		studyListen study_btn = new studyListen();
		work2.addActionListener(study_btn);
		work2.setBounds(395, 40, 140, 30);

		work3 = new JButton("놀러가기");
		playListen play_btn = new playListen();
		work3.addActionListener(play_btn);
		work3.setBounds(395, 75, 140, 30);

		work4 = new JButton("게임하기");
		gameListen game_btn = new gameListen();
		work4.addActionListener(game_btn);
		work4.setBounds(395, 110, 140, 30);

		work5 = new JButton("운동하기");
		sportListen sport_btn = new sportListen();
		work5.addActionListener(sport_btn);
		work5.setBounds(395, 145, 140, 30);

		work6 = new JButton("전문과정");
		hobbyListen hobby_btn = new hobbyListen();
		work6.addActionListener(hobby_btn);
		work6.setBounds(395, 180, 140, 30);

		work7 = new JButton("잠자기");
		sleepListen sleep_btn = new sleepListen();
		work7.addActionListener(sleep_btn);
		work7.setBounds(395, 215, 140, 30);

		reset = new JButton("리셋");
		resetListen reset_btn = new resetListen();
		reset.addActionListener(reset_btn);
		reset.setBounds(395, 250, 65, 25);
		reset.setEnabled(false);

		run = new JButton("실행");
		runListen run_btn = new runListen();
		run.addActionListener(run_btn);
		run.setBounds(470, 250, 65, 25);
		run.setEnabled(false);

		table.add(reset);
		table.add(run);
		table.add(work1);
		table.add(work2);
		table.add(work3);
		table.add(work4);
		table.add(work5);
		table.add(work6);
		table.add(work7);
		table.add(blank);
		table.add(week);
		table.add(morning);
		table.add(afternoon);
		table.add(evening);

		add(table);
	}

	class resetListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			for (int i = 0; i < 15; i++) {
				space[i].setIcon(new ImageIcon(adress + "null.png"));
				work[i] = null;
			}
			count = 0;
			lesson_check();
			all_yes();
			reset.setEnabled(false);
			run.setEnabled(false);
		}
	}

	class runListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			RP.news.setEnabled(false);
			RP.state.setEnabled(false);
			RP.calend.setEnabled(false);
			RP.record.setEnabled(false);
			RP.exit_btn.setEnabled(false);
			RP.chatting.setEnabled(false);
			gw.calendwindow.setVisible(false);
			remove(gw.calendwindow);
			run();
		}
	}

	class lessonListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "lesson.png"));
				work[count] = "lesson";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class studyListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "study.png"));
				work[count] = "study";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class playListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "play.png"));
				work[count] = "play";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class gameListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "game.png"));
				work[count] = "game";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class sportListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "sport.png"));
				work[count] = "sport";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class hobbyListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "hobby.png"));
				work[count] = "hobby";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	class sleepListen implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if (count != 15) {
				space[count].setIcon(new ImageIcon(adress + "sleep.png"));
				work[count] = "sleep";
				count++;
			}
			lesson_check();
			reset.setEnabled(true);
			if (count == 15) {
				all_no();
				run.setEnabled(true);
			}
		}
	}

	private void run() {
		timer.schedule(r, 0, 1000);
	}

	public void space() {
		for (int i = 0; i < 15; i++) {
			if (space[i] != null) {
				remove(space[i]);
			}
			space[i] = new JLabel(new ImageIcon(adress + "null.png"));
			space[i].setBounds(40 + ((i / 3) * 70), 40 + ((i % 3) * 80), 70, 80);
			table.add(space[i]);
		}
	}

	public void lesson_check() {
		if (count % 3 == 0) {
			work1.setEnabled(true);
		} else {
			work1.setEnabled(false);
		}
	}

	public void all_yes() {
		work1.setEnabled(true);
		work2.setEnabled(true);
		work3.setEnabled(true);
		work4.setEnabled(true);
		work5.setEnabled(true);
		work6.setEnabled(true);
		work7.setEnabled(true);
	}

	public void all_no() {
		work1.setEnabled(false);
		work2.setEnabled(false);
		work3.setEnabled(false);
		work4.setEnabled(false);
		work5.setEnabled(false);
		work6.setEnabled(false);
		work7.setEnabled(false);
	}

	class repeat extends TimerTask {// work[i]안에 들어가잇음 모든 일정이
		@Override
		public void run() {
			int arf=0;
			if (i != 15) {
				try {
					if (sleep_count == 1) {
						gw.show_ballon(i, "체력이 0이되어 휴식을 취합니다.", 1);
						String s = "runing:" + gw.id + ",sleep";
						gw.gm.writer.println(s);
						gw.gm.writer.flush();
						System.out.println(i + " : 체력고갈로 강제슬립");

						try {
							// sta = gw.gm.reader.readLine();
							sta = gw.sta;
						} catch (Exception ex) {
							ex.printStackTrace();
						}

					} else {
						String s = "runing:" + gw.id + "," + work[i];
						gw.gm.writer.println(s);
						gw.gm.writer.flush();

						try {
							// sta = gw.gm.reader.readLine();
							try {
								Thread.sleep(1000);
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
							sta = gw.sta;
							//System.out.println(i + "::" + sta);
						} catch (Exception ex) {
							ex.printStackTrace();
						}
						
						if (sta.equals("Sleep")) {
							sleep_count = 1;
						}
						if (sta.equals("NotMoney")) {
							money_count = 1;
						}
						if (money_count == 1) {
							gw.show_ballon(i, work[i] + ", 돈이 부족하다.", 2);
							System.out.println(i + " : " + work[i] + ", 돈 부족으로 아무것도 안함");
							money_count = 0;
						} else {
							gw.show_ballon(i, work[i], 0);
							System.out.println(i + " : " + work[i]);
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				i++;
				arf=1;
			} else if (i == 15 && arf==0) {
				System.out.println("stop");

				RP.news.setEnabled(true);
				RP.state.setEnabled(true);
				RP.calend.setEnabled(true);
				RP.record.setEnabled(true);
				RP.exit_btn.setEnabled(true);
				RP.chatting.setEnabled(true);

				gw.calendC = 1;
				sleep_count = 0;
				try {
					String s = "runing:" + gw.id + ",week";
					gw.gm.writer.println(s);
					gw.gm.writer.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				try {
					// sta = gw.gm.reader.readLine();
					sta = gw.sta;
					//System.out.println("마지막 "+sta);
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				gw.re_ministate();
				gw.close_ballon();
				timer.cancel();

			}
		}
	}
}
