package ItemOptions;

import Core.LangManager;
import Core.MyHeroMain_Items;
import ItemManager.AbstractItemStackOption;
import ItemManager.ItemStackOption;
import cn.nukkit.item.Item;
import cn.nukkit.item.ItemColorArmor;
import cn.nukkit.utils.DyeColor;

public class ItemStackOption_Color extends ItemStackOption{

	public ItemStackOption_Color(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@Override
	public void addItemStackOption(Object... value) {
		Item i = getItem();
		if(i instanceof ItemColorArmor)
		{
			try
			{
				ItemColorArmor color = (ItemColorArmor)i;
				color.setColor(DyeColor.getByWoolData((int)value[0]));
			}
			catch(Exception nfe)
			{
				MyHeroMain_Items.Main.getLogger().info(nfe.getMessage());
				MyHeroMain_Items.Main.getLogger().info(LangManager.Color_Error);
			}
		}
		
	}
	
	

}
