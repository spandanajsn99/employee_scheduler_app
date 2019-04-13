<?php

     $con=mysqli_connect("localhost","root","root","scheduler");
	
    
		  $sql="select * from final;";

		  $result = mysqli_query($con, $sql);
          $number_of_rows = mysqli_num_rows($result);
		  $response = array();

		if($number_of_rows > 0) {
        while($row = mysqli_fetch_assoc($result)) {
            $response[] = $row;
        }
    }
	
    header('Content-Type: application/json');
    echo json_encode(array("schedule"=>$response));
    mysqli_close($con);
		  
?>