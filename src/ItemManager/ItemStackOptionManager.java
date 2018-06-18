package ItemManager;

import java.util.HashMap;

import ItemOptions.ItemStackOption_Amount;
import ItemOptions.ItemStackOption_Color;
import ItemOptions.ItemStackOption_DisplayName;
import ItemOptions.ItemStackOption_Enchant;
import ItemOptions.ItemStackOption_Lore;

interface ItemStackOptionInterface
{
	ItemStackOption Create(AbstractItemStackOption e);
}

public class ItemStackOptionManager
{
	public static HashMap<String,ItemStackOptionInterface> ListOptions = new HashMap<>();
	
	public static void Load()
	{
		ListOptions.put("displayname", e -> new ItemStackOption_DisplayName(e));
		ListOptions.put("lore", e -> new ItemStackOption_Lore(e));
		ListOptions.put("amount", e -> new ItemStackOption_Amount(e));
		ListOptions.put("enchant", e -> new ItemStackOption_Enchant(e));
		ListOptions.put("color", e -> new ItemStackOption_Color(e));
	}
	
}
