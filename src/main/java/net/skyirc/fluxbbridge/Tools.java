package net.skyirc.fluxbbridge;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Florian Reichmuth
 */
public class Tools {
public static boolean check( String user, String login, String pw , FluxBBridge plugin)
  {
      	JSONParser parser = new JSONParser();
    InputStream is = null;
 //List list = new ArrayList();
    try
    {
      URL url = new URL( plugin.getConfig().getString("url") + "?mcUser=" + user + "&boardUser=" + login + "&password=" + sha1(pw) + "&key=" + plugin.getConfig().getString("key") );
      is = url.openStream();
      
      //System.out.println( new Scanner( is ).useDelimiter( "\\Z" ).next() );
      JSONObject jsonObject = (JSONObject)  parser.parse(new Scanner( is ).useDelimiter( "\\Z" ).next()) ;
        System.out.println(jsonObject.get("status"));
        System.out.println(jsonObject.get("message"));
      return true;
    }
    catch ( Exception e ) {
      e.printStackTrace();
      return false;
    }
    finally {
      if ( is != null ){
        try { is.close(); } catch ( IOException e ) { e.printStackTrace(); }
      }
    }
  }
    static String sha1(String input) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        byte[] result = mDigest.digest(input.getBytes());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
        }
         
        return sb.toString();
    }
}
