package koldunec.vint.init;

public class IntegrationHelper {
    public static boolean isLoadedTwilight = false;
    public static boolean isLoadedTinkers = false;
    public static boolean isLoadedProjectX = false;
    public static boolean isLoadedProjectRed_exploration = false;
    public static boolean isLoadedSulfurTorches = false;
    public static boolean isLoadedBaubles = false;
    public static boolean isLoadedPrimitive = false;
    public static boolean isLoadedThaumcraft = false;
    public static boolean isLoadedNatura = false;
    public static boolean isLoadedHype = false;
    public static boolean isLoadedScalingH = false;
    public static boolean isLoadedQuark = false;
    public static boolean isLoadedCharm = false;
    public static boolean isLoadedChisel = false;
    public static boolean isLoadedFuture = false;

    public static String idTwilight = "twilightforest";
    public static String idTinker = "tconstruct";
    public static String idProjectRed = "projectred-exploration";
    public static String idFuture = "minecraftfuture";

    public static void init(){
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

    public static boolean isLoaded(String name){
        return net.minecraftforge.fml.common.Loader.isModLoaded(name);
    }
}
