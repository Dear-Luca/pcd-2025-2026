package pcd.lab01.ex01;

import static org.fusesource.jansi.Ansi.*;

import org.fusesource.jansi.AnsiConsole;

/**
 * 
 * Simple program showing basic features 
 * of J-ANSI lib.
 *
 * To be run from a command line.
 * 
 */
public class TestScreen {

	public static void main(String[] args) {

		Screen screen = Screen.getInstance();
		screen.clear();
		
		var x0 = 1;
		var y0 = 10;
		
		for (int i = 1; i < 80; i++) {					
			screen.writeStringAt(y0, x0 + i, Color.RED, "Hello");
			try {
				Thread.sleep(100);
			} catch (Exception ex) {}
			screen.writeStringAt(y0, x0 + i, Color.BLACK, "Hello");
		}
	}

}
