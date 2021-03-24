package br.jeronimo.api.sicredi.services;

import java.util.List;

public interface SicrediService<T, T2>{

	public T create(T obj);
	
	public List<T> findAll();
	
	public T findById(T2 id);
	
	public void deleteById(T2 id);
	
	public T updateData(T obj);
}
