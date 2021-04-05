<?php
    session_id(htmlspecialchars($_COOKIE["SessionID"]));
    session_start();

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SolarProx - Home</title>
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

    <div class="topnav">
  <a class="active" href="./Home.php">Home</a>
  <a style="position: absolute; top: 0px;right: 0px;" href="./Login.php">Logout</a>
    </div>
    
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
                <h3>Avalible Boxes</h3>
                
                <div class="sectionBody">
                    
                <?php
                    echo $_SESSION["priv"];
                    
                    chdir("Scripts");

                    $result = shell_exec('./getAllMachineInfo.sh pve');
                    //echo "<pre>$result</pre>";

                    echo "<table style='width: 100%'><tr><th>Box Name</th><th>Box Status</th></tr>";

                    $BoxList = json_decode($result);
                    $List = $BoxList -> data;
                    foreach($List as $Box){
                        $BoxID = $Box -> vmid;
                        $rollback = 'rollback'.$BoxID;
                        
                        if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST[$rollback]))
                        {
                            rollback($BoxID);
                        }
                        
                        if($Box -> status == "running")
                            {
                                echo "<tr>";

                                echo "<td>";
                                echo $Box -> name;
                                echo "</td>";

                                echo "<td>";
                                echo $Box -> status;
                                echo "</td>";

                                echo "<td>";      

                                $button3 =  "<form action='Home.php' method='post'><input type='submit' name='".$rollback."' value='Rollback' /></form>&emsp;";
                                $button4 =  "<form action='ViewMachine.php' method='post'><input type='submit' name='".$BoxID."' value='View Machine' /></form>";
                                echo $button3;
                                echo $button4;

                                echo "</td>";

                                echo "</tr>";
                            }
                        }
                    echo "</table>";

                    function rollback($id)
                        {
                            $command = './rollback.sh pve '.$id.' base';
                            shell_exec($command);
                            echo "<script>window.location = window.location.href;</script>";
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