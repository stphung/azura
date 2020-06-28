package experiment.particle.debug.renderer;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import experiment.particle.particle.Particle;

public class TetrahedronParticleDrawer implements ParticleDrawer {
    
    private static final double SCALE = 0.2;
    private static final double ANGLE = 180;
    
    private final double maxLife;
    
    public TetrahedronParticleDrawer(double maxLife) {
        this.maxLife = maxLife;
    }
    
    public void draw(Particle particle, GL gl, GLUT glut) {
        gl.glTranslated(particle.getX(), particle.getY(), particle.getZ());
        
        gl.glColor4d(
                1.0,1.0,1.0, 
                particle.getLife() / this.maxLife);
        
        gl.glRotated(
                particle.getLife() / this.maxLife * 180, 
                particle.getLife(),
                particle.getLife(), 
                particle.getLife());
                
        gl.glScaled(SCALE, SCALE, SCALE);
        glut.glutWireTetrahedron();
    }
}
