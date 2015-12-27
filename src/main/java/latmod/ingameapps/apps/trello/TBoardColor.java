package latmod.ingameapps.apps.trello;

import latmod.lib.FastMap;

public enum TBoardColor
{
	blue(0xFF0079BF),
	orange(0xFFD29034),
	green(0xFF519839),
	red(0xFFB04632),
	purple(0xFF89609E),
	pink(0xFFCD5A91),
	lime(0xFF4BBF6B),
	sky(0xFF00AECC),
	grey(0xFF838C91);
	
	private static final FastMap<String, TBoardColor> map = new FastMap<String, TBoardColor>();
	public final int color;
	
	TBoardColor(int c)
	{ color = c; }
	
	public static TBoardColor get(String s)
	{
		if(map.isEmpty())
		{ for(TBoardColor c : values()) map.put(c.name(), c); }
		return map.get(s);
	}
}