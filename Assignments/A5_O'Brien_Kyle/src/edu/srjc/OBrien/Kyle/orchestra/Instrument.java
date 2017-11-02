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

package edu.srjc.OBrien.Kyle.orchestra;

import edu.srjc.OBrien.Kyle.orchestra.Enums.*;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class Instrument
{

    private String name = "";
    private String description = "";
    private ArrayList<Key> keys = new ArrayList<Key>();
    private ArrayList<Clef> clefs = new ArrayList<Clef>();

    public Instrument()
    {

    }

    public Instrument(String name, String description, ArrayList<Clef> clefs, ArrayList<Key> keys)
    {
        this.name = name;
        this.description = description;
        this.clefs = clefs;
        this.keys = keys;
    }

    public abstract void play();

    public String describe()
    {
        String Description = "Instrument: The " + this.getClass().getSimpleName() + "\n\n" + getDescription() + "\n";
        String commonKeys = "It's most common key(s) are " + Arrays.deepToString(keys.toArray()) + "\n";
        return Description + commonKeys;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public ArrayList<Key> keys()
    {
        return keys;
    }

    public ArrayList<Clef> clefs()
    {
        return clefs;
    }

}
