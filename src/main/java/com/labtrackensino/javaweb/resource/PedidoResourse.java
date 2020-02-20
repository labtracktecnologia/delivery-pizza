package com.labtrackensino.javaweb.resource;

import com.labtrackensino.javaweb.model.Pedido;
import com.labtrackensino.javaweb.model.PizzaPedido;
import com.labtrackensino.javaweb.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;

@RestController
@RequestMapping(value = "/pedido")
@CrossOrigin(origins = "Access-Control-Allow-Origin: *")

public class PedidoResourse {


	@Autowired
	private PedidoRepository repository;


	/**
	 * @param limit  pagina atual;
	 * @param offset quantos por pagina;
	 */
	@RequestMapping(method = GET, produces = APPLICATION_JSON_VALUE,
			path = "listar")
	public ResponseEntity listar(
			@RequestParam(value = "limit") int limit,
			@RequestParam(value = "offset") int offset
	) {
		Pageable pageable = PageRequest.of(limit, offset, Sort.by(Sort.Direction.DESC, "id"));
		Iterable<Pedido> pedidos = repository.findAll(pageable);

		return new ResponseEntity<>(pedidos, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = GET, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable Long id) {

		Pedido pedido = repository.findById(id).orElse(null);

		return new ResponseEntity<>(pedido, HttpStatus.OK);
	}

	@RequestMapping(method = POST, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity post(@RequestBody Pedido pedido) {

		pedido.getPizzas().forEach(
				(p) -> p.setPedido(pedido)
		);
		Pedido persist = repository.save(pedido);

		return new ResponseEntity<>(persist, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = PUT, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity put(@PathVariable("id") Long id, @RequestBody Pedido pedido) {


		pedido.getPizzas().forEach(
				(p) -> p.setPedido(pedido)
		);
		Pedido pedidoUpdate = repository.save(pedido);

		return new ResponseEntity<>(pedidoUpdate, HttpStatus.OK);
	}

	@RequestMapping(value = "{id}", method = DELETE)
	public ResponseEntity delete(@PathVariable("id") Long id) {
		repository.deleteById(id);
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}

}
