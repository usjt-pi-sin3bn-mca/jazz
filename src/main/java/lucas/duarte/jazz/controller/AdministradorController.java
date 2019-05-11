package lucas.duarte.jazz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import lucas.duarte.jazz.model.bean.Administrador;
import lucas.duarte.jazz.model.service.AdministradorService;

@RestController
@RequestMapping("/api")
public class AdministradorController {
	@Autowired
	private AdministradorService administradorService;

	// Create administrador
	@RequestMapping(value = "/administrador/", method = RequestMethod.POST)
	public ResponseEntity<Administrador> createAdministrator(@RequestBody Administrador adm,
			UriComponentsBuilder ucBuilder) {
		return administradorService.cadastrarAdministrador(adm);
	}

	// deletar um administrador
	@RequestMapping(value = "/administrador/", method = RequestMethod.DELETE)
	public ResponseEntity<Administrador> deleteAdministrator(@RequestBody Administrador adm,
			UriComponentsBuilder ucBuilder) {
		return administradorService.DeletarAdministrador(adm);
	}

	// buscar todos administradores.
	@RequestMapping(value = "/administrador/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllAministradores() {
		return administradorService.getAlladministradores();

	}

//atualizar informações do Adm
	@RequestMapping(value = "/administrador/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSet(@PathVariable("id") long id, @RequestBody Administrador adm,
			UriComponentsBuilder ucBuilder) {
		return administradorService.updateAdm(adm, id);

	}
	
	//Verifica se o admin existe na base
	@RequestMapping(value = "/loginAdmin/", method = RequestMethod.POST, produces ="application/json")
	public ResponseEntity<?> loginAdmin(@RequestBody Administrador adm, UriComponentsBuilder ucBuilder) {
		return administradorService.loginAdmin(adm);
	}


}
