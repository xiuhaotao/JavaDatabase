package exercise1;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ScoreDAO {
	private DataSource db;

	public ScoreDAO(DataSource source) {
		db = source;
	}

	public List<ReportDisplayModel> getScoreModelByPlayer(int playerId) {
		List<ReportDisplayModel> result = new ArrayList<ReportDisplayModel>();
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "SELECT p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_title, s.playing_date, s.score FROM playandgame s left join player p on p.player_id=s.player_id left join game g on s.game_id=g.game_id where p.player_id=" + playerId;
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReportDisplayModel r = new ReportDisplayModel();
				// TODO
				result.add(r);
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

	public void addScoreModel(ScoreModel s) {
		try {
			Statement stmt = db.getConnection().createStatement();
			String sql;
			sql = "INSERT INTO playandgame (player_game_id, game_id, player_id, playing_date, score) VALUES ("
					+ s.getId() + ", " + s.getGameId() + ", " + s.getPlayerId() + ", '" + s.getPlayDate() + "', "
					+ s.getScore() + ")";
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
