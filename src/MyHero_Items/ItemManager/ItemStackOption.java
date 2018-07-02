package MyHero_Items.ItemManager;

import cn.nukkit.item.Item;

public class ItemStackOption extends AbstractItemStackOption
{
	protected AbstractItemStackOption itemStackOption;
	public ItemStackOption(AbstractItemStackOption itemStackOption)
	{
		this.itemStackOption = itemStackOption;
	}
	@Override
	protected Item getItem()
	{
		return itemStackOption.getItem();
	}
	@Override
	public ItemStackConstructor getRoot()
	{
		return itemStackOption.getRoot();
	}
	@Override
	public Item spawnItem()
	{
		return getRoot().spawnItem();
	}
	@Override
	public void addItemStackOption(Object... value) {
		// TODO Auto-generated method stub
		
	}
}
