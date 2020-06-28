package experiment.physics.objects;

import javax.vecmath.Vector3d;

public class PointMassImpl implements PointMass {
    
    private final Vector3d position;
    private final Vector3d velocity;
    private final double mass;
    
    public PointMassImpl(double mass, double[] position, double[] velocity) {
        this.mass = mass;
        this.position = new Vector3d(position);
        this.velocity = new Vector3d(velocity);
    }
    
    public double getMass() {
        return this.mass;
    }

    public double getX() {
        return this.position.x;
    }
    
    public double getY() {
        return this.position.y;
    }
    
    public double getZ() {
        return this.position.z;
    }

    public double getVx() {
        return this.velocity.x;
    }
    
    public double getVy() {
        return this.velocity.y;
    }
    
    public double getVz() {
        return this.velocity.z;
    }

}
