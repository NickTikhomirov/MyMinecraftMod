package koldunec.vint.compatibility.TinkerBook;

import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;
import slimeknights.tconstruct.library.traits.AbstractTrait;

import java.util.HashSet;

public class ModifierAppender extends SectionTransformer {

    public static HashSet<AbstractTrait> APPENDANTS = new HashSet<>();

    public ModifierAppender() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        for(AbstractTrait app: APPENDANTS) {
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
