package com.labtrackensino.javaweb.resource;


import com.labtrackensino.javaweb.model.Borda;
import com.labtrackensino.javaweb.repository.BordaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "Access-Control-Allow-Origin: *")
@RequestMapping(value = "/borda")
public class BordaResourse {


	@Autowired
	private BordaRepository repository;

	/**
	 * @param limit  pagina atual;
	 * @param offset quantos por pagina;
	 */
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, path = "listar")
	public ResponseEntity listar(
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "offset") int offset,
			@RequestParam(value = "filter", required = false) String filter
	) {


		Pageable pageable = PageRequest.of(limit, offset, Sort.by(Sort.Direction.DESC, "id"));
		Iterable<Borda> bordas = repository.findAll(pageable);

		return new ResponseEntity<>(bordas, HttpStatus.OK);
	}


	@RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable Long id) {

		Borda borda = repository.findById(id).orElse(null);

		return new ResponseEntity<>(borda, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity post(@RequestBody Borda borda) {

		Borda persist = repository.save(borda);

		return new ResponseEntity<>(persist, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Borda borda) {

		Optional<Borda> find = repository.findById(id);
		if (find.isPresent()) {
			borda.setId(find.get().getId());
			return new ResponseEntity<>(repository.save(borda), HttpStatus.OK);
		}
		return new ResponseEntity<>(repository.save(borda), HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
