import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        System.out.println("1->註冊帳號");
        System.out.println("2->登入現有帳號");
        System.out.println("輸入數字選擇使用功能:");

        //name hash account
        UserRepository repo = new InMemoryUserRepository();
        PasswordEncoder encoder = new SHA256PasswordEncoder();
        PasswordPolicy policy = new DefaultPasswordPolicy();

        AuthService authService = new AuthService(repo,policy,encoder);

    }
}


