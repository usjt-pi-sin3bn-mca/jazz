package lucas.duarte.jazz.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import lucas.duarte.jazz.model.bean.Partida;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {
	//E possivel criar metodos de query especificos aqui
	public Partida findOneByTimeA(String timea);
	public Partida findSetById(Long id);
	
	public static final String FIND_PARTIDA_ANDAMENTO = "SELECT * FROM partida WHERE (partida_iniciada = true AND partida_finalizada = false)";
	@Query(value = FIND_PARTIDA_ANDAMENTO, nativeQuery = true)
    public List<Partida> findPartidasEmAndamento();
	
}
