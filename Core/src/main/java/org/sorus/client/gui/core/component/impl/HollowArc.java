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

package org.sorus.client.gui.core.component.impl;

import org.sorus.client.Sorus;
import org.sorus.client.gui.core.component.Component;

/** Implementation of {@link Component} for a hollow arc. */
public class HollowArc extends Component {

  private double xRadius, yRadius;
  private int startAngle, endAngle;
  private double thickness;

  /** Draws a hollow arc to the screen based on state. */
  @Override
  public void onRender() {
    Sorus.getSorus()
        .getGUIManager()
        .getRenderer()
        .drawHollowArc(
            this.absoluteX(),
            this.absoluteY(),
            this.xRadius * this.absoluteXScale(),
            this.yRadius * this.absoluteYScale(),
            startAngle,
            endAngle,
            thickness * this.absoluteXScale(),
            this.absoluteColor());
  }

  /**
   * Sets the radius' of the hollow arc.
   *
   * @param xRadius the x radius of the hollow arc
   * @param yRadius the y radius of the hollow arc
   * @return the hollow arc
   */
  public <T extends HollowArc> T radius(double xRadius, double yRadius) {
    this.xRadius = xRadius;
    this.yRadius = yRadius;
    return this.cast();
  }

  /**
   * Sets the angles of the hollow arc.
   *
   * @param startAngle the angle to start the hollow arc
   * @param endAngle the angle to end the hollow arc
   * @return the hollow arc
   */
  public <T extends HollowArc> T angle(int startAngle, int endAngle) {
    this.startAngle = startAngle;
    this.endAngle = endAngle;
    return this.cast();
  }

  /**
   * Sets the thickness of the hollow arc.
   *
   * @param thickness the thickness of the hollow arc
   * @return the hollow arc
   */
  public <T extends HollowArc> T thickness(double thickness) {
    this.thickness = thickness;
    return this.cast();
  }
}
