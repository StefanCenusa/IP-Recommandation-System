##Flickr API

----------
Overview
-------------

In order to use any API, the first step is to obtain an API key from the organization that is providing the service. This key is used by the system to recognize you as a trusted developer and thus allow your app to comunicate with the service. It is worth mentioning that not all functions require a key, these functions are generaly public services that don't involve any sensitive data.

The Flickr API consists of a set of callable methods, and some API endpoints.

To perform an action using the Flickr API, you need to select a calling convention, send a request to its endpoint specifying a method and some arguments, and will receive a formatted response.

All request formats, listed on the [API index page][1], take a list of named parameters.

The REQUIRED parameter api_key is used to specify your [API Key][2].

The optional parameter format is used to specify a response format.

The arguments, responses and error codes for each method are listed on the method's spec page. Methods are lists on the API index page.

>Limits imposed by the Flickr API:

> - 3600 Queries / hour;
>- Data can be cached for up to one day.

Before using any API it is important to get accustomed to the Community Guidelines and the API Terms of Use, since not respecting them may get your key void, thus removing your access to the API.

[Flickr Community Guidelines](https://www.flickr.com/help/guidelines/)
[Flickr API Terms of Use](https://www.flickr.com/services/api/tos/)
 
> **Note:**

> The Flickr API exposes identifiers for users, photos, photosets and other uniquely identifiable objects. These IDs should always be treated as opaque strings, rather than integers of any specific type. The format of the IDs can change over time, so relying on the current format may cause you problems in the future.

Login Flow
-------------
You may use Flickr to authenticate users in your app based on their Flickr credentials. In order to do so, Flickr proposes the following login flow.

![](https://s.yimg.com/pw/images/en-us/flickr_oauth_flow.jpg)

1. singin request - signature
2. request token with signature - user token
3. redirect to authorization page
4. prompts user to provide authorization
5. return back to app, passing oauth_verifier
6. grants acces

