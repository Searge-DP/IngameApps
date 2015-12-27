package latmod.ingameapps.apps.trello;

import ftb.lib.FTBLib;
import latmod.ingameapps.apps.IngameApp;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class TrelloApp extends IngameApp
{
	public static Trello trello = null;

	public TrelloApp()
	{
		super("trello", true);
	}

	public void load()
	{
	}

	public void onButtonClicked()
	{
		if(trello == null)
		{
			new Thread("TrelloAPI")
			{
				public void run()
				{
					try
					{
						trello = Trello.create("latvianmodder@gmail.com");
						FTBLib.printChat(null, "Connected to Trello account!");

						trello.refreshData();

						for(TBoard b : trello.boards)
						{
							FTBLib.dev_logger.info(b.name);

							FTBLib.dev_logger.info("{");

							for(TList l : b.lists)
							{
								FTBLib.dev_logger.info("  " + l.name);
								FTBLib.dev_logger.info("  {");

								for(TCard c : l.cards)
								{
									FTBLib.dev_logger.info("    " + c.name + " " + c.labels);
								}

								FTBLib.dev_logger.info("  }");
							}

							FTBLib.dev_logger.info("}");
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
						FTBLib.printChat(null, "Failed to connect to Trello account!");
					}
				}
			}.start();
		}
	}
}