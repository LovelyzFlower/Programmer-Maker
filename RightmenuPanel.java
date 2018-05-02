package PM_game;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class RightmenuPanel extends JPanel {
   GameWindow gw=null;
   JLabel G_bg=null;
   String adress = "C:/PM/";
   static JButton news,state,calend,record,exit_btn,chatting;
   JLabel name_pr;
   static int ch_count=0;
   
   RightmenuPanel(GameWindow gw){
      this.gw=gw;
      setLayout(null);
      setBounds(800, 10, 192, 384);
      G_bg = new JLabel(new ImageIcon(adress + "rightmenu.png"));
      G_bg.setBounds(0, 0, 192, 384);
      
      System.out.println(gw.charname);
      name_pr=new JLabel(""+gw.charname);
      name_pr.setBounds(65, 67, 200, 50);
      name_pr.setFont(new Font("Dialog", Font.BOLD, 16));
      
      news = new JButton(new ImageIcon(adress + "notice.png"));
      newsListen Nbtn = new newsListen();
      news.addActionListener(Nbtn);
      news.setBounds(7, 120, 89, 83);
      news.setRolloverIcon(new ImageIcon(adress + "reverse_notice.png"));
      news.setPressedIcon(new ImageIcon(adress +"reverse_notice.png"));
      
      
      state = new JButton(new ImageIcon(adress + "status.png"));
      stateListen Sbtn = new stateListen();
      state.addActionListener(Sbtn);
      state.setBounds(96, 120, 89, 83);
      state.setRolloverIcon(new ImageIcon(adress + "reverse_status.png"));
      state.setPressedIcon(new ImageIcon(adress +"reverse_status.png"));
      
      calend = new JButton(new ImageIcon(adress + "scheduling.png"));
      calendListen Cbtn = new calendListen();
      calend.addActionListener(Cbtn);
      calend.setBounds(7, 286, 89, 83);
      calend.setRolloverIcon(new ImageIcon(adress + "reverse_scheduling.png"));
      calend.setPressedIcon(new ImageIcon(adress +"reverse_scheduling.png"));
      
      record = new JButton(new ImageIcon(adress + "grade.png"));
      recordListen Rbtn = new recordListen();
      record.addActionListener(Rbtn);
      record.setBounds(7, 203, 89, 83);
      record.setRolloverIcon(new ImageIcon(adress + "reverse_grade.png"));
      record.setPressedIcon(new ImageIcon(adress +"reverse_grade.png"));
      
      exit_btn = new JButton(new ImageIcon(adress + "exit_out.png"));
      exitListen E_btn = new exitListen();
      exit_btn.addActionListener(E_btn);
      exit_btn.setBounds(96, 286, 89, 83);
      exit_btn.setRolloverIcon(new ImageIcon(adress + "reverse_exit_out.png"));
      exit_btn.setPressedIcon(new ImageIcon(adress +"reverse_exit_out.png"));
      
      chatting = new JButton(new ImageIcon(adress + "chatting.png"));
      chattingListen chat_btn = new chattingListen();
      chatting.addActionListener(chat_btn);
      chatting.setBounds(96,203,89,83);
      chatting.setRolloverIcon(new ImageIcon(adress + "reverse_chating.png"));
      chatting.setPressedIcon(new ImageIcon(adress +"reverse_chating.png"));
      
      add(name_pr);
      add(news);
      add(state);
      add(calend);
      add(record);
      add(exit_btn);
      add(chatting);
      add(G_bg);
   }
   class newsListen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         gw.show_news();
      }
   }
   class chattingListen implements ActionListener{ //network 넣을 부분
      public void actionPerformed(ActionEvent e){
    	  if(ch_count==0){
    		  ch_count=1;
    		  gw.chatFrame.setVisible(true);
    	  }else{
    		  ch_count=0;
    		  gw.chatFrame.setVisible(false);
    	  }
    	  
         gw.show_chats();
      }
   }
   class stateListen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         gw.show_state();
      }
   }
   class calendListen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         gw.show_calend();
      }
   }
   class recordListen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         gw.show_record();
      }
   }
   class exitListen implements ActionListener {
      public void actionPerformed(ActionEvent e) {
         go_exit();
      }
   }
   public void go_exit(){
      gw.show_exit(this);
   }
}