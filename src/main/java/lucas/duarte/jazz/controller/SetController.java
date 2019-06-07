package lucas.duarte.jazz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lucas.duarte.jazz.model.bean.Set;
import lucas.duarte.jazz.model.service.SetService;

@RestController
@RequestMapping("/api")
public class SetController {

	@Autowired
	private SetService setServ;
	@Autowired
	private ExceptionController exceptController;
	@Autowired
	private REController responseController;

	@RequestMapping(value = "/setPartida/", method = RequestMethod.GET)
	public ResponseEntity<?> getSetBasedOnPartidaId(@RequestParam("partidaId") long partidaId,
			UriComponentsBuilder ucBuilder) {
		List<Set> setPartida = setServ.getSetsOfPartida(partidaId);

		if (setPartida.isEmpty()) {
			exceptController.errorHandling("Nao existem Sets cadastrados nessa partida", HttpStatus.NOT_FOUND);
		}
		// TODO: Implement Headers
		return responseController.responseController(setPartida, HttpStatus.OK);
	}

	@RequestMapping(value = "/set/", method = RequestMethod.POST)
	public ResponseEntity<?> salvarSet(@RequestBody Set set, UriComponentsBuilder ucBuilder) {
		boolean setSalvo = setServ.salvarSet(set);

		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode1 = mapper.createObjectNode();

		objectNode1.put("Status", setSalvo);

		if (setSalvo) {
			System.out.println("Set salvo e vinculado a partida com sucesso");
			return responseController.responseController(objectNode1, HttpStatus.OK);
		}

		return responseController.responseController(objectNode1, HttpStatus.BAD_GATEWAY);
	}

	@RequestMapping(value = "/set/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSet(@PathVariable("id") long id, @RequestBody Set set,
			UriComponentsBuilder ucBuilder) {
		try {
			boolean success = setServ.updateSet(set, id);

			if (success)
				return responseController.responseController(set, HttpStatus.OK);
			else
				return responseController.responseController("Set não alterado!", HttpStatus.NOT_FOUND);

		} catch (Exception e) {
			return exceptController.errorHandling(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
