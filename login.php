<?php
if($_SERVER['REQUEST_METHOD']=='POST'){


 $con = mysqli_connect("localhost","root","root","sample");

 $Email = $_POST['username'];
 
 $Password = $_POST['password'];
 
 $Full_Name = $_POST['name'];

 $CheckSQL = "SELECT * FROM users WHERE email='$Email'";
 
$Sql_Query = "INSERT INTO users (name,username,password) values ('$Full_Name','$Email','$Password')";

 if(mysqli_query($con,$Sql_Query))
{
 echo 'User Registration Successfully';
}
else
{
 echo 'Something went wrong';
 }
}
 mysqli_close($con);
?>