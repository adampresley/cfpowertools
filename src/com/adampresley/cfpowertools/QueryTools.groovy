package com.adampresley.cfpowertools

import net.sf.json.*

class QueryTools extends Base
{
	public QueryTools() { super() }
	
	def toArrayOfStructs(source) {
		def result = []
		def row = [:]
		def metadata = source.getMetaData()
		def numCols = metadata.getColumnCount()
		
		def colNames = []
		def colNamesRetrieved = false
		
		while (source.next()) {
			row = [:]
			
			(1..numCols).each { colIndex ->
				def colName = ""
				
				if (!colNamesRetrieved) {
					colName = metadata.getColumnName(colIndex)
					colNames << colName
				}
				else {
					colName = colNames[colIndex]
				}
				
				def value = source.getString(colIndex).toString()
				row[colName] = (source.wasNull()) ? "" : value
			}
			
			result << row
		}
		
		result
	}
	
	def toJson(source)
	{
		def result = new java.lang.StringBuffer(1024)
		def metadata = source.getMetaData()
		def numCols = metadata.getColumnCount()
		
		def record = [:]
		def recordSet = []
		def jsonObject = null
		
		result.append("[")
		
		try
		{
			while (source.next())
			{
				record = [:]
				(1..numCols).each { index ->
					def value = source.getString(index)
					record["${metadata.getColumnName(index)}"] = ((source.wasNull()) ? "" : value)
				}
				
				result.append((JSONObject.fromObject(record)).toString())
				//recordSet << record
			}
		}
		catch (Exception ex)
		{
			ex.printStackTrace()
			return ex.getMessage()
		}

		//jsonObject = (JSONObject) JSONSerializer.toJSON(recordSet)
		//result.append(jsonObject.toString())

		//jsonObject.toString()
		result.append("]")
		result.toString()
	}
	
	def rowToStruct(query, rowIndex, caseSensitive)
	{
		def metadata = query.getMetaData()
		
	}
}
