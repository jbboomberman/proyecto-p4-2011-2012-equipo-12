package bomberman.ventanas;

import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

public class VentanaDirectorio extends JFileChooser {
	public VentanaDirectorio() {
		super();
		this.setControlButtonsAreShown(false);

		JButton acceptB = new JButton("Open");
		JButton cancelB = new JButton("Cancel");

		final JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		panel.add(Box.createHorizontalGlue());
		panel.add(acceptB);
		panel.add(Box.createHorizontalStrut(4));
		panel.add(cancelB);

		Insets insets = this.getInsets();

		panel.setBorder(BorderFactory.createEmptyBorder(insets.top,
				((insets.left >= 2) ? insets.left - 2 : insets.left),
				insets.bottom, insets.right));

		acceptB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				approveSelection();
			}

		});

		cancelB.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				cancelSelection();
			}

		});
	}

	public static void main(String args[]) {
		JFileChooser jfc = new VentanaDirectorio();
		int returnVal = jfc.showOpenDialog(null);
	}
}