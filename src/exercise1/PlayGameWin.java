package exercise1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

public class PlayGameWin extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5625944313258155606L;
	private JLabel gameLabel, playerLabel, playDateLabel, scoreLabel;
	private JDatePickerImpl datePicker;
	private JComboBox<ComboItem> playerList, gameList;
	private JTextField txtScore;
	private JButton btnPlayGame;
	private DataSource ds;

	public PlayGameWin(GamesManageDemo frame) {
		super(frame, "Play Game", true);
		ds = DataSource.getInstance();
		JPanel mainWin = new JPanel();
		mainWin.setLayout(new GridLayout(5, 2, 1, 1));
		
		Vector<ComboItem> pModel = new Vector<ComboItem>();
		PlayerDAO pDAO = new PlayerDAO(ds);
		for (PlayerModel m : pDAO.getPlayerModel()) {
			pModel.add(new ComboItem(m.getId(), m.getFirstName() + " " + m.getLastName()));
		}		
		playerLabel = new JLabel("Player: ");
		playerList = new JComboBox<ComboItem>(pModel);
		
		Vector<ComboItem> gModel = new Vector<ComboItem>();
		GameDAO gDAO = new GameDAO(ds);
		for (GameModel m : gDAO.getGameModel()) {
			gModel.add(new ComboItem(m.getId(), m.getGameTitle()));
		}
		gameLabel = new JLabel("Game: ");
		gameList = new JComboBox<ComboItem>(gModel);

		playDateLabel = new JLabel("Play Date: ");

		UtilDateModel model = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
		datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
		datePicker.setBounds(220, 350, 120, 30);
		scoreLabel = new JLabel("Score: ");
		txtScore = new JTextField(15);
		btnPlayGame = new JButton("Play !");
		mainWin.add(playerLabel);
		mainWin.add(playerList);
		mainWin.add(gameLabel);
		mainWin.add(gameList);
		mainWin.add(playDateLabel);
		mainWin.add(datePicker);
		mainWin.add(scoreLabel);
		mainWin.add(txtScore);
		mainWin.add(btnPlayGame);
		add(mainWin);
		setBounds(200, 200, 300, 200);
		setResizable(false);

		btnPlayGame.addActionListener(playBtnHandler);
	}

	ActionListener playBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ScoreDAO dao = new ScoreDAO(ds);
			ScoreModel s = new ScoreModel();
			s.setPlayerId(((ComboItem)playerList.getSelectedItem()).getValue());
			s.setGameId(((ComboItem)gameList.getSelectedItem()).getValue());
			Calendar selectedValue = Calendar.getInstance();
			selectedValue.setTime((Date)datePicker.getModel().getValue());
			s.setPlayDate(selectedValue);
			s.setScore(Integer.parseInt(txtScore.getText()));
			dao.addScoreModel(s);
			dispose();
		}
	};
}
