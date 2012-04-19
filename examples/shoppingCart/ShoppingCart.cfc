<cfcomponent output="false">

	<cffunction name="init" access="public" output="false">
		<!---
			Initialize the session structure that holds our items.
		--->
		<cfif !structKeyExists(session, "cart")>
			<cfset session.cart = {
				items = []
			} />
		</cfif>
		
		<cfreturn this />
	</cffunction>
	
	<cffunction name="add" access="public" output="false">
		<cfargument name="product" type="struct" required="true" />
		<cfargument name="quantity" type="numeric" required="false" default="1" />

		<!---
		<cfset var index = 0 />
		
		<cfloop from="1" to="#arguments.quantity#" index="index">
			<cfset arrayAppend(session.cart.items, arguments.product) />
		</cfloop>
		--->
		
		<!---
			CFPowerTools way:
		--->
		<cfset application.arrayTools.addRepeatedly(session.cart.items, arguments.product, arguments.quantity) />
		<cfreturn this />
	</cffunction>
	
	<cffunction name="remove" access="public" output="false">
		<cfargument name="startIndex" type="numeric" required="true" />
		<cfargument name="endIndex" type="numeric" required="false" default="0" />
		
		<!---
		<cfset var index = 0 />
		
		<cfif arrayLen(session.cart.items) GT 0>
			<cfif arguments.endIndex LTE arguments.startIndex OR arguments.endIndex LTE 1>
				<cfset arrayDeleteAt(session.cart.items, arguments.startIndex) />
				
			<cfelse>
				<cfloop from="0" to="#arguments.endIndex - 1#" index="index">
					<cfset arrayDeleteAt(session.cart.items, arguments.startIndex) />
				</cfloop>
			</cfif>
		</cfif>
		--->
		
		<!---
			CFPowerTools way:
		--->
		<cfset application.arrayTools.removeRange(session.cart.items, arguments.startIndex, arguments.endIndex) />
		<cfreturn this />
	</cffunction>
	
</cfcomponent>