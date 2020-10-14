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

package org.sorus.client.gui.core.component;

import java.awt.*;
import org.sorus.client.gui.core.IContainer;

/** The parent of all {@link Component}, rendered directly on the screen. */
public class Panel extends Collection {

  private IContainer container;

  /**
   * Gets the absolute x based on the parents state.
   *
   * @return absolute x
   */
  @Override
  public double absoluteX() {
    return this.x;
  }

  /**
   * Gets the absolute y based on the parents state.
   *
   * @return absolute y
   */
  @Override
  public double absoluteY() {
    return this.y;
  }

  /**
   * Gets the absolute x scale based on the parents state.
   *
   * @return absolute x scale
   */
  @Override
  public double absoluteXScale() {
    return this.xScale;
  }

  /**
   * Gets the absolute y scale based on the parents state.
   *
   * @return absolute y scale
   */
  @Override
  public double absoluteYScale() {
    return this.yScale;
  }

  /**
   * Gets the absolute color based on the parents state.
   *
   * @return absolute color
   */
  @Override
  public Color absoluteColor() {
    return this.color;
  }

  @Override
  protected IContainer getContainer() {
    return container;
  }

  public void onRender(IContainer container) {
    this.container = container;
    super.onRender();
  }
}
