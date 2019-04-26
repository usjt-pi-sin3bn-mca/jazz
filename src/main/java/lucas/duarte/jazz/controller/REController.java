package lucas.duarte.jazz.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

@Controller
public class REController {

	public ResponseEntity<?> responseController(Object obj, HttpStatus statusCode) {
		return ResponseEntity.status(statusCode).body(obj);
	}

}
