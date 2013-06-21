/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.navisu.nmea.devices.controller.parser.impl;

import org.navisu.nmea.controller.parser.impl.NMEALexer;
import org.navisu.nmea.controller.parser.impl.NMEAParser;
import org.navisu.nmea.devices.controller.handler.impl.NMEAEventHandler;
import org.navisu.nmea.devices.controller.parser.services.NMEAFileParserService;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Serge Morvan
 */
@ServiceProvider(service = NMEAFileParserService.class)
public class NMEAFileParser
        implements NMEAFileParserService {

    private NMEAParser parser;
    private ANTLRInputStream input = null;

    @Override
    public void parse(String fileName) {
        try {
            input = new ANTLRInputStream(new FileInputStream(fileName));
        } catch (IOException ex) {
            Logger.getLogger(NMEAFileParser.class.getName()).log(Level.SEVERE, null, ex);
        }
        init();
    }

    protected void init() {
        NMEALexer lexer = new NMEALexer(input);
        NMEAEventHandler handler = new NMEAEventHandler();
        lexer.setHandler(handler);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        parser = new NMEAParser(tokens);
        ParserSwingWorker swingWorker = new ParserSwingWorker();
        swingWorker.execute();
    }

    class ParserSwingWorker
            extends SwingWorker<Integer, String> {

        @Override
        protected Integer doInBackground() throws Exception {
            parser.entry();
            return 0;
        }
    }
}
