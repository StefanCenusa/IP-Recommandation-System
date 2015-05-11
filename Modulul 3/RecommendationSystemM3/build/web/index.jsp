<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>RecommendationSystem</title>
        <meta http-equiv="Content-Type" content="text/html;  charset=UTF-8" />
        <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css" />
    </head>
    <body style="text-align: center">
        <div id="header">
            <br />
            <br />
            <img src="images/logo.png" />
        </div>
        <div class="navbar-collapse collapse" id="header-nav">
            <form action="NewServlet" class="navbar-form" role="form">
                <br />
                <div class="form-group" style="display:inline;">
                    <div class="input-group">
                        <input style="width: 500px; border-radius: 6px;" type="text" class="form-control" name="search" />
                    </div>
                </div>
                <br />
                <br />
                <br />
                <div>
                    <label class="checkbox-inline"><input type="checkbox" id="twitterCheckbox" />Twitter</label>
                    <label class="checkbox-inline"><input type="checkbox" id="flickrCheckbox" />Flickr</label>
                    <label class="checkbox-inline"><input type="checkbox" id="googleCheckbox" />Google+</label>
                    <label class="checkbox-inline"><input type="checkbox" id="facebookCheckbox" />Facebook</label>
                    <label class="checkbox-inline"><input type="checkbox" id="linkedInCheckbox" />LinkedIn</label>
                </div>
                <br />
                <br />
                <br />
                <br />
                <input type="submit" class="btn btn-primary" value="Search" />
            </form>
        </div>
    </body>
</html>
