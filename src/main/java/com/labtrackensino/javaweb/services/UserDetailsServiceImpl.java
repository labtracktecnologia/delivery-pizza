package com.labtrackensino.javaweb.services;

import com.labtrackensino.javaweb.model.Usuario;
import com.labtrackensino.javaweb.repository.UsuarioRepository;
import com.labtrackensino.javaweb.security.Perfil;
import com.labtrackensino.javaweb.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Usuario usuario = repository.findByEmail(email);
		if (usuario == null) {
			throw new UsernameNotFoundException(email);
		}
		Set<Perfil> perfis = new HashSet<>();
		perfis.add(Perfil.ADMIN);
		return new UserSS(usuario.getId(), usuario.getEmail(), usuario.getSenha(), perfis);
	}
}
