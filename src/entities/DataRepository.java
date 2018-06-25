package entities;

import java.util.List;

public interface DataRepository<T> {
	public List<T> findAll();
	public T find(int id);
}
