package id.posyandu.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import id.posyandu.domain.Berat;

public interface BeratRepository extends CrudRepository<Berat, String> {
	
	@Query(value = "SELECT * FROM berat where id_balita = :idBalita order by umur asc", nativeQuery=true)
	Iterable<Berat> findById(@Param("idBalita") String idBalita);
}
