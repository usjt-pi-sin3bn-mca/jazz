package lucas.duarte.jazz.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lucas.duarte.jazz.model.bean.Administrador;
import lucas.duarte.jazz.model.bean.Partida;
import lucas.duarte.jazz.model.bean.Set;
import lucas.duarte.jazz.model.repository.SetRepository;

@Service
public class SetService {
	@Autowired
	private SetRepository setRepo;

	public boolean salvarSet(Set set) {
		List<Set> listaSets = setRepo.findOneByPartida(set.getPartida().getId());

		if (listaSets.size() >= 5) {
			return false;
		}

		setRepo.save(set);
		return true;
	}

	public boolean updateSet(Set setAtualizado, long id) {
		System.out.println(setAtualizado);

		Set meuSet = setRepo.findById(id).orElse(null);

		if (meuSet != null) {
			setAtualizado.setId(id);
			Partida partida = meuSet.getPartida();
			setAtualizado.setPartida(partida);

			if (setAtualizado.isSetFinalizado()) {
				if (setAtualizado.getPontoA() > setAtualizado.getPontoB())
					setAtualizado.setGanhador(partida.getTimeA());
				else
					setAtualizado.setGanhador(partida.getTimeB());
			}

			setRepo.save(setAtualizado);
			return true;
		}

		return false;
	}

	public List<Set> getSetsOfPartida(long partidaId) {
		return setRepo.findOneByPartida(partidaId);
	}

	public boolean addTempo(Set set, long id) {
		Set setUpdate = setRepo.findById(id).orElse(null);
		if (setUpdate != null) {
			if (set.getTempoA() != 0 && set.getTempoA() <= 2) {
				setUpdate.setTempoA(set.getTempoA());
			} 
			if (set.getTempoB() != 0 && set.getTempoB() <= 2) {
				setUpdate.setTempoB(set.getTempoB());
			}
			setRepo.save(setUpdate);
			return true;
		}
		return false;
	}

}
