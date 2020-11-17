package com.koabs.util;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class ASCUtil {
    private static final String DES_ALGORITHM = "DES";
    private static final String KEY = "";

    public ASCUtil() {
    }

    public String encryption(String plainData) throws Exception {
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(1, generateKey());
        } catch (NoSuchAlgorithmException var6) {
            var6.printStackTrace();
        } catch (NoSuchPaddingException var7) {
            var7.printStackTrace();
        } catch (InvalidKeyException var8) {
            ;
        }

        try {
            byte[] e = cipher.doFinal(plainData.getBytes());
            return ASCUtil.Base64Utils.encode(e);
        } catch (IllegalBlockSizeException var4) {
            var4.printStackTrace();
            throw new Exception("IllegalBlockSizeException", var4);
        } catch (BadPaddingException var5) {
            var5.printStackTrace();
            throw new Exception("BadPaddingException", var5);
        }
    }

    public static String decryption(String secretData) throws Exception {
        Cipher cipher = null;

        try {
            cipher = Cipher.getInstance("DES");
            cipher.init(2, generateKey());
        } catch (NoSuchAlgorithmException var5) {
            var5.printStackTrace();
            throw new Exception("NoSuchAlgorithmException", var5);
        } catch (NoSuchPaddingException var6) {
            var6.printStackTrace();
            throw new Exception("NoSuchPaddingException", var6);
        } catch (InvalidKeyException var7) {
            var7.printStackTrace();
            throw new Exception("InvalidKeyException", var7);
        }

        try {
            byte[] e = cipher.doFinal(ASCUtil.Base64Utils.decode(secretData.toCharArray()));
            return new String(e);
        } catch (IllegalBlockSizeException var3) {
            var3.printStackTrace();
            throw new Exception("IllegalBlockSizeException", var3);
        } catch (BadPaddingException var4) {
            var4.printStackTrace();
            throw new Exception("BadPaddingException", var4);
        }
    }

    private static SecretKey generateKey() throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
        DESKeySpec keySpec = new DESKeySpec("uhome-des-key".getBytes());
        keyFactory.generateSecret(keySpec);
        return keyFactory.generateSecret(keySpec);
    }

    public static void main(String[] args) throws Exception {
        ASCUtil des = new ASCUtil();
        String result = des.encryption("UxIEI$2Isu");
        System.out.println("encryp-----serorder>>:" + result);
        System.out.println("decrypt----->>:" + decryption("iWKOI10oF00YvBsivIz/tw=="));
        System.out.println("decrypt----->>:" + decryption("VLPwNHjBXnkVbX82kbt0Bw=="));
    }

    static class Base64Utils {
        private static char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=".toCharArray();
        private static byte[] codes = new byte[256];

        Base64Utils() {
        }

        public static String encode(byte[] data) {
            char[] out = new char[(data.length + 2) / 3 * 4];
            int i = 0;

            for(int index = 0; i < data.length; index += 4) {
                boolean quad = false;
                boolean trip = false;
                int val = 255 & data[i];
                val <<= 8;
                if(i + 1 < data.length) {
                    val |= 255 & data[i + 1];
                    trip = true;
                }

                val <<= 8;
                if(i + 2 < data.length) {
                    val |= 255 & data[i + 2];
                    quad = true;
                }

                out[index + 3] = alphabet[quad?val & 63:64];
                val >>= 6;
                out[index + 2] = alphabet[trip?val & 63:64];
                val >>= 6;
                out[index + 1] = alphabet[val & 63];
                val >>= 6;
                out[index + 0] = alphabet[val & 63];
                i += 3;
            }

            return new String(out);
        }

        public static byte[] decode(char[] data) {
            int len = (data.length + 3) / 4 * 3;
            if(data.length > 0 && data[data.length - 1] == 61) {
                --len;
            }

            if(data.length > 1 && data[data.length - 2] == 61) {
                --len;
            }

            byte[] out = new byte[len];
            int shift = 0;
            int accum = 0;
            int index = 0;

            for(int ix = 0; ix < data.length; ++ix) {
                byte value = codes[data[ix] & 255];
                if(value >= 0) {
                    accum <<= 6;
                    shift += 6;
                    accum |= value;
                    if(shift >= 8) {
                        shift -= 8;
                        out[index++] = (byte)(accum >> shift & 255);
                    }
                }
            }

            if(index != out.length) {
                throw new Error("miscalculated data length!");
            } else {
                return out;
            }
        }

        static {
            int i;
            for(i = 0; i < 256; ++i) {
                codes[i] = -1;
            }

            for(i = 65; i <= 90; ++i) {
                codes[i] = (byte)(i - 65);
            }

            for(i = 97; i <= 122; ++i) {
                codes[i] = (byte)(26 + i - 97);
            }

            for(i = 48; i <= 57; ++i) {
                codes[i] = (byte)(52 + i - 48);
            }

            codes[43] = 62;
            codes[47] = 63;
        }
    }
}

