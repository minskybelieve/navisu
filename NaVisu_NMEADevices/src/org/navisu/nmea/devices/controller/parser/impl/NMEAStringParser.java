/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.parser.impl;

import org.navisu.nmea.controller.parser.impl.NMEALexer;
import org.navisu.nmea.controller.parser.impl.NMEAParser;
import org.navisu.nmea.devices.controller.handler.impl.NMEAEventHandler;
import org.navisu.nmea.devices.controller.parser.services.NMEAStringParserService;
import javax.swing.SwingWorker;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = NMEAStringParserService.class)
public class NMEAStringParser
        implements NMEAStringParserService {

    private NMEAParser parser;
    private NMEALexer lexer;
    private ANTLRStringStream input;
    private NMEAEventHandler handler;
    private CommonTokenStream tokens;
    private ParserSwingWorker swingWorker;
   // private String source;

    public NMEAStringParser() {
        handler = new NMEAEventHandler();
    }

    @Override
    public void parse(String source) {
        input = new ANTLRStringStream(source);
        swingWorker = new ParserSwingWorker(); 
        swingWorker.execute();
    }

    class ParserSwingWorker
            extends SwingWorker<Integer, String> {

        @Override
        protected Integer doInBackground() throws Exception {
            lexer = new NMEALexer(input);
            lexer.setHandler(handler);
            tokens = new CommonTokenStream(lexer);
            parser = new NMEAParser(tokens);
            parser.entry();         
            return 0;
        }
    }
}
