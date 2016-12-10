package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import exercise1.DataSource;
import model.ReportDisplayModel;
import model.ScoreDAO;

public class MainWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016921512715478555L;
	private JPanel contentPane;
	private JTextField playerTextField;
	private JTable table;
	private DataSource ds;

	public MainWin() {
		super("Games Management");
		ds = DataSource.getInstance();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 631, 338);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(3, 1, 0, 0));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		FlowLayout flowLayout = (FlowLayout) panel_2.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.add(panel_2);
		
		JButton btnAddGame = new JButton("Add Game");
		panel_2.add(btnAddGame);
		btnAddGame.addActionListener(openGameWin);
		
		JButton btnAddPlayer = new JButton("Add Player");
		panel_2.add(btnAddPlayer);
		btnAddPlayer.addActionListener(openPlayerWin);
		
		JButton btnPlayGame = new JButton("Play Game");
		panel_2.add(btnPlayGame);
		btnPlayGame.addActionListener(openPlayGameWin);

		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(Color.WHITE);
		panel.add(panel_3);
		panel_3.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblReportTitle = new JLabel("Player Score Report");
		lblReportTitle.setHorizontalAlignment(SwingConstants.CENTER);
		panel_3.add(lblReportTitle);
		
		JPanel panel_5 = new JPanel();
		panel.add(panel_5);
		
		JLabel lblPlayerName = new JLabel("Player Name");
		panel_5.add(lblPlayerName);
		
		playerTextField = new JTextField();
		panel_5.add(playerTextField);
		playerTextField.setColumns(10);
		
		JButton btnFilter = new JButton("Filter");
		panel_5.add(btnFilter);
		btnFilter.addActionListener(getReportBtnHandler);
		
		JButton btnEditPlayer = new JButton("Edit Player");
		panel_5.add(btnEditPlayer);
		btnEditPlayer.addActionListener(editPlayerBtnHandler);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		
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
		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);
	}

	ActionListener openGameWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameEditWin win = new GameEditWin(MainWin.this);
			win.setVisible(true);
		}
	};

	ActionListener openPlayerWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerEditWin win = new PlayerEditWin(MainWin.this);
			win.setVisible(true);
		}
	};

	ActionListener openPlayGameWin = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayGameWin win = new PlayGameWin(MainWin.this);
			win.setVisible(true);
		}
	};

	ActionListener getReportBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};

	ActionListener editPlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {

		}
	};
}
