package experiment.physics.objects;



public class SphericalMassImpl extends PointMassImpl implements SphericalMass {
    
	private final double radius;
	
	public SphericalMassImpl(double radius, double mass, double[] position, double[] velocity) {
	    super(mass, position, velocity);
        this.radius = radius;
	}

    public double getRadius() {
        return this.radius;
    }

}
