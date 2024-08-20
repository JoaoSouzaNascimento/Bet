package dao;

import java.math.BigDecimal;
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
import model.Aposta;

public class ApostaDaoPostgreSQL implements ApostaDao{

	    @Override
	    public Aposta createAposta(Aposta aposta) throws InsercaoException{
	        String sql = "INSERT INTO \"APOSTAS\" (\"ID\", \"AMOUNT\", \"STATUS\") VALUES (?, ?, ?)";
	        
			 try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
   		          PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, aposta.getId());
	            ps.setBigDecimal(2, aposta.getAmount());
	            ps.setBoolean(3, aposta.isStatus());
	            ps.executeUpdate();

	        } catch (SQLException e) {
	            throw new InsercaoException("Erro ao criar aposta", e);
	        }
			 return aposta;
	    }

	    @Override
	    public Aposta updateAposta(Aposta aposta) throws AtualizacaoException{
	        String sql = "UPDATE \"APOSTAS\" SET \"AMOUNT\" = ?, \"STATUS\" = ? WHERE \"ID\" = ?";
	        
			try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
  		        PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setBigDecimal(1, aposta.getAmount());
	            ps.setBoolean(2, aposta.isStatus());
	            ps.setInt(3, aposta.getId());
	            
	            ps.executeUpdate();
	        } catch (SQLException e) {
	        	throw new AtualizacaoException("Erro ao atualizar aposta", e);
	        }
			return aposta;
			
	    }

	    @Override
	    public void deleteAposta(int id) throws DelecaoException{
	        String sql = "DELETE FROM \"APOSTAS\" WHERE \"ID\" = ?";

			try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
	 	        PreparedStatement ps = conn.prepareStatement(sql)) {
					            
	            ps.setInt(1, id);
	            ps.executeUpdate();
	        } catch (SQLException e) {
	        	throw new DelecaoException("Erro ao deletar aposta", e);
	        }
	    }

	    @Override
	    public Aposta getApostaById(int id) throws ConsultaException{
	        String sql = "SELECT * FROM \"APOSTAS\" WHERE \"ID\" = ?";
	        Aposta aposta = null;
	            
			try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
            	PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setInt(1, id);
	            ResultSet rs = ps.executeQuery();
	            
	            if (rs.next()) {
	                BigDecimal amount = rs.getBigDecimal("amount");
	                boolean status = rs.getBoolean("status");
	                aposta = new Aposta(id, amount, null,status);
	            }
	        } catch (SQLException e) {
	        	throw new ConsultaException("Erro ao buscar aposta por id", e);
	        }
	        
	        return aposta;
	    }

	    @Override
	    public List<Aposta> getAllApostas() throws ConsultaException{
	        String sql = "SELECT * FROM \"APOSTAS\"";
	        List<Aposta> apostas = new ArrayList<>();
	       
			try (Connection conn = ConexaoBdSingleton.getInstance().getConexao();
            	PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery(sql)) {

	            while (rs.next()) {
	                int id = rs.getInt("id");
	                BigDecimal amount = rs.getBigDecimal("amount");
	                boolean status = rs.getBoolean("status");
	                
	                Aposta aposta = new Aposta(id, amount, null,status);
	                apostas.add(aposta);
	            }
	        } catch (SQLException e) {
	        	throw new ConsultaException("Erro ao listar todas as apostas", e);
	        }
	        
	        return apostas;
	    }
}
