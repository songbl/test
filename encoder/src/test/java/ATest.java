import com.sbl.EncoderApplication;
import com.sbl.redis.RedisUtils;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Unit test for simple App.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = EncoderApplication.class)
public class ATest
{
    @Autowired
    RedisUtils redisUtils ;

    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        StandardPBEStringEncryptor standardPBEStringEncryptor = new StandardPBEStringEncryptor();
        EnvironmentPBEConfig config = new EnvironmentPBEConfig();
        config.setAlgorithm("PBEWithMD5AndDES");    // 加密的算法，这个算法是默认的
        config.setPassword("mypassword");        // 加密的salt
        standardPBEStringEncryptor.setConfig(config);
        String plainText = "root";      // 需要加密的参数值
        String encryptedText = standardPBEStringEncryptor.encrypt(plainText);
        System.out.println("加密结果： "+encryptedText);  // 输出加密结果
    }
}
