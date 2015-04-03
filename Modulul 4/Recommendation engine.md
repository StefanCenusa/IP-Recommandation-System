Recommendation engine
===================


**Recommendation system** (sometimes replacing "system" with a synonym such as platform or engine) are a subclass of [information filtering system](http://en.wikipedia.org/wiki/Information_filtering_system) that seek to predict the 'rating' or 'preference' that user would give to an item.

----------


Overview
-------------

Recommender systems typically produce a list of recommendations in one of two ways - through collaborative or content-based filtering. 

[Collaborative filtering](http://en.wikipedia.org/wiki/Collaborative_filtering) approaches building a model from a user's past behavior (items previously purchased or selected and/or numerical ratings given to those items) as well as similar decisions made by other users; then use that model to predict items (or ratings for items) that the user may have an interest in. 

Content-based filtering approaches utilize a series of discrete characteristics of an item in order to recommend additional items with similar properties. 

These approaches are often combined (see [Hybrid Recommender Systems](http://en.wikipedia.org/wiki/Recommender_system#Hybrid_Recommender_Systems)).

The differences between collaborative and content-based filtering can be demonstrated by comparing two popular music recommender systems - [Last.fm](http://www.last.fm/) and [Pandora Radio](http://www.pandora.com).

**Last.fm** creates a "station" of recommended songs by observing what bands and individual tracks that the user has listened to on a regular basis and comparing those against the listening behavior of other users. Last.fm will play tracks that do not appear in the user's library, but are often played by other users with similar interests. As this approach leverages the behavior of users, it is an example of a collaborative filtering technique.


**Pandora** uses the properties of a song or artist (a subset of the 400 attributes provided by the Music Genome Project) in order to seed a "station" that plays music with similar properties. User feedback is used to refine the station's results, deemphasizing certain attributes when a user "dislikes" a particular song and emphasizing other attributes when a user "likes" a song. This is an example of a content-based approach.

Each type of system has its own strengths and weaknesses. In the above example, Last.fm requires a large amount of information on a user in order to make accurate recommendations. This is an example of the cold start problem, and is common in collaborative filtering systems. While Pandora needs very little information to get started, it is far more limited in scope (for example, it can only make recommendations that are similar to the original seed).

Recommender systems are a useful alternative to search algorithms since they help users discover items they might not have found by themselves. Interestingly enough, recommender systems are often implemented using search engines indexing non-traditional data.

----------


Approaches
-------------

**Collaborative filtering**

One approach to the design of recommender systems that has seen wide use is collaborative filtering.

Collaborative filtering methods are based on collecting and analyzing a large amount of information on users’ behaviors, activities or preferences and predicting what users will like based on their similarity to other users. A key advantage of the collaborative filtering approach is that it does not rely on machine analyzable content and therefore it is capable of accurately recommending complex items such as movies without requiring an "understanding" of the item itself. Many algorithms have been used in measuring user similarity or item similarity in recommender systems. For example, the k-nearest neighbor (k-NN) approach and the Pearson Correlation as first implemented by Allen R. B. (1990).

Collaborative Filtering is based on the assumption that people who agreed in the past will agree in the future, and that they will like similar kinds of items as they liked in the past.

**Content-based filtering**

Another common approach when designing recommender systems is content-based filtering. Content-based filtering methods are based on a description of the item and a profile of the user’s preference. In a content-based recommender system, keywords are used to describe the items; beside, a user profile is built to indicate the type of item this user likes. In other words, these algorithms try to recommend items that are similar to those that a user liked in the past (or is examining in the present). In particular, various candidate items are compared with items previously rated by the user and the best-matching items are recommended. This approach has its roots in information retrieval and information filtering research.

To abstract the features of the items in the system, an item presentation algorithm is applied. A widely used algorithm is the [tf–idf](http://en.wikipedia.org/wiki/Tf%E2%80%93idf) representation (also called vector space representation).

**Hybrid Recommender Systems**

Recent research has demonstrated that a hybrid approach, combining collaborative filtering and content-based filtering could be more effective in some cases. 

Hybrid approaches can be implemented in several ways: by making content-based and collaborative-based predictions separately and then combining them; by adding content-based capabilities to a collaborative-based approach (and vice versa); or by unifying the approaches into one model.  Several studies empirically compare the performance of the hybrid with the pure collaborative and content-based methods and demonstrate that the hybrid methods can provide more accurate recommendations than pure approaches. 

These methods can also be used to overcome some of the common problems in recommender systems such as [cold start](http://en.wikipedia.org/wiki/Cold_start) and the sparsity problem.