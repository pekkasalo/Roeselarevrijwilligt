<?php

require "conn.php";
$user_name= $_POST["username"];
$user_pass = $_POST["password"];
$postid = $_POST["postid"];
$moitesti = $_POST["moitesti"];
switch($postid){

case "1":
$mysql_qry = "select * from person where username = '$user_name' and passphrase ='$user_pass';";
$result = mysqli_query($conn, $mysql_qry);

if (mysqli_num_rows($result) > 0 )
{
	echo "Login success";
}
else
{
	echo "Login unsuccessfull";	
}
break;

case "2":
$mysql_qry = "select * from test where moi = '$moitesti';";
$res = mysqli_query($conn, $mysql_qry);
$response = array();

	while($row = mysql_fetch_array($res)){
		array_push($response,array(
		"test"=>$row[0],
		"moi"=>$row[1],
		"hei"=>$row[2]
)
);	
}

echo json_encode(array("result"=>$response));
echo $response;
break;

}
mysqli_close($conn);
?>