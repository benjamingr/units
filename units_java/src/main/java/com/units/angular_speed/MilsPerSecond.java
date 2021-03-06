/* This file is auto generated.
 * DO NOT EDIT IT MANUALY!
 */
package com.units.angular_speed;

import com.units.Unit;
import com.units.internal.NumericValue;
import com.units.internal.Ratio;
import com.units.angle.Mils;
import com.units.duration.Seconds;


public class MilsPerSecond extends NumericValue implements AngularSpeed{

	public static final double _scale = Mils._scale / Seconds._scale;
	
	public MilsPerSecond(double value) {
		super(value);
	}

	@Override
	public String pluralName() {
		return "mils/second";
	}
	
	@Override
	public double scale() {
		return _scale;
	}
	
	public static MilsPerSecond zero() {
		return new MilsPerSecond(0);
	}
	
	public static MilsPerSecond one() {
		return new MilsPerSecond(1);
	}
	
	public MilsPerSecond plus(MilsPerSecond other) {
		return new MilsPerSecond(value() + other.value());
	}
	
	public MilsPerSecond minus(MilsPerSecond other) {
		return new MilsPerSecond(value() - other.value());
	}
	
	public MilsPerSecond opposite() {
		return new MilsPerSecond(-value());
	}

	public MilsPerSecond multiplyByScalar(double scalar) {
		return new MilsPerSecond(scalar * value());
	}
	
	public MilsPerSecond divideByScalar(double scalar) {
		return new MilsPerSecond(value() / scalar);
	}
	
	public boolean equals(MilsPerSecond other) {
		if (other == null)
			return false;
		return almostEqualsValue(other.value());
	}
	
	private static MilsPerSecond castFromScale(double value, double scale) {
		return new MilsPerSecond(value * scale / _scale);
	}

	public static MilsPerSecond castFrom(AngularSpeed other) {
		return castFromScale(other.value(), other.scale());
	}

	public static MilsPerSecond divide(Unit unit1, Unit unit2) {
		Ratio resultTypeCode = unit1.typeCode().divide(unit2.typeCode());
		if (!resultTypeCode.equals(_typeCode))
			throw new IllegalArgumentException("Illigal division");
		return castFromScale(
				unit1.value() / unit2.value(),
				unit1.scale() / unit2.scale());
	}
}