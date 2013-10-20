package de.abg.jreichert.repositorytarget.activeannotations

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import org.eclipse.xtend.lib.macro.AbstractFieldProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.MutableFieldDeclaration

@Target(ElementType.FIELD)
@Active(PropertyProcessor)
annotation Property {
	String idGenerationType = ""
}

class PropertyProcessor extends AbstractFieldProcessor {

	override doTransform(MutableFieldDeclaration field, extension TransformationContext context) {
		field.declaringType.addMethod("get" + field.simpleName.toFirstUpper) [
			returnType = field.type
			body = ['''return this.«field.simpleName»;''']
		]
		field.declaringType.addMethod("set" + field.simpleName.toFirstUpper) [
			addParameter(field.simpleName, field.type)
			body = ['''this.«field.simpleName» = «field.simpleName»;''']
		]
	}
}
