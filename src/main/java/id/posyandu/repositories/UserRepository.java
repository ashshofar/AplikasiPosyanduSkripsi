package id.posyandu.repositories;

import id.posyandu.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String>{
    
}
