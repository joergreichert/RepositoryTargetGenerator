package de.abg.jreichert.repositorytarget.activeannotations

import java.text.SimpleDateFormat
import java.util.Calendar
import org.eclipse.xtend.lib.macro.AbstractMethodProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext
import org.eclipse.xtend.lib.macro.declaration.MethodDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableMethodDeclaration
import org.eclipse.xtend.lib.macro.declaration.Visibility

@Active(DurationProcessor)
annotation LogExecutionTime {
	
}


class DurationProcessor extends AbstractMethodProcessor {
	
	override doTransform(MutableMethodDeclaration method, extension TransformationContext context) {
		val oldName = method.simpleName
		val oldVisibility = method.visibility
		method.simpleName = method.newMethodName
		method.visibility = Visibility.PRIVATE
		val clazz = method.declaringType
		clazz.addMethod(oldName) [
			visibility = oldVisibility
			final = method.final
			static = method.static
			method.parameters.forEach(p|it.addParameter(p.simpleName, p.type))
			returnType = method.returnType
			docComment = method.docComment
			val params = parameters
			body = ['''
				long start = System.currentTimeMillis();
				«method.handleReturnTypeAssignment(context)»«method.simpleName»(«params.map[simpleName].join(", ")»);
				logDuration(start, "«oldName»(«params.map[simpleName + " = \" + " + simpleName + " + \""].join(", ")»)");
				«method.handleReturnTypeReturnment(context)»
			''']
		]
		clazz.addMethod("logDuration") [
			visibility = Visibility.PRIVATE
			static = true
			addParameter("start", context.primitiveLong)
			addParameter("message", context.string)
			returnType = context.primitiveVoid
			body = ['''
				long end = System.currentTimeMillis();
				«fqnSimpleDateFormat(context)» df = new «fqnSimpleDateFormat(context)»("sss.SSS");
				«fqnCalendar(context)» cal = «fqnCalendar(context)».getInstance();
				cal.setTimeInMillis(end - start);
				String timeStr = df.format(cal.getTime());
				System.err.println(message + ": " + timeStr + " s");
			''']
		]
	}
	
	def private String fqnSimpleDateFormat(CompilationContext compilationContext, TransformationContext transformationContext) {
		fqnClass(compilationContext, transformationContext, SimpleDateFormat)
	}

	def private String fqnCalendar(CompilationContext compilationContext, TransformationContext transformationContext) {
		fqnClass(compilationContext, transformationContext, Calendar)
	}

	def private String fqnClass(CompilationContext compilationContext, TransformationContext transformationContext, Class<?> clazz) {
		compilationContext.toJavaCode(transformationContext.newTypeReference(clazz))
	}
	
	def private newMethodName(MethodDeclaration it) {
		simpleName + "__Measured"
	}	
	
	def private handleReturnTypeAssignment(MutableMethodDeclaration method, extension TransformationContext context) {
		if(method.returnType.equals(context.primitiveVoid)) {
			""
		} else {
			method.returnType.simpleName + " result = "
		}
	}

	def private handleReturnTypeReturnment(MutableMethodDeclaration method, extension TransformationContext context) {
		if(method.returnType.equals(context.primitiveVoid)) {
			""
		} else {
			"return result;"
		}
	}
}