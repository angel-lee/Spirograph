import java.awt.Graphics;

public class Pen{
	private int width;
	private double x, y, r, o;
	private Shapes stator, rotor, pen, h;
	private Parameters p;
	
	public Pen(Parameters p) {
		this.p = p;
		this.h = new Shapes();
		setStator(p.getStator());
		setRotor();
		setPen();
	}
	
	public Shapes getStator() {
		return stator;
	}
 	
	public void setStator(int statorInt) {
		width = p.getWidth() - 20;
		x = (double) width / 2.0 + 10;
		x = (double) width / 2.0;
		r = (double) statorInt * ((double) width / (double) (p.getStator() * 2));
		y = (double) r + 10;
		stator = new Shapes(x, y, r);
	}
	
	public Shapes getRotor() {
		return rotor;
	}
	
	private void setRotor() {
		width = p.getWidth() - 20;
		x = (double) width / 2.0 + 10;
		r = (double) p.getRotor() * ((double) width / (double) (p.getStator() * 2));
		y = (double) r + 10;
		rotor = new Shapes(x, y, r);
	}
	
	public double getPen() {
		return p.getPen() * ((double) (p.getWidth() - 20) / (double) (p.getStator() * 2));
	}

	private void setPen() {
		x = (double) (p.getWidth() - 20) / 2.0 + 10;
		o = getPen();
		y = (double) rotor.getR() - o + 10;
		pen = new Shapes(x, y, 5.0);
	}
	
	private void addPoint(double x, double y) {
		h.add(x, y);
	}
	
	public void moveRotor(double x, double y) {
		rotor.setCenter(x, y);
	}
	
	public void movePen(double x, double y) {
		pen.setCenter(x, y);
		addPoint(x, y);
	}
	
	public void draw(Graphics g) {
		drawBackground(g);
		h.draw(p.getPenColor(), p.getPenSize(), g);
	}

	public void drawBackground(Graphics g) {
		g.setColor(p.getBackgroundColor());
		g.fillRect(0, 0, p.getWidth(), p.getWidth());
	}
}
