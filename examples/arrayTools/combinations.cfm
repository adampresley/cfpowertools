<cfinclude template="../head.cfm" />

<h1>ArrayTools.combinations</h1>
<hr />

<p>
	The <strong>combinations</strong> method will take two input arrays and return
	a new array which contains arrays of all the possible combinations of the values
	contained in the source arrays. This example will show all the possible combinations
	for the values of two dice. Given two source arrays of <strong>[ 1, 2, 3, 4, 5, 6 ]</strong>
	the combinations are:
</p>

<!---
	Create an array of arrays. Each sub-array represents the possible
	values of a dice.
--->
<cfset twoDice = [
	[ 1, 2, 3, 4, 5, 6 ],
	[ 1, 2, 3, 4, 5, 6 ]
] />

<section class="well"> 
	<!---
		Calculate the possible combinations for both sets of dice.
	--->
	<cfset combinations = application.arrayTools.combinations(twoDice) />

	<strong>Possible combinations:</strong>
	<cfdump var="#combinations#" />
</section>

<cfinclude template="../foot.cfm" />
