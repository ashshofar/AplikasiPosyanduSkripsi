package id.posyandu.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.posyandu.domain.Berat;
import id.posyandu.repositories.BeratRepository;

@Service
@Transactional
public class BeratServiceImplementation implements BeratService{

	@Autowired
	protected BeratRepository beratRepository;
	
	@Override
	public Berat saveBerat(Berat berat) {
		return beratRepository.save(berat);
	}

	@Override
	public Boolean deleteBerat(String beratId) {
		Berat temp = beratRepository.findOne(beratId);
		if (temp != null) {
			beratRepository.delete(temp);
            return true;
        }
        return false;
	}

	@Override
	public Berat editBerat(Berat berat) {
		return beratRepository.save(berat);
	}

	@Override
	public Berat findBerat(String beratId) {
		return this.beratRepository.findOne(beratId);
	}

	@Override
	public Collection<Berat> getAllBerats() {
		Iterable<Berat> itr = beratRepository.findAll();
        return (Collection<Berat>) itr;
	}
	

}
