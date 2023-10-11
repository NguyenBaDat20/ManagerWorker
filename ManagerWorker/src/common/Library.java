/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package common;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Scanner;
import model.History;
import model.Worker;

/**
 *
 * @author PC
 */
public class Library {

    Scanner sc = new Scanner(System.in);

    public String getValue(String msg) {
        System.out.print(msg);
        return sc.nextLine();
    }

    public int getIntForMenu(String msg, int min, int max) {
        int a = -1;
        while (true) {
            System.out.print(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
                if (a >= min && a <= max) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("Please enter a number between " + min + " and " + max);
            }
        }
        return a;
    }

    public int getInteger(String msg) {
        int a = -1;
        while (true) {
            System.out.println(msg);
            try {
                a = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Only input number");
                continue;
            }
            break;
        }
        return a;
    }

    public void addWorker(ArrayList<Worker> lw) {
        while (true) {
            System.out.print("Enter code: ");
            String id = Validate.checkInputString();
            if (Validate.checkIdExist(lw, id)) {
                System.err.println("Code(id) is existed in DB.");
                continue;
            }
            System.out.print("Enter name: ");
            String name = Validate.checkInputString();
            System.out.print("Enter age: ");
            int age = Validate.checkInputIntLimit(18, 50);
            System.out.print("Enter salary: ");
            int salary = Validate.checkInputSalary();
            System.out.print("Enter work location: ");
            String workLocation = Validate.checkInputString();
            if (!Validate.checkWorkerExist(lw, id, name, age, salary, workLocation)) {
                System.err.println("Duplicate.");
            } else {
                lw.add(new Worker(id, name, age, salary, workLocation));
                System.err.println("Add success.");
                break;
            }
        }
    }

    public void changeSalary(ArrayList<Worker> lw, ArrayList<History> lh, int status) {
        if (lw.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.print("Enter code: ");
        String id = Validate.checkInputString();
        Worker worker = getWorkerByCode(lw, id);
        if (worker == null) {
            System.err.println("Not exist worker.");
        } else {
            int salaryCurrent = worker.getSalary();
            int salaryUpdate;

            if (status == 1) {
                System.out.print("Enter salary: ");

                while (true) {
                    salaryUpdate = Validate.checkInputSalary();

                    if (salaryUpdate <= salaryCurrent) {
                        System.err.println("Must be greater than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("UP", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            } else {
                System.out.print("Enter salary: ");

                while (true) {
                    salaryUpdate = Validate.checkInputSalary();

                    if (salaryUpdate >= salaryCurrent) {
                        System.err.println("Must be smaller than current salary.");
                        System.out.print("Enter again: ");
                    } else {
                        break;
                    }
                }
                lh.add(new History("DOWN", getCurrentDate(), worker.getId(),
                        worker.getName(), worker.getAge(), salaryUpdate,
                        worker.getWorkLocation()));
            }
            worker.setSalary(salaryUpdate);
            System.err.println("Update success");
        }
    }

    public void printListHistory(ArrayList<History> lh) {
        if (lh.isEmpty()) {
            System.err.println("List empty.");
            return;
        }
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age",
                "Salary", "Status", "Date");
        Collections.sort(lh);

        for (History history : lh) {
            printHistory(history);
        }
    }

    public Worker getWorkerByCode(ArrayList<Worker> lw, String id) {
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public void printHistory(History history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(),
                history.getName(), history.getAge(), history.getSalary(),
                history.getStatus(), history.getDate());
    }
}
