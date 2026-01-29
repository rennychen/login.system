import java.util.Scanner;

public class Main{
    public static void main(String[] args){

        UserRepository repo = new InMemoryUserRepository();
        PasswordEncoder encoder = new SHA256PasswordEncoder();
        PasswordPolicy policy = new DefaultPasswordPolicy();

        AuthService authService = new AuthService(repo,policy,encoder);
        LoginSession session = new LoginSession();
        Scanner scan = new Scanner(System.in);

        while (true){
            System.out.println("====MENU====");
            System.out.println("1->註冊帳號");
            System.out.println("2->登入帳號");
            System.out.println("3->顯示目前登入身分");
            System.out.println("4->登出");
            System.out.println("0->結束使用");
            System.out.print("選擇使用功能:");

            String num = scan.nextLine();
            switch (num){
                case "1":
                    register(scan,authService);
                    break;
                case "2":
                    login(scan,session,authService);
                    break;
                case "3":
                    showCurrentUser(session);
                    break;
                case "4":
                    logout(session);
                    break;
                case "0":
                    System.out.println("謝謝使用本系統。");
                    return;
                default:
                    System.out.println("請輸入正確的數字!");
                    break;
            }
//            if(num.equals("0")){
//                break;
//            }
        }
    }

    //註冊
    private static void register(Scanner scan,AuthService authService){
        try{
            System.out.print("請輸入帳號:");
            String account = scan.nextLine();

            System.out.print("請輸入密碼:");
            String password = scan.nextLine();

            System.out.print("請輸入使用者名稱:");
            String userName = scan.nextLine();

            authService.register(account,userName,password);
            System.out.println("註冊成功。");
        } catch (RuntimeException e) {
            System.out.println("註冊失敗," + e.getMessage());
        }

    }

    //登入
    private static void login(Scanner scan,LoginSession session,AuthService authService){
        try{
            System.out.print("請輸入帳號:");
            String account = scan.nextLine();

            System.out.print("請輸入密碼:");
            String password = scan.nextLine();

            User user = authService.login(account,password);
            session.login(user);
            System.out.println("登入成功!歡迎回來," + user.getUserName());
        }catch (AccountNotFoundException e){
            System.out.println(e.getMessage());
        }catch (AccountLockedException e){
            System.out.println(e.getMessage());
        }catch (PasswordMismatchException e){
            System.out.println(e.getMessage());
        }

    }

    //顯示目前登入身分
    private static void showCurrentUser(LoginSession session){
        try{
            User user = session.getCurrentUser();
            System.out.println("目前使用者為:" + user.getUserName());
        }catch (IllegalStateException e){
            System.out.println("錯誤," + e.getMessage());
        }
    }

    //登出
    private static void logout(LoginSession session){
        if(session.isLoggedIn()){
            session.logout();
            System.out.println("登出成功!");
        }else{
            System.out.println("尚未登入。");
        }

    }

}


