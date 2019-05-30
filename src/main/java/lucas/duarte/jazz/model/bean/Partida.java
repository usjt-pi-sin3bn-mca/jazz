package lucas.duarte.jazz.model.bean;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", resolver = EntityIdResolver.class, scope = Long.class)
public class Partida implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int rodada;
	// Campeonato ID vai se o nome da coluna na tabela
	@ManyToOne
	@JoinColumn(name = "campeonato_id")
	// @JsonBackReference

	@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "nome")
	@JsonIdentityReference(alwaysAsId = true)
	private Campeonato campeonato;
	// Nome do time sempre sera sao judas
	private String timeA;
	private String timeB;
	private boolean visitante;
	private String descricao;
	private String local;

	// yyyy-MM-dd HH:MM:SS
	@Temporal(TemporalType.DATE)
	private Date data;

//	@Temporal(TemporalType.TIME)
//	private Date hora;

	@OneToMany(mappedBy = "partida", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JsonManagedReference
	private List<Set> sets;

	private int desafioA;
	private int desafioB;
	private String urlLogoAdv;
	private String ganhadorPartida;
	private boolean partidaIniciada;
	private boolean partidaFinalizada;
	private int totalSetsTimeA;
	private int totalSetsTimeB;

	public Partida() {
		this.timeA = "Sao Judas";
	}

	public void setTimeA(String timeA) {
		this.timeA = timeA;
	}

	public List<Set> getSets() {
		return sets;
	}

	public void setSets(List<Set> sets) {
		this.sets = sets;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTimeA() {
		return timeA;
	}

	public String getTimeB() {
		return timeB;
	}

	public void setTimeB(String timeB) {
		this.timeB = timeB;
	}

	public boolean isVisitante() {
		return visitante;
	}

	public void setVisitante(boolean visitante) {
		this.visitante = visitante;
	}

	public int getRodada() {
		return rodada;
	}

	// Mocar Sao judas como time A
	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public String getData() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(data);
	}

	public void setData(Date data) {
		this.data = data;
	}

//	public String getHora() {
//		DateFormat dateFormat = new SimpleDateFormat("HH:mm");
//		return dateFormat.format(hora);
//	}
//
//	public void setHora(Date hora) {
//		this.hora = hora;
//	}

	public int getDesafioA() {
		return desafioA;
	}

	public void setDesafioA(int desafioA) {
		this.desafioA = desafioA;
	}

	public int getDesafioB() {
		return desafioB;
	}

	public void setDesafioB(int desafioB) {
		this.desafioB = desafioB;
	}

	public String getUrlLogoAdv() {
		return urlLogoAdv;
	}

	public void setUrlLogoAdv(String urlLogoAdv) {
		this.urlLogoAdv = urlLogoAdv;
	}

	public String getGanhadorPartida() {
		setGanhadorPartida();
		return this.ganhadorPartida;
	}

	public void setGanhadorPartida() {
		if (this.partidaFinalizada) {
			if (this.totalSetsTimeA > this.totalSetsTimeB)
				this.ganhadorPartida = this.timeA;
			else
				this.ganhadorPartida = this.timeB;
		} else
			this.ganhadorPartida = "";
	}

	public boolean isPartidaIniciada() {
		return partidaIniciada;
	}

	public void setPartidaIniciada(boolean partidaIniciada) {
		this.partidaIniciada = partidaIniciada;
	}

	public boolean isPartidaFinalizada() {
		return partidaFinalizada;
	}

	public void setPartidaFinalizada(boolean partidaFinalizada) {
		this.partidaFinalizada = partidaFinalizada;
	}

	public int getTotalSetsTimeA() {
		setTotalSets();
		return this.totalSetsTimeA;
	}

	public int getTotalSetsTimeB() {
		setTotalSets();
		return this.totalSetsTimeB;
	}

	private void setTotalSets() {
		this.totalSetsTimeA = 0;
		this.totalSetsTimeB = 0;

		List<Set> sets = this.getSets();
		if (sets != null) {
			for (Set set : sets) {
				if (set.isSetFinalizado()) {
					if (set.getGanhador().equals(this.timeA))
						this.totalSetsTimeA++;
					else
						this.totalSetsTimeB++;
				}
			}
		}
	}

}
