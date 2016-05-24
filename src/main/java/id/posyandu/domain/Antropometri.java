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
@Table(name = "antropometri")
public class Antropometri {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String antroId;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn(name = "kategori")
    private Kategori kategori;
	
	String jenisKelamin;
	
	int umur;
	
	float minus3sd;
	
	float minus2sd;
	
	float minus1sd;
	
	float median;
	
	float plus1sd;
	
	float plus2sd;
	
	float plus3sd;
	
	public String getAntroId() {
		return antroId;
	}

	public void setAntroId(String antroId) {
		this.antroId = antroId;
	}

	public Kategori getKategori() {
		return kategori;
	}

	public void setKategori(Kategori kategori) {
		this.kategori = kategori;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public int getUmur() {
		return umur;
	}

	public void setUmur(int umur) {
		this.umur = umur;
	}

	public float getMinus3sd() {
		return minus3sd;
	}

	public void setMinus3sd(float minus3sd) {
		this.minus3sd = minus3sd;
	}

	public float getMinus2sd() {
		return minus2sd;
	}

	public void setMinus2sd(float minus2sd) {
		this.minus2sd = minus2sd;
	}

	public float getMinus1sd() {
		return minus1sd;
	}

	public void setMinus1sd(float minus1sd) {
		this.minus1sd = minus1sd;
	}

	public float getMedian() {
		return median;
	}

	public void setMedian(float median) {
		this.median = median;
	}

	public float getPlus1sd() {
		return plus1sd;
	}

	public void setPlus1sd(float plus1sd) {
		this.plus1sd = plus1sd;
	}

	public float getPlus2sd() {
		return plus2sd;
	}

	public void setPlus2sd(float plus2sd) {
		this.plus2sd = plus2sd;
	}

	public float getPlus3sd() {
		return plus3sd;
	}

	public void setPlus3sd(float plus3sd) {
		this.plus3sd = plus3sd;
	}
	
	

}
