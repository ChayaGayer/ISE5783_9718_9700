package primitives;

///class for the vector

public class Vector extends Point
{
	private Point head;
/**
 * constructor that get three double numbers 
 * @param d1
 * @param d2
 * @param d3
 */
public Vector(double d1,double d2,double d3)
{
		
	  super(d1,d2,d3);
	  if(this.xyz.equals(Double3.ZERO))
	  throw new IllegalArgumentException("isn't lagal-Vector 0");
		
	}
/**
 * constructor that get an object of Double3
 * @param double3
 */
public Vector(Double3 xyz) {
        super(xyz);
        if(this.xyz.equals( Double3.ZERO))
			throw new IllegalArgumentException("Vector coudlnt be 0");

    }

@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Vector vector)) return false;
    return xyz.equals(vector.xyz);
}
/**
 *vector connection and return the new vector 
 */
public Vector add(Vector v)
{
	 return new Vector((super.add(v)).xyz);
}
public String toString()
{
	return "Vector="+super.toString();
}
/**
 * vector multiplication in scalar
 * @param d
 * @return
 */
public Vector scale(double d)
{
	return new Vector(super.xyz.scale(d));
	
}
/**
 * scalar multiplication
 * @param vec
 * @return the dotProdcut between two vectors
 */
public double dotProduct(Vector vec)
{
	return this.xyz.d1*vec.xyz.d1+
			this.xyz.d2*vec.xyz.d2+
			this.xyz.d3*vec.xyz.d3;

}

  

/**
 * vector multiplication 
 *   @param vector
 * @return returns a new vector that stands for two existing vectors
 */
public Vector crossProduct(Vector vec)
{
	double x= this.xyz.d2 * vec.xyz.d3 -this.xyz.d3 *vec.xyz.d2;
	double y=(-1)*(this.xyz.d1*vec.xyz.d3 -this.xyz.d3 *vec.xyz.d1);
	double z=this.xyz.d1 *vec.xyz.d2-this.xyz.d2*vec.xyz.d1;
	return new Vector(x,y,z);
}
/**
 * calculate the  length of the Vector
 * @return the length
 */
public double lengthSquared()
{
	
	double d1=xyz.d1;
	double d2=xyz.d2;
	double d3=xyz.d3;
	return d1*d1+d2*d2+d3*d3;
}
public double length()
{
	return (Math.sqrt(lengthSquared()));
}
/**
 * normalizes a vector 
 * @return normal vector
 */
public Vector normalize()
{
	double d1=xyz.d1;
	double d2=xyz.d2;
	double d3=xyz.d3;
	double vec1=d1/this.length();
	double vec2=d2/this.length();
	double vec3=d3/this.length();
	return new Vector(vec1,vec2,vec3);
}
}

