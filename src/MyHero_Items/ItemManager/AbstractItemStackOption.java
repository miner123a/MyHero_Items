package MyHero_Items.ItemManager;

import cn.nukkit.item.Item;

public abstract class AbstractItemStackOption
{
	
	abstract void addItemStackOption(Object... value);
	abstract protected Item getItem();
	public abstract Item spawnItem();
	public abstract ItemStackConstructor getRoot();
}
