package koldunec.vint.helpers;

public class IntegrationHelper {
    public boolean isLoadedTwilight = false;
    public boolean isLoadedTinkers = false;
    public boolean isLoadedProjectX = false;
    public boolean isLoadedProjectRed_exploration = false;
    public boolean isLoadedSulfurTorches = false;
    public boolean isLoadedBaubles = false;
    public boolean isLoadedPrimitive = false;
    public boolean isLoadedThaumcraft = false;
    public boolean isLoadedNatura = false;
    public boolean isLoadedHype = false;
    public boolean isLoadedScalingH = false;
    public boolean isLoadedQuark = false;
    public boolean isLoadedCharm = false;
    public boolean isLoadedChisel = false;
    public boolean isLoadedFuture = false;

    public String idTwilight = "twilightforest";
    public String idTinker = "tconstruct";
    public String idProjectRed = "projectred-exploration";
    public String idFuture = "minecraftfuture";

    public void init(){
        isLoadedTwilight = isLoaded(idTwilight);
        isLoadedTinkers = isLoaded(idTinker);
        isLoadedHype = isLoaded("hypewear");
        isLoadedProjectRed_exploration = isLoaded(idProjectRed);
        isLoadedProjectX = isLoaded("projectx");
        isLoadedSulfurTorches = isLoaded("sulfurtorches");
        isLoadedBaubles = isLoaded("baubles");
        isLoadedPrimitive = isLoaded("primitivemobs");
        isLoadedThaumcraft = isLoaded("thaumcraft");
        isLoadedNatura = isLoaded("natura");
        isLoadedScalingH = isLoaded("scalinghealth");
        isLoadedQuark = isLoaded("quark");
        isLoadedCharm = isLoaded("charm");
        isLoadedChisel = isLoaded("chisel");
        isLoadedFuture = isLoaded(idFuture);
    }

    public boolean isLoaded(String name){
        return net.minecraftforge.fml.common.Loader.isModLoaded(name);
    }
}
