/**
 */
package de.abg.jreichert.targetDefinition;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.abg.jreichert.targetDefinition.TargetDefinitionFactory
 * @model kind="package"
 * @generated
 */
public interface TargetDefinitionPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "targetDefinition";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.abg.de/jreichert/TargetDefinition";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "targetDefinition";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  TargetDefinitionPackage eINSTANCE = de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl.init();

  /**
   * The meta object id for the '{@link de.abg.jreichert.targetDefinition.impl.TargetImpl <em>Target</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.abg.jreichert.targetDefinition.impl.TargetImpl
   * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getTarget()
   * @generated
   */
  int TARGET = 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET__NAME = 0;

  /**
   * The feature id for the '<em><b>Target File Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET__TARGET_FILE_NAME = 1;

  /**
   * The feature id for the '<em><b>Locations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET__LOCATIONS = 2;

  /**
   * The number of structural features of the '<em>Target</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TARGET_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.abg.jreichert.targetDefinition.impl.LocationImpl <em>Location</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.abg.jreichert.targetDefinition.impl.LocationImpl
   * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getLocation()
   * @generated
   */
  int LOCATION = 1;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__NAME = 0;

  /**
   * The feature id for the '<em><b>Repository Location</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__REPOSITORY_LOCATION = 1;

  /**
   * The feature id for the '<em><b>Unit</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION__UNIT = 2;

  /**
   * The number of structural features of the '<em>Location</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LOCATION_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link de.abg.jreichert.targetDefinition.impl.UnitImpl <em>Unit</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see de.abg.jreichert.targetDefinition.impl.UnitImpl
   * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getUnit()
   * @generated
   */
  int UNIT = 2;

  /**
   * The feature id for the '<em><b>Category Id</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT__CATEGORY_ID = 0;

  /**
   * The feature id for the '<em><b>Version</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT__VERSION = 1;

  /**
   * The feature id for the '<em><b>No Feature</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT__NO_FEATURE = 2;

  /**
   * The number of structural features of the '<em>Unit</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int UNIT_FEATURE_COUNT = 3;


  /**
   * Returns the meta object for class '{@link de.abg.jreichert.targetDefinition.Target <em>Target</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Target</em>'.
   * @see de.abg.jreichert.targetDefinition.Target
   * @generated
   */
  EClass getTarget();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Target#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.abg.jreichert.targetDefinition.Target#getName()
   * @see #getTarget()
   * @generated
   */
  EAttribute getTarget_Name();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Target#getTargetFileName <em>Target File Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Target File Name</em>'.
   * @see de.abg.jreichert.targetDefinition.Target#getTargetFileName()
   * @see #getTarget()
   * @generated
   */
  EAttribute getTarget_TargetFileName();

  /**
   * Returns the meta object for the containment reference list '{@link de.abg.jreichert.targetDefinition.Target#getLocations <em>Locations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Locations</em>'.
   * @see de.abg.jreichert.targetDefinition.Target#getLocations()
   * @see #getTarget()
   * @generated
   */
  EReference getTarget_Locations();

  /**
   * Returns the meta object for class '{@link de.abg.jreichert.targetDefinition.Location <em>Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Location</em>'.
   * @see de.abg.jreichert.targetDefinition.Location
   * @generated
   */
  EClass getLocation();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Location#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see de.abg.jreichert.targetDefinition.Location#getName()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_Name();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Location#getRepositoryLocation <em>Repository Location</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Repository Location</em>'.
   * @see de.abg.jreichert.targetDefinition.Location#getRepositoryLocation()
   * @see #getLocation()
   * @generated
   */
  EAttribute getLocation_RepositoryLocation();

  /**
   * Returns the meta object for the containment reference list '{@link de.abg.jreichert.targetDefinition.Location#getUnit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Unit</em>'.
   * @see de.abg.jreichert.targetDefinition.Location#getUnit()
   * @see #getLocation()
   * @generated
   */
  EReference getLocation_Unit();

  /**
   * Returns the meta object for class '{@link de.abg.jreichert.targetDefinition.Unit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Unit</em>'.
   * @see de.abg.jreichert.targetDefinition.Unit
   * @generated
   */
  EClass getUnit();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Unit#getCategoryId <em>Category Id</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category Id</em>'.
   * @see de.abg.jreichert.targetDefinition.Unit#getCategoryId()
   * @see #getUnit()
   * @generated
   */
  EAttribute getUnit_CategoryId();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Unit#getVersion <em>Version</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Version</em>'.
   * @see de.abg.jreichert.targetDefinition.Unit#getVersion()
   * @see #getUnit()
   * @generated
   */
  EAttribute getUnit_Version();

  /**
   * Returns the meta object for the attribute '{@link de.abg.jreichert.targetDefinition.Unit#isNoFeature <em>No Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>No Feature</em>'.
   * @see de.abg.jreichert.targetDefinition.Unit#isNoFeature()
   * @see #getUnit()
   * @generated
   */
  EAttribute getUnit_NoFeature();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  TargetDefinitionFactory getTargetDefinitionFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link de.abg.jreichert.targetDefinition.impl.TargetImpl <em>Target</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.abg.jreichert.targetDefinition.impl.TargetImpl
     * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getTarget()
     * @generated
     */
    EClass TARGET = eINSTANCE.getTarget();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET__NAME = eINSTANCE.getTarget_Name();

    /**
     * The meta object literal for the '<em><b>Target File Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TARGET__TARGET_FILE_NAME = eINSTANCE.getTarget_TargetFileName();

    /**
     * The meta object literal for the '<em><b>Locations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TARGET__LOCATIONS = eINSTANCE.getTarget_Locations();

    /**
     * The meta object literal for the '{@link de.abg.jreichert.targetDefinition.impl.LocationImpl <em>Location</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.abg.jreichert.targetDefinition.impl.LocationImpl
     * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getLocation()
     * @generated
     */
    EClass LOCATION = eINSTANCE.getLocation();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCATION__NAME = eINSTANCE.getLocation_Name();

    /**
     * The meta object literal for the '<em><b>Repository Location</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute LOCATION__REPOSITORY_LOCATION = eINSTANCE.getLocation_RepositoryLocation();

    /**
     * The meta object literal for the '<em><b>Unit</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference LOCATION__UNIT = eINSTANCE.getLocation_Unit();

    /**
     * The meta object literal for the '{@link de.abg.jreichert.targetDefinition.impl.UnitImpl <em>Unit</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see de.abg.jreichert.targetDefinition.impl.UnitImpl
     * @see de.abg.jreichert.targetDefinition.impl.TargetDefinitionPackageImpl#getUnit()
     * @generated
     */
    EClass UNIT = eINSTANCE.getUnit();

    /**
     * The meta object literal for the '<em><b>Category Id</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT__CATEGORY_ID = eINSTANCE.getUnit_CategoryId();

    /**
     * The meta object literal for the '<em><b>Version</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT__VERSION = eINSTANCE.getUnit_Version();

    /**
     * The meta object literal for the '<em><b>No Feature</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute UNIT__NO_FEATURE = eINSTANCE.getUnit_NoFeature();

  }

} //TargetDefinitionPackage
