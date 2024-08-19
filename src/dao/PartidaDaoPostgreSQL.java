package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.SSLEngineResult.Status;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Partida;
import model.ResultadoPartida;
import model.StatusPartida;
import model.Time;
import model.Usuario;

public class PartidaDaoPostgreSQL implements PartidaDao {

	@Override
	public Partida createPartida(Partida partida) throws InsercaoException {
		String sql = "INSERT INTO \"MATCHES\" (\"ID\", \"TEAM_HOME\", \"TEAM_AWAY\", \"DATE\", \"TIME\", \"STATUS\", \"RESULT\", \"HOME_WIN_ODD\", \"AWAY_WIN_ODD\", \"DRAW_ODD\") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, partida.getId());
            ps.setInt(2, partida.getTeamHome().getId());
            ps.setInt(3, partida.getTeamAway().getId());
            ps.setObject(4, partida.getData());
            ps.setObject(5, partida.getTime());
            ps.setString(6, partida.getResultado().name());
            ps.setDouble(7, partida.getHomeWinOdd());
            ps.setDouble(8, partida.getAwayWinOdd());
            ps.setDouble(9, partida.getDrawOdd());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao criar partida", e);
        }

        return partida;
	}

	@Override
	public Partida updatePartida(Partida partida) throws AtualizacaoException {
		String sql = "UPDATE \"MATCHES\" SET \"TEAM_HOME\" = ?, \"TEAM_AWAY\" = ?, \"DATE\" = ?, \"TIME\" = ?, \"STATUS\" = ?, \"RESULT\" = ?, \"HOME_WIN_ODD\" = ?, \"AWAY_WIN_ODD\" = ?, \"DRAW_ODD\" = ? WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao(); 
        		PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, partida.getId());
            ps.setInt(2, partida.getTeamHome().getId());
            ps.setInt(3, partida.getTeamAway().getId());
            ps.setObject(4, partida.getData());
            ps.setObject(5, partida.getTime());
            ps.setString(6, partida.getResultado().name());
            ps.setDouble(7, partida.getHomeWinOdd());
            ps.setDouble(8, partida.getAwayWinOdd());
            ps.setDouble(9, partida.getDrawOdd());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new AtualizacaoException("Erro ao atualizar partida", e);
        }

        return partida;
	}

	@Override
	public List<Partida> listPartida() throws ConsultaException {
	    List<Partida> partidas = new ArrayList<>();
	    String sql = "SELECT " +
	                 "m.ID AS match_id, " +
	                 "m.DATE AS match_date, " +
	                 "m.TIME AS match_time, " +
	                 "m.STATUS AS match_status, " +
	                 "m.RESULT AS match_result, " +
	                 "m.HOME_WIN_ODD AS home_win_odd, " +
	                 "m.AWAY_WIN_ODD AS away_win_odd, " +
	                 "m.DRAW_ODD AS draw_odd, " +
	                 "th.ID AS team_home_id, " +
	                 "th.NAME AS team_home_name, " +
	                 "th.LOGO AS team_home_logo, " +
	                 "ta.ID AS team_away_id, " +
	                 "ta.NAME AS team_away_name " +
	                 "ta.LOGO AS team_home_logo, " +
	                 "FROM MATCHES m " +
	                 "JOIN TEAMS th ON m.TEAM_HOME = th.ID " +
	                 "JOIN TEAMS ta ON m.TEAM_AWAY = ta.ID";

	    try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
	         PreparedStatement ps = conn.prepareStatement(sql);
	         ResultSet rs = ps.executeQuery()) {

	        while (rs.next()) {
	            Time teamHome = new Time(
	                rs.getInt("team_home_id"),
	                rs.getString("team_home_name"),
	                rs.getString("team_home_logo")
	            );

	            Time teamAway = new Time(
	                rs.getInt("team_away_id"),
	                rs.getString("team_away_name"),
	                rs.getString("team_away_logo")
	            );

	            Partida partida = new Partida(
	                rs.getInt("match_id"),
	                teamHome,  
	                teamAway,  
	                rs.getDate("match_date").toLocalDate(),
	                rs.getTime("match_time").toLocalTime(),
	                StatusPartida.valueOf(rs.getString("match_status")),
	                ResultadoPartida.valueOf(rs.getString("match_result")),
	                rs.getFloat("home_win_odd"),
	                rs.getFloat("away_win_odd"),
	                rs.getFloat("draw_odd")
	            );
	            partidas.add(partida);
	        }
	        
	    } catch (SQLException e) {
	        throw new ConsultaException("Erro ao buscar todas as partidas", e);
	    }

	    return partidas;
	}

	@Override
	public void deletePartida(Partida partida) throws DelecaoException {
		String sql = "DELETE \"MATCHES\" WHERE \"ID\" = ?";
        
        try (Connection conn = ConexaoBdSingleton.getInstance().getConexao(); 
        		PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setObject(1, partida.getId());
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Nenhuma partida encontrado com o ID: " + partida.getId());
            }
        } catch (SQLException e) {
            throw new DelecaoException("Erro ao deletar partida", e);
        }
	}

}
