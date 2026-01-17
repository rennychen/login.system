public class AuthService {

    private final UserRepository userRepository;
    private final PasswordPolicy passwordPolicy;

    public AuthService(UserRepository userRepository,PasswordPolicy passwordPolicy) {
        this.userRepository = userRepository;
        this.passwordPolicy = passwordPolicy;
    }

    public void register(String account, String userName, String password) {
        checkAccountNotExists(account);
        checkPasswordPolicyCorrect(password);


        // 之後會加 password 檢核、hash
        User user = new User(userName, password, account);
        userRepository.save(user);
    }

    private void checkAccountNotExists(String account){
        if(userRepository.existsByAccount(account)){
            throw new RuntimeException("帳號已存在");
        }
    }

    private void checkPasswordPolicyCorrect(String password){
        passwordPolicy.validate(password);
    }
}
