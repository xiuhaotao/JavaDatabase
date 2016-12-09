package exercise1;

import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.BoxLayout;
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
	private static final long serialVersionUID = -1346414054241053555L;
	// for database connection
	static final String DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	static final String DATABASE_URL = "jdbc:sqlserver://localhost:1433;databaseName=Games";
	static final String USERNAME = "user=lliu";
	static final String PASSWORD = "password=111111";

	private ReportDisplayModel reportDisplayModel;
	
	private JPanel pMain, pEast, pWestUp, pWestDown;
	private JTextArea queryArea; //need to be changed to JTable later
	private JLabel lblBlank, lblBlank2, lblGameId, lblGameTitle, lblPlayerId, lblFirstName, lblLastName, lblAddress, lblPostalCode, lblProvince, lblPhoneNum, lblQueryPId;
	private JButton btnAddGame, btnAddPlayer, btnUpdatePlayer, btnGetReport;
	private JTextField txtGameId, txtGameTitle,txtPlayerId, txtFirstName, txtLastName, txtAddress, txtPostalCode, txtProvince, txtPhoneNum, txtQueryPId;
	private Box westBox, eastBox;
	
	//constructor
	public GamesManageDemo() {
		super("Games Management");
		
		//set east west panels in frame
		pMain = new JPanel();
		pMain.setLayout(new BoxLayout(pMain,BoxLayout.X_AXIS));
		pEast = new JPanel();
		pWestUp = new JPanel();
		pWestDown = new JPanel();
		
		//pWestUp
		lblGameId = new JLabel("Game ID: ");
		lblGameTitle = new JLabel("Game Title: ");
		txtGameId = new JTextField(15);
		txtGameTitle = new JTextField(15);
		lblBlank = new JLabel();
		btnAddGame = new JButton("Add Game");
		pWestUp.setLayout(new GridLayout(3,2,1,1));
		pWestUp.add(lblGameId);
		pWestUp.add(txtGameId);
		pWestUp.add(lblGameTitle);
		pWestUp.add(txtGameTitle);
		pWestUp.add(lblBlank);
		pWestUp.add(btnAddGame);
		
		//pWestDown
		lblPlayerId = new JLabel("Player ID: ");
		lblFirstName = new JLabel("First Name: ");
		lblLastName = new JLabel("Last Name: ");
		lblAddress = new JLabel("Address: ");
		lblPostalCode = new JLabel("Postal Code: ");
		lblProvince = new JLabel("Province: ");
		lblPhoneNum = new JLabel("Phone Number: ");
		txtPlayerId = new JTextField(15);
		txtFirstName = new JTextField(15);
		txtLastName = new JTextField(15);
		txtAddress = new JTextField(15);
		txtPostalCode = new JTextField(15);
		txtProvince = new JTextField(15);
		txtPhoneNum = new JTextField(15);
		btnAddPlayer = new JButton("Add Player");
		btnUpdatePlayer = new JButton("Update Player");
		pWestDown.setLayout(new GridLayout(8, 2, 5, 5));
		pWestDown.add(lblPlayerId);
		pWestDown.add(txtPlayerId);
		pWestDown.add(lblFirstName);
		pWestDown.add(txtFirstName);
		pWestDown.add(lblLastName);
		pWestDown.add(txtLastName);
		pWestDown.add(lblAddress);
		pWestDown.add(txtAddress);
		pWestDown.add(lblPostalCode);
		pWestDown.add(txtPostalCode);
		pWestDown.add(lblProvince);
		pWestDown.add(txtProvince);
		pWestDown.add(lblPhoneNum);
		pWestDown.add(txtPhoneNum);
		pWestDown.add(btnAddPlayer);
		pWestDown.add(btnUpdatePlayer);
		
		westBox = Box.createVerticalBox();

		westBox.add(pWestUp);
		westBox.add(pWestDown);
		pMain.add(westBox);
		
		
		eastBox = Box.createVerticalBox();
		
		lblQueryPId = new JLabel("Enter the Player ID to query:");
		txtQueryPId = new JTextField(5);
		lblBlank2 = new JLabel();
		btnGetReport = new JButton("Get Report");
		
		pEast.setLayout(new GridLayout(3,2,5,5));
		pEast.add(lblQueryPId);
		pEast.add(txtQueryPId);
		pEast.add(lblBlank2);
		pEast.add(btnGetReport);
			
		//JTable resultTable = new JTable(reportDisplayModel);
		queryArea = new JTextArea();
		
		eastBox.add(pEast);
		
		//eastBox.add(new JScrollPane(resultTable));
		eastBox.add(queryArea);
		
		pMain.add(eastBox);
		
		add(pMain);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(500, 250); // set window size
		setVisible(true);
				
		
		
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new GamesManageDemo();
	}

}
