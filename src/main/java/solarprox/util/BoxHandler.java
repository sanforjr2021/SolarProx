public class BoxHandler {
    private String boxID;
    
    public BoxHandler(String ID){
        boxID = ID;
    }
    
    public int startBox(){   
        Process p = new ProcessBuilder("./start.sh", boxID).start();
        return 0;
    }
    
    public int stopBox(){
        Process p = new ProcessBuilder("./stop.sh", boxID).start();
        return 0;
    }
    

    public int rollback(String stateName){
        Process p = new ProcessBuilder("./stop.sh", boxID, stateName).start();
        return 0;
    }
    
    public int rollback(){
        Process p = new ProcessBuilder("./stop.sh", boxID).start();
        return 0;
    }