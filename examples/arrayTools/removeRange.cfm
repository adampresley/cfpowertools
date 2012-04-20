<cfinclude template="../head.cfm" />

<h1>ArrayTools.removeRange</h1>
<hr />

<p>
	The <strong>removeRange</strong> method will take an array of pretty much anything followed
	by a start and end range of index for the items you wish to remove, and it will remove them.
	Note that the end index is not inclusive.
</p>

<p>
	In this example we will take a shopping cart array of structures and remove both <strong>Widget 2</strong>
	items.
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

<cfset application.arrayTools.removeRange(cart, 3, 4) />

<section class="well">
	<p>
		Using the following line of code we will remove the duplicates.

		<pre>
			&lt;alteredCart = application.arrayTools.removeRange(cart, 3, 4) /&gt;
		</pre>
	</p>

	<cfdump var="#cart#" />
</section>

<cfinclude template="../foot.cfm" />
