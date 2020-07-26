package koldunec.vint.compatibility.Tinker.TinkerBook;

import koldunec.vint.compatibility.Tinker.TinkerIntegration;
import net.minecraft.client.resources.I18n;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.modifiers.Modifier;


public class ModifierAppender extends SectionTransformer {

    public ModifierAppender() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        boolean flag = false;
        int i = 0;
        for (; section.pages.get(i).content instanceof ContentListing; ++i) {
        }  // search for end
        --i;
        ContentListing listing = (ContentListing) section.pages.get(i).content;
        for (Modifier app : TinkerIntegration.APPENDANTS_MOD) {
            PageData pd = new PageData();
            pd.source = new FileRepository("vint:book");
            pd.parent = section;
            pd.type = "modifier";
            pd.data = "modifiers/" + app.getIdentifier() + ".json";
            section.pages.add(pd);
            pd.load();
            if (!flag) {
                listing.addEntry(I18n.format("vint.tcon.placetaker"), pd);
                flag = true;
            }
        }
    }
}