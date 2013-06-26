/**
 * This file is part of NaVisu.
 *
 * NaVisu is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * NaVisu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * NaVisu. If not, see <http://www.gnu.org/licenses/>.
 */
package org.navisu.charts.actions;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import org.navisu.charts._old.ChartsControllerServices;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.actions.Presenter;

@ActionID(
        category = "File",
        id = "org.navisu.charts.actions.PolygonLayerOpacity")
@ActionRegistration(
        lazy = false,
        displayName = "NOT-USED")
@ActionReference(
        path = "Toolbars/File", 
        position = 0)
public final class PolygonLayerOpacity extends AbstractAction implements Presenter.Toolbar {

    protected final ChartsControllerServices ctrlServices = Lookup.getDefault().lookup(ChartsControllerServices.class);
    
    protected JPanel createPresenter() {
        
        JPanel presenter = new JPanel(new BorderLayout());
        
        final JSlider slider = new JSlider(0, 100, 95);
        presenter.add(slider);
        
        slider.addChangeListener(new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
            
                double opacity = slider.getValue() > 0 ? slider.getValue()/100d : 0.00001;
                ctrlServices.setPolygonLayerOpacity(opacity);
            }
        });
        
        return presenter;
    }
    
    @Override
    public Component getToolbarPresenter() {
        return this.createPresenter();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {}
}
