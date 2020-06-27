package koldunec.vint.compatibility.Tinker.TinkerBook;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.element.TextData;
import slimeknights.mantle.client.gui.book.GuiBook;
import slimeknights.mantle.client.gui.book.element.BookElement;
import slimeknights.mantle.client.gui.book.element.ElementItem;
import slimeknights.mantle.client.gui.book.element.ElementText;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.book.content.ContentMaterial;
import slimeknights.tconstruct.library.book.elements.ElementTinkerItem;
import slimeknights.tconstruct.library.materials.IMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.tools.IToolPart;
import slimeknights.tconstruct.library.tools.ToolCore;
import slimeknights.tconstruct.library.traits.ITrait;
import slimeknights.tconstruct.smeltery.TinkerSmeltery;
import slimeknights.tconstruct.smeltery.block.BlockCasting;
import slimeknights.tconstruct.tools.TinkerTools;
import slimeknights.tconstruct.tools.common.block.BlockToolTable;
import slimeknights.tconstruct.tools.harvest.TinkerHarvestTools;
import slimeknights.tconstruct.tools.melee.TinkerMeleeWeapons;
import slimeknights.tconstruct.tools.ranged.TinkerRangedWeapons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;

public class HeadlessMaterialData extends ContentMaterial {
    public HeadlessMaterialData(Material material) {
        super(material);
    }

    @Override
    public void build(BookData book, ArrayList<BookElement> list, boolean rightSide) {
        Material material = TinkerRegistry.getMaterial(materialName);
        boolean hasHandle = material.hasStats("handle");
        addTitle(list, material.getLocalizedNameColored(), true);
        this.addDisplayItems(list, rightSide ? GuiBook.PAGE_WIDTH - 18 : 0);
        int col_margin = 22;
        int top = 15;
        int left = rightSide ? 0 : col_margin;
        int y = top + 10;
        int x = left + 10;
        int w = GuiBook.PAGE_WIDTH / 2 - 10;
        x+=GuiBook.PAGE_WIDTH / 4;              //centering happens probably
        LinkedHashSet<ITrait> allTraits = new LinkedHashSet();
        if(hasHandle){
            addStatsDisplay(x, y, w, list, allTraits, "handle");
            y += 65 + 10 * material.getAllTraitsForStats("handle").size();
        }
        addStatsDisplay(x, y, w, list, allTraits, "extra");
        // No flavor due to centering
        // nobody cares

    }


    // Copy-pasted from TiC because somebody is offensively private
    public void addStatsDisplay(int x, int y, int w, ArrayList<BookElement> list, LinkedHashSet<ITrait> allTraits, String stattype) {
        Material material = TinkerRegistry.getMaterial(materialName);
        IMaterialStats stats = material.getStats(stattype);
        if (stats != null) {
            List<ITrait> traits = material.getAllTraitsForStats(stats.getIdentifier());
            allTraits.addAll(traits);
            List<ItemStack> parts = Lists.newLinkedList();
            Iterator var10 = TinkerRegistry.getToolParts().iterator();
            while(var10.hasNext()) {
                IToolPart part = (IToolPart)var10.next();
                if (part.hasUseForStat(stats.getIdentifier())) {
                    parts.add(part.getItemstackWithMaterial(material));
                }
            }
            if (parts.size() > 0) {
                ElementItem display = new ElementTinkerItem(x, y + 1, 0.5F, parts);
                list.add(display);
            }
            ElementText name = new ElementText(x + 10, y, w - 10, 10, stats.getLocalizedName());
            name.text[0].underlined = true;
            list.add(name);
            y += 12;
            List<TextData> lineData = Lists.newArrayList();
            lineData.addAll(getStatLines(stats));
            lineData.addAll(getTraitLines(traits, material));
            list.add(new ElementText(x, y, w, GuiBook.PAGE_HEIGHT, lineData));
        }
    }

    // Copy-pasted from TiC because somebody is offensively private
    private void addDisplayItems(ArrayList<BookElement> list, int x) {
        Material material = TinkerRegistry.getMaterial(materialName);
        List<ElementItem> displayTools = Lists.newArrayList();
        int y = 10;
        if (!material.getRepresentativeItem().isEmpty()) {
            displayTools.add(new ElementTinkerItem(material.getRepresentativeItem()));
        }

        ItemStack basin;
        ElementTinkerItem elementItem;
        if (material.isCraftable()) {
            basin = new ItemStack(TinkerTools.toolTables, 1, BlockToolTable.TableTypes.PartBuilder.meta);
            elementItem = new ElementTinkerItem(basin);
            elementItem.tooltip = ImmutableList.of(this.parent.translate("material.craft_partbuilder"));
            displayTools.add(elementItem);
        }

        if (material.isCastable()) {
            basin = new ItemStack(TinkerSmeltery.castingBlock, 1, BlockCasting.CastingType.BASIN.getMeta());
            elementItem = new ElementTinkerItem(basin);
            String text = this.parent.translate("material.craft_casting");
            elementItem.tooltip = ImmutableList.of(String.format(text, material.getFluid().getLocalizedName(new FluidStack(material.getFluid(), 0))));
            displayTools.add(elementItem);
        }

        ToolCore[] tools = new ToolCore[]{TinkerHarvestTools.pickaxe, TinkerHarvestTools.mattock, TinkerMeleeWeapons.broadSword, TinkerHarvestTools.hammer, TinkerMeleeWeapons.cleaver, TinkerRangedWeapons.shuriken, TinkerMeleeWeapons.fryPan, TinkerHarvestTools.lumberAxe, TinkerMeleeWeapons.battleSign};
        ToolCore[] var13 = tools;
        int var15 = tools.length;

        for(int var8 = 0; var8 < var15; ++var8) {
            ToolCore tool = var13[var8];
            if (tool != null) {
                ImmutableList.Builder<Material> builder = ImmutableList.builder();

                for(int i = 0; i < tool.getRequiredComponents().size(); ++i) {
                    builder.add(material);
                }

                ItemStack builtTool = tool.buildItem(builder.build());
                if (tool.hasValidMaterials(builtTool)) {
                    displayTools.add(new ElementTinkerItem(builtTool));
                }

                if (displayTools.size() == 9) {
                    break;
                }
            }
        }

        if (!displayTools.isEmpty()) {
            Iterator var14 = displayTools.iterator();

            while(var14.hasNext()) {
                ElementItem element = (ElementItem)var14.next();
                element.x = x;
                element.y = y;
                element.scale = 1.0F;
                y += 16;
                list.add(element);
            }
        }

    }
}
