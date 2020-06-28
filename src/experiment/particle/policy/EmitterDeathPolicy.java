package experiment.particle.policy;

import experiment.particle.emitter.Emitter;

public interface EmitterDeathPolicy {
    boolean isDead(Emitter emitter);
}
