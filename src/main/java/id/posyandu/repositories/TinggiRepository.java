package id.posyandu.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import id.posyandu.domain.Tinggi;

public interface TinggiRepository extends CrudRepository<Tinggi, String>{
	
	@Query(value = "SELECT * FROM tinggi where id_balita = :idBalita order by umur asc", nativeQuery=true)
	Iterable<Tinggi> findById(@Param("idBalita") String idBalita);
	
}
