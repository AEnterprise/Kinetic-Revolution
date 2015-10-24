package kineticrevolution.duster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityFallingBlock;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;

/**
 * Created by AEnterprise
 */
public enum Components {
	PLAYER_PRESSURE_PLATE("playerPressurePlate") {
		@Override
		public boolean canEntityDust(Entity entity) {
			return entity instanceof EntityPlayer;
		}
	},

	SLIME_PRESSURE_PLATE("slimePressurePlate") {
		@Override
		public boolean canEntityDust(Entity entity) {
			return entity instanceof EntitySlime;
		}
	},

	ANVIL_PRESSURE_PLATE("anvilPressurePlate") {
		@Override
		public boolean canEntityDust(Entity entity) {
			return entity instanceof EntityFallingBlock && ((EntityFallingBlock) entity).func_145805_f() == Blocks.anvil;
		}
	};

	private final String name;

	Components(String name) {
		this.name = name;
	}

	public static Components getComponent(String name) {
		for (Components c : values()) {
			if (c.name.equalsIgnoreCase(name))
				return c;
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public boolean canEntityDust(Entity entity) {
		return false;
	}
}
