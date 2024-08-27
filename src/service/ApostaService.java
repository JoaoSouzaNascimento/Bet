package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import api.dto.FixtureResponse;
import dao.ApostaDao;
import dao.PalpiteDao;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import model.Aposta;
import model.Palpite;
import model.ResultadoPartida;
import model.Usuario;
import service.util.FixtureData;

public class ApostaService {
	private final ApostaDao apostaDao;
	private final PalpiteDao palpiteDao;
	private final FootballApiService footballApiService;
	private final PartidaService partidaService;

	public ApostaService(ApostaDao apostaDao, PalpiteDao palpiteDao, FootballApiService footballApiService,
			PartidaService partidaService) {
		this.apostaDao = apostaDao;
		this.palpiteDao = palpiteDao;
		this.footballApiService = footballApiService;
		this.partidaService = partidaService;
	}

	public boolean validarAposta(Aposta aposta, String league, String timezone) throws Exception {
		try {
			List<Palpite> palpites = palpiteDao.getTodosPalpitesDeUmaAposta(aposta.getId());

			List<FixtureResponse> fixtures = footballApiService.getFixtures(league,
					String.valueOf(aposta.getDate().getYear()), aposta.getDate().toString(), timezone);
			List<FixtureData> fixtureDataList = getFinishedFixtures(fixtures);

			boolean apostaGanha = isApostaGanha(palpites, fixtureDataList);

			aposta.setStatus(apostaGanha);
			apostaDao.updateAposta(aposta);
			return apostaGanha;
		} catch (ConsultaException | AtualizacaoException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Aposta getApostaById(int apostaId) {
		try {
			return apostaDao.getApostaById(apostaId);
		} catch (ConsultaException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Aposta> getTodasApostas(Usuario usuario) {
		try {
			return apostaDao.getTodasApostasPorUsuarioId(usuario.getId());
		} catch (ConsultaException e) {
			e.printStackTrace();
			return null;
		}
	}

//	public void atualizarValorApostado(Aposta aposta, BigDecimal novoValorApostado) {
//		
//		isApostaAberta(aposta);
//		aposta.setAmount(novoValorApostado);
//		try {
//			apostaDao.updateAposta(aposta);
//		} catch (AtualizacaoException e) {
//			e.printStackTrace();
//		}
//	}

	public void criarAposta(Aposta aposta, List<Palpite> palpites) {
		try {
			apostaDao.createAposta(aposta);
			palpites.forEach(palpite -> palpite.setApostaId(aposta.getId()));
			palpiteDao.createListaDePalpites(palpites);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	public void excluirAposta(Aposta aposta) throws Exception {
		isApostaAberta(aposta);

		try {
			apostaDao.deleteAposta(aposta.getId());
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}

	public List<Palpite> getPalpitesDeUmaAposta(Aposta aposta) {
		try {
			System.out.println("ID da Aposta: " + aposta.getId());
			return palpiteDao.getTodosPalpitesDeUmaAposta(aposta.getId());
			
		} catch (ConsultaException e) {
			e.printStackTrace();
		}

		return null;
	}

	private List<FixtureData> getFinishedFixtures(List<FixtureResponse> fixtures) throws Exception {
		List<FixtureData> fixtureDataList = new ArrayList<>();

		for (FixtureResponse fixture : fixtures) {
			FixtureData fixtureData = partidaService.getFixtureData(fixture);
			if (fixtureData.getStatus().equals("Match Finished")) {
				fixtureDataList.add(fixtureData);
			} else {
				throw new RuntimeException("Ainda existem partidas não finalizadas");
			}
		}

		return fixtureDataList;
	}

	private boolean isApostaGanha(List<Palpite> palpites, List<FixtureData> fixtureDataList) {
		for (Palpite palpite : palpites) {
			Optional<FixtureData> optionalFixtureData = fixtureDataList.stream()
					.filter(fixtureData -> fixtureData.getFixtureId().equals(String.valueOf(palpite.getPartidaId())))
					.findFirst();

			if (optionalFixtureData.isPresent()) {
				FixtureData fixtureData = optionalFixtureData.get();
				if (palpite.getResultado().equals(ResultadoPartida.HOME_WIN) && !fixtureData.isCasaGanhou()
						|| palpite.getResultado().equals(ResultadoPartida.AWAY_WIN) && !fixtureData.isForaGanhou()
						|| palpite.getResultado().equals(ResultadoPartida.DRAW) && !fixtureData.isCasaGanhou()
								&& !fixtureData.isForaGanhou()) {
					return false;
				}
			}
		}

		return true;
	}

	private boolean isApostaAberta(Aposta aposta) throws Exception {
		List<Palpite> palpites = getPalpitesDeUmaAposta(aposta);
		boolean isApostaAberta = true;
		for (Palpite palpite : palpites) {
			FixtureData fixtureData = partidaService.getFixtureDataById(String.valueOf(palpite.getPartidaId()));
			if (!fixtureData.getStatus().equals("Not Started")) {
				return isApostaAberta = false;
			}
		}
		return isApostaAberta;
	}

}