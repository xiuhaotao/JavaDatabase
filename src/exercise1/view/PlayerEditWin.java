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
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import exercise1.DataSource;
import exercise1.model.PlayerDAO;
import exercise1.model.PlayerModel;

public class PlayerEditWin extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 9184174091541448444L;
	private final JPanel contentPanel = new JPanel();
	private JTextField firstNameTextField, lastNameTextField, addressTextField, postalCodeTextField, provinceTextField,
			phoneNumberTextField;
	private JButton okButton;
	private MainWin pFrame;
	private DataSource ds;
	private int selectedId;

	public PlayerEditWin(MainWin frame, String actionName, int id) {
		super(frame, "Player", true);
		pFrame = frame;
		selectedId = id;
		ds = DataSource.getInstance();
		setBounds(100, 100, 240, 329);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT, 5, 5));
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblFirstName = new JLabel("First Name");
					panel_1.add(lblFirstName);
				}
				{
					firstNameTextField = new JTextField();
					panel_1.add(firstNameTextField);
					firstNameTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblLastName = new JLabel("Last Name");
					panel_1.add(lblLastName);
				}
				{
					lastNameTextField = new JTextField();
					panel_1.add(lastNameTextField);
					lastNameTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblAddress = new JLabel("Address");
					panel_1.add(lblAddress);
				}
				{
					addressTextField = new JTextField();
					panel_1.add(addressTextField);
					addressTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblPostalCode = new JLabel("Postal Code");
					panel_1.add(lblPostalCode);
				}
				{
					postalCodeTextField = new JTextField();
					panel_1.add(postalCodeTextField);
					postalCodeTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblProvince = new JLabel("Province");
					panel_1.add(lblProvince);
				}
				{
					provinceTextField = new JTextField();
					panel_1.add(provinceTextField);
					provinceTextField.setColumns(10);
				}
			}
			{
				JPanel panel_1 = new JPanel();
				panel.add(panel_1);
				{
					JLabel lblTelephone = new JLabel("Telephone");
					panel_1.add(lblTelephone);
				}
				{
					phoneNumberTextField = new JTextField();
					panel_1.add(phoneNumberTextField);
					phoneNumberTextField.setColumns(10);
				}
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Save");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
				cancelButton.addActionListener(cancelBtnHandler);
			}
		}
		if (actionName.equals("Update")) {
			PlayerDAO dao = new PlayerDAO(ds);
			PlayerModel dto = dao.getPlayerModelById(id);
			if(null != dto) {
				firstNameTextField.setText(dto.getFirstName());
				lastNameTextField.setText(dto.getLastName());
				addressTextField.setText(dto.getAddress());
				postalCodeTextField.setText(dto.getPostCode());
				provinceTextField.setText(dto.getProvince());
				phoneNumberTextField.setText(dto.getPhoneNumber());
			}
			okButton.addActionListener(updatePlayerBtnHandler);			
		} else {
			okButton.addActionListener(addPlayerBtnHandler);
		}
	}

	ActionListener addPlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerDAO dao = new PlayerDAO(ds);
			PlayerModel dto = new PlayerModel();
			dto.setFirstName(firstNameTextField.getText());
			dto.setLastName(lastNameTextField.getText());
			dto.setAddress(addressTextField.getText());
			dto.setPostCode(postalCodeTextField.getText());
			dto.setProvince(provinceTextField.getText());
			dto.setPhoneNumber(phoneNumberTextField.getText());
			dao.addPlayerModel(dto);
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			addressTextField.setText("");
			postalCodeTextField.setText("");
			provinceTextField.setText("");
			phoneNumberTextField.setText("");
			pFrame.updateComboBox();
			dispose();
		}
	};

	ActionListener updatePlayerBtnHandler = new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
			PlayerDAO dao = new PlayerDAO(ds);
			PlayerModel dto = new PlayerModel();
			dto.setId(selectedId);
			dto.setFirstName(firstNameTextField.getText());
			dto.setLastName(lastNameTextField.getText());
			dto.setAddress(addressTextField.getText());
			dto.setPostCode(postalCodeTextField.getText());
			dto.setProvince(provinceTextField.getText());
			dto.setPhoneNumber(phoneNumberTextField.getText());
			dao.updatePlayerModel(dto);
			firstNameTextField.setText("");
			lastNameTextField.setText("");
			addressTextField.setText("");
			postalCodeTextField.setText("");
			provinceTextField.setText("");
			phoneNumberTextField.setText("");
			pFrame.updateComboBox();
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
