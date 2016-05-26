package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Antropometri;


public interface AntropometriService {
	
	public Antropometri saveAntro(Antropometri antro);

    public Boolean deleteAntro(String antroId);

    public Antropometri editAntro(Antropometri antro);

    public Antropometri findAntro(String antroId);

    public Collection<Antropometri> getAllAntros();
    
    public Collection<Antropometri> getBbuAntrosL();
    
    public Collection<Antropometri> getBbtAntrosL();
    
    public Collection<Antropometri> getTbuAntrosL();
    
    public Collection<Antropometri> getBbuAntrosP();
    
    public Collection<Antropometri> getBbtAntrosP();
    
    public Collection<Antropometri> getTbuAntrosP();

}
