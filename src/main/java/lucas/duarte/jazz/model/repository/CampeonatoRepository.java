package lucas.duarte.jazz.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lucas.duarte.jazz.model.bean.Campeonato;

@Repository
public interface CampeonatoRepository extends JpaRepository<Campeonato, Long>{
	
	public static final String FIND_PROJECTS = "SELECT id, nome from campeonato";
	@Query(value = FIND_PROJECTS, nativeQuery = true)
    public List<Campeonato> findAllByNameAndId();
	
}
