/*
 * LengthFunction.java
 * 
 * 1.0.0
 * 
 * 2014/01/08
 * 
 * Copyright 2014 Marcin Rzewucki
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.rzewucki.solr.functions;

import org.apache.lucene.queries.function.ValueSource;
import org.apache.lucene.queries.function.FunctionValues;
import org.apache.lucene.queries.function.docvalues.IntDocValues;
import org.apache.lucene.index.AtomicReaderContext;

import org.apache.solr.common.SolrException;
import org.apache.solr.common.SolrException.ErrorCode;

import java.util.Map;
import java.io.IOException;

/**
 * This class provides named function to check the length of value in a
 * given field. It might be used in function queries, sorting and grouping
 * results.
 * @author Marcin Rzewucki
 * @version 1.0.0 2014/01/08
 */
public class LengthFunction extends ValueSource {
    protected final ValueSource valueSource;// accept field or function

    public LengthFunction(ValueSource valueSource){
	this.valueSource = valueSource;
    }

    /**
     * Gets the values for this reader and the context. This method
     * returns FunctionValues object containing integer for output.
     * @param context SOLR context object
     * @param readerContext index reader object
     * @return FunctionValues object
     * @exception IOException
     * @see FunctionValues
     */
    @Override
	public FunctionValues getValues(Map context,AtomicReaderContext readerContext) throws IOException {
	final FunctionValues firstValues = (valueSource != null) ? (valueSource.getValues(context, readerContext)) : (null);

	return new IntDocValues(this) {
	    @Override
		public int intVal(int documentId) {//this method is called for each document in results set
		String stringValue;
		if ((firstValues == null) || ((stringValue = firstValues.strVal(documentId)) == null)){
		    return 0;
		} else{
		    return stringValue.length();
		}
	    }
	};
    }

    /**
     * Indicates whether some other object is "equal to" this one.
     */
    @Override
	public boolean equals(Object o) {
	if (this.getClass() != o.getClass()) return false;
	LengthFunction other = (LengthFunction) o;
	
	return this.valueSource.equals(other.valueSource);
    }

    /**
     * Returns a hash code value for the object.
     */
    @Override
	public int hashCode() {
	long combinedHashes;
	combinedHashes = (this.valueSource.hashCode());
	return (int) (combinedHashes ^ (combinedHashes >>> 32));
    }

    /**
     * description of field, used in explain()
     */
    @Override
	public String description() {
	return "Returns the length of value in a given field.";
    }
}
