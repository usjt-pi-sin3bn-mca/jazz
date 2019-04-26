package lucas.duarte.jazz.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lucas.duarte.jazz.model.bean.Partida;
import lucas.duarte.jazz.model.repository.PartidaRepository;

@Service
public class PartidaService {
	@Autowired
	private PartidaRepository partidaRepo;

	public boolean cadastrarPartida(Partida partida) {
		try {
			partidaRepo.save(partida);
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

}