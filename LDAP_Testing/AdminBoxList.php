<!DOCTYPE html>
<html>
      
<head>
    <title>
        Box List
    </title>
</head>
  
<body style="text-align:center; padding: 10px;">
      
    <h1>
        Admin Box List
    </h1>
  
    <?php
        chdir("Scripts");
    
        $result = shell_exec('./getAllMachineInfo.sh pve');
        //echo "<pre>$result</pre>";
        
        echo "<table style='width: 100%'><tr><th>Box Name</th><th>Box Status</th></tr>";
    
        $BoxList = json_decode($result);
        $List = $BoxList -> data;
        foreach($List as $Box){
            $BoxID = $Box -> vmid;
            $start = 'start'.$BoxID;
            $stop = 'stop'.$BoxID;
            $rollback = 'rollback'.$BoxID;
            if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST[$start]))
                    {
                start($BoxID);
                }
            if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST[$stop]))
                    {
                stop($BoxID);
                } 
            if($_SERVER['REQUEST_METHOD'] == "POST" and isset($_POST[$rollback]))
                    {
                rollback($BoxID);
                } 
            echo "<tr>";
                
            echo "<td>";
            echo $Box -> name;
            echo "</td>";
                
            echo "<td>";
            echo $Box -> status;
            echo "</td>";
                
            echo "<td>";      
            
            $button1 =  "<form action='AdminBoxList.php' method='post'><input type='submit' name='".$start."' value='Start' /></form>";
            $button2 =  "<form action='AdminBoxList.php' method='post'><input type='submit' name='".$stop."' value='Stop' /></form>";
            $button3 =  "<form action='AdminBoxList.php' method='post'><input type='submit' name='".$rollback."' value='Rollback' /></form>";
            echo $button1;
            echo $button2;
            echo $button3;
            
            echo "</td>";
            
            echo "</tr>";
        }
        echo "</table>";
    
        function start($id)
            {
                $command = './start.sh pve '.$id;
                shell_exec($command);  
            }
        function stop($id)
            {
                $command = './stop.sh pve '.$id;
                shell_exec($command);  
            }
        function rollback($id)
            {
                $command = './rollback.sh pve '.$id.' base';
                shell_exec($command);  
            }
      
    ?>
    

</body>
  
</html>
