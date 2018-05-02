package PM_game;

import javax.swing.*;

import PM_game.RightmenuPanel.exitListen;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ending extends JPanel {
   JPanel END = null;
   JButton exit = null;
   String id;
   double res;// 첫번째 엔딩 - 학점 4.3 ~ 올A+엔딩 학점맥스 - 대기업 엔딩
   // 두번째 엔딩 - 학점 3.5이상 엔딩 - 일반취업엔딩
   int game;// 네번째 엔딩 - 게임력 90이상 - 프로게이머
   int friendly;// 여덟번째 엔딩 - 친밀도 90이상, 학점 4.0미만 - 소개취직
   int hobby;
   // 다섯번째 엔딩 - 전문과정 max 엔딩 - 킹갓제네럴 전문가 프리렌서 엔딩
   // 여섯번째 엔딩 - 전문과정 80 && 학점 4 이상 - 연구원엔딩
   // 아홉번째 엔딩 - 학점 3.5미만 3.0이상, 전문과정 50이상, 전산직 엔딩
   // 학점 F 3개 이상 - 백수
   // 학점 3.0미만 - 치킨집
   JPanel endPanel = null;
   JLabel EndLabel;
   String adress = "C:/PM/";
   String aaa;
   ending(String id) throws SQLException {
      this.id = id;
      setLayout(null);
      setBounds(0,0,1000,650);
      setBackground(Color.BLUE);//임시
      //setResult(id);
      res = res / 9;
      endPanel = new JPanel();
      endPanel.setBounds(200, 100, 400, 300);
      if (res >= 9) { // 학점맥스엔딩
         EndLabel = new JLabel(new ImageIcon(adress + "samsung.png"));
         aaa = "신입사원엔딩";
      }else if(game > 99){
         EndLabel = new JLabel(new ImageIcon(adress + "gamer.png"));
         aaa = "프로게이머엔딩";
      }else if(hobby > 90){
    	  aaa = "프리랜서엔딩";
    	  EndLabel = new JLabel(new ImageIcon(adress + "freelancer.png"));
      }else if(res >= 8 && hobby > 80){
    	  aaa = "연구원엔딩";
    	  EndLabel = new JLabel(new ImageIcon(adress + "advisor.png")); //연구원
      }else if(res >= 7 && friendly > 80){
    	  aaa = "전산직엔딩";
    	  EndLabel = new JLabel(new ImageIcon(adress + "ITSpecialist.png"));//전산직
      }else {
    	  aaa = "치킨집사장엔딩";
    	  EndLabel = new JLabel(new ImageIcon(adress + "chicken.png"));
      }
      EndLabel.setBounds(0, 0, 400, 300);
      endPanel.add(EndLabel);
      add(endPanel);
      
      exit = new JButton(aaa);
      
      exit.addActionListener(new ActionListener(){
    	  public void actionPerformed(ActionEvent e){
    		  System.exit(0);
    	  }
      });
      exit.setBounds(500,150,100,100);
      add(exit);
   }

   /*
   public void setResult(String id) throws SQLException {
      Statement co = Database.conn.createStatement();
      ResultSet rsload = co.executeQuery("select * from user where db_id='" + id + "';");
      rsload.next();

      int resultall[] = new int[8];

      resultall[0] = rsload.getInt("db_grade_1_1");
      resultall[1] = rsload.getInt("db_grade_1_2");
      resultall[2] = rsload.getInt("db_grade_2_1");
      resultall[3] = rsload.getInt("db_grade_2_2");
      resultall[4] = rsload.getInt("db_grade_3_1");
      resultall[5] = rsload.getInt("db_grade_3_2");
      resultall[6] = rsload.getInt("db_grade_4_1");
      resultall[7] = rsload.getInt("db_grade_4_2");
      int i = 0;
      for (i = 0; i < 8; i++) {
         if (resultall[i] >= 95) {
            res += 9;
         } else if (resultall[i] >= 90) {
            res += 8;
         } else if (resultall[i] >= 85) {
            res += 7;
         } else if (resultall[i] >= 80) {
            res += 6;
         } else if (resultall[i] >= 75) {
            res += 5;
         } else if (resultall[i] >= 70) {
            res += 4;
         } else if (resultall[i] >= 65) {
            res += 3;
         } else if (resultall[i] >= 60) {
            res += 2;
         } else {
            res += 0;
         }
      }

      game = rsload.getInt("db_game");
      friendly = rsload.getInt("db_goodwill");
      hobby = rsload.getInt("db_hobby");
   }
*/
}

// ending구성.

//