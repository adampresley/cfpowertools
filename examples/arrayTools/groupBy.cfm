<cfinclude template="../head.cfm" />

<h1>ArrayTools.groupBy</h1>
<hr />

<p>
	The <strong>groupBy</strong> method will take an array of pretty much anything followed
	by a bit of Groovy code and return a structure of grouped arrays. The Groovy
	code must return a value to be used as your grouping key. This can be anything you
	wish.
</p>

<p>
	In this example we will take a shopping cart array of structures and group them by
	discounted and non-discounted products. Each key, <strong>DISCOUNTED</strong>
	and <strong>NOTDISCOUNTED</strong>, will contain an array of the items that fit
	in each group.
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
	Group the items in the cart by discount
--->
<cfset grouped = application.arrayTools.groupBy(cart, "(item.DISCOUNTED) ? 'DISCOUNTED' : 'NOTDISCOUNTED'") />

<section class="well">
	<p>
		Now using the following line of code we will create our two groups.

		<pre>
			&lt;cfset grouped = application.arrayTools.groupBy(cart, "(item.DISCOUNTED) ? 'DISCOUNTED' : 'NOTDISCOUNTED'") /&gt;
		</pre>
	</p>

	<cfdump var="#grouped#" />
</section>

<cfinclude template="../foot.cfm" />
