/* This file is auto generated.
 * DO NOT EDIT IT MANUALY!
 */
package com.units.mass;

import com.units.internal.NumericValue;
import com.units.internal.Multiplyers;


public class Tonnes extends NumericValue implements Mass{

	public static final double _scale = Multiplyers.mega * Grams._scale;
	
	public Tonnes(double value) {
		super(value);
	}

	@Override
	public String pluralName() {
		return "tonnes";
	}
	
	@Override
	public double scale() {
		return _scale;
	}
	
	public static Tonnes zero() {
		return new Tonnes(0);
	}
	
	public static Tonnes one() {
		return new Tonnes(1);
	}
	
	public Tonnes plus(Tonnes other) {
		return new Tonnes(value() + other.value());
	}
	
	public Tonnes minus(Tonnes other) {
		return new Tonnes(value() - other.value());
	}
	
	public Tonnes opposite() {
		return new Tonnes(-value());
	}

	public Tonnes multiplyByScalar(double scalar) {
		return new Tonnes(scalar * value());
	}
	
	public Tonnes divideByScalar(double scalar) {
		return new Tonnes(value() / scalar);
	}
	
	public boolean equals(Tonnes other) {
		if (other == null)
			return false;
		return almostEqualsValue(other.value());
	}
	
	public static Tonnes castFrom(Mass other) {
		return new Tonnes(other.value() * other.scale() / _scale);
	}
}