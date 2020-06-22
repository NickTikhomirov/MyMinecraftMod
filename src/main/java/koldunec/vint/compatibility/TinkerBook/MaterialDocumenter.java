package koldunec.vint.compatibility.TinkerBook;


import koldunec.vint.compatibility.TinkerIntegration;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.data.element.ImageData;
import slimeknights.mantle.client.gui.book.element.ElementImage;
import slimeknights.mantle.client.gui.book.element.ElementItem;
import slimeknights.mantle.client.gui.book.element.SizedBookElement;
import slimeknights.tconstruct.library.book.content.ContentMaterial;
import slimeknights.tconstruct.library.book.content.ContentPageIconList;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.materials.Material;

import java.util.HashSet;


public class MaterialDocumenter extends SectionTransformer {
    public static HashSet<Material> APPENDANTS = new HashSet<>();

    public MaterialDocumenter() {
        super("materials");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        int i = 0;
        for(;section.pages.get(i).content instanceof ContentPageIconList;++i){}  // search for end
        --i;
        ContentPageIconList overview = (ContentPageIconList)section.pages.get(i).content;
        for(Material material: APPENDANTS){
            PageData page = addPage(section, material.getIdentifier(), ContentMaterial.ID, new ContentMaterial(material));
            SizedBookElement icon;
            if(material.getRepresentativeItem() != null)
                icon = new ElementItem(0, 0, 1f, material.getRepresentativeItem());
            else
                icon = new ElementImage(ImageData.MISSING);
            overview.addLink(icon, material.getLocalizedNameColored(), page);

        }
    }
}
