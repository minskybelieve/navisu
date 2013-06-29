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
package org.navisu.core.console.impl;

import org.navisu.core.console.ConsoleWriter;
import org.openide.windows.OutputWriter;

/**
 *
 * @author Thibault
 */
public class ConsoleWriterNBImpl implements ConsoleWriter {
    
    protected final OutputWriter writer;

    public ConsoleWriterNBImpl(final OutputWriter writer) {
        this.writer = writer;
    }

    @Override
    public void print(Object o) {
        this.writer.print(o);
    }

    @Override
    public void print(String s) {
        this.writer.print(s);
    }

    @Override
    public void println(Object o) {
        this.writer.println(o);
    }

    @Override
    public void println(String s) {
        this.writer.println(s);
    }

    @Override
    public void println() {
        this.writer.println();
    }
}
