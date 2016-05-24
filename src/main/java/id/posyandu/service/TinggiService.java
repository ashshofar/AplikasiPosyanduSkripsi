package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Tinggi;

public interface TinggiService {
	public Tinggi saveTinggi(Tinggi tinggi);

    public Boolean deleteTinggi(String tinggiId);

    public Tinggi editTinggi(Tinggi tinggi);

    public Tinggi findTinggi(String tinggiId);

    public Collection<Tinggi> getAllTinggis();
}
