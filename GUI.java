package povrsina_figure;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI {
    JButton calculate;
    JButton erase;
    Povrsina_figure tabla = new Povrsina_figure();
    ActionListener actionListener1 = new ActionListener() {

	public void actionPerformed(ActionEvent a) {
	    if (a.getSource() == calculate) {
		tabla.calc();

	    }
	}
    };
    ActionListener actionListener2 = new ActionListener() {

	public void actionPerformed(ActionEvent b) {
	    if (b.getSource() == erase) {
		tabla.clear();

	    }
	}
    };

    public static void main(String[] args) {
	new GUI().show();
    }

    public void show() {
	// create main frame
	JFrame frame = new JFrame("Површина фигуре");
	Container content = frame.getContentPane();
	// set layout on content pane
	content.setLayout(new BorderLayout());
	// create draw area
	tabla = new Povrsina_figure();

	// add to content pane
	content.add(tabla, BorderLayout.CENTER);

	// create controls to apply colors and call clear feature
	JPanel controls = new JPanel();

	calculate = new JButton("Израчунај");
	calculate.addActionListener(actionListener1);
        erase = new JButton("Избриши");
	erase.addActionListener(actionListener2);
	// add to panel

	controls.add(calculate);
        controls.add(erase);

	// add to content pane
	content.add(controls, BorderLayout.NORTH);

	frame.setSize(600, 600);
	// can close frame
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	// show the swing paint result
	frame.setVisible(true);

    }

}