package id.posyandu.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "kategori")
public class Kategori {
	
	 @Id
	    @GeneratedValue(generator = "uuid")
	    @GenericGenerator(name = "uuid", strategy = "uuid2")
	    private String kategoriId;
	 
	 	private String kodeKategori;

	    private String namaKategori;
	    
	    private String deskripsiKategori;
	    
	    @OneToMany(cascade = CascadeType.MERGE, mappedBy="kategori")
	    private List<Antropometri> daftarAntro = new ArrayList<>();
	    
	    public List<Antropometri> getDaftarAntro() {
			return daftarAntro;
		}

		public void setDaftarAntro(List<Antropometri> daftarAntro) {
			this.daftarAntro = daftarAntro;
		}

		public String getKategoriId() {
			return kategoriId;
		}

		public void setKategoriId(String kategoriId) {
			this.kategoriId = kategoriId;
		}

		public String getKodeKategori() {
			return kodeKategori;
		}

		public void setKodeKategori(String kodeKategori) {
			this.kodeKategori = kodeKategori;
		}

		public String getNamaKategori() {
			return namaKategori;
		}

		public void setNamaKategori(String namaKategori) {
			this.namaKategori = namaKategori;
		}

		public String getDeskripsiKategori() {
			return deskripsiKategori;
		}

		public void setDeskripsiKategori(String deskripsiKategori) {
			this.deskripsiKategori = deskripsiKategori;
		}
	    
	    	    
}
