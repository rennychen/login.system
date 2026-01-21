import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("1->註冊帳號");
        System.out.println("2->登入現有帳號");
        System.out.println("輸入數字選擇使用功能:");

        UserRepository repo = new InMemoryUserRepository();
        PasswordEncoder encoder = new SHA256PasswordEncoder();
        PasswordPolicy policy = new DefaultPasswordPolicy();

        AuthService authService = new AuthService(repo,policy,encoder);

        try{
//            authService.login(account,password);
            System.out.println("登入成功!");
        }catch (AccountNotFoundException e){
            System.out.println(e.getMessage());
        }catch (AccountLockedException e){
            System.out.println(e.getMessage());
        }catch (PasswordMismatchException e){
            System.out.println(e.getMessage());
        }

    }
}


