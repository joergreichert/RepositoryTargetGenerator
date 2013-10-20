package de.abg.jreichert.repositorytarget.activeannotations;

import java.io.Serializable;

// copied and adapted from https://github.com/sculptor/base/blob/master/sculptor-framework/sculptor-framework-main/src/main/java/org/fornax/cartridges/sculptor/framework/domain/PropertiesCollection.java
public class PropertiesCollectionLiteral implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String name;

    public PropertiesCollectionLiteral(String parentPath) {
        this.name = parentPath;
    }

    public PropertiesCollectionLiteral(String parentPath, String additionalPath) {
        this.name = (parentPath == null ? additionalPath
                : (parentPath + "." + additionalPath));
    }

    public String getName() {
        return name;
    }

    protected String getParentPath() {
        return name;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PropertiesCollectionLiteral other = (PropertiesCollectionLiteral) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }
}
