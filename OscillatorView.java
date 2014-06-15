import java.awt.*;
import java.awt.geom.*;

public class OscillatorView {
   private Color color = Color.YELLOW;
   private Rectangle2D.Double outerShape=null;
   private Ellipse2D.Double centerShape = null;
   private double size;  // width/2
   private Oscillator osc;
   
   public OscillatorView (Oscillator o){
      osc = o;
      size = 0.1;
      outerShape = new Rectangle2D.Double(o.getPosition()-size,-size, 2*size, 2*size);
      centerShape = new Ellipse2D.Double(o.getPosition()-size/2, -size/2, size, size);
   }
   public boolean contains (double x, double y){
      return outerShape.contains(x,y);
   }
   public void setSelected (){
      color = Color.RED;
   }
   public void setReleased() {
      color = Color.YELLOW;
   }
   void updateView(Graphics2D g) {
      outerShape.setFrame(osc.getPosition()-size, -size, 2*size, 2*size);
      g.setColor(color);
      g.fill(outerShape);
      centerShape.setFrame(osc.getPosition()-size/2, -size/2, size, size);
      g.setColor(Color.BLACK);
      g.fill(centerShape);
   }
}