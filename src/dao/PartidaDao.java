package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
import model.Partida;

public interface PartidaDao {
	
	public Partida createPartida(Partida partida) throws InsercaoException;
	public Partida updatePartida(Partida partida) throws AtualizacaoException;
	public List<Partida> listPartida() throws ConsultaException;
	public void deletePartida(Partida partida) throws DelecaoException;

}
