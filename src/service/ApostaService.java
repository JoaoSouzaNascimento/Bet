package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import api.dto.FixtureResponse;
import dao.ApostaDao;
import dao.ApostaDaoPostgreSQL;
import dao.PalpiteDao;
import dao.PalpiteDaoPostgreSQL;
import exceptions.AtualizacaoException;
import exceptions.ConsultaException;
import exceptions.DelecaoException;
import exceptions.InsercaoException;
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

	public void validarApostaGanha(Aposta aposta, String league, String timezone) throws Exception {
		try {
			if (aposta.getPalpites() == null) {
				aposta.setPalpites(palpiteDao.getTodosPalpitesDeUmaAposta(aposta.getId()));
			}

			List<FixtureResponse> fixtures = footballApiService.getFixtures(league,
					String.valueOf(aposta.getDate().getYear()), aposta.getDate().toString(), timezone);
			List<FixtureData> fixtureDataList = getFinishedFixtures(fixtures);

			boolean apostaGanha = isApostaGanha(aposta, fixtureDataList);

			aposta.setStatus(apostaGanha);
			apostaDao.updateAposta(aposta);
		} catch (ConsultaException | AtualizacaoException e) {
			e.printStackTrace();
		}
	}

//	public void validarApostaGanha(Aposta aposta, String league, String timezone) throws Exception {
//		try {
//			if (aposta.getPalpites() == null) {
//				aposta.setPalpites(palpiteDao.getTodosPalpitesDeUmaAposta(aposta.getId()));
//			}
//
//			List<FixtureResponse> fixtures = footballApiService.getFixtures(league,
//					String.valueOf(aposta.getDate().getYear()), aposta.getDate().toString(), timezone);
//			List<FixtureData> fixtureDataList = new ArrayList<>();
//
//			for (FixtureResponse fixture : fixtures) {
//				FixtureData fixtureData = partidaService.getFixtureData(fixture);
//				if (fixtureData.getStatus().equals("Match Finished")) {
//					fixtureDataList.add(fixtureData);
//				} else {
//					throw new RuntimeException("Ainda existem partidas não finalizadas");
//				}
//			}
//
//			boolean apostaGanha = true;
//
//			for (Palpite palpite : aposta.getPalpites()) {
//
//				Optional<FixtureData> optionalFixtureData = fixtureDataList.stream()
//						.filter(fixtureData -> fixtureData.getFixtureId().equals(palpite.getPartidaId())).findFirst();
//
//				if (optionalFixtureData.isPresent()) {
//					FixtureData fixtureData = optionalFixtureData.get();
//					if (palpite.getResultado().equals(ResultadoPartida.HOME_WIN) && !fixtureData.isCasaGanhou()
//							|| palpite.getResultado().equals(ResultadoPartida.AWAY_WIN) && !fixtureData.isForaGanhou()
//							|| palpite.getResultado().equals(ResultadoPartida.DRAW) && !fixtureData.isCasaGanhou()
//									&& !fixtureData.isForaGanhou()) {
//						apostaGanha = false;
//						break;
//					}
//				}
//			}
//			aposta.setStatus(apostaGanha);
//			apostaDao.updateAposta(aposta);
//		} catch (ConsultaException |
//
//				AtualizacaoException e) {
//			e.printStackTrace();
//		}
//	}

	// Criar nova aposta e persistir no banco de dados
//    public Aposta criarAposta(Usuario usuario, float valorApostado, List<Palpite> palpites) {
//        Aposta aposta = null;
//    	try {
//            aposta = apostaDao.createAposta(usuario.getId(), new Aposta(valorApostado, null));
//            //TODO implementar
//            //aposta.setPalpites(palpiteDao.createListaDePalpites(aposta.getId(), palpites));
//            aposta.setPalpites(palpites);
//        } catch (InsercaoException e) {
//            e.printStackTrace();
//        }
//    }

	// Recupera uma aposta específica pelo ID
	public Aposta getApostaById(int apostaId) {
		try {
			return apostaDao.getApostaById(apostaId);
		} catch (ConsultaException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Recupera todas as apostas de um usuário
	public List<Aposta> getTodasApostas(Usuario usuario) {
		try {
			return apostaDao.getTodasApostasPorUsuarioId(usuario.getId());
		} catch (ConsultaException e) {
			e.printStackTrace();
			return null;
		}
	}

	// Atualiza o valor apostado
	public void atualizarValorApostado(Aposta aposta, BigDecimal novoValorApostado) {
		aposta.setAmount(novoValorApostado);
		try {
			apostaDao.updateAposta(aposta);
		} catch (AtualizacaoException e) {
			e.printStackTrace();
		}
	}

	// Exclui uma aposta específica
	public void excluirAposta(Aposta aposta) {
		try {
			apostaDao.deleteAposta(aposta.getId());
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
	}

	// Adiciona um novo palpite à aposta e persiste no banco de dados
	public void addPalpite(Aposta aposta, Palpite palpite) {
		try {
			palpiteDao.createPalpite(aposta.getId(), palpite);
			aposta.addPalpite(palpite);
		} catch (InsercaoException e) {
			e.printStackTrace();
		}
	}

	public void getPalpitesDeUmaAposta(Aposta aposta) {
		try {
			aposta.setPalpites(palpiteDao.getTodosPalpitesDeUmaAposta(aposta.getId()));

		} catch (ConsultaException e) {
			e.printStackTrace();
		}
	}

	// Exclui um palpite específico da aposta
	public void excluirPalpite(Aposta aposta, int palpiteId) {
		try {
			palpiteDao.deletePalpite(palpiteId);
			aposta.removePalpiteById(palpiteId);
		} catch (DelecaoException e) {
			e.printStackTrace();
		}
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

	private boolean isApostaGanha(Aposta aposta, List<FixtureData> fixtureDataList) {
		for (Palpite palpite : aposta.getPalpites()) {
			Optional<FixtureData> optionalFixtureData = fixtureDataList.stream()
					.filter(fixtureData -> fixtureData.getFixtureId().equals(palpite.getPartidaId())).findFirst();

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

}