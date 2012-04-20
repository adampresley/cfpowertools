<cfinclude template="../head.cfm" />

<h1>ArrayTools.min</h1>
<hr />

<p>
	The <strong>min</strong> method will take an array of pretty much anything followed
	by a bit of optional Groovy code and return the smallest item. If you don't pass in
	a Groovy script to dictate how to compare items it will try to treat it as a primitive 
	comparison.
</p>

<p>
	In this example we will take a shopping cart array of structures and find the least
	expensive item. The Groovy comparison code will return -1 or 1 if the two items
	being compared are less than or greater than each other, or it will return zero (0)
	if the items are the same. In the end the items sorted up to the top as the "smallest"
	will be returned.
</p>

<cfset cart = [
	{
		productId = 3,
		price = 14.99,
		discounted = false,
		name = "Widget 3"
	},
	{
		productId = 1,
		price = 8.50,
		discounted = false,
		name = "Widget 1"
	},
	{
		productId = 2,
		price = 9.99,
		discounted = true,
		name = "Widget 2"
	},
	{
		productId = 2,
		price = 10.99,
		discounted = false,
		name = "Widget 2"
	},
	{
		productId = 1,
		price = 5.99,
		discounted = true,
		name = "Widget 1"
	}
] />

<section class="well">
	<strong>Cart:</strong>
	<cfdump var="#cart#" expand="false" />
</section>

<!---
	Get the product with the lowest price.
--->
<cfset leastExpensiveProduct = application.arrayTools.min(cart, "item1.PRICE <=> item2.PRICE") />

<section class="well">
	<p>
		Now using the following line of code we will find the least expensive item.

		<pre>
			&lt;cfset mostExpensiveProduct = application.arrayTools.min(cart, "item1.PRICE <=> item2.PRICE") /&gt;
		</pre>
	</p>

	<cfdump var="#leastExpensiveProduct#" />
</section>

<cfinclude template="../foot.cfm" />

