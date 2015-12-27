package latmod.ingameapps.apps.trello;

import latmod.lib.util.FinalIDObject;

public class TUser extends FinalIDObject
{
	public String username;
	public String fullName;
	
	public TUser(String id)
	{
		super(id);
		username = id;
		fullName = id;
	}
}