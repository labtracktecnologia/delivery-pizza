
package com.labtrackensino.javaweb.repository;

import com.labtrackensino.javaweb.model.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PizzaRepository extends JpaRepository<Pizza, Long> {
	String SQL_PESQUISA = "select b from Pizza b where " +
			"translate(lower(b.descricao),  'àáãâèéêìíòóôõùúûüç','aaaaeeeiioooouuuuc') like lower('%' || :texto || '%') ";

	@Query(value = SQL_PESQUISA)
	public List<Pizza> getPizzasByTexto(@Param("texto") String texto);
}

