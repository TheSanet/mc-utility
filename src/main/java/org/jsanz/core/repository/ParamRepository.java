package org.jsanz.core.repository;

import org.jsanz.core.batch.entity.Parametro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParamRepository extends JpaRepository<Parametro, Integer> {
	
	public default String get(Integer i) {
		return this.getOne(i).getValor();
	}

}
