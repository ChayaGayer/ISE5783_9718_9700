package primitives;

public class Vector extends Point
{
	public Vector(double d1, double d2, double d3)
	{
		super(d1,d2,d3);
		if(this.equals( Double3.ZERO))
			throw new IllegalArgumentException("the argument isn't lagal");
	}
	public Vector(Double3 xyz) 
	{
		super(xyz);
		if(this.equals( Double3.ZERO))
			throw new IllegalArgumentException("the argument isn't lagal");
	}
	@Override
	public String toString()
	{
		return "Vector- " + super.toString();
	}
	public Vector add(Vector other)
	{
		return new Vector((super.add(other)).xyz);
	}
	public Vector scale(int num)
	{
		return new Vector((super.xyz.scale(num)));
	}
	public double dotProduct (Vector other)
	{
		/* יש כאן מלא בדיקות לעשות על הוקטורים*/
		Vector tmp= new Vector(this.xyz.product(other.xyz));
		return tmp.xyz.d1+tmp.xyz.d2+tmp.xyz.d3;
	}
	public Vector crossProduct (Vector other)
	{
		/* יש כאן מלא בדיקות לעשות על הוקטורים*/
		return new Vector(this.xyz.d2*other.xyz.d3-this.xyz.d3*other.xyz.d2,this.xyz.d3*other.xyz.d1-this.xyz.d1*other.xyz.d3,this.xyz.d1*other.xyz.d2-this.xyz.d2*other.xyz.d1);
	}
	public double lengthSquared ()
	{
		return super.DistanceSquared(Double3.ZERO);
	}
	public double length()
	{
		return Math.sqrt(this.lengthSquared());
	}
	public Vector normalize()
	{
		//double t=this.length();
		return new Vector(this.xyz.d1/this.length(),this.xyz.d2/this.length(),this.xyz.d3/this.length());
	}
}
	
	
	
	
	
	
	
	
	
	
	
	