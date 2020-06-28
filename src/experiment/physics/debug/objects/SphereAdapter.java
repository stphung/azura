package experiment.physics.debug.objects;

import experiment.physics.objects.SphericalMassImpl;

public class SphereAdapter extends SphericalMassImpl {
    public SphereAdapter(Sphere sphere) {
        super(sphere.getRadius(), sphere.getMass(), new double[]{sphere.getX(), sphere.getY(), sphere.getZ()}, new double[]{sphere.getVx(), sphere.getVy(), sphere.getVz()});
    }
}
