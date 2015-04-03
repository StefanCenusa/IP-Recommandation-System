

The profile of a person – Short-Term interests and Long-Term interests


	Since the fast update rate of micro-blog and user's interests are changing dynamicaly, we divide users' interests into long-term interests and short-term interests. To improve the precision of recommendation, we consider three factors to build users' interests :

-Tags : People can use tags to represent their interests. We can also find users that has common interests through tags.

-Contents : When users send a status, they may want to transmit information or express their status, but these all contains their interests. We are gonna use the contents of the latest one month to build one user's short-term interests.

-Followees : In social networks, most people follow someone to get useful information from them, we are interested in what followees post, so wheb constructing interests, we consider using their interests in oreder to extend ours. Prior work had proved the effectiveness of combining text content from a group to capture the interest of a single user.
	A person's profile can be build more precise if we divide interests into long-term and short-term interests. 

1. Short-term interests

	Users' short-term interests show change of his recent statuses. In one research, following the approach in Pazzani et al. people manage to build a bag-of-words profile for each user to represent his short-term interests. Unlike in Pazzani et al., where the profile was consisted of words from web pages that the user has rated explicitly, in this particular research the profile is conturated form the users' recent one month micro-blog contents with TD-Inverse document frequency model.

	TD-IDF model consists in measuring how much information the word provides, that is, whether the term is common or rare across all documents. It is the logarithmically scaled fraction of the documents that contain the word, obtained by dividing the total number of documents by the number of documents containing the term, and then taking the logarithm of that quotient.



	However, since each status contains less than 140 words, the constructed bag-of-words had lower weight, so the researchers used TweeTopic technique to enrich the content of each status on Twitter. They fed each micro-blog to Google CSE search engine and extracted the returned documents to get key words to represent this information's vector.

	Short-term interests change usually and can decay over time but they offer detailed information about a person or in special cases, a “deviation” from the usual. For example: a person A who likes drama and thriller films can watch  a comedy one time and post a status about it but that doesn't mean we add comedy movies to his preferences.




2. Long-Term Interests 

	Unlike short-term interests, users’ long-term interests don’t change usually but it can increase. User posts a status, which is valuable within a short time, and when time is passing, the value of this status is decaying, so old statuses only show that users had been interested in them.
	When building long-term interests there are considered three factors: micro-blog’s contents, tags and followees’ interests. For most people read information rather than write, using contents and tags merely to build interests maybe not precise. 
	For constructing short-term interest there are analyzed the recent one month contens. So to build long-term interest, those contents aren’t analyzed any more. The short-term interests collected every month are reused, and a time factor is added having month as a unit.
	The RDF-model weights user interest using the number of the entities, their frequency and potentially some additional factors. These factors might depend on:
2. 1 whether or not the interest was implicitly mined or explicitly expresed by the user;
2. 2 a time-based function which computes the decay of the interests over time;
2  3 the trustworthiness of the social platform.
	Each interest is connected to the agent who expressed the interest, the time when it was initially expressed/created or modified, its dereferencable description, the website or user accout it was extracted from and the social web action which caused the interest to be expressed.
	Interests will vary in importance or relevence for any user over time, and preferences that have been expressed by a user some time in the past will probably be less relevant than interests which have been expressed very recently. In general, the relevance of interests for a user decay over time. 
	An exponential time decay  function can be used to compute the relevance of interests over time. This function can be used to evaluate the relevance of each interest according to its position on the user timeline, giving higher weights for interests that have occurred recently and lower weights for older interests. The recent entities of interest extracted from the Social Web activity of a user reflect his or her current interests more than the older ones.
The exponential decay function is:



where x(t) is the quantity at time t, x0=x(0) is the initial quantity (at time t=0), τ = 1/λ is
a constant called the mean lifetime, and λ is a positive number called the decay constant. When an interest reoccurs multiple times, we use the average of the timestamps of the different reoccuring events as time t.
The exponential decay function is directly applied to the frequency value of the interests, calculated as the ratio between the number of occurences of the interest and the total number of occurences of all interests. As regards the time considered for the decay function (the value of t), it is computed the average time of the timestamos collected for each interest. 
Using this function it is also made the distinction between long-term interest and short-term or occasional interests. Interests reoccuring many times while separeted by long time periods indicate stable/long –term interests, while the opposite would happen for occasional interests.


See more information here:

http://www.ics.uci.edu/~pazzani/Publications/SW-MLJ.pdf) 

http://people.opposingviews.com/difference-between-shortterm-longterm-interest-rates-7496.html

http://www.jstor.org/discover/10.2307/2118356?sid=21105853199011&uid=3738920&uid=4&uid=2


https://books.google.ro/books?id=pO2XBAAAQBAJ&printsec=frontcover&hl=ro#v=onepage&q&f=false

http://books.google.ro/books?hl=ro&id=j-ptBgAAQBAJ&q=interest#v=snippet&q=interest&f=false
