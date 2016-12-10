package exercise1;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerEditWin extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9184174091541448444L;
	private JLabel lblFirstName, lblLastName, lblAddress,
	lblPostalCode, lblProvince, lblPhoneNum;
	private JTextField txtFirstName, txtLastName, txtAddress, txtPostalCode,
	txtProvince, txtPhoneNum;
	private JButton btnAddPlayer, btnUpdatePlayer;
	private DataSource ds;

	public PlayerEditWin(GamesManageDemo frame) {
		super(frame, "Player", true);
		ds = DataSource.getInstance();
		JPanel mainWin = new JPanel();
		mainWin.setLayout(new GridLayout(7, 2, 1, 1));
		lblFirstName = new JLabel("First Name: ");
		lblLastName = new JLabel("Last Name: ");
		lblAddress = new JLabel("Address: ");
		lblPostalCode = new JLabel("Postal Code: ");
		lblProvince = new JLabel("Province: ");
		lblPhoneNum = new JLabel("Phone Number: ");
		txtFirstName = new JTextField(15);
		txtLastName = new JTextField(15);
		txtAddress = new JTextField(15);
		txtPostalCode = new JTextField(15);
		txtProvince = new JTextField(15);
		txtPhoneNum = new JTextField(15);
		btnAddPlayer = new JButton("Add Player");
		btnUpdatePlayer = new JButton("Update Player");
		mainWin.add(lblFirstName);
		mainWin.add(txtFirstName);
		mainWin.add(lblLastName);
		mainWin.add(txtLastName);
		mainWin.add(lblAddress);
		mainWin.add(txtAddress);
		mainWin.add(lblPostalCode);
		mainWin.add(txtPostalCode);
		mainWin.add(lblProvince);
		mainWin.add(txtProvince);
		mainWin.add(lblPhoneNum);
		mainWin.add(txtPhoneNum);
		mainWin.add(btnAddPlayer);
		mainWin.add(btnUpdatePlayer);
		add(mainWin);
		setBounds(200, 200, 300, 300);
		setResizable(false);
		
		btnAddPlayer.addActionListener(addPlayerBtnHandler);
		btnUpdatePlayer.addActionListener(updatePlayerBtnHandler);
	}
	
	ActionListener addPlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerDAO dao = new PlayerDAO(ds);
			PlayerModel dto = new PlayerModel();
			dto.setFirstName(txtFirstName.getText());
			dto.setLastName(txtLastName.getText());
			dto.setAddress(txtAddress.getText());
			dto.setPostCode(txtPostalCode.getText());
			dto.setProvince(txtProvince.getText());
			dto.setPhoneNumber(txtPhoneNum.getText());
			dao.addPlayerModel(dto);
			txtFirstName.setText("");
			txtLastName.setText("");
			txtAddress.setText("");
			txtPostalCode.setText("");
			txtProvince.setText("");
			txtPhoneNum.setText("");
			dispose();
		}
	};

	ActionListener updatePlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerDAO dao = new PlayerDAO(ds);
			PlayerModel dto = new PlayerModel();
			dto.setFirstName(txtFirstName.getText());
			dto.setLastName(txtLastName.getText());
			dto.setAddress(txtAddress.getText());
			dto.setPostCode(txtPostalCode.getText());
			dto.setProvince(txtProvince.getText());
			dto.setPhoneNumber(txtPhoneNum.getText());
			dao.addPlayerModel(dto);
			txtFirstName.setText("");
			txtLastName.setText("");
			txtAddress.setText("");
			txtPostalCode.setText("");
			txtProvince.setText("");
			txtPhoneNum.setText("");
			dispose();
		}
	};
}
