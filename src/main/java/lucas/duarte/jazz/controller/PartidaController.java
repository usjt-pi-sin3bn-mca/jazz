package lucas.duarte.jazz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lucas.duarte.jazz.model.bean.Partida;
import lucas.duarte.jazz.model.service.PartidaService;

@RestController
@RequestMapping("/api")
public class PartidaController {
	@Autowired
	private PartidaService partidaServ;
	@Autowired
	private ExceptionController exceptController;
	@Autowired
	private REController responseController;

	@RequestMapping(value = "/partidas/", method = RequestMethod.GET)
	public ResponseEntity<?> listAllpartidas() {
		List<Partida> partidas = partidaServ.getAllPartidas();

		if (partidas.isEmpty()) {
			return exceptController.errorHandling("Nao existem partidas cadastradas", HttpStatus.NOT_FOUND);
		}

		return responseController.responseController(partidas, HttpStatus.OK);
	}

	@RequestMapping(value = "/partidas/", method = RequestMethod.POST)
	public ResponseEntity<?> createPartida(@RequestBody Partida partida, UriComponentsBuilder ucBuilder) {
		if (partidaServ.cadastrarPartida(partida)) {
			return responseController.responseController(partida, HttpStatus.OK);
			// return new ResponseEntity<Partida>(partida, HttpStatus.CREATED);
		} else {
			return exceptController.errorHandling("Erro no cadastro de partidas", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@RequestMapping(value = "/partidas/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getPartida(@PathVariable("id") long id) {
		Partida partida = partidaServ.getpartidaById(id);
		if (partida == null) {
			return exceptController.errorHandling("Nao existe essa partida", HttpStatus.NOT_FOUND);
		}
		return responseController.responseController(partida, HttpStatus.OK);
	}

	@RequestMapping(value = "/partidas/andamento/", method = RequestMethod.GET)
	public ResponseEntity<?> getPartidasEmAndamento() {

		List<Partida> partidasAndamento = partidaServ.getPartidaEmAndamento();

		if (partidasAndamento.isEmpty()) {
			return exceptController.errorHandling("Nao existem partidas em andamento", HttpStatus.NOT_FOUND);
		}

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode partidas = mapper.createArrayNode();

		for (Partida p : partidasAndamento) {

			ObjectNode objectNode1 = mapper.createObjectNode();
			objectNode1.put("id", p.getId());
			objectNode1.put("timeA", p.getTimeA());
			objectNode1.put("timeB", p.getTimeB());
			objectNode1.put("descricao", p.getDescricao());

			partidas.add(objectNode1);
		}

		return responseController.responseController(partidas, HttpStatus.OK);
	}

	@RequestMapping(value = "/partidas/iniciar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> iniciarPartida(@PathVariable("id") long id) {

		boolean p = partidaServ.iniciarPartida(id);

		if (!p) {
			return exceptController.errorHandling("Partida ja iniciada ou inexistente", HttpStatus.BAD_REQUEST);
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode objectNode1 = mapper.createObjectNode();
		
		objectNode1.put("id", id);
		objectNode1.put("iniciada", p);
		
		return responseController.responseController(objectNode1, HttpStatus.OK);

	}
	
	@RequestMapping(value = "/partidas/finalizar/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> finalizarPartida(@PathVariable("id") long id) {
		
		try {
			boolean p = partidaServ.finalizarPartida(id);

			if (!p) {
				return exceptController.errorHandling("Partida ja finalizada", HttpStatus.OK);
			}
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode objectNode1 = mapper.createObjectNode();
			
			objectNode1.put("id", id);
			objectNode1.put("finalizada", p);
			
			return responseController.responseController(objectNode1, HttpStatus.OK);
		}catch (RuntimeException e) {
			return exceptController.errorHandling("Partida nao iniciada", HttpStatus.BAD_REQUEST);
		}

	}
	
	
		// Retorna as Partidas Anteriores
		@RequestMapping(value = "/partidasAnteriores/", method = RequestMethod.GET)
		public ResponseEntity<?> listaPartidasAnteriores() {
			List<Partida> partidasAnteriores = partidaServ.getPartidasAnteriores();

			if (partidasAnteriores.isEmpty()) {
				return exceptController.errorHandling("Nao existem partidas anteriores", HttpStatus.NOT_FOUND);
			}
			ObjectMapper mapper = new ObjectMapper();
			ArrayNode partidas = mapper.createArrayNode();

			for (Partida p : partidasAnteriores) {

				ObjectNode objectNode1 = mapper.createObjectNode();
				objectNode1.put("id", p.getId());
				objectNode1.put("timeA", p.getTimeA());
				objectNode1.put("timeB", p.getTimeB());
				objectNode1.put("data", p.getData().toString());
				objectNode1.put("ganhador", p.getGanhadorPartida());

				partidas.add(objectNode1);
			}

			return responseController.responseController(partidas, HttpStatus.OK);
		}
		
		// Retorna as Partidas Posteriores
				@RequestMapping(value = "/partidasPosteriores/", method = RequestMethod.GET)
				public ResponseEntity<?> listaPartidasPosteriores() {
					List<Partida> partidasPosteriores = partidaServ.getPartidasPosteriores();

					if (partidasPosteriores.isEmpty()) {
						return exceptController.errorHandling("Nao existem partidas posteriores", HttpStatus.NOT_FOUND);
					}
					ObjectMapper mapper = new ObjectMapper();
					ArrayNode partidas = mapper.createArrayNode();

					for (Partida p : partidasPosteriores) {

						ObjectNode objectNode1 = mapper.createObjectNode();
						objectNode1.put("id", p.getId());
						objectNode1.put("timeA", p.getTimeA());
						objectNode1.put("timeB", p.getTimeB());
						objectNode1.put("data", p.getData().toString());
						objectNode1.put("ganhador", p.getGanhadorPartida());

						partidas.add(objectNode1);
					}

					return responseController.responseController(partidas, HttpStatus.OK);
				}

	}
	

