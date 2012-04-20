<cfinclude template="../head.cfm" />

<h1>ArrayTools.minus</h1>
<hr />

<p>
	The <strong>minus</strong> method will take an array of pretty much anything followed
	by another array containing items that match what you wish to remove from the first array.
</p>

<p>
	In this example we will take a shopping cart array of structures and remove a single
	item. The second array contains a structure matching the item we wish to remove from
	the first array.
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

<cfset toRemove = [ 	
	{
		productId = 2,
		price = 9.99,
		discounted = true,
		name = "Widget 2"
	}
] />

<section class="well">
	<strong>Cart:</strong>
	<cfdump var="#cart#" expand="false" />
</section>

<section class="well">
	<strong>To Remove:</strong>
	<cfdump var="#toRemove#" expand="false" />
</section>

<!---
	Subtract two arrays
--->
<cfset result = application.arrayTools.minus(cart, toRemove) />

<section class="well">
	<p>
		Using the following lines of code we will remove a complex item from the first array.

		<pre>
			&lt;cfset toRemove = [ 
				{
					productId = 2,
					price = 9.99,
					discounted = true,
					name = "Widget 2"
				}
			] /&gt;

			&lt;cfset result = application.arrayTools.minus(cart, toRemove) /&gt;
		</pre>
	</p>

	<cfdump var="#result#" />
</section>

<cfinclude template="../foot.cfm" />
