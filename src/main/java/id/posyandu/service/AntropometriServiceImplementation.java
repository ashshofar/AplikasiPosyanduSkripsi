package id.posyandu.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.posyandu.domain.Antropometri;
import id.posyandu.repositories.AntropometriRepository;

@Service
@Transactional
public class AntropometriServiceImplementation implements AntropometriService{
	
	@Autowired
    protected AntropometriRepository antroRepository;
	
	@Override
	public Antropometri saveAntro(Antropometri antro) {
		return antroRepository.save(antro);
	}

	@Override
	public Boolean deleteAntro(String antroId) {
		Antropometri temp = antroRepository.findOne(antroId);
        if (temp != null) {
        	antroRepository.delete(temp);
            return true;
        }
        return false;
	}

	@Override
	public Antropometri editAntro(Antropometri antro) {
		return antroRepository.save(antro);
	}

	@Override
	public Antropometri findAntro(String antroId) {
		return this.antroRepository.findOne(antroId);
	}

	@Override
	public Collection<Antropometri> getAllAntros() {
		Iterable<Antropometri> itr = antroRepository.findAll();
        return (Collection<Antropometri>) itr;
	}
	

}
