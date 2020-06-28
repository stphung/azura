package experiment.particle.debug.ui;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import experiment.particle.emitter.Emitter;

public class EmitterConfigurationPanel extends JPanel {
    
    private final Emitter emitter;
    
    public EmitterConfigurationPanel(final Emitter emitter) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        this.emitter = emitter;
        
        JSlider numParticlesSlider = new JSlider(0, 200, emitter.getNumParticles());
        final JLabel numParticlesLabel = new JLabel(Integer.toString(numParticlesSlider.getValue()));
        numParticlesSlider.addChangeListener(new ChangeListener() 
        {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider source = (JSlider)e.getSource();
                int value = source.getValue();
                emitter.setMaxParticles(value);
                numParticlesLabel.setText(Integer.toString(value));
            }
        });
        
        this.add(new SliderPanel("Particles", numParticlesLabel, numParticlesSlider));
    }
}
