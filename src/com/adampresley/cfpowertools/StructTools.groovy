package com.adampresley.cfpowertools

import java.util.regex.Pattern

/**
 * Class: StructTools
 * StructTools is a set of methods for manipulating structures in a manner that
 * is mostly missing from ColdFusion. This set of tools makes use
 * of the JSR-223 specification for embedded scripting engines in Java,
 * and uses the Groovy script engine to do these tasks.
 * 
 * Package:
 * 	com.adampresley.cfpowertools
 * 
 * Author:
 * 	Adam Presley
 */
class StructTools extends Base 
{
	public StructTools() { super() }
	
	/**
	 * Function: extract
	 * Extracts items from a structure (but leaves original intact) and into a new
	 * structure based on a list of keys. This list of keys can be an array or
	 * a string list.
	 * 
	 * Example 1:
	 * 	> <cfset source = { key1 = 1, key2 = 2, key3 = 3, key4 = 4 } />
	 * 	> <cfset result = extract(source, "KEY2,KEY4") />
	 * 	> // result == { key2 = 2, key4 = 4 }
	 * 
	 * Example 2:
	 * 	> <cfset source = { key1 = 1, key2 = 2, key3 = 3, key4 = 4 } />
	 * 	> <cfset result = extract(source, [ "KEY2", "KEY4" ]) />
	 * 	> // result == { key2 = 2, key4 = 4 }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to extract from
	 * 	keys - An array or list of keys to extract
	 * 
	 * Returns:
	 * 	An new structure of items that were listed in keys.
	 */
	def extract(source, keys) {
		if (keys instanceof java.lang.String) keys = (Collection) keys.split(",")
		source.subMap(keys)
	}
	
	/**
	 * Function: findAll
	 * Finds all structure items that match the criteria as defined by the
	 * closure code in "condition". The closure code will revieve the variable
	 * "item" for each iterated item. The "item" variable is a Map.Entry class,
	 * and thus can be reference like *item.key* and *item.value*.
	 * 
	 * Example:
	 * 	> <cfset source = { key1 = 1, key2 = 2, key3 = 3 } />
	 * 	> <cfset result = findAll(source, "item.value > 1") />
	 * 	> // result == { key2 = 2, key3 = 3 }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to iterate over
	 * 	condition - String containing code which defines search criteria
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An new structure of items that matched
	 */
	def findAll(source, condition, params) {
		source.findAll { item ->
			params.item = item
			condition.run(params) 
		}
	}
	
	def findAll(source, condition) {
		findAll(source, condition, [:])
	}
	
	/**
	 * Function: findFirst
	 * Finds the first structure items that matches the criteria as defined by the
	 * closure code in "condition". The closure code will revieve the variable
	 * "item" for each iterated item. The "item" variable is a Map.Entry class,
	 * and thus can be reference like *item.key* and *item.value*.
	 * 
	 * Example:
	 * 	> <cfset source = { key1 = 1, key2 = 2, key3 = 3 } />
	 * 	> <cfset result = findAll(source, "item.value > 1") />
	 * 	> // result == { key2 = 2 }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to iterate over
	 * 	condition - String containing code which defines search criteria
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An new structure of the matched item
	 */
	def findFirst(source, condition, params) {
		source.find { item ->
			params.item = item
			condition.run(params)
		}
	}
	
	def findFirst(source, condition) {
		findFirst(source, condition, [:])
	}
	
	/**
	 * Function: groupBy
	 * Sorts all structure items into groups determined by the code defined in
	 * "condition". The "condition" code should <cfreturn the key that the item
	 * should be grouped by. "item" is the variable name for each iterated item,
	 * and is a Map.Entry class, and thus can be reference like *item.key* and *item.value*.
	 * 
	 * Example:
	 * 	> <cfset source = { a = 1, b = 2, c = 3, d = 4, e = 5, f = 6 } />
	 * 	> <cfset result = groupBy(source, "item.value % 2") />
	 * 	> // result == { 0 = { b = 2, d = 4, f = 6 }, 1 = { a = 1, c = 3, e = 5 } }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to iterate over
	 * 	condition - String containing code which defines grouping criteria
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	A structure of the grouped items
	 */
	def groupBy(source, condition, params) {
		source.groupBy { item ->
			params.item = item
			condition.run(params) 
		}
	}
	
	def groupBy(source, condition) {
		groupBy(source, condition, [:])
	}
	
	/**
	 * Function: intersect
	 * Returns a structure of the intersection of two structures. Any items that exist in
	 * both structures are added to the resultant structure.
	 * 
	 * Example:
	 * 	> <cfset s1 = { name = "Adam", age = 32 } />
	 * 	> <cfset s2 = { name = "Adam", height = 6.0 } />
	 * 	> <cfset result = intersect(s1, s2) />
	 * 	> // result == { name = "Adam" }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	struct1 - The first structure
	 * 	struct2 - The second structure
	 * 
	 * Returns:
	 * 	A structure of intersecting items
	 */
	def intersect(struct1, struct2) {
		struct1.intersect(struct2)
	}
	
	/**
	 * Function: merge
	 * Takes an array of structures and merges the keys together. If duplicate keys exist, last 
	 * one wins.
	 * 
	 * Example:
	 * 	> <cfset s1 = { name = "Adam" } />
	 * 	> <cfset s2 = { age = 32 } />
	 * 	> <cfset s3 = { test = "Another key", age = 33 } />
	 * 	> <cfset result = merge([ s1, s2, s3 ]) />
	 * 	> // result = { name = "Adam", age = 33, test = "Another key" } />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	sourceList - An array of structures to merge
	 * 
	 * Returns:
	 * 	A newly merged structure containing all the keys from all the structures
	 * 	from the sourceList array.
	 */
	def merge(sourceList) {
		def result = [:]
		
		sourceList.each {
			result.putAll(it)
		}
		
		result
	}
	
	/**
	 * Function: minus
	 * Takes out all items found in "compareTo" from "source". Kind of like
	 * a subtraction problem.
	 * 
	 * Example:
	 * 	> <cfset source = { name = "Adam", age = 32, height = 6.0 } />
	 * 	> <cfset compareTo = { age = 32 } />
	 * 	> <cfset result = minus(source, compareTo) />
	 * 	> // result == { name = "Adam", height = 6.0 }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to remove items from
	 * 	compareTo - Structure which contains the basis of items to remove from source
	 * 
	 * Returns:
	 * 	A new structure with items removed
	 */
	def minus(source, compareTo) {
		source - compareTo
	}
	
	/**
	 * Function: reMerge
	 * Takes an array of structures and merges the keys together if the regular express "regex" condition
	 * is met. The regex is tested against the keys. If duplicate keys exist, last one wins.
	 * 
	 * Example:
	 * 	> <cfset s1 = { name = "Adam" } />
	 * 	> <cfset s2 = { age = 32, companyName = "adampresley.com" } />
	 * 	> <cfset s3 = { test = "Another key", age = 33 } />
	 * 	> <cfset result = merge([ s1, s2, s3 ], "(?i)(.*)name") />
	 * 	> // result = { name = "Adam", companyName = "adampresley.com" } />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	sourceList - An array of structures to merge
	 * 	regex - A string or Pattern object with a regular expression to match. When a structure's key
	 * 			  matches the regex it is added to the resulting struct
	 * Returns:
	 * 	A newly merged structure containing all the keys from all the structures
	 * 	from the sourceList array that match the regex.
	 */
	def reMerge(sourceList, regex) {
		def result = [:]
		def p = (regex instanceof Pattern) ? regex : ~/${regex}/
		
		sourceList.each { it.each { k, v -> if (p.matcher(k).matches()) result[k] = v } }
		result
	}
	
	/**
	 * Function: sort
	 * Sorts a structure based on closure code defined in "condition". Your closure code
	 * will have the variables "item1" and "item2" passed to it, and you must return
	 * -1, 0, or 1 to determine how items are sorted. Anything can be in the source array,
	 * making this sort function very powerful. Each item is a Map.Entry class, and thus
	 * can be reference like *item1.key* and *item1.value*.
	 * 
	 * Example:
	 * 	> <cfset source = { a = 3, b = 1, c = 2 } />
	 * 	> <cfset result = sort(source, "item1.value <=> item2.value") />
	 * 	> // result == { b = 1, c = 2, a = 3 };
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source structure to sort
	 * 	condition - String containing code telling us how to sort the structure
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	A new, sorted structure
	 */
	def sort(source, condition, params) {
		source.sort { item1, item2 ->
			params.item1 = item1
			params.item2 = item2
			condition.run(params)
		}
	}
	
	def sort(source, condition) {
		sort(source, condition, [:])
	}
}
