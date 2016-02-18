import java.awt.geom.*;
import java.util.*;

public class Animation extends Observable implements Runnable {
	private boolean animated, finished, stopped;
	public int fps;
	private int numRev, pointsPerRev;
	private double angle, stator, rotor;
	private long prevTime;
	private Pen pen;
	private Point2D center;

	public Animation(Parameters p, int numRev, int pointsPerRev, boolean animated, int fps) {
		this.animated = animated;
		this.stopped = false;
		this.numRev = numRev;
		this.pointsPerRev = pointsPerRev;
		this.fps = fps;
		this.angle = (Math.PI * 2) /(double) pointsPerRev;
		this.pen = new Pen(p);
		this.stator = pen.getStator().getR();
		this.rotor = pen.getRotor().getR();
		this.center = pen.getStator().getCenter();
		this.prevTime = System.currentTimeMillis();
	}
	
	public void stop() {
		this.stopped = true;
	}
	
	public boolean stopped() {
		return stopped;
	}
	
	public boolean finished() {
		return finished;
	}
	
	private void rotate(int a) {
		double penR = pen.getPen();
		double f = (stator - rotor) * -angle * a / rotor;
		
		double x = Math.cos(angle * a) * (stator - rotor);
		double y = Math.sin(angle * a) * (stator - rotor);
		double x1 = center.getX() - x;
		double y1 = center.getY() - y;
		pen.moveRotor(x1, y1);

		x = Math.cos(f) * penR;
		y = Math.sin(f) * penR;
		x1 = pen.getRotor().getX() - x;
		y1 = pen.getRotor().getY() - y;
		pen.movePen(x1, y1);

		if (animated) {
			notifyObservers(pen);
		}
	}
	
	public void run() {
		finished = false;
		for (int i = 0; i < numRev; i++) {
			for (int j = 0; j < pointsPerRev; j++) {
				if (!stopped) {
					rotate(i * pointsPerRev + j);
				}
			}
		}
		finished = true;
		notifyObservers(pen);
	}
	
	public void notifyObservers(Object o) {
		long current = System.currentTimeMillis();
		if (animated) {
			long elapsed = current - prevTime;
			long sleep = (1000L / fps) - elapsed;
			if (sleep > 0L) {
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
				}
				prevTime = System.currentTimeMillis();
			} else {
				prevTime = System.currentTimeMillis() + sleep;
			}
		}
		setChanged();
		super.notifyObservers(o);
	}
}
