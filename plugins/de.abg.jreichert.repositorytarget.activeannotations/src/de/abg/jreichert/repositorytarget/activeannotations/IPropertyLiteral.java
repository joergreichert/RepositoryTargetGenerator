package de.abg.jreichert.repositorytarget.activeannotations;

import java.io.Serializable;

// copied and adapted from https://github.com/sculptor/base/blob/master/sculptor-framework/sculptor-framework-main/src/main/java/org/fornax/cartridges/sculptor/framework/domain/Property.java
public interface IPropertyLiteral<T> extends Serializable {

    String getName();
}
