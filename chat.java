package PM_game;

import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class chat extends JFrame {
	JTextArea incoming;
	JTextField outgoing;
	GameMain gm;
	GameWindow gw;
	//String id;
	JScrollPane qScroller;

	public chat(GameWindow gw) {

		this.gw = gw;
		//JFrame frame = new JFrame("채팅창");
		setTitle("채팅창");
		setResizable(false);
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(0, 255, 191));

		incoming = new JTextArea(20, 30); // was 15,50
		incoming.setLineWrap(true);
		incoming.setWrapStyleWord(true);
		incoming.setEditable(false);

		qScroller = new JScrollPane(incoming);
		qScroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		qScroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		outgoing = new JTextField(25);

		JButton sendButton = new JButton("전송");
		sendButton.setFont(new Font("나눔바른고딕", Font.PLAIN, 10));
		sendButton.addActionListener(new SendButtonListener());
		outgoing.addActionListener(new SendButtonListener());

		mainPanel.add(qScroller);
		mainPanel.add(outgoing);
		mainPanel.add(sendButton);

		
		Thread readerThread = new Thread(new IncomingReader());
		readerThread.start();

		add(BorderLayout.CENTER, mainPanel);
		setSize(400, 400);
		setVisible(false);
	}

	public class SendButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent ev) {
			try {
				gm.writer.print("chattt:" + gw.id + " : ");
				gm.writer.println(outgoing.getText());
				gm.writer.flush();

			} catch (Exception ex) {
				ex.printStackTrace();
			}
			outgoing.setText("");
			outgoing.requestFocus();
			qScroller.getVerticalScrollBar().setValue(qScroller.getVerticalScrollBar().getMaximum());
		}
	} // close SendButtonListener inner class

	public class IncomingReader implements Runnable {
		public void run() {
			String message, info;
			try {
				while ((message = gm.reader.readLine()) != null) {
					//System.out.println("read " + message);
					int i = message.indexOf(",");
					info = message.substring(0, i);
					message = message.substring(i+1);
					if(info.equals("bring")){
						gw.re_bring(message);
					}else if(info.equals("chatt")){
						incoming.append(message + "\n");
					}else if(info.equals("state")){
						gw.sta = message;
					}
				} // close while
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		} // close run
	} // close inner class

}
