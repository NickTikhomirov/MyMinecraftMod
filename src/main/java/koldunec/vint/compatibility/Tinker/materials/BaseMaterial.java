package koldunec.vint.compatibility.Tinker.materials;

//import koldunec.vint.compatibility.Tinker.TinkerBook.MaterialDocumenter;
import slimeknights.tconstruct.library.TinkerRegistry;
import slimeknights.tconstruct.library.materials.AbstractMaterialStats;
import slimeknights.tconstruct.library.materials.Material;
import slimeknights.tconstruct.library.materials.MaterialTypes;


public abstract class BaseMaterial extends Material {

    static String HEAD = MaterialTypes.HEAD;
    static String EXTRA = MaterialTypes.EXTRA;
    static String HANDLE = MaterialTypes.HANDLE;
    static String SHAFT = MaterialTypes.SHAFT;
    static String BOW = MaterialTypes.BOW;
    static String STRING = MaterialTypes.BOWSTRING;
    static String CORE = "core";
    static String PLATES = "plates";
    static String TRIM = "trim";

    protected BaseMaterial(String identifier, int color) {
        super(identifier, color);
    }

    public boolean has(String name){
        return stats.containsKey(name);
    }



    //public abstract boolean hasHead();
    //public abstract boolean hasExtra();
    //public abstract boolean hasHandle();
    //public abstract boolean hasShaft();
    //public abstract boolean hasString();
    //public abstract boolean hasBow();



    //public abstract List<ITrait> getCommonTraitsForTool();
    //public abstract List<ITrait> getHeadTraits();
    //public abstract List<ITrait> getHandleTraits();
    //public abstract List<ITrait> getExtraTraits();
    //public abstract List<ITrait> getBowTraits();

    //public void addTraitsAll(List<ITrait> traits, String name){
    //    if(traits==null)
    //        return;
    //    for(ITrait trait: traits) {
    //        this.addTrait(trait, name);
    //    }
    //}

    public abstract void registerTraits();
    public abstract boolean allowed();
    public abstract void addParts();

    //public void registerTraits(){
    //    if(hasHead()){
    //        addTraitsAll(getHeadTraits(), HEAD);
    //        addTraitsAll(getCommonTraitsForTool(), HEAD);
    //    }
    //    if(hasHandle()){
    //        addTraitsAll(getHandleTraits(), HANDLE);
    //        addTraitsAll(getCommonTraitsForTool(), HANDLE);
    //    }
    //    if(hasExtra()){
    //        addTraitsAll(getExtraTraits(), EXTRA);
    //        addTraitsAll(getCommonTraitsForTool(), EXTRA);
    //    }
    //    if(hasBow()){
    //        addTraitsAll(getBowTraits(), BOW);
    //        addTraitsAll(getCommonTraitsForTool(), BOW);
    //    }
    //    if(hasShaft()){
    //        addTraitsAll(getBowTraits(), SHAFT);
    //        addTraitsAll(getCommonTraitsForTool(), SHAFT);
    //    }
    //    if(has)
    //}

    public void register(){
        if(!allowed())
            return;
        TinkerRegistry.integrate(this).preInit();
        //if(!has(HEAD) && (has(EXTRA) || has(HANDLE)))
        //    MaterialDocumenter.APPENDANTS.add(this);
    }

    protected void addStat(AbstractMaterialStats abs){
        TinkerRegistry.addMaterialStats(this, abs);
    }
}
