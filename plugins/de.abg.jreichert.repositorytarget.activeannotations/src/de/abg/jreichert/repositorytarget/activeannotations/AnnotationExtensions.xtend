package de.abg.jreichert.repositorytarget.activeannotations

import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.AnnotationTarget
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import org.eclipse.xtend.lib.macro.declaration.CompilationStrategy.CompilationContext
import org.eclipse.xtend.lib.annotations.Accessors

class AnnotationExtensions {
	
	def String fqnClass(CompilationContext compilationContext, TransformationContext transformationContext,
		Class<?> clazz) {
		compilationContext.toJavaCode(transformationContext.newTypeReference(clazz))
	}
	
	def boolean isAnnotatedWithType(TypeReference typeRef, TransformationContext context, Class<?> type) {
		if(!(typeRef.type instanceof AnnotationTarget)) {
			return false
		}
		isAnnotatedWithType(typeRef.type as AnnotationTarget, context, type)
	}	
	
	def boolean isAnnotatedWithType(AnnotationTarget annotationTarget, TransformationContext context, Class<?> type) {
		findAnnotation(
			new AnnotationSearch => [
				it.type = annotationTarget 
				it.context = context
				it.annotationClass = type
			]
		) !== null
	}	
	
	def <T> findAnnotationWithValue(extension AnnotationValueSearch<T> annotationValueSearch) {
		findAnnotation(annotationSearch)?.getValue(annotationValueSearch.value) as T
	}

	def findAnnotation(extension AnnotationSearch annotationSearch) {
		findAnnotation(annotationSearch, annotationSearch.context)
	} 
	
	def private findAnnotation(extension AnnotationSearch annotationSearch, extension TransformationContext context) {
		type.annotations.filter[annotationTypeDeclaration.qualifiedName == annotationClass.newTypeReference.type.qualifiedName].head
	}	
}

class AnnotationSearch {
	@Accessors AnnotationTarget type
	@Accessors TransformationContext context
	@Accessors Class<?> annotationClass
}

class AnnotationValueSearch<T> {
	@Accessors AnnotationSearch annotationSearch
	@Accessors String value
	@Accessors Class<T> valueType	
}