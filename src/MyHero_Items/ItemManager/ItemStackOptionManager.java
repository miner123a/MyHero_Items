package MyHero_Items.ItemManager;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataItems;
import MyHero_Items.ItemOptions.ItemStackOption_Amount;
import MyHero_Items.ItemOptions.ItemStackOption_Color;
import MyHero_Items.ItemOptions.ItemStackOption_DisplayName;
import MyHero_Items.ItemOptions.ItemStackOption_Enchant;
import MyHero_Items.ItemOptions.ItemStackOption_Lore;


public class ItemStackOptionManager
{

	interface ItemStackOptionInterface
	{
		ItemStackOption Create(AbstractItemStackOption e);
	}

	public static void Load()
	{
		DataItems data = MyHeroMain.getMyHeroData().getDataItems();
		data.addItemStackOption("displayname", e -> new ItemStackOption_DisplayName(e));
		data.addItemStackOption("lore", e -> new ItemStackOption_Lore(e));
		data.addItemStackOption("amount", e -> new ItemStackOption_Amount(e));
		data.addItemStackOption("enchant", e -> new ItemStackOption_Enchant(e));
		data.addItemStackOption("color", e -> new ItemStackOption_Color(e));
	}
	
}
