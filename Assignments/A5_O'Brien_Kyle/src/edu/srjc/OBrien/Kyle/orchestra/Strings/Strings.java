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

package edu.srjc.OBrien.Kyle.orchestra.Strings;

import edu.srjc.OBrien.Kyle.orchestra.Enums.Clef;
import edu.srjc.OBrien.Kyle.orchestra.Enums.Key;
import edu.srjc.OBrien.Kyle.orchestra.Instrument;

import java.util.ArrayList;

public abstract class Strings extends Instrument
{
    private int numberOfStrings = 0;

    public Strings()
    {

    }

    public Strings(String name, String description, ArrayList<Clef> clefs, ArrayList<Key> keys, int numberOfStrings)
    {
        super(name, description, clefs, keys);
        this.numberOfStrings = numberOfStrings;
    }

    public int getNumberOfStrings()
    {
        return numberOfStrings;
    }

    public void setNumberOfStrings(int numberOfStrings)
    {
        this.numberOfStrings = numberOfStrings;
    }

}
