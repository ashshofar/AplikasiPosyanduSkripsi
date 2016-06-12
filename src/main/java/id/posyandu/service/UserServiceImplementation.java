package id.posyandu.service;

import id.posyandu.domain.User;
import id.posyandu.repositories.UserRepository;
import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImplementation implements UserService {

    @Autowired
    protected UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Boolean deleteUser(String userId) {
        User temp = userRepository.findOne(userId);
        if (temp != null) {
            userRepository.delete(temp);
            return true;
        }
        return false;
    }

    @Override
    public User editUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User findUser(String userId) {
        return this.userRepository.findOne(userId);
    }

    @Override
    public Collection<User> getAllUsers() {
        Iterable<User> itr = userRepository.findAll();
        return (Collection<User>) itr;
    }

	@Override
	public Collection<User> getAllOrangtuas() {
		Iterable<User> itr = userRepository.findOrangtua();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllBidans() {
		Iterable<User> itr = userRepository.findBidan();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllPetugas() {
		Iterable<User> itr = userRepository.findPetugas();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllRws() {
		Iterable<User> itr = userRepository.findRw();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllAdmins() {
		Iterable<User> itr = userRepository.findAdmin();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllAyahs() {
		Iterable<User> itr = userRepository.findAyah();
        return (Collection<User>) itr;
	}

	@Override
	public Collection<User> getAllIbus() {
		Iterable<User> itr = userRepository.findIbu();
        return (Collection<User>) itr;
	}

}
