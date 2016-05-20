package id.posyandu.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "berat")
public class Berat {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String beratId;
	
	@ManyToOne
    @JoinColumn (name = "id_balita")
    private Balita idBalita;
	
	int umur;
	
	private Float beratBalita;

	public String getBeratId() {
		return beratId;
	}

	public void setBeratId(String beratId) {
		this.beratId = beratId;
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

	public Float getBeratBalita() {
		return beratBalita;
	}

	public void setBeratBalita(Float beratBalita) {
		this.beratBalita = beratBalita;
	}

	
	
	
	
}
