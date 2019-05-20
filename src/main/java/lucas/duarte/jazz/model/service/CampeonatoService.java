package lucas.duarte.jazz.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lucas.duarte.jazz.model.bean.Campeonato;
import lucas.duarte.jazz.model.bean.Partida;
import lucas.duarte.jazz.model.repository.CampeonatoRepository;

@Service
public class CampeonatoService {
	@Autowired
	private CampeonatoRepository campeonatoRepo;

	@Autowired
	private PartidaService partidaService;

	public ResponseEntity<Campeonato> salvarCampeonato(Campeonato camp) {
		try {
			campeonatoRepo.save(camp);
			return new ResponseEntity<Campeonato>(camp, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	public ResponseEntity<List<Campeonato>> getAllCampeonatos() {
		List<Campeonato> campeonatos = campeonatoRepo.findAll();

		if (campeonatos.isEmpty()) {
			// Return 404 beacause was not found
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Campeonato>>(campeonatos, HttpStatus.OK);
		}

	}

	public List<Campeonato> getAllCampeonatosNameId() {
		return campeonatoRepo.findAllByNameAndId();
	}

	public void processaEntidade(Campeonato campeonato) {
		List<Partida> partidas = campeonato.getPartidas();
		campeonato.setPartidas(null);
		campeonato = campeonatoRepo.save(campeonato);
		for (Partida partida : partidas) {
			partida.setCampeonato(campeonato);
			partidaService.cadastrarPartida(partida);
		}
	}
}