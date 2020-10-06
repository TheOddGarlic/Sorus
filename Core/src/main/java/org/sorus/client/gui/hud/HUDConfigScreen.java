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

package org.sorus.client.gui.hud;

import java.awt.*;
import org.sorus.client.Sorus;
import org.sorus.client.event.EventInvoked;
import org.sorus.client.event.impl.client.input.MousePressEvent;
import org.sorus.client.gui.core.Screen;
import org.sorus.client.gui.core.component.Collection;
import org.sorus.client.gui.core.component.Panel;
import org.sorus.client.gui.core.component.impl.*;
import org.sorus.client.gui.core.component.impl.Rectangle;
import org.sorus.client.gui.core.font.IFontRenderer;
import org.sorus.client.gui.screen.ISelectComponentReceiver;
import org.sorus.client.gui.screen.SelectComponentScreen;
import org.sorus.client.version.input.Key;

public class HUDConfigScreen extends Screen {

  private final HUD hud;

  private Panel main;
  private Scroll scroll;

  private int componentCount;

  public HUDConfigScreen(HUD hud) {
    this.hud = hud;
  }

  @Override
  public void init() {
    Sorus.getSorus().getVersion().getRenderer().enableBlur();
    main = new Panel();
    Collection menu = new Collection().position(610, 140);
    main.add(menu);
    menu.add(new Rectangle().smooth(5).size(700, 720).position(0, 70).color(new Color(18, 18, 18)));
    menu.add(new Rectangle().size(700, 65).position(0, 5).color(new Color(30, 30, 30)));
    menu.add(new Arc().radius(5, 5).angle(180, 270).position(0, 0).color(new Color(30, 30, 30)));
    menu.add(new Arc().radius(5, 5).angle(90, 180).position(690, 0).color(new Color(30, 30, 30)));
    menu.add(new Rectangle().size(690, 5).position(5, 0).color(new Color(30, 30, 30)));
    menu.add(
        new Rectangle()
            .gradient(
                new Color(14, 14, 14, 0),
                new Color(14, 14, 14, 0),
                new Color(14, 14, 14),
                new Color(14, 14, 14))
            .size(700, 7)
            .position(0, 70));
    IFontRenderer fontRenderer =
        Sorus.getSorus().getGUIManager().getRenderer().getRubikFontRenderer();
    menu.add(
        new Text()
            .fontRenderer(fontRenderer)
            .text("SORUS")
            .position(350 - fontRenderer.getStringWidth("SORUS") / 2 * 5.5, 17.5)
            .scale(5.5, 5.5)
            .color(new Color(215, 215, 215)));
    menu.add(
        new Text()
            .fontRenderer(fontRenderer)
            .text(hud.getName())
            .position(350 - fontRenderer.getStringWidth(hud.getName()) / 2 * 4.5, 95)
            .scale(4.5, 4.5)
            .color(new Color(215, 215, 215)));
    menu.add(new HUDConfigScreen.Add().position(320, 705));
    Scissor scissor = new Scissor().size(680, 600).position(10, 150);
    this.scroll = new Scroll();
    scroll.position(0, 2);
    scissor.add(scroll);
    menu.add(scissor);
    this.updateComponents();
  }

  public void updateComponents() {
    scroll.clear();
    componentCount = 0;
    for (IComponent component : this.hud.getComponents()) {
      scroll.add(
          new ComponentComponent(this, (Component) component).position(0, 135 * componentCount));
      componentCount++;
    }
  }

  @Override
  public void onRender() {
    main.scale(
        Sorus.getSorus().getVersion().getScreen().getScaledWidth() / 1920,
        Sorus.getSorus().getVersion().getScreen().getScaledHeight() / 1080);
    main.onRender();
  }

  @Override
  public void exit() {
    Sorus.getSorus().getSettingsManager().save();
    Sorus.getSorus().getVersion().getRenderer().disableBlur();
    main.onRemove();
  }

  @Override
  public void keyTyped(Key key) {
    if (key == Key.ESCAPE && this.isInteractContainer()) {
      Sorus.getSorus().getGUIManager().close(this);
    }
  }

  public HUD getHUD() {
    return hud;
  }

  @Override
  public boolean shouldTakeOutOfGame() {
    return true;
  }

  public class Add extends Collection {

    private final Collection main;
    private final Collection centerCross;
    private double expandedPercent;

    private long prevRenderTime;

    public Add() {
      Sorus.getSorus().getEventManager().register(this);
      this.add(main = new Collection());
      main.add(new Rectangle().smooth(4).size(60, 60).color(new Color(35, 160, 65)));
      this.centerCross = new Collection();
      this.centerCross.add(new Rectangle().smooth(3).size(10, 40).position(25, 10));
      this.centerCross.add(new Rectangle().smooth(3).size(40, 10).position(10, 25));
      main.add(centerCross);
    }

    @Override
    public void onRender() {
      long renderTime = System.currentTimeMillis();
      long deltaTime = System.currentTimeMillis() - prevRenderTime;
      double mouseX = Sorus.getSorus().getVersion().getInput().getMouseX();
      double mouseY = Sorus.getSorus().getVersion().getInput().getMouseY();
      boolean hovered = this.isHovered(mouseX, mouseY);
      double x = -80 * expandedPercent;
      expandedPercent =
          Math.min(Math.max(0, expandedPercent + (hovered ? 1 : -1) * deltaTime / 100.0), 1);
      main.scale(1 + 0.1 * expandedPercent, 1 + 0.1 * expandedPercent)
          .position(-2.5 * expandedPercent, -2.5 * expandedPercent);
      prevRenderTime = renderTime;
      super.onRender();
    }

    @EventInvoked
    public void onClick(MousePressEvent e) {
      if (this.isHovered(e.getX(), e.getY())) {
        Sorus.getSorus().getGUIManager().close(HUDConfigScreen.this);
        Sorus.getSorus()
            .getGUIManager()
            .open(
                new SelectComponentScreen(Sorus.getSorus().getHUDManager(), new OnAddComponent()));
      }
    }

    private boolean isHovered(double mouseX, double mouseY) {
      double x = this.absoluteX() - 80 * expandedPercent * this.absoluteXScale();
      double y = this.absoluteY();
      double width = (60 + 160 * expandedPercent) * this.absoluteXScale();
      double height = 60 * this.absoluteYScale();
      return mouseX > x && mouseX < x + width && mouseY > y && mouseY < y + height;
    }

    @Override
    public void onRemove() {
      Sorus.getSorus().getEventManager().unregister(this);
      super.onRemove();
    }

    public class OnAddComponent implements ISelectComponentReceiver {

      @Override
      public void select(IComponent selected) {
        HUDConfigScreen.this.hud.addComponent(selected);
        HUDConfigScreen.this.hud.displaySettings();
      }

      @Override
      public void cancel() {}
    }
  }
}
