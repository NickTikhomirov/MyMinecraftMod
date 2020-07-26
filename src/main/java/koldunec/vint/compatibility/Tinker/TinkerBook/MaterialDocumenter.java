package koldunec.vint.compatibility.Tinker.TinkerBook;


import koldunec.vint.compatibility.Tinker.TinkerIntegration;
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


public class MaterialDocumenter extends SectionTransformer {

    public MaterialDocumenter() {
        super("materials");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        for(Material material: TinkerIntegration.APPENDANTS_MAT){
            PageData page = addPage(section, material.getIdentifier(), ContentMaterial.ID, new HeadlessMaterialData(material));
            SizedBookElement icon;
            if(material.getRepresentativeItem() != null)
                icon = new ElementItem(0, 0, 1f, material.getRepresentativeItem());
            else
                icon = new ElementImage(ImageData.MISSING);

           // boolean put = false;
            int i=0;
            for(; section.pages.get(i).content instanceof ContentPageIconList; ++i){
                if(((ContentPageIconList)section.pages.get(i).content).addLink(icon, material.getLocalizedNameColored(),page)){
             //       put = true;
                    break;
                }
            }
            //if(!put){
            //    PageData page1 = new PageData();
            //    page1.source = section.source;
            //    page1.parent = section;
            //    page1.content = new ContentPageIconList(20);
            //    page1.load();
            //    section.pages.add(i,page1);
            //}
        }
    }
}