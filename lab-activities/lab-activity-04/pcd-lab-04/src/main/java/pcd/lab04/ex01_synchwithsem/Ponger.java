package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Ponger extends ActiveComponent {
	private final Semaphore semaphore;
	
	public Ponger(Semaphore semaphore) {
		this.semaphore = semaphore;
	}	
	
	public void run() {
		while (true) {
			try	{
				this.semaphore.acquire();
            	println("pong");
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
				this.semaphore.release();
			}
		}
	}
}