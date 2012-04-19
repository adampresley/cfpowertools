package com.adampresley.cfpowertools

/**
 * Class: ArrayTools
 * ArrayTools is a set of methods for manipulating arrays in a manner that
 * is mostly missing from ColdFusion.
 * 
 * Package:
 * 	com.adampresley.cfpowertools
 * 
 * Author:
 * 	Adam Presley
 */
class ArrayTools extends Base 
{
	
	public ArrayTools() { super() }
	
	
	/**
	 * Function: addRepeatedly
	 * Takes an item and adds it to an array as many times as specified by
	 * numToAdd.
	 * 
	 * Example:
	 * 
	 *  > <cfset a = [] />
	 *  > <cfset s = { firstName = "Adam", lastName = "Presley" } />
	 *  > <cfset addRepeatedly(a, s, 2) />
	 * 	> // a == [ { firstName = "Adam", lastName = "Presley" }, { firstName = "Adam", lastName = "Presley" } ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to add item to
	 * 	itemToAdd - The item to add to the source array
	 * 	numToAdd - The number of times to add itemToAdd
	 */
	def addRepeatedly(source, itemToAdd, numToAdd) {
		(0..<numToAdd.toInteger()).each {
			(Collection) source << itemToAdd
		}
	}
	
	/**
	 * Function: combinations
	 * Takes 2 or more arrays, and will return an array or arrays, each
	 * sub-array a set of unique combinations of all the input arrays.
	 * 
	 * Example: 
	 * 
	 *  > <cfset arrays = [ ["a", "b"], [1, 2] ] />
	 *  > <cfset combinations = combinations(arrays) />
	 * 	> // combinations == [ ["a", 1], ["b", 1], ["a", 2], ["b", 2] ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	arrayOfArrays - An array containing arrays of items to combine
	 * 
	 * Returns:
	 * 	An array of the array combinations
	 */
	def combinations(arrayOfArrays) {
		arrayOfArrays.combinations()
	}
	
	/**
	 * Function: findAll
	 * Finds all values in an array that match the criteria as defined by the
	 * closure code in "condition". The closure code will retrieve the variable
	 * "item" for each iterated item.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = findAll(source, "item % 2 == 0") />
	 * 	> // result == [ 2, 4 ]
	 * 
	 * Author:
	 * 	Adam Presley
	 *
	 * Parameters:
	 * 	source - Source array to iterate over
	 * 	condition - String containing code which defines search criteria
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An array of the matched array items
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
	 * Finds the first value in an array that matches the criteria as defined by the
	 * closure code in "condition". The closure code will retrieve the variable
	 * "item" for each iterated item.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = findFirst(source, "item > 1") />
	 * 	> // result == [ 2 ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to iterate over
	 * 	condition - String containing code which defines search criteria
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	The first matched value
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
	 * Sorts all array items into groups determined by the code defined in
	 * "condition". The "condition" code should return the key that the item
	 * should be grouped by. "item" is the variable name for each iterated item.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = groupBy(source, "item % 2") />
	 * 	> // result == { "0" = [ 2, 4 ], "1" = [ 1, 3 ] }
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to iterate over
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
	 * Returns an array of the intersection of two arrays. Any items that exist in
	 * both arrays are added to the resultant array.
	 * 
	 * Example:
	 * 	> <cfset a1 = [ 1, 2, 3, 4 ] />
	 * 	> <cfset a2 = [ 3, 4 ] />
	 * 	> <cfset result = intersect(a1, a2) />
	 * 	> // result == [ 3, 4 ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	arary1 - The first array
	 * 	array2 - The second array
	 * 
	 * Returns:
	 * 	An array of intersecting items
	 */
	def intersect(array1, array2) {
		array1.intersect(array2)
	}

	/**
	 * Function: max
	 * Returns the maximum value of the items in the source array. If the source
	 * array contains numeric values this will be the highest number. If string
	 * values are used a string comparison is done.
	 * If you provide closure code in the condition argument variables "item1" and
	 * "item2" are passed to your closure, and you return which one is highest,
	 * similar to a comparator.
	 * 
	 * Example 1:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = max(source) />
	 * 	> // result == 4
	 * 
	 * Example 2:
	 * 	> <cfset source = [ "hello", "hi", "hey" ] />
	 * 	> <cfset result = max(source, "item1.length() <=> item2.length()") />
	 * 	> // result == "hello"
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to get max of
	 * 	condition - String containing code which defines max criteria (optional)
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	The value who is biggest
	 */
	def max(source, condition, params) {
		source.max { item1, item2 ->
			params.item1 = item1
			params.item2 = item2 
			condition.run(params) 
		}
	}

	def max(source, condition) {
		max(source, condition, [:])
	}
	
	def max(source) {
		source.max()
	}
	
	/**
	 * Function: min
	 * Returns the minimum value of the items in the source array. If the source
	 * array contains numeric values this will be the lowest number. If string
	 * values are used a string comparison is done.
	 * If you provide closure code in the condition argument variables "item1" and
	 * "item2" are passed to your closure, and you return which one is highest,
	 * similar to a comparator.
	 * 
	 * Example 1:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = min(source) />
	 * 	> // result == 1
	 * 
	 * Example 2:
	 * 	> <cfset source = [ "hello", "hi", "hey" ] />
	 * 	> <cfset result = min(source, "item1.length() <=> item2.length()") />
	 * 	> // result == "hi"
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to get min of
	 * 	condition - String containing code which defines min criteria (optional)
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	The value who is smallest
	 */
	def min(source, condition, params) {
		source.min { item1, item2 ->
			params.item1 = item1
			params.item2 = item2 
			condition.run(params) 
		}
	}
	
	def min(source, condition) {
		min(source, condition, [:])
	}
	
	def min(source) {
		source.min()
	}
	
	/**
	 * Function: minus
	 * Takes out all items found in "compareTo" from "source". Kind of like
	 * a subtraction problem.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset compareTo = [ 2, 4 ] />
	 * 	> <cfset result = minus(source, compareTo) />
	 * 	> // result == [ 1, 3 ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to remove items from
	 * 	compareTo - Array which contains the basis of items to remove from source
	 * 
	 * Returns:
	 * 	An array of items left after the removal
	 */
	def minus(source, compareTo) {
		((Collection) source - (Collection) compareTo)
	}

	/**
	 * Function: permutations
	 * Iterates over all permutation (unique) of an array and returns an
	 * array of arrays, each sub-array being a permutation set.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2 ] />
	 * 	> <cfset result = permutations(source) />
	 * 	> // result == [ [1, 2], [2, 1] ];
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to permutate
	 * 
	 * Returns:
	 * 	An array of arrays. Sounds confusing!
	 */
	def permutations(source) {
		def result = []
		source.eachPermutation { item -> result << item }
		result
	}

	/**
	 * Function: remove
	 * Removes items from an array that are matched according to the code
	 * closure in "condition". The closure code will have a variable named
	 * "item" for each item in the array, and will return true/false if
	 * the item should be removed from the array.
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = remove(source, "item % 2") />
	 * 	> // result == [ 2, 4 ];
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to remove stuff from
	 * 	condition - String containing code which determines if an item should be removed
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An array items left over
	 */
	def remove(source, condition, params) {
		source.removeAll { item->
			params.item = item 
			condition.run(params) 
		}
	}

	def remove(source, condition) {
		remove(source, condition, [:])
	}
	
	/**
	 * Function: removeDuplicates
	 * Removes duplicate items from an array. If you pass closure code into "condition"
	 * there are two possible modes: single item, and comparator, which is dictated
	 * by the third argument (defaults to single). In single mode your closure gets
	 * a variable named "item" and you return true/false if the item is to be removed.
	 * When in comparator mode each item is passed in as "item1" and "item2", and is
	 * treated as a comparator.
	 * 
	 * Example 1:
	 * 	> <cfset source = [ 1, 2, 2, 3 ] />
	 * 	> <cfset result = removeDuplicates(source) />
	 * 	> // result == [ 1, 2, 3 ];
	 * 
	 * Example 2:
	 * 	> <cfset source = [ 1, 2, 2, 4 ] />
	 * 	> <cfset result = removeDuplicates(source, "item % 2") />
	 * 	> // result == [ 2, 4 ]
	 * 
	 * Example 3:
	 * 	> <cfset source = [ 1, 2, 2, 3, 3, 4 ] />
	 * 	> <cfset result = removeDuplicates(source, "item1 <=> item2", true) />
	 * 	> // result == [ 1, 2, 3, 4 ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to make unique
	 * 	condition - String containing code which determines if an item should be removed
	 * 	comparatorMode - True/false to run as a comparator. Defaults to false
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An array items left over
	 */
	def removeDuplicates(source, condition, comparatorMode, params) {
		def result
		
		if (comparatorMode == true) {
			result = source.unique { item1, item2 ->
				params.item1 = item1
				params.item2 = item2 
				condition.run(params) 
			}
		}
		else {
			result = source.unique { item ->
				params.item = item 
				condition.run(params) 
			}
		}
		
		result
	}
	
	def removeDuplicates(source, condition, comparatorMode) {
		removeDuplicates(source, condition, comparatorMode, [:])
	}
	
	def removeDuplicates(source, condition) {
		removeDuplicates(source, condition, false, [:])
	}
	
	def removeDuplicates(source) {
		source.unique()
	}

	/**
	* Function: removeRange
	* Removes items from an array starting at startIndex, deleting up to
	* endIndex (exclusive). If endIndex is zero or less, the function will only
	* delete the item at startIndex. If endIndex is less than or equal to 
	* startIndex then the function will only delete the item at startIndex.
	* If there are no items in the array the function simply returns.
	*
	* Example:
	*
	*  > <cfset a = [ 1, 2, 3, 4, 5, 6 ] />
	*  > <cfset removeRepeatedly(a, 2, 4) />
	* 	> // a == [ 1, 4, 5, 6 ]
	*
	* Author:
	* 	Adam Presley
	*
	* Parameters:
	* 	source - Source array to remove items from
	* 	startIndex - The starting index to start removing at
	* 	endIndex - The index of where to stop removing
	*/
  def removeRange(source, startIndex, endIndex) {
	  if (source.size() > 0) { 
		  def actualStart = startIndex.toInteger() - 1
		  def actualEnd = endIndex.toInteger() - 1
		  
		  if (actualEnd <= actualStart || actualEnd <= 1) {
			  /*
			   * Railo doesn't have a removeElementAt, but it does have a remove.
			   */
			  if (((Collection) source).metaClass.respondsTo("removeElementAt", Integer)) {
				  source.removeElementAt(actualStart)
			  }
			  else {
				  source.remove(actualStart)
			  }
		  }
		  else {
			  /*
			   * Railo doesn't have a removeRange. Instead just loop
			   */
			  if (((Collection) source).metaClass.respondsTo("removeRange", Integer, Integer)) {
				  source.removeRange(actualStart, actualEnd)
			  }
			  else {
				  (actualStart..<actualEnd).each {
					  source.remove(actualStart)
				  }
			  }
		  }
	  }
  }
  

	/**
	 * Function: sort
	 * Sorts an array based on closure code defined in "condition". Your closure code
	 * will have the variables "item1" and "item2" passed to it, and you must return
	 * -1, 0, or 1 to determine how items are sorted. Anything can be in the source array,
	 * making this sort function very powerful.
	 * 
	 * Example:
	 * 	> <cfset source = [ 2, 1, 3, 5, 4 ] />
	 * 	> <cfset result = sort(source, "item1 <=> item2") />
	 * 	> // result == [ 1, 2, 3, 4, 5 ];
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to sort
	 * 	condition - String containing code telling us how to sort the array
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An array or sorted items
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
	
	/**
	 * Function: split
	 * Splits a source array into two different arrays based on the criteria provided
	 * in your "condition" closure. The closure will be passed the variable "item".
	 * 
	 * Example:
	 * 	> <cfset source = [ 1, 2, 3, 4 ] />
	 * 	> <cfset result = split(source, "item % 2") />
	 * 	> // result == [ [2,4], [1,3] ];
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source array to split up
	 * 	condition - String containing code which determines how to split the source array
	 * 	params - An optional structure of parameters to send to your closure code
	 * 
	 * Returns:
	 * 	An array of arrays
	 */
	def split(source, condition, params) {
		source.split { item ->
			params.item = item 
			condition.run(params) 
		}
	}
	
	def split(source, condition) {
		split(source, condition, [:])
	}
}
