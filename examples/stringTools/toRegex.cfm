<cfset stringTools = createObject("java", "com.adampresley.cfpowertools.Factory").getStringTools() />

<cfset p = stringTools.toRegex("(?i)(.*?)da(.*?)") />
<cfset source = "Adam was here" />

<cfdump var="#p.matcher(source).matches()#">