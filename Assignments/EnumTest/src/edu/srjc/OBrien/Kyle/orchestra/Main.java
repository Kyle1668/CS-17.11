package edu.srjc.OBrien.Kyle.orchestra;

public class Main {

    static private class Student {
        private String name = "";
        private ClassLevel level;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public ClassLevel getLevel()
        {
            return level;
        }

        public void setLevel(ClassLevel level)
        {
            this.level = level;
        }

        @Override
        public String toString()
        {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", level=" + level +
                    '}';
        }
    }

    private enum ClassLevel {
        Freshman, Sophmore, Juior, Senior
    }

    public static void main(String[] args) {

        Student kyle = new Student();

        kyle.setName("Kyle O'Brien");
        kyle.setLevel(ClassLevel.Juior);

        System.out.println(kyle.toString());

    }
}
