package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Ponger extends ActiveComponent {
	private final Semaphore pingDoneEvent, pongDoneEvent;
	
	public Ponger(Semaphore semaphore, Semaphore pongDoneEvent) {
		this.pingDoneEvent = semaphore;
        this.pongDoneEvent = pongDoneEvent;
    }
	
	public void run() {
		while (true) {
			try	{
				this.pingDoneEvent.acquire();
            	println("pong");
				this.pongDoneEvent.release();
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}
}