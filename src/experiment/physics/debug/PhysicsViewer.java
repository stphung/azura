package experiment.physics.debug;

import java.util.ArrayList;
import java.util.List;

import javax.media.opengl.GL;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCanvas;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.glu.GLU;
import javax.swing.JFrame;
import javax.vecmath.Vector3d;


import com.sun.opengl.util.Animator;
import com.sun.opengl.util.GLUT;

import experiment.physics.constants.PhysicsConstants;
import experiment.physics.debug.engine.Renderer;
import experiment.physics.debug.engine.Timer;
import experiment.physics.debug.engine.Updater;
import experiment.physics.debug.objects.Sphere;
import experiment.physics.debug.objects.SphereAdapter;
import experiment.physics.debug.objects.World;
import experiment.physics.engine.PhysicsEngine;

public class PhysicsViewer implements GLEventListener {

    private final Timer timer;
    private final World world;
    private final Renderer renderer;
    private final Updater updater;
    private final PhysicsEngine physicsEngine = PhysicsEngine.getInstance();
    private static final double TIME_INTERVAL = 60*60 / 10;
    private static final double CAMERA_DISTANCE = 500000000.0;

    public PhysicsViewer(World world) {
        this.timer = new Timer();
        this.renderer = new Renderer();
        this.updater = new Updater();
        this.world = world;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        timer.start();
        GL gl = drawable.getGL();
        GLU glu = new GLU();
        GLUT glut = new GLUT();

        // Clear the screen.
        gl.glClear(GL.GL_COLOR_BUFFER_BIT | GL.GL_DEPTH_BUFFER_BIT);

        // This transformation sequence is always applied to everything.
        gl.glMatrixMode(GL.GL_PROJECTION);
        gl.glLoadIdentity();
        glu.gluPerspective(60, 1, 0.1, 1000000000000000.0);
        glu.gluLookAt(0, 0, CAMERA_DISTANCE, 0, 0, 0, 0, 1, 0);
        //

        // Draw
        this.renderer.render(drawable, this.world);

        // Update
        this.updater.update(TIME_INTERVAL, this.world);

        // Collision detection
        for (Sphere s1 : this.world) {
            for (Sphere s2 : this.world) {
                if (s1 != s2) {
                    SphereAdapter bs1 = new SphereAdapter(s1);
                    SphereAdapter bs2 = new SphereAdapter(s2);
                    if (physicsEngine.isColliding(bs1, bs2)) {
                        System.out.println("Collision detected!");
                    }
                }
            }
        }

        // Physics
        for (Sphere s : this.world) {
            for (Sphere s2 : this.world) {
                if (s != s2) {
                    SphereAdapter bs = new SphereAdapter(s);
                    SphereAdapter bs2 = new SphereAdapter(s2);

//                    Vector3d f12 = physicsEngine.getForceVector(bs, bs2);
//                    Vector3d dv = physicsEngine.getVelocityChangeDueToForce(bs,
//                            f12, TIME_INTERVAL);
                    Vector3d dv = physicsEngine.getVelocityChange(bs, bs2, TIME_INTERVAL);

                    s.setVx(s.getVx() + dv.x);
                    s.setVy(s.getVy() + dv.y);
                    s.setVz(s.getVz() + dv.z);
                    
                }
            }
        }
    }

    @Override
    public void displayChanged(GLAutoDrawable arg0, boolean arg1, boolean arg2) {
        // TODO Auto-generated method stub

    }

    @Override
    public void init(GLAutoDrawable drawable) {
        GL gl = drawable.getGL();
        gl.glEnable(GL.GL_DEPTH);
    }

    @Override
    public void reshape(GLAutoDrawable arg0, int arg1, int arg2, int arg3,
            int arg4) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        PhysicsEngine be = PhysicsEngine.getInstance();

        // Full screen aa
        GLCapabilities glc = new GLCapabilities();
        glc.setSampleBuffers(true);
        glc.setNumSamples(10);
        
        GLCanvas canvas = new GLCanvas(glc);

        // Binary Star
//        Sphere[] s = {
//                new Sphere(Constants.MASS_EARTH, Constants.RADIUS_EARTH, 0, 0, 0, 0, -800,0),
//                new Sphere(Constants.MASS_EARTH, Constants.RADIUS_EARTH, -150000000, 0, 0, 0, 800, 0)
//        }; 
//        Sphere s1 = new Sphere(PBSConstants.MASS_EARTH, PBSConstants.RADIUS_EARTH, 0, 0, 0, 0, -800,0);
//        Sphere s2 = new Sphere(PBSConstants.MASS_EARTH, PBSConstants.RADIUS_EARTH, -150000000, 0, 0, 0, 800, 0);        
        
        // Three Planets
//        Sphere[] s = {
//                new Sphere(Constants.MASS_EARTH*2, Constants.RADIUS_EARTH, 0, 0, 0, 0, -800,0),
//                new Sphere(Constants.MASS_EARTH, Constants.RADIUS_EARTH, -150000000, 0, 0, 0, 800, 0),
//                new Sphere(Constants.MASS_EARTH, Constants.RADIUS_EARTH, 0, -150000000, 0, 0, 0, 800)
//        }; 
        
        // Moon Orbiting Earth, closer by 150M meters, increased orbital speed for easier viewing
//        Sphere[] s = {
//                new Sphere(Constants.MASS_EARTH, Constants.RADIUS_EARTH*5, 0, 0, 0, 0, 0, 0),
//                new Sphere(Constants.MASS_MOON, Constants.RADIUS_MOON*3, -150000000, 0, 0, 0, 1500, 0)
//        }; 
        
        Sphere[] s = {
                new Sphere(PhysicsConstants.MASS_EARTH, PhysicsConstants.RADIUS_EARTH, 0, 0, 0, 0, 0,0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -100000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 100000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 100000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -100000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -150000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 150000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 150000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -150000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -160000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 160000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 160000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -160000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -170000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 170000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 170000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -170000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -180000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 180000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 180000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -180000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -190000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 190000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 190000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -190000000, 0, -900, 0, 0),
                
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, -200000000, 0, 0, 0, 900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 200000000, 0, 0, 0, -900, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, 200000000, 0, 900, 0, 0),
                new Sphere(20, PhysicsConstants.RADIUS_EARTH, 0, -200000000, 0, -900, 0, 0)
        }; 

        // Build world, add it to the scene
        World world = new World(s);

        PhysicsViewer scene = new PhysicsViewer(world);
        canvas.addGLEventListener(scene);
        final Animator animator = new Animator(canvas);
        animator.start();

        frame.add(canvas);
        frame.setVisible(true);
    }
}
