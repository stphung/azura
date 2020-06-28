package experiment.particle.debug.renderer;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import experiment.particle.particle.Particle;
import experiment.particle.util.RandomUtil;
import experiment.particle.util.Range;

public class RainbowParticleDrawer implements ParticleDrawer {

    private static final double PARTICLE_SIZE = 0.03;
    
    private final double maxLife;
    
    public RainbowParticleDrawer(double maxLife) {
        this.maxLife = maxLife;
    }
    
    @Override
    public void draw(Particle particle, GL gl, GLUT glut) {
        gl.glTranslated(particle.getX(), particle.getY(), particle.getZ());
        gl.glColor4d(
                RandomUtil.randomValueBetween(0,1), 
                RandomUtil.randomValueBetween(0,1), 
                RandomUtil.randomValueBetween(0,1), 
                particle.getLife() / this.maxLife);
        glut.glutSolidSphere(PARTICLE_SIZE, 10, 10);
    }
}
