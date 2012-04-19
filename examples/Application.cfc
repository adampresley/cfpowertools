<cfcomponent>
	
	<cfset this.name = "CFPowerToolsQuickStart" />
	<cfset this.sessionManagement = true />
	
	<cffunction name="onApplicationStart" returntype="boolean" access="public" output="false">
		<cfset var cfPowerToolsFactory = createObject("java", "com.adampresley.cfpowertools.Factory") />
		
		<cfset structClear(application) />
		<cfset application.name = "CFPowerToolsQuickStart_#getTickCount()#" />
		
		<cfset application.arrayTools = cfPowerToolsFactory.getArrayTools() />
		<cfset application.structTools = cfPowerToolsFactory.getStructTools() />
		<cfset application.collectionTools = cfPowerToolsFactory.getCollectionTools() />
		<cfset application.stringTools = cfPowerToolsFactory.getStringTools() />
		
		<cfreturn true />
	</cffunction>
	
	<cffunction name="onRequest" returntype="void" access="public" output="true">
		<cfargument name="targetPage" type="string" required="true" />

		<cfif structKeyExists(url, "reloadapp")>
			<cfset onApplicationStart() />
		</cfif>
		
		<cfset getPageContext().getOut().clearBuffer() /><cfinclude template="#arguments.targetPage#" />
	</cffunction>
	
</cfcomponent>