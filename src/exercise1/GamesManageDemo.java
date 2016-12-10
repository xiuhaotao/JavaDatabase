package exercise1;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GamesManageDemo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016921512715478555L;
	private JPanel pMain, pEast;
	private JTextArea queryArea; // need to be changed to JTable later
	private JLabel lblQueryPId;
	private JButton btnGetReport, btnAddGame, btnAddPlayer, btnPlayGame, btnEditPlayer;
	private JTextField txtQueryPId;
	private JTable table;
	private Box eastBox;
	private DataSource ds;

	// constructor
	public GamesManageDemo() {
		super("Games Management");
		ds = DataSource.getInstance();

		// set east west panels in frame
		pMain = new JPanel();
		pMain.setLayout(new BoxLayout(pMain, BoxLayout.X_AXIS));
		pEast = new JPanel();

		eastBox = Box.createVerticalBox();
		lblQueryPId = new JLabel("Enter the Player ID to query:");
		txtQueryPId = new JTextField(5);
		btnGetReport = new JButton("Get Report");
		btnAddGame = new JButton("Add Game");
		btnAddPlayer = new JButton("Add Player");
		btnPlayGame = new JButton("Play Game!");

		String[] columnNames = { "Player Name", "Game", "Playing Date", "Score" };
		ScoreDAO sDAO = new ScoreDAO(ds);
		List<ReportDisplayModel> result = sDAO.getScoreModelByPlayer(-1);
		Object[][] data = new Object[result.size()][4];
		for (int m=0; m< result.size(); m++) {
			data[m][0] = result.get(m).getPlayerName();
			data[m][1] = result.get(m).getGameName();
			data[m][2] = new SimpleDateFormat("DD-MMM-yyyy").format(result.get(m).getPlayingDate().getTime());
			data[m][3] = result.get(m).getScore();
		}
		table = new JTable(data, columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		pEast.setLayout(new GridLayout(4, 2, 5, 5));
		pEast.add(lblQueryPId);
		pEast.add(txtQueryPId);
		pEast.add(btnGetReport);
		pEast.add(btnAddGame);
		pEast.add(btnAddPlayer);
		pEast.add(btnPlayGame);

		// JTable resultTable = new JTable(reportDisplayModel);
		queryArea = new JTextArea();
		eastBox.add(pEast);

		// eastBox.add(new JScrollPane(resultTable));
		eastBox.add(queryArea);
		pMain.add(eastBox);
		add(pMain);
		JScrollPane scrollPane = new JScrollPane(table);
		 
        //Add the scroll pane to this panel.
        add(scrollPane);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(400, 500); // set window size
		setVisible(true);

		btnGetReport.addActionListener(getReportBtnHandler);
		btnAddGame.addActionListener(openGameWin);
		btnAddPlayer.addActionListener(openPlayerWin);
		btnPlayGame.addActionListener(openPlayGameWin);
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

	ActionListener openPlayGameWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayGameWin win = new PlayGameWin(GamesManageDemo.this);
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
