package com.Projects;

//This program takes input of a date and validates by comparing it to regular expressions
import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        String userInput;
        String result;

        //^ and $ are anchors for start and end of the expression
        //^abcde$ must start with a and end with e
        //FOR YEAR -- (19|20)\d\d == 19 or 20 plus any digit any digit
        //FOR DAY -- (0[1-9]) == 01 - 09   || [12][0-9] == 1 0r 2 and 0 - 9
        //FOR MONTH -- (0[1-9]|| 1[0-2]) == 01 - 09 or 10 - 12
        String pattern = "^(0?[1-9]|[12][0-9]|3[0-1])/(0?[0-9]||1[0-2])/(19|20)\\d\\d$";

        userInput = JOptionPane.showInputDialog(null, "Enter a date: ");

        if(userInput.matches(pattern)) {
            //split into days, months and year
            //create an array and use split() to put up to / in each element
            String[] parts = userInput.split("/");

            //put each element into new variables
            int days = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            String monthString = parts[1];//this is the month segment of the string for later validation
            int year = Integer.parseInt(parts[2]);

            boolean flag = true;
            //check if date is february 29
            if (month == 2) {
                //if true, check if it's a leap year
                if (days == 29) {
                    flag = isLeapYear(year);//method returns true or false and sets flag accordingly
                } else if (days > 29) {//if the date is greater than 29
                    flag = false;
                }
            }
            //compare month and date given as input to ensure a month with 30 days does not have 31
            //check if it's either 04/06/09/11 which all have 30 days
            //if the month matches regex and date is 31 flag is set to false
            else if (monthString.matches("(0[469]|1[1])") && days == 31) {
                flag = false;
            }

            //final verification to check if the date complies to dd/mm/yyyy and the dates are correct per month
            if (flag) {
                result = "Valid date format";
            }else{
                result = "Invalid date format";
            }
        }
        else{
            result = "Invalid date format";
        }
        JOptionPane.showConfirmDialog(null, result);

    }




    //This method takes an int as input parameter and checks if the
    //number corresponds with a leap year
    public static boolean isLeapYear(int year){
        if(year < 1 || year > 9999){
            return false;
        }
        if(year % 4 != 0 ){//if is not divisible by 4 it's false
            return false;
        }
        else if(year % 400 == 0){//if it's divisible by 400 it must be a leap year
            return true;
        }
        else if(year % 100 == 0){//if it's divisible by 100 it must be a millennium year
            return false;   //millennium years are only divisible by 400
        }
        else{
            return true;// otherwise it must be divisible by 4 and a leap year
        }
    }
}

