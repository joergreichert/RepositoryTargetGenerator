package de.abg.jreichert.repositorytarget.database

import java.util.Set
import javax.persistence.CascadeType
import javax.persistence.Column
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
	Long id = -1L

	@Property
	Long timestamp = -1L

	//@Column(unique=true)
	@Property
	String url

	@Property
	@OneToMany(mappedBy="_location", cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	Set<Unit> units
}

@Entity
class Unit {

	@Id
  	@GeneratedValue(strategy = GenerationType.AUTO)	
  	@Property
	Long id = -1L

	@Property
	@ManyToOne
	@JoinColumn(name="location_id")  
	Location location

	@Property
	String name

	@Property
	String version
}