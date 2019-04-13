<?php

     $con=mysqli_connect("localhost","root","root","scheduler");
	
     if(isset($_POST['id']) && isset($_POST['d1']) && isse&& isset($_POST['d2'])&& isset($_POST['d3'])&& isset($_POST['d4'])t($_POST['d5'])&& isset($_POST['d6'])&& isset($_POST['d7']))
     {
	   	  $d1 = $_POST['d1'];
		  $d2 = $_POST['d2'];
		  $d3 = $_POST['d3'];
		  $d4 = $_POST['d4'];
		  $d5 = $_POST['d5'];
		  $d6 = $_POST['d6'];
		  $d7 = $_POST['d7'];
		  $id = $_POST['id'];
        
		  $sql="update `shiftreq` set d1=$d1, d2=$d2, d3=$d3, d4=$d4, d5=$d5, d6=$d6, d7=$d7 where id=$id;";
		  
        if (mysqli_query($con,$sql)) {
          echo "Shift requests have been inserted!";
		}
		else
		{
			echo "Failed!";
		}
   
		 var_dump(mysqli_error($con));
  	}
	mysqli_close($con);
?>