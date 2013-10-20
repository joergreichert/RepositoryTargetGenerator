package de.abg.jreichert.repositorytarget.activeannotations;

import java.util.Collection;
import java.util.List;

//copied and adapted from https://github.com/sculptor/base/blob/master/sculptor-framework/sculptor-framework-main/src/main/java/org/fornax/cartridges/sculptor/framework/accessapi/ConditionalCriteria.java
public class ConditionalCriteria {

	Operator operator;
	String propertyName;
	String propertyAlias;
	String[] propertyPath;
	String propertyFullName;
	Object firstOperant;
	Object secondOperant;
	IPropertyLiteral<?> property;

	private ConditionalCriteria(Operator operator, IPropertyLiteral<?> property) {
		this(operator, property, null, null);
	}

	private ConditionalCriteria(Operator operator,
			IPropertyLiteral<?> property, Object firstOperant) {
		this(operator, property, firstOperant, null);
	}

	private ConditionalCriteria(Operator operator,
			IPropertyLiteral<?> property, Object firstOperant,
			Object secondOperant) {
		this.property = property;
		this.operator = operator;
		this.firstOperant = firstOperant;
		this.secondOperant = secondOperant;

		if (property == null) {
			this.propertyFullName = null;
			this.propertyName = null;
			this.propertyPath = new String[0];
		} else {
			this.propertyFullName = property instanceof PropertyLiteral<?> ? ((PropertyLiteral<?>) property)
					.getEmbeddedName() : property.getName();

			int lastDotPos = propertyFullName.lastIndexOf('.');
			if (lastDotPos == -1) {
				this.propertyName = propertyFullName;
				this.propertyPath = new String[0];
			} else {
				this.propertyName = propertyFullName.substring(lastDotPos + 1);
				this.propertyPath = propertyFullName.substring(0, lastDotPos)
						.split("\\.");
			}
		}
	}

	public Operator getOperator() {
		return operator;
	}

	public String getPropertyFullName() {
		return propertyFullName == null ? null : propertyFullName.replaceAll(
				"#", ".");
	}

	public String getPropertyName() {
		return propertyName == null ? null : propertyName.replaceAll("#", ".");
	}

	public String[] getPropertyPath() {
		return propertyPath;
	}

	public String getPropertyAlias() {
		return propertyAlias;
	}

	public Object getFirstOperant() {
		return firstOperant;
	}

	public <Y extends Comparable<?>> Y getFirstOperantAs(Class<Y> type) {
		return type.cast(firstOperant);
	}

	public Object getSecondOperant() {
		return secondOperant;
	}

	public <Y extends Comparable<?>> Y getSecondOperantAs(Class<Y> type) {
		return type.cast(secondOperant);
	}

	public ConditionalCriteria withProperty(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(this.operator, property, firstOperant,
				secondOperant);
	}

	public static ConditionalCriteria equal(IPropertyLiteral<?> property,
			Object value) {
		return new ConditionalCriteria(Operator.Equal, property, value);
	}

	public static ConditionalCriteria ignoreCaseEqual(
			IPropertyLiteral<?> property, Object value) {
		return new ConditionalCriteria(Operator.IgnoreCaseEqual, property,
				value);
	}

	public static ConditionalCriteria lessThan(IPropertyLiteral<?> property,
			Object value) {
		return new ConditionalCriteria(Operator.LessThan, property, value);
	}

	public static ConditionalCriteria lessThanOrEqual(
			IPropertyLiteral<?> property, Object value) {
		return new ConditionalCriteria(Operator.LessThanOrEqual, property,
				value);
	}

	public static ConditionalCriteria greatThan(IPropertyLiteral<?> property,
			Object value) {
		return new ConditionalCriteria(Operator.GreatThan, property, value);
	}

	public static ConditionalCriteria greatThanOrEqual(
			IPropertyLiteral<?> property, Object value) {
		return new ConditionalCriteria(Operator.GreatThanOrEqual, property,
				value);
	}

	public static ConditionalCriteria like(IPropertyLiteral<?> property,
			Object value) {
		return new ConditionalCriteria(Operator.Like, property, value);
	}

	public static ConditionalCriteria ignoreCaseLike(
			IPropertyLiteral<?> property, Object value) {
		return new ConditionalCriteria(Operator.IgnoreCaseLike, property, value);
	}

	public static ConditionalCriteria between(IPropertyLiteral<?> property,
			Object lowRange, Object hightRange) {
		return new ConditionalCriteria(Operator.Between, property, lowRange,
				hightRange);
	}

	public static ConditionalCriteria in(IPropertyLiteral<?> property,
			Object... itemList) {
		return new ConditionalCriteria(Operator.In, property, itemList);
	}

	public static ConditionalCriteria in(IPropertyLiteral<?> property,
			Collection<?> itemList) {
		return new ConditionalCriteria(Operator.In, property, itemList);
	}

	public static ConditionalCriteria isNull(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.IsNull, property);
	}

	public static ConditionalCriteria isNotNull(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.IsNotNull, property);
	}

	public static ConditionalCriteria isEmpty(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.IsEmpty, property);
	}

	public static ConditionalCriteria isNotEmpty(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.IsNotEmpty, property);
	}

	public static ConditionalCriteria not(ConditionalCriteria criteria) {
		return new ConditionalCriteria(Operator.Not, null, criteria);
	}

	public static ConditionalCriteria or(ConditionalCriteria criteriaLeft,
			ConditionalCriteria criteriaRight) {
		return new ConditionalCriteria(Operator.Or, null, criteriaLeft,
				criteriaRight);
	}

	public static ConditionalCriteria or(List<ConditionalCriteria> orCriteria) {
		return new ConditionalCriteria(Operator.Or, null, orCriteria);
	}

	public static ConditionalCriteria and(ConditionalCriteria criteriaLeft,
			ConditionalCriteria criteriaRight) {
		return new ConditionalCriteria(Operator.And, null, criteriaLeft,
				criteriaRight);
	}

	public static ConditionalCriteria and(List<ConditionalCriteria> andCriteria) {
		return new ConditionalCriteria(Operator.And, null, andCriteria);
	}

	// Property comparators
	public static ConditionalCriteria equalProperty(
			IPropertyLiteral<?> propertyLeft, IPropertyLiteral<?> propertyRight) {
		return new ConditionalCriteria(Operator.EqualProperty, propertyLeft,
				propertyRight.getName());
	}

	public static ConditionalCriteria lessThanProperty(
			IPropertyLiteral<?> propertyLeft, IPropertyLiteral<?> propertyRight) {
		return new ConditionalCriteria(Operator.LessThanProperty, propertyLeft,
				propertyRight.getName());
	}

	public static ConditionalCriteria lessThanOrEqualProperty(
			IPropertyLiteral<?> propertyLeft, IPropertyLiteral<?> propertyRight) {
		return new ConditionalCriteria(Operator.LessThanOrEqualProperty,
				propertyLeft, propertyRight.getName());
	}

	public static ConditionalCriteria greatThanProperty(
			IPropertyLiteral<?> propertyLeft, IPropertyLiteral<?> propertyRight) {
		return new ConditionalCriteria(Operator.GreatThanProperty,
				propertyLeft, propertyRight.getName());
	}

	public static ConditionalCriteria greatThanOrEqualProperty(
			IPropertyLiteral<?> propertyLeft, IPropertyLiteral<?> propertyRight) {
		return new ConditionalCriteria(Operator.GreatThanOrEqualProperty,
				propertyLeft, propertyRight.getName());
	}

	public static ConditionalCriteria orderAsc(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.OrderAsc, property);
	}

	public static ConditionalCriteria orderDesc(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.OrderDesc, property);
	}

	public static ConditionalCriteria fetchEager(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.FetchEager, property);
	}

	public static ConditionalCriteria fetchLazy(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.FetchLazy, property);
	}

	public static ConditionalCriteria distinctRoot() {
		return new ConditionalCriteria(Operator.DistinctRoot, null);
	}

	public static ConditionalCriteria projectionRoot() {
		return new ConditionalCriteria(Operator.ProjectionRoot, null);
	}

	public static ConditionalCriteria groupBy(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.GroupBy, property);
	}

	public static ConditionalCriteria select(IPropertyLiteral<?> property) {
		return new ConditionalCriteria(Operator.Select, property);
	}

	public enum Operator {
		Equal, LessThan, LessThanOrEqual, GreatThan, GreatThanOrEqual, Like, IgnoreCaseLike, IgnoreCaseEqual, IsNull, IsNotNull, IsEmpty, IsNotEmpty, Not, Or, And, In, Between, EqualProperty, LessThanProperty, LessThanOrEqualProperty, GreatThanProperty, GreatThanOrEqualProperty, OrderAsc, OrderDesc, DistinctRoot, ProjectionRoot, FetchLazy, FetchEager, GroupBy, Select, Min, Max, Avg, Sum, SumAsLong, SumAsDouble, Count, CountDistinct
	}
}
