package PM_game;

import java.awt.*;
import javax.swing.*;

public class newsPanel extends JPanel{
	GameWindow gw;
	
	newsPanel(GameWindow gw){
		this.gw=gw;
		setLayout(null);
		setBounds(180, 25, 550, 580);
		setBackground(Color.GRAY);
	}
}
