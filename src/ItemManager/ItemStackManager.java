package ItemManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

import com.google.common.base.Preconditions;

import Core.LangManager;
import Core.MyHeroMain_Items;
import cn.nukkit.Server;
import cn.nukkit.utils.Utils;

public class ItemStackManager
{
	public static HashMap<String,AbstractItemStackOption> ItemList = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	public static void LoadItems()
	{
		String path = MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "");
		File ItemFileRoot = new File(path + "/MyHero/Items/");
		if(!ItemFileRoot.exists())
			ItemFileRoot.mkdirs();
		saveResource("Test.yml","Test.yml", false);
		
		File[] ItemFileList = ItemFileRoot.listFiles();
		//MyHeroMain_Items.Main.getLogger().info(ItemFileList[0]+"");
		if(ItemFileList != null)
			for(File ItemsFile : ItemFileList)
			{
				//MyHeroMain_Items.Main.getLogger().info(ItemsFile.getPath());
				if(ItemsFile.isFile())
				{
					if(ItemsFile.getName().contains(".yml"))
					{
						
						Yaml ItemsFileYML = new Yaml();
						ItemsFileYML.load(ItemsFile.getPath());
						String itemerrorname = "";
						try
						{
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
										MyHeroMain_Items.Main.getLogger().info(LangManager.Item_ID_Is_Not_Int.replaceAll("%Mob_Name%",Item.getKey()));
									}
									ItemStackConstructor ItemCreator;
									if(!Item.getValue().containsKey("Data"))
										ItemCreator = new ItemStackConstructor(ItemID);
									else
										ItemCreator = new ItemStackConstructor(ItemID,Integer.parseInt(Item.getValue().get("Data").toString()));
									AbstractItemStackOption OptionLast = ItemCreator;
									//MyHeroMain_Items.Main.getLogger().info("Item id: " + ItemID);
									for(Map.Entry<String, Object> Options : Item.getValue().entrySet())
									{
										//MyHeroMain_Items.Main.getLogger().info("Option add: " + Options.getKey());
										//MyHeroMain_Items.Main.getLogger().info("Option value: " + Options.getValue().toString());
										//MyHeroMain_Items.Main.getLogger().info("Exist: " + ItemStackOptionManager.ListOptions.containsKey(Options.getKey()));
										if( ItemStackOptionManager.ListOptions.containsKey(Options.getKey().toLowerCase()))
										{
											//MyHeroMain_Items.Main.getLogger().info("Option add: " + Options.getKey());
											//MyHeroMain_Items.Main.getLogger().info("Option value: " + Options.getValue().toString());
											if(Options.getValue() == null) MyHeroMain_Items.Main.getLogger().info(LangManager.Item_Error.replaceAll("%Item_Name%",Item.getKey()));
											ItemStackOption Option = ItemStackOptionManager.ListOptions.get(Options.getKey().toString().toLowerCase()).Create(OptionLast);
											Option.addItemStackOption(Options.getValue());
											OptionLast = Option;
											
										}
									}
									//MyHeroMain_Items.Main.getLogger().info(LangManager.Item_Load_Succes.replaceAll("%Item_Name%",Item.getKey()) );
									ItemStackManager.ItemList.put(Item.getKey(),OptionLast);
								}
								else
								{
									MyHeroMain_Items.Main.getLogger().info(LangManager.Item_No_ID.replaceAll("%Item_Name%",Item.getKey()));
								}
							}
						}
						catch ( IOException e )
						{
							//e.printStackTrace();
							 MyHeroMain_Items.Main.getLogger().info(LangManager.Item_Error.replaceAll("%Item_Name%",itemerrorname));
						}
						
					}
				}
			}
	}
	
	public static boolean saveResource(String filename, String outputName, boolean replace) {
        Preconditions.checkArgument(filename != null && outputName != null, "Filename can not be null!");
        Preconditions.checkArgument(filename.trim().length() != 0 && outputName.trim().length() != 0, "Filename can not be empty!");

        File out = new File(MyHeroMain_Items.Main.getDataFolder().toString().replaceAll(MyHeroMain_Items.Main.getName(), "")+ "/MyHero/Items/", outputName);
        if (!out.exists() || replace) {
            try (InputStream resource = MyHeroMain_Items.Main.getResource(filename)) {
                if (resource != null) {
                    File outFolder = out.getParentFile();
                    if (!outFolder.exists()) {
                        outFolder.mkdirs();
                    }
                    Utils.writeFile(out, resource);

                    return true;
                }
            } catch (IOException e) {
                Server.getInstance().getLogger().logException(e);
            }
        }
        return false;
    }
	
	
	public static void Reload()
	{
		ItemList = new HashMap<>();
		LoadItems();
	}
	
}
