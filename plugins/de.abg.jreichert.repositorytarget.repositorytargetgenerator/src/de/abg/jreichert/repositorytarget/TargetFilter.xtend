package de.abg.jreichert.repositorytarget

import de.abg.jreichert.repositorytarget.definition.Unit
import java.util.List
import java.util.regex.Pattern

class TargetFilter {
	
	def unitFilters() {
		val list = sdkFilter()
		list.addAll(pdeFilter())
		list
	}

	def sdkFilter() {
		unitFilters("org.eclipse.sdk.feature.group", "\\d+\\.\\d+\\.\\d+\\.v\\d{8}-\\d{4}-.*")
	}
	
	def pdeFilter() {
		unitFilters("org.eclipse.pde.feature.group", "3.8.\\d+\\.v\\d{8}-\\d{4}-.*")
	}
	
	def unitFilters(String expectedTargetId, String versionRegex) {
		val versionPattern = Pattern::compile(versionRegex)
		val (String) => boolean filterVersion = [ versionPattern.matcher(it).matches ]
		val versionFilters = newArrayList(filterVersion)
		val emptyVersionFilters = <(String) => boolean>newArrayList()
		val (Unit) => boolean targetIdFilter = [ expectedTargetId.equals(targetId) ]
		val (Unit) => List<(String) => boolean> unitFilter
			= [ if(targetIdFilter.apply(it)) versionFilters else emptyVersionFilters ]
//		val List<(Unit) => List<(String) => boolean>> list = newArrayList(unitFilter)
		val List<(Unit) => List<(String) => boolean>> list = newArrayList()
		list.add(unitFilter)
		list
	}
}