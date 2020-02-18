
package com.labtrackensino.javaweb.repository;

import com.labtrackensino.javaweb.model.Borda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BordaRepository extends 
		JpaRepository<Borda, Long> {

	String SQL_PESQUISA = "select b from Borda b where " +
			"translate(lower(b.descricao),  'àáãâèéêìíòóôõùúûüç','aaaaeeeiioooouuuuc') like lower('%' || :texto || '%') ";

	@Query(value = SQL_PESQUISA)
	public List<Borda> getBordasByTexto(@Param("texto") String texto);
}

