<cfset arrayTools = createObject("java", "com.adampresley.cfpowertools.Factory").getArrayTools() />

<!---
	Create an array of arrays. Each sub-array represents the possible
	values of a dice.
--->
<cfset twoDice = [
	[ 1, 2, 3, 4, 5, 6 ],
	[ 1, 2, 3, 4, 5, 6 ]
] />

<strong>Two dice:</strong>
<cfdump var="#twoDice#" />
 
<!---
	Calculate the possible combinations for both sets of dice.
--->
<cfset combinations = arrayTools.combinations(twoDice) />

<strong>Possible combinations:</strong>
<cfdump var="#combinations#" />
