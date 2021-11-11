package StructuralPatterns.Adapter;

import java.util.ArrayList;
import java.util.List;

import Introduction.Engine;
import Introduction.StandardEngine;
import Introduction.TurboEngine;

public class AbstractTester {
    public static void main(String args[]) {
        List<Engine> engines = new ArrayList<Engine>();
        engines.add(new StandardEngine(1300));
        engines.add(new StandardEngine(1600));
        engines.add(new TurboEngine(2000));

        for (Engine e : engines) {
            System.out.println(e);
        } // endae ENHANCED FOR
    } // endae MAIN
} // endae CLASS
