package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Palpite;
import model.ResultadoPartida;

public class PalpiteDaoPostgreSQL implements PalpiteDao {

    private static final String INSERT_PALPITE = "INSERT INTO BETS_MATCHES (BET_ID, MATCH_ID, SHOT) VALUES (?, ?, ?)";
    private static final String UPDATE_PALPITE = "UPDATE BETS_MATCHES SET SHOT = ? WHERE BET_ID = ? AND MATCH_ID = ?";
    private static final String DELETE_PALPITE = "DELETE FROM BETS_MATCHES WHERE BET_ID = ?";
    private static final String SELECT_PALPITE_BY_ID = "SELECT BET_ID, MATCH_ID, SHOT FROM BETS_MATCHES WHERE MATCH_ID = ?";
    private static final String SELECT_ALL_PALPITES = "SELECT BET_ID, MATCH_ID, SHOT FROM BETS_MATCHES";

    private Connection getConnection() throws SQLException {
        return ConexaoBdSingleton.getInstance().getConexao();
    }

    @Override
    public Palpite createPalpite(int aposta, Palpite palpite) throws InsercaoException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(INSERT_PALPITE)) {

            stmt.setInt(1, aposta);
            stmt.setInt(2, palpite.getPartidaId());
            stmt.setString(3, palpite.getResultado().name());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return palpite;
            } else {
                throw new InsercaoException("Falha ao inserir palpite.");
            }
        } catch (SQLException e) {
            throw new InsercaoException("Erro ao inserir palpite: " + e.getMessage(), e);
        }
    }

    @Override
    public Palpite updatePalpite(int aposta, Palpite palpite) throws AtualizacaoException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(UPDATE_PALPITE)) {

            stmt.setString(1, palpite.getResultado().name());
            stmt.setInt(2, aposta);
            stmt.setInt(3, palpite.getPartidaId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                return palpite;
            } else {
                throw new AtualizacaoException("Falha ao atualizar palpite.");
            }
        } catch (SQLException e) {
            throw new AtualizacaoException("Erro ao atualizar palpite: " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteAposta(int id) throws DelecaoException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(DELETE_PALPITE)) {

            stmt.setInt(1, id);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected == 0) {
                throw new DelecaoException("Falha ao deletar aposta.");
            }
        } catch (SQLException e) {
            throw new DelecaoException("Erro ao deletar aposta: " + e.getMessage(), e);
        }
    }

    @Override
    public Palpite getPalpiteById(int partidaId) throws ConsultaException {
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_PALPITE_BY_ID)) {

            stmt.setInt(1, partidaId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Palpite(
                        rs.getInt("MATCH_ID"),
                        ResultadoPartida.valueOf(rs.getString("SHOT"))
                    );
                } else {
                    throw new ConsultaException("Palpite não encontrado para a partida: " + partidaId);
                }
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao consultar palpite: " + e.getMessage(), e);
        }
    }

    @Override
    public List<Palpite> getAllApostas() throws ConsultaException {
        List<Palpite> palpites = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement stmt = connection.prepareStatement(SELECT_ALL_PALPITES);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Palpite palpite = new Palpite(
                    rs.getInt("MATCH_ID"),
                    ResultadoPartida.valueOf(rs.getString("SHOT"))
                );
                palpites.add(palpite);
            }
        } catch (SQLException e) {
            throw new ConsultaException("Erro ao consultar todas as apostas: " + e.getMessage(), e);
        }
        return palpites;
    }
}
