package exercise1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
			sql = "SELECT game_id, game_title FROM games";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				PlayerModel p = new PlayerModel();
				p.setId(rs.getInt("player_id"));
				p.setFirstName(rs.getString("first_name"));
				p.setLastName(rs.getString("last_name"));
				p.setAddress(rs.getString("address"));
				p.setPostCode(rs.getString("postal_code"));
				p.setProvince(rs.getString("province"));
				p.setPhoneNumber(rs.getInt("phone_number"));
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

	public void addPlayerModel(PlayerModel p) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "INSERT INTO player (player_id, first_name, last_name, address, postal_code, province, phone_number) "
					+ "VALUES (" + p.getId() + ", '" + p.getFirstName() + ", '" + p.getLastName() + ", '"
					+ p.getAddress() + ", '" + p.getPostCode() + ", '" + p.getProvince() + ", " + p.getPhoneNumber()
					+ ")";
			ResultSet rs = stmt.executeQuery(sql);
			db.closeConnection(rs, stmt);
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
			sql = "UPDATE player SET player_id=" + p.getId() + ", first_name='" + p.getFirstName()
					+ ", last_name='" + p.getLastName() + ", address='" + p.getAddress() + ", post_code='"
					+ p.getPostCode() + ", province='" + p.getProvince() + ", phone_number=" + p.getPhoneNumber() + ")";
			ResultSet rs = stmt.executeQuery(sql);
			db.closeConnection(rs, stmt);
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
	}
}
