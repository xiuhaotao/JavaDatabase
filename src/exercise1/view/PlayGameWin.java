package exercise1.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
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
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;

import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import exercise1.DataSource;
import exercise1.model.GameDAO;
import exercise1.model.GameModel;
import exercise1.model.PlayerDAO;
import exercise1.model.PlayerModel;
import exercise1.model.ScoreDAO;
import exercise1.model.ScoreModel;

public class PlayGameWin extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5625944313258155606L;
	private final JPanel contentPanel = new JPanel();
	private JDatePickerImpl datePicker;
	private JComboBox<ComboItem> playerList, gameList;
	private JFormattedTextField scoreTextField;
	private MainWin pFrame;
	private DataSource ds;

	public PlayGameWin(MainWin frame) {
		super(frame, "Play Game", true);
		pFrame = frame;
		ds = DataSource.getInstance();
		setBounds(100, 100, 320, 223);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(5, 2, 5, 0));
			{
				JLabel lblPlayer = new JLabel("Player");
				lblPlayer.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblPlayer);
			}
			{
				Vector<ComboItem> pModel = new Vector<ComboItem>();
				PlayerDAO pDAO = new PlayerDAO(ds);
				for (PlayerModel m : pDAO.getPlayerModel()) {
					pModel.add(new ComboItem(m.getId(), m.getFirstName() + " " + m.getLastName()));
				}
				playerList = new JComboBox<ComboItem>(pModel);
				panel.add(playerList);
			}
			{
				JLabel lblGame = new JLabel("Game");
				lblGame.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblGame);
			}
			{
				Vector<ComboItem> gModel = new Vector<ComboItem>();
				GameDAO gDAO = new GameDAO(ds);
				for (GameModel m : gDAO.getGameModel()) {
					gModel.add(new ComboItem(m.getId(), m.getGameTitle()));
				}
				gameList = new JComboBox<ComboItem>(gModel);
				panel.add(gameList);
			}
			{
				JLabel lblPlayingDate = new JLabel("Playing Date");
				lblPlayingDate.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblPlayingDate);
			}
			{
				UtilDateModel model = new UtilDateModel();
				Properties p = new Properties();
				p.put("text.today", "Today");
				p.put("text.month", "Month");
				p.put("text.year", "Year");
				JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
				datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
				datePicker.setBounds(220, 350, 120, 30);
				panel.add(datePicker);
			}
			{
				JLabel lblScore = new JLabel("Score");
				lblScore.setHorizontalAlignment(SwingConstants.RIGHT);
				panel.add(lblScore);
			}
			{
				NumberFormat format = NumberFormat.getInstance();
				NumberFormatter formatter = new NumberFormatter(format);
				formatter.setValueClass(Integer.class);
				formatter.setMinimum(0);
				formatter.setMaximum(Integer.MAX_VALUE);
				formatter.setAllowsInvalid(false);
				formatter.setCommitsOnValidEdit(true);
				scoreTextField = new JFormattedTextField(formatter);
				panel.add(scoreTextField);
				scoreTextField.setColumns(10);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Play");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(playBtnHandler);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(cancelBtnHandler);
			}
		}

	}

	ActionListener playBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			ScoreDAO dao = new ScoreDAO(ds);
			ScoreModel s = new ScoreModel();
			s.setPlayerId(((ComboItem) playerList.getSelectedItem()).getValue());
			s.setGameId(((ComboItem) gameList.getSelectedItem()).getValue());
			Calendar selectedValue = Calendar.getInstance();
			selectedValue.setTime((Date) datePicker.getModel().getValue());
			s.setPlayDate(selectedValue);
			s.setScore(Integer.parseInt(scoreTextField.getText()));
			dao.addScoreModel(s);
			pFrame.updateTable();
			dispose();
		}
	};

	ActionListener cancelBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	};
}
