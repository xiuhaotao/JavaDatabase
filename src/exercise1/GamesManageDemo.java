package exercise1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class GamesManageDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016921512715478555L;
	private JPanel pMain, pEast, pWestUp, pWestDown;
	private JTextArea queryArea; // need to be changed to JTable later
	private JLabel lblQueryPId;
	private JButton btnGetReport;
	private JTextField txtQueryPId;
	private Box eastBox;

	// constructor
	public GamesManageDemo() {
		super("Games Management");

		// set east west panels in frame
		pMain = new JPanel();
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.X_AXIS));
		pEast = new JPanel();
		
		eastBox = Box.createVerticalBox();
		lblQueryPId = new JLabel("Enter the Player ID to query:");
		txtQueryPId = new JTextField(5);
		btnGetReport = new JButton("Get Report");
		pEast.setLayout(new GridLayout(3, 2, 5, 5));
		pEast.add(lblQueryPId);
		pEast.add(txtQueryPId);
		pEast.add(btnGetReport);

		// JTable resultTable = new JTable(reportDisplayModel);
		queryArea = new JTextArea();
		eastBox.add(pEast);

		// eastBox.add(new JScrollPane(resultTable));
		eastBox.add(queryArea);
		pMain.add(eastBox);
		add(pMain);

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500); // set window size
		setVisible(true);

		//btnAddGame.addActionListener(addGameBtnHandler);

		//btnGetReport.addActionListener(getReportBtnHandler);
		btnGetReport.addActionListener(openPlayerWin);

	}

	ActionListener openGameWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameEditWin win = new GameEditWin(GamesManageDemo.this);
			win.setVisible(true);
		}
	};
	
	ActionListener openPlayerWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerEditWin win = new PlayerEditWin(GamesManageDemo.this);
			win.setVisible(true);
		}
	};

	ActionListener getReportBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};

	public static void main(String[] args) {
		new GamesManageDemo();
	}

}
