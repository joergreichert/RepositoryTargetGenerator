/**
 */
package de.abg.jreichert.targetDefinition.impl;

import de.abg.jreichert.targetDefinition.TargetDefinitionPackage;
import de.abg.jreichert.targetDefinition.Unit;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Unit</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.UnitImpl#getCategoryId <em>Category Id</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.UnitImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.abg.jreichert.targetDefinition.impl.UnitImpl#isNoFeature <em>No Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class UnitImpl extends MinimalEObjectImpl.Container implements Unit
{
  /**
   * The default value of the '{@link #getCategoryId() <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategoryId()
   * @generated
   * @ordered
   */
  protected static final String CATEGORY_ID_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getCategoryId() <em>Category Id</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCategoryId()
   * @generated
   * @ordered
   */
  protected String categoryId = CATEGORY_ID_EDEFAULT;

  /**
   * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected static final String VERSION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getVersion()
   * @generated
   * @ordered
   */
  protected String version = VERSION_EDEFAULT;

  /**
   * The default value of the '{@link #isNoFeature() <em>No Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNoFeature()
   * @generated
   * @ordered
   */
  protected static final boolean NO_FEATURE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isNoFeature() <em>No Feature</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNoFeature()
   * @generated
   * @ordered
   */
  protected boolean noFeature = NO_FEATURE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected UnitImpl()
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
    return TargetDefinitionPackage.Literals.UNIT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getCategoryId()
  {
    return categoryId;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCategoryId(String newCategoryId)
  {
    String oldCategoryId = categoryId;
    categoryId = newCategoryId;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.UNIT__CATEGORY_ID, oldCategoryId, categoryId));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getVersion()
  {
    return version;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setVersion(String newVersion)
  {
    String oldVersion = version;
    version = newVersion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.UNIT__VERSION, oldVersion, version));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNoFeature()
  {
    return noFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNoFeature(boolean newNoFeature)
  {
    boolean oldNoFeature = noFeature;
    noFeature = newNoFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, TargetDefinitionPackage.UNIT__NO_FEATURE, oldNoFeature, noFeature));
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
      case TargetDefinitionPackage.UNIT__CATEGORY_ID:
        return getCategoryId();
      case TargetDefinitionPackage.UNIT__VERSION:
        return getVersion();
      case TargetDefinitionPackage.UNIT__NO_FEATURE:
        return isNoFeature();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case TargetDefinitionPackage.UNIT__CATEGORY_ID:
        setCategoryId((String)newValue);
        return;
      case TargetDefinitionPackage.UNIT__VERSION:
        setVersion((String)newValue);
        return;
      case TargetDefinitionPackage.UNIT__NO_FEATURE:
        setNoFeature((Boolean)newValue);
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
      case TargetDefinitionPackage.UNIT__CATEGORY_ID:
        setCategoryId(CATEGORY_ID_EDEFAULT);
        return;
      case TargetDefinitionPackage.UNIT__VERSION:
        setVersion(VERSION_EDEFAULT);
        return;
      case TargetDefinitionPackage.UNIT__NO_FEATURE:
        setNoFeature(NO_FEATURE_EDEFAULT);
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
      case TargetDefinitionPackage.UNIT__CATEGORY_ID:
        return CATEGORY_ID_EDEFAULT == null ? categoryId != null : !CATEGORY_ID_EDEFAULT.equals(categoryId);
      case TargetDefinitionPackage.UNIT__VERSION:
        return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
      case TargetDefinitionPackage.UNIT__NO_FEATURE:
        return noFeature != NO_FEATURE_EDEFAULT;
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
    result.append(" (categoryId: ");
    result.append(categoryId);
    result.append(", version: ");
    result.append(version);
    result.append(", noFeature: ");
    result.append(noFeature);
    result.append(')');
    return result.toString();
  }

} //UnitImpl
