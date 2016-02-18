import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class Main{
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new Frame(new Parameters());
			}
		});
	}
}

class Frame extends JFrame{
	private Parameters p;
	private Canvas c;
	private JFrame frame = new JFrame();
	private JMenuBar menuBar = new JMenuBar();
	private BufferedImage b = null;
	
	public Frame(Parameters p) {
		this.p = p;
		c = new Canvas(p);
		p.setCanvas(c);
		setTitle("Angel Lee - Term Project ");
		setLayout(new FlowLayout());
		addMenu();
		add(c);
		add(p.getPanel());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void addMenu(){
		// setup the frame's menu bar
		JMenu fileMenu = new JMenu("File");	
		
		// Save image
		JMenuItem save = new JMenuItem("Save image");
		save.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				save();
			}
		});
		fileMenu.add(save);
		
		// Exit
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		fileMenu.add(exitItem);
		
		//attach a menu to a menu bar
		menuBar.add(fileMenu);	
		this.setJMenuBar(menuBar);
	}
	
	// returns canvas
	public Canvas returnCanvas(){
		return c;
	}
	
	// prompt user for name of output file
	private String promptOutput(){
		String result = JOptionPane.showInputDialog(this, "Output File", "Name", JOptionPane.QUESTION_MESSAGE);
		return result;
	}
	
	// save method
	private void save(){
		b = new BufferedImage(p.getWidth(), p.getWidth(), BufferedImage.TYPE_INT_RGB);
		Graphics g = b.createGraphics();
		JPanel panel = returnCanvas();
		panel.print(g);
		g.dispose();
		String output = promptOutput();
		File outputFile = new File(output);
		try{
			javax.imageio.ImageIO.write(b, "png", outputFile);
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

	// returns frame
	public JFrame getFrame() {
		return frame;
	}
}


