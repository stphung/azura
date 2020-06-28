package experiment.physics.debug.engine;

import experiment.physics.debug.objects.Sphere;
import experiment.physics.debug.objects.World;

public class Updater {
    public void update(double timeElapsedSeconds, World world) {
        double t = timeElapsedSeconds;
        for ( Sphere sphere : world ) {
            sphere.setX(sphere.getX()+sphere.getVx()*t);
            sphere.setY(sphere.getY()+sphere.getVy()*t);
            sphere.setZ(sphere.getZ()+sphere.getVz()*t);
        }
    }
}
