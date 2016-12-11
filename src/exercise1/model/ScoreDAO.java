package exercise1.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import exercise1.DataSource;

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
			sql = "SELECT s.player_game_id, p.player_id, p.first_name, p.last_name, p.address, p.postal_code, p.province, p.phone_number, g.game_id, g.game_title, s.playing_date, s.score FROM playerandgame s left join player p on p.player_id=s.player_id left join game g on s.game_id=g.game_id where " 
			+ (-1 == playerId ? "1=1" : "p.player_id=" + playerId);
			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				ReportDisplayModel r = new ReportDisplayModel();
				r.setId(rs.getInt("player_game_id"));
				r.setGameId(rs.getInt("game_id"));
				r.setPlayerId(rs.getInt("player_id"));
				r.setPlayerName(rs.getString("first_name") + " " + rs.getString("last_name"));
				r.setGameName(rs.getString("game_title"));
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(rs.getTimestamp("playing_date").getTime());
				r.setPlayingDate(cal);
				r.setScore(rs.getInt("score"));
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
			Timestamp time = new Timestamp(s.getPlayDate().getTimeInMillis());
			sql = "INSERT INTO playerandgame (game_id, player_id, playing_date, score) VALUES ("
					+ s.getGameId() + ", " + s.getPlayerId() + ", '" + time.toString() + "', "
					+ s.getScore() + ")";
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
