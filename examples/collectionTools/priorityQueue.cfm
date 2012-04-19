<cfset collectionTools = createObject("java", "com.adampresley.cfpowertools.Factory").getCollectionTools() />
<cfset queue = collectionTools.getPriorityQueue(25, "object1.PRIORITY <=> object2.PRIORITY") />

<cfset people = [
	{ priority = 2, name = "Adam" },
	{ priority = 3, name = "Jesse" },
	{ name = "Maryanne", priority = 1 }
] />

<strong>Array of people:</strong>
<cfdump var="#people#" />
<br /><br />

<strong>Get people by priority out of queue:</strong>
<cfset queue.addAll(people) />

<cfset item = queue.poll() />
<cfloop condition="isDefined('item')">
	<cfdump var="#item#" />
	<cfset item = queue.poll() />
</cfloop>
