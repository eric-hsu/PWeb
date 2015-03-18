package com.jinxin.common.utils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;

import org.bouncycastle.util.encoders.Base64;

/**
 * 
 * <p>Title: </p>
 * <p>Description: RSA加密类</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-6-18上午10:13:39
 * Description:RSA 工具类,提供加密,解密,生成密钥对等方法
 * 1加密后返回byte[]的方法,引进Base64
 * 2解密方法,引进Base64
 * 3加密、解密方法的参数和返回类型都改为String
 */
public class RSAUtil {
	
	//加密算法方式
    private static final String ALGORITHM = "RSA";

    //字符串和byte[]互相转换时的编码
    private static final String ENCODING = "utf-8";

    /**
	 * 
	 * @author  
	 * @Title 使用Base64把byte[]转为String
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 使用Base64把byte[]转为String
	 * @return: 字符串
	 * @throws:UnsupportedEncodingException byte[]转为String时异常 
	 * @param encrytpByte 加密后得到的byte[]
	 */
    private static String bytesToString(byte[] encrytpByte) throws UnsupportedEncodingException {
        return new String(Base64.encode(encrytpByte), ENCODING);
    }

    /**
	 * 
	 * @author  
	 * @Title 使用Base64把字符串转为byte[]
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 使用Base64把字符串转为byte[]
	 * @return: 字符串
	 * @throws: UnsupportedEncodingException 字符串转为byte[]时异常
	 * @param encrypted 加密后得到的字符串
	 */
    private static byte[] StringToBytes(String encrypted) throws UnsupportedEncodingException {
        return Base64.decode(encrypted.getBytes(ENCODING));
    }

    /**
	 * 
	 * @author  
	 * @Title 生成密钥对
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 使用Base64把字符串转为byte[]
	 * @return: KeyPair 
	 * @throws: Exception
	 * @param key_size (512 ～ 2048) 这个值关系到块加密的大小,可以更改,但是不要太大,否则效率会低
	 */
    public static KeyPair generateKeyPair(int key_size) throws Exception {
        try {
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(ALGORITHM,
                    new org.bouncycastle.jce.provider.BouncyCastleProvider());
            keyPairGen.initialize(key_size, new SecureRandom());
            KeyPair keyPair = keyPairGen.genKeyPair();
            return keyPair;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
	 * 
	 * @author  
	 * @Title 生成公钥
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 
	 * @return: RSAPublicKey 
	 * @throws: Exception
	 * @param modulus,publicExponent
	 */
    public static RSAPublicKey generateRSAPublicKey(byte[] modulus, byte[] publicExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance(ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(new BigInteger(modulus), new BigInteger(publicExponent));
        try {
            return (RSAPublicKey) keyFac.generatePublic(pubKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
	 * 
	 * @author  
	 * @Title 生成私钥
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 
	 * @return: RSAPrivateKey 
	 * @throws: Exception
	 * @param modulus,privateExponent
	 */
    public static RSAPrivateKey generateRSAPrivateKey(byte[] modulus, byte[] privateExponent) throws Exception {
        KeyFactory keyFac = null;
        try {
            keyFac = KeyFactory.getInstance(ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
        } catch (NoSuchAlgorithmException ex) {
            throw new Exception(ex.getMessage());
        }

        RSAPrivateKeySpec priKeySpec = new RSAPrivateKeySpec(new BigInteger(modulus), new BigInteger(privateExponent));
        try {
            return (RSAPrivateKey) keyFac.generatePrivate(priKeySpec);
        } catch (InvalidKeySpecException ex) {
            throw new Exception(ex.getMessage());
        }
    }

    /**
	 * 
	 * @author  
	 * @Title 加密
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 
	 * @return: 加密后的数据
	 * @throws: Exception 加密时异常
	 * @param key 加密的密钥,src 待加密的明文数据
	 */
    public static String encrypt(Key key, String src) throws Exception {
        try {
            byte[] data = src.getBytes(ENCODING);

            Cipher cipher = Cipher.getInstance(ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            /**
             * 获得加密块大小,
             * 如:加密前数据为128个byte,而key_size=1024 加密块大小为127 byte,
             * 加密后为128个byte;因此共有2个加密块,第一个127 byte第二个为1个byte
             */
            int blockSize = cipher.getBlockSize();
            /**
             * 获得加密块加密后块大小
             */
            int outputSize = cipher.getOutputSize(data.length);
            int leavedSize = data.length % blockSize;
            int blocksSize = leavedSize != 0 ? data.length / blockSize + 1 : data.length / blockSize;
            byte[] raw = new byte[outputSize * blocksSize];
            int i = 0;
            while (data.length - i * blockSize > 0) {
                if (data.length - i * blockSize > blockSize)
                    cipher.doFinal(data, i * blockSize, blockSize, raw, i * outputSize);
                else
                    cipher.doFinal(data, i * blockSize, data.length - i * blockSize, raw, i * outputSize);
              i++;
            }
            return bytesToString(raw);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
	 * 
	 * @author  
	 * @Title 解密
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 
	 * @return: 解密后的明文
	 * @throws: Exception 解密时异常
	 * @param key   解密的密钥,rawStr 已经加密的数据
	 */
    @SuppressWarnings("static-access")
	public static String decrypt(Key key, String rawStr) throws Exception {
        try {
            byte[] raw = StringToBytes(rawStr);

            Cipher cipher = Cipher.getInstance(ALGORITHM, new org.bouncycastle.jce.provider.BouncyCastleProvider());
            cipher.init(cipher.DECRYPT_MODE, key);
            int blockSize = cipher.getBlockSize();
            ByteArrayOutputStream bout = new ByteArrayOutputStream(64);
            int j = 0;

            while (raw.length - j * blockSize > 0) {
                bout.write(cipher.doFinal(raw, j * blockSize, blockSize));
                j++;
            }
            return new String(bout.toByteArray(), ENCODING);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    /**
	 * 
	 * @author  
	 * @Title 生成密匙对到指定目录
	 * @Time: 2011-6-18上午10:13:39
	 * @Description: 
	 * @return: 解密后的明文
	 * @throws: Exception,Exception
	 * @param key   FilePath 文件路径,Ex: c:/key/
	 * @param KeySize  密匙长度
	 */
    public static void generateKeyPairToFile(String FilePath, int KeySize) throws Exception, IOException {
        KeyPair keyPair = generateKeyPair(KeySize);

        RSAPublicKey pubKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey priKey = (RSAPrivateKey) keyPair.getPrivate();

        writeObject(pubKey, FilePath + "RSAPublicKey.dat");
        writeObject(priKey, FilePath + "RSAPrivateKey.dat");
    }

    /**
     * 
     * @author  
     * @Title writeObject
     * @Time: 2011-10-24下午03:01:40
     * @Description: 写Key文件
     * @return: void 
     * @throws: 
     * @param object 
     * @param fileNmae 文件名
     * @throws IOException
     */
    public static void writeObject(Object object, String fileNmae) throws IOException {
        FileOutputStream fos = new FileOutputStream(new File(fileNmae));
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(object);
        oos.flush();
        oos.close();
    }

    /**
     * 
     * @author  
     * @Title readObject
     * @Time: 2011-10-24下午03:02:19
     * @Description: 根据路径读取Key文件
     * @return: Object 
     * @throws: 
     * @param fineName 文件名称（完整路径）
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Object readObject(String fineName) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(new File(fineName));
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object o = ois.readObject();
        ois.close();
        return o;
    }

    public static void main(String[] args) throws Exception {
    	java.net.URL baseURL = RSAUtil.class.getResource("/");

        System.out.println("keyUrl = " + baseURL.getPath());

        String src = "testKang";
        System.out.println("src = " + src);
        System.out.println("src.length() = " + src.length());

        //加密测试－把字符串加密成字符串
        //
        long startTime = System.currentTimeMillis();

        //RSAPublicKey Public = (RSAPublicKey) RSAUtil.readObject(baseURL.getPath() + "RSAPublicKey.dat");
        RSAPublicKey Public = (RSAPublicKey) RSAUtil.readObject("D:/RSAPublicKey.dat");
        String encryptedStr = RSAUtil.encrypt(Public, src);
        long endTime1 = System.currentTimeMillis();

        System.out.println("encryptedStr = " + encryptedStr);
        System.out.println("encryptedStr.length() = " + encryptedStr.length());
        System.out.println("加密耗时:" + (endTime1 - startTime) + "ms");
    }
}

