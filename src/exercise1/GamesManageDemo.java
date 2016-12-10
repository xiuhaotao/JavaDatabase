package exercise1;

import javax.swing.JFrame;

import exercise1.view.MainWin;

public class GamesManageDemo {

	public static void main(String[] args) {
		MainWin swingLayoutFrame = new MainWin();
		swingLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		swingLayoutFrame.setSize(600, 300); 
		swingLayoutFrame.setVisible(true); 
	}

}
