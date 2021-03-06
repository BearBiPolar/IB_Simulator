import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.IOException;

import javax.swing.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * The Game Engine.
 *
 * @author William Wang
 * @version 2018-12-14 1.0
 */
public class Engine
{
    // instance variables - replace the example below with your own
    private Money money;
    private Time time;
    private Happiness happiness;
    private Health health;
    private Grades grades;
    private Productivity productivity;

    /**
     * Constructor for objects of class Engine
     */
    public Engine()
    {
        // initialise instance variables
        money = new Money();
        time = new Time();
        happiness = new Happiness();
        health = new Health();
        grades = new Grades();
        productivity = new Productivity();
    }

    public void action(String string)
    {
        boolean isDone= false;
        // analyze which action it is
        switch(string)
        {
            // if the action is sleep, work, study etc and its effects
            case "sleep":
            time.addTime(480);
            happiness.addHappiness(10);
            health.addHealth(10);
            break;
            case "study":
            time.addTime(60);
            happiness.addHappiness(-10);
            health.addHealth(-2);
            grades.addGrades(1);
            break;
            case "work":
            time.addTime(120);
            money.addMoney(30);
            happiness.addHappiness(-15);
            health.addHealth(-10);
            break;
            case "game":
            time.addTime(60);
            happiness.addHappiness(15);
            health.addHealth(-5);
            break;
            case "exercise":
            time.addTime(60);
            happiness.addHappiness(5);
            health.addHealth(10);
            case "eat":
            time.addTime(60);
            money.addMoney(-10);
            happiness.addHappiness(10);
            health.addHealth(10);
            

            break;
            // if user would like to exit
            case "exit":
            isDone = true;
            break;
            // if the action is not included
            default:
            System.out.println("Sorry, this option is not available.");
            action();
            break;
        }
        // change productivity rate to match new happiness and health levels
        productivity.changeProductivity(happiness.returnHappiness(), health.returnHealth());

    }

    public void newDayEvent(JFrame frame)
    {
        money.addMoney(-30);
        happiness.addHappiness(-10);
        health.addHealth(-10);

        int caseNumber = 3;
        int last = 0;
        int randomNum;
        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        do
        {
            randomNum = ThreadLocalRandom.current().nextInt(1, caseNumber + 1);
        }
        while(last == randomNum);

        last = randomNum;
        //event
        switch(randomNum)
        {

            case 1:
            //Custom button text
            Object[] options = {"Yes, please",
                    "No, thanks",
                    "No eggs, no ham!"};
            int n = JOptionPane.showOptionDialog(frame,
                    "Would you like some green eggs to go "
                    + "with that ham?",
                    "A Silly Question",
                    JOptionPane.YES_NO_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    options,
                    options[2]);

            break;
            case 2:
            //Custom button text
            options = new Object[]{"Yes, please",
                "No, thanks",
                "Ignore them!"};
            n = JOptionPane.showOptionDialog(frame,
                "Your friends invite you to go out"
            ,
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
            break;
            case 3:
            //Custom button text
            options = new Object[]{"Yes, please",
                "No, thanks",
                "Ignore them!"};
            n = JOptionPane.showOptionDialog(frame,
                "Your buddies ask you to go work out"
            ,
                "A Silly Question",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[2]);
            break;
        }
    }

    public void action()
    {
        Boolean isDone = false;
        String exitCondition = "exit";
        // create new buffered reader
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try
        {
            // read the action
            string = in.readLine().trim();
            in.close();
        }
        catch(IOException exception)
        {

        }
        // analyze which action it is
        switch(string)
        {
            // if the action is sleep, work, study etc and its effects
            case "sleep":
            time.addTime(480);
            happiness.addHappiness(10);
            health.addHealth(10);
            break;
            case "study":
            time.addTime(60);
            happiness.addHappiness(-10);
            health.addHealth(-2);
            grades.addGrades(1);
            break;
            case "work":
            time.addTime(120);
            money.addMoney(30);
            happiness.addHappiness(-15);
            health.addHealth(-10);
            break;
            case "game":
            time.addTime(60);
            happiness.addHappiness(15);
            health.addHealth(-5);
            break;
            case "exercise":
            time.addTime(60);
            happiness.addHappiness(5);
            health.addHealth(10);

            break;
            // if user would like to exit
            case "exit":
            isDone = true;
            break;
            // if the action is not included
            default:
            System.out.println("Sorry, this option is not available.");
            action();
            break;
        }
        // change productivity rate to match new happiness and health levels
        productivity.changeProductivity(happiness.returnHappiness(), health.returnHealth());
        // if user is not done
        if(!isDone)
        {
            // return the new values
            System.out.println(returnValues());
            // continue to accept actions
            action();
        }
        else
        {
            // return the new values
            System.out.println(returnValues());
        }
    }

    public boolean isNewDay()
    {
        return time.isNewDay();
    }

    /**
     *
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the values
     */
    public String returnValues()
    {
        return "Time: " + time.returnTime() + ", Money: $" + money.returnBalance() + ", Happiness: " + happiness.returnHappiness() + "%, Health: " + health.returnHealth() + "%, Grades: " + grades.returnGrades() + "%, Productivity " + productivity.returnProductivity() + "%, ";
    }

    public String moneyValue()
    {
        return Integer.toString(money.returnBalance());
    }

    public String timeValue()
    {
        return time.returnTime();
    }

    public String healthValue()
    {
        return Integer.toString(health.returnHealth());
    }

    public String happinessValue()
    {
        return Integer.toString(happiness.returnHappiness());
    }

    public String gradesValue()
    {
        return Integer.toString(grades.returnGrades());
    }

    public String productivityValue()
    {
        return Integer.toString(productivity.returnProductivity());
    }
}
