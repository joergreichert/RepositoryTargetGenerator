package de.abg.jreichert.repositorytarget.activeannotations

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class LogExecutionTimeTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(LogExecutionTime)

	@Test def void testAnnotationAtSimpleMethod() {
		'''
			class MyClass {
				/**
				 * MyDoc
				 */
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				def void simple() {
					println("simple");
				}
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime;
				import java.text.SimpleDateFormat;
				import java.util.Calendar;
				import org.eclipse.xtext.xbase.lib.InputOutput;
				
				@SuppressWarnings("all")
				public class MyClass {
				  /**
				   * MyDoc
				   */
				  @LogExecutionTime
				  public void simple() {
				    long start = System.currentTimeMillis();
				    simple__Measured();
				    logDuration(start, "simple()");
				  }
				  
				  private void simple__Measured() {
				    InputOutput.<String>println("simple");
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " min");
				  }
				}
			''')
	}

	@Test def void testAnnotationAtMethodWithParameters() {
		'''
			class MyClass {
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				def void simple(String text) {
					println(text);
				}
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime;
				import java.text.SimpleDateFormat;
				import java.util.Calendar;
				import org.eclipse.xtext.xbase.lib.InputOutput;
				
				@SuppressWarnings("all")
				public class MyClass {
				  @LogExecutionTime
				  public void simple(final String text) {
				    long start = System.currentTimeMillis();
				    simple__Measured(text);
				    logDuration(start, "simple(text = " + text + ")");
				  }
				  
				  private void simple__Measured(final String text) {
				    InputOutput.<String>println(text);
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " min");
				  }
				}
			''')
	}

	@Test def void testAnnotationAtMethodWithReturnType() {
		'''
			class MyClass {
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				def String simple(String text) {
					println(text);
					return text;
				}
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime;
				import java.text.SimpleDateFormat;
				import java.util.Calendar;
				import org.eclipse.xtext.xbase.lib.InputOutput;
				
				@SuppressWarnings("all")
				public class MyClass {
				  @LogExecutionTime
				  public String simple(final String text) {
				    long start = System.currentTimeMillis();
				    String result = simple__Measured(text);
				    logDuration(start, "simple(text = " + text + ")");
				    return result;
				  }
				  
				  private String simple__Measured(final String text) {
				    InputOutput.<String>println(text);
				    return text;
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " min");
				  }
				}
			''')
	}

	@Test def void testAnnotationAtMethodWithAnnotation() {
		'''
			class MyClass {
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				@javax.annotation.Resource
				def String simple(String text) {
					println(text);
					return text;
				}
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime;
				import java.text.SimpleDateFormat;
				import java.util.Calendar;
				import javax.annotation.Resource;
				import org.eclipse.xtext.xbase.lib.InputOutput;
				
				@SuppressWarnings("all")
				public class MyClass {
				  @LogExecutionTime
				  @Resource
				  public String simple(final String text) {
				    long start = System.currentTimeMillis();
				    String result = simple__Measured(text);
				    logDuration(start, "simple(text = " + text + ")");
				    return result;
				  }
				  
				  private String simple__Measured(final String text) {
				    InputOutput.<String>println(text);
				    return text;
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " min");
				  }
				}
			''')
	}

	@Test def void testMultipleAnnotations() {
		'''
			class MyClass {
			
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				def void method1(String text) {
					println(text);
				}
			
				@de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime
				def void method2(String text) {
				println(text);
				}
			}
		'''.assertCompilesTo(
			'''
				import de.abg.jreichert.repositorytarget.activeannotations.LogExecutionTime;
				import java.text.SimpleDateFormat;
				import java.util.Calendar;
				import org.eclipse.xtext.xbase.lib.InputOutput;
				
				@SuppressWarnings("all")
				public class MyClass {
				  @LogExecutionTime
				  public void method1(final String text) {
				    long start = System.currentTimeMillis();
				    method1__Measured(text);
				    logDuration(start, "method1(text = " + text + ")");
				  }
				  
				  @LogExecutionTime
				  public void method2(final String text) {
				    long start = System.currentTimeMillis();
				    method2__Measured(text);
				    logDuration(start, "method2(text = " + text + ")");
				  }
				  
				  private void method1__Measured(final String text) {
				    InputOutput.<String>println(text);
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("mm:ss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " min");
				  }
				  
				  private void method2__Measured(final String text) {
				    InputOutput.<String>println(text);
				  }
				}
			''')
	}
}
