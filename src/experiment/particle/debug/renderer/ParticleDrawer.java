package experiment.particle.debug.renderer;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

import experiment.particle.particle.Particle;

/*
 * Sample interface for a particle renderer.
 */
public interface ParticleDrawer {
    void draw(Particle particle, GL gl, GLUT glut);
}
