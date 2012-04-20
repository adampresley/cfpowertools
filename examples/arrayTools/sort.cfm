<cfinclude template="../head.cfm" />

<h1>ArrayTools.sort</h1>
<hr />

<p>
	The <strong>sort</strong> method will take an array of pretty much anything followed
	by Groovy closure code that gets handed two items. The closure needs to return -1
	if the first item is less than the second, 0 if they are the same, or 1 if the first
	item is greater than the second.
</p>

<p>
	In this example we will take a shopping cart array of structures and sort them by product
	ID. If the product IDs match we will check the prices and sort by that secondarily.
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

<cfset sortedCart = application.arrayTools.sort(cart, "(item1.PRODUCTID == item2.PRODUCTID) ? (item1.PRICE <=> item2.PRICE) : (item1.PRODUCTID <=> item2.PRODUCTID)") />

<section class="well">
	<p>
		Using the following line of code we will sort our array of structures.

		<pre>
			&lt;cfset sortedCart = application.arrayTools.sort(cart, "(item1.PRODUCTID == item2.PRODUCTID) ? (item1.PRICE <=> item2.PRICE) : (item1.PRODUCTID <=> item2.PRODUCTID)") /&gt;
		</pre>
	</p>

	<cfdump var="#sortedCart#" />
</section>

<cfinclude template="../foot.cfm" />

