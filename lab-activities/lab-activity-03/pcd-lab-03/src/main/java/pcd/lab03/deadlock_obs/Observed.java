package pcd.lab03.deadlock_obs;

public interface Observed {
	int getState();
	void register(Observer obj);
}

