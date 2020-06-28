package experiment.particle.debug.ui;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

public class SliderPanel extends JPanel {
    public SliderPanel(String fieldName, JLabel valueLabel, JSlider slider) {
        super();
        this.setBorder(BorderFactory.createLineBorder(Color.black));
        this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
        this.add(new JLabel(fieldName));
        this.add(slider);
        this.add(valueLabel);
    }
}
