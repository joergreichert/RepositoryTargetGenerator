package de.abg.jreichert.repositorytarget.activeannotations

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import javax.persistence.Id
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration

@Target(ElementType.TYPE)
@Active(EntityProcessor)
annotation Entity {
	String idGenerationType = "GenerationType.AUTO"
	boolean generateLiterals = true
}

class EntityProcessor extends AbstractClassProcessor {
	private extension AnnotationExtensions = new AnnotationExtensions
	private EntityLiteralProcessor entityLiteralProcessor
	
	new () {
		entityLiteralProcessor = new EntityLiteralProcessor()
	}
	
	override doRegisterGlobals(ClassDeclaration annotatedClass, RegisterGlobalsContext context) {
		entityLiteralProcessor.doRegisterGlobals(annotatedClass, context)
	}	

	override doTransform(MutableClassDeclaration clazz, extension TransformationContext context) {
		clazz.addAnnotation(javax.persistence.Entity.newAnnotationReference)
		if(clazz.declaredConstructors.filter[parameters.empty].empty) clazz.addConstructor[]
		val idGenerationType = findAnnotationWithValue(
			new AnnotationValueSearch => [
				annotationSearch = new AnnotationSearch => [
					it.type = clazz
					it.context = context
					it.annotationClass = Entity
				]
				value = "idGenerationType"
				valueType = String
			]
		)
		if (!idGenerationType.nullOrEmpty && !clazz.declaredFields.exists[annotations.exists[it.annotationTypeDeclaration.qualifiedName == Id.canonicalName]]) {
			val idField = clazz.addField("id") [
				addAnnotation(Id.newAnnotationReference)
				// TODO: waiting for fix of 
//				val generationType = findEnumerationType("javax.persistence.GenerationType")
//				if(generationType !== null) {
//					val idGenerationTypeLiteral = generationType.findDeclaredValue(idGenerationType)
//					if(idGenerationTypeLiteral !== null) {
//						addAnnotation(GeneratedValue.newAnnotationReference [
//							it.setEnumValue("strategy", idGenerationTypeLiteral)
//						])
//					}
//				}
				type = Long.newTypeReference
				initializer = ['''-1L''']
			]
			new PropertyProcessor().doTransform(idField, context)
		}
		entityLiteralProcessor.doTransform(clazz, context)
	}
}

