package exercise1.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import exercise1.DataSource;
import exercise1.model.GameDAO;
import exercise1.model.GameModel;

public class GameEditWin extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7092018321259717034L;
	private final JPanel contentPanel = new JPanel();
	private JTextField gameTextField;

	private DataSource ds;

	public GameEditWin(MainWin frame) {
		super(frame, "New Game", true);
		ds = DataSource.getInstance();

		setBounds(100, 100, 249, 111);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblGameLabel = new JLabel("Game Name");
			contentPanel.add(lblGameLabel);
		}
		{
			gameTextField = new JTextField();
			contentPanel.add(gameTextField);
			gameTextField.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Save");
				okButton.setActionCommand("Save");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				okButton.addActionListener(addGameBtnHandler);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(cancelBtnHandler);
			}
		}
	}

	ActionListener addGameBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			GameDAO dao = new GameDAO(ds);
			GameModel dto = new GameModel();
			dto.setGameTitle(gameTextField.getText());
			dao.addGameModel(dto);
			gameTextField.setText("");
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
