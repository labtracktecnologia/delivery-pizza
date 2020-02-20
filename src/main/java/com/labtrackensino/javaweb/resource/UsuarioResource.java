package com.labtrackensino.javaweb.resource;

import com.labtrackensino.javaweb.model.Usuario;
import com.labtrackensino.javaweb.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@CrossOrigin(origins = "Access-Control-Allow-Origin: *")
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private BCryptPasswordEncoder pe;

	@Autowired
	private UsuarioRepository repository;


	/**
	 * @param limit  pagina atual;
	 * @param offset quantos por pagina;
	 */
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE, path = "listar")
	public ResponseEntity listar(
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "offset") int offset
	) {
		Pageable pageable = PageRequest.of(limit, offset, Sort.by(Sort.Direction.DESC, "id"));
		Iterable<Usuario> usuarios = repository.findAll(pageable);

		return new ResponseEntity<>(usuarios, HttpStatus.OK);
	}


	@RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable Long id) {

		Usuario usuario = repository.findById(id).orElse(null);

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@RequestMapping(value = "email", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity getByEmail(@RequestParam String email) {

		Usuario usuario = repository.findByEmail(email);

		return new ResponseEntity<>(usuario, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity post(@RequestBody Usuario usuario) {

		usuario.setSenha(pe.encode(usuario.getSenha()));
		Usuario persist = repository.save(usuario);

		return new ResponseEntity<>(persist, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Usuario usuario) {

		Optional<Usuario> find = repository.findById(id);
		if (find.isPresent()) {
			Usuario update = find.get();
			update.setEmail(usuario.getEmail());
			update.setSenha(usuario.getSenha());
			return new ResponseEntity<>(repository.save(update), HttpStatus.OK);
		}
		return new ResponseEntity<>(repository.save(usuario), HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
