package lucas.duarte.jazz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import lucas.duarte.jazz.model.bean.Campeonato;
import lucas.duarte.jazz.model.service.CampeonatoService;

@RestController
@RequestMapping("/api")
public class CampeonatoController {
	@Autowired
	private CampeonatoService campeonatoServ;
	@Autowired
	private ExceptionController exceptController;
	@Autowired
	private REController responseController;

	@RequestMapping(value = "/campeonato/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllCampeonatos() {
		System.out.println("Entrei no get all campeonatos");
		return campeonatoServ.getAllCampeonatos();

	}

	// Retorna apenas o ID e o nome dos campeonatos
	@RequestMapping(value = "/campeonato/names/", method = RequestMethod.GET)
	public ResponseEntity<?> getNamesAndIds() {
		List<Campeonato> campeonato = campeonatoServ.getAllCampeonatosNameId();
		if (campeonato.isEmpty()) {
			return exceptController.errorHandling("Nao existem campeonatos cadastrados", HttpStatus.NOT_FOUND);
		}

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode campeonatos = mapper.createArrayNode();

		for (Campeonato c : campeonato) {
			ObjectNode objectNode1 = mapper.createObjectNode();
			objectNode1.put("id", c.getId());
			objectNode1.put("nome", c.getNome());
			
			campeonatos.add(objectNode1);
		}
		return responseController.responseController(campeonatos, HttpStatus.OK);
	}

	// Create Partida
	@RequestMapping(value = "/campeonato/", method = RequestMethod.POST)
	public ResponseEntity<?> createCampeonato(@RequestBody Campeonato camp, UriComponentsBuilder ucBuilder) {
		// Apenas retorna a trataviva do service
		System.out.println("Vou cadastrar um campeonato");
		return campeonatoServ.salvarCampeonato(camp);

	}

}
