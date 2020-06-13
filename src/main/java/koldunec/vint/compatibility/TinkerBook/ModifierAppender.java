package koldunec.vint.compatibility.TinkerBook;

import koldunec.vint.compatibility.TinkerIntegration;
import slimeknights.mantle.client.book.data.BookData;
import slimeknights.mantle.client.book.data.PageData;
import slimeknights.mantle.client.book.data.SectionData;
import slimeknights.mantle.client.book.repository.FileRepository;
import slimeknights.tconstruct.library.book.content.ContentListing;
import slimeknights.tconstruct.library.book.sectiontransformer.SectionTransformer;

public class ModifierAppender extends SectionTransformer {

    public ModifierAppender() {
        super("modifiers");
    }

    @Override
    public void transform(BookData book, SectionData section) {
        ContentListing listing = (ContentListing)section.pages.get(0).content;
        PageData pd =  new PageData();
        pd.source = new FileRepository("vint:book");
        pd.parent = section;
        pd.type = "modifier";
        pd.data = "modifiers/primal.json";
        section.pages.add(pd);
        pd.load();
        listing.addEntry(TinkerIntegration.PRIMAL.getLocalizedName(), pd);
    }
}
