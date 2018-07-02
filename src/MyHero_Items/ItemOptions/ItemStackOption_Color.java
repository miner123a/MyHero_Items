package MyHero_Items.ItemOptions;


import MyHero_Core.Managers.LangManager;
import MyHero_Items.ItemManager.AbstractItemStackOption;
import MyHero_Items.ItemManager.ItemStackOption;
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
				LangManager.Log(nfe.getMessage());
				LangManager.Log(LangManager.Color_Error);
			}
		}
		
	}
	
	

}
