package de.abg.jreichert.repositorytarget.activeannotations

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class EntityLiteralTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(EntityLiteral)

	@Test def void testEntityLiteral() {
		'''
			@de.abg.jreichert.repositorytarget.activeannotations.EntityLiteral
			class MyClass {
				String fieldWithoutAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				String fieldWithAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				MyClass2 fieldWithAnnotationAndEntityType;
			}
			
			@de.abg.jreichert.repositorytarget.activeannotations.EntityLiteral
			class MyClass2 {
				String fieldWithoutAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				String fieldWithAnnotation;
			}

			class MyClass3 {
				String fieldWithoutAnnotation;
				@de.abg.jreichert.repositorytarget.activeannotations.Property
				String fieldWithAnnotation;
			}
		'''.assertCompilesTo(
			'''
				MULTIPLE FILES WERE GENERATED
				
				File 1 : /myProject/xtend-gen/MyClass.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.EntityLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.Property;
				
				@EntityLiteral
				@SuppressWarnings("all")
				public class MyClass {
				  private String fieldWithoutAnnotation;
				  
				  @Property
				  private String fieldWithAnnotation;
				  
				  @Property
				  private MyClass2 fieldWithAnnotationAndEntityType;
				  
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
				
				File 2 : /myProject/xtend-gen/MyClass2.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.EntityLiteral;
				import de.abg.jreichert.repositorytarget.activeannotations.Property;
				
				@EntityLiteral
				@SuppressWarnings("all")
				public class MyClass2 {
				  private String fieldWithoutAnnotation;
				  
				  @Property
				  private String fieldWithAnnotation;
				  
				  public String getFieldWithAnnotation() {
				    return this.fieldWithAnnotation;
				  }
				  
				  public void setFieldWithAnnotation(final String fieldWithAnnotation) {
				    this.fieldWithAnnotation = fieldWithAnnotation;
				  }
				}
				
				File 3 : /myProject/xtend-gen/MyClass2Literal.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClass2Literal<T> extends MyClass2LiteralImpl<T> implements Property<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public MyClass2Literal(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 4 : /myProject/xtend-gen/MyClass2LiteralImpl.java
				
				import org.sculptor.framework.domain.LeafProperty;
				import org.sculptor.framework.domain.PropertiesCollection;
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClass2LiteralImpl<T> extends PropertiesCollection {
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
				  
				  public Property<T> fieldWithAnnotation() {
				    return new LeafProperty<T>(getParentPath(), "fieldWithAnnotation", false, owningClass);
				  }
				}
				
				File 5 : /myProject/xtend-gen/MyClass2Literals.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClass2Literals {
				  private final static MyClass2LiteralImpl<MyClass2> sharedInstance = new MyClass2LiteralImpl<MyClass2>(MyClass2.class);;
				  
				  private MyClass2Literals() {
				    
				  }
				  
				  public static Property<MyClass2> fieldWithAnnotation() {
				    return sharedInstance.fieldWithAnnotation();
				  }
				}
				
				File 6 : /myProject/xtend-gen/MyClass3.java
				
				import de.abg.jreichert.repositorytarget.activeannotations.Property;
				
				@SuppressWarnings("all")
				public class MyClass3 {
				  private String fieldWithoutAnnotation;
				  
				  @Property
				  private String fieldWithAnnotation;
				  
				  public String getFieldWithAnnotation() {
				    return this.fieldWithAnnotation;
				  }
				  
				  public void setFieldWithAnnotation(final String fieldWithAnnotation) {
				    this.fieldWithAnnotation = fieldWithAnnotation;
				  }
				}
				
				File 7 : /myProject/xtend-gen/MyClassLiteral.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClassLiteral<T> extends MyClassLiteralImpl<T> implements Property<T> {
				  private final static long serialVersionUID = 1L;
				  
				  public MyClassLiteral(final String parentPath, final String additionalPath, final Class<T> owningClass) {
				    super(parentPath, additionalPath, owningClass);
				  }
				}
				
				File 8 : /myProject/xtend-gen/MyClassLiteralImpl.java
				
				import org.sculptor.framework.domain.LeafProperty;
				import org.sculptor.framework.domain.PropertiesCollection;
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClassLiteralImpl<T> extends PropertiesCollection {
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
				  
				  public Property<T> fieldWithAnnotation() {
				    return new LeafProperty<T>(getParentPath(), "fieldWithAnnotation", false, owningClass);
				  }
				  
				  public MyClass2Literal<T> fieldWithAnnotationAndEntityType() {
				    return new MyClass2Literal<T>(getParentPath(), "fieldWithAnnotationAndEntityType", owningClass);
				  }
				}
				
				File 9 : /myProject/xtend-gen/MyClassLiterals.java
				
				import org.sculptor.framework.domain.Property;
				
				@SuppressWarnings("all")
				public class MyClassLiterals {
				  private final static MyClassLiteralImpl<MyClass> sharedInstance = new MyClassLiteralImpl<MyClass>(MyClass.class);;
				  
				  private MyClassLiterals() {
				    
				  }
				  
				  public static Property<MyClass> fieldWithAnnotation() {
				    return sharedInstance.fieldWithAnnotation();
				  }
				  
				  public static MyClass2Literal<MyClass> fieldWithAnnotationAndEntityType() {
				    return sharedInstance.fieldWithAnnotationAndEntityType();
				  }
				}

			''')
	}
}
