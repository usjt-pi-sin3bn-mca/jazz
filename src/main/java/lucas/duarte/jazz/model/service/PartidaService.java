package lucas.duarte.jazz.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucas.duarte.jazz.model.bean.Partida;
import lucas.duarte.jazz.model.bean.Set;
import lucas.duarte.jazz.model.repository.PartidaRepository;

@Service
public class PartidaService {
	@Autowired
	private PartidaRepository partidaRepo;
	@Autowired
	private SetService setService;

	public boolean cadastrarPartida(Partida partida, Set set) {
		try {
			partidaRepo.save(partida);
			setService.salvarSet(set);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

	public Partida getpartidaById(Long id) {
		System.out.println("Pegar partida pelo ID");
		return partidaRepo.findById(id).orElse(null);
	}

	public List<Partida> getAllPartidas() {
		List<Partida> partidas = partidaRepo.findAll();
		return partidas;
	}

	public List<Partida> getPartidaEmAndamento() {
		return partidaRepo.findPartidasEmAndamento();
	}

	public boolean iniciarPartida(Long id) {
		// Partida antes da alteracao
		Partida partidaOld = partidaRepo.findById(id).orElse(null);
		if (partidaOld != null) {
			if (partidaOld.isPartidaIniciada()) {
				return false;
			} else {
				partidaOld.setPartidaIniciada(true);
				partidaRepo.save(partidaOld);
				return true;
			}
		} else {
			System.out.println("Partida nao existente");
			return false;
		}

	}

	public boolean finalizarPartida(Long id) {
		// Partida antes da alteracao
		Partida partidaOld = partidaRepo.findById(id).orElse(null);
		if (partidaOld.isPartidaIniciada()) {
			if (partidaOld != null) {
				if (partidaOld.isPartidaFinalizada()) {
					return false;
				} else {
					partidaOld.setPartidaFinalizada(true);
					partidaRepo.save(partidaOld);
					return true;
				}
			}
		}
		throw new RuntimeException("Partida nao iniciada");
	}

	// Retorna as partidas anteriores
	public List<Partida> getPartidasAnteriores() {

		return partidaRepo.getAnteriores();
	}
	
	// Retorna as partidas posteriores
		public List<Partida> getPartidasPosteriores() {

			return partidaRepo.getPosteriores();
		}

}