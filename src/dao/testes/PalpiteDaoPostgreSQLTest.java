package dao.testes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Palpite;
import model.ResultadoPartida;

@ExtendWith(MockitoExtension.class)
class PalpiteDaoPostgreSQLTest {

    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement preparedStatement;

    @Mock
    private ResultSet resultSet;

    @InjectMocks
    private PalpiteDaoPostgreSQL palpiteDao;

    private Palpite palpite;

    @BeforeEach
    void setUp() throws SQLException {
        palpite = new Palpite(1, ResultadoPartida.HOME_WIN);
        when(connection.prepareStatement(anyString())).thenReturn(preparedStatement);
    }

    @Test
    void testCreatePalpite() throws SQLException, InsercaoException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        Palpite createdPalpite = palpiteDao.createPalpite(1, palpite);
        assertNotNull(createdPalpite);
        assertEquals(palpite, createdPalpite);
    }

    @Test
    void testUpdatePalpite() throws SQLException, AtualizacaoException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        Palpite updatedPalpite = palpiteDao.updatePalpite(1, palpite);
        assertNotNull(updatedPalpite);
        assertEquals(palpite, updatedPalpite);
    }

    @Test
    void testDeleteAposta() throws SQLException, DelecaoException {
        when(preparedStatement.executeUpdate()).thenReturn(1);
        assertDoesNotThrow(() -> palpiteDao.deleteAposta(1));
    }

    @Test
    void testGetPalpiteById() throws SQLException, ConsultaException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("MATCH_ID")).thenReturn(1);
        when(resultSet.getString("SHOT")).thenReturn("HOME_WIN");
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        Palpite foundPalpite = palpiteDao.getPalpiteById(1);
        assertNotNull(foundPalpite);
        assertEquals(1, foundPalpite.getPartidaId());
        assertEquals(ResultadoPartida.HOME_WIN, foundPalpite.getResultado());
    }

    @Test
    void testGetAllApostas() throws SQLException, ConsultaException {
        when(resultSet.next()).thenReturn(true).thenReturn(false);
        when(resultSet.getInt("MATCH_ID")).thenReturn(1);
        when(resultSet.getString("SHOT")).thenReturn("HOME_WIN");
        when(preparedStatement.executeQuery()).thenReturn(resultSet);

        List<Palpite> palpites = palpiteDao.getAllApostas();
        assertNotNull(palpites);
        assertEquals(1, palpites.size());
    }
}
