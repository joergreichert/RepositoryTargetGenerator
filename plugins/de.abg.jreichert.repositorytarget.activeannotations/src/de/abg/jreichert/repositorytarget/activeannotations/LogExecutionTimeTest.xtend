package de.abg.jreichert.repositorytarget.activeannotations

import org.eclipse.xtend.core.compiler.batch.XtendCompilerTester
import org.junit.Test

class LogExecutionTimeTest {

	extension XtendCompilerTester compilerTester = XtendCompilerTester.newXtendCompilerTester(LogExecutionTime)

	@Test def void testAnnotationAtSimpleMethod() {
		'''
			class MyClass {
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
				  @LogExecutionTime
				  private void simple__Measured() {
				    InputOutput.<String>println("simple");
				  }
				  
				  public void simple() {
				    long start = System.currentTimeMillis();
				    simple__Measured();
				    logDuration(start, "simple()");
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("sss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " s");
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
				  private void simple__Measured(final String text) {
				    InputOutput.<String>println(text);
				  }
				  
				  public void simple(final String text) {
				    long start = System.currentTimeMillis();
				    simple__Measured(text);
				    logDuration(start, "simple(text = " + text + ")");
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("sss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " s");
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
				  private String simple__Measured(final String text) {
				    InputOutput.<String>println(text);
				    return text;
				  }
				  
				  public String simple(final String text) {
				    long start = System.currentTimeMillis();
				    String result = simple__Measured(text);
				    logDuration(start, "simple(text = " + text + ")");
				    return result;
				  }
				  
				  private static void logDuration(final long start, final String message) {
				    long end = System.currentTimeMillis();
				    SimpleDateFormat df = new SimpleDateFormat("sss.SSS");
				    Calendar cal = Calendar.getInstance();
				    cal.setTimeInMillis(end - start);
				    String timeStr = df.format(cal.getTime());
				    System.err.println(message + ": " + timeStr + " s");
				  }
				}
			''')
	}
}
