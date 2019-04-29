package lucas.duarte.jazz.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	public Set updateSet(Set set, long id) {
		Set meuSet = setRepo.findById(id).orElse(null);
		System.out.println(set.isSetFinalizado());
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
			} else if (set.getGanhador() != "") {
				//TODO: Implementar logica de Time A e Time B baseado nas letras define qual time ganhou
				meuSet.setGanhador(set.getGanhador());
				setRepo.save(meuSet);
			} else if (set.isSetFinalizado()) {
				meuSet.setSetFinalizado(set.isSetFinalizado());
				setRepo.save(meuSet);
			}
			return meuSet;
		} else {
			return null;
		}
	}
	
	public List<Set> getSetsOfPartida(long partidaId) {
		return setRepo.findOneByPartida(partidaId);

	}
}
