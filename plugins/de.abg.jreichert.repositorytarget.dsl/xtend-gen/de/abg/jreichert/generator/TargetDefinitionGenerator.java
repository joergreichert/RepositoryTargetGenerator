package de.abg.jreichert.generator;

import com.google.common.base.Objects;
import com.google.common.collect.Iterators;
import de.abg.jreichert.targetDefinition.Location;
import de.abg.jreichert.targetDefinition.Target;
import de.abg.jreichert.targetDefinition.Unit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.IGenerator;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure1;
import org.eclipse.xtext.xbase.lib.StringExtensions;

@SuppressWarnings("all")
public class TargetDefinitionGenerator implements IGenerator {
  public void doGenerate(final Resource resource, final IFileSystemAccess fsa) {
    TreeIterator<EObject> _allContents = resource.getAllContents();
    Iterator<Target> _filter = Iterators.<Target>filter(_allContents, Target.class);
    final Target dslTarget = IteratorExtensions.<Target>head(_filter);
    boolean _notEquals = (!Objects.equal(dslTarget, null));
    if (_notEquals) {
      final de.abg.jreichert.repositorytarget.definition.Target generatorTarget = this.transformTarget(dslTarget);
      CharSequence _generateCategoryXml = generatorTarget.generateCategoryXml();
      fsa.generateFile("category.xml", _generateCategoryXml);
      String _fileName = this.fileName(dslTarget);
      CharSequence _generateTarget = generatorTarget.generateTarget();
      fsa.generateFile(_fileName, _generateTarget);
    }
  }
  
  private String fileName(final Target it) {
    String _xifexpression = null;
    String _targetFileName = it.getTargetFileName();
    boolean _isNullOrEmpty = StringExtensions.isNullOrEmpty(_targetFileName);
    if (_isNullOrEmpty) {
      String _name = it.getName();
      String _replaceAll = _name.replaceAll(" ", "");
      String _firstUpper = StringExtensions.toFirstUpper(_replaceAll);
      String _plus = (_firstUpper + ".target");
      _xifexpression = _plus;
    } else {
      String _xifexpression_1 = null;
      String _targetFileName_1 = it.getTargetFileName();
      boolean _endsWith = _targetFileName_1.endsWith(".target");
      if (_endsWith) {
        String _targetFileName_2 = it.getTargetFileName();
        _xifexpression_1 = _targetFileName_2;
      } else {
        String _targetFileName_3 = it.getTargetFileName();
        String _plus_1 = (_targetFileName_3 + ".target");
        _xifexpression_1 = _plus_1;
      }
      _xifexpression = _xifexpression_1;
    }
    return _xifexpression;
  }
  
  public de.abg.jreichert.repositorytarget.definition.Target transformTarget(final Target dslTarget) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(dslTarget);
    final de.abg.jreichert.repositorytarget.definition.Target _result;
    synchronized (_createCache_transformTarget) {
      if (_createCache_transformTarget.containsKey(_cacheKey)) {
        return _createCache_transformTarget.get(_cacheKey);
      }
      de.abg.jreichert.repositorytarget.definition.Target _target = new de.abg.jreichert.repositorytarget.definition.Target();
      _result = _target;
      _createCache_transformTarget.put(_cacheKey, _result);
    }
    _init_transformTarget(_result, dslTarget);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,de.abg.jreichert.repositorytarget.definition.Target> _createCache_transformTarget = CollectionLiterals.newHashMap();
  
  private void _init_transformTarget(final de.abg.jreichert.repositorytarget.definition.Target generatorTarget, final Target dslTarget) {
    String _name = dslTarget.getName();
    generatorTarget.setName(_name);
    EList<Location> _locations = dslTarget.getLocations();
    final Procedure1<Location> _function = new Procedure1<Location>() {
        public void apply(final Location it) {
          List<de.abg.jreichert.repositorytarget.definition.Location> _locations = generatorTarget.getLocations();
          de.abg.jreichert.repositorytarget.definition.Location _transformLocation = TargetDefinitionGenerator.this.transformLocation(it);
          _locations.add(_transformLocation);
        }
      };
    IterableExtensions.<Location>forEach(_locations, _function);
  }
  
  public de.abg.jreichert.repositorytarget.definition.Location transformLocation(final Location dslLocation) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(dslLocation);
    final de.abg.jreichert.repositorytarget.definition.Location _result;
    synchronized (_createCache_transformLocation) {
      if (_createCache_transformLocation.containsKey(_cacheKey)) {
        return _createCache_transformLocation.get(_cacheKey);
      }
      de.abg.jreichert.repositorytarget.definition.Location _location = new de.abg.jreichert.repositorytarget.definition.Location();
      _result = _location;
      _createCache_transformLocation.put(_cacheKey, _result);
    }
    _init_transformLocation(_result, dslLocation);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,de.abg.jreichert.repositorytarget.definition.Location> _createCache_transformLocation = CollectionLiterals.newHashMap();
  
  private void _init_transformLocation(final de.abg.jreichert.repositorytarget.definition.Location generatorLocation, final Location dslLocation) {
    String _repositoryLocation = dslLocation.getRepositoryLocation();
    generatorLocation.setRepositoryLocation(_repositoryLocation);
    EList<Unit> _unit = dslLocation.getUnit();
    final Procedure1<Unit> _function = new Procedure1<Unit>() {
        public void apply(final Unit it) {
          List<de.abg.jreichert.repositorytarget.definition.Unit> _units = generatorLocation.getUnits();
          de.abg.jreichert.repositorytarget.definition.Unit _transformUnit = TargetDefinitionGenerator.this.transformUnit(it);
          _units.add(_transformUnit);
        }
      };
    IterableExtensions.<Unit>forEach(_unit, _function);
  }
  
  public de.abg.jreichert.repositorytarget.definition.Unit transformUnit(final Unit dslUnit) {
    final ArrayList<?>_cacheKey = CollectionLiterals.newArrayList(dslUnit);
    final de.abg.jreichert.repositorytarget.definition.Unit _result;
    synchronized (_createCache_transformUnit) {
      if (_createCache_transformUnit.containsKey(_cacheKey)) {
        return _createCache_transformUnit.get(_cacheKey);
      }
      de.abg.jreichert.repositorytarget.definition.Unit _unit = new de.abg.jreichert.repositorytarget.definition.Unit();
      _result = _unit;
      _createCache_transformUnit.put(_cacheKey, _result);
    }
    _init_transformUnit(_result, dslUnit);
    return _result;
  }
  
  private final HashMap<ArrayList<? extends Object>,de.abg.jreichert.repositorytarget.definition.Unit> _createCache_transformUnit = CollectionLiterals.newHashMap();
  
  private void _init_transformUnit(final de.abg.jreichert.repositorytarget.definition.Unit generatorUnit, final Unit dslUnit) {
    boolean _isNoFeature = dslUnit.isNoFeature();
    if (_isNoFeature) {
      String _categoryId = dslUnit.getCategoryId();
      generatorUnit.setTargetId(_categoryId);
    } else {
      String _categoryId_1 = dslUnit.getCategoryId();
      generatorUnit.setCategoryId(_categoryId_1);
    }
    String _version = dslUnit.getVersion();
    generatorUnit.setVersion(_version);
    boolean _isNoFeature_1 = dslUnit.isNoFeature();
    boolean _not = (!_isNoFeature_1);
    generatorUnit.setFeature(Boolean.valueOf(_not));
  }
}
