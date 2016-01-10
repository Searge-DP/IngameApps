package latmod.ingameapps.apps.trello;

import com.google.gson.JsonObject;
import latmod.lib.util.FinalIDObject;

public class TLabel extends FinalIDObject
{
	public final TBoard board;
	public final String name;
	public final TLabelColor color;
	
	public TLabel(TBoard b, JsonObject o)
	{
		super(o.get("id").getAsString());
		board = b;
		color = TLabelColor.get(o.get("color").getAsString());
		name = o.get("name").getAsString();
	}
	
	public String toString()
	{ return name.isEmpty() ? color.name() : name; }
	
	public int compareTo(Object o)
	{ return Integer.compare(color.ordinal(), ((TLabel) o).color.ordinal()); }
}