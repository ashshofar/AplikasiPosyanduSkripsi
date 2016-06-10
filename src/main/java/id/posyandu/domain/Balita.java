package id.posyandu.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

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
    
    @Transient 
    private int umur;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn
    private User ayah;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade=CascadeType.MERGE)
    @JoinColumn
    private User ibu;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idBalita")
    private List<Berat> daftarBerat = new ArrayList<>();
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy="idBalita")
    private List<Tinggi> daftarTinggi = new ArrayList<>();
    
    public List<Berat> getDaftarBerat() {
		return daftarBerat;
	}

	public void setDaftarBerat(List<Berat> daftarBerat) {
		this.daftarBerat = daftarBerat;
	}

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

	public int getUmur() {
		return umur;
	}

	public void setUmur(int umur) {
		this.umur = umur;
		
	}

	public List<Tinggi> getDaftarTinggi() {
		return daftarTinggi;
	}

	public void setDaftarTinggi(List<Tinggi> daftarTinggi) {
		this.daftarTinggi = daftarTinggi;
	}
	
	public Integer hitungumur(){
		Date lahir = this.tanggalLahir;
		Date sekarang = new Date();
		
	    Calendar cal = Calendar.getInstance();
	    cal.setTime(lahir);
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH);
	    	    
	    Calendar calnow = Calendar.getInstance();
	    calnow.setTime(sekarang);
	    int yearnow = calnow.get(Calendar.YEAR);
	    int monthnow = calnow.get(Calendar.MONTH);
	   
	    
	    this.umur = (yearnow - year) * 12 + (monthnow - month) + 1;
	    
		return umur;
	}
	
	
}
