var twitter = require('./twitter-login');
var flickr = require('./flickr-login');

module.exports = function (passport) {

    // Passport needs to be able to serialize and deserialize users to support persistent login sessions
    passport.serializeUser(function (user, done) {
        done(null, user.id);
    });

    passport.deserializeUser(function (id, done) {
        done(null, id);
    });

    twitter(passport);
    flickr(passport);
};