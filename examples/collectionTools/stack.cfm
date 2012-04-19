<cfset collectionTools = createObject("java", "com.adampresley.cfpowertools.Factory").getCollectionTools() />
<cfset stack = collectionTools.getStack() />

<cfset people = [
	"Adam",
	"Maryanne",
	"Taylor",
	"Aaliyah"
] />

<cfloop array="#people#" index="item">
	<cfoutput>Pushing #item# onto stack...</cfoutput><br />
	<cfset stack.push(item) />
</cfloop>

<br />
<br />

<cfloop condition="!stack.isEmpty()">
	<cfset item = stack.pop() />
	<cfoutput>#item# popped off the stack.<br /></cfoutput>
</cfloop>
