## Overview

This package provides named function to check the length of a value in
the given field. It might be used in function queries, sorting and
grouping results.

## Getting Started

### Installation

Copy Jar file (solr-function-length-1.0.0.jar) to Solr <lib> directory
so it is loaded on startup.

Register parser for length function in SOLR

	   <valueSourceParser name="length" class="com.rzewucki.solr.functions.LengthFunctionParser" />

### Using length function

__1) Function query__

     http://<host>:<port>/solr/<collection>/select?q={!frange l=10 u=60}length(field)&wt=xml&indent=true

__2) Sorting results__

     http://<host>:<port>/solr/<collection>/select?q={!frange l=10 u=60}length(field)&wt=xml&indent=true&sort=length(field) desc

__3) Grouping results__

     http://<host>:<port>/solr/<collection>/select?q={!frange l=10 u=60}length(field)&wt=xml&indent=true&sort=length(field) desc&group=true&group.func=length(field)
