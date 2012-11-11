/**
 */
package de.abg.jreichert.targetDefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.Target#getName <em>Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Target#getTargetFileName <em>Target File Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Target#getLocations <em>Locations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getTarget()
 * @model
 * @generated
 */
public interface Target extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getTarget_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Target#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Target File Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Target File Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Target File Name</em>' attribute.
   * @see #setTargetFileName(String)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getTarget_TargetFileName()
   * @model
   * @generated
   */
  String getTargetFileName();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Target#getTargetFileName <em>Target File Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Target File Name</em>' attribute.
   * @see #getTargetFileName()
   * @generated
   */
  void setTargetFileName(String value);

  /**
   * Returns the value of the '<em><b>Locations</b></em>' containment reference list.
   * The list contents are of type {@link de.abg.jreichert.targetDefinition.Location}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Locations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Locations</em>' containment reference list.
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getTarget_Locations()
   * @model containment="true"
   * @generated
   */
  EList<Location> getLocations();

} // Target
