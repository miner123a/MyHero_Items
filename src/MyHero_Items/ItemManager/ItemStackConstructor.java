package MyHero_Items.ItemManager;

import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.nukkit.item.Item;
import cn.nukkit.item.ItemColorArmor;
import cn.nukkit.item.enchantment.Enchantment;
import cn.nukkit.utils.TextFormat;

public class ItemStackConstructor extends AbstractItemStackOption
{
	private Item i;
	
	private int Data = 0;
	public ItemStackConstructor(int Id)
	{
		this.i = Item.get(Id);
	}
	public ItemStackConstructor(int Id,int data)
	{
		this.i = Item.get(Id,data);
		Data = data;
	}
	
	@Override
	public void addItemStackOption(Object... value)
	{
		
	}
	@Override
	protected Item getItem()
	{
		return this.i;
	}
	@Override
	public Item spawnItem()
	{
		
		Item item = Clone();
		//LangManager.Log("Obj 1: " + i + "\n"+" Obj 2: " + item);
		
		String pattern = "\\[(\\d+)\\-(\\d+)\\]";
		Pattern p = Pattern.compile(pattern);
		Matcher m;
		String lore[] = new String[item.getLore().length];
		//System.arraycopy( item.getLore(), 0, lore, 0, item.getLore().length);
		//Random r = new Random();
		int index = 0;
		for (String s : item.getLore())
		{
			
			m = p.matcher(s);
			while(m.find())
			{
				
				int left = Integer.parseInt(m.group(1)), right = Integer.parseInt(m.group(2));
				
				int from = Math.min(left, right), to = Math.max(left, right);
				
				//LangManager.Log("Generate: " + from + " to " +to );
				int exit = ThreadLocalRandom.current().nextInt(from, to + 1);//r.nextInt(to-from+1)+from;//
				//LangManager.Log("Generated: " + exit);
				s = s.replace(m.group(0), exit+"");
				
			}
			lore[index] = TextFormat.colorize(s);
			index++;
		}
		item.setLore(lore);
		return item;
	}
	@Override
	public ItemStackConstructor getRoot() {
		return this;
	}
	
	public Item Clone()
	{
		Item item = Item.get(i.getId(), Data);
		item.setLore(i.getLore().clone());
		for(Enchantment e : i.getEnchantments() )item.addEnchantment(e);
		item.setCustomName(i.getCustomName());
		item.setCount(i.getCount());
		if(i instanceof ItemColorArmor) 
			((ItemColorArmor)item).setColor(((ItemColorArmor) i).getColor());
		
		return item;
	}

}
