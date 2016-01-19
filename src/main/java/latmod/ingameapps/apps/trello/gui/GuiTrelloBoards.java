package latmod.ingameapps.apps.trello.gui;

import ftb.lib.api.gui.GuiLM;
import ftb.lib.api.gui.widgets.ButtonLM;
import latmod.ingameapps.apps.trello.TBoard;

import java.util.ArrayList;

/**
 * Created by LatvianModder on 26.12.2015.
 */
public class GuiTrelloBoards extends GuiLM
{
	public final ArrayList<ButtonBoard> buttons;
	
	public GuiTrelloBoards()
	{
		super(null, null);
		
		buttons = new ArrayList<>();
	}
	
	public void addWidgets()
	{
		buttons.clear();
		
		mainPanel.addAll(buttons);
	}
	
	public void drawBackground()
	{
	}
	
	public static class ButtonBoard extends ButtonLM
	{
		public final TBoard board;
		
		public ButtonBoard(GuiTrelloBoards g, TBoard b)
		{
			super(g, 8, 8, (g.width - 32) / 3, 60);
			board = b;
			
			posX += (g.buttons.size() % 3) * (width + 8);
		}
		
		public void onButtonPressed(int b)
		{
		}
		
		public void renderWidget()
		{
		}
	}
}