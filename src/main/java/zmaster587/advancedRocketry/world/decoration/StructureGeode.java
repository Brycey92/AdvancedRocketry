package zmaster587.advancedRocketry.world.decoration;

import com.mojang.serialization.Codec;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.structure.Structure;
import net.minecraft.world.gen.feature.structure.StructureStart;
import net.minecraft.world.gen.feature.template.TemplateManager;
import net.minecraftforge.common.BiomeDictionary;

public class StructureGeode  extends Structure<ProbabilityConfig>  {

	public StructureGeode(Codec<ProbabilityConfig> p_i231947_1_) {
		super(p_i231947_1_);
	}

	public Structure.IStartFactory<ProbabilityConfig> getStartFactory() {
		return StructureGeode.Start::new;
	}

	public static class Start extends StructureStart<ProbabilityConfig> {
		public Start(Structure<ProbabilityConfig> p_i225801_1_, int p_i225801_2_, int p_i225801_3_, MutableBoundingBox p_i225801_4_, int p_i225801_5_, long p_i225801_6_) {
			super(p_i225801_1_, p_i225801_2_, p_i225801_3_, p_i225801_4_, p_i225801_5_, p_i225801_6_);
		}

		public void func_230364_a_(DynamicRegistries p_230364_1_, ChunkGenerator p_230364_2_, TemplateManager p_230364_3_, int p_230364_4_, int p_230364_5_, Biome p_230364_6_, ProbabilityConfig p_230364_7_) {
			StructurePieceGeode geodePiece = new StructurePieceGeode(this.rand, p_230364_4_ * 16, p_230364_5_ * 16);
			this.components.add(geodePiece);
			this.recalculateStructureSize();
		}
	}
	
	@Override
	public String getStructureName() {
		return "advancedrocketry:geode";
	}

	public GenerationStage.Decoration func_236396_f_() {
		return GenerationStage.Decoration.LOCAL_MODIFICATIONS;
	}

	//Geodes should absolutely not be generating on top of liquid. That they could before was a huge oversight
	private static boolean canGeodeGenerate(World world, int x, int z) {
		ResourceLocation biomeName = world.getBiome(new BlockPos(x, 0, z)).getRegistryName();
		if(biomeName == null) {
			return false;
		}
		RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biomeName);

		return !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.OCEAN) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.RIVER) && !BiomeDictionary.hasType(biomeKey, BiomeDictionary.Type.BEACH);
	}
}
