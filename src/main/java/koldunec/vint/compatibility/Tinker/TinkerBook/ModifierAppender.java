package koldunec.vint.compatibility.Tinker.TinkerBook;

import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.modifiers.Modifier;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.HashSet;

public class ModifierAppender extends SectionTransformer {

    public static HashSet<Modifier> APPENDANTS = new HashSet<>();

    public ModifierAppender() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        int i = 0;
        for(; section.pages.get(i).content instanceof ContentListing; ++i){}  // search for end
        --i;
        ContentListing listing = (ContentListing)section.pages.get(i).content;
        for(Modifier app: APPENDANTS) {
            PageData pd = new PageData();
            pd.source = new FileRepository("vint:book");
            pd.parent = section;
            pd.type = "modifier";
            pd.data = "modifiers/"+app.getIdentifier()+".json";
            section.pages.add(pd);
            pd.load();
            listing.addEntry(app.getLocalizedName(), pd);
        }
    }
}
