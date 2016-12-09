package exercise1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GameDAO {
	private DataSource db;

	public GameDAO(DataSource source) {
		db = source;
	}

	public GameModel getGameModelById(int Id) {
		try {
			GameModel g = new GameModel();
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "SELECT id, first, last, age FROM games where id=?";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				g.setId(rs.getInt("id"));
				g.setGameTitle(rs.getString("gametitle"));
			}

			db.closeConnection(rs, stmt);
			return g;
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		}
		return null;
	}
	
	public void addGameModel(GameModel g) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "INSERT INTO id, first, last, age FROM games where id=?";
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				g.setId(rs.getInt("id"));
				g.setGameTitle(rs.getString("gametitle"));
			}

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
