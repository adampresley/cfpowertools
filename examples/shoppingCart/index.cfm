<!---
	Check for classes and methods and stuff
<cfset clazz = arrayNew(1).getClass() />
<cfset methods = clazz.getMethods() />
<cfloop array="#methods#" index="method">
	<cfoutput>#method.toString()#<br /></cfoutput>
</cfloop>
--->

<cfparam name="form.productId" default="" />
<cfparam name="form.productName" default="" />
<cfparam name="form.basePrice" default="0.00" />
<cfparam name="form.quantity" default="1" />
<cfparam name="form.startIndex" default="1" />
<cfparam name="form.endIndex" default="0" />

<cfset shoppingCart = createObject("component", "ShoppingCart").init() />

<cfif structKeyExists(form, "btnAdd")>
	<!---
	<cfset product = {
		productId = form.productId,
		productName = form.productName,
		basePrice = form.basePrice
	} />

	<cfset shoppingCart.add(product, form.quantity) />
	--->
	<cfset shoppingCart.add(
		application.structTools.extract(form, "PRODUCTID,PRODUCTNAME,BASEPRICE"), 
		form.quantity
	) />

<cfelseif structKeyExists(form, "btnFill")>
	<cfset session.cart.items = [
		{
			productId = 1,
			productName = "Item 1",
			basePrice = 10.00
		},
		{
			productId = 1,
			productName = "Item 1",
			basePrice = 10.00
		},
		{
			productId = 3,
			productName = "Item 3",
			basePrice = 12.00
		},
		{
			productId = 2,
			productName = "Item 2",
			basePrice = 15.00
		},
		{
			productId = 3,
			productName = "Item 3",
			basePrice = 12.00
		}
	] />
	
<cfelseif structKeyExists(form, "btnRemove")>
	<cfset shoppingCart.remove(form.startIndex, form.endIndex) />

<cfelseif structKeyExists(form, "clearCart")>
	<cfset structDelete(session, "cart") />
	<cfset shoppingCart.init() />
</cfif>



<cfoutput>

<fieldset>
	<legend>Add A Product</legend>
	
	<form name="frmAdd" id="frmAdd" method="post">
		Product ID: <input type="text" name="productId" id="productId" value="#form.productId#" /><br />
		Product Name: <input type="text" name="productName" id="productName" value="#form.productName#" /><br />
		Base Price: <input type="text" name="basePrice" id="basePrice" value="#form.basePrice#" /><br />
		Quantity: <input type="text" name="quantity" id="quantity" value="#form.quantity#" /><br />
		<br />
		
		<input type="submit" name="btnAdd" id="btnAdd" value="Add Product" />
		<input type="submit" name="btnFill" id="btnFill" value="Fill Me Up!" />
	</form>
</fieldset>


<fieldset>
	<legend>Remove Products</legend>
	
	<form name="frmAdd" id="frmAdd" method="post">
		Start Index: <input type="text" name="startIndex" id="startIndex" value="#form.startIndex#" /><br />
		End Index: <input type="text" name="endIndex" id="endIndex" value="#form.endIndex#" /><br />
		<br />
		
		<input type="submit" name="btnRemove" id="btnRemove" value="Remove Products" />
		<input type="submit" name="clearCart" id="clearCart" value="Clear Cart" />
	</form>
</fieldset>

<br /><br />

<fieldset>
	<legend>Shopping Cart Session:</legend>
	<cfdump var="#session.cart#" />
</fieldset>

</cfoutput>