package exercise1.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exercise1.DataSource;

public class PlayerDAO {
	private DataSource db;

	public PlayerDAO(DataSource source) {
		db = source;
	}

	public List<PlayerModel> getPlayerModel() {
		List<PlayerModel> result = new ArrayList<PlayerModel>();
		try {

			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "SELECT * FROM player";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PlayerModel p = new PlayerModel();
				p.setId(rs.getInt("player_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setAddress(rs.getString("address"));
				p.setPostCode(rs.getString("postal_code"));
				p.setProvince(rs.getString("province"));
				p.setPhoneNumber(rs.getString("phone_number"));
				result.add(p);
			}
			db.closeConnection(rs, stmt);
			return result;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return result;
	}
	
	public PlayerModel getPlayerModelById(int id) {
		PlayerModel result = null;
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "SELECT * FROM player where player_id=" + id;
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				result = new PlayerModel();
				result.setId(rs.getInt("player_id"));
				result.setFirstName(rs.getString("first_name"));
				result.setLastName(rs.getString("last_name"));
				result.setAddress(rs.getString("address"));
				result.setPostCode(rs.getString("postal_code"));
				result.setProvince(rs.getString("province"));
				result.setPhoneNumber(rs.getString("phone_number"));
			}
			db.closeConnection(rs, stmt);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return result;
	}

	public void addPlayerModel(PlayerModel p) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "INSERT INTO player (first_name, last_name, address, postal_code, province, phone_number) "
					+ "VALUES ('" + p.getFirstName() + "', '" + p.getLastName() + "', '" + p.getAddress() + "', '"
					+ p.getPostCode() + "', '" + p.getProvince() + "', '" + p.getPhoneNumber() + "')";
			stmt.execute(sql);
			db.closeConnection(null, stmt);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}

	public void updatePlayerModel(PlayerModel p) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "UPDATE player SET first_name='" + p.getFirstName() + "', last_name='" + p.getLastName()
					+ "', address='" + p.getAddress() + "', post_code='" + p.getPostCode() + "', province='"
					+ p.getProvince() + "', phone_number='" + p.getPhoneNumber() + "') WHERE player_id=" + p.getId();
			stmt.execute(sql);
			db.closeConnection(null, stmt);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}
