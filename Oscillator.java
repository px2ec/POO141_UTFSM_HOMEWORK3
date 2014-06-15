import java.util.*;
import java.awt.*;

public class Oscillator extends PhysicsElement implements Simulateable, SpringAttachable {
   private static int id=0;  // Oscillator's identification number
   private double center;     // oscillator's center
   private double amplitude;     // oscillation's amplitude
   private double w;     // oscillator's frequency [rad]
   private double time;
   private double pos_t; 
   private double pos_tPlusDelta; 
   private OscillatorView view;  // Oscillator view of Model-View-Controller design pattern
   private ArrayList<Spring> springs;
   
   private Oscillator(){   // nobody can create a block without state
     this(1.0,0.3,0.5);
   }
   public Oscillator(double c, double a, double f){
      super(id++);
      pos_t = pos_tPlusDelta = center = c;
      amplitude = a;
      w = 2*Math.PI*f;
      time=0;
      view = new OscillatorView(this);
      springs = new ArrayList<Spring>();
   }
   public double getPosition() {
      return pos_t;
   }
   public void computeNextState(double delta_t, MyWorld world) {
      time+=delta_t;
      pos_tPlusDelta = center + amplitude*Math.sin(w*time);
   }
   public void updateState(){
     pos_t = pos_tPlusDelta;
   }
   public void updateView (Graphics2D g) {   
     view.updateView(g);  // update this Oscillator's view in Model-View-Controller design pattern     
   }
   public boolean contains(double x, double y) {
      return view.contains(x,y);
   }
   public void setSelected(){
      view.setSelected();
   }
   public void setReleased(){
      view.setReleased();
   }
   public void dragTo(double x){  // pos_t = center +dx
      center+= (x-pos_t);         // x = new_center + dx
      pos_t=x;                    // new_center= x - dx = x - (pos_t-center) 
   }                              // new_center=center +x-pos_t 

   public String getDescription() {
     return "Oscillator_" + getId()+":x";
   }
   public String getState() {
     return getPosition()+"";
   }
   public void attachSpring(Spring s){
     springs.add(s);
   }
   public void detachSpring(Spring s){
     springs.remove(s);
   }
}
