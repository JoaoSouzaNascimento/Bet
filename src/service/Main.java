//package service;
//
//import dao.UsuarioDaoPostgreSQL;
//import exceptions.AtualizacaoException;
//import exceptions.ConsultaException;
//import exceptions.DelecaoException;
//import exceptions.InsercaoException;
//import model.Usuario;
//
//public class Main {
//
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//	
//		
//		UsuarioDaoPostgreSQL usuarioDao = new UsuarioDaoPostgreSQL();
//		
//		Usuario usuario = new Usuario("3cee0470-ad72-427d-86b6-91acebd2a7bc", "nomelegal", "nomelegal", "senhasupercriptografada", "email@foda.com", 2.50, false);
//		
////		try {
////			System.out.print(usuarioDao.createUsuario(usuario).toString());
////		} catch (InsercaoException e) {
////			e.printStackTrace();
////		}
//		
//		
////		try {
////			System.out.print(usuarioDao.updateUsuario(usuario).toString());
////		} catch (AtualizacaoException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
//		
//		try {
//			usuarioDao.getAllUsuarios().forEach(user -> System.out.println(user.toString()));
//		} catch (ConsultaException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			usuarioDao.deleteUsuario("3cee0470-ad72-427d-86b6-91acebd2a7bc");
//		} catch (DelecaoException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//}
