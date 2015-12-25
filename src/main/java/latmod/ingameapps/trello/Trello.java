package latmod.ingameapps.trello;

import com.google.gson.*;
import latmod.lib.*;
import latmod.lib.net.*;

public class Trello
{
	public static final String API_KEY = "ac6a70d3f00b61b8bf2fb57c2c5fb7fb";
	public static final String API_PATH = "https://api.trello.com/1/";
	public static final String AUTH_URL = "https://trello.com/1/authorize?response_type=token&key=" + API_KEY + "&return_url=https%3A%2F%2Ftrello.com&callback_method=postMessage&scope=read%2Cwrite%2Caccount&expiration=never&name=LatMod+Bot";
	
	public static Trello create(String name) throws Exception
	{
		JsonObject data = connectTrello("members/" + name + "?fields=username,fullName", null).getAsJsonObject();
		TUser user = new TUser(data.get("id").getAsString());
		user.username = data.get("username").getAsString();
		user.fullName = data.get("fullName").getAsString();
		
		System.out.println("Connected to Trello account: " + name + ": " + data);
		return new Trello(user);
	}
	
	public static JsonElement connectTrello(String urlS, String authToken) throws Exception
	{
		StringBuilder sb = new StringBuilder();
		if(!urlS.startsWith("http")) sb.append(API_PATH);
		sb.append(urlS);
		if(urlS.indexOf('?') > 0) sb.append('&'); else sb.append('?');
		sb.append("key=");
		sb.append(API_KEY);
		if(authToken != null)
		{
			sb.append("&token=");
			sb.append(authToken);
		}
		return new LMURLConnection(RequestMethod.GET, sb.toString()).connect().asJson();
	}
	
	public final TUser owner;
	public final FastList<TBoard> boards;
	public String authToken = null;
	
	private Trello(TUser u)
	{
		owner = u;
		boards = new FastList<TBoard>();
	}
	
	public void refreshData() throws Exception
	{
		long l = LMUtils.millis();
		System.out.println("Refreshing...");
		
		JsonArray a = connectTrello("members/" + owner.username + "/boards?fields=shortUrl,name,desc", authToken).getAsJsonArray();
		System.out.println("Found " + a.size() + " boards");
		
		boards.clear();
		
		for(int i = 0; i < a.size(); i++)
		{
			JsonObject o = a.get(i).getAsJsonObject();
			boards.add(new TBoard(this, o));
		}
		
		sort();
		System.out.println("Boards refreshed after " + (LMUtils.millis() - l) + "ms");
	}
	
	public void sort()
	{
		boards.sort(null);
		
		for(TBoard b : boards)
		{
			b.lists.sort();
			b.labels.sort();
			for(TList l : b.lists)
				l.cards.sort(null);
		}
	}
}