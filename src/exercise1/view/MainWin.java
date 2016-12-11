package exercise1.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import exercise1.DataSource;
import exercise1.model.PlayerDAO;
import exercise1.model.PlayerModel;
import exercise1.model.ReportDisplayModel;
import exercise1.model.ScoreDAO;

public class MainWin extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8016921512715478555L;
	private JPanel contentPane;
	private JButton btnEditPlayer;
	private JTable table;
	private JComboBox<ComboItem> playerList;
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

		{
			Vector<ComboItem> pModel = new Vector<ComboItem>();
			pModel.add(new ComboItem(-1, "ALL"));
			PlayerDAO pDAO = new PlayerDAO(ds);
			for (PlayerModel m : pDAO.getPlayerModel()) {
				pModel.add(new ComboItem(m.getId(), m.getFirstName() + " " + m.getLastName()));
			}
			playerList = new JComboBox<ComboItem>(pModel);
			playerList.addActionListener(playerListHandler);
			panel_5.add(playerList);
		}

		btnEditPlayer = new JButton("Edit Player");
		btnEditPlayer.setEnabled(false);
		panel_5.add(btnEditPlayer);
		btnEditPlayer.addActionListener(editPlayerBtnHandler);

		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.CENTER);
		panel_1.setLayout(new GridLayout(0, 1, 0, 0));

		table = new JTable() {
			private static final long serialVersionUID = -5283236123386910183L;

			public boolean isCellEditable(int nRow, int nCol) {
				return false;
			}
		};
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		updateTableData(tableModel, -1);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		panel_1.add(scrollPane);
	}

	public void updateTable() {
		updateTableData((DefaultTableModel) table.getModel(), ((ComboItem) playerList.getSelectedItem()).getValue());
	}

	private void updateTableData(DefaultTableModel tableModel, int selectedId) {
		String[] names = { "Player Name", "Game", "Playing Date", "Score" };
		Vector<String> columnNames = new Vector<String>(Arrays.asList(names));
		tableModel.setDataVector(new Vector<String>(), columnNames);
		tableModel.setColumnIdentifiers(columnNames);
		ScoreDAO sDAO = new ScoreDAO(ds);
		List<ReportDisplayModel> result = sDAO.getScoreModelByPlayer(selectedId);
		String[] data = new String[4];
		for (int i = 0; i < result.size(); i++) {
			data[0] = result.get(i).getPlayerName();
			data[1] = result.get(i).getGameName();
			data[2] = new SimpleDateFormat("DD-MMM-yyyy").format(result.get(i).getPlayingDate().getTime());
			data[3] = String.valueOf(result.get(i).getScore());
			tableModel.addRow(data);
		}
		tableModel.fireTableDataChanged();
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
			PlayerEditWin win = new PlayerEditWin(MainWin.this, "Add", 0);
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

	ActionListener playerListHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			@SuppressWarnings("unchecked")
			JComboBox<ComboItem> cb = (JComboBox<ComboItem>) e.getSource();
			int selectedId = ((ComboItem) cb.getSelectedItem()).getValue();
			if (-1 == selectedId) {
				btnEditPlayer.setEnabled(false);
			} else {
				btnEditPlayer.setEnabled(true);
			}
			updateTableData((DefaultTableModel) table.getModel(), selectedId);
		}
	};

	ActionListener editPlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			int selectedId = ((ComboItem) playerList.getSelectedItem()).getValue();
			PlayerEditWin win = new PlayerEditWin(MainWin.this, "Update", selectedId);
			win.setVisible(true);
		}
	};
}
