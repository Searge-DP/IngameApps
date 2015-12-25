package latmod.ingameapps.trello;

import com.google.gson.JsonObject;
import latmod.lib.FastList;
import latmod.lib.util.FinalIDObject;

public class TList extends FinalIDObject
{
	public TBoard board;
	public final FastList<TCard> cards;
	
	public final String name;
	public final int pos;
	
	public TList(TBoard b, JsonObject o) throws Exception
	{
		super(o.get("id").getAsString());
		board = b;
		cards = new FastList<TCard>();
		
		name = o.get("name").getAsString();
		pos = o.get("pos").getAsInt();
	}
	
	public String toString()
	{ return name; }
	
	public int compareTo(Object o)
	{ return Integer.compare(pos, ((TList)o).pos); }
}