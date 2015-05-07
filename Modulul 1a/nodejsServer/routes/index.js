var express = require('express');
var router = express.Router();

var isAuthenticated = function (req, res, next) {
    // if user is authenticated in the session, call the next() to call the next request handler
    // Passport adds this method to request object. A middleware is allowed to add properties to
    // request and response objects
    if (req.isAuthenticated())
        return next();
    // if the user is not authenticated then redirect him to the login page
    res.redirect('/');
}

module.exports = function (passport) {

    /* GET login page. */
    router.get('/', function (req, res) {
        // Display the Login page with any flash message, if any
        res.render('index', {message: req.flash('message')});
    });

    /* GET Home Page */
    router.get('/home', isAuthenticated, function (req, res) {
        res.render('home', {user: req.user});
    });

    router.get('/auth/twitter', passport.authenticate('twitter'));

    router.get('/auth/twitter/callback', passport.authenticate('twitter', {
        successRedirect: '/home',
        failureRedirect: '/'
    }));

    router.get('/auth/flickr', passport.authenticate('flickr'));

    router.get('/auth/flickr/callback', passport.authenticate('flickr', {
        successRedirect: '/home',
        failureRedirect: '/'
    }));

    return router;
};





