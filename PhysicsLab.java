import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;
import javax.swing.KeyStroke;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.net.URL;

public class PhysicsLab extends JApplet{

	protected URL codeBase = null;
    protected AudioClip beepClip;
    private double maxPlotTime = 15;

	public void init()
	{  
		if (getParameter("maxPlotTime") != null)
			maxPlotTime = Double.parseDouble(getParameter("maxPlotTime"));

		String deltaTime = getParameter("deltaTime");
		MyWorld world = new MyWorld(this);
		if (deltaTime != null) world.setDelta_t(Double.parseDouble(deltaTime));

		setSize(MyWorldView.WIDTH, MyWorldView.HEIGHT+50);  // height+50 to account for menu height
		String title = getParameter("title");

		MyWorldView  worldView = new MyWorldView(world);
		PhysicsLab_GUI_OnlyMenu labmenugui = new PhysicsLab_GUI_OnlyMenu(world, title);

		labmenugui.setVisible(true);

		world.setView(worldView);

		getParamFromHTML(world);

		codeBase = getCodeBase();

		beepClip = getAudioClip(codeBase, "audio/beep.au");

		add(worldView);  
	}

	public void beepcol(){
		if (beepClip != null) beepClip.play();
	}

	private void getParamFromHTML(MyWorld world) {

		int ballNum = 0;
		int springNum = 0;
		int fixedHookNum = 0;
		int blockNum = 0;
		int rubberNum = 0;
		int oscillatorNum = 0;

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

		if (getParameter("oscillatorNum") != null)
			oscillatorNum = Integer.parseInt(getParameter("oscillatorNum"));
		

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
			Spring sp0 = new Spring(Double.parseDouble(springvalues[0]), Double.parseDouble(springvalues[1]));
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
				Double.parseDouble(blockvalues[0]), Double.parseDouble(blockvalues[1]), 
				Double.parseDouble(blockvalues[2]), Double.parseDouble(blockvalues[3]));
			world.addElement(bl);
		}

		for (int j = 0; j < rubberNum; j++) {
			String rubberparams = getParameter("rubber." + (j + 1));

			String rubbervalues[] = rubberparams.split(";");
			Rubber rb0 = new Rubber(Double.parseDouble(rubbervalues[0]), Double.parseDouble(rubbervalues[1]));
			world.addElement(rb0);
		}		

		for (int j = 0; j < oscillatorNum; j++) {
			String oscillatorparams = getParameter("oscillator." + (j + 1));

			String oscillatorvalues[] = oscillatorparams.split(";");
			Oscillator osc0 = new Oscillator(Double.parseDouble(oscillatorvalues[0]),
					 Double.parseDouble(oscillatorvalues[1]), Double.parseDouble(oscillatorvalues[2]));
			world.addElement(osc0);
		}

	}

//----------------------------------------------------------------
	public static void main(String[] args) {
		PhysicsLab_GUI lab_gui = new PhysicsLab_GUI();
		lab_gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		lab_gui.setVisible(true);
	}
}

//------------------------------------------------------------------------------------------
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

		menuItem = new JMenuItem("Oscillator");
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

//------------------------------------------------------------------------------------------
class PhysicsLab_GUI_OnlyMenu extends JFrame {
	public PhysicsLab_GUI_OnlyMenu(MyWorld w, String title) {
		setTitle("My Small and Nice Physics Laboratory");
		if (title != null) setTitle(title);
		MyWorld world = w;
		setSize(MyWorldView.WIDTH, 100);  // height+50 to account for menu height

		LabMenuListener menuListener = new LabMenuListener(world);
		this.setJMenuBar(this.createLabMenuBar(menuListener));

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

		menuItem = new JMenuItem("Oscillator");
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
		return mb;
	}

}



