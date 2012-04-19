package com.adampresley.cfpowertools

import javax.script.ScriptContext
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptEngineFactory

/**
 * Class: Base
 * Base class for all CF PowerTools classes. Provides the Groovy script
 * engine for them to use, as well as an extension for the String
 * class that will run code using the Groovy engine.
 * 
 * Author:
 * 	Adam Presley
 */
class Base 
{
	ScriptEngineManager manager = null
	ScriptEngine engine = null

	/**
	 * Constructor: Base
	 * Initializes the object by creating an instance of the Groovy script engine
	 * and modifying the String meta-class.
	 * 
	 * Author:
	 * 	Adam Presley
	 */
	public Base() {
		manager = new ScriptEngineManager()
		engine = manager.getEngineByName("groovy")

		String.metaClass.run = { Map bindings ->
			def b = engine.createBindings()
			b.putAll(bindings)
	
			engine.setBindings(b, ScriptContext.ENGINE_SCOPE)
			engine.eval(delegate)
		}
	}
}
