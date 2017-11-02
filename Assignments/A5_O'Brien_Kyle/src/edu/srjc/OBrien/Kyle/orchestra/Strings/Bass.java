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

import java.util.ArrayList;

public class Bass extends Strings
{

    public Bass()
    {
        this.setName("The " + this.getClass().getSimpleName());
        this.setNumberOfStrings(4);
        this.keys().add(Key.Eflat);
        this.clefs().add(Clef.Bass);
        this.clefs().add(Clef.Tenor);
        this.clefs().add(Clef.Treble);

        String bassDesc = "Bass (/ˈbeɪs/ BAYSS) describes musical instruments that produce tones in \n" +
                "the low-pitched range C4- C2. They belong to different families of instruments \n" +
                "and can cover a wide range of musical roles. Since producing low pitches usually \n" +
                "requires a long air column or string, the string and wind bass instruments are \n" +
                "usually the largest instruments in their families or instrument classes. \n\nIt has "
                + getNumberOfStrings() + " strings\n";

        this.setDescription(bassDesc);
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
