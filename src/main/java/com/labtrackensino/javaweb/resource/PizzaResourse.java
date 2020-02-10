package com.labtrackensino.javaweb.resource;


import com.labtrackensino.javaweb.model.Pizza;
import com.labtrackensino.javaweb.repository.PizzaRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "Access-Control-Allow-Origin: *")
@RequestMapping(value = "/pizza")
public class PizzaResourse {


	@Autowired
	private PizzaRepository repository;


	/**
	 * @param limit  pagina atual;
	 * @param offset quantos por pagina;
	 */
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, path = "listar")
	public ResponseEntity listar(@RequestParam(value = "limit") int limit,
								 @RequestParam(value = "offset") int offset) {
		Pageable pageable = PageRequest.of(limit, offset, Sort.by(Sort.Direction.DESC, "id"));
		Iterable<Pizza> pizzas = repository.findAll(pageable);

		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}

	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, path = "pesquisar")
	public ResponseEntity pesquisar(@Param(value = "texto") String texto) {
		Iterable<Pizza> pizzas = repository.getPizzasByTexto(texto);
		return new ResponseEntity<>(pizzas, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable Long id) {

		Pizza pizza = repository.findById(id).orElse(null);

		return new ResponseEntity<>(pizza, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity post(@RequestBody Pizza pizza) {

		Pizza persist = repository.save(pizza);

		return new ResponseEntity<>(persist, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Pizza pizza) {

		Optional<Pizza> pizzaOld = repository.findById(id);
		if (pizzaOld.isPresent()) {
			return new ResponseEntity<>(repository.save(pizza), HttpStatus.OK);
		}
		return new ResponseEntity<>("ID do objeto difere do ID da URL", HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
