<?php
    session_id(htmlspecialchars($_COOKIE["PHPSESSID"]));
    session_start();
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SolarProx - Profile</title>
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
                <a class="active" href="./Profile.php">Profile</a>
                <a style="position: absolute; top: 0px;right: 0px;" href="./Login.php">Logout</a>
                </div>

                ';
            }
        elseif ($_SESSION["priv"] == "User"){
                echo '
                    <div class="topnav">
                    <a href="./Home.php">Home</a>
                    <a class="active" href="./Profile.php">Profile</a>
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
<!--- This is the main content --->
<div class="container-fluid">
    <div class="row">
        <div class="col-md-2"></div>
        <div class="col-md-8">
            <!--- Do not paste above here. This is for formatting. --->

            <!--- copy from here to the end of the section div to create
            a new category ---->
            <div class="section">


                <?php
                     echo "<h3>Profile - {$_SESSION["name"]}</h3>";

                     echo '<div class="sectionBody">';
                    
                    $UserList = explode("\n", file_get_contents("./Scripts/UserScores.txt"));
                    $searchword = $_SESSION["name"];
                    //echo "<br>{$searchword}<br>";
                    //echo var_dump($UserList);
                    $User = array_filter($UserList, function($var) use ($searchword) { return preg_match("/$searchword/", $var); });
                    
                    $User = array_filter($User);
                    
                    //echo "<br>";
                    //echo var_dump($User);
                
                    $UserArr = explode(',',end($User));

                    
                    echo "<b>Total Points: </b>{$UserArr[1]}";
                    echo '<br><b>Completed Boxes</b>';
                    for ($i = 2; $i < sizeof($UserArr); $i++ ){
                        echo "<br>&emsp;{$UserArr[$i]}";
                    }

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
