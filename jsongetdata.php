<?php
require "conn.php";

$poststring = $_POST["getinfo"];
$postid = "2";
$tableid = $_POST["tableid"];
$cafeid = $_POST["cafeid"];
$testi = "testi";
$rowitem = "";
$getlist = False;
$getProducts = False;
$getProductsfood = False;


switch($postid){
	case "2":
		$query = "SELECT * from vacancy;";
		$getlist = True;
		break;

	default:
		$query ="";
		
}


if($getlist == TRUE){
	$res = mysqli_query($conn,$query);	
	$result = array();
		while($row = mysqli_fetch_array($res)){
			array_push($result,array(
			"id"=>$row[0],
			"organisation_id"=>$row[1],
			"title"=>$row[2],
			"summary"=>$row[3],
			"description"=>$row[4],
			"postal_code"=>$row[9],
			"street"=>$row[7],
			"number"=>$row[8],
			"city"=>$row[11],
			"wanted"=>$row[23]
			)
			);
		}

	}
		echo json_encode(array("result"=>$result));
	mysql_close($conn);
?>
	
	