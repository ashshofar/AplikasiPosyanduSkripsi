package id.posyandu.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import id.posyandu.domain.Antropometri;
;

public interface AntropometriRepository extends CrudRepository<Antropometri, String>{
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = 'f18e9b61-987e-4fb1-ac88-29495ebf93b4' and jenis_kelamin = 'Laki-Laki'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriBbuL();
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = 'f18e9b61-987e-4fb1-ac88-29495ebf93b4' and jenis_kelamin = 'Perempuan'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriBbuP();
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = '12b516d2-08f9-4a09-8165-431de426b9d5' and jenis_kelamin = 'Laki-Laki'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriBbtL();	
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = '12b516d2-08f9-4a09-8165-431de426b9d5' and jenis_kelamin = 'Perempuan'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriBbtP();
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = 'a11e8a71-e558-4974-9323-5721e624358e' and jenis_kelamin = 'Laki-Laki'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriTbuL();
	
	@Query(value = "SELECT * FROM antropometri WHERE kategori = 'a11e8a71-e558-4974-9323-5721e624358e' and jenis_kelamin = 'Perempuan'", nativeQuery = true)
	Iterable<Antropometri> findByKategoriTbuP();
	
}
