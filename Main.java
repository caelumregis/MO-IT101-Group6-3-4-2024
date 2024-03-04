import java.time.Duration;
import java.time.LocalTime;

public class Main {
    public static void main(String[] args) {
        // sample or tester for the codes
        double salary = 25000.00;
        double taxableIncome = grossSalaryDeduction(salary);
        double withHoldingTax = incomeTaxDeduction(taxableIncome);

        // hours worked sample or tester
        LocalTime startTime = LocalTime.of(8, 0);
        LocalTime endTime = LocalTime.of(17, 30);

        double hoursWorked = calculateHoursWorked(startTime, endTime);
        System.out.println("Hours worked: " + hoursWorked);

        // salary based on hours worked sample/tester code

        double hourlyRate = 250.00;

        double totalRateBasedOnHours = salaryBasedOnHoursWorked(hourlyRate, hoursWorked);

        System.out.println("Rate: " + hourlyRate + " | Hours Duration: " + hoursWorked + " | Total: " + totalRateBasedOnHours);


        System.out.println("Salary: " + salary + " Pagibig deduction: " + pagIbigDeduction(salary));
        System.out.println("Salary: " + salary + " Pagibig deduction: " + philHealthDeduction(salary));
        System.out.println("Salary: " + salary + " Pagibig deduction: " + sssDeduction(salary));
        System.out.println("Salary: " + salary + " Withholding Tax: " + withHoldingTax);
        System.out.println("Salary: " + salary + " total taxable income: " + taxableIncome);

    }

    // Government Deductions

    public static double pagIbigDeduction(double salary) {

        double deduction = 0;

        if (salary >= 1000 && salary <= 1500) {
            deduction = salary * 0.01;
        } else if (salary > 1500) {
            deduction = Math.min(salary * 0.02, 100); // maximum contribution amt is 100
        }
        return deduction;
    }

    public static double philHealthDeduction(double salary) {

        double deduction = 0;

        if ((salary == 10000) || (salary >= 10000.01 && salary <= 59999.99) || (salary == 60000)) {
            deduction = (salary * 0.03) / 2;
        }
        return deduction;
    }

    public static double sssDeduction(double salary) {
        double deduction = 0.0;

        if (salary < 3250) {
            deduction = Math.min(salary * 0.01, 135.00);
        }
        else if (salary <= 24750) {
            int range = (int)((salary - 3250) / 500);
            double baseContri = 157.50;
            double contriIncrement = 22.50;
            deduction = baseContri + range * contriIncrement;
        }
        else {
            deduction = 1125.00;
        }
        return deduction;
    }

    public static double grossSalaryDeduction(double salary) {
        double deductionTotal = pagIbigDeduction(salary) + philHealthDeduction(salary) + sssDeduction(salary);
        return salary - deductionTotal;
    }

    // as of 2023 this is the updated withholding tax computation
    public static double incomeTaxDeduction(double salary) {

        double deduction = 0.00;

        if (salary < 20833.33) {
            deduction = salary * 0;
        } else if (salary > 20833.33 && salary < 33333.33) {
            deduction = (salary - 20833.33) * 0.15;
        } else if (salary > 33333.33 && salary < 66666.66) {
            deduction = (salary - 33333.33) * 0.20 + 22500;
        } else if (salary > 66666.66 && salary < 166666.66 ) {
            deduction = (salary - 66666.66) * 0.25 + 102500;
        } else if (salary > 166666.66 && salary < 666666.66) {
            deduction = (salary - 166666.66) * 0.32 + 490000;
        } else {
            deduction = (salary - 666666.66) * 0.35 + 2202500;
        }
        return deduction;
    }

    // hours worked codes

    public static double calculateHoursWorked(LocalTime startTime, LocalTime endTime) {

        Duration duration = Duration.between(startTime, endTime);
        long secondsWorked = duration.getSeconds();
        double hoursWorked = secondsWorked / 3600.0; // Seconds per hour
        return hoursWorked;
    }

    // salary based on hours worked code

    public static double salaryBasedOnHoursWorked(double rate, double hoursWorked) {
        double salary = rate * hoursWorked;
        return salary;
    }
}
