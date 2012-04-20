<cfinclude template="../head.cfm" />

<h1>ArrayTools.findAll</h1>
<hr />

<p>
	The <strong>findAll</strong> method will take an array of pretty much anything followed
	by a bit of Groovy code and return any items that match your criteria. The Groovy
	code must return true or false to indicate if the item currently being inspected from 
	the source array should be included in the return result.
</p>

<p>
	The ability to specify arbitrary conditions allows you to work with complex objects in
	your array. This example is an array of structures that looks similar to a shopping cart.
	In this example we get an array containing only the items that are discounted.
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
	Find all discounted products
--->
<cfset discountedProducts = application.arrayTools.findAll(cart, "item.DISCOUNTED == true") />

<section class="well">
	<p>
		Now using the following line of code we will search the shopping cart array
		for discounted items, and get a new array with only those discounted items
		in it.

		<pre>
			&lt;cfset discountedProducts = application.arrayTools.findAll(cart, "item.DISCOUNTED == true") /&gt;
		</pre>
	</p>

	<cfdump var="#discountedProducts#" />
</section>

<cfinclude template="../foot.cfm" />
