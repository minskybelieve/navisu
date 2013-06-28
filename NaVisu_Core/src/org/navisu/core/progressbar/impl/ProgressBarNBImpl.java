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
package org.navisu.core.progressbar.impl;

import org.navisu.core.progressbar.ProgressBar;
import org.netbeans.api.progress.ProgressHandle;
import org.netbeans.api.progress.ProgressHandleFactory;

/**
 *
 * @author Thibault
 */
public class ProgressBarNBImpl implements ProgressBar {
    
    protected ProgressHandle progressHandle;
    
    public ProgressBarNBImpl(String title) {
        this.progressHandle = ProgressHandleFactory.createHandle(title);
    }

    @Override
    public void start() {
        this.progressHandle.start();
    }

    @Override
    public void progress(String message) {
        this.progressHandle.progress(message);
    }

    @Override
    public void start(int nbSteps) {
        this.progressHandle.start(nbSteps);
    }

    @Override
    public void progress(String message, int progression) {
        this.progressHandle.progress(message, progression);
    }

    @Override
    public void finish() {
        this.progressHandle.finish();
    }
}
