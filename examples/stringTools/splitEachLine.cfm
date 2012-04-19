<cfset stringTools = createObject("java", "com.adampresley.cfpowertools.Factory").getStringTools() />

<cfsavecontent variable="sampleFile">Adam Presley|32|6.0
Maryanne Anello|40|5.10
Taylor Presley|11|5.5
Aaliyah Presley|8|5.2</cfsavecontent>

<cfset params = { output = [] } />
<cfset stringTools.splitEachLine(sampleFile, "\|", "OUTPUT << [ name: tokens[0], age: tokens[1], height: tokens[2] ]", params) />

<cfdump var="#params.output#">