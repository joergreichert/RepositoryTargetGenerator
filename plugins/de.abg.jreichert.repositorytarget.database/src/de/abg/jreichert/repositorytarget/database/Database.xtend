package de.abg.jreichert.repositorytarget.database

import java.util.Set
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany
import org.hibernate.annotations.Sort

@Entity
class Location {
	
	new() {
		
	}
	
	new(Location parentLocation) {
		this.parentLocation = parentLocation
	}
	
	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)	
  	@Property
	Long id = 1L	

	@Property
	String timestamp = null

	//@Column(unique=true)
	@Property
	String url

	@Property
	@ManyToOne
	@JoinColumn(name="location_id")  
	Location parentLocation

	@Property
	@OneToMany(mappedBy="_parentLocation", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Sort
	Set<Location> aggregatedLocations = <Location>newTreeSet[a,b|a.url.compareTo(b.url)]

	@Property
	@OneToMany(mappedBy="_location", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Sort
	Set<Unit> units = <Unit>newTreeSet[a,b|a.name.compareTo(b.name)]
	
	override String toString() {
		'''
			location (
				parentLocationId=«parentLocation?.id», 
				id=«id», 
				timestamp=«timestamp»
				url=«url»
				units (
					«FOR unit : units.sortBy[id]»
						«unit.toString»
					«ENDFOR»
				)
				aggregatedLocations (
					«FOR aggregatedLocation : aggregatedLocations.sortBy[id]»
						«aggregatedLocation.toString»
					«ENDFOR»
				)
			)
		'''
	}	
}

@Entity
class Unit {
	
	new() {
		
	}
	
	new(Location location) {
		this._location = location
	}
	
	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)	
  	@Property
	Long id = null	

	@Property
	@ManyToOne
	@JoinColumn(name="location_id")  
	Location location

	@Property
	String name

	@Property
	@OneToMany(mappedBy="_unit", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@Sort
	Set<Version> versions = <Version>newTreeSet[a,b|a.name.compareTo(b.name)]
	
	override String toString() {
		'''
			unit (
				locationId=«location?.id», 
				id=«id», 
				name=«name»
				versions (
					«FOR version : versions.sortBy[id]»
						«version.toString»
					«ENDFOR»
				) 
			)
		'''
	}	
}

@Entity
class Version {
	
	new() {
		
	}
	
	new(Unit unit) {
		this._unit = unit
	}	

	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)	
  	@Property
	Long id = null
	
	@Property
	@ManyToOne
	@JoinColumn(name="unit_id")  
	Unit unit

	@Property
	String name
	
	override String toString() {
		'''version ( unitId=«unit?.id», id=«id», name=«name» )'''
	}
}