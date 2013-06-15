package de.ohnename.fluxbbridge;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Florian Reichmuth
 */
public class Tools {

	public static boolean check(String user, String login, String pw, FluxBBridge plugin) throws BoardUserNotExistingException, PasswordWrongException, MinecraftAccountTakenException {

		try {

			JSONObject jsonObject = doRequest(plugin.getConfig().getString("url") + "?action=assign&mcUser=" + user + "&boardUser=" + login + "&password=" + pw + "&key=" + plugin.getConfig().getString("key"));

			if(jsonObject.get("status").toString().equalsIgnoreCase("ok")) {
				return true;
			}
			else {

				if(jsonObject.get("message").toString().equalsIgnoreCase("key missing or wrong")) {
					return false;
				}
				else if(jsonObject.get("message").toString().equalsIgnoreCase("boardUser missing")) {
					return false;
				}
				else if(jsonObject.get("message").toString().equalsIgnoreCase("boardUser not existing")) {
					throw new BoardUserNotExistingException();
				}
				else if(jsonObject.get("message").toString().equalsIgnoreCase("password wrong")) {
					throw new PasswordWrongException();
				}
				else if(jsonObject.get("message").toString().equalsIgnoreCase("mcUser missing")) {
					return false;
				}
				else if(jsonObject.get("message").toString().equalsIgnoreCase("mcUser in use")) {
					throw new MinecraftAccountTakenException();
				}

			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			return false;
		}

		return false;

	}

	public static String getKeyLink(String login, FluxBBridge plugin) throws BoardUserNotExistingException {

		try {

			JSONObject jsonObject = doRequest(plugin.getConfig().getString("url") + "?action=keyLink&boardUser=" + login + "&key=" + plugin.getConfig().getString("key"));

			if(jsonObject.get("status").toString().equalsIgnoreCase("ok")) {

				return jsonObject.get("message").toString();

			}
			else if(jsonObject.get("message").toString().equalsIgnoreCase("key missing or wrong")) {
				return null;
			}
			else if(jsonObject.get("message").toString().equalsIgnoreCase("boardUser missing")) {
				return null;
			}
			else if(jsonObject.get("message").toString().equalsIgnoreCase("boardUser not existing")) {
				throw new BoardUserNotExistingException();
			}
			else {
				return null;
			}

		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			return null;
		}

	}

	protected static JSONObject doRequest(String urlString) throws IOException, ParseException {

		JSONParser parser = new JSONParser();
		InputStream is;

		URL url = new URL(urlString);

		is = url.openStream();

		//System.out.println( new Scanner( is ).useDelimiter( "\\Z" ).next() );
		JSONObject jsonObject = (JSONObject) parser.parse(new Scanner(is).useDelimiter("\\Z").next());

		is.close();

		return jsonObject;

	}

	public static class BoardUserMissingException extends Exception {

	}

	public static class BoardUserNotExistingException extends Exception {

	}

	public static class PasswordWrongException extends Exception {

	}

	public static class MinecraftUserMissingException extends Exception {

	}

	public static class MinecraftAccountTakenException extends Exception {

	}

}
