<cfinclude template="../head.cfm" />

<h1>ArrayTools.split</h1>
<hr />

<p>
	The <strong>split</strong> method will take an array of pretty much anything followed
	by Groovy closure code and will split your array into two new arrays. To place items
	in the first array set return 1, otherwise return 0 to place items in the second array set.
</p>

<p>
	In this example we will take a shopping cart array of structures and split them into
	two array, the first containing non-discounted products, and the second discounted products.
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

<cfset splitCart = application.arrayTools.split(cart, "(item.DISCOUNTED) ? 0 : 1") />

<section class="well">
	<p>
		Using the following line of code we will split up our array.

		<pre>
			&lt;cfset splitCart = application.arrayTools.split(cart, "(item.DISCOUNTED) ? 0 : 1") /&gt;
		</pre>
	</p>

	<cfdump var="#splitCart#" />
</section>

<cfinclude template="../foot.cfm" />

