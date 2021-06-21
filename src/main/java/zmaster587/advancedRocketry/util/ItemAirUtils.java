package zmaster587.advancedRocketry.util;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import zmaster587.advancedRocketry.api.AdvancedRocketryAPI;
import zmaster587.advancedRocketry.api.ARConfiguration;
import zmaster587.advancedRocketry.api.AdvancedRocketryAPI;
import zmaster587.advancedRocketry.api.IAtmosphere;
import zmaster587.advancedRocketry.api.armor.IFillableArmor;
import zmaster587.advancedRocketry.api.armor.IProtectiveArmor;

import javax.annotation.Nonnull;

public class ItemAirUtils implements IFillableArmor {

	public static final ItemAirUtils INSTANCE = new ItemAirUtils();

	/**
	 * gets the amount of air remaining in the suit.
	 * @param stack stack from which to get an amount of air
	 * @return the amount of air in the stack
	 */
	@Override
	public int getAirRemaining(@Nonnull ItemStack stack) {

		if(stack.hasTag()) {
			return stack.getTag().getInt("air");
		}
		else {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putInt("air", 0);
			stack.setTag(nbt);
			return getMaxAir(stack);
		}
	}

	/**
	 * Sets the amount of air remaining in the suit (WARNING: DOES NOT BOUNDS CHECK!)
	 * @param stack the stack to operate on
	 * @param amt amount of air to set the suit to
	 */
	@Override
<<<<<<< HEAD
	public void setAirRemaining(ItemStack stack, int amt) {
		CompoundNBT nbt;
		if(stack.hasTag()) {
			nbt = stack.getTag();
=======
	public void setAirRemaining(@Nonnull ItemStack stack, int amt) {
		NBTTagCompound nbt;
		if(stack.hasTagCompound()) {
			nbt = stack.getTagCompound();
>>>>>>> origin/feature/nuclearthermalrockets
		}
		else {
			nbt = new CompoundNBT();
		}
		nbt.putInt("air", amt);
		stack.setTag(nbt);
	}

	/**
	 * Decrements air in the suit by amt
	 * @param stack the item stack to operate on
	 * @param amt amount of air by which to decrement
	 * @return The amount of air extracted from the suit
	 */
	@Override
	public int decrementAir(@Nonnull ItemStack stack, int amt) {

		CompoundNBT nbt;
		if(stack.hasTag()) {
			nbt = stack.getTag();
		}
		else {
			nbt = new CompoundNBT();
		}

		int prevAmt = nbt.getInt("air");
		int newAmt = Math.max(prevAmt - amt,0);
		nbt.putInt("air", newAmt);
		stack.setTag(nbt);

		return prevAmt - newAmt;
	}

	/**
	 * Increments air in the suit by amt
	 * @param stack the item stack to operate on
	 * @param amt amount of air by which to decrement
	 * @return The amount of air inserted into the suit
	 */
	@Override
	public int increment(@Nonnull ItemStack stack, int amt) {

		CompoundNBT nbt;
		if(stack.hasTag()) {
			nbt = stack.getTag();
		}
		else {
			nbt = new CompoundNBT();
		}

		int prevAmt = nbt.getInt("air");
		int newAmt = Math.min(prevAmt + amt, getMaxAir(stack));
		nbt.putInt("air", newAmt);
		stack.setTag(nbt);

		return newAmt - prevAmt;
	}

	/**
	 * @return the maximum amount of air allowed in this suit
	 */
	@Override
	public int getMaxAir(@Nonnull ItemStack stack) {

		return ARConfiguration.getCurrentConfig().spaceSuitOxygenTime.get()*1200; //30 minutes;
	}

	public boolean isStackValidAirContainer(@Nonnull ItemStack stack) {
		if(stack.isEmpty())
			return false;

		//Check for enchantment
		boolean isEnchanted = false;
<<<<<<< HEAD
		ListNBT enchList = stack.getEnchantmentTagList();
		if(enchList != null) {
			for(int i = 0 ; i < enchList.size(); i++) {
				CompoundNBT compound = enchList.getCompound(i);
				isEnchanted = AdvancedRocketryAPI.enchantmentSpaceProtection.getRegistryName().equals(ResourceLocation.tryCreate(compound.getString("id")));
				if(isEnchanted)
					break;
			}
=======
		NBTTagList enchList = stack.getEnchantmentTagList();
		for(int i = 0 ; i < enchList.tagCount(); i++) {
			NBTTagCompound compound = enchList.getCompoundTagAt(i);
			isEnchanted = compound.getShort("id") == Enchantment.getEnchantmentID(AdvancedRocketryAPI.enchantmentSpaceProtection);
			if(isEnchanted)
				break;
>>>>>>> origin/feature/nuclearthermalrockets
		}
		return isEnchanted;
	}
	
	public static class ItemAirWrapper implements IFillableArmor, IProtectiveArmor {
		ItemStack stack;
		
		public ItemAirWrapper(@Nonnull ItemStack myStack) {
			stack = myStack;
		}

		@Override
		public int getAirRemaining(@Nonnull ItemStack stack) {
			return ItemAirUtils.INSTANCE.getAirRemaining(this.stack);
		}

		@Override
		public void setAirRemaining(@Nonnull ItemStack stack, int amt) {
			ItemAirUtils.INSTANCE.setAirRemaining(this.stack,amt);
		}

		@Override
		public int decrementAir(@Nonnull ItemStack stack, int amt) {
			return ItemAirUtils.INSTANCE.decrementAir(this.stack, amt);
		}

		@Override
		public int increment(@Nonnull ItemStack stack, int amt) {
			return ItemAirUtils.INSTANCE.increment(this.stack, amt);
		}

		@Override
		public int getMaxAir(@Nonnull ItemStack stack) {
			return ItemAirUtils.INSTANCE.getMaxAir(this.stack);
		}

		@Override
<<<<<<< HEAD
		public boolean protectsFromSubstance(IAtmosphere atmosphere,
				ItemStack stack, boolean commitProtection) {
			if(stack != null && stack.getItem() instanceof ArmorItem) {
				if(((ArmorItem) stack.getItem()).getEquipmentSlot() == EquipmentSlotType.CHEST )
=======
		public boolean protectsFromSubstance(IAtmosphere atmosphere, @Nonnull ItemStack stack, boolean commitProtection) {
			if(!stack.isEmpty() && stack.getItem() instanceof ItemArmor) {
				if(((ItemArmor) stack.getItem()).armorType == EntityEquipmentSlot.CHEST )
>>>>>>> origin/feature/nuclearthermalrockets
					return decrementAir(stack, 1) == 1;
				
				return true;
			}
			return false;
		}
		
	}
}
