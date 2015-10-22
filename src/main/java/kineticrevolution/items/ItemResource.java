package kineticrevolution.items;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import kineticrevolution.lib.Names;
import kineticrevolution.lib.Reference;
import kineticrevolution.lib.Textures;
import kineticrevolution.loaders.ItemLoader;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ItemResource extends ItemBase {
	private static final SortedSet<Resource> resources = new TreeSet<Resource>(new Comparator<Resource>() {
		@Override
		public int compare(Resource o1, Resource o2) {
			return Integer.compare(o1 != null ? o1.getMeta() : Integer.MAX_VALUE, o2 != null ? o2.getMeta() : Integer.MIN_VALUE);
		}
	});
	private static int currentMeta = 0;
	@SideOnly(Side.CLIENT)
	private IIcon[] icons;

	public ItemResource() {
		super(Names.Items.RESOURCE);
		setHasSubtypes(true);
	}

	@Override
	public String getUnlocalizedName(ItemStack stack) {
		Resource resource = Resource.get(stack);
		if (resource != null)
			return "item." + resource.getName();
		return super.getUnlocalizedName(stack);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int meta) {
		return meta >= 0 && meta < icons.length ? icons[meta] : super.getIconFromDamage(meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[Resource.getMaxMeta() + 1];
		for (Resource resource : resources) {
			if (resource != null)
				icons[resource.getMeta()] = register.registerIcon(Reference.MOD_ID + ":" + resource.getTextureName());
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubItems(Item item, CreativeTabs tab, List list) {
		for (Resource resource : resources) {
			if (resource != null)
				list.add(resource.getStack());
		}
	}

	public enum Resource {

		GRINDING_WHEEL(Names.Items.Resources.GRINDING_WHEEL),
		SPRING_TIN(Names.Items.Resources.SPRING_TIN),
		SPRING_SILVER(Names.Items.Resources.SPRING_SILVER),
		SPRING_LEAD(Names.Items.Resources.SPRING_LEAD),
		SPRING_IRON(Names.Items.Resources.SPRING_IRON),
		SPRING_GOLD(Names.Items.Resources.SPRING_GOLD),
		SPRING_ENDERIUM(Names.Items.Resources.SPRING_ENDERIUM),
		SPRING_ELECTRUM(Names.Items.Resources.SPRING_ELECTRUM),
		SPRING_DIAMOND(Names.Items.Resources.SPRING_DIAMOND),
		SPRING_COPPER(Names.Items.Resources.SPRING_COPPER),
		SPRING_BRONZE(Names.Items.Resources.SPRING_BRONZE);

		private final int meta;
		private final String name;

		Resource(String name) {
			int meta = currentMeta++;
			if (meta < 0 || meta >= Short.MAX_VALUE)
				throw new RuntimeException("Meta '" + meta + "' is out of bounds!");
			Resource resource = get(meta);
			if (resource != null)
				throw new RuntimeException("Meta '" + meta + "' is already occupied by the resource '" + resource + "'!");
			this.meta = meta;
			this.name = name;
			resources.add(this);
		}

		public static Resource get(ItemStack stack) {
			if (stack != null)
				return get(stack.getItemDamage());
			return null;
		}

		public static Resource get(int meta) {
			for (Resource resource : resources) {
				if (resource != null && resource.getMeta() == meta)
					return resource;
			}
			return null;
		}

		public static int getMaxMeta() {
			Resource resource = resources.last();
			return resource != null ? resource.getMeta() : 0;
		}

		public int getMeta() {
			return meta;
		}

		public String getName() {
			return name;
		}

		public ItemStack getStack() {
			return getStack(1);
		}

		public ItemStack getStack(int count) {
			return new ItemStack(ItemLoader.resourceItem, count, meta);
		}

		public String getTextureName() {
			return Textures.Items.RESOURCE_ITEM_PREFIX + name;
		}

	}

}
