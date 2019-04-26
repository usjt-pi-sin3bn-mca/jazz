package lucas.duarte.jazz.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lucas.duarte.jazz.model.bean.Set;
import lucas.duarte.jazz.model.repository.SetRepository;

@Service
public class SetService {
	@Autowired
	private SetRepository setRepo;

	// Criar novo set
	public ResponseEntity<Set> salvarSet(Set set) {
		try {
			setRepo.save(set);
			return new ResponseEntity<Set>(set, HttpStatus.OK);
		} catch (Exception e) {
			System.out.println(e);
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		} // TODO: handle exception
	}

	public ResponseEntity<Set> updateSet(Set set, long id) {
		Set meuSet = setRepo.findById(id).orElse(null);
		//printando o Set para verificar os atributos
		if (meuSet != null) {
			if (set.getPontoA() != 0) {
				meuSet.setPontoA(set.getPontoA());
				setRepo.save(meuSet);
			} else if (set.getPontoB() != 0) {
				meuSet.setPontoB(set.getPontoB());
				setRepo.save(meuSet);
			} else if (set.getTempoA() != 0) {
				meuSet.setTempoA(set.getTempoA());
				setRepo.save(meuSet);
			} else if (set.getTempoB() != 0) {
				meuSet.setPontoB(set.getTempoB());
				setRepo.save(meuSet);
			} else if (set.getGanhador() != null) {
				meuSet.setGanhador(set.getGanhador());
				setRepo.save(meuSet);
			} else if (set.isSetFinalizado()) {
				meuSet.setSetFinalizado(set.isSetFinalizado());
				setRepo.save(meuSet);
			}
			return new ResponseEntity<Set>(meuSet, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<List<Set>> getSetsOfPartida(long partidaId) {
		List<Set> meuSets = setRepo.findOneByPartida(partidaId);
		if (meuSets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Set>>(meuSets, HttpStatus.OK);
		}
	}
}
