<?php
   $con=mysqli_connect("localhost","root","root","scheduler");
   
  
   $name =$_GET['name'];
   $desg=$_GET['desg'];
   $username = $_GET['username'];
   $password = $_GET['password'];
   $sql="INSERT INTO  hr_acc (name, desg, username, password) VALUES ('$name', '$desg','$username','$password')";
   if (mysqli_query($con,$sql)) {
      echo "Values have been inserted successfully";
   }
   mysqli_close($con);
?>