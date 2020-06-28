package experiment.particle.emitter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import javax.vecmath.Point3d;

import experiment.particle.particle.Particle;
import experiment.particle.particle.ParticleFactory;

/*
 * Emitter is a class used to specify where particles are generated from, how many there are, and how fast they die.
 */
public class Emitter implements Iterable<Particle> {
    
    private double lifeElapsedPerIteration;
    private int maxParticles;
    private final ParticleFactory particleFactory;
    private final List<Particle> particles;
    private final Point3d position;

    public Emitter(ParticleFactory particleFactory, int maxParticles, double lifeElapsedPerIteration, Point3d position) {
        this.position = position;
        this.particleFactory = particleFactory;
        this.lifeElapsedPerIteration = lifeElapsedPerIteration;
        this.particles = new LinkedList<Particle>();
        this.maxParticles = maxParticles;
        for (int i = 0; i < this.maxParticles; i++) {
            this.particles.add(
                    this.particleFactory
                        .createFromEmitterPosition(this.position));
        }

    }

    public void emit(double timeElapsed) {
        List<Particle> deadParticles = new LinkedList<Particle>();
        for (Particle particle : particles) {
            double dx = particle.getVx() * timeElapsed;
            double dy = particle.getVy() * timeElapsed;
            double dz = particle.getVz() * timeElapsed;

            // Move particles forward in time
            particle.setX(particle.getX() + dx);
            particle.setY(particle.getY() + dy);
            particle.setZ(particle.getZ() + dz);

            // Add dead particles to the deadParticles list for removal
            if (particle.getLife() <= 0) {
                deadParticles.add(particle);
            } else {
                // Age the particle
                particle.setLife(particle.getLife() - this.lifeElapsedPerIteration);
            }

        }

        // Remove dead particles
        for (Particle particle : deadParticles) {
            this.particles.remove(particle);
        }

        // Particle recharge
        this.recharge();

    }
    
    public void recharge() {
        for (int i = 0; i < this.maxParticles - this.particles.size(); i++) {
            this.particles.add(this.particleFactory
                    .createFromEmitterPosition(this.position));
        }
    }

    public double getLifeElapsedPerIteration() {
        return lifeElapsedPerIteration;
    }

    public int getNumParticles() {
        return this.maxParticles;
    }

    public ParticleFactory getParticleFactory() {
        return this.particleFactory;
    }

    public double getX() {
        return this.position.getX();
    }

    public double getY() {
        return this.position.getY();
    }

    public double getZ() {
        return this.position.getZ();
    }

    public void setLifeElapsedPerIteration(double lifeElapsedPerIteration) {
        this.lifeElapsedPerIteration = lifeElapsedPerIteration;
    }

    public void setMaxParticles(int maxParticles) {
        this.maxParticles = maxParticles;
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setX(y);
    }

    public void setZ(double z) {
        this.position.setZ(z);
    }
    
    @Override
    public Iterator<Particle> iterator() {
        return this.particles.iterator();
    }
}
