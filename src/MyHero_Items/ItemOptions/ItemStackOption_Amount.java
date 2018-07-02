package MyHero_Items.ItemOptions;


import MyHero_Core.Managers.LangManager;
import MyHero_Items.ItemManager.AbstractItemStackOption;
import MyHero_Items.ItemManager.ItemStackOption;

public class ItemStackOption_Amount extends ItemStackOption
{
	public ItemStackOption_Amount(AbstractItemStackOption itemStackOption)
	{
		super(itemStackOption);
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
		try
		{
			int Amount = Integer.parseInt(value[0].toString());
			getItem().setCount(Amount);
		}
		catch(NumberFormatException nfe)
		{
			LangManager.Log(LangManager.Item_Amount_Is_Not_Int);
		}
		
		
	}
}
