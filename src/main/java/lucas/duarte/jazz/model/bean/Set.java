package lucas.duarte.jazz.model.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;


@Entity
@Table(name = "meu_set")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, 
//property  = "id",
//resolver = EntityIdResolver.class,
//scope     = Long.class)
public class Set implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "ponto_a")
	private long pontoA;
	@Column(name = "ponto_b")
	private long pontoB;
	@Column(name = "tempo_a")
	private long tempoA;
	@Column(name = "tempo_b")
	private long tempoB;
	@Column(name = "ganhador_set")
	private String ganhador;
	@Column(name = "set_finalizado")
	private boolean setFinalizado;
	@Column(name = "saque")
	private String saque;
	
	@ManyToOne
	@JoinColumn(name = "partida_id")
	@JsonBackReference
	private Partida partida;
	
	public long getTempoA() {
		return tempoA;
	}

	public void setTempoA(long tempoA) {
		this.tempoA = tempoA;
	}

	public long getTempoB() {
		return tempoB;
	}

	public void setTempoB(long tempoB) {
		this.tempoB = tempoB;
	}

	public String getGanhador() {
		return ganhador;
	}

	public void setGanhador(String ganhador) {
		this.ganhador = ganhador;
	}

	//https://pt.stackoverflow.com/questions/242288/infinite-recursion-stackoverflowerror-erro-ao-listar-produtos-com-categorias
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public long getPontoA() {
		return pontoA;
	}

	public void setPontoA(long pontoA) {
		this.pontoA += pontoA;
	}

	public long getPontoB() {
		return pontoB;
	}

	public void setPontoB(long pontoB) {
		this.pontoB += pontoB;
	}

	public boolean isSetFinalizado() {
		return setFinalizado;
	}

	public void setSetFinalizado(boolean setFinalizado) {
		this.setFinalizado = setFinalizado;
	}

	public Partida getPartida() {
		return partida;
	}

	public void setPartida(Partida partida) {
		this.partida = partida;
	}
	
	public String getSaque() {
		return saque;
	}

	public void setSaque(String saque) {
		this.saque = saque;
	}
	
	public String toString() {
		return "Set [id=" + id + ", pontoA=" + pontoA + ", pontoB=" + pontoB + ", tempoA=" + tempoA + ", tempoB="
				+ tempoB + ", ganhador=" + ganhador + ", setFinalizado=" + setFinalizado + ", partida=" + partida + "]";
	}
	

}
