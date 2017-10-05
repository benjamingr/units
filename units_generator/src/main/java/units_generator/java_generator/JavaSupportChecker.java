package units_generator.java_generator;

import units_generator.internal.LanguageSupportChecker;
import units_schema.UnitScale;
import units_schema.UnitType;

public class JavaSupportChecker implements LanguageSupportChecker{

	@Override
	public boolean isSupported(UnitScale unitScale) { 
		return unitScale.getRatio() == null;
	}
	
	@Override
	public boolean isSupported(UnitType unitType) { 
		return unitType.getRatio() == null;
	}
}
