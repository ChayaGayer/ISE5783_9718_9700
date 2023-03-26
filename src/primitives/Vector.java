package primitives;


public class Vector extends Point{

public Vector(double d1,double d2,double d3) {
		
	  super(d1,d2,d3);
	  if(this.xyz.equals(Double3.ZERO))
	throw new IllegalArgumentException("isn't lagal-Vector 0");
		// TODO Auto-generated constructor stub
	}
/**
 * ask about this ctor (super, throw)
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

public Vector add(Vector v)
{
	 return new Vector((super.add(v)).xyz);
}
public String toString()
{
	return "Vector="+super.toString();
}
public Vector scale(double d)
{
	return new Vector(super.xyz.scale(d));
	
}
/**
 * Vector product – returns a new vector that stands for two existing vectors 
 *   @param vector
 * @return returns a new vector that stands for two existing vectors
 */
public Vector crossProduct(Vector vec)
{
	//vec=2,this.xyz=1
	//double x = this.xyz.d2 * u.xyz.d3 - this.xyz.d3 * u.xyz.d2;
	double x= this.xyz.d2 * vec.xyz.d3 -this.xyz.d3 *vec.xyz.d2;
	double y=(-1)*(this.xyz.d1*vec.xyz.d3 -this.xyz.d3 *vec.xyz.d1);
	double z=this.xyz.d1 *vec.xyz.d2-this.xyz.d2*vec.xyz.d1;
	return new Vector(x,y,z);
}
/**
 * calculate the length of the Vector
 * @return the length
 */
public double lengthSquared()
{
	//Keep within variables the values of the vector
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
 * Function that normalizes a vector 
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
/**
 * function which calculate dotProdcut
 * @param vec
 * @return the dotProdcut between two vectors
 */
public double dotProduct(Vector vec)
{
	return this.xyz.d1*vec.xyz.d1+
			this.xyz.d2*vec.xyz.d2+
			this.xyz.d3*vec.xyz.d3;
}
}

  


