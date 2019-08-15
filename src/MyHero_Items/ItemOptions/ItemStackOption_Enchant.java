package MyHero_Items.ItemOptions;

import java.util.List;

import MyHero_Core.Managers.LangManager;
import me.petterim1.customenchantsnk;
import MyHero_Items.ItemManager.AbstractItemStackOption;
import MyHero_Items.ItemManager.ItemStackOption;
import cn.nukkit.item.enchantment.Enchantment;

public class ItemStackOption_Enchant extends ItemStackOption
{
	public ItemStackOption_Enchant(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void addItemStackOption(Object... value)
	{
		try
		{
			List<String> Enchants = (List<String>)value[0];
			for(String enchant : Enchants)
			{
				try
				{
					int EnchantID,Power;
					if(enchant.contains(":"))
					{
						String[] enchantparateters = enchant.split(":");
						EnchantID = Integer.parseInt(enchantparateters[0]);
						Power = Integer.parseInt(enchantparateters[1]);
					}
					else
					{
						EnchantID = Integer.parseInt(enchant);
						Power = 1;
					}
					Enchantment e = Enchantment.getEnchantment(EnchantID);
					e.setLevel(Power, false);
					getItem().addEnchantment(e);
				}
				catch(NumberFormatException e)
				{
					LangManager.Log(e.toString());
				}
			}
		}
		catch(Exception ex)
		{
			LangManager.Log(ex.toString());
		}
		
	}
}
