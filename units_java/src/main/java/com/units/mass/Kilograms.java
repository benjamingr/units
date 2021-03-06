/* This file is auto generated.
 * DO NOT EDIT IT MANUALY!
 */
package com.units.mass;

import com.units.Unit;
import com.units.internal.NumericValue;
import com.units.internal.Ratio;
import com.units.internal.Multiplyers;


public class Kilograms extends NumericValue implements Mass{

	public static final double _scale = Multiplyers.kilo * Grams._scale;
	
	public Kilograms(double value) {
		super(value);
	}

	@Override
	public String pluralName() {
		return "kilograms";
	}
	
	@Override
	public double scale() {
		return _scale;
	}
	
	public static Kilograms zero() {
		return new Kilograms(0);
	}
	
	public static Kilograms one() {
		return new Kilograms(1);
	}
	
	public Kilograms plus(Kilograms other) {
		return new Kilograms(value() + other.value());
	}
	
	public Kilograms minus(Kilograms other) {
		return new Kilograms(value() - other.value());
	}
	
	public Kilograms opposite() {
		return new Kilograms(-value());
	}

	public Kilograms multiplyByScalar(double scalar) {
		return new Kilograms(scalar * value());
	}
	
	public Kilograms divideByScalar(double scalar) {
		return new Kilograms(value() / scalar);
	}
	
	public boolean equals(Kilograms other) {
		if (other == null)
			return false;
		return almostEqualsValue(other.value());
	}
	
	private static Kilograms castFromScale(double value, double scale) {
		return new Kilograms(value * scale / _scale);
	}

	public static Kilograms castFrom(Mass other) {
		return castFromScale(other.value(), other.scale());
	}

	public static Kilograms divide(Unit unit1, Unit unit2) {
		Ratio resultTypeCode = unit1.typeCode().divide(unit2.typeCode());
		if (!resultTypeCode.equals(_typeCode))
			throw new IllegalArgumentException("Illigal division");
		return castFromScale(
				unit1.value() / unit2.value(),
				unit1.scale() / unit2.scale());
	}
}