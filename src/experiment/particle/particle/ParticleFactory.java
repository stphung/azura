package experiment.particle.particle;

import javax.vecmath.Point3d;

public interface ParticleFactory {
    Particle createFromEmitterPosition(Point3d position);
}