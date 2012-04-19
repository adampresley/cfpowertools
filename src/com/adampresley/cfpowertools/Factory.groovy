package com.adampresley.cfpowertools

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptEngineFactory

/**
 * Class: Factory
 * This factory object is a helper for instantiating the various CF Power Tools
 * components. It is provided only as a component to make life easier, and does
 * not provide any specific functionality.
 * 
 * Package:
 * 	com.adampresley.cfpowertools
 * 	
 * Author:
 * 	Adam Presley
 */
class Factory 
{
	/**
	 * Function: getArrayTools
	 * Instantiates an instance of the ArrayTools component. 
	 * 
	 * Author:
	 * 	Adam Presley
	 * 	
	 * Returns:
	 * 	Returns an instance of the ArrayTools component
	 */
	def static getArrayTools() { new ArrayTools() }
	
	/**
	 * Function: getCollectionTools
	 * Instantiates an instance of the CollectionTools component. 
	 * 
	 * Author:
	 * 	Adam Presley
	 * 	
	 * Returns:
	 * 	Returns an instance of the CollectionTools component
	 */
	def static getCollectionTools() { new CollectionTools() }
	
	/**
	 * Function: getGroovyEngine
	 * Instantiates an instance of the Groovy script engine.
	 * 
	 * Author:
	 * 	Adam Presley
	 * 	
	 * Returns:
	 * 	Returns an instance of the Groovy script engine.
	 */
	def static getGroovyEngine()
	{
		def manager = new ScriptEngineManager()
		manager.getEngineByName("groovy")
	}

	/**
	 * Function: getStringTools
	 * Instantiates an instance of the StructTools component.
	 * 
	 * Author:
	 * 	Adam Presley
	 * 	
	 * Returns:
	 * 	Returns an instance of the StructTools component
	 */
	def static getStringTools() { new StringTools() }

	/**
	 * Function: getStructTools
	 * Instantiates an instance of the StructTools component. 
	 * 
	 * Author:
	 * 	Adam Presley
	 * 	
	 * Returns:
	 * 	Returns an instance of the StructTools component
	 */
	def static getStructTools() { new StructTools() }
}
