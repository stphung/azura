package experiment.physics.debug.objects;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class World implements Iterable<Sphere> {
    private final List<Sphere> list;
    public World() {
        this.list = new ArrayList<Sphere>();
    }
    public World(Sphere[] s) {
        this();
        for ( Sphere sphere : s ) this.list.add(sphere);
    }
    public void add(Sphere sphere) {
        this.list.add(sphere);
    }
    public void remove(Sphere sphere) {
        this.list.remove(sphere);
    }
    @Override
    public Iterator<Sphere> iterator() {
        return this.list.iterator();
    }
}
