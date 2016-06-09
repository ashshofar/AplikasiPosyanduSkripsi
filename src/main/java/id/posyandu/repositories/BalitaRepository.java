package id.posyandu.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import id.posyandu.domain.Balita;

public interface BalitaRepository extends CrudRepository<Balita, String>{
	
	@Query(value = "SELECT *, PERIOD_DIFF(EXTRACT(YEAR_MONTH FROM now()), EXTRACT(YEAR_MONTH FROM tanggal_lahir)) as umur from balita", nativeQuery = true)
	Iterable<Balita> findBalitaAndUmur();
}
