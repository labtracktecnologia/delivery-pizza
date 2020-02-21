package com.labtrackensino.javaweb.specification;

import com.labtrackensino.javaweb.model.QUsuario;
import com.labtrackensino.javaweb.model.Usuario;
import com.labtrackensino.javaweb.repository.UsuarioRepository;
import com.labtrackensino.javaweb.validation.BasicSpecification;
import com.labtrackensino.javaweb.validation.Specifications;
import com.querydsl.core.BooleanBuilder;

public class UsuarioSpecification {

	public static BasicSpecification<Usuario> checkEmailDuplicado(UsuarioRepository repository) {
		return Specifications.basic("Já existe um usuário registrado com este e-mail", (candidate, specification) -> {

			final BooleanBuilder builder = new BooleanBuilder();
			builder.and(QUsuario.usuario.email.eq(candidate.getEmail()));

			if (candidate.getId() != null) {
				builder.and(QUsuario.usuario.id.ne(candidate.getId()));
			}

			return !repository.exists(builder);
		});
	}
}