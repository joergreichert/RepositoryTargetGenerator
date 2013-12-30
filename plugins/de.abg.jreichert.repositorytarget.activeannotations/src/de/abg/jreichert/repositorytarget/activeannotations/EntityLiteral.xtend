package de.abg.jreichert.repositorytarget.activeannotations

import java.lang.annotation.ElementType
import java.lang.annotation.Target
import javax.persistence.Id
import org.eclipse.xtend.lib.macro.AbstractClassProcessor
import org.eclipse.xtend.lib.macro.Active
import org.eclipse.xtend.lib.macro.RegisterGlobalsContext
import org.eclipse.xtend.lib.macro.TransformationContext
import org.eclipse.xtend.lib.macro.declaration.ClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.FieldDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableClassDeclaration
import org.eclipse.xtend.lib.macro.declaration.MutableTypeParameterDeclaration
import org.eclipse.xtend.lib.macro.declaration.TypeReference
import org.eclipse.xtend.lib.macro.declaration.Visibility
import org.sculptor.framework.domain.PropertiesCollection

@Target(ElementType.TYPE)
@Active(EntityLiteralProcessor)
annotation EntityLiteral {
}

class EntityLiteralProcessor extends AbstractClassProcessor {
	private extension AnnotationExtensions = new AnnotationExtensions

	override doRegisterGlobals(ClassDeclaration annotatedClass, RegisterGlobalsContext context) {
		context.registerClass(annotatedClass.literalsClassName)
		context.registerClass(annotatedClass.literalClassName)
		context.registerClass(annotatedClass.literalClassImplName)
	}

	def getLiteralsClassName(ClassDeclaration annotatedClass) {
		annotatedClass.qualifiedName + "Literals"
	}

	def getLiteralClassName(ClassDeclaration annotatedClass) {
		annotatedClass.qualifiedName + "Literal"
	}
	
	def getLiteralClassImplName(ClassDeclaration annotatedClass) {
		annotatedClass.qualifiedName + "LiteralImpl"
	}
	
	private def findClassWithTypeParam(String qualifiedName, extension TransformationContext context) {
		val mutableClass = findClass(qualifiedName)
		if(mutableClass.typeParameters.size == 0) {
			mutableClass.addTypeParameter("T")
		}
		mutableClass
	}
	
	override doTransform(MutableClassDeclaration annotatedClass, extension TransformationContext context) {
		val literalImplType = findClassWithTypeParam(annotatedClass.literalClassImplName, context)
		val literalType = findClassWithTypeParam(annotatedClass.literalClassName, context)
		val literalsType = findClass(annotatedClass.literalsClassName)
		
		literalImplType.addLiteralImplMembers(annotatedClass, context, "")
		literalType.addLiteralMembers(annotatedClass, context, "")
		literalsType.addLiteralsMembers(annotatedClass, context)
	}

	private def void addLiteralImplMembers(MutableClassDeclaration classType, ClassDeclaration annotatedClass, extension TransformationContext context, String prefix) {
//		classType.static = true
		val typeParameter = classType.typeParameters.head
		classType.extendedClass = PropertiesCollection.newTypeReference
		classType.addSerialVersionUIDField(context)
		classType.addField("owningClass") [
			type = typeParameter.owningClassTypeRef(context)
		]
		classType.addConstructor[
			addParameter("owningClass", typeParameter.owningClassTypeRef(context))
			body = 	[
				'''
					super(null);
					this.owningClass = owningClass;
				'''
			]	
		]
		classType.addConstructor[
			addParameter("parentPath", string)
			addParameter("additionalPath", string)
			addParameter("owningClass", typeParameter.owningClassTypeRef(context))
			body = 	[
				'''
		            super(parentPath, additionalPath);
		            this.owningClass = owningClass;
				'''
			]	
		]
		for (field : annotatedClass.declaredFields) {
			if (field.isAnnotatedWithProperty(context) || field.isAnnotatedWithId(context)) {
				if(field.type.isAnnotatedWithEntity(context) || field.type.isAnnotatedWithEntityLiteral(context)) {
					classType.addTypeLiteralImplMethod(field.type.type as ClassDeclaration, field, context, typeParameter)
				} else {
					classType.addLiteralImplMethod(field, context, typeParameter)
				}
			}
		}
	}
	
	private def addSerialVersionUIDField(MutableClassDeclaration classType, extension TransformationContext context) {
		classType.addField("serialVersionUID") [
			static = true
			final = true
			type = primitiveLong
			initializer = ["1L"]
		]
	}
	
	private def owningClassTypeRef(MutableTypeParameterDeclaration typeParameter, extension TransformationContext context) {
		Class.newTypeReference(typeParameter.newTypeReference)
	}

	private def void addLiteralMembers(MutableClassDeclaration classType, ClassDeclaration annotatedClass, extension TransformationContext context, String prefix) {
//		classType.static = true
		val typeParameter = classType.typeParameters.head
		classType.extendedClass = findClassWithTypeParam(annotatedClass.literalClassImplName, context).newTypeReference(typeParameter.newTypeReference)
		classType.setImplementedInterfaces(newArrayList(org.sculptor.framework.domain.Property.newTypeReference(typeParameter.newTypeReference)))
		classType.addSerialVersionUIDField(context)
		classType.addConstructor[
			addParameter("parentPath", string)
			addParameter("additionalPath", string)
			addParameter("owningClass", typeParameter.owningClassTypeRef(context))
			body = ['''super(parentPath, additionalPath, owningClass);''']	
		]
	}

	private def void addLiteralsMembers(MutableClassDeclaration classType, ClassDeclaration annotatedClass, TransformationContext context) {
		classType.addSharedInstanceField(annotatedClass, context)
		classType.addConstructor[
			visibility = Visibility.PRIVATE
			body = ['''''']
		]
		for (field : annotatedClass.declaredFields) {
			if (field.isAnnotatedWithProperty(context) || field.isAnnotatedWithId(context)) {
				if(field.type.isAnnotatedWithEntity(context) || field.type.isAnnotatedWithEntityLiteral(context)) {
					classType.addTypeLiteralsMethod(field.type.type as ClassDeclaration, annotatedClass, field, context)
				} else {
					classType.addLiteralsMethod(annotatedClass, field, context)
				}
			}
		}
	}
	
	private def void addSharedInstanceField(MutableClassDeclaration classType, ClassDeclaration annotatedClass, extension TransformationContext context) {
		classType.addField("sharedInstance") [
			visibility = Visibility.PRIVATE
			static = true
			final = true
			type = findClassWithTypeParam(annotatedClass.literalClassImplName, context).newTypeReference(annotatedClass.newTypeReference)
			initializer = ['''new «toJavaCode(findClass(annotatedClass.literalClassImplName).newTypeReference(annotatedClass.newTypeReference))»(«toJavaCode(annotatedClass.newTypeReference)».class);''']
		]
	}
	
	private def void addLiteralsMethod(MutableClassDeclaration classType, ClassDeclaration annotatedClass, FieldDeclaration field, 
			extension TransformationContext context) {
		classType.addMethod(field.simpleName) [
			visibility = Visibility.PUBLIC
			static = true
			returnType = org.sculptor.framework.domain.Property.newTypeReference(annotatedClass.newTypeReference)
			body = ['''return sharedInstance.«field.simpleName»();''']
		]
	}
	
	private def void addTypeLiteralsMethod(MutableClassDeclaration outerClassType, ClassDeclaration classType, ClassDeclaration annotatedClass, 
			FieldDeclaration field,	extension TransformationContext context) {
		outerClassType.addMethod(field.simpleName) [
			visibility = Visibility.PUBLIC
			static = true
			returnType = {
				val typeParameter = annotatedClass.newTypeReference
				findClassWithTypeParam(classType.literalClassName, context).newTypeReference(typeParameter)
			}
			body = ['''return sharedInstance.«field.simpleName»();''']
		]
	}
	
	private def void addLiteralImplMethod(MutableClassDeclaration classType, FieldDeclaration field, extension TransformationContext context, 
			MutableTypeParameterDeclaration typeParameter) {
		classType.addMethod(field.simpleName) [
			visibility = Visibility.PUBLIC
			returnType = org.sculptor.framework.domain.Property.newTypeReference(typeParameter.newTypeReference)
			body = ['''return new «toJavaCode(org.sculptor.framework.domain.LeafProperty.newTypeReference(typeParameter.newTypeReference))»(getParentPath(), "«field.simpleName»", false, owningClass);''']
		]
	}
	
	private def void addTypeLiteralImplMethod(MutableClassDeclaration outerClassType, ClassDeclaration classType, 
			FieldDeclaration field,	extension TransformationContext context, MutableTypeParameterDeclaration typeParameter) {
		outerClassType.addMethod(field.simpleName) [
			visibility = Visibility.PUBLIC
			returnType = {
				findClassWithTypeParam(classType.literalClassName, context).newTypeReference(typeParameter.newTypeReference)
			}
			body = ['''return new «toJavaCode(findClassWithTypeParam(classType.literalClassName, context).newTypeReference(typeParameter.newTypeReference))»(getParentPath(), "«field.simpleName»", owningClass);''']
		]
	}

	private def boolean isAnnotatedWithProperty(FieldDeclaration field, TransformationContext context) {
		isAnnotatedWithType(field, context, Property)
	}
	
	private def boolean isAnnotatedWithId(FieldDeclaration field, TransformationContext context) {
		isAnnotatedWithType(field, context, Id)
	}
	
	private def boolean isAnnotatedWithEntity(TypeReference typeRef, TransformationContext context) {
		isAnnotatedWithType(typeRef, context, Entity)
	}	
	
	private def boolean isAnnotatedWithEntityLiteral(TypeReference typeRef, TransformationContext context) {
		isAnnotatedWithType(typeRef, context, EntityLiteral)
	}	
}