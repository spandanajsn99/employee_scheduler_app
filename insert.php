<?php

     $con=mysqli_connect("localhost","root","root","scheduler");
	
     if(isset($_POST['name']) && isset($_POST['username']) && isset($_POST['password']) && isset($_POST['desg']))
     {
	   	  $username = $_POST['username'];
          $password = $_POST['password'];
		  $name = $_POST['name'];
		  $desg = $_POST['desg'];
		  
		  if($desg=="H")
		  {
		  
			$sql="INSERT INTO  `hr_acc` (`name`, `desg`, `username`, `password`) VALUES ('".$name."', '".$desg."','".$username."','".$password."');";
		  
		  }
		  else
		  {
			$sql="INSERT INTO  `nurse_acc` (`name`, `desg`, `username`, `password`) VALUES ('".$name."', '".$desg."','".$username."','".$password."');";
		  }  
		  
        if (mysqli_query($con,$sql)) {
          echo "Values have been inserted successfully";
		}
		else
		{
			echo "Failed!";
		}
   
		 var_dump(mysqli_error($con));
  	}
	mysqli_close($con);
?>