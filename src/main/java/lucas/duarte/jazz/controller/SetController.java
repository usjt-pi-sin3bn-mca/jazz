package lucas.duarte.jazz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lucas.duarte.jazz.model.bean.Set;
import lucas.duarte.jazz.model.service.SetService;

@RestController
@RequestMapping("/api")
public class SetController {

	@Autowired
	private SetService setServ;

	// Get Sets vinculados a uma partida
	@RequestMapping(value = "/setPartida/", method = RequestMethod.GET)
	public ResponseEntity<?> getSetBasedOnPartidaId(@RequestParam("partidaId") long partidaId, UriComponentsBuilder ucBuilder) {
		return setServ.getSetsOfPartida(partidaId);
	}

	@RequestMapping(value = "/set/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarSet(@RequestBody Set set, UriComponentsBuilder ucBuilder) {
		// Realizar validacao se a partida possui 5 sets
		return setServ.salvarSet(set);
	}

	// Realizar Update do Set baseado no ID
	@RequestMapping(value = "/set/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSet(@PathVariable("id") long id, @RequestBody Set set,
			UriComponentsBuilder ucBuilder) {
		return setServ.updateSet(set, id);
	}

}
