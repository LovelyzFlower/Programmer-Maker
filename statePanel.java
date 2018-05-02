package PM_game;

import java.awt.*;
import javax.swing.*;

public class statePanel extends JPanel {
   GameWindow gw;
   JLabel status_background;
   JLabel id, money, goodwill, stress, study, hp, hobby, game;
   String adress = "C:/PM/";

   statePanel(GameWindow gw) {
      this.gw = gw;
      setLayout(null);
      setBounds(600, 10, 200, 384);
      setBackground(Color.WHITE);
      status_background = new JLabel(new ImageIcon(adress + "status_window.png"));
      status_background.setBounds(0,0,200,384);
   
      id = new JLabel("" + gw.id); //아이디
      id.setBounds(100, 10, 200, 20);
      money = new JLabel("" + gw.money);//용돈
      money.setBounds(100, 57, 200, 20);
      hp = new JLabel("" + gw.hp);//체력
      hp.setBounds(100, 109, 200, 20);
      stress = new JLabel("" + gw.stress);//스트레스
      stress.setBounds(100, 157, 200, 20);
      study = new JLabel("" + gw.study);//공부력
      study.setBounds(100, 205, 200, 20);
      game = new JLabel("" + gw.game);//게임력
      game.setBounds(100, 253, 200, 20);
      hobby = new JLabel("" + gw.hobby);//전문성
      hobby.setBounds(100, 301, 200, 20);
      goodwill = new JLabel("" + gw.goodwill);//친밀도
      goodwill.setBounds(100, 349, 200, 20);

      add(id);
      add(money);
      add(hp);
      add(stress);
      add(study);
      add(game);
      add(hobby);
      add(goodwill);
      add(status_background);

   }
}