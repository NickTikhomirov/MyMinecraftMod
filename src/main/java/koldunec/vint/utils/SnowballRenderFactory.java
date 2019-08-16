package koldunec.vint.utils;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.IRenderFactory;

public class SnowballRenderFactory implements IRenderFactory {

    public final Item item;

    //Конструктор. В него мы передаём предмет, от которого нужно взять текстуру
    public SnowballRenderFactory(Item item) {
        this.item = item;
    }

    @Override
    public Render createRenderFor(RenderManager manager) {
        //Возвращаем рендер снежка, и передаём предмет, который мы инициализировали в конструкторе
        return new RenderSnowball(manager, item, Minecraft.getMinecraft().getRenderItem());
    }

}