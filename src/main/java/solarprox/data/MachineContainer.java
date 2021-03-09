package solarprox.data;

public class MachineContainer {
    private String name, address, flag, note;
    private int pointValue;

    public MachineContainer(String name, String address, String flag, String note, int pointValue) {
        this.name = name;
        this.address = address;
        this.flag = flag;
        this.note = note;
        this.pointValue = pointValue;
    }

    public MachineContainer(String name, String address, String note) {
        this.name = name;
        this.address = address;
        this.note = note;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    } //TODO: add check for it being an ip.

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPointValue() {
        return pointValue;
    }

    public void setPointValue(int pointValue) {
        this.pointValue = pointValue;
    }

    /**
     * Compares if a flag value is correct or if it is invalid.
     * @param userInput
     * @return
     */
    public boolean verifyFlag(String userInput){
        return flag.equals(userInput.trim());
    }//TODO: check for cross site scripting is not possible using a regex for <> tags
}
