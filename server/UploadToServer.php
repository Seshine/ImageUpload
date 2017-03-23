<?php
 
    // DISPLAY FILE INFORMATION JUST TO CHECK IF FILE OR IMAGE EXIST
   /* echo '<pre>';
    print_r($_FILES);
    echo '</pre>';
    
    // DISPLAY POST DATA JUST TO CHECK IF THE STRING DATA EXIST
    echo '<pre>';
    print_r($_POST);
    echo '</pre>';*/
$response=Array();
    
    $file_path = "images/";
    $file_path = $file_path . basename( $_FILES['file']['name']);
    
    if(move_uploaded_file($_FILES['file']['tmp_name'], $file_path)) {
       
           $response["status"]="1";
          $response["message"]="success";
          echo json_encode($response);

    
       
    } else{
        
          $response["status"]="0";
          $response["message"]="fail";
          echo json_encode($response);
    }
?>