/*
 * MIT License
 *
 * Copyright (c) 2020 Danterus
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package org.sorus.client.module.impl.armorstatus;

import org.sorus.client.Sorus;
import org.sorus.client.gui.core.component.Collection;
import org.sorus.client.gui.core.component.Panel;
import org.sorus.client.gui.core.component.impl.Item;
import org.sorus.client.gui.core.component.impl.Rectangle;
import org.sorus.client.gui.core.component.impl.Text;
import org.sorus.client.gui.hud.Component;
import org.sorus.client.gui.screen.settings.components.ColorPicker;
import org.sorus.client.gui.screen.settings.components.Toggle;
import org.sorus.client.settings.Setting;
import org.sorus.client.version.game.IItemStack;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ArmorStatusComponent extends Component {

    private final Setting<Boolean> rawDurability;
    private final Setting<Color> backgroundColor;

    private final Panel modPanel;
    private final Collection mainCollection;
    private final Rectangle background;

    private List<IItemStack> prevArmor = new ArrayList<>();

    public ArmorStatusComponent() {
        super("ARMOR STATUS");
        this.register(rawDurability = new Setting<>("rawDurability", false));
        this.register(backgroundColor = new Setting<>("backgroundColor", new Color(0, 0, 0, 50)));
        this.modPanel = new Panel();
        modPanel.add(background = new Rectangle());
        this.modPanel.add(mainCollection = new Collection());
    }

    @Override
    public void render(double x, double y) {
        this.background.size(this.hud.getWidth(), this.getHeight()).color(backgroundColor.getValue());
        List<IItemStack> armor = Sorus.getSorus().getVersion().getGame().getPlayer().getInventory().getArmor();
        if(!armor.equals(prevArmor)) {
            this.mainCollection.clear();
            int i = armor.size() - 1;
            for(IItemStack iItemStack : armor) {
                mainCollection.add(new SingleArmorComponent(iItemStack).position(2, 2 + i * 18));
                i--;
            }
        }
        prevArmor = armor;
        this.modPanel.position(x, y);
        this.modPanel.onRender();
    }

    @Override
    public double getWidth() {
        double maxWidth = 0;
        for(org.sorus.client.gui.core.component.Component component : mainCollection.getComponents()) {
            maxWidth = Math.max(maxWidth, ((SingleArmorComponent) component).width + 2);
        }
        return maxWidth + 4;
    }

    @Override
    public double getHeight() {
        return this.mainCollection.getComponents().size() * 18 + 4;
    }

    @Override
    public void addConfigComponents(Collection collection) {
        collection.add(new Toggle(rawDurability, "Raw Durability"));
        collection.add(new ColorPicker(backgroundColor, "Background Color"));
    }

    public class SingleArmorComponent extends Collection {

        private final IItemStack iItemStack;
        private final Text text;

        private double width;

        public SingleArmorComponent(IItemStack iItemStack) {
            this.iItemStack = iItemStack;
            this.add(new Item().itemStack(iItemStack));
            this.add(text = new Text().fontRenderer(Sorus.getSorus().getGUIManager().getRenderer().getMinecraftFontRenderer()).position(20, 4.5));
        }

        @Override
        public void onRender() {
            if(ArmorStatusComponent.this.rawDurability.getValue()) {
                this.text.text(String.valueOf((iItemStack.getMaxDamage() - iItemStack.getDamage())));
            } else {
                this.text.text(String.format("%.2f", (iItemStack.getMaxDamage() - iItemStack.getDamage()) / (double) iItemStack.getMaxDamage() * 100) + "%");
            }
            this.width = this.text.width() + 20;
            super.onRender();
        }

    }

}
