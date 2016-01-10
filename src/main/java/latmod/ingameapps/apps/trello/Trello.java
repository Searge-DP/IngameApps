package latmod.ingameapps.apps.trello;

import com.google.gson.*;
import latmod.lib.LMUtils;
import latmod.lib.net.*;

import java.util.ArrayList;

public class Trello
{
	public static final String API_KEY = "ac6a70d3f00b61b8bf2fb57c2c5fb7fb";
	public static final String API_PATH = "https://api.trello.com/1/";
	public static final String AUTH_URL = "https://trello.com/1/authorize?response_type=token&key=" + API_KEY + "&return_url=https%3A%2F%2Ftrello.com&callback_method=postMessage&scope=read%2Cwrite%2Caccount&expiration=never&name=LatMod+Bot";
	
	public static Trello create(String name) throws Exception
	{
		StringBuilder sb = new StringBuilder(API_PATH);
		sb.append("members/");
		sb.append(name);
		sb.append("?fields=username,fullName&key=");
		sb.append(API_KEY);
		
		JsonObject data = new LMURLConnection(RequestMethod.GET, sb.toString()).connect().asJson().getAsJsonObject();
		
		TUser user = new TUser(data.get("id").getAsString());
		user.username = data.get("username").getAsString();
		user.fullName = data.get("fullName").getAsString();
		
		System.out.println("Connected to Trello account: " + name + ": " + data);
		return new Trello(user);
	}
	
	public JsonElement connectTrello(RequestMethod m, LinkBuilder url) throws Exception
	{
		if(m != RequestMethod.SIMPLE_GET)
		{
			url.put("key", API_KEY);
			if(authToken != null) url.put("token", authToken);
		}
		
		return new LMURLConnection(m, url.toString()).connect().asJson();
	}
	
	public final TUser owner;
	public final ArrayList<TBoard> boards;
	public String authToken = null;
	
	private Trello(TUser u)
	{
		owner = u;
		boards = new ArrayList<>();
	}
	
	public void refreshData() throws Exception
	{
		long l = LMUtils.millis();
		System.out.println("Refreshing...");
		
		LinkBuilder url = new LinkBuilder(API_PATH + "members/" + owner.username + "/boards");
		url.put("fields", "shortUrl,name,desc");
		JsonArray a = connectTrello(RequestMethod.GET, url).getAsJsonArray();
		
		System.out.println("Found " + a.size() + " boards");
		
		boards.clear();
		
		for(int i = 0; i < a.size(); i++)
		{
			JsonObject o = a.get(i).getAsJsonObject();
			boards.add(new TBoard(this, o));
		}
		
		System.out.println("Boards refreshed after " + (LMUtils.millis() - l) + "ms");
	}
}