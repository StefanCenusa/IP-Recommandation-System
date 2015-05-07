var FlickrStrategy = require('passport-flickr').Strategy;
var http = require("http");
var https = require("https");
var Flickr = require('flickr').Flickr;
var fs = require('fs');
var async = require('async');
var config = require('../config');

const dir = "../flickr - userData/";

module.exports = function (passport) {
    passport.use(new FlickrStrategy({
            consumerKey: config.flickrAPI.consumerKey,
            consumerSecret: config.flickrAPI.consumerSecret,
            callbackURL: "http://127.0.0.1:3000/auth/flickr/callback"
        },
        function (token, tokenSecret, profile, done) {
            var userJSON = {};
            userJSON.profile = profile;
            var client = new Flickr(
                config.flickrAPI.consumerKey,
                config.flickrAPI.consumerSecret,
                {
                    oauth_token: token,
                    oauth_token_secret: tokenSecret
                }
            );

            async.parallel([function (callback) {
                var flickr_params = {
                    text: "soccer",
                    media: "photos",
                    per_page: 25,
                    page: 1,
                    extras: "url_q, url_z, url_b, owner_name"
                };

                client.executeAPIRequest("flickr.photos.search", flickr_params, false, function (err, result) {
                    userJSON.photos = result.photos;
                    callback(null, result.photos);
                })
            }], function (err, res) {
                var fileName = dir + userJSON.profile.displayName + '.json';
                fs.writeFile(fileName, JSON.stringify(userJSON, null, 2), function (err) {
                    if (err) throw err;
                    console.log('It\'s saved!');
                });
            });
            done(null, profile);
        }
    ));
};