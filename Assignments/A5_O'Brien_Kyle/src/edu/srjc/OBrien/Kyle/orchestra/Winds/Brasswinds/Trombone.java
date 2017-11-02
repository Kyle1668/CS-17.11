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

import edu.srjc.OBrien.Kyle.orchestra.Enums.Clef;
import edu.srjc.OBrien.Kyle.orchestra.Enums.Key;

public class Trombone extends Brasswind
{

    public Trombone()
    {

        this.setName("The " + this.getClass().getSimpleName());
        this.setTransposing(false);
        this.setValveCount(2);

        this.keys().add(Key.Bflat);
        this.keys().add(Key.Eflat);

        this.clefs().add(Clef.Bass);
        this.clefs().add(Clef.Tenor);

        String transposingDesc = isTransposing() ? " It transposes from C " : " It does not transpose from C. ";

        String tromboneDesc = "The trombone is a musical instrument in the brass family. Like all brass \n" +
                "instruments, sound is produced when the player's vibrating lips cause the air column \n" +
                "inside the instrument to vibrate\n" +
                "\nIt has " + getValveCount() + " valves." + transposingDesc + "\n";

        this.setDescription(tromboneDesc);
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
