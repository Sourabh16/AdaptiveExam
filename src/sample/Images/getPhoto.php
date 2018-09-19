<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Search</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
    <div class="container">
        <form action="search.php" method="GET">
            <strong>Select your search type</strong><br><br>
            <div class="row">
                <input type="radio" name="searchtype" value="title"> Title &nbsp;
                <input type="radio" name="searchtype" value="keywords">Keywords &nbsp;
                <input type="radio" name="searchtype" value="date">Date &nbsp;<br><br>
            </div>
            <div class="row">
                <div class="col"><input type="text" name="searchfor"></div> <br><br>
            </div>
            <div class="row"><input type="submit" value="Search" /></div>
        </form>
    </div>
</body>
</html>