package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Balita;

public interface BalitaService {
	public Balita saveBalita(Balita balita);

    public Boolean deleteBalita(String balitaId);

    public Balita editBalita(Balita balita);

    public Balita findBalita(String balitaId);

    public Collection<Balita> getAllBalitas();
    
    public Collection<Balita> getAllBalitaAndUmur();
}
