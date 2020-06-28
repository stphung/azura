package experiment.particle.particle;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/*
 * Particle is a class used to specify where a particle is, its velocities, and its life.
 */
public class Particle {
    
    private final Point3d position;
    private final Vector3d velocity;
    private double life;
    // TODO: particle size, color?

    public Particle(Point3d position, Vector3d velocity, double life) {
        this.position = position;
        this.velocity = velocity;
        this.life = life;
    }

    public double getLife() {
        return this.life;
    }

    public double getVx() {
        return this.velocity.getX();
    }

    public double getVy() {
        return this.velocity.getY();
    }

    public double getVz() {
        return this.velocity.getZ();
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

    public void setLife(double life) {
        this.life = life;
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setY(y);
    }

    public void setZ(double z) {
        this.position.setZ(z);
    }

}
