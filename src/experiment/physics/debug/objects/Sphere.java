package experiment.physics.debug.objects;

import javax.media.opengl.GL;

import com.sun.opengl.util.GLUT;

public class Sphere {
	private static final int SLICES = 15;
	private static final int STACKS = 15;

	private double mass;
    private double radius;
	private double x, y, z;
	private double vx, vy, vz;
	
	public Sphere(double mass, double radius, double x, double y, double z, double vx, double vy, double vz) {
	    this.mass = mass;
		this.radius = radius;
		this.x = x;
		this.y = y;
		this.z = z;
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
	}

	public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public double getVx() {
        return vx;
    }

    public void setVx(double vx) {
        this.vx = vx;
    }

    public double getVy() {
        return vy;
    }

    public void setVy(double vy) {
        this.vy = vy;
    }

    public double getVz() {
        return vz;
    }

    public void setVz(double vz) {
        this.vz = vz;
    }

    public void draw(GL gl, GLUT glut) {
		gl.glTranslated(x, y, z);
		glut.glutWireSphere(this.radius, STACKS, SLICES);
	}
}