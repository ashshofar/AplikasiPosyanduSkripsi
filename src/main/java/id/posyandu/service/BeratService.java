package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Berat;

public interface BeratService {
	public Berat saveBerat(Berat berat);

    public Boolean deleteBerat(String beratId);

    public Berat editBerat(Berat berat);

    public Berat findBerat(String beratId);

    public Collection<Berat> getAllBerats();

}
