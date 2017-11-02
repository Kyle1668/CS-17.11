// Kyle O'Brien
// 11-24-17
// CS 17.11
// Assignment 5 - Orchestra

// File Description: This program implements an orchestra system using abstract classes,
// enumeration types, and inheritance. All instruments inherit from a parent Instrument
// class. From there, instruments are split into winds and strings. Winds is further
// segregated into continuing categories until each instrument is fully categorized.

// Once all the instruments have been added, the orchestra is iterated through,
// and information about each instrument is printed to the console.
// This included common keys, pitches. For string instruments,
// the number of strings is printed. For wind instruments,
// whether they transpose from C is shown along with
// other instrument specific information.

// Resources Cited
// http://mmallory.fcsd.wnyric.org/documents/concertpitchchart.pdf
// https://www.talkbass.com/threads/jazz-keys.583750/
// https://en.wikipedia.org/wiki/Cello
// https://music.stackexchange.com/questions/17046/why-is-music-for-strings-more-likely-to-be-in-keys-with-sharps
// http://www.viola.com/
// https://en.wikipedia.org/wiki/Viola
// www.sharmusic.com/Instruments/Violin/
// https://en.wikipedia.org/wiki/Violin
// https://www.youtube.com/watch?v=zgaQFLUdUL0
// https://en.wikipedia.org/wiki/Horn_(instrument)
// https://en.wikipedia.org/wiki/Brass_instrument
// https://en.wikipedia.org/wiki/Trombone
// https://en.wikipedia.org/wiki/Trumpet
// https://en.wikipedia.org/wiki/Tuba)
// https://en.wikipedia.org/wiki/Alto_saxophone
// https://en.wikipedia.org/wiki/baritone_saxophone
// https://en.wikipedia.org/wiki/soprano_saxophone
// https://en.wikipedia.org/wiki/soprano_saxophone
// https://en.wikipedia.org/wiki/https://en.wikipedia.org/wiki/Bassoon
// https://en.wikipedia.org/wiki/https://en.wikipedia.org/wiki/Clarinet
// https://en.wikipedia.org/wiki/https://en.wikipedia.org/wiki/Flute
// https://en.wikipedia.org/wiki/https://en.wikipedia.org/wiki/Oboe
// https://en.wikipedia.org/wiki/https://en.wikipedia.org/wiki/Piccolo

package edu.srjc.OBrien.Kyle.orchestra;

import java.util.ArrayList;

import edu.srjc.OBrien.Kyle.orchestra.Strings.*;
import edu.srjc.OBrien.Kyle.orchestra.Winds.Brasswinds.*;
import edu.srjc.OBrien.Kyle.orchestra.Winds.Woodwinds.Saxophones.*;
import edu.srjc.OBrien.Kyle.orchestra.Winds.Woodwinds.*;

public class Main
{

    public static void main(String[] args)
    {

        ArrayList<Instrument> theOrchestra = new ArrayList<> ();

        // Strings Section
        theOrchestra.add(new Bass());
        theOrchestra.add(new Cello());
        theOrchestra.add(new Viola());
        theOrchestra.add(new Violin());

        // Brasswind Section
        theOrchestra.add(new Horn());
        theOrchestra.add(new Tuba());
        theOrchestra.add(new Trumpet());
        theOrchestra.add(new Tuba());

        // Woodwind Section - Saxophones
        theOrchestra.add(new Alto());
        theOrchestra.add(new Baritone());
        theOrchestra.add(new Soprano());
        theOrchestra.add(new Tenor());

        // Woodwind Section
        theOrchestra.add(new Bassoon());
        theOrchestra.add(new Clarinet());
        theOrchestra.add(new Flute());
        theOrchestra.add(new Oboe());
        theOrchestra.add(new Piccolo());

        printOrchestra(theOrchestra);

    }

    private static void generateLine()
    {
        for (int i = 0; i < 100; i++)
        {
            System.out.print("-");
        }
        System.out.println("\n");
    }

    private static void printOrchestra(ArrayList<Instrument> theOrchestra)
    {
        for (Instrument instrument : theOrchestra)
        {
            generateLine();
            System.out.println(instrument.describe());
            instrument.play();
            System.out.println();
            generateLine();
        }
    }

}
