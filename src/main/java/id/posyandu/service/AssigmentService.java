package id.posyandu.service;

import java.util.Collection;

import id.posyandu.domain.Assigment;

public interface AssigmentService {
	public Assigment saveAssigment(Assigment assigment);

    public Boolean deleteAssigment(String assigmentId);

    public Assigment editAssigment(Assigment assigment);

    public Assigment findAssigment(String assigmentId);

    public Collection<Assigment> getAllAssigments();
}
