import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;
import javax.swing.KeyStroke;
import java.awt.event.*;

public class PhysicsLab extends JApplet{

	public void init()
	{  
		MyWorld world = new MyWorld();
		setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+50);  // height+50 to account for menu height
		
		MyWorldView  worldView = new MyWorldView(world);
		world.setView(worldView);

		getParamFromHTML(world);

		add(worldView);  
	}

	private void getParamFromHTML(MyWorld world) {

		int ballNum = 0;
		int springNum = 0;
		int fixedHookNum = 0;
		int blockNum = 0;
		int rubberNum = 0;

		if (getParameter("ballNum") != null)
			ballNum = Integer.parseInt(getParameter("ballNum"));
		
		if (getParameter("springNum") != null)
			springNum = Integer.parseInt(getParameter("springNum"));
		
		if (getParameter("fixedHookNum") != null)
			fixedHookNum = Integer.parseInt(getParameter("fixedHookNum"));
		
		if (getParameter("blockNum") != null)
			blockNum = Integer.parseInt(getParameter("blockNum"));
		
		if (getParameter("rubberNum") != null)
			rubberNum = Integer.parseInt(getParameter("rubberNum"));
		

		for (int j = 0; j < ballNum; j++) {
			String ballparams = getParameter("ball." + (j + 1));

			String ballvalues[] = ballparams.split(";");
			Ball b0 = new Ball(Double.parseDouble(ballvalues[0]), Double.parseDouble(ballvalues[1]), 
								Double.parseDouble(ballvalues[2]), Double.parseDouble(ballvalues[3]));
			world.addElement(b0);   
		}
	
		for (int j = 0; j < springNum; j++) {
			String springparams = getParameter("spring." + (j + 1));

			String springvalues[] = springparams.split(";");
			Spring sp0 = new Spring(Double.parseDouble(springvalues[0]), Double.parseDouble(springvalues[0]));
			world.addElement(sp0);
		}

		for (int j = 0; j < fixedHookNum; j++) {
			String fixedHookparams = getParameter("fixedHook." + (j + 1));

			String fixedHookvalues[] = fixedHookparams.split(";");
			FixedHook f0 = new FixedHook(Double.parseDouble(fixedHookvalues[0]));
			world.addElement(f0);
		}

		for (int j = 0; j < blockNum; j++) {
			String blockparams = getParameter("block." + (j + 1));

			String blockvalues[] = blockparams.split(";");
			Block bl = new Block(Double.parseDouble(blockvalues[0]), 
				Double.parseDouble(blockvalues[0]), Double.parseDouble(blockvalues[0]), 
				Double.parseDouble(blockvalues[0]), Double.parseDouble(blockvalues[0]));
			world.addElement(bl);
		}

		for (int j = 0; j < rubberNum; j++) {
			String rubberparams = getParameter("rubber." + (j + 1));

			String rubbervalues[] = rubberparams.split(";");
			Rubber rb0 = new Rubber(Double.parseDouble(rubbervalues[0]), Double.parseDouble(rubbervalues[0]));
			world.addElement(rb0);
		}

	}

	public static void main(String[] args) {
		PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
		lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lab_gui.setVisible(true);
	}
}

class PhysicsLab_GUI extends JFrame {
	MyWorld world = new MyWorld();
	public PhysicsLab_GUI() {
		setTitle("My Small and Nice Physics Laboratory");
		setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+50);  // height+50 to account for menu height
		
		MyWorldView  worldView = new MyWorldView(world);
		world.setView(worldView);
		add(worldView);  
		LabMenuListener menuListener = new LabMenuListener(world);
		this.setJMenuBar(this.createLabMenuBar(menuListener));
		/* ... */
	}

	public JMenuBar createLabMenuBar(LabMenuListener menu_l) {
		JMenuBar mb = new JMenuBar();
		
		JMenu menu = new JMenu ("Configuration");
		mb.add(menu);
		JMenu subMenu = new JMenu("Insert");  
		menu.add(subMenu);
		
		JMenuItem menuItem = new JMenuItem("Ball");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Spring");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Fixed Hook");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Block");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);

		menuItem = new JMenuItem("Rubber");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);

		menuItem = new JMenuItem("My scenario");
		menuItem.addActionListener(menu_l);
		subMenu.add(menuItem);
 
		menu = new JMenu("MyWorld");
		mb.add(menu);
		menuItem = new JMenuItem("Start");
		menuItem.addActionListener(menu_l);
		menu.add(menuItem);

		menuItem = new JMenuItem("Stop");
		menuItem.addActionListener(menu_l);
		menu.add(menuItem);

		menuItem = new JMenuItem("Next Item");
		menuItem.addActionListener(menu_l);
		menu.add(menuItem);

		JMenu submenu = new JMenu("Simulator");

		menuItem = new JMenuItem("Delta time");
		menuItem.addActionListener(menu_l);
		submenu.add(menuItem);

		menuItem = new JMenuItem("View refresh time");
		menuItem.addActionListener(menu_l);
		submenu.add(menuItem);

		menuItem = new JMenuItem("Set gravity");
		menuItem.addActionListener(menu_l);
		submenu.add(menuItem);

		menu.add(submenu);
		/* ...*/
		return mb;
	}
}