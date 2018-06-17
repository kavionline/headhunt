package kavinda.headhunter.repository;

import kavinda.headhunter.model.Worker;

public interface WorkerRepository {

	Worker findById(int id);

	void save(Worker worker);

}
