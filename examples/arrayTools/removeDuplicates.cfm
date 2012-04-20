<cfinclude template="../head.cfm" />

<h1>ArrayTools.removeDuplicates</h1>
<hr />

<p>
	The <strong>removeDuplicates</strong> method will take an array of pretty much anything followed
	by Groovy closure code that will return false to remove the current item as a duplicate, or true 
	to keep the item. You can also pass an additional value to use the closure code as a comparator
	so you can determine which item to remove.
</p>

<p>
	In this example we will take a shopping cart array of structures and remove all the duplicates
	based on if the product IDs match. If the two product IDs passed into the Groovy closure code
	match we will return false.
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

<cfset alteredCart = application.arrayTools.removeDuplicates(cart, "(item1.PRODUCTID == item2.PRODUCTID) ? 0 : 1", true) />

<section class="well">
	<p>
		Using the following line of code we will remove the duplicates.

		<pre>
			&lt;cfset alteredCart = application.arrayTools.removeDuplicates(cart, "(item1.PRODUCTID == item2.PRODUCTID) ? 1 : 0", true) /&gt;
		</pre>
	</p>

	<cfdump var="#alteredCart#" />
</section>

<cfinclude template="../foot.cfm" />
