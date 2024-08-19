package dao;

import java.util.List;
import exceptions.InsercaoException;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import model.Time;

public interface TimeDao {
    public Time createTime(Time time) throws InsercaoException;
    public Time updateTime(Time time) throws AtualizacaoException;
    public List<Time> listTimes() throws ConsultaException;
    public void deleteTime(Time time) throws DelecaoException;
}