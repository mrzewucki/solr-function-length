/*
 * LengthFunctionParser.java
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
import org.apache.solr.search.ValueSourceParser;
import org.apache.solr.search.SyntaxError;
import org.apache.solr.search.FunctionQParser;

/**
 * This class provides parser for length function.
 * @author Marcin Rzewucki
 * @version 1.0.0 2014/01/08
 */
public class LengthFunctionParser extends ValueSourceParser {
    /**
     * Parses input as ValueSource object and passes it to
     * LengthFunction object.
     * @param fqp FunctionQParser object to parse input values
     * @return ValueSource object
     * @exception SyntaxError
     * @see FunctionQParser
     */
    public ValueSource parse(FunctionQParser fqp) throws SyntaxError {
	ValueSource value = fqp.parseValueSource();

	return new LengthFunction(value);
    }
}
