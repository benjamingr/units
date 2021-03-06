package units_generator.java_generator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import units_generator.internal.NamesManipulator;
import units_generator.internal.UnitTypeInterface;
import units_generator.internal.UnitsSchemaInterface;
import units_generator.internal.UnitsTestSuiteInterface;
import units_schema.Schema;
import units_schema.TestSuite;
import units_schema.UnitType;

public class JavaUnitsSchema implements UnitsSchemaInterface {

	private List<UnitTypeInterface> unitTypes;
	private List<UnitsTestSuiteInterface> testSuites;
	
	private static JavaSupportChecker supportChecker = new JavaSupportChecker();
	
	public JavaUnitsSchema(Schema schema) {
		unitTypes = new ArrayList<>();
		testSuites = new ArrayList<>();
		for (UnitType unitType : schema.getUnitTypes()) {
			if (!supportChecker.isSupported(unitType))
				continue;
			unitTypes.add(new JavaUnitType(this, unitType));
			String unitTypeName = NamesManipulator.getName(unitType);
			TestSuite testSuite = schema.getTests().getTestSuites().stream()
					.filter((someTestSuite) -> someTestSuite.getUnitType().equals(unitTypeName))
					.collect(Collectors.toList()).get(0);
			testSuites.add(new JavaTestSuite(testSuite));
		}
	}
	
	@Override
	public List<UnitTypeInterface> getUnitTypes() {
		return unitTypes;
	}
	
	@Override
	public List<UnitsTestSuiteInterface> getTestSuites() {
		return testSuites;
	}
}
