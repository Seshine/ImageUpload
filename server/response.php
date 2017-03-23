<?php

$response=Array();

if(isset($_POST["NM"])){

$response["status"]="1";
$response["message"]="success";
echo json_encode($response);
}
else
{

	$response["status"]="0";
$response["message"]="fail";
echo json_encode($response);
}
?>
