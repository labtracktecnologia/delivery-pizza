package com.labtrackensino.javaweb.services;

import com.labtrackensino.javaweb.model.Usuario;
import com.labtrackensino.javaweb.repository.UsuarioRepository;
import com.labtrackensino.javaweb.specification.UsuarioSpecification;
import com.labtrackensino.javaweb.validation.SpecificationValidator;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

	private UsuarioRepository repository;
	private final ApplicationContext context;


	public UsuarioService(UsuarioRepository repository, ApplicationContext context) {
		this.repository = repository;
		this.context = context;
	}

	public Usuario save(Usuario usuario){
		SpecificationValidator.create(context)
				.addSpecification(UsuarioSpecification.checkEmailDuplicado(repository))
				.validateWithException(usuario);


		return repository.save(usuario);

	}
}
