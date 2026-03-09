package pcd.lab04.ex01_synchwithsem;

import java.util.concurrent.Semaphore;

public class Pinger extends ActiveComponent {
	private final Semaphore pongDoneEvent, pingDoneEvent;

	public Pinger(Semaphore semaphore, Semaphore pingDoneEvent) {
		this.pongDoneEvent = semaphore;
        this.pingDoneEvent = pingDoneEvent;
    }
	
	public void run() {
		while (true) {
			try {
				this.pongDoneEvent.acquire();
				println("ping");
				this.pingDoneEvent.release();
			} catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
	}
}