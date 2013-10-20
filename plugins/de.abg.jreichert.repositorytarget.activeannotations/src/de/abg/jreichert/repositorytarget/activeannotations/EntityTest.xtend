package de.abg.jreichert.repositorytarget.activeannotations

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class EntityTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(Entity)

	@Test def void testEntity() {
		'''
			@de.abg.jreichert.repositorytarget.activeannotations.Entity
			class MyClass {
				String fieldWithoutAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				String fieldWithAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				MyClass2 fieldWithAnnotationAndEntityType;
			}
			
			@de.abg.jreichert.repositorytarget.activeannotations.Entity
			class MyClass2 {
				String fieldWithoutAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				String fieldWithAnnotation;
			}
		'''.assertCompilesTo(
			'''
				MULTIPLE FILES WERE GENERATED
				
				File 1 : MyClass.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.Entity;
				import de.abg.jreichert.repositorytarget.activeannotations.Property;
				import javax.persistence.Id;
				
				@Entity
				@javax.persistence.Entity
				@SuppressWarnings("all")
				public class MyClass {
				  private String fieldWithoutAnnotation;
				  
				  @Property
				  private String fieldWithAnnotation;
				  
				  @Property
				  private MyClass2 fieldWithAnnotationAndEntityType;
				  
				  @Id
				  private Long id = -1L;
				  
				  public Long getId() {
				    return this.id;
				  }
				  
				  public void setId(final Long id) {
				    this.id = id;
				  }
				  
				  public String getFieldWithAnnotation() {
				    return this.fieldWithAnnotation;
				  }
				  
				  public void setFieldWithAnnotation(final String fieldWithAnnotation) {
				    this.fieldWithAnnotation = fieldWithAnnotation;
				  }
				  
				  public MyClass2 getFieldWithAnnotationAndEntityType() {
				    return this.fieldWithAnnotationAndEntityType;
				  }
				  
				  public void setFieldWithAnnotationAndEntityType(final MyClass2 fieldWithAnnotationAndEntityType) {
				    this.fieldWithAnnotationAndEntityType = fieldWithAnnotationAndEntityType;
				  }
				}
				
				File 2 : MyClass2.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.Entity;
				import de.abg.jreichert.repositorytarget.activeannotations.Property;
				import javax.persistence.Id;
				
				@Entity
				@javax.persistence.Entity
				@SuppressWarnings("all")
				public class MyClass2 {
				  private String fieldWithoutAnnotation;
				  
				  @Property
				  private String fieldWithAnnotation;
				  
				  @Id
				  private Long id = -1L;
				  
				  public Long getId() {
				    return this.id;
				  }
				  
				  public void setId(final Long id) {
				    this.id = id;
				  }
				  
				  public String getFieldWithAnnotation() {
				    return this.fieldWithAnnotation;
				  }
				  
				  public void setFieldWithAnnotation(final String fieldWithAnnotation) {
				    this.fieldWithAnnotation = fieldWithAnnotation;
				  }
				}
				
				File 3 : MyClass2Literal.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClass2Literal<T> extends MyClass2LiteralImpl<T> implements IPropertyLiteral<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public MyClass2Literal(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 4 : MyClass2LiteralImpl.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.PropertiesCollectionLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.PropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClass2LiteralImpl<T> extends PropertiesCollectionLiteral {
				  private final static long serialVersionUID = 1L;
				  
				  private Class<T> owningClass;
				  
				  public MyClass2LiteralImpl(final Class<T> owningClass) {
				    super(null);
				    this.owningClass = owningClass;
				  }
				  
				  public MyClass2LiteralImpl(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath);
				    this.owningClass = owningClass;
				  }
				  
				  public IPropertyLiteral<T> fieldWithAnnotation() {
				    return new PropertyLiteral<T>(getParentPath(), "fieldWithAnnotation", false, owningClass);
				  }
				  
				  public IPropertyLiteral<T> id() {
				    return new PropertyLiteral<T>(getParentPath(), "id", false, owningClass);
				  }
				}
				
				File 5 : MyClass2Literals.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClass2Literals {
				  private final static MyClass2LiteralImpl<MyClass2> sharedInstance = new MyClass2LiteralImpl<MyClass2>(MyClass2.class);;
				  
				  private MyClass2Literals() {
				    
				  }
				  
				  public static IPropertyLiteral<MyClass2> fieldWithAnnotation() {
				    return sharedInstance.fieldWithAnnotation();
				  }
				  
				  public static IPropertyLiteral<MyClass2> id() {
				    return sharedInstance.id();
				  }
				}
				
				File 6 : MyClassLiteral.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClassLiteral<T> extends MyClassLiteralImpl<T> implements IPropertyLiteral<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public MyClassLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 7 : MyClassLiteralImpl.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.PropertiesCollectionLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.PropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClassLiteralImpl<T> extends PropertiesCollectionLiteral {
				  private final static long serialVersionUID = 1L;
				  
				  private Class<T> owningClass;
				  
				  public MyClassLiteralImpl(final Class<T> owningClass) {
				    super(null);
				    this.owningClass = owningClass;
				  }
				  
				  public MyClassLiteralImpl(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath);
				    this.owningClass = owningClass;
				  }
				  
				  public IPropertyLiteral<T> fieldWithAnnotation() {
				    return new PropertyLiteral<T>(getParentPath(), "fieldWithAnnotation", false, owningClass);
				  }
				  
				  public MyClass2Literal<T> fieldWithAnnotationAndEntityType() {
				    return new MyClass2Literal<T>(getParentPath(), "fieldWithAnnotationAndEntityType", owningClass);
				  }
				  
				  public IPropertyLiteral<T> id() {
				    return new PropertyLiteral<T>(getParentPath(), "id", false, owningClass);
				  }
				}
				
				File 8 : MyClassLiterals.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.IPropertyLiteral;
				
				@SuppressWarnings("all")
				public class MyClassLiterals {
				  private final static MyClassLiteralImpl<MyClass> sharedInstance = new MyClassLiteralImpl<MyClass>(MyClass.class);;
				  
				  private MyClassLiterals() {
				    
				  }
				  
				  public static IPropertyLiteral<MyClass> fieldWithAnnotation() {
				    return sharedInstance.fieldWithAnnotation();
				  }
				  
				  public static MyClass2Literal<MyClass> fieldWithAnnotationAndEntityType() {
				    return sharedInstance.fieldWithAnnotationAndEntityType();
				  }
				  
				  public static IPropertyLiteral<MyClass> id() {
				    return sharedInstance.id();
				  }
				}

			''')
	}
}
