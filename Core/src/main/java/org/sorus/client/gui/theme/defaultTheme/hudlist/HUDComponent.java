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

package org.sorus.client.gui.theme.defaultTheme.hudlist;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import org.sorus.client.Sorus;
import org.sorus.client.event.EventInvoked;
import org.sorus.client.event.impl.client.input.MousePressEvent;
import org.sorus.client.gui.core.component.Collection;
import org.sorus.client.gui.core.component.impl.HollowArc;
import org.sorus.client.gui.core.component.impl.Image;
import org.sorus.client.gui.core.component.impl.Rectangle;
import org.sorus.client.gui.core.component.impl.Text;
import org.sorus.client.gui.core.font.IFontRenderer;
import org.sorus.client.gui.hud.HUD;
import org.sorus.client.gui.hud.SingleHUD;
import org.sorus.client.gui.theme.defaultTheme.DefaultTheme;
import org.sorus.client.util.Axis;
import org.sorus.client.version.IGLHelper;

public class HUDComponent extends Collection {

  private final DefaultHUDListScreen screen;

  public HUDComponent(DefaultHUDListScreen screen, HUD hud) {
    this.screen = screen;
    IFontRenderer fontRenderer =
        Sorus.getSorus().getGUIManager().getRenderer().getRubikFontRenderer();
    this.add(
        new Rectangle().size(670, 125).position(5, 4).color(DefaultTheme.getMedgroundLayerColor()));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor())
            .size(670, 4)
            .position(5, 0));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor())
            .size(4, 4)
            .position(675, 0));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor())
            .size(4, 125)
            .position(675, 4));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor())
            .size(4, 4)
            .position(675, 129));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowStartColor())
            .size(670, 4)
            .position(5, 129));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor())
            .size(4, 4)
            .position(2, 129));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor())
            .size(4, 125)
            .position(2, 4));
    this.add(
        new Rectangle()
            .gradient(
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowStartColor(),
                DefaultTheme.getShadowEndColor(),
                DefaultTheme.getShadowEndColor())
            .size(4, 4)
            .position(1, 0));
    Collection collection = new Collection().position(15, 15);
    this.add(collection);
    // hud.addIconElements(collection);
    this.add(
        new Text()
            .fontRenderer(fontRenderer)
            .text(hud.getName())
            .position(125, 27)
            .scale(4, 4)
            .color(new Color(235, 235, 235, 210)));
    this.add(new SettingsButton(hud).position(615, 22));
    this.add(new RemoveButton(hud).position(555, 22));
    if (hud instanceof SingleHUD) {
      this.add(
          new HollowArc()
              .thickness(2)
              .radius(10, 10)
              .angle(0, 360)
              .position(130 + fontRenderer.getStringWidth(hud.getName()) * 4 + 15, 24.5)
              .color(new Color(235, 235, 235, 210)));
    } else {
      this.add(
          new HollowArc()
              .thickness(2)
              .radius(10, 10)
              .angle(0, 360)
              .position(130 + fontRenderer.getStringWidth(hud.getName()) * 4 + 15, 24.5)
              .color(new Color(235, 235, 235, 210)));
      this.add(
          new HollowArc()
              .thickness(2)
              .radius(10, 10)
              .angle(0, 360)
              .position(130 + fontRenderer.getStringWidth(hud.getName()) * 4 + 37.375, 24.5)
              .color(new Color(235, 235, 235, 210)));
      this.add(
          new HollowArc()
              .thickness(2)
              .radius(10, 10)
              .angle(0, 360)
              .position(130 + fontRenderer.getStringWidth(hud.getName()) * 4 + 60.375, 24.5)
              .color(new Color(235, 235, 235, 210)));
    }
    int i = 0;
    for (String string :
        this.getSplitDescription(
            hud.getDescription(),
            Sorus.getSorus().getGUIManager().getRenderer().getRubikFontRenderer(),
            150)) {
      this.add(
          new Text()
              .fontRenderer(Sorus.getSorus().getGUIManager().getRenderer().getRubikFontRenderer())
              .text(string)
              .position(125, 72 + i * 23)
              .scale(2, 2)
              .color(new Color(190, 190, 190, 210)));
      i++;
    }
  }

  public java.util.List<String> getSplitDescription(
      String description, IFontRenderer fontRenderer, double width) {
    List<String> strings = new ArrayList<>();
    StringBuilder stringBuilder = new StringBuilder();
    for (char c : description.toCharArray()) {
      stringBuilder.append(c);
      if (fontRenderer.getStringWidth(stringBuilder.toString()) > width) {
        String string = stringBuilder.toString();
        int index = string.lastIndexOf(" ");
        strings.add(string.substring(0, index));
        stringBuilder = new StringBuilder(string.substring(index + 1));
      }
    }
    strings.add(stringBuilder.toString());
    return strings;
  }

  public class SettingsButton extends Collection {

    private double hoverPercent;

    private long prevRenderTime;

    private final HUD hud;

    private final org.sorus.client.gui.core.component.impl.Image image;

    public SettingsButton(HUD hud) {
      this.hud = hud;
      this.add(image = new Image().resource("sorus/gear.png").size(40, 40));
      Sorus.getSorus().getEventManager().register(this);
    }

    @Override
    public void onRender() {
      long renderTime = System.currentTimeMillis();
      long deltaTime = renderTime - prevRenderTime;
      boolean hovered =
          this.isHovered(
              Sorus.getSorus().getVersion().getInput().getMouseX(),
              Sorus.getSorus().getVersion().getInput().getMouseY());
      hoverPercent =
          Math.max(0, Math.min(1, hoverPercent + (hovered ? 1 : -1) * deltaTime * 0.008));
      image.scale(1 + hoverPercent * 0.1, 1 + hoverPercent * 0.1);
      image.position(-2 * hoverPercent, -2 * hoverPercent);
      image.color(new Color(235, 235, 235, (int) (210 + 45 * hoverPercent)));
      prevRenderTime = renderTime;
      double x = this.absoluteX() + 20 * this.absoluteXScale();
      double y = this.absoluteY() + 20 * this.absoluteYScale();
      IGLHelper glHelper = Sorus.getSorus().getVersion().getGLHelper();
      glHelper.translate(x, y, 0);
      glHelper.rotate(Axis.Z, hoverPercent * 50);
      glHelper.translate(-x, -y, 0);
      super.onRender();
      glHelper.translate(x, y, 0);
      glHelper.rotate(Axis.Z, -hoverPercent * 50);
      glHelper.translate(-x, -y, 0);
    }

    @Override
    public void onRemove() {
      Sorus.getSorus().getEventManager().unregister(this);
      super.onRemove();
    }

    @EventInvoked
    public void onClick(MousePressEvent e) {
      if (this.isHovered(e.getX(), e.getY())) {
        Sorus.getSorus().getGUIManager().close(HUDComponent.this.screen.screen);
        hud.displaySettings(HUDComponent.this.screen.screen);
      }
    }

    private boolean isHovered(double x, double y) {
      return x > this.absoluteX()
          && x < this.absoluteX() + 50 * this.absoluteXScale()
          && y > this.absoluteY()
          && y < this.absoluteY() + 50 * this.absoluteYScale();
    }
  }

  public class RemoveButton extends Collection {

    private final HUD hud;

    private final Collection main;

    private double hoverPercent;

    private long prevRenderTime;

    public RemoveButton(HUD hud) {
      this.hud = hud;
      this.add(main = new Collection());
      main.add(new Rectangle().size(40, 40).smooth(5).color(new Color(160, 35, 35)));
      main.add(
          new Rectangle().size(30, 10).smooth(3).position(5, 15).color(new Color(210, 210, 210)));
      Sorus.getSorus().getEventManager().register(this);
    }

    @Override
    public void onRender() {
      long renderTime = System.currentTimeMillis();
      long deltaTime = renderTime - prevRenderTime;
      boolean hovered =
          this.isHovered(
              Sorus.getSorus().getVersion().getInput().getMouseX(),
              Sorus.getSorus().getVersion().getInput().getMouseY());
      hoverPercent =
          Math.max(0, Math.min(1, hoverPercent + (hovered ? 1 : -1) * deltaTime * 0.008));
      this.main
          .position(-hoverPercent * 2.25, -hoverPercent * 2.25)
          .scale(1 + hoverPercent * 0.1, 1 + hoverPercent * 0.1);
      prevRenderTime = renderTime;
      super.onRender();
    }

    @Override
    public void onRemove() {
      Sorus.getSorus().getEventManager().unregister(this);
      super.onRemove();
    }

    @EventInvoked
    public void onClick(MousePressEvent e) {
      if (this.isHovered(e.getX(), e.getY())) {
        Sorus.getSorus().getHUDManager().unregister(hud);
        HUDComponent.this.screen.updateHUDS();
      }
    }

    private boolean isHovered(double x, double y) {
      return x > this.absoluteX()
          && x < this.absoluteX() + 40 * this.absoluteXScale()
          && y > this.absoluteY()
          && y < this.absoluteY() + 40 * this.absoluteYScale();
    }
  }
}
