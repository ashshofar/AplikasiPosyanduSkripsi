package id.posyandu.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.posyandu.domain.Kategori;
import id.posyandu.repositories.KategoriRepository;

@Service
@Transactional
public class KategoriServiceImplementation implements KategoriService{
	
	@Autowired
	protected KategoriRepository kategoriRepository;

	@Override
	public Kategori saveKategori(Kategori kategori) {
		return kategoriRepository.save(kategori);
	}

	@Override
	public Boolean deleteKategori(String kategoriId) {
		Kategori temp = kategoriRepository.findOne(kategoriId);
        if (temp != null) {
        	kategoriRepository.delete(temp);
            return true;
        }
        return false;
	}

	@Override
	public Kategori editKategori(Kategori kategori) {
		return kategoriRepository.save(kategori);
	}

	@Override
	public Kategori findKategori(String kategoriId) {
		return this.kategoriRepository.findOne(kategoriId);
	}

	@Override
	public Collection<Kategori> getAllKategoris() {
		Iterable<Kategori> itr = kategoriRepository.findAll();
        return (Collection<Kategori>) itr;
	}

	
	

}
