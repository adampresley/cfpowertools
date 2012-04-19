<!--- NOPE, don't think it's useful --->
<cfset stringTools = createObject("java", "com.adampresley.cfpowertools.Factory").getStringTools() />

<cfsavecontent variable="sampleFile">Adam Presley|32|6.0
Maryanne Anello|40|5.10
Taylor Presley|11|5.5
Aaliyah Presley|8|5.2</cfsavecontent>

<cfset params = { output = "hi " } />
<cfsavecontent variable="closure">
	if (currentChar != "|") {
		OUTPUT += currentChar
	}
	else {
		OUTPUT += "~"
	}
	
	OUTPUT
</cfsavecontent>
<cfset stringTools.each(sampleFile, closure, params) />

<cfdump var="#params#">