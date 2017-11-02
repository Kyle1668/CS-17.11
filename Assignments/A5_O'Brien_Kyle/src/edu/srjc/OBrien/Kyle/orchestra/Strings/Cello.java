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

public class Cello extends Strings
{

    public Cello()
    {
        this.setName("The " + this.getClass().getSimpleName());
        this.setNumberOfStrings(4);
        this.keys().add(Key.C);
        this.clefs().add(Clef.Bass);
        this.clefs().add(Clef.Tenor);
        this.clefs().add(Clef.Treble);

        String celloDesc = "The cello or violoncello is a bowed, and sometimes plucked, \n" +
                "string instrument with four strings tuned in perfect fifths. The strings from low to \n" +
                "high are generally tuned to C₂, G₂, D₃ and A₃, an octave lower than the viola. " +
                "\n\nIt has " + getNumberOfStrings() + " strings\n";

        this.setDescription(celloDesc);
    }

    @Override
    public void play()
    {
        for (Key theKey : this.keys())
        {
            String name = this.getClass().getSimpleName();
            System.out.println("The " + theKey + " " + name + " sounds concert "
                    + theKey + " when " + "the musician plays a written C with this instrument");
        }
    }

    @Override
    public String describe()
    {
        return super.describe();
    }

}
