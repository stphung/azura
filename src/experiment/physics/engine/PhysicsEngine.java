package experiment.physics.engine;

import static java.lang.Math.pow;

import javax.vecmath.Vector3d;


import experiment.physics.constants.PhysicsConstants;
import experiment.physics.math.PhysicsMath;
import experiment.physics.objects.PointMass;
import experiment.physics.objects.SphericalMass;


public class PhysicsEngine {
	
	private static final PhysicsEngine instance = new PhysicsEngine();
	
	private PhysicsEngine() {
	}
	
	public static PhysicsEngine getInstance() {
		return PhysicsEngine.instance;
	}
	
	public boolean isColliding(SphericalMass a, SphericalMass b) {
		return a.getRadius() + b.getRadius() >= PhysicsMath.distance(a,b);
	}
	
	public Vector3d getVelocityChange(PointMass effected, PointMass effector, double timeElapsed) {
	    Vector3d forceOnEffected = getForceVector(effected, effector);
	    return getVelocityChangeDueToForce(effected, forceOnEffected, timeElapsed);
	}
	
	/*
	 * Newton's Second Law:  F = ma
	 * 
	 * a = F/m
	 * 
	 * v = v0 + at
	 * (v-v0)/t = a
	 * 
	 * F/m = (v-v0)/t
	 * Ft/m = v-v0
	 * Ft/m+v0 = v
	 * 
	 */
	private Vector3d getVelocityChangeDueToForce(PointMass effected, Vector3d effectorForce, double timeElapsed) {
        double mass = effected.getMass();
        return new Vector3d(
                ((effectorForce.x*timeElapsed)/mass), 
                ((effectorForce.y*timeElapsed)/mass), 
                ((effectorForce.z*timeElapsed)/mass));
	}
	
	private Vector3d getForceVector(PointMass effected, PointMass effector) {
	    double force = getForceMagnitude(effected, effector);
	    Vector3d diff = PhysicsMath.getRadialVector(effected,effector);

	    return new Vector3d(
	            force*diff.x/diff.length(), 
	            force*diff.y/diff.length(),
	            force*diff.z/diff.length());
	}

	/*
	 * Newton's Law of Universal Gravitation:  F = GMm/r^2
	 */
	private double getForceMagnitude(PointMass a, PointMass b) {
	    return PhysicsConstants.G*a.getMass()*b.getMass() / pow(PhysicsMath.distance(a,b),2);
	}
}
