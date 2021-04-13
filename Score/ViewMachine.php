<?php
    session_id(htmlspecialchars($_COOKIE["PHPSESSID"]));
    session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SolarProx - Box View</title>
    <!-- Fonts -->
    <link rel="preconnect" href="https://fonts.gstatic.com">
    <link href="https://fonts.googleapis.com/css2?family=Baloo+Paaji+2:wght@500&display=swap" rel="stylesheet">
    <link rel="shortcut icon" href="images/favicon.ico" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href="main.css" rel="stylesheet">
    <!--- BootStrap --->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
    <!-- Local files -->



</head>
<body style="background: #2c5684"> <!-- hardcoded to blue to override boostrap --->

        <?php

        if ($_SESSION["priv"] == "Admin"){
                echo '
                <div class="topnav">
                <a href="./Home_Admin.php">Admin Home</a>
                <a href="./Home.php">Student Home</a>
                <a href="./Profile.php">Profile</a>
                <a href="#" class="active" >Box View</a>
                <a style="position: absolute; top: 0px;right: 0px;" href="./Login.php">Logout</a>
                </div>

                ';
            }
        elseif ($_SESSION["priv"] == "User"){
                echo '
                    <div class="topnav">
                    <a href="./Home.php">Home</a>
                    <a href="./Profile.php">Profile</a>
                    <a href="#" class="active" >Box View</a>
                    <a style="position: absolute; top: 0px;right: 0px;" href="./Login.php">Logout</a>
                    </div>

                ';
            }
        else{
                echo '<script> window.location.replace("./Login.php")</script>';
            }


    ?>


<header class="header">
    <h1>SolarProx</h1>
    <h2>Your solution to penetration testing with Proxmox</h2>
</header>
<br>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <!--- Do not paste above here. This is for formatting. --->

            <!--- copy from here to the end of the section div to create
            a new category ---->
            <div class="section">
                <?php
                    $BoxID = array_search("View Machine", $_POST, true);
                    //echo $BoxID;
                    chdir("Scripts");

                    //$getMachine = './getMachineNotes.sh  '.$BoxID;
                    $result = shell_exec($getMachine);
                    //echo $result;

                    $BoxArr = json_decode($result);
                    $data = $BoxArr -> data;
                    $BoxName = $data -> name;
                    $BoxDes = $data -> description;


                echo "<h3>Box Info</h3>";

                echo '<div class="sectionBody">';

                echo "<b>Name: </b>{$BoxName}<br>";
                //echo "<b>Description: </b>{$BoxDes}";
                echo $BoxDes;
                
                    
                
                echo'<form action="SendFlag.php" method="post">
                        <label for="Flag">Flag:</label>
                        <input type="text" id="Flag" name="Flag"><br><br>
                        <input type="hidden" value="'.$BoxID.'" name="BoxID" />
                        <input type="submit" value="Submit">
                    </form>';

                ?>
                </div>
                <br>
            </div>
            <!---end of Category --->

            <!--- DO NOT EDIT BELOW HERE. This is for formatting--->
            </div>
            <br>
        </div>
    </div>
</div>
</body>
</html>
