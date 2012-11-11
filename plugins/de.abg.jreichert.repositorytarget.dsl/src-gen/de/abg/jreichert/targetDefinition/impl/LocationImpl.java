/**
 */
package de.abg.jreichert.targetDefinition.impl;

import de.abg.jreichert.targetDefinition.Location;
import de.abg.jreichert.targetDefinition.TargetDefinitionPackage;
import de.abg.jreichert.targetDefinition.Unit;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Location</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.LocationImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.LocationImpl#getRepositoryLocation <em>Repository Location</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.LocationImpl#getUnit <em>Unit</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LocationImpl extends MinimalEObjectImpl.Container implements Location
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getRepositoryLocation() <em>Repository Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepositoryLocation()
   * @generated
   * @ordered
   */
  protected static final String REPOSITORY_LOCATION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getRepositoryLocation() <em>Repository Location</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRepositoryLocation()
   * @generated
   * @ordered
   */
  protected String repositoryLocation = REPOSITORY_LOCATION_EDEFAULT;

  /**
   * The cached value of the '{@link #getUnit() <em>Unit</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnit()
   * @generated
   * @ordered
   */
  protected EList<Unit> unit;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LocationImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return TargetDefinitionPackage.Literals.LOCATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.LOCATION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getRepositoryLocation()
  {
    return repositoryLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setRepositoryLocation(String newRepositoryLocation)
  {
    String oldRepositoryLocation = repositoryLocation;
    repositoryLocation = newRepositoryLocation;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.LOCATION__REPOSITORY_LOCATION, oldRepositoryLocation, repositoryLocation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Unit> getUnit()
  {
    if (unit == null)
    {
      unit = new EObjectContainmentEList<Unit>(Unit.class, this, TargetDefinitionPackage.LOCATION__UNIT);
    }
    return unit;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.LOCATION__UNIT:
        return ((InternalEList<?>)getUnit()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.LOCATION__NAME:
        return getName();
      case TargetDefinitionPackage.LOCATION__REPOSITORY_LOCATION:
        return getRepositoryLocation();
      case TargetDefinitionPackage.LOCATION__UNIT:
        return getUnit();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.LOCATION__NAME:
        setName((String)newValue);
        return;
      case TargetDefinitionPackage.LOCATION__REPOSITORY_LOCATION:
        setRepositoryLocation((String)newValue);
        return;
      case TargetDefinitionPackage.LOCATION__UNIT:
        getUnit().clear();
        getUnit().addAll((Collection<? extends Unit>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.LOCATION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case TargetDefinitionPackage.LOCATION__REPOSITORY_LOCATION:
        setRepositoryLocation(REPOSITORY_LOCATION_EDEFAULT);
        return;
      case TargetDefinitionPackage.LOCATION__UNIT:
        getUnit().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.LOCATION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case TargetDefinitionPackage.LOCATION__REPOSITORY_LOCATION:
        return REPOSITORY_LOCATION_EDEFAULT == null ? repositoryLocation != null : !REPOSITORY_LOCATION_EDEFAULT.equals(repositoryLocation);
      case TargetDefinitionPackage.LOCATION__UNIT:
        return unit != null && !unit.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", repositoryLocation: ");
    result.append(repositoryLocation);
    result.append(')');
    return result.toString();
  }

} //LocationImpl
