package id.posyandu.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.posyandu.domain.Tinggi;
import id.posyandu.repositories.TinggiRepository;

@Service
@Transactional
public class TinggiServiceImplementation implements TinggiService{
	@Autowired
	protected TinggiRepository tinggiRepository;
	
	@Override
	public Tinggi saveTinggi(Tinggi tinggi) {
		return tinggiRepository.save(tinggi);
	}

	@Override
	public Boolean deleteTinggi(String tinggiId) {
		Tinggi temp = tinggiRepository.findOne(tinggiId);
		if (temp != null) {
			tinggiRepository.delete(temp);
            return true;
        }
        return false;
	}

	@Override
	public Tinggi editTinggi(Tinggi tinggi) {
		return tinggiRepository.save(tinggi);
	}

	@Override
	public Tinggi findTinggi(String tinggiId) {
		return this.tinggiRepository.findOne(tinggiId);
	}

	@Override
	public Collection<Tinggi> getAllTinggis() {
		Iterable<Tinggi> itr = tinggiRepository.findAll();
        return (Collection<Tinggi>) itr;
	}
	
	@Override
	public Collection<Tinggi> findAllTinggiByIdBalita(String idBalita){
		Iterable<Tinggi> itr = tinggiRepository.findById(idBalita);
		return (Collection<Tinggi>) itr;
	}

}
