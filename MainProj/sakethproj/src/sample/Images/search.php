
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Search results</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="style.css"/>
</head>
<body>
<?php
$servername = "ec2-13-236-67-34.ap-southeast-2.compute.amazonaws.com";
$username = "mounikadatabase";
$password = "mounika123";
$dbname = "mydatabase";

// Create connection
$con = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($con->connect_error) {
    die("Connection failed: " . $con->connect_error);
}
   $type = $_GET['searchtype']; 
    $searchfor= $_GET['searchfor'];
    if ($type=="title")
     $data=$con->query("select * from photoalbum where title='$searchfor'");
     else if($type=="keywords")
     $data =$con->query("select * from photoalbum where keywords='$searchfor'");
    else if($type=="date")
    $data= $con->query("select * from photoalbum where date='$searchfor");

    if (mysqli_affected_rows($con)>0)
    {
        echo "<h3>Following are the search results";
        echo "<table border cellpadding=3><tr><th>Title</th><th>Keywords</th><th>Date</th><th>Image</th></tr>";
        while ($result= mysqli_fetch_array($data)){
            echo "<tr><td>".$result['title']."</td>";
            echo "<td>".$result['description']."</td>";
            echo "<td>".$result['keywords']."</td>";
            echo "<td>".$result['date']."</td>";
            echo "<td><img src=".$result['url']." width='200' height='200'> </td></tr>";
        }
        echo "</table>";
    }
    else
    {
        echo "Your search didn't find any thing.";
    }
?>
</body>
</html>