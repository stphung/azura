package experiment.particle.policy;

import experiment.particle.particle.Particle;

public interface ParticleDeathPolicy {
    boolean isDead(Particle particle);
}
