package id.posyandu.repositories;

import id.posyandu.domain.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{
	
	@Query(value = "SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = 'c33d618b-a759-4d2f-b5d0-bf5bc5c26d71'", nativeQuery = true)
	Iterable<User> findOrangtua();
	
	@Query(value = "SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = '1ac99d1b-cee9-4c60-bb42-05e5399c883c'", nativeQuery = true)
	Iterable<User> findPetugas();
	
	@Query(value = "SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = '9ce32cfb-67f7-4410-93a1-3f15282a9811'", nativeQuery = true)
	Iterable<User> findBidan();
	
	@Query(value = "SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = '6b651276-f046-4ce9-b6d8-14bfeb42591c'", nativeQuery = true)
	Iterable<User> findRw();
	
	@Query(value = "SELECT * FROM user JOIN assigment on user_id = id_user_userId WHERE id_jabatan_jabatanId = 'aa0efeb2-556b-48ca-9043-69c7229c7bcb'", nativeQuery = true)
	Iterable<User> findAdmin();
	
	@Query(value = "SELECT * FROM user WHERE jenis_kelamin='Laki-Laki'", nativeQuery = true)
	Iterable<User> findAyah();
	
	@Query(value = "SELECT * FROM user WHERE jenis_kelamin='Perempuan'", nativeQuery = true)
	Iterable<User> findIbu();
	
}
