public class AuthService {

    private final UserRepository userRepository;
    private final PasswordPolicy passwordPolicy;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository,PasswordPolicy passwordPolicy,PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordPolicy = passwordPolicy;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(String account, String userName, String password) {
        checkAccountNotExists(account); //確認帳號是否存在
        checkPasswordPolicyCorrect(password); //確認密碼設置是否符合規範
        String hashedPassword = passwordEncoder.encode(password); //密碼改成Hash


        User user = new User(userName, hashedPassword, account); //使用者密碼改為hash儲存
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
