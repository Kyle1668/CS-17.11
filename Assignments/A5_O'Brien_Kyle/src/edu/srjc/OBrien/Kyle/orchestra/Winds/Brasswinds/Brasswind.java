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

package edu.srjc.OBrien.Kyle.orchestra.Winds.Brasswinds;

import edu.srjc.OBrien.Kyle.orchestra.Instrument;
import edu.srjc.OBrien.Kyle.orchestra.Winds.Winds;

public abstract class Brasswind extends Winds
{
    private int valveCount = 0;

    public int getValveCount()
    {
        return valveCount;
    }

    public void setValveCount(int valveCount)
    {
        this.valveCount = valveCount;
    }

}
