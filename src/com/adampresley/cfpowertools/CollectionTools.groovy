package com.adampresley.cfpowertools

/**
 * Class: CollectionTools
 * CollectionTools is a set of methods for retrieving collection objects
 * in the Java Collections Framework that provide advanced collection
 * manipulation and management. This set of tools makes use
 * of the JSR-223 specification for embedded scripting engines in Java,
 * and uses the Groovy script engine to do these tasks.
 * 
 * Package:
 * 	com.adampresley.cfpowertools
 * 
 * Author:
 * 	Adam Presley
 */
class CollectionTools extends Base 
{
	public CollectionTools() { super() }
	
	/**
	 * Function: getArrayDeque
	 * A resizable array implementation that supports both queue or stack. For more
	 * information see http://download.oracle.com/javase/6/docs/api/java/util/ArrayDeque.html
	 * 
	 * Example 1 (queue):
	 * 	> <cfset q = getArrayDeque() />
	 * 	> <cfset q.add("Adam") />
	 * 	> <cfset q.add("Sr. Software Engineer") />
	 * 	> 
	 * 	> <cfset item = q.poll() />
	 * 	> <cfloop condition="isDefined('item')">
	 * 	>    <cfdump var="#item#" />
	 * 	>    <cfset item = q.poll() />
	 * 	> </cfloop>
	 * 
	 * Example 2 (stack):
	 * 	> <cfset s = getArrayDeque() />
	 * 	> <cfset s.push("Adam") />
	 * 	> <cfset s.push("Sr. Software Engineer") />
	 * 	> 
	 * 	> <cfset item = q.pop() />
	 * 	> <cfloop condition="isDefined('item')">
	 * 	>    <cfdump var="#item#" />
	 * 	>    <cfset item = q.pop() />
	 * 	> </cfloop>
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	numElements - The number of elements to support initially (optional)
	 * 
	 * Returns:
	 * 	A new ArrayDeque object
	 */
	def getArrayDeque(numElements) {
		new ArrayDeque(numElements.toInteger())
	}
	
	def getArrayDeque() {
		new ArrayDeque()
	}
	
	/**
	 * Function: getLinkedList
	 * A resizable array implementation that supports both queue or stack. This is very similar
	 * to the ArrayDeque, except it supports bi-directional access. For more
	 * information see http://download.oracle.com/javase/6/docs/api/java/util/LinkedList.html
	 * 
	 * Example 1 (queue):
	 * 	> <cfset q = getLinkedList() />
	 * 	> <cfset q.add("Adam") />
	 * 	> <cfset q.add("Sr. Software Engineer") />
	 * 	> 
	 * 	> <cfset item = q.poll() />
	 * 	> <cfloop condition="isDefined('item')">
	 * 	>    <cfdump var="#item#" />
	 * 	>    <cfset item = q.poll() />
	 * 	> </cfloop>
	 * 
	 * Example 2 (stack):
	 * 	> <cfset s = getLinkedList() />
	 * 	> <cfset s.push("Adam") />
	 * 	> <cfset s.push("Sr. Software Engineer") />
	 * 	> 
	 * 	> <cfset item = q.pop() />
	 * 	> <cfloop condition="isDefined('item')">
	 * 	>    <cfdump var="#item#" />
	 * 	>    <cfset item = q.pop() />
	 * 	> </cfloop>
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Returns:
	 * 	A new LinkedList object
	 */
	def getLinkedList() {
		new LinkedList()
	}
	
	/**
	 * Function: getPriorityQueue
	 * A queue collection of items that are ordered according to either natural order,
	 * or the order specified in comparator code. For more information on the
	 * usage of this queue see http://download.oracle.com/javase/6/docs/api/java/util/PriorityQueue.html
	 * 
	 * Example:
	 * 	> <cfset q = getPriorityQueue(25, "object1.priority <=> object2.priority") />
	 * 	> <cfset q.add({ name = "Adam", priority = 2 }) />
	 * 	> <cfset q.add({ name = "Jesse", priority = 1 }) />
	 * 	>
	 * 	> <cfset item = q.poll() />
	 * 	> <cfloop condition="isDefined('item')">
	 * 	>    <cfdump var="#item#" />
	 * 	>    <cfset item = q.poll() />
	 *		> </cfloop>
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	initialCapacity - The number of initial elements the queue can store (optional)
	 * 	comparatorCode - The code that compares two object and dictates order (optional)
	 * 	params - A set of parameters to send to the comparator code (optional)
	 * 
	 * Returns:
	 * 	A new priority queue object
	 */
	def getPriorityQueue(initialCapacity, comparatorCode, params) {
		new PriorityQueue(initialCapacity.toInteger(), new Comparator<Object>() { 
			public int compare(Object object1, Object object2) {
				params.object1 = object1
				params.object2 = object2
				comparatorCode.run(params)
			}
		})
	}

	def getPriorityQueue(initialCapacity, comparatorCode) {
		getPriorityQueue(initialCapacity, comparatorCode, [:])
	}
	
	def getPriorityQueue(initialCapacity) {
		new PriorityQueue(initialCapacity.toInteger())
	}

	def getPriorityQueue() {
		new PriorityQueue()
	}
	
	/**
	 * Function: getQueue
	 * A convenience method for getting an ArrayDeque. See <getArrayDeque>
	 * 
	 * Example:
	 *  	> <cfset queue = getQueue() />
	 *  
	 * Author:
	 * 	Adam Presley
	 */
	def getQueue() {
		getArrayDeque()
	}
	
	/**
	 * Function: getStack
	 * A convenience method for getting an ArrayDeque. See <getArrayDeque>
	 * 
	 * Example:
	 *  	> <cfset stack = getStack() />
	 *  
	 * Author:
	 * 	Adam Presley
	 */
	def getStack() {
		getArrayDeque()
	}
	
	/**
	 * Function: getTreeMap
	 * A sorted map (or structure). This structure maintains order based on either natural order
	 * of its keys, or by custom comparator code. For more information on the usage of this TreeMap 
	 * see http://download.oracle.com/javase/6/docs/api/java/util/TreeMap.html
	 * 
	 * Example:
	 * 	> <cfset t = getTreeMap() />
	 * 	> <cfset t.put("name", "Adam") />
	 * 	> <cfset t.put("title", "Sr. Software Engineer") />
	 * 	> <cfset t.put("age", 32) />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	comparatorCode - The code that compares two object and dictates order (optional)
	 * 	params - A set of parameters to send to the comparator code (optional)
	 * 
	 * Returns:
	 * 	A new TreeMap object
	 */
	def getTreeMap(comparatorCode, params) {
		new TreeMap(new Comparator<Object>() {
			public int compare(Object key1, Object key2) {
				params.key1 = key1
				params.key2 = key2
				comparatorCode.run(params)
			}
		})
	}

	def getTreeMap(comparatorCode) {
		getTreeMap(comparatorCode, [:])
	}
	
	def getTreeMap() {
		new TreeMap()
	}
}
