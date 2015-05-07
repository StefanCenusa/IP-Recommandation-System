var TwitterStrategy = require('passport-twitter').Strategy;
var http = require("http");
var https = require("https");
var Twitter = require('twitter');
var fs = require('fs');
var async = require('async');
var config = require('../config');

const dir = "../twitter - userData/";

module.exports = function (passport) {
    passport.use(new TwitterStrategy({
            consumerKey: config.twitterAPI.consumerKey,
            consumerSecret: config.twitterAPI.consumerSecret,
            callbackURL: "http://127.0.0.1:3000/auth/twitter/callback"
        },
        function (token, tokenSecret, profile, done) {
            var userJSON = {};
            userJSON.profile = profile;
            var client = new Twitter({
                consumer_key: config.twitterAPI.consumerKey,
                consumer_secret: config.twitterAPI.consumerSecret,
                access_token_key: token,
                access_token_secret: tokenSecret
            });

            async.parallel([function (callback) {
                client.get('statuses/user_timeline', userJSON.profile.username, function (error, tweets, response) {
                    if (!error) {
                        userJSON.tweets = tweets;
                        callback(null, 'tweets added');
                    }
                });
            }], function (err, res) {
                var fileName = dir + userJSON.profile.username + '.json';
                fs.writeFile(fileName, JSON.stringify(userJSON, null, 2), function (err) {
                    if (err) throw err;
                    console.log('It\'s saved!');
                });
            });
            done(null, profile);
        }
    ));
};