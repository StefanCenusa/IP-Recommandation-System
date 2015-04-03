Flickr API!
===================

Overview
-------------

The Flickr API consists of a set of callable methods, and some API endpoints.

To perform an action using the Flickr API, you need to select a calling convention, send a request to its endpoint specifying a method and some arguments, and will receive a formatted response.

All request formats, listed on the [API index page][1], take a list of named parameters.

The REQUIRED parameter api_key is used to specify your [API Key][2].

The optional parameter format is used to specify a response format.

The arguments, responses and error codes for each method are listed on the method's spec page. Methods are lists on the API index page.

Note: The Flickr API exposes identifiers for users, photos, photosets and other uniquely identifiable objects. These IDs should always be treated as opaque strings, rather than integers of any specific type. The format of the IDs can change over time, so relying on the current format may cause you problems in the future.

> **Note:**

> The Flickr API exposes identifiers for users, photos, photosets and other uniquely identifiable objects. These IDs should always be treated as opaque strings, rather than integers of any specific type. The format of the IDs can change over time, so relying on the current format may cause you problems in the future.

Login Flow
-------------

![](https://s.yimg.com/pw/images/en-us/flickr_oauth_flow.jpg)

1. singin request - signature
2. request token with signature - user token
3. redirect to authorization page
4. prompts user to provide authorization
5. return back to app, passing oauth_verifier
6. grants acces

[1]:https://www.flickr.com/services/api/
[2]:https://www.flickr.com/services/api/keys/
