package latmod.ingameapps.apps.trello;

import java.util.HashMap;

public enum TLabelColor
{
	green,
	yellow,
	orange,
	red,
	purple,
	blue,
	sky,
	lime,
	pink,
	black;
	
	private static final HashMap<String, TLabelColor> map = new HashMap<>();
	
	public static TLabelColor get(String s)
	{
		if(map.isEmpty())
		{ for(TLabelColor c : values()) map.put(c.name(), c); }
		return map.get(s);
	}
}