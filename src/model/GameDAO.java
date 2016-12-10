package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import exercise1.DataSource;

public class GameDAO {
	private DataSource db;

	public GameDAO(DataSource source) {
		db = source;
	}

	public List<GameModel> getGameModel() {
		List<GameModel> result = new ArrayList<GameModel>();
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "SELECT game_id, game_title FROM game";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				GameModel g = new GameModel();
				g.setId(rs.getInt("game_id"));
				g.setGameTitle(rs.getString("game_title"));
				result.add(g);
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

	public void addGameModel(GameModel g) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "INSERT INTO game (game_title) VALUES ('" + g.getGameTitle() + "')";
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
