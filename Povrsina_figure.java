
package povrsina_figure;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

import javax.swing.JComponent;
import javax.swing.JOptionPane;

/**
 *
 * @author Nikola Vulovic
 *
 */
public class Povrsina_figure extends JComponent {
    private static final int NUMPOINTS = 500; // Up to 500 points can be chosen

    private JButton finish; // Button to indicate user is done entering points
    private Polygon shape; // polygon object to be drawn
    private boolean isDrawn; // boolean flag for when the user is finished drawing
    private int count; // how many points the user has clicked
    private Color color; // color of the polygon after user finalizes points
    private int[] x; // x coordinates of each point user picks
    private int[] y; // y coordinates of each point user picks
    private float sum = 0;
    private float area;
    // Image in which we're going to draw
    private Image slika;
    // Graphics2D object ==> used to draw on
    private Graphics2D g2;
    // Mouse coordinates

    public Povrsina_figure() {

	// adds MouseListener for mouse clicks

	isDrawn = false; // isDrawn is initially false
	count = 0; // count starts as 0
	x = new int[NUMPOINTS]; // allows for up to 500 points to be chosen
	y = new int[NUMPOINTS]; // allows for up to 500 points to be chosen

	// creates finish button
	// adds finish button to ActionListener
	// adds finish button to GUI

	color = Color.BLACK; // color is intially black, and will remain
			     // so if user cancels the color chooser

	// JOptionPane.showMessageDialog(null, "Click points that will make up the
	// polygon. After each" +
	// "point is entered, press the Finalize Points button");
	shape = new Polygon();

	setDoubleBuffered(false);

	addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
		if (isDrawn == false && count < NUMPOINTS) // if the finalize button is not pressed
		{ // the user can add additional points
		    x[count] = e.getX(); // adds the x point at the current mouse x coordinate
		    y[count] = e.getY(); // adds the y point at the current mouse y coordinate
		    count++; // count increases with each mouse click
		    repaint();

		}
	    }
	});

    }

    public void paint(Graphics g) // draws the Polylines, Polygons, and sets the color
    {
	super.paint(g);

	g.drawPolyline(x, y, count); // draws the polyline specified by user
				     // mouseclick
	g.setColor(color); // sets the color to the user chosen color

	if (isDrawn) // if finalize button is clicked
	{

	    g.fillPolygon(x, y, count); // finalizes polylines into

	} // polygon and fills shape
    }

    protected void paintComponent(Graphics g) {
	
        if (slika == null) {
	    // image to draw null ==> we create
	    slika = createImage(getSize().width, getSize().height);
	    g2 = (Graphics2D) slika.getGraphics();
	    // enable antialiasing
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	    // clear draw area
            g2.setPaint(Color.white);
            g2.fillRect(0, 0, getSize().width, getSize().height);
	    
	}

	g.drawImage(slika, 0, 0, null);
    }

    // now we create exposed methods
    public void clear() {
	count = 0;
        isDrawn = false;
        repaint();
    }

    public float getPolygonArea(int[] x, int[] y, int count) {
	area = 0;         // Accumulates area in the loop
 int j = count-1;  // The last vertex is the 'previous' one to the first

  for (int i=0; i<count; i++)
    { area = area +  (x[j]+x[i]) * (y[j]-y[i]); 
      j = i;  //j is previous vertex to i
    }
  sum = area/2;
  return sum;
    }

   
    public float getSum() {
	  isDrawn = true;                     //isDrawn is set to true, ending the users ability
                                            //  to add more points, and fills the polygon
        color = Color.black; 


        
        repaint();    

        
        return sum;
        
        
        
    }

    
    

     
    public void calc() {
	
	getPolygonArea(x,y,count);
	JOptionPane.showMessageDialog(null, "Површина полигона је" + getSum());
    }

}