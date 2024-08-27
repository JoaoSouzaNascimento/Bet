
package dao;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.InsercaoException;
import model.AdminLog;

public interface LogDao {
    void createLog(AdminLog log) throws InsercaoException;
    List<AdminLog> getTodosLogsDeUmAdmin(UUID adminId) throws ConsultaException;
}
