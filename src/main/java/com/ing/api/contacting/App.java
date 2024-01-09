package com.ing.api.contacting;

import picocli.CommandLine;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        int exitCode = new CommandLine(new Processor()).execute(args);
        System.exit(exitCode);
    }
}
