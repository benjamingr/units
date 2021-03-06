package units_generator.schema_reader;

import java.util.List;
import java.util.stream.Collectors;

import units_generator.internal.NamesManipulator;
import units_generator.schema_reader.exceptions.*;
import units_schema.Schema;
import units_schema.TestCase;
import units_schema.TestSuite;
import units_schema.UnitType;
import units_schema.UnitScale;

public class SchemaValidator {

	public static void validateSchema(
			Schema schema) throws InvalidSchema {
		for (UnitType unitType : schema.getUnitTypes()) {
			int thisTypeIndex = schema.getUnitTypes().indexOf(unitType) + 1;
			validateUnitType(thisTypeIndex, unitType);
			validateUnitTypeCount(schema, NamesManipulator.getName(unitType));
			validateUnitTypeTestSuite(schema, unitType);
		}
		for (TestSuite testSuite : schema.getTests().getTestSuites()) {
			validateTestSuite(testSuite, schema);
		}
	}
	
	public static void validateUnitType(
			int thisTypeIndex,
			UnitType unitType) throws InvalidSchema {
		String typeName = NamesManipulator.getName(unitType);
		validateUnitTypeName(typeName, thisTypeIndex);
		for (UnitScale scale : unitType.getUnitScales()) {
			int thisScaleIndex = unitType.getUnitScales().indexOf(scale) + 1;
			validateUnitScalePrintNames(scale, thisScaleIndex, typeName);
			String scaleName = NamesManipulator.getName(scale);
			validateUnitScaleName(scaleName, thisScaleIndex, typeName);
			validateUnitScaleCount(scaleName, unitType);
			validateUnitScale(scaleName, scale);
		}
	}

	private static void validateUnitTypeName(
			String name,
			int thisTypeIndex) throws InvalidSchema {
		if(name == null) {
			throw new InvalidUnitTypeName(thisTypeIndex);
		}
	}

	public static void validateUnitScalePrintNames(
			UnitScale scale,
			int index,
			String typeName) throws InvalidSchema {
		if (!NamesManipulator.isValidString(scale.getPluralName()))
			throw new InvalidPluralName(index, typeName);
		if (!NamesManipulator.isValidString(scale.getSingularName()))
			throw new InvalidSingularName(index, typeName);
	}
	
	private static void validateUnitScaleName(
			String scaleName,
			int thisScaleIndex,
			String typeName) throws InvalidSchema {
		if (scaleName == null) {
			throw new InvalidUnitScaleName(thisScaleIndex, typeName);
		}
	}
	
	private static void validateUnitScaleCount(
			String scaleName,
			UnitType unitType) throws InvalidSchema {
		long unitScaleCount = unitType.getUnitScales().stream()
				.filter((someUnitScale) -> scaleName.equals(NamesManipulator.getName(someUnitScale)))
				.count();
		if (unitScaleCount >= 2)
			throw new InvalidUnitScaleCount(
					scaleName,
					NamesManipulator.getName(unitType),
					unitScaleCount);
	}
	
	public static void validateUnitScale(
			String name,
			UnitScale scale) throws InvalidSchema {
		boolean isBasic = scale.getIsBasic();
		boolean isRatio = scale.getRatio() != null;
		boolean isStringMultiplyer = 
				NamesManipulator.isValidName(scale.getRelativeTo()) &&
				NamesManipulator.isValidName(scale.getMultiplyerString());
		boolean isNumberMultiplyer = 
				NamesManipulator.isValidName(scale.getRelativeTo()) &&
				scale.getMultiplyerNumber() != null;
		if (!BooleanChecker.exactlyOne(
				isBasic,
				isRatio,
				isStringMultiplyer,
				isNumberMultiplyer)) {
			throw new InvalidScaleDefinition(name);
		}
	}

	private static void validateUnitTypeCount(
			Schema schema,
			String name) throws InvalidSchema {
		long unitTypeCount = schema.getUnitTypes().stream()
				.filter((someUnitType) -> name.equals(NamesManipulator.getName(someUnitType)))
				.count();
		if (unitTypeCount >= 2)
			throw new InvalidUnitTypeCount(name, unitTypeCount);
	}

	private static void validateUnitTypeTestSuite(
			Schema schema,
			UnitType unitType) throws InvalidSchema {
		String typeName = NamesManipulator.getName(unitType);
		TestSuite testSuite = getTestSuite(schema, typeName);
		validateTestSuiteExistance(testSuite, typeName);
		for (int i=1; i < unitType.getUnitScales().size(); ++i) {
			for (int j=0; j < i; ++j) {
				String unitScale1 = NamesManipulator.getName(unitType.getUnitScales().get(i));
				String unitScale2 = NamesManipulator.getName(unitType.getUnitScales().get(j));
				validateTestCaseExistance(unitScale1, unitScale2, testSuite);
				validateTestCaseExistance(unitScale2, unitScale1, testSuite);
			}
		}
	}
	
	private static TestSuite getTestSuite(
			Schema schema,
			String typeName) {
		List<TestSuite> unitTypeTestSuites = schema.getTests().getTestSuites().stream()
				.filter((testSuite) -> typeName.equals(testSuite.getUnitType()))
				.collect(Collectors.toList());
		if (unitTypeTestSuites.size() != 1)
			return null;
		return unitTypeTestSuites.get(0);
	}
	
	private static void validateTestSuiteExistance(
			TestSuite testSuite,
			String typeName) throws InvalidSchema {
		if (testSuite == null)
			throw new InvalidTestSuite(typeName);
	}

	private static void validateTestCaseExistance(
			String from,
			String to,
			TestSuite testSuite) throws InvalidSchema {
		long testCaseCount = testSuite.getTestCases().stream()
			.filter((testCase) ->
				from.equals(testCase.getFrom()) &&
				to.equals(testCase.getTo()))
			.count();
		if (testCaseCount != 1) {
			throw new InvalidConversionTest(testCaseCount, from, to);
		}
	}
	
	private static UnitType getUnitType(Schema schema, TestSuite testSuite) {
		List<UnitType> filteredUnitTypes = schema.getUnitTypes().stream()
				.filter((unitType) -> NamesManipulator.getName(unitType).equals(testSuite.getUnitType()))
				.collect(Collectors.toList());
		if (filteredUnitTypes.size() != 1)
			return null;
		return filteredUnitTypes.get(0);
	}

	private static void validateTestSuite(
			TestSuite testSuite,
			Schema schema) throws InvalidSchema {
		UnitType unitType = getUnitType(schema, testSuite);
		if (unitType == null)
			throw new InvalidUnitTypeInTestSuite(
					schema.getTests().getTestSuites().indexOf(testSuite),
					testSuite.getUnitType());
		for (TestCase testCase : testSuite.getTestCases()) {
			validateTestCase(testCase, unitType);
		}
	}
	
	private static void validateTestCase(
			TestCase testCase,
			UnitType unitType) throws InvalidSchema {
		validateTestCaseUnitScaleExitance(testCase.getFrom(), unitType);
		validateTestCaseUnitScaleExitance(testCase.getTo(), unitType);
	}
	
	private static void validateTestCaseUnitScaleExitance(
			String unitScaleName,
			UnitType unitType) throws InvalidSchema {
		long unitScaleCount = unitType.getUnitScales().stream()
				.filter((someUnitScale) -> NamesManipulator.getName(someUnitScale).equals(unitScaleName))
				.count();
		if (unitScaleCount != 1) {
			throw new InvalidTestCaseUnitScaleName(unitScaleCount, unitScaleName);
		}
	}
}
