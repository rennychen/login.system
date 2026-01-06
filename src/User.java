public class User {
    private String userName;  //使用者名稱
    private String passwordHash;
    private String userAccount; //登入帳號
    private int failedLoginAttempts = 0;  //計算登入錯誤次數

    public User(String userName, String passwordHash, String userAccount){
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.userAccount = userAccount;
    }

    public String getUserName(){
        return userName;
    }

    public void setPassword(String passwordHash){
        this.passwordHash = passwordHash;
    }

    public String getUserAccount(){
        return userAccount;
    }

    public void resetFailedLoginAttempts(){
        failedLoginAttempts = 0;
    }

    public void increaseFailedLoginAttempts(){
        failedLoginAttempts++;
    }

    public boolean isLocked(){
        return failedLoginAttempts > 3 ;
    }
}
