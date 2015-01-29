package com.finalyear.cvss;


import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;








import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.view.Menu;

public class Signin extends Activity {
	Button login;
    EditText inputuname;
    EditText inputpwd;
    String url;
 
    public static Cipher getAESCBCEncryptor(byte[] keyBytes, byte[] IVBytes, String      padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IVBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
        return cipher;
    }

    public static Cipher getAESCBCDecryptor(byte[] keyBytes, byte[] IVBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IVBytes);
        Cipher cipher = Cipher.getInstance("AES/CBC/"+padding);
        cipher.init(Cipher.DECRYPT_MODE, key, ivSpec);
        return cipher; 
    } 

    public static Cipher getAESECBEncryptor(byte[] keyBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/"+padding);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        return cipher;
    }

    public static Cipher getAESECBDecryptor(byte[] keyBytes, String padding) throws Exception{
        SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/"+padding);
        cipher.init(Cipher.DECRYPT_MODE, key);
        return cipher;
    }

    public static byte[] encrypt(Cipher cipher, byte[] dataBytes) throws Exception{
        ByteArrayInputStream bIn = new ByteArrayInputStream(dataBytes);
        CipherInputStream cIn = new CipherInputStream(bIn, cipher);
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        int ch;
        while ((ch = cIn.read()) >= 0) {
          bOut.write(ch);
        }
        return bOut.toByteArray();
    } 

    public static byte[] decrypt(Cipher cipher, byte[] dataBytes) throws Exception{
        ByteArrayOutputStream bOut = new ByteArrayOutputStream();
        CipherOutputStream cOut = new CipherOutputStream(bOut, cipher);
        cOut.write(dataBytes);
        cOut.close();
        return bOut.toByteArray();    
    } 
    /**
     * @param args
     */

    public static byte[] demo1encrypt(byte[] keyBytes, byte[] ivBytes, String sPadding, byte[] messageBytes) throws Exception {
        Cipher cipher = getAESCBCEncryptor(keyBytes, ivBytes, sPadding); 
        return encrypt(cipher, messageBytes);
    }

    public static byte[] demo1decrypt(byte[] keyBytes, byte[] ivBytes, String sPadding, byte[] encryptedMessageBytes) throws Exception {
        Cipher decipher = getAESCBCDecryptor(keyBytes, ivBytes, sPadding);
        return decrypt(decipher, encryptedMessageBytes);
    }

    public static byte[] demo2encrypt(byte[] keyBytes, String sPadding, byte[] messageBytes) throws Exception {
        Cipher cipher = getAESECBEncryptor(keyBytes, sPadding); 
        return encrypt(cipher, messageBytes);
    }

    public static byte[] demo2decrypt(byte[] keyBytes, String sPadding, byte[] encryptedMessageBytes) throws Exception {
        Cipher decipher = getAESECBDecryptor(keyBytes, sPadding);
        return decrypt(decipher, encryptedMessageBytes);
    }
    private static char[]    map1 = new char[64];
    static {
       int i=0;
       for (char c='A'; c<='Z'; c++) map1[i++] = c;
       for (char c='a'; c<='z'; c++) map1[i++] = c;
       for (char c='0'; c<='9'; c++) map1[i++] = c;
       map1[i++] = '+'; map1[i++] = '/'; }

    // Mapping table from Base64 characters to 6-bit nibbles.
    private static byte[]    map2 = new byte[128];
    static {
       for (int i=0; i<map2.length; i++) map2[i] = -1;
       for (int i=0; i<64; i++) map2[map1[i]] = (byte)i; }

    /**
    * Encodes a string into Base64 format.
    * No blanks or line breaks are inserted.
    * @param s  a String to be encoded.
    * @return   A String with the Base64 encoded data.
    */
    public static String encodeString (String s) {
    return new String(encode(s.getBytes())); }

    /**
    * Encodes a byte array into Base64 format.
    * No blanks or line breaks are inserted.
    * @param in  an array containing the data bytes to be encoded.
    * @return    A character array with the Base64 encoded data.
    */
    public static char[] encode (byte[] in) {
    return encode(in,in.length); }

    /**
    * Encodes a byte array into Base64 format.
    * No blanks or line breaks are inserted.
    * @param in   an array containing the data bytes to be encoded.
    * @param iLen number of bytes to process in <code>in</code>.
    * @return     A character array with the Base64 encoded data.
    */
    public static char[] encode (byte[] in, int iLen) {
    int oDataLen = (iLen*4+2)/3;       // output length without padding
    int oLen = ((iLen+2)/3)*4;         // output length including padding
    char[] out = new char[oLen];
    int ip = 0;
    int op = 0;
    while (ip < iLen) {
       int i0 = in[ip++] & 0xff;
       int i1 = ip < iLen ? in[ip++] & 0xff : 0;
       int i2 = ip < iLen ? in[ip++] & 0xff : 0;
       int o0 = i0 >>> 2;
       int o1 = ((i0 &   3) << 4) | (i1 >>> 4);
       int o2 = ((i1 & 0xf) << 2) | (i2 >>> 6);
       int o3 = i2 & 0x3F;
       out[op++] = map1[o0];
       out[op++] = map1[o1];
       out[op] = op < oDataLen ? map1[o2] : '='; op++;
       out[op] = op < oDataLen ? map1[o3] : '='; op++; }
    return out; }

    /**
    * Decodes a string from Base64 format.
    * @param s  a Base64 String to be decoded.
    * @return   A String containing the decoded data.
    * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static String decodeString (String s) {
    return new String(decode(s)); }

    /**
    * Decodes a byte array from Base64 format.
    * @param s  a Base64 String to be decoded.
    * @return   An array containing the decoded data bytes.
    * @throws   IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static byte[] decode (String s) {
    return decode(s.toCharArray()); }

    /**
    * Decodes a byte array from Base64 format.
    * No blanks or line breaks are allowed within the Base64 encoded data.
    * @param in  a character array containing the Base64 encoded data.
    * @return    An array containing the decoded data bytes.
    * @throws    IllegalArgumentException if the input is not valid Base64 encoded data.
    */
    public static byte[] decode (char[] in) {
    int iLen = in.length;
    if (iLen%4 != 0) throw new IllegalArgumentException ("Length of Base64 encoded input string is not a multiple of 4.");
    while (iLen > 0 && in[iLen-1] == '=') iLen--;
    int oLen = (iLen*3) / 4;
    byte[] out = new byte[oLen];
    int ip = 0;
    int op = 0;
    while (ip < iLen) {
       int i0 = in[ip++];
       int i1 = in[ip++];
       int i2 = ip < iLen ? in[ip++] : 'A';
       int i3 = ip < iLen ? in[ip++] : 'A';
       if (i0 > 127 || i1 > 127 || i2 > 127 || i3 > 127)
          throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
       int b0 = map2[i0];
       int b1 = map2[i1];
       int b2 = map2[i2];
       int b3 = map2[i3];
       if (b0 < 0 || b1 < 0 || b2 < 0 || b3 < 0)
          throw new IllegalArgumentException ("Illegal character in Base64 encoded data.");
       int o0 = ( b0       <<2) | (b1>>>4);
       int o1 = ((b1 & 0xf)<<4) | (b2>>>2);
       int o2 = ((b2 &   3)<<6) |  b3;
       out[op++] = (byte)o0;
       if (op<oLen) out[op++] = (byte)o1;
       if (op<oLen) out[op++] = (byte)o2; }
    return out; }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signin);
		  inputuname= (EditText) findViewById(R.id.user);
	        inputpwd = (EditText) findViewById(R.id.password1);
	        login = (Button) findViewById(R.id.login);
	        final String lock = getIntent().getStringExtra("lock");
	        //final String uname = getIntent().getStringExtra("username");
	      //  Toast.makeText(Signin.this,lock, Toast.LENGTH_LONG).show();
		   	
	       // final String uname;
			//uname = getIntent().getStringExtra("username");	
	        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			StrictMode.setThreadPolicy(policy);
	        
	        login.setOnClickListener(new View.OnClickListener() {
	        	 
	            public void onClick(View view) {
	                String sDemoMesage = inputpwd.getText().toString();
	                String user = inputuname.getText().toString();
	                String command;
		   if(lock.length()==2)
		   {
	        	 command="multi";
		   }
		   else
		   {
	                 command="login";
		   }
		   Calendar c = Calendar.getInstance();
	                System.out.println("Current time => "+c.getTime());

	                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	                String formattedDate = df.format(c.getTime());
	                // formattedDate have current date/time
	                Toast.makeText(Signin.this, formattedDate, Toast.LENGTH_SHORT); 
	                SimpleDateFormat df1 = new SimpleDateFormat("HH:mm:ss");
	                String formattedDate1 = df1.format(c.getTime());
	                Toast.makeText(Signin.this, formattedDate1, Toast.LENGTH_SHORT);
	               
	                String password;
	                byte[] demoMesageBytes = sDemoMesage.getBytes();
	                //shared secret
	                byte[] demoKeyBytes = new byte[] {  0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
	                        0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
	                // Initialization Vector - usually a random data, stored along with the shared secret,
	                // or transmitted along with a message.
	                // Not all the ciphers require IV - we use IV in this particular sample
	                byte[] demoIVBytes = new byte[] { 0x00, 0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07,
	                                                0x08, 0x09, 0x0a, 0x0b, 0x0c, 0x0d, 0x0e, 0x0f};
	                String sPadding = "ZeroBytePadding"; //"ISO10126Padding", "PKCS5Padding"

	           
	                byte[] demo1EncryptedBytes=null;
	                try {
	                demo1EncryptedBytes = demo1encrypt(demoKeyBytes, demoIVBytes, sPadding, demoMesageBytes);
	                } catch (Exception e1) {
	                // TODO Auto-generated catch block
	                e1.printStackTrace();
	                }
	           //     System.out.println("Demo1 encrypted (base64): "+ new String(encode(demo1EncryptedBytes)));
	                //byte[] demo1DecryptedBytes = demo1decrypt(demoKeyBytes, demoIVBytes, sPadding, demo1EncryptedBytes);
	                //System.out.println("Demo1 decrypted message : "+new String(demo1DecryptedBytes));
	                
	                password=new String(encode(demo1EncryptedBytes));
	                TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
	                String imei=telephonyManager.getDeviceId();
	        		
	                url="http://mobilelock.freeiz.com/test/loginuser.php?uname="+user+"&lock="+lock+"&pwd="+password+"&imei="+imei+"&date="+formattedDate+"&time="+formattedDate1+"&command="+command;
	                HttpClient httpclient = new DefaultHttpClient();
	        		HttpGet httpget = new HttpGet(url);
	        		try {
	        		    HttpResponse response = httpclient.execute(httpget);
	        		    
	        		    if(response != null) {
	        		        String line = "";
	        		        InputStream inputstream = response.getEntity().getContent();
	        		        line = convertStreamToString(inputstream, lock, user);
	        		        
	        		        //if(line.equals)
	        		        Toast.makeText(Signin.this, line, Toast.LENGTH_SHORT).show();
	        		    } else {
	        		        Toast.makeText(Signin.this, "Unable to complete your request", Toast.LENGTH_LONG).show();
	        		    }
	        		} catch (ClientProtocolException e) {
	        		    Toast.makeText(Signin.this, "Caught ClientProtocolException", Toast.LENGTH_SHORT).show();
	        		} catch (IOException e) {
	        		    Toast.makeText(Signin.this, "Caught IOException", Toast.LENGTH_SHORT).show();
	        		} catch (Exception e) {
	        		    Toast.makeText(Signin.this, "Caught Exception", Toast.LENGTH_SHORT).show();
	        		}
	                    }   });
	       
	}
	private String convertStreamToString(InputStream is, String u, String l) {
	    String line = "";
	    StringBuilder total = new StringBuilder();
	    BufferedReader rd = new BufferedReader(new InputStreamReader(is));
	    try {
	        line=rd.readLine();
	        boolean  b = line.startsWith("correct");
	        boolean a = line.startsWith("username");
	        boolean c= line.startsWith("You");
	        boolean d= line.startsWith("Not");
	      
	    	
	        if(b==true) {
	            total.append(line);
	            Intent goToNextActivity = new Intent(getApplicationContext(), Door.class);
	           goToNextActivity.putExtra("lock", u);
	           goToNextActivity.putExtra("username", l);
		           
	             
	           startActivity(goToNextActivity);
	            
	    	}
	    	if(d==true) {
	            total.append(line);
	            
	            
	    	}
	    	
	    	else if(a==true)
	    	{
	    		total.append(line);
	    		}
	    	
	    	else if(c==true)
	    	{
	    		total.append(line);
	    		}
	    
	    
	    }    
	     catch (Exception e) {
	        Toast.makeText(Signin.this, "Stream Exception", Toast.LENGTH_SHORT).show();
	    }
	    return total.toString();
	}    
	     
	    

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

}
