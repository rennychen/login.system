import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;

public class SHA256PasswordEncoder implements PasswordEncoder{
    @Override
    public String encode(String rawPassword){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(rawPassword.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hashBytes);
        }catch(NoSuchAlgorithmException e){
            throw new RuntimeException("Hash失敗",e);
        }
    }

    @Override
    public boolean matches(String rawPassword,String encodedPassword){
        String rawHash = encode(rawPassword);
        return rawHash.equals(encodedPassword);
    }

    public String bytesToHex(byte[] bytes){
        StringBuilder hexString = new StringBuilder();
        for(byte b:bytes){
            hexString.append(String.format("%02x",b));
        }
        return hexString.toString();
    }
}
