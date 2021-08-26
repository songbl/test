#### 启动命令
java -jar -Djasypt.encryptor.password=mypassword XXX-jasypt.jar
#### 生成待加密的文本内容
在jasypt-1.9.3.jar 下：
java -cp  D:\maven_lib\org\jasypt\jasypt\1.9.3\jasypt-1.9.3.jar org.jasypt.intf.cli.JasyptPBEStringEncryptionCLI input="mypassword" password=35579B7F9C8CB15E
* input： 需要加密的数据
* password： 加密时候使用的salt值（需要和项目启动加入的password是一致的）
*algorithm： 加密算法，默认的即可