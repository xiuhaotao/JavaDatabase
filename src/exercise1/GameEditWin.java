package exercise1;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GameEditWin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092018321259717034L;
	private JLabel lblGameId, lblGameTitle;
	private JTextField txtGameId, txtGameTitle;
	private JButton btnAddGame;
	private DataSource ds;

	public GameEditWin(GamesManageDemo frame) {
		super(frame, "Game", true);
		ds = DataSource.getInstance();
		JPanel mainWin = new JPanel();
		mainWin.setLayout(new GridLayout(3, 2, 1, 1));
		lblGameId = new JLabel("Game ID: ");
		lblGameTitle = new JLabel("Game Title: ");
		txtGameId = new JTextField(15);
		txtGameTitle = new JTextField(15);
		btnAddGame = new JButton("Add Game");
		mainWin.add(lblGameId);
		mainWin.add(txtGameId);
		mainWin.add(lblGameTitle);
		mainWin.add(txtGameTitle);
		mainWin.add(btnAddGame);
		add(mainWin);
		setBounds(200, 200, 300, 100);
		setResizable(false);
		
		btnAddGame.addActionListener(addGameBtnHandler);
	}

	ActionListener addGameBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameDAO dao = new GameDAO(ds);
			GameModel dto = new GameModel();
			dto.setId(Integer.parseInt(txtGameId.getText()));
			dto.setGameTitle(txtGameTitle.getText());
			dao.addGameModel(dto);
			txtGameId.setText("");
			txtGameTitle.setText("");
		}
	};
}
