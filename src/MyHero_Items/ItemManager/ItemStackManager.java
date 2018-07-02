package MyHero_Items.ItemManager;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import MyHero_Core.Core.MyHeroMain;
import MyHero_Core.DataManagment.DataItems;
import MyHero_Core.Managers.LangManager;
import MyHero_Core.Managers.ResourceManager;


public class ItemStackManager
{
	
	
	@SuppressWarnings("unchecked")
	public static void LoadItems()
	{
		//String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File ItemFileRoot = new File(ResourceManager.getPathTo("Items"));
		if(!ItemFileRoot.exists())
			ItemFileRoot.mkdirs();
		ResourceManager.saveResource("TestItems.yml","TestItems.yml", false,"Items");
		File[] ItemFileList = ItemFileRoot.listFiles();
		//LangManager.Log(ItemFileList[0]+"");
		if(ItemFileList != null)
			for(File ItemsFile : ItemFileList)
			{
				
				
				if(ItemsFile.isFile())
				{
					if(ItemsFile.getName().contains(".yml"))
					{
						
						Yaml ItemsFileYML = new Yaml();
						ItemsFileYML.load(ItemsFile.getPath());
						String itemerrorname = "";
						try
						{
							DataItems myherodata = MyHeroMain.getMyHeroData().getDataItems();
							Map<String, Map<String, Object> > Items = ItemsFileYML.loadAs(Files.newInputStream(Paths.get(ItemsFile.getPath())), Map.class);
							for(Map.Entry<String,Map<String, Object>> Item : Items.entrySet())
							{
								itemerrorname = Item.getKey();
								//MyHeroMain.Main.getLogger().info("Key: " + Item.getKey());
								if(Item.getValue() != null && Item.getValue().containsKey("ID"))
								{
									int ItemID = 0;
									try
									{
										ItemID = Integer.parseInt(Item.getValue().get("ID").toString());
									}
									catch(NumberFormatException nfe)
									{
										LangManager.Log(LangManager.Item_ID_Is_Not_Int.replaceAll("%Mob_Name%",Item.getKey()));
									}
									ItemStackConstructor ItemCreator;
									if(!Item.getValue().containsKey("Data"))
										ItemCreator = new ItemStackConstructor(ItemID);
									else
										ItemCreator = new ItemStackConstructor(ItemID,Integer.parseInt(Item.getValue().get("Data").toString()));
									AbstractItemStackOption OptionLast = ItemCreator;
									//LangManager.Log("Item id: " + ItemID);
									for(Map.Entry<String, Object> Options : Item.getValue().entrySet())
									{
										//LangManager.Log("Option add: " + Options.getKey());
										//LangManager.Log("Option value: " + Options.getValue().toString());
										//LangManager.Log("Exist: " + ItemStackOptionManager.ListOptions.containsKey(Options.getKey()));
										if( myherodata.OptionExist(Options.getKey().toLowerCase()))
										{
											//LangManager.Log("Option add: " + Options.getKey());
											//LangManager.Log("Option value: " + Options.getValue().toString());
											if(Options.getValue() == null) LangManager.Log(LangManager.Item_Error.replaceAll("%Item_Name%",Item.getKey()));
											
											ItemStackOption Option = myherodata.getOption(Options.getKey().toString().toLowerCase()).Create(OptionLast);
											
											Option.addItemStackOption(Options.getValue());
											OptionLast = Option;
											
										}
									}
									//LangManager.Log(LangManager.Item_Load_Succes.replaceAll("%Item_Name%",Item.getKey()) );
									myherodata.addItem(Item.getKey(),OptionLast);
								}
								else
								{
									LangManager.Log(LangManager.Item_No_ID.replaceAll("%Item_Name%",Item.getKey()));
								}
							}
						}
						catch ( IOException e )
						{
							//e.printStackTrace();
							LangManager.Log(LangManager.Item_Error.replaceAll("%Item_Name%",itemerrorname));
						}
						
					}
				}
			}
	}
}
