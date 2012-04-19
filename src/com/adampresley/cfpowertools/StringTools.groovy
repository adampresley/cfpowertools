package com.adampresley.cfpowertools

/**
 * Class: StringTools
 * StringTools is a set of methods for manipulating strings in a manner that
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
class StringTools extends Base 
{
	public StringTools() { super() }
	
	/**
	 * Function: denormalize
	 * Returns a string with lines terminated by platform-specific line separator
	 * (LF, CR, CR/LF).
	 * 
	 * Example 1:
	 * 	> <cfset source = fileRead("C:/someUnixFile.txt") />
	 * 	> <cfset result = denormalize(source) />
	 * 	> // result == some denormalized string
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source string to denormalize
	 * 
	 * Returns:
	 * 	An newly denormalized string with line enders appropriate for the platform
	 * 	the code is executed on.
	 */
	def denormalize(source)
	{
		((String) source).denormalize()
	}
	
	/**
	* Function: eachLine
	* Iterates over a string, breaking it up by line ender.
	*
	* Example:
	* 	> <cfsavecontent variable="source">
	*  > This is line 1
	*  > This is line 2
	*  > </cfsavecontent>
	*  >
	*  > <cfsavecontent variable="closureCode">
	*  > if (line.contains("1")) OUTPUT << line
	*  > </cfsavecontent>
	*  >
	*  > <cfset params = { output = [] } />
	* 	> <cfset result = eachLine(source, closureCode, params) />
	* 	> // result == an array containing one line from the source
	*
	* Author:
	* 	Adam Presley
	*
	* Parameters:
	* 	source - Source string to iterate over (each line)
	*	closureCode - The Groovy code to execute for each line in the string
	*	params - An optional structure of parameters to send to the closure code
	*/
	def eachLine(source, closureCode, params) {
		((String) source).eachLine { line ->
			params.line = line
			closureCode.run(params)
		}
	}
	
	def eachLine(source, closureCode) {
		eachLine(source, closureCode, [:])
	}
	
	/**
	 * Function: normalize
	 * Returns a string with lines terminated by a linefeed. Existing carriage returns
	 * or CF/LF combinations are converted to linefeeds.
	 * 
	 * Example 1:
	 * 	> <cfset source = fileRead("C:/someWindowsFile.txt") />
	 * 	> <cfset result = normalize(source) />
	 * 	> // result == some normalized string
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source string to normalize
	 * 
	 * Returns:
	 * 	An newly normalized string with line enders all converted to linefeeds.
	 */
	def normalize(source) {
		((String) source).normalize()
	}
	
	/**
	* Function: splitEachLine
	* Iterates over a string, breaking it up by line ender, then further breaking each string line 
	* into tokens by a given regular expression.
	*
	* Example:
	* 	> <cfsavecontent variable="source">
	*  > Adam|Presley|Programmer
	*  > Taylor|Presley|Student
	*  > </cfsavecontent>
	*  >
	*  > <cfsavecontent variable="closureCode">
	*  > OUTPUT << [ firstName: tokens[0], lastName: tokens[1], category: tokens[2] ]
	*  > </cfsavecontent>
	*  >
	*  > <cfset params = { output = [] } />
	* 	> <cfset result = splitEachLine(source, closureCode, params) />
	* 	> // result == an array of structures, each structure having keys "firstName", "lastName", and "category"
	*
	* Author:
	* 	Adam Presley
	*
	* Parameters:
	* 	source - Source string to iterate over (each line)
	*  regex - Regex used to break each line into tokens, or columns
	*	closureCode - The Groovy code to execute for each line in the string
	*	params - An optional structure of parameters to send to the closure code
	*/
	def splitEachLine(source, regex, closureCode, params) {
		source.splitEachLine regex, { tokens ->
			params.tokens = tokens
			closureCode.run(params)
		}
	}
	
	def splitEachLine(source, regex, closureCode) {
		splitEachLine(source, regex, closureCode, [:])
	}
	
	/**
	 * Function: stripIndent
	 * This method will strip all leading spaces for each line in a string.
	 * 
	 * Example:
	 * 	> <cfsavecontent variable="source">
	 * 	>      Hi all, it is nice to see you
	 * 	> using CFPowerTools. A nifty library
	 * 	>    for those who need to manipulate more than
	 * 	>  just query data.
	 * 	> </cfsavecontent>
	 * 	>
	 * 	> <cfset result = stripIndent(source) />
	 * 	> // result == no leading spaces on any lines
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - Source string to strip leading spaces from
	 * 
	 * Returns:
	 * 	A new string, no leading spaces attached.
	 */
	def stripIndent(source) {
		source.stripIndent()
	}
	
	/**
	 * Function: toArray
	 * This method converts a string to an array of characters. Java has a built-in
	 * method on the String class calledtoCharArray()that turns a string
	 * into a primitive array. However we ColdFusion peeps like to work with
	 * CF arrays, or Vectors, so this gives us a Collection object.
	 * 
	 * Example:
	 * 	> <cfset source = "Test" />
	 * 	> <cfset result = toArray(source) />
	 * 	> // result == [ "T", "e", "s", "t" ]
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - The source string to convert
	 * 
	 * Returns:
	 * 	A collection/array of each character in the source string.
	 */	
	def toArray(source) {
		def a = source.toCharArray()
		def result = []
		
		result.addAll(a)
		result
	}

	/**
	 * Function: toRegex
	 * A method to convert a string to a Java Pattern object without having to go
	 * through the fuss of createObject.
	 * 
	 * Example:
	 * 	> <cfset pattern = toRegex("(?i)[^0-9a-z_]") />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - A string to turn into a Java Pattern object
	 * 
	 * Returns:
	 * 	A Java Pattern object from the regex string
	 */
	def toRegex(source) {
		def pattern = ~/source/
		pattern
	}
	
	/**
	 * Function: toURI
	 * This method parses a string into a URI object. See
	 * http://download.oracle.com/javase/1.5.0/docs/api/java/net/URI.html for more information.
	 * 
	 * Example:
	 * 	> <cfset uriString = "mailto:adam@adampresley.com" />
	 * 	> <cfset uriObject = toURI(uriString) />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - A string to parse into a URI
	 * 
	 * Returns:
	 * 	A Java URI object
	 */
	def toURI(source) {
		((String) source).toURI()
	}
	
	/**
	 * Function: toURL
	 * This method parses a string into a URL object. See
	 * http://download.oracle.com/javase/1.5.0/docs/api/java/net/URL.html for more information.
	 * 
	 * Example:
	 * 	> <cfset urlString = "http://www.adampresley.com" />
	 * 	> <cfset urlObject = toURL(urlString) />
	 * 
	 * Author:
	 * 	Adam Presley
	 * 
	 * Parameters:
	 * 	source - A string to parse into a URL
	 * 
	 * Returns:
	 * 	A Java URL object
	 */
	def toURL(source) {
		((String) source).toURL()
	}
}
