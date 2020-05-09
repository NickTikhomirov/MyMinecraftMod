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


public class BambooDocumenter extends SectionTransformer {

    public BambooDocumenter() {
        super("materials");
    }


    @Override
    public void transform(BookData book, SectionData section) {
        ContentPageIconList overview = (ContentPageIconList)section.pages.get(0).content;
        Material material = TinkerIntegration.BAMBOO;
        PageData page = addPage(section, material.getIdentifier(), ContentMaterial.ID, new ContentMaterial(material));
        SizedBookElement icon;
        if(material.getRepresentativeItem() != null)
            icon = new ElementItem(0, 0, 1f, material.getRepresentativeItem());
        else
            icon = new ElementImage(ImageData.MISSING);
        overview.addLink(icon, material.getLocalizedNameColored(), page);
    }
}
