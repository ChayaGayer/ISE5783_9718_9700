package geometries;

import java.util.LinkedList;
import java.util.List;
import primitives.Ray;


/**
 * Class Geometries is a class representing a collection of geometries Cartesian
 * 3-Dimensional coordinate system.
 * 
 * @author Chaya Gayer 214309718, Shira Gayer 214309700
 */

public class Geometries extends Intersectable {
	private final List<Intersectable> geometries = new LinkedList<>();
	private final List<Intersectable> infinites = new LinkedList<>();

	/**
	 * a default constructor
	 */
	public Geometries() {
	}

	/**
	 * constructor that gets several intersectables and add them to the geometries
	 * list
	 * 
	 * @param geometries geometries to add to list
	 */
	public Geometries(Intersectable... geometries) {
		add(geometries);
	}

	/**
	 * constructor that gets several intersectables and add them to the geometries
	 * list
	 * 
	 * @param geometries geometries to add to list
	 */
	public Geometries(List<Intersectable> geometries) {
		add(geometries);
	}

	/**
	 * adds geometries to the list
	 * 
	 * @param geometries the geomtries to add
	 */
	public void add(Intersectable... geometries) {
		add(List.of(geometries));
	}

	/**
	 * adds geometries to the list
	 * 
	 * @param geometries the geomtries to add
	 */
	public void add(List<Intersectable> geometries) {
		if (!cbr) {
			this.geometries.addAll(geometries);
			return;
		}

		for (var g : geometries) {
			if (g.box == null)
				infinites.add(g);
			else {
				this.geometries.add(g);
				if (infinites.isEmpty()) {
					if (box == null)
						box = new Border();
					if (g.box.minX < box.minX)
						box.minX = g.box.minX;
					if (g.box.minY < box.minY)
						box.minY = g.box.minY;
					if (g.box.minZ < box.minZ)
						box.minZ = g.box.minZ;
					if (g.box.maxX > box.maxX)
						box.maxX = g.box.maxX;
					if (g.box.maxY > box.maxY)
						box.maxY = g.box.maxY;
					if (g.box.maxZ > box.maxZ)
						box.maxZ = g.box.maxZ;
				}
			}
		}
		// if there are inifinite objects
		if (!infinites.isEmpty())
			box = null;
	}

	@Override
	public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double dis) {
		LinkedList<GeoPoint> toReturn = null;
		for (Intersectable g : this.geometries) {
			var lPoints = g.findGeoIntersections(ray, dis);
			if (lPoints != null)
				if (toReturn == null)
					toReturn = new LinkedList<>(lPoints);
				else
					toReturn.addAll(lPoints);
		}
		for (Intersectable g : this.infinites) {
			var lPoints = g.findGeoIntersections(ray, dis);
			if (lPoints != null)
				if (toReturn == null)
					toReturn = new LinkedList<>(lPoints);
				else
					toReturn.addAll(lPoints);
		}
		return toReturn;
	}

	/**
	 * create the hierarchy and put into the right boxes
	 */
	public void setBVH() {
		double x = box.maxX - box.minX;
		double y = box.maxY - box.minY;
		double z = box.maxZ - box.minZ;
		// which axis we are reffering to
		setBVH(y > x && y > z ? 1 : z > x && z > y ? 2 : 0, 3);
	}

	/**
	 * Creates the hierarchy for the intersectable objects using Bounding Volume Hierarchy (BVH) and organizes them into the right boxes.
	 *
	 * @param axis  The axis along which to perform the BVH partitioning. Possible values: 0 (X-axis), 1 (Y-axis), 2 (Z-axis).
	 * @param count The maximum depth of the BVH hierarchy. If 'count' reaches 0, the method will stop further partitioning.
	 */
	private void setBVH(int axis, int count) {
	    // Check if the BVH construction is disabled (cbr is false) or if the maximum depth (count) has been reached.
	    // In such cases, return without performing any further partitioning.
	    if (!cbr || count == 0)
	        return;

	    
	    if (geometries.size() > 4) {
	        // If there is no bounding box (box is null), create a new bounding box that encompasses all finite geometries
	        // and then stop further partitioning.
	        if (box == null) {
	            var finites = new Geometries(geometries);
	            geometries.clear();
	            this.add(finites.geometries);
	            return;
	        }

	        // Create three lists to hold geometries on the left, middle, and right side of the selected axis.
	        var l = new Geometries();
	        var m = new Geometries();
	        var r = new Geometries();

	        // Calculate the midpoint along the selected axis.
	        double midX = (box.maxX + box.minX) / 2;
	        double midY = (box.maxY + box.minY) / 2;
	        double midZ = (box.maxZ + box.minZ) / 2;

	        // Based on the selected axis, partition the geometries into 'l', 'm', or 'r' lists.
	        switch (axis) {
	            case 0:
	                for (var g : geometries) {
	                    if (g.box.minX > midX)
	                        r.add(g);
	                    else if (g.box.maxX < midX)
	                        l.add(g);
	                    else
	                        m.add(g);
	                }
	                break;
	            case 1:
	                for (var g : geometries) {
	                    if (g.box.minY > midY)
	                        r.add(g);
	                    else if (g.box.maxY < midY)
	                        l.add(g);
	                    else
	                        m.add(g);
	                }
	                break;
	            case 2:
	                for (var g : geometries) {
	                    if (g.box.minZ > midZ)
	                        r.add(g);
	                    else if (g.box.maxZ < midZ)
	                        l.add(g);
	                    else
	                        m.add(g);
	                }
	                break;
	        }

	        // Determine the next axis for further partitioning (cycle through X, Y, Z axes).
	        int nextAxis = (axis + 1) % 3;

	        // Get the sizes of the left, middle, and right lists.
	        int lsize = l.geometries.size();
	        int msize = m.geometries.size();
	        int rsize = r.geometries.size();

	        // Clear the 'geometries' list to prepare for adding the sub-boxes after partitioning.
	        geometries.clear();

	        // Based on the sizes of the sub-boxes, decide whether to add them directly to 'geometries'
	        // or further partition them by calling 'setBVH' recursively.
	        if (lsize <= 2 || msize + rsize == 0) {
	            this.add(l.geometries);
	            if (msize + rsize == 0)
	                this.setBVH(nextAxis, count - 1);
	        } else {
	            geometries.add(l);
	        }

	        if (msize <= 2 || lsize + rsize == 0) {
	            this.add(m.geometries);
	            if (lsize + rsize == 0)
	                this.setBVH(nextAxis, count - 1);
	        } else {
	            geometries.add(m);
	        }

	        if (rsize <= 2 || lsize + msize == 0) {
	            this.add(r.geometries);
	            if (lsize + msize == 0)
	                this.setBVH(nextAxis, count - 1);
	        } else {
	            geometries.add(r);
	        }
	    }

	    // Recursively call 'setBVH' on any nested Geometries objects present in the 'geometries' list.
	    for (var geo : this.geometries) {
	        if (geo instanceof Geometries geos) {
	            geos.setBVH();
	        }
	    }
	    return;
	}

}



