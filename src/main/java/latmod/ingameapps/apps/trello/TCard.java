package latmod.ingameapps.apps.trello;

import com.google.gson.*;
import latmod.lib.FastList;
import latmod.lib.util.FinalIDObject;

public class TCard extends FinalIDObject
{
	public final TList list;
	public final String name;
	public final String desc;
	public final int pos;
	public final String url;
	public final FastList<TLabel> labels;
	
	public TCard(TList l, JsonObject o) throws Exception
	{
		super(o.get("id").getAsString());
		list = l;
		
		name = o.get("name").getAsString();
		desc = o.get("desc").getAsString();
		pos = o.get("pos").getAsInt();
		url = o.get("shortUrl").getAsString();
		labels = new FastList<TLabel>();
		
		JsonArray a = o.get("idLabels").getAsJsonArray();
		
		for(int i = 0; i < a.size(); i++)
		{
			TLabel label = list.board.labels.get(a.get(i).getAsString());
			if(label != null) labels.add(label);
		}
	}
	
	public String toString()
	{ return name; }
	
	public int compareTo(Object o)
	{ return Integer.compare(pos, ((TCard)o).pos); }
	
	/*
	 * "id": "5679d72bea3bcd1c7f52689b",
      "checkItemStates": [],
      "closed": false,
      "dateLastActivity": "2015-12-24T00:04:43.910Z",
      "desc": "",
      "idBoard": "5679d53ee4466e2b6f9bba8c",
      "idList": "5679d54f3216cde3272e1f93",
      "idMembersVoted": [],
      "idShort": 4,
      "manualCoverAttachment": false,
      "idLabels": [
        "5679d66ffb396fe706fa02ac",
        "5679d60efb396fe706fa01df"
      ],
      "name": "Chunkloading",
      "pos": 192,
      "shortLink": "nPNsC9o2",
      "badges": {
        "votes": 0,
        "viewingMemberVoted": false,
        "subscribed": false,
        "fogbugz": "",
        "checkItems": 0,
        "checkItemsChecked": 0,
        "comments": 0,
        "attachments": 0,
        "description": false
      },
      "idChecklists": [],
      "idMembers": [],
      "labels": [
        {
          "id": "5679d66ffb396fe706fa02ac",
          "idBoard": "5679d53ee4466e2b6f9bba8c",
          "name": "Important",
          "color": "red",
          "uses": 12
        },
        {
          "id": "5679d60efb396fe706fa01df",
          "idBoard": "5679d53ee4466e2b6f9bba8c",
          "name": "Future Release",
          "color": "orange",
          "uses": 11
        }
      ],
      "shortUrl": "https://trello.com/c/nPNsC9o2",
      "subscribed": false,
      "url": "https://trello.com/c/nPNsC9o2/4-chunkloading",
      "attachments": []
	 */
}