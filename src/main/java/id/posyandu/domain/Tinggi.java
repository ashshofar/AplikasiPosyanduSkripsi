package id.posyandu.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "tinggi")
public class Tinggi {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String tinggiId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn (name = "id_balita")
    private Balita idBalita;
	
	int umur;
	
	private Float tinggiBalita;

	public String getTinggiId() {
		return tinggiId;
	}

	public void setTinggiId(String tinggiId) {
		this.tinggiId = tinggiId;
	}

	public Balita getIdBalita() {
		return idBalita;
	}

	public void setIdBalita(Balita idBalita) {
		this.idBalita = idBalita;
	}

	public int getUmur() {
		return umur;
	}

	public void setUmur(int umur) {
		this.umur = umur;
	}

	public Float getTinggiBalita() {
		return tinggiBalita;
	}

	public void setTinggiBalita(Float tinggiBalita) {
		this.tinggiBalita = tinggiBalita;
	}
		
}
