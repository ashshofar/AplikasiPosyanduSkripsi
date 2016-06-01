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
@Table(name = "jabatan")
public class Jabatan {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String jabatanId;

    private String namaJabatan;

    private String deskripsi;
    
    @OneToMany(cascade = CascadeType.MERGE, mappedBy="idJabatan")
    private List<Assigment> daftarAssigmentJabatan = new ArrayList<>();

	public String getJabatanId() {
		return jabatanId;
	}

	public void setJabatanId(String jabatanId) {
		this.jabatanId = jabatanId;
	}

	public String getNamaJabatan() {
		return namaJabatan;
	}

	public void setNamaJabatan(String namaJabatan) {
		this.namaJabatan = namaJabatan;
	}

	public String getDeskripsi() {
		return deskripsi;
	}

	public void setDeskripsi(String deskripsi) {
		this.deskripsi = deskripsi;
	}

	public List<Assigment> getDaftarAssigmentJabatan() {
		return daftarAssigmentJabatan;
	}

	public void setDaftarAssigmentJabatan(List<Assigment> daftarAssigmentJabatan) {
		this.daftarAssigmentJabatan = daftarAssigmentJabatan;
	}

	 
}
