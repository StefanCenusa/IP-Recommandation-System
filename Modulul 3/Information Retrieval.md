# Information Retrieval

Information retrieval (IR) is finding material (usually documents) of an unstructured nature (usually text) that satisfies an information need from within large collections (usually stored on computers).
Information retrieval is a problem-oriented discipline, concerned with the problem of the effective and efficient transfer of desired information between human generator and human user In other words:
 * The indexing and retrieval of textual documents. 
 * Concerned firstly with retrieving relevant documents to a query. 
 * Query Matching
 * Ranking & Sorting

References:
  * [Christopher D. Manning, Prabhakar Raghavan and Hinrich Schütze, Introduction to Information Retrieval, Cambridge University Press. 2008](http://nlp.stanford.edu/IR-book/pdf/01bool.pdf)
  * Robert R. Korfhage, Information Storage and Retrieval
  * [Susan Feldman, Natural Language Processing in Information Retrieval](http://www.scism.lsbu.ac.uk/inmandw/ir/jaberwocky.htm)


# Directory API

This API is intended for developers who are writing client applications that manage devices, groups, group aliases, group members, organization units, users, and user aliases.

 * https://developers.google.com/admin-sdk/directory/v1/get-start/getting-started
 * https://developers.google.com/admin-sdk/directory/v1/guides/prerequisites

All requests to the Directory API must be authorized by an authenticated user.

The details of the authorization process, or "flow," for OAuth 2.0 vary somewhat depending on 		what kind of application you're writing. The following general process applies to all application types:

1.  When you create your application, you register it using the Google Developers Console. Google then provides information you'll need later, such as a client ID and a client secret.
2.  When your application needs access to user data, it asks Google for a particular scope of access.
3.  Google displays a consent screen to the user, asking them to authorize your application to request some of their data.
4.  If the user approves, then Google gives your application a short-lived access token.
5.  Your application requests user data, attaching the access token to the request.
6.  If Google determines that your request and the token are valid, it returns the requested data.

The Google APIs client libraries can handle some of the authorization process for you. They are available for a variety of programming languages; check the page with libraries and samples for more details.

 * https://developers.google.com/admin-sdk/directory/v1/libraries


# Lucene & Lucene.NET

[*Lucene*](https://www.google.ro/webhp?sourceid=chrome-instant&ion=1&espv=2&es_th=1&ie=UTF-8#q=lucene&es_th=1) is an open-source project that provides Java-based indexing and search technology. Using its API, it is easy to implement full-text search.

Lucene offers powerful features through a simple API:

*Scalable, High-Performance Indexing*
* small RAM requirements -- only 1MB heap
* incremental indexing as fast as batch indexing
* index size roughly 20-30% the size of text indexed

*Powerful, Accurate and Efficient Search Algorithms*
* ranked searching -- best results returned first
* multiple-index searching with merged results
* allows simultaneous update and searching
* flexible faceting, highlighting, joins and result grouping

There are many versions of Lucene, but our focus is on Lucene (written in *Java*) and Lucene.Net (written in *C#*).

[*Lucene.Net*](https://lucenenet.apache.org/) is a port of the Lucene search engine library, written in C# and targeted at .NET runtime users. The Lucene search library is based on an inverted index. Lucene.Net maintains the existing line-by-line port from Java to C#, fully automating and commoditizing the process such that the project can easily synchronize with the Java Lucene release schedule.

More information here:
* http://www.javacodegeeks.com/2010/05/introduction-to-apache-lucene-for-full.html
* http://www.codeproject.com/Articles/29755/Introducing-Lucene-Net
* https://www.safaribooksonline.com/library/view/windows-developer-power/0596527543/ch04s04.html


# LIRE: Lucene Image Retrieval

LIRE is a Java library that provides a simple way to retrieve images and photos based on their color and texture characteristics. 

LIRE creates a [Lucene]( http://lucene.apache.org/) index of image features for content based image retrieval ([CBIR]( http://en.wikipedia.org/wiki/CBIR)). Several different low level features are available, such as MPEG-7 ScalableColor, ColorLayout, and EdgeHistogram, Auto Color Correlogram, PHOG, CEDD, JCD, FCTH, and many more. 

Furthermore, simple and extended methods for searching the index and result browsing are provided by LIRE. LIRE scales well up to millions of images with hash based approximate indexing. 

The LIRE library and the demo application - as well as all the test and development source - are available under the GNU GPL license.

Documentation and help are available:
* on the [developers wiki]( http://www.semanticmetadata.net/wiki/)
* on the [developers mailing list]( https://groups.google.com/forum/#!forum/lire-dev)
* on the [Google Code page of LIRE]( https://code.google.com/p/lire/)
* in the LIRE book on [Amazon]( http://www.amazon.com/Information-Retrieval-Synthesis-Lectures-Concepts/dp/1608459187/ref=sr_1_1?ie=UTF8&qid=1366355737&sr=8-1&keywords=lux+lire) or [Morgan & Claypool]( http://www.morganclaypool.com/doi/abs/10.2200/S00468ED1V01Y201301ICR025)
* [http://www.semanticmetadata.net/lire/](http://www.semanticmetadata.net/lire/)
* [http://www.lire-project.net/](http://www.lire-project.net/)



