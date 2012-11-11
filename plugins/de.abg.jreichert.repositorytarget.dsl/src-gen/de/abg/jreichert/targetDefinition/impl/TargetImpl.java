/**
 */
package de.abg.jreichert.targetDefinition.impl;

import de.abg.jreichert.targetDefinition.Location;
import de.abg.jreichert.targetDefinition.Target;
import de.abg.jreichert.targetDefinition.TargetDefinitionPackage;

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
 * An implementation of the model object '<em><b>Target</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.TargetImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.TargetImpl#getTargetFileName <em>Target File Name</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.TargetImpl#getLocations <em>Locations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TargetImpl extends MinimalEObjectImpl.Container implements Target
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
   * The default value of the '{@link #getTargetFileName() <em>Target File Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetFileName()
   * @generated
   * @ordered
   */
  protected static final String TARGET_FILE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getTargetFileName() <em>Target File Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTargetFileName()
   * @generated
   * @ordered
   */
  protected String targetFileName = TARGET_FILE_NAME_EDEFAULT;

  /**
   * The cached value of the '{@link #getLocations() <em>Locations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLocations()
   * @generated
   * @ordered
   */
  protected EList<Location> locations;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TargetImpl()
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
    return TargetDefinitionPackage.Literals.TARGET;
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
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.TARGET__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getTargetFileName()
  {
    return targetFileName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTargetFileName(String newTargetFileName)
  {
    String oldTargetFileName = targetFileName;
    targetFileName = newTargetFileName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.TARGET__TARGET_FILE_NAME, oldTargetFileName, targetFileName));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Location> getLocations()
  {
    if (locations == null)
    {
      locations = new EObjectContainmentEList<Location>(Location.class, this, TargetDefinitionPackage.TARGET__LOCATIONS);
    }
    return locations;
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
      case TargetDefinitionPackage.TARGET__LOCATIONS:
        return ((InternalEList<?>)getLocations()).basicRemove(otherEnd, msgs);
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
      case TargetDefinitionPackage.TARGET__NAME:
        return getName();
      case TargetDefinitionPackage.TARGET__TARGET_FILE_NAME:
        return getTargetFileName();
      case TargetDefinitionPackage.TARGET__LOCATIONS:
        return getLocations();
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
      case TargetDefinitionPackage.TARGET__NAME:
        setName((String)newValue);
        return;
      case TargetDefinitionPackage.TARGET__TARGET_FILE_NAME:
        setTargetFileName((String)newValue);
        return;
      case TargetDefinitionPackage.TARGET__LOCATIONS:
        getLocations().clear();
        getLocations().addAll((Collection<? extends Location>)newValue);
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
      case TargetDefinitionPackage.TARGET__NAME:
        setName(NAME_EDEFAULT);
        return;
      case TargetDefinitionPackage.TARGET__TARGET_FILE_NAME:
        setTargetFileName(TARGET_FILE_NAME_EDEFAULT);
        return;
      case TargetDefinitionPackage.TARGET__LOCATIONS:
        getLocations().clear();
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
      case TargetDefinitionPackage.TARGET__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case TargetDefinitionPackage.TARGET__TARGET_FILE_NAME:
        return TARGET_FILE_NAME_EDEFAULT == null ? targetFileName != null : !TARGET_FILE_NAME_EDEFAULT.equals(targetFileName);
      case TargetDefinitionPackage.TARGET__LOCATIONS:
        return locations != null && !locations.isEmpty();
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
    result.append(", targetFileName: ");
    result.append(targetFileName);
    result.append(')');
    return result.toString();
  }

} //TargetImpl
