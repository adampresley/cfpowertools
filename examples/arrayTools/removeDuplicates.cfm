<cfset arrayTools = createObject("java", "com.adampresley.cfpowertools.Factory").getArrayTools() />

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

<strong>Cart:</strong>
<cfdump var="#cart#" expand="false" />

<strong>Cart without duplicates:</strong>
<cfset alteredCart = arrayTools.removeDuplicates(cart, "(item1.PRODUCTID == item2.PRODUCTID) ? 0 : 1", true) />
<cfdump var="#alteredCart#" />
