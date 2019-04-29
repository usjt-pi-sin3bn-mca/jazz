package lucas.duarte.jazz.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lucas.duarte.jazz.model.bean.Set;

@Repository
public interface SetRepository extends JpaRepository<Set, Long> {
	
	public static final String FIND_SETS_PARTIDA = "SELECT * from meu_set where partida_id = :id";
	@Query(value = FIND_SETS_PARTIDA, nativeQuery = true)
	public List<Set> findOneByPartida(@Param("id") long partidaId);
	

}
