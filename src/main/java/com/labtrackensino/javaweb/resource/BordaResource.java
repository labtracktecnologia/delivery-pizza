package com.labtrackensino.javaweb.resource;

import com.labtrackensino.javaweb.model.Borda;
import com.labtrackensino.javaweb.repository.BordaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/borda")
public class BordaResource {

	@Autowired
	private BordaRepository repository;

	@RequestMapping(method = GET,
			produces = APPLICATION_JSON_VALUE,
			path = "listar")
	public ResponseEntity listar(@RequestParam(value = "limit", defaultValue = "1") int limit,
								 @RequestParam(value = "offset", defaultValue = "0") int offset,
								 @RequestParam(value = "filter",
										 required = false ) String filter) {
		Pageable pageable = PageRequest.of(offset,limit,
				Sort.by(Sort.Direction.DESC,"id"));

		Page<Borda> bordaPage = repository.findAll(pageable);
		return new ResponseEntity<>(bordaPage, HttpStatus.OK);
	}
	@RequestMapping(value = "{id}", method = GET,
			produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.OK)
	public Optional<Borda> get(@PathVariable Long id){

		return repository.findById(id);
	}

	@RequestMapping(method = POST, produces = APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public Borda post(@RequestBody @Valid Borda borda){
		return repository.save(borda);
	}

	@RequestMapping(value = "{id}" ,method = PUT, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Borda borda) {

		if(borda.getId().equals(id)){
			Borda bordaUpdate = repository.save(borda);
			return new ResponseEntity<>(bordaUpdate, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}
	@RequestMapping(value = "{id)", method = DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
	}
}
