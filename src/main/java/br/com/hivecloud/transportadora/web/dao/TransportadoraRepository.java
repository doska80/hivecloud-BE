package br.com.hivecloud.transportadora.web.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.hivecloud.transportadora.web.entity.Statistics;
import br.com.hivecloud.transportadora.web.entity.TransportadoraEntity;

public interface TransportadoraRepository extends JpaRepository<TransportadoraEntity, Long> {
	
	@Query("select distinct e from TransportadoraEntity e "
			+ "JOIN e.modais  m "
			+ "where ((:empresa is null or :empresa = '') or upper(e.empresa) like concat('%', upper(:empresa), '%')) "
			+ "and ((:uf is null or :uf = '') or e.uf = :uf) " 
			+ "and ((:cidade is null or :cidade = '') or e.cidade = :cidade) " 
			+ "and ((:modal is null or :modal = '') or m.nome = :modal ) ")
	List<TransportadoraEntity> findByCriteria(@Param("empresa") String empresa, @Param("uf") String uf,
			@Param("cidade") String cidade, @Param("modal") String modal);
	
	
    @Query(nativeQuery = true, value =
            "SELECT t.cidade AS item, COUNT(*) AS total FROM transportadoras t GROUP BY t.cidade")
     List<Statistics> cidadeCount();
	
    @Query(nativeQuery = true, value =
            "SELECT t.uf AS item, COUNT(*) AS total FROM transportadoras t GROUP BY t.uf")
     List<Statistics> ufCount();
	
    @Query(nativeQuery = true, value =
            "SELECT m.nome AS item, COUNT(*) AS total FROM transportadoras_modais t INNER JOIN modal m ON t.id_modal = m.id_modal GROUP BY m.nome ")
     List<Statistics> modalCount();


}
