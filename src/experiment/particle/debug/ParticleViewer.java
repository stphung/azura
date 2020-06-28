package experiment.particle.debug;

import java.awt.BorderLayout;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.vecmath.Point3d;

import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;

import experiment.particle.debug.renderer.ParticleDrawer;
import experiment.particle.debug.renderer.RainbowParticleDrawer;
import experiment.particle.debug.renderer.TetrahedronParticleDrawer;
import experiment.particle.debug.ui.EmitterConfigurationPanel;
import experiment.particle.emitter.Emitter;
import experiment.particle.particle.Particle;
import experiment.particle.particle.ParticleFactory;
import experiment.particle.particle.RandomParticleFactory;
import experiment.particle.util.Range;

public class ParticleViewer implements GLEventListener {

    public ParticleDrawer particleDrawer;
    
    // Emitter parameters
    private static final int NUM_PARTICLES = 70;
    private static final double LIFE_ELAPSED = 0.025;
    private static final double EMITTER_X = 0;
    private static final double EMITTER_Y = 0;
    private static final double EMITTER_Z = 0;

    // Random particle generation parameters
    private static final double MIN_X = -0.1, MAX_X = 0.1;
    private static final double MIN_Y = -0.1, MAX_Y = 0.1;
    private static final double MIN_Z = -0.1, MAX_Z = 0.1;
    private static final double MIN_VX = -3, MAX_VX = 3;
    private static final double MIN_VY = -3, MAX_VY = 3;
    private static final double MIN_VZ = -3, MAX_VZ = 3;
    private static final double MIN_LIFE = 0, MAX_LIFE = 2;
    
    // Emitter driver parameter to specify how much time passes per call
    private static final double TIME_ELAPSED = 0.005;
    
    private static ParticleFactory particleFactory = new RandomParticleFactory(
                                                        new Range(MIN_X, MAX_X), 
                                                        new Range(MIN_Y, MAX_Y), 
                                                        new Range(MIN_Z, MAX_Z), 
                                                        new Range(MIN_VX, MAX_VX), 
                                                        new Range(MIN_VY, MAX_VY), 
                                                        new Range(MIN_VZ, MAX_VZ), 
                                                        new Range(MIN_LIFE, MAX_LIFE));

    private static Emitter emitter = new Emitter(particleFactory, NUM_PARTICLES, LIFE_ELAPSED, new Point3d(EMITTER_X, EMITTER_Y, EMITTER_Z));

    public ParticleViewer() {
//        this.particleDrawer = new RainbowParticleDrawer(MAX_LIFE);
        this.particleDrawer = new TetrahedronParticleDrawer(MAX_LIFE);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUT glut = new GLUT();

        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT );
        setupView(gl, glu);

        emitter.emit(TIME_ELAPSED);

        for (Particle particle : emitter) {
            gl.glPushMatrix();
            this.particleDrawer.draw(particle, gl, glut);
            gl.glPopMatrix();
        }
    }
    
    private void setupView(GL gl, GLU glu) {
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 1000000000000000.0);
        glu.gluLookAt(0, 0, 5, 0, 0, 0, 0, 1, 0);
    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glEnable(gl.GL_BLEND); // Turn blending On
        gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE); // (Type of blending) Set the blending
    }

    @Override
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
    }
    
    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
            int arg4) {
    }

    public static void main(String[] args) {
        // Full screen anti-aliasing
        GLCapabilities glc = new GLCapabilities();
        glc.setSampleBuffers(true);
        glc.setNumSamples(10);

        // GlCanvas initialization
        GLCanvas glCanvas = new GLCanvas(glc);
        ParticleViewer scene = new ParticleViewer();
        glCanvas.addGLEventListener(scene);
        final Animator animator = new Animator(glCanvas);
        animator.start();

        JFrame frame = new JFrame();
        frame.setSize(1000, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(glCanvas, BorderLayout.CENTER);
        
        panel.add(new EmitterConfigurationPanel(emitter), BorderLayout.EAST);
        
        frame.add(panel);
        frame.setVisible(true);
    }
}
