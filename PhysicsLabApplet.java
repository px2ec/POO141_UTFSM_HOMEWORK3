import javax.swing.JFrame;
import javax.swing.*;
import java.awt.Container;
import javax.swing.KeyStroke;
import java.awt.event.*;
import java.applet.AudioClip;
import java.applet.Applet;
import java.net.URL;

public class PhysicsLabApplet extends JApplet implements PhysicsLabClass{

	// Applet program
	protected URL codeBase = null;
    protected AudioClip beepClip;

    // Max plot time for graphics
    private double maxPlotTime = 15;

	public void init()
	{
		JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		// Check maxPlotTime parameter
		if (getParameter("maxPlotTime") != null)
			maxPlotTime = Double.parseDouble(getParameter("maxPlotTime"));

		//Plot interface
		DynamicData plot = new DynamicData("Graficos");
		// Create a world
		MyWorld world = new MyWorld(this, plot);

		// Check delta time parameter
		if (getParameter("deltaTime") != null) 
			world.setDelta_t(Double.parseDouble(getParameter("deltaTime")));

		// Check refresh time parameter
		if (getParameter("refreshTime") != null) 
			world.setRefreshPeriod(Double.parseDouble(getParameter("refreshTime")));
		
		// Asign title param from document
		String title = getParameter("title");
		
		PhysicsLab_GUI_Internal labinternalgui = new PhysicsLab_GUI_Internal(world, title);

		//Graphic interface		
		labinternalgui.setVisible(true);
		plot.setVisible(true);
		// Get parameters from document and adds elements
		getParamFromHTML(world);

		// Sound
		codeBase = getCodeBase();
		beepClip = getAudioClip(codeBase, "audio/beep.au");

		// Add worldView to content pane
		sp.add(labinternalgui);
		sp.add(plot);
		add(sp);  
	}

	// Sound function
	public void beepcol(){
		if (beepClip != null) beepClip.play();
	}

	// Check is runnig applet
	public boolean isApplet() {
		return true;
	}

	// Reset plot trigger
	public void resetPlotPL(DynamicData plot) {
		plot.reset();
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
}

//------------------------------------------------------------------------------------------
class PhysicsLab_GUI_Internal extends JInternalFrame {
	public PhysicsLab_GUI_Internal(MyWorld w, String title) {
		setTitle("My Small and Nice Physics Laboratory");
		if (title != null) setTitle(title);
		MyWorld world = w;
		MyWorldView  worldView = new MyWorldView(world);
		// Asigns WorldView
		world.setView(worldView);
		setSize(MyWorldView.WIDTH+50, MyWorldView.HEIGHT+100);  // height+50 to account for menu height
		add(worldView);  
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

		menuItem = new JMenuItem("Reset Plot");
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