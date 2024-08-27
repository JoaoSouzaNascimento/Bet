package config;

import service.ApostaService;
import service.FootballApiService;
import service.PartidaService;
import service.TransactionService;
import service.UsuarioService;

public class AppContext {

	private static UsuarioService usuarioService;
	private static TransactionService transactionService;
	private static ApostaService apostaService;
	private static FootballApiService footballApiService;

	public static void setUsuarioService(UsuarioService service) {
		usuarioService = service;
	}

	public static final String API_KEY = "0af6c4cae9850dba88765797e79d9413";
	public static final String API_HOST = "v3.football.api-sports.io";

	public static TransactionService getTransactionService() {
		return transactionService;
	}

	public static void setTransactionService(TransactionService transactionService) {
		AppContext.transactionService = transactionService;
	}

	public static UsuarioService getUsuarioService() {
		return usuarioService;
	}
	
	public static ApostaService getApostaService() {
		return apostaService;
	}
	
	public static void setApostaService(ApostaService service) {
		apostaService = service;
	}
	
	public static FootballApiService getFootballApiService() {
		return footballApiService;
	}
	
	public static void setFootballApiService(FootballApiService service) {
		footballApiService = service;
	}
}
