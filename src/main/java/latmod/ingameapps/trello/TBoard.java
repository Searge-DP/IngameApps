package latmod.ingameapps.trello;

import com.google.gson.*;
import latmod.lib.FastMap;
import latmod.lib.net.*;
import latmod.lib.util.FinalIDObject;

public class TBoard extends FinalIDObject
{
	public final Trello parent;
	public final FastMap<String, TList> lists;
	public final FastMap<String, TLabel> labels;
	
	public final String url;
	public final String name;
	public final String desc;
	public final TBoardColor color;
	
	public TBoard(Trello t, JsonObject o) throws Exception
	{
		super(o.get("id").getAsString());
		parent = t;
		lists = new FastMap<String, TList>();
		labels = new FastMap<String, TLabel>();
		
		url = o.get("shortUrl").getAsString();
		name = o.get("name").getAsString();
		desc = o.get("desc").getAsString();
		
		JsonObject o1 = new LMURLConnection(RequestMethod.GET, url + ".json").connect().asJson().getAsJsonObject();
		
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