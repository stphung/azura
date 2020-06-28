package experiment.physics.math;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import experiment.physics.objects.PointMass;

public class PhysicsMath {
    public static Vector3d getRadialVector(PointMass from, PointMass to) {
        Vector3d fromPos = new Vector3d(from.getX(), from.getY(), from.getZ());
        Vector3d toPos = new Vector3d(to.getX(), to.getY(), to.getZ());
        toPos.sub(fromPos);
        return toPos;
    }
    public static double distance(PointMass a, PointMass b) {
        Point3d pa = new Point3d(a.getX(), a.getY(), a.getZ());
        Point3d pb = new Point3d(b.getX(), b.getY(), b.getZ());
        return pa.distance(pb);
    }
}
