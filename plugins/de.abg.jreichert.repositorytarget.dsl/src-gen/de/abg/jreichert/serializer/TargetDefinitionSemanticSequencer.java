package de.abg.jreichert.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import de.abg.jreichert.services.TargetDefinitionGrammarAccess;
import de.abg.jreichert.targetDefinition.Location;
import de.abg.jreichert.targetDefinition.Target;
import de.abg.jreichert.targetDefinition.TargetDefinitionPackage;
import de.abg.jreichert.targetDefinition.Unit;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;

@SuppressWarnings("all")
public class TargetDefinitionSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private TargetDefinitionGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == TargetDefinitionPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case TargetDefinitionPackage.LOCATION:
				if(context == grammarAccess.getLocationRule()) {
					sequence_Location(context, (Location) semanticObject); 
					return; 
				}
				else break;
			case TargetDefinitionPackage.TARGET:
				if(context == grammarAccess.getTargetRule()) {
					sequence_Target(context, (Target) semanticObject); 
					return; 
				}
				else break;
			case TargetDefinitionPackage.UNIT:
				if(context == grammarAccess.getUnitRule()) {
					sequence_Unit(context, (Unit) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (name=STRING? repositoryLocation=URL unit+=Unit unit+=Unit*)
	 */
	protected void sequence_Location(EObject context, Location semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=STRING targetFileName=ID? locations+=Location*)
	 */
	protected void sequence_Target(EObject context, Target semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (categoryId=ID version=VERSION? noFeature?='noFeature'?)
	 */
	protected void sequence_Unit(EObject context, Unit semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
