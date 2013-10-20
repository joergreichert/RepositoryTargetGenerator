package de.abg.jreichert.repositorytarget.activeannotations

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import java.text.SimpleDateFormat
import java.util.Calendar
import org.eclipse.xtend.lib.macro.AbstractMethodProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration
import org.eclipse.xtend.lib.macro.declaration.Visibility

@Target(ElementType.METHOD)
@Active(DurationProcessor)
annotation LogExecutionTime {
}

class DurationProcessor extends AbstractMethodProcessor {
	private extension AnnotationExtensions = new AnnotationExtensions

	override doTransform(MutableMethodDeclaration method, extension TransformationContext context) {

		if (method.returnType == null)
			method.addError("A method to be time measured have to explicitly declare its return type (or void if there should be none).")

		val wrappedMethodName = method.newMethodName
		val clazz = method.declaringType
		val originalMethodBody = method.body
		val params = method.parameters

		method.body = [
			'''
				long start = System.currentTimeMillis();
				«method.handleReturnTypeAssignment(context)»«wrappedMethodName»(«params.map[simpleName].join(", ")»);
				logDuration(start, "«method.simpleName»(«params.map[simpleName + " = \" + " + simpleName + " + \""].join(", ")»)");
				«method.handleReturnTypeReturnment(context)»
			'''
		]
		
		clazz.addMethod(wrappedMethodName) [
			visibility = Visibility.PRIVATE
			final = method.final
			static = method.static
			method.parameters.forEach(p|it.addParameter(p.simpleName, p.type))
			returnType = method.returnType
			exceptions = method.exceptions
			body = originalMethodBody
		]

		if(clazz.declaredMethods.filter[it.simpleName == "logDuration"].empty) {
			clazz.addMethod("logDuration") [
				visibility = Visibility.PRIVATE
				static = true
				addParameter("start", context.primitiveLong)
				addParameter("message", context.string)
				returnType = context.primitiveVoid
				body = [
					'''
						long end = System.currentTimeMillis();
						«fqnSimpleDateFormat(context)» df = new «fqnSimpleDateFormat(context)»("mm:ss.SSS");
						«fqnCalendar(context)» cal = «fqnCalendar(context)».getInstance();
						cal.setTimeInMillis(end - start);
						String timeStr = df.format(cal.getTime());
						System.err.println(message + ": " + timeStr + " min");
					''']
			]
		}
	}

	def private String fqnSimpleDateFormat(CompilationContext compilationContext,
		TransformationContext transformationContext) {
		fqnClass(compilationContext, transformationContext, SimpleDateFormat)
	}

	def private String fqnCalendar(CompilationContext compilationContext, TransformationContext transformationContext) {
		fqnClass(compilationContext, transformationContext, Calendar)
	}

	def private newMethodName(MethodDeclaration it) {
		simpleName + "__Measured"
	}

	def private handleReturnTypeAssignment(MutableMethodDeclaration method, extension TransformationContext context) {
		if (method.returnType.equals(context.primitiveVoid)) {
			""
		} else {
			method.returnType.simpleName + " result = "
		}
	}

	def private handleReturnTypeReturnment(MutableMethodDeclaration method, extension TransformationContext context) {
		if (method.returnType.equals(context.primitiveVoid)) {
			""
		} else {
			"return result;"
		}
	}
}
