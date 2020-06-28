package experiment.particle.particle;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import experiment.particle.util.RandomUtil;
import experiment.particle.util.Range;

public class RandomParticle extends Particle {
    public RandomParticle(Range x, Range y, Range z, Range vx, Range vy, Range vz, Range life) {
        super(new Point3d(RandomUtil.randomValueIn(x), RandomUtil.randomValueIn(y), RandomUtil.randomValueIn(z)),
              new Vector3d(RandomUtil.randomValueIn(vx), RandomUtil.randomValueIn(vy), RandomUtil.randomValueIn(vz)), 
              RandomUtil.randomValueIn(life));
    }
}
