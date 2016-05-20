package id.posyandu.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "balita")
public class Balita {
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String balitaId;
	
	private String nama;

    private String tempatLahir;
    
    @Temporal(TemporalType.DATE)
    private Date tanggalLahir;
    
    private String jenisKelamin;
    
    private String foto;
    
    private Float beratLahir;
    
    private Float tinggiLahir;
    
    @ManyToOne
    @JoinColumn
    private User ayah;
    
    @ManyToOne
    @JoinColumn
    private User ibu;

	public String getBalitaId() {
		return balitaId;
	}

	public void setBalitaId(String balitaId) {
		this.balitaId = balitaId;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public String getTempatLahir() {
		return tempatLahir;
	}

	public void setTempatLahir(String tempatLahir) {
		this.tempatLahir = tempatLahir;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getJenisKelamin() {
		return jenisKelamin;
	}

	public void setJenisKelamin(String jenisKelamin) {
		this.jenisKelamin = jenisKelamin;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public Float getBeratLahir() {
		return beratLahir;
	}

	public void setBeratLahir(Float beratLahir) {
		this.beratLahir = beratLahir;
	}

	public Float getTinggiLahir() {
		return tinggiLahir;
	}

	public void setTinggiLahir(Float tinggiLahir) {
		this.tinggiLahir = tinggiLahir;
	}

	public User getAyah() {
		return ayah;
	}

	public void setAyah(User ayah) {
		this.ayah = ayah;
	}

	public User getIbu() {
		return ibu;
	}

	public void setIbu(User ibu) {
		this.ibu = ibu;
	}
       
}
