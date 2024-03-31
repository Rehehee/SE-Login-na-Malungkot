package Models;

public class ModelUser {
    private int ownerID;
    private String ownerEmail;
    private String ownerPass;
    private String verifyCode;

    /**
     * @return the ownerID
     */
    public int getOwnerID() {
        return ownerID;
    }

    /**
     * @param ownerID the ownerID to set
     */
    public void setOwnerID(int ownerID) {
        this.ownerID = ownerID;
    }

    /**
     * @return the ownerEmail
     */
    public String getOwnerEmail() {
        return ownerEmail;
    }

    /**
     * @param ownerEmail the ownerEmail to set
     */
    public void setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
    }

    /**
     * @return the ownerPass
     */
    public String getOwnerPass() {
        return ownerPass;
    }

    /**
     * @param ownerPass the ownerPass to set
     */
    public void setOwnerPass(String ownerPass) {
        this.ownerPass = ownerPass;
    }

    /**
     * @return the verifyCode
     */
    public String getVerifyCode() {
        return verifyCode;
    }

 
    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public ModelUser(int ownerID, String ownerEmail, String ownerPass, String verifyCode) {
        this.ownerID = ownerID;
        this.ownerEmail = ownerEmail;
        this.ownerPass = ownerPass;
        this.verifyCode = verifyCode;
    }

     public ModelUser(int ownerID, String ownerEmail, String ownerPass) {
        this.ownerID = ownerID;
        this.ownerEmail = ownerEmail;
        this.ownerPass = ownerPass;
    }
    
    public ModelUser() {
        
    }
    
    
}
