/**
 */
package de.abg.jreichert.targetDefinition;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.Location#getName <em>Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Location#getRepositoryLocation <em>Repository Location</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Location#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getLocation()
 * @model
 * @generated
 */
public interface Location extends EObject
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
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getLocation_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Location#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Repository Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Repository Location</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Repository Location</em>' attribute.
   * @see #setRepositoryLocation(String)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getLocation_RepositoryLocation()
   * @model
   * @generated
   */
  String getRepositoryLocation();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Location#getRepositoryLocation <em>Repository Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Repository Location</em>' attribute.
   * @see #getRepositoryLocation()
   * @generated
   */
  void setRepositoryLocation(String value);

  /**
   * Returns the value of the '<em><b>Unit</b></em>' containment reference list.
   * The list contents are of type {@link de.abg.jreichert.targetDefinition.Unit}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unit</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unit</em>' containment reference list.
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getLocation_Unit()
   * @model containment="true"
   * @generated
   */
  EList<Unit> getUnit();

} // Location
