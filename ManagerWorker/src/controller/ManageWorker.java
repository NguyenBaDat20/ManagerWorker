/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import common.Library;
import java.util.ArrayList;
import model.History;
import model.Worker;
import view.Menu;

/**
 *
 * @author PC
 */
public class ManageWorker extends Menu<String>{
    Library library = new Library();
    ArrayList<Worker> workerList = new ArrayList<>();
    ArrayList<History> listHistory = new ArrayList<>();
    static String[] mc = {"Add Worker", "Up Salary", "Down Salary", "Display Information Salary", "Exit"};
    public ManageWorker(){
        super("Worker Management", mc);
    }
    @Override
    public void executed(int n) {
        switch (n) {
            case 1:
                library.addWorker(workerList);
                break;
            case 2:
                library.changeSalary(workerList, listHistory, 1);
                break;
            case 3:
                library.changeSalary(workerList, listHistory, 2);
                break;
            case 4:
                library.printListHistory(listHistory);
                break;
            case 5:
                System.out.println("Exit program");
                System.exit(0);
            default:
                throw new AssertionError();
        }
    }
    
}
