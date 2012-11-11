/**
 */
package de.abg.jreichert.targetDefinition;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.Unit#getCategoryId <em>Category Id</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Unit#getVersion <em>Version</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.Unit#isNoFeature <em>No Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getUnit()
 * @model
 * @generated
 */
public interface Unit extends EObject
{
  /**
   * Returns the value of the '<em><b>Category Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category Id</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category Id</em>' attribute.
   * @see #setCategoryId(String)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getUnit_CategoryId()
   * @model
   * @generated
   */
  String getCategoryId();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Unit#getCategoryId <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category Id</em>' attribute.
   * @see #getCategoryId()
   * @generated
   */
  void setCategoryId(String value);

  /**
   * Returns the value of the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Version</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Version</em>' attribute.
   * @see #setVersion(String)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getUnit_Version()
   * @model
   * @generated
   */
  String getVersion();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Unit#getVersion <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Version</em>' attribute.
   * @see #getVersion()
   * @generated
   */
  void setVersion(String value);

  /**
   * Returns the value of the '<em><b>No Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>No Feature</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>No Feature</em>' attribute.
   * @see #setNoFeature(boolean)
   * @see de.abg.jreichert.targetDefinition.TargetDefinitionPackage#getUnit_NoFeature()
   * @model
   * @generated
   */
  boolean isNoFeature();

  /**
   * Sets the value of the '{@link de.abg.jreichert.targetDefinition.Unit#isNoFeature <em>No Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>No Feature</em>' attribute.
   * @see #isNoFeature()
   * @generated
   */
  void setNoFeature(boolean value);

} // Unit
