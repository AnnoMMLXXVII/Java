package DecisionStructures;

// design a class that stores the names of three runners and their times in minutes for each to finish the race
// class should have methods that return the nam eof the runner in the first, second, and third;

// Singleton Enumberation desgin
public class RunningTheRace {

    private enum getINSTANCE {
        INSTANCE;

        private String name1, name2, name3;
        private int mins1, mins2, mins3;

        private void mainRun() {
            compareTimes(mins1, mins2, mins3);
            System.out.println(toString());
        } // enade mainRun

        private void setTimes(int mins1, int mins2, int mins3) {
            this.mins1 = mins1;
            this.mins2 = mins2;
            this.mins3 = mins3;
        } // endae mutator setTimes

        private void setNames(String name1, String name2, String name3) {
            this.name1 = name1;
            this.name2 = name2;
            this.name3 = name3;
        } // endae Mutator getName();


        private void setFirst(String name1) {
            this.name1 = name1;
        }

        private void setSecond(String name2) {
            this.name2 = name2;
        }

        private void setThird(String name3) {
            this.name3 = name3;
        }

        private String getName1() {
            return name1;
        } // endae Accessor getName1

        private String getName2() {
            return name2;
        } // endae Accessor getName2

        private String getName3() {
            return name3;
        } // ednae Accessor getName3

        private String getFirst() {
            return getName1();
        } // endae Accessor getName1

        private String getSecond() {
            return getName2();
        } // endae Accessor getName2

        private String getThird() {
            return getName3();
        } // endae Accessor getName3


        @Override
        public String toString() {
            String result = "First: " + getFirst() + " -- Time: " + mins1 + "\n" +
                    "Second: " + getSecond() + " -- Time: " + mins2 + "\n" +
                    "Third: " + getThird() + " -- Time: " + mins3;
            return result;
        } // endae toString

        private String name;

        private void compareTimes(int a, int b, int c) {
            if (a < b && a < c) {
                name = getName1();
                setFirst(name1);
                if (b < c) {
                    name = getName2();
                    setSecond(name2);
                    name = getName3();
                    setThird(name3);
                } // endae IF
                else {
                    name = getName3();
                    setSecond(name);
                    name = getName2();
                    setThird(name);
                } // endae nested ELSE
            } // ednae IF
            else if (b < a && b < c) {
                name = getName2();
                setFirst(name2);
                if (a < c) {
                    name = getName1();
                    setSecond(name1);
                    name = getName3();
                    setThird(name3);
                }  // endae IF
                else {
                    name = getName3();
                    setSecond(name3);
                    name = getName1();
                    setThird(name1);
                } // enae  nested ELSE
            } // endae ELSE

            else if (c < a && c < b) {
                name = getName3();
                setFirst(name3);
                if (a < b) {
                    name = getName1();
                    setSecond(name1);
                    name = getName2();
                    setThird(name2);
                } // endae nested IF
                else {
                    name = getName2();
                    setSecond(name2);
                    name = getName1();
                    setThird(name1);
                } // ednae nested ELSE
            } // enae ELSE IF
        }


    } // endae ENUMERATION getINSTANCE


    // order : int --> returns the pollPosition


    public static void main(String args[]) {
        RunningTheRace.getINSTANCE.INSTANCE.setNames("Haku", "Elizabeth", "Other");
        RunningTheRace.getINSTANCE.INSTANCE.setTimes(3, 50, 30);
        RunningTheRace.getINSTANCE.INSTANCE.mainRun();


    }


}


