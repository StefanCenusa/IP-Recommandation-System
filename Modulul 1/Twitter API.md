TWTTTER API
============

REST API
------------
The REST APIs provide programmatic access to read and write Twitter data. Author a new Tweet, read author profile and follower data, and more. The REST API identifies Twitter applications and users using OAuth; responses are available in JSON.

![REST API schema](https://g.twimg.com/dev/documentation/image/streaming-intro-1_1.png)

 https://dev.twitter.com/rest/public

>SEARCH API

>The Twitter Search API is part of Twitter’s v1.1 REST API. It allows queries against the indices of recent or popular Tweets and behaves similarily to, but not exactly like the Search feature available in Twitter mobile or web clients, such as Twitter.com search.
>
>The Search API is focused on relevance and not completeness. This means that some Tweets and users may be missing from search results. If you want to match for completeness you should consider using a Streaming API instead.
>https://dev.twitter.com/rest/public/search

STREAMING API
--------------------

The Streaming APIs give developers low latency access to Twitter’s global stream of Tweet data. A proper implementation of a streaming client will be pushed messages indicating Tweets and other events have occurred, without any of the overhead associated with polling a REST endpoint.



Connecting to the streaming API requires keeping a persistent HTTP connection open. In many cases this involves thinking about your application differently than if you were interacting with the REST API.  An app which connects to the Streaming APIs will not be able to establish a connection in response to a user request, as shown in the above example. Instead, the code for maintaining the Streaming connection is typically run in a process separate from the process which handles HTTP requests. 

![enter image description here](https://g.twimg.com/dev/sites/default/files/images_documentation/streaming-intro-2_1.png)

The streaming process gets the input Tweets and performs any parsing, filtering, and/or aggregation needed before storing the result to a data store. The HTTP handling process queries the data store for results in response to user requests. While this model is more complex than the first example, the benefits from having a realtime stream of Tweet data make the integration worthwhile for many types of apps.

https://dev.twitter.com/streaming/overview

