package de.abg.jreichert.repositorytarget

import org.eclipse.xtext.xbase.lib.Functions
import de.abg.jreichert.repositorytarget.definition.Unit
import java.util.List
import java.util.regex.Pattern

class TargetFilter {
	
	def unitFilters() {
		val list = <Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>>>newArrayList()
		list.addAll(sdkFilter())
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
		val Functions$Function1<String, Boolean> filterVersion = [ versionPattern.matcher(it).matches ]
		val List<Functions$Function1<String, Boolean>> versionFilters = newArrayList(filterVersion)
		val List<Functions$Function1<String, Boolean>> emptyVersionFilters = newArrayList()
		val (Unit) => boolean targetIdFilter = [ expectedTargetId.equals(targetId) ]
		val Functions$Function1<Unit, List<Functions$Function1<String, Boolean>>> unitFilter
			= [ if(targetIdFilter.apply(it)) versionFilters else emptyVersionFilters ]
		newArrayList(unitFilter)
	}
}