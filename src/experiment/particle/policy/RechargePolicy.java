package experiment.particle.policy;

import experiment.particle.emitter.Emitter;

public interface RechargePolicy {
    boolean rechargeDue(Emitter emitter);
}
