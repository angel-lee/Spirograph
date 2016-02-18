import java.awt.*;
import java.awt.geom.*;
import java.util.*;
import java.util.List;

public class Shapes {
	private double x, y, r;
	private List<Point2D> h;
	
	public Shapes(){
		this.h = new ArrayList<Point2D>();
	}
	
	public Shapes(double x, double y , double r){
		this.x = x;
		this.y = y;
		this.r = r;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}

	public double getR() {
		return r;
	}

	public void setR(double r) {
		this.r = r;
	}
	
	public Point2D getCenter() {
		return new Point2D.Double(x, y);
	}
	
	public void setCenter(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void add(double x, double y) {
		h.add(new Point2D.Double(x, y));
	}
	
	public void draw(Color penColor, int penSize, Graphics g) {
		if (h.size() > 1) {
			Graphics2D g2d = (Graphics2D) g;
			g2d.setColor(penColor);
			g2d.setStroke(new BasicStroke((float) penSize));
			Point2D p2 = h.get(0);
			for (int i = 1; i < h.size(); i++) {
				Point2D p1 = h.get(i);
				g2d.drawLine((int) p2.getX(), (int) p2.getY(), (int) p1.getX(), (int) p1.getY());
				p2 = p1;
			}
		}
	}
}
