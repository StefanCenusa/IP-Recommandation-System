var FlickrStrategy = require('passport-flickr').Strategy;
var http = require("http");
var path = require('path');
var https = require("https");
var Flickr = require('flickr').Flickr;
var fs = require('fs');
var async = require('async');
var config = require('../config');

const dir = path.join(__dirname,"..","flickr - userData",'/');

module.exports = function (passport) {
    passport.use(new FlickrStrategy({
            consumerKey: config.flickrAPI.consumerKey,
            consumerSecret: config.flickrAPI.consumerSecret,
            callbackURL: "http://46.101.55.154:80/auth/flickr/callback"
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
                    user_id: profile.id
                }

                client.executeAPIRequest("flickr.people.getPhotos", flickr_params, false, function (err, result) {
                    userJSON.photos = result.photos;
                    var iterator = 0;
                    result.photos.photo.forEach(function(item){
                        client.executeAPIRequest("flickr.photos.getInfo", {photo_id: item.id}, false, function (err, result){
                            userJSON.photos.photo[iterator].info = result;
                            iterator ++;
                            if (iterator === userJSON.photos.photo.length)
                                callback(null, userJSON.photos);
                        })
                    });
                    if (userJSON.photos.photo.length === 0)
                        callback(null, userJSON.photos);
                })
            }], function (err, res) { 
                var fileName = dir + userJSON.profile.fullName + '.json';
                fs.writeFile(fileName, JSON.stringify(userJSON, null, 2), function (err) {
                    if (err) throw err;
                    console.log('It\'s saved!');
                });
            });
            done(null, profile);
        }
    ));
};