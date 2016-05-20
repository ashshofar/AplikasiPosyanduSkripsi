package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Kategori;

public interface KategoriService {
	public Kategori saveKategori(Kategori kategori);

    public Boolean deleteKategori(String kategoriId);

    public Kategori editKategori(Kategori kategori);

    public Kategori findKategori(String kategoriId);

    public Collection<Kategori> getAllKategoris();
}
