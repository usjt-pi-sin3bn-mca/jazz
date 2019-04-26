package lucas.duarte.jazz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Controller
public class ExceptionController {
	
	private static final String ERROR_MESSAGE = "Ops! ocorreu um erro";
	
	public ResponseEntity<Object> errorHandling(@Nullable String message, HttpStatus status) {
		if (message == null) {
			message = ERROR_MESSAGE;
		}
		ObjectMapper mapper = new ObjectMapper();
		ObjectNode jsonObject = mapper.createObjectNode();
		jsonObject.put("message", message);
		return ResponseEntity.status(status).body(jsonObject);
	}

}
