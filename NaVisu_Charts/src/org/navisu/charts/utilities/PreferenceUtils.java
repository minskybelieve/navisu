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
package org.navisu.charts.utilities;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 *
 * @author Thibault
 */
public class PreferenceUtils {
    
    public static final String LIST_SEPARATOR = ";";
    
    public static <T> void storeList(Preferences prefs, String key, List<T> list) {
        StringBuilder sb = new StringBuilder();
        
        for(T t : list) {
            sb.append(t.toString()).append(LIST_SEPARATOR);
        }
        
        prefs.put(key, sb.toString());
    }
    
    public static <T> List<T> loadList(Preferences prefs, String key, Converter<String, T> converter) {
        
        List<T> list = new ArrayList<>();
        
        String pref = prefs.get(key, "");
        if(!pref.isEmpty()) {
            
            String[] elmts = pref.split(LIST_SEPARATOR);
            for(String elmt : elmts) {
                list.add(converter.convert(elmt));
            }
        }
        
        return list;
    }
    
    public static List<String> loadStringList(Preferences prefs, String key) {
        return loadList(prefs, key, new Converter<String, String>() {

            @Override
            public String convert(String input) {
                return input;
            }
        });
    }
    
    public interface Converter<I, O> {
        O convert(I input);
    }
}
