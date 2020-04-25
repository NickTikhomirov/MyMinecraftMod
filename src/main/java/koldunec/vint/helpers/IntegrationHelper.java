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

    public String idTwilight = "twilightforest";
    public String idTinker = "tconstruct";
    public String idProjectRed = "projectred-exploration";

    public void init(){
        isLoadedTwilight = isLoaded(idTwilight);
        isLoadedTinkers = isLoaded(idTinker);
        isLoadedHype = isLoaded("hypewear");
        isLoadedProjectRed_exploration = isLoaded(idProjectRed);
        //isLoadedBaubles;
    }

    public boolean isLoaded(String name){
        return net.minecraftforge.fml.common.Loader.isModLoaded(name);
    }
}
