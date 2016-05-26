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

	@Override
	public Collection<Antropometri> getBbuAntrosL() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriBbuL();
        return (Collection<Antropometri>) itr;
	}
	
	@Override
	public Collection<Antropometri> getBbuAntrosP() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriBbuP();
        return (Collection<Antropometri>) itr;
	}

	@Override
	public Collection<Antropometri> getBbtAntrosL() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriBbtL();
        return (Collection<Antropometri>) itr;
	}
	
	@Override
	public Collection<Antropometri> getBbtAntrosP() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriBbtP();
        return (Collection<Antropometri>) itr;
	}

	@Override
	public Collection<Antropometri> getTbuAntrosL() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriTbuL();
        return (Collection<Antropometri>) itr;
	}
	
	@Override
	public Collection<Antropometri> getTbuAntrosP() {
		Iterable<Antropometri> itr = antroRepository.findByKategoriTbuP();
        return (Collection<Antropometri>) itr;
	}
	

}
