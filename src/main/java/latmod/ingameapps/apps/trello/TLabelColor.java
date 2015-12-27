package latmod.ingameapps.apps.trello;

import latmod.lib.FastMap;

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
	
	private static final FastMap<String, TLabelColor> map = new FastMap<String, TLabelColor>();
	
	public static TLabelColor get(String s)
	{
		if(map.isEmpty())
		{ for(TLabelColor c : values()) map.put(c.name(), c); }
		return map.get(s);
	}
}