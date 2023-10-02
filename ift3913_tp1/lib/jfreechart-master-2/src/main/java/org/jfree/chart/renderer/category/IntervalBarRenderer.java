/* ===========================================================
 * JFreeChart : a free chart library for the Java(tm) platform
 * ===========================================================
 *
 * (C) Copyright 2000-2022, by David Gilbert and Contributors.
 *
 * Project Info:  http://www.jfree.org/jfreechart/index.html
 *
 * This library is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 2.1 of the License, or
 * (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public
 * License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301,
 * USA.
 *
 * [Oracle and Java are registered trademarks of Oracle and/or its affiliates. 
 * Other names may be trademarks of their respective owners.]
 *
 * ------------------------
 * IntervalBarRenderer.java
 * ------------------------
 * (C) Copyright 2002-2020, by Jeremy Bowman and Contributors.
 *
 * Original Author:  Jeremy Bowman;
 * Contributor(s):   David Gilbert;
 *                   Christian W. Zuckschwerdt;
 *                   Peter Kolb (patch 2497611, 2791407);
 *
 */

package org.jfree.chart.renderer.category;

import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.entity.EntityCollection;
import org.jfree.chart.labels.CategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.api.RectangleEdge;
import org.jfree.data.Range;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.IntervalCategoryDataset;

/**
 * A renderer that handles the drawing of bars for a bar plot where
 * each bar has a high and low value.  This renderer is for use with the
 * {@link CategoryPlot} class.  The example shown here is generated by the
 * {@code IntervalBarChartDemo1.java} program included in the JFreeChart
 * Demo Collection:
 * <br><br>
 * <img src="doc-files/IntervalBarRendererSample.png"
 * alt="IntervalBarRendererSample.png">
 */
public class IntervalBarRenderer extends BarRenderer {

    /** For serialization. */
    private static final long serialVersionUID = -5068857361615528725L;

    /**
     * Constructs a new renderer.
     */
    public IntervalBarRenderer() {
        super();
    }

    /**
     * Returns the range of values from the specified dataset.  For this
     * renderer, this is equivalent to calling
     * {@code findRangeBounds(dataset, true)}.
     *
     * @param dataset  the dataset ({@code null} permitted).
     *
     * @return The range (or {@code null} if the dataset is
     *         {@code null} or empty).
     */
    @Override
    public Range findRangeBounds(CategoryDataset dataset) {
        return findRangeBounds(dataset, true);
    }

    /**
     * Draws the bar for a single (series, category) data item.
     *
     * @param g2  the graphics device.
     * @param state  the renderer state.
     * @param dataArea  the data area.
     * @param plot  the plot.
     * @param domainAxis  the domain axis.
     * @param rangeAxis  the range axis.
     * @param dataset  the dataset.
     * @param row  the row index (zero-based).
     * @param column  the column index (zero-based).
     * @param pass  the pass index.
     */
    @Override
    public void drawItem(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, CategoryDataset dataset, int row, int column,
            int pass) {

         if (dataset instanceof IntervalCategoryDataset) {
             IntervalCategoryDataset d = (IntervalCategoryDataset) dataset;
             drawInterval(g2, state, dataArea, plot, domainAxis, rangeAxis,
                     d, row, column);
         } else {
             super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis,
                     dataset, row, column, pass);
         }

     }

     /**
      * Draws a single interval.
      *
      * @param g2  the graphics device.
      * @param state  the renderer state.
      * @param dataArea  the data plot area.
      * @param plot  the plot.
      * @param domainAxis  the domain axis.
      * @param rangeAxis  the range axis.
      * @param dataset  the data.
      * @param row  the row index (zero-based).
      * @param column  the column index (zero-based).
      */
     protected void drawInterval(Graphics2D g2, CategoryItemRendererState state,
            Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis,
            ValueAxis rangeAxis, IntervalCategoryDataset dataset, int row,
                                 int column) {

        int visibleRow = state.getVisibleSeriesIndex(row);
        if (visibleRow < 0) {
            return;
        }

        PlotOrientation orientation = plot.getOrientation();
        double rectX = 0.0;
        double rectY = 0.0;

        RectangleEdge rangeAxisLocation = plot.getRangeAxisEdge();

        // Y0
        Number value0 = dataset.getEndValue(row, column);
        if (value0 == null) {
            return;
        }
        double java2dValue0 = rangeAxis.valueToJava2D(value0.doubleValue(),
                dataArea, rangeAxisLocation);

        // Y1
        Number value1 = dataset.getStartValue(row, column);
        if (value1 == null) {
            return;
        }
        double java2dValue1 = rangeAxis.valueToJava2D(
                value1.doubleValue(), dataArea, rangeAxisLocation);

        if (java2dValue1 < java2dValue0) {
            double temp = java2dValue1;
            java2dValue1 = java2dValue0;
            java2dValue0 = temp;
        }

        // BAR WIDTH
        double rectWidth = state.getBarWidth();

        // BAR HEIGHT
        double rectHeight = Math.abs(java2dValue1 - java2dValue0);

        RectangleEdge barBase = RectangleEdge.LEFT;
        if (orientation == PlotOrientation.HORIZONTAL) {
            // BAR Y
            rectX = java2dValue0;
            rectY = calculateBarW0(getPlot(), orientation, dataArea, 
                    domainAxis, state, visibleRow, column);
            rectHeight = state.getBarWidth();
            rectWidth = Math.abs(java2dValue1 - java2dValue0);
            barBase = RectangleEdge.LEFT;
        } else if (orientation.isVertical()) {
            // BAR X
            rectX = calculateBarW0(getPlot(), orientation, dataArea, 
                    domainAxis, state, visibleRow, column);
            rectY = java2dValue0;
            barBase = RectangleEdge.BOTTOM;
        }
        Rectangle2D bar = new Rectangle2D.Double(rectX, rectY, rectWidth,
                rectHeight);
        BarPainter painter = getBarPainter();
        if (state.getElementHinting()) {
            beginElementGroup(g2, dataset.getRowKey(row), 
                    dataset.getColumnKey(column));
        }
        if (getShadowsVisible()) {
            painter.paintBarShadow(g2, this, row, column, bar, barBase, false);
        }
        getBarPainter().paintBar(g2, this, row, column, bar, barBase);
        if (state.getElementHinting()) {
            endElementGroup(g2);
        }

        CategoryItemLabelGenerator generator = getItemLabelGenerator(row,
                column);
        if (generator != null && isItemLabelVisible(row, column)) {
            drawItemLabel(g2, dataset, row, column, plot, generator, bar,
                    false);
        }

        // add an item entity, if this information is being collected
        EntityCollection entities = state.getEntityCollection();
        if (entities != null) {
            addItemEntity(entities, dataset, row, column, bar);
        }

    }

    /**
     * Tests this renderer for equality with an arbitrary object.
     *
     * @param obj  the object ({@code null} permitted).
     *
     * @return A boolean.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof IntervalBarRenderer)) {
            return false;
        }
        // there are no fields to check
        return super.equals(obj);
    }

}
