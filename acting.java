package PM_game;

import java.sql.*;

public class acting {
	calendPanel cP;
	examPanel eP;
	GameWindow gw;

	public void running(String id, String info) throws SQLException {
		Statement co = Database.conn.createStatement();
		ResultSet rsload = co.executeQuery("select * from user where db_id='" + id + "';");
		rsload.next();

		int temp;
		int temp2;
		int temp3;
		int temp4;
		if (info.equals("lesson")) // 수업듣기
		{
			temp = rsload.getInt("db_stress") + 2;
			temp2 = rsload.getInt("db_study") + 2;
			temp3 = rsload.getInt("db_hp") - 1;
			if (rsload.getInt("db_stress") >= 20)
				temp3--;
			if (rsload.getInt("db_hp") < 70)
				temp2--;
			if (rsload.getInt("db_study") >= 20 && rsload.getInt("db_study") < 29)
				temp2++;
			// if (rsload.getInt("db_study") >= 30)
			// temp2 = temp2*2;
			if (temp >= 100) { // 스트레스 최대제한
				temp = 100;
			}
			if (temp2 >= 100) { // 공부력 최대제한
				temp2 = 100;
			}
			if (temp3 <= 0) { // 체력 최소제한
				temp3 = 0;
				cP.sleep_count = 1; // 강제 슬립하라는 카운터 보내기
			}

			String s = "update user set db_stress='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_study='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_hp='" + temp3 + "'where db_id='" + id + "';";
			co.executeUpdate(s);

		} else if (info.equals("study")) // 공부하기
		{
			temp = rsload.getInt("db_stress") + 2;
			temp2 = rsload.getInt("db_study") + 1;
			temp3 = rsload.getInt("db_hp") - 1;
			temp4 = rsload.getInt("db_goodwill") - 1;

			if (rsload.getInt("db_stress") >= 20)
				temp3--;
			if (rsload.getInt("db_hp") < 70)
				temp2--;
			if (rsload.getInt("db_study") >= 20 && rsload.getInt("db_study") < 29)
				temp2++;
			// if (rsload.getInt("db_study") >= 30)
			// temp2 += temp2;
			if (temp >= 100) { // 스트레스 최대제한
				temp = 100;
			}
			if (temp2 >= 100) { // 공부력 최대제한
				temp2 = 100;
			}
			if (temp3 <= 0) { // 체력 최소제한
				temp3 = 0;
				cP.sleep_count = 1; // 강제 슬립하라는 카운터 보내기
			}
			if (temp4 <= 0) { // 친밀도 최소제한
				temp4 = 0;
			}

			String s = "update user set db_stress='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_study='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_hp='" + temp3 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_goodwill='" + temp4 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
		} else if (info.equals("play")) // 놀러가기
		{
			temp = rsload.getInt("db_stress") - 3;
			temp2 = rsload.getInt("db_money") - 3000;
			temp3 = rsload.getInt("db_goodwill") + 3;
			if (temp <= 0) { // 스트레스 최소제한
				temp = 0;
			}
			if (rsload.getInt("db_money") <= 2999) { // 용돈 최소제한
				temp2 = rsload.getInt("db_money");
				temp = rsload.getInt("db_stress");
				temp3 = rsload.getInt("db_goodwill");
				cP.money_count = 1; // 돈 없다고 카운터 보내기
			}
			if (temp3 >= 100) { // 친밀력 최대제한
				temp3 = 100;
			}

			String s = "update user set db_stress='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_money='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_goodwill='" + temp3 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
		} else if (info.equals("sleep")) // 잠자기
		{
			temp = rsload.getInt("db_stress") - 4;
			temp2 = rsload.getInt("db_hp") + 3;
			if (temp <= 0) { // 스트레스 최소제한
				temp = 0;
			}
			if (temp2 >= 100) { // 체력 최대제한
				temp2 = 100;
			}
			String s = "update user set db_stress='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_hp='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
		} else if (info.equals("sport")) // 운동하기
		{
			temp = rsload.getInt("db_stress") + 1;
			temp2 = rsload.getInt("db_hp") + 2;
			if (rsload.getInt("db_stress") >= 20) {
				temp2--;
			}
			if (temp >= 100) { // 스트레스 최대제한
				temp = 100;
			}
			if (temp2 >= 100) { // 체력 최대제한
				temp2 = 100;
			}
			String s = "update user set db_stress='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_hp='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
		} else if (info.equals("hobby")) // 전문과정
		{
			temp = rsload.getInt("db_hobby") + 1;
			temp2 = rsload.getInt("db_stress") + 1;
			temp3 = rsload.getInt("db_hp") - 1;
			if (rsload.getInt("db_stress") >= 20) {
				temp3--;
			}
			if (temp >= 100) { // 전문과정 최대제한
				temp = 100;
			}
			if (temp2 >= 100) { // 스트레스 최대제한
				temp2 = 100;
			}
			if (temp3 <= 0) { // 체력 최소제한
				temp3 = 0;
				cP.sleep_count = 1; // 강제 슬립하라는 카운터 보내기
			}
			String s = "update user set db_hobby='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_stress='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_hp='" + temp3 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
		} else if (info.equals("game")) // 게임하기
		{
			temp = rsload.getInt("db_game") + 2;
			temp2 = rsload.getInt("db_stress") - 1;
			temp3 = rsload.getInt("db_goodwill") + 1;
			if (temp >= 100) { // 게임력 최대제한
				temp = 100;
			}
			if (temp2 <= 0) { // 스트레스 최소제한
				temp2 = 0;
			}
			if (temp3 >= 100) { // 친밀도 최대제한
				temp3 = 100;
			}

			String s = "update user set db_game='" + temp + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_stress='" + temp2 + "'where db_id='" + id + "';";
			co.executeUpdate(s);
			s = "update user set db_goodwill='" + temp3 + "'where db_id='" + id + "';";
			co.executeUpdate(s);

		} else if (info.equals("week"))// 주차 늘리기
		{
			String s;
			temp = rsload.getInt("db_week") + 1;
			temp2 = rsload.getInt("db_money") + 50000;
			int p = (rsload.getInt("db_mid_exam") + rsload.getInt("db_final_exam")) / 2;

			if (temp == 13 && gw.grade == 4 && gw.term == 2) {
				s = "update user set db_grade_" + gw.grade + "_" + gw.term + "='" + p + "'where db_id='" + id + "';"; // 점수 조절
				co.executeUpdate(s);
				eP.end_count=1;
			} else {
				if (temp == 13) {
					s = "update user set db_grade_" + gw.grade + "_" + gw.term + "='" + p + "'where db_id='" + id + "';"; // 점수 조절
					co.executeUpdate(s);
					s = "update user set db_mid_exam='" + 0 + "'where db_id='" + id + "';";
					co.executeUpdate(s);
					s = "update user set db_final_exam='" + 0 + "'where db_id='" + id + "';";
					co.executeUpdate(s);
					temp = 0;
				}

				s = "update user set db_week='" + temp + "'where db_id='" + id + "';";
				co.executeUpdate(s);
				s = "update user set db_money='" + temp2 + "'where db_id='" + id + "';";
				co.executeUpdate(s);
			}
		}
	}
}