package latmod.ingameapps.apps.trello;

import com.google.gson.*;
import latmod.lib.net.*;
import latmod.lib.util.FinalIDObject;

import java.util.HashMap;

public class TBoard extends FinalIDObject
{
	public final Trello parent;
	public final HashMap<String, TList> lists;
	public final HashMap<String, TLabel> labels;
	
	public final String url;
	public final String name;
	public final String desc;
	public final TBoardColor color;
	
	public TBoard(Trello t, JsonObject o) throws Exception
	{
		super(o.get("id").getAsString());
		parent = t;
		lists = new HashMap<>();
		labels = new HashMap<>();
		
		url = o.get("shortUrl").getAsString();
		name = o.get("name").getAsString();
		desc = o.get("desc").getAsString();
		
		JsonObject o1 = parent.connectTrello(RequestMethod.SIMPLE_GET, new LinkBuilder(url + ".json")).getAsJsonObject();

		System.out.println(o1);

		JsonArray a = o1.get("labels").getAsJsonArray();
		
		for(int i = 0; i < a.size(); i++)
		{
			JsonObject o2 = a.get(i).getAsJsonObject();
			TLabel label = new TLabel(this, o2);
			labels.put(label.ID, label);
		}
		
		a = o1.get("lists").getAsJsonArray();
		
		for(int i = 0; i < a.size(); i++)
		{
			TList list = new TList(this, a.get(i).getAsJsonObject());
			lists.put(list.ID, list);
		}
		
		a = o1.get("cards").getAsJsonArray();
		
		for(int i = 0; i < a.size(); i++)
		{
			JsonObject o2 = a.get(i).getAsJsonObject();
			TList l = lists.get(o2.get("idList").getAsString());
			TCard c = new TCard(l, o2);
			l.cards.add(c);
		}
		
		JsonObject prefs = o1.get("prefs").getAsJsonObject();
		color = TBoardColor.valueOf(prefs.get("background").getAsString());
		
		//System.out.println(LMJsonUtils.toJson(LMJsonUtils.getGson(true), o1));
	}
	
	public String toString()
	{ return name; }
	
	public int compareTo(Object o)
	{ return name.compareToIgnoreCase(toString()); }
}