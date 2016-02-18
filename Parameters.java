import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.*;

import javax.swing.*;
import javax.swing.event.*;

public class Parameters{
	private Canvas c;
	private JLabel message, penTitle, backgroundTitle;
	private JPanel panel = new JPanel();
	private JSlider rotor, stator, penSize, penLocation, animationSpeed;
	private JSlider penR, penG, penB, bR, bG, bB;
	public Color penColor, paperColor;
	public int statorValue, rotorValue, penSizeValue, penValue;
	public int penRValue, penGValue, penBValue, bRValue, bGValue, bBValue, animationValue;
	private boolean animated;
	private int width, statorInt, rotorInt, penInt, penSizeInt;
	private Color penColorInit, backgroundColorInit;
	
	public Parameters(){
		initValues();
		create();
		showColor();
		setDefault();
	}
	
	// initialize values for most of the parameters
	public void initValues(){
		this.width = 650;
		this.statorInt = 750;
		this.rotorInt = 400;
		this.penInt = 100;
		this.penSizeInt = 5;
		this.penColorInit = Color.PINK;
		this.backgroundColorInit = Color.GRAY;
		this.animationValue = 400;
		this.animated = true;
	}	
	
	public void setCanvas(Canvas c){
		this.c = c;
	}
	
	// create the GUI controls
	private void create(){
		panel.setLayout(new GridBagLayout());
		int gridy = 0;
		
		JLabel statorLabel = new JLabel("Stator radius:");
		statorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(statorLabel, 0, gridy, 2, 1, GridBagConstraints.LINE_START);

		stator = new JSlider(JSlider.HORIZONTAL, 0, 800, 750);
		stator.setMajorTickSpacing(200);
		stator.setMinorTickSpacing(50);
		stator.setPaintTicks(true);
		stator.setPaintLabels(true);
		add(stator, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
				
		
		JLabel rotorLabel = new JLabel("Rotor radius:");
		rotorLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(rotorLabel, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
		
		rotor = new JSlider(JSlider.HORIZONTAL, 0, 800, 400);
		rotor.setMajorTickSpacing(200);
		rotor.setMinorTickSpacing(50);
		rotor.setPaintTicks(true);
		rotor.setPaintLabels(true);
		add(rotor, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
		
		JLabel penLabel = new JLabel("Pen location (radius on rotor):");
		penLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(penLabel, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
		
		penLocation = new JSlider(JSlider.HORIZONTAL, 0, 400, 100);
		penLocation.setMajorTickSpacing(100);
		penLocation.setPaintTicks(true);
		penLocation.setPaintLabels(true);
		add(penLocation, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
		
		
		JLabel penSizeLabel = new JLabel("Pen stroke size:");
		penSizeLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(penSizeLabel, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
		
		penSize = new JSlider(JSlider.HORIZONTAL, 0, 20, 5);
		penSize.setMajorTickSpacing(5);
		penSize.setPaintTicks(true);
		penSize.setPaintLabels(true);
		add(penSize, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
	
		penTitle = new JLabel("Pen RGB");
		penTitle.setHorizontalAlignment(SwingConstants.LEFT);
		add(penTitle, 0, gridy, 2, 1, GridBagConstraints.CENTER);
		
		backgroundTitle = new JLabel("Background RGB");
		backgroundTitle.setHorizontalAlignment(SwingConstants.LEFT);
		add(backgroundTitle, 3, gridy++, 2, 1, GridBagConstraints.CENTER);
		
		penR = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
		penR.setPaintLabels(true);
		penR.addChangeListener(new ColorListener());
		Hashtable label = new Hashtable();
		label.put(new Integer(0), new JLabel("0"));
		label.put(new Integer(255), new JLabel("255"));
		penR.setLabelTable(label);
		add(penR, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
				
		bR = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
		bR.setPaintLabels(true);
		bR.addChangeListener(new ColorListener());
		bR.setLabelTable(label);
		add(bR, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
		
		penG = new JSlider(JSlider.HORIZONTAL, 0, 255, 175);
		penG.setPaintLabels(true);
		penG.addChangeListener(new ColorListener());
		penG.setLabelTable(label);
		add(penG, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
				
		bG = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
		bG.setPaintLabels(true);
		bG.addChangeListener(new ColorListener());
		bG.setLabelTable(label);
		add(bG, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
				
		penB = new JSlider(JSlider.HORIZONTAL, 0, 255, 175);
		penB.setPaintLabels(true);
		penB.addChangeListener(new ColorListener());
		penB.setLabelTable(label);
		add(penB, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
				
		bB = new JSlider(JSlider.HORIZONTAL, 0, 255, 128);
		bB.setPaintLabels(true);
		bB.addChangeListener(new ColorListener());
		bB.setLabelTable(label);
		add(bB, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);

		JLabel speedLabel = new JLabel("Animation speed:");
		speedLabel.setHorizontalAlignment(SwingConstants.LEFT);
		add(speedLabel, 0, gridy, 2, 1, GridBagConstraints.LINE_START);
		
		animationSpeed = new JSlider(JSlider.HORIZONTAL, 0, 1000, 500);
		animationSpeed.setPaintTicks(true);
		animationSpeed.setPaintLabels(true);
		animationSpeed.addChangeListener(new AnimationListener());
		Hashtable label2 = new Hashtable();
		JLabel slowSpeed = new JLabel("Slow");
		slowSpeed.setForeground(Color.blue);
		label2.put(new Integer(0), slowSpeed);
		JLabel fastSpeed = new JLabel("Fast");
		fastSpeed.setForeground(Color.blue);
		label2.put(new Integer(1000), fastSpeed);
		animationSpeed.setLabelTable(label2);
		add(animationSpeed, 2, gridy++, 2, 1, GridBagConstraints.LINE_START);
		
		JButton start = new JButton("Start");
		start.setHorizontalAlignment(SwingConstants.CENTER);
		start.addActionListener(new StartListener());
		add(start, 0, gridy, 2, 1, GridBagConstraints.CENTER);
		
		JButton stop = new JButton("Stop");
		stop.setHorizontalAlignment(SwingConstants.CENTER);
		stop.addActionListener(new StopListener());
		add(stop, 2, gridy++, 2, 1, GridBagConstraints.CENTER);

		JButton reset = new JButton("Reset");
		reset.setHorizontalAlignment(SwingConstants.CENTER);
		reset.addActionListener(new ResetListener());
		add(reset, 0, gridy++, 4, 1, GridBagConstraints.CENTER);
		
		message = new JLabel("");
		message.setForeground(Color.RED);
		message.setHorizontalAlignment(SwingConstants.LEFT);
		add(message, 0, gridy++, 4, 1, GridBagConstraints.LINE_START);
	}
	
	private void add(Component a, int gridx, int gridy, int gridw, int gridh, int anchor){
		Insets insets = new Insets(0, 10, 10, 10);
		int fill = GridBagConstraints.HORIZONTAL;
		GridBagConstraints g = new GridBagConstraints(gridx, gridy, gridw, gridh, 1.0, 1.0, anchor, fill, insets, 0, 0);
		panel.add(a, g);
	}
	
	private void setDefault(){
		stator.setValue(getStator());
		rotor.setValue(getRotor());
		penLocation.setValue(getPen());
		penSize.setValue(getPenSize());
		penR.setValue(255);
		penG.setValue(175);
		penB.setValue(175);
		bR.setValue(128);
		bG.setValue(128);
		bB.setValue(128);
		animationSpeed.setValue(400);
	}
	
	public class ColorListener implements ChangeListener{ 
		public void stateChanged(ChangeEvent e){
			penRValue = penR.getValue();
			penGValue = penG.getValue();
			penBValue = penB.getValue();
			bRValue = bR.getValue();
			bGValue = bG.getValue();
			bBValue = bB.getValue();
			setPenColor(new Color(penRValue, penGValue, penBValue, 255));
			setBackgroundColor(new Color(bRValue, bGValue, bBValue, 255));
			showColor();
		}
	}
	
	public class StartListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			boolean showMessage = false;
			int s = stator.getValue();
			int r = rotor.getValue();
			int pL = penLocation.getValue();
			int pS = penSize.getValue();
			int a = animationSpeed.getValue();
			setAnimated(true);
			message.setText("");
			
			if(s == 0 || r == 0 || pL == 0 || pS == 0){
				message.setText("None of the values may be zero.");
				showMessage = true;
			}
			if(a == 0){
				message.setText("The animation speed is zero.");
				showMessage = true;
			}
			if (s <= r) {
				message.setText("The rotor radius must be smaller than the stator radius");
				showMessage = true;
			}
			if (r <= pL) {
				message.setText("The pen location must be smaller than the rotor radius");
				showMessage = true;
			}
			if (showMessage) {
				return;
			}
			
			setStator(s);
			setRotor(r);
			setPen(pL);
			setPenSize(pS);
			setAnimationValue(a);
			c.draw(getAnimated());
		}
	}
	
	public class StopListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			c.stop();
		}
	}
	
	public class AnimationListener implements ChangeListener{
		public void stateChanged(ChangeEvent e){
			animationValue = animationSpeed.getValue();
			setAnimationValue((int)animationValue);
		}
	}
	
	public class ResetListener implements ActionListener{
		public void actionPerformed(ActionEvent a){
			initValues();
			setDefault();
			showColor();
		}	
	}
	
	private void showColor(){
		penRValue = penR.getValue();
		penGValue = penG.getValue();
		penBValue = penB.getValue();
		bRValue = bR.getValue();
		bGValue = bG.getValue();
		bBValue = bB.getValue();
		Color pen = new Color(penRValue, penGValue, penBValue);
		Color background = new Color(bRValue, bGValue, bBValue);
		penTitle.setForeground(pen);
		backgroundTitle.setForeground(background);
	}
	
	public boolean getAnimated() {
		return animated;
	}

	public void setAnimated(boolean animated) {
		this.animated = animated;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getStator() {
		return statorInt;
	}

	public void setStator(int statorInt) {
		this.statorInt = statorInt;
	}

	public int getRotor() {
		return rotorInt;
	}

	public void setRotor(int rotorInt) {
		this.rotorInt = rotorInt;
	}

	public int getPen() {
		return penInt;
	}

	public void setPen(int penInt) {
		this.penInt = penInt;
	}

	public int getPenSize() {
		return penSizeInt;
	}

	public void setPenSize(int penSizeInt) {
		this.penSizeInt = penSizeInt;
	}

	public Color getPenColor() {
		return penColorInit;
	}

	public void setPenColor(Color penColorInit) {
		this.penColorInit = penColorInit;
	}

	public Color getBackgroundColor() {
		return backgroundColorInit;
	}

	public void setBackgroundColor(Color backgroundColorInit) {
		this.backgroundColorInit = backgroundColorInit;
	}
	
	public JPanel getPanel(){
		return panel;
	}
	
	public int getAnimationValue(){
		return animationValue;
	}
	
	public void setAnimationValue(int animationValue){
		this.animationValue = animationValue;
	}
}

class Canvas extends JPanel{
	private Animation a;
	private Pen pen;
	private Parameters p;
	int fps, statorInt;
	JPanel panel = new JPanel();
	private Shapes stator;
	private Shape circle;
	
	public Canvas(Parameters p){
		this.p = p;
		create();
	}
	
	private void create(){
		new JPanel();
		int width = p.getWidth();
		int height = p.getWidth();
		setBackground(p.getBackgroundColor());
		setPreferredSize(new Dimension(width, height));
	}
	
	private int lcm(int stator, int rotor){
		long longStator = (long) stator;
		long longRotor = (long) rotor;
		long a = longStator;
		long b = longRotor;
		while (a != b) {
			if (a < b) {
				a += longStator;
			} else if (a > b) {
				b += longRotor;
			}
		}
		return (int) (a / longStator);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		if ((pen != null)) {
			pen.draw(g);
		}
	}
	
	public void draw(boolean animated){
		fps = p.getAnimationValue();
		int revolutions = lcm(p.getStator(), p.getRotor());
		int pointsPerRevolution = p.getWidth(); 
		a = new Animation(p, revolutions, pointsPerRevolution, animated, fps);
		a.addObserver(new Observer() {
			public void update(Observable o, Object arg) {
				pen = (Pen) arg;
				repaint();
			}
		});
		Thread thread = new Thread(a);
		thread.start();
	}
	
	public void stop() {
		a.stop();
	}
	
	public JPanel getCanvas(){
		return panel;
	}
}