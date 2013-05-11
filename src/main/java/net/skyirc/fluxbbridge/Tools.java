package net.skyirc.fluxbbridge;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Florian Reichmuth
 */
public class Tools {
    public static void main(String[] args){
        check(null, null, null, null);
    }
    
public static boolean check( String login, String user, String pw , FluxBBridge plugin)
  {
      	JSONParser parser = new JSONParser();
    InputStream is = null;
 //List list = new ArrayList();
    try
    {
      URL url = new URL( "http://ufen.skyirc.net/forum/fluxbbridge.php?mcUser=" + user + "&boardUser=" + login + "&password=" + pw + "&key=" + plugin.getConfig().getString("key") );
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
}
