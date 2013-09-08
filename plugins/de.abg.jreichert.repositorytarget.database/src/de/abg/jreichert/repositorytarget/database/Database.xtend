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

@Entity
class Location {
	
	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)	
  	@Property
	long id = 1L	

	@Property
	long timestamp = -1L

	//@Column(unique=true)
	@Property
	String url

	@Property
	@OneToMany(mappedBy="_location", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	Set<Unit> units = <Unit>newHashSet
}

@Entity
class Unit {
	
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
	Set<Version> versions = <Version>newHashSet
}

@Entity
class Version {

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
}