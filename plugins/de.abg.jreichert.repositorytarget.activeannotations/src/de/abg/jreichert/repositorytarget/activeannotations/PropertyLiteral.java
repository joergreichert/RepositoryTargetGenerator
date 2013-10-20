package de.abg.jreichert.repositorytarget.activeannotations;

//copied and adapted from https://github.com/sculptor/base/blob/master/sculptor-framework/sculptor-framework-main/src/main/java/org/fornax/cartridges/sculptor/framework/domain/LeafProperty.java
public class PropertyLiteral<T> implements IPropertyLiteral<T> {
	private static final long serialVersionUID = 1L;

	private final String name;
	private Class<T> owningClass;

	public PropertyLiteral(String name, Class<T> owningClass) {
		this.name = name;
		this.owningClass = owningClass;
	}

	public PropertyLiteral(String parentPath, String name, boolean isEmbedded,
			Class<T> owningClass) {
		this.name = (parentPath == null ? name : (parentPath
				+ (isEmbedded ? "#" : ".") + name));
		this.owningClass = owningClass;
	}

	public String getName() {
		return name.replaceAll("#", ".");
	}

	public String getEmbeddedName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public int hashCode() {
		return owningClass.hashCode() + name.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || !(other instanceof PropertyLiteral<?>)) {
			return false;
		}

		String thisString = this.owningClass.getCanonicalName() + name;
		String otherString = ((PropertyLiteral<?>) other).owningClass
				.getCanonicalName() + ((PropertyLiteral<?>) other).name;
		return thisString.equals(otherString);
	}
}
