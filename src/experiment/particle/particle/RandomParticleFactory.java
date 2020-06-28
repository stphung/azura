package experiment.particle.particle;

import javax.vecmath.Point3d;

import experiment.particle.util.Range;


public class RandomParticleFactory implements ParticleFactory {
    
    private final Range x;
    private final Range y;
    private final Range z;
    private final Range vx;
    private final Range vy;
    private final Range vz;
    private final Range life;
    
    public RandomParticleFactory(Range x, Range y, Range z, Range vx, Range vy, Range vz, Range life) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
        this.life = life;
    }
    
    @Override
    public Particle createFromEmitterPosition(Point3d position) {
        return new RandomParticle(new Range(this.x.getMin()+position.getX(), this.x.getMax()+position.getX()),
                                  new Range(this.y.getMin()+position.getY(), this.y.getMax()+position.getY()), 
                                  new Range(this.z.getMin()+position.getZ(), this.x.getMax()+position.getZ()), 
                                  this.vx, this.vy, this.vz, 
                                  this.life);
    }
}
