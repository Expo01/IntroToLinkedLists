import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> placesToVisit = new LinkedList<String>();
        placesToVisit.add("Sydney");
        placesToVisit.add("Melbourne");
        placesToVisit.add("Brisbane");
        placesToVisit.add("Perth");
        placesToVisit.add("Canberra");
        placesToVisit.add("Adelaide");
        placesToVisit.add("Darwin");

//        printList(placesToVisit);

//        placesToVisit.add(1, "Alice Springs"); //add as specified index
//        printList(placesToVisit);
//
//        placesToVisit.remove(4);
//        printList(placesToVisit);

        visit(placesToVisit);

    }

    private static void printList(LinkedList<String> linkedList) {
        Iterator<String> i = linkedList.iterator(); //iterator similar to for loop which will print all indexes of the
        //linked list. as we can see, '.iterator' is a method built into the LinkedList class.
        while (i.hasNext()) {
            System.out.println("Now visiting " + i.next()); // '.next' when not at index 0 yet will just start it at index 0
        }
        System.out.println("=========================");
    }

    private static boolean addInOrder(LinkedList<String> linkedList, String newCity) {
        ListIterator<String> stringListIterator = linkedList.listIterator();  //listIterator vs iterator has the advantage of being
        //able to go forward and backward for insertion of new values

        while (stringListIterator.hasNext()) {
            int comparison = stringListIterator.next().compareTo(newCity); // .compareTo tests a string (string 2) against
            //another string (string 1) which is located by stringListIterator.next(). runs through all the unicode values
            // at each place in the string where 0 means characters are the same, '1' means character of string 2 is
            // alphabetically before string 1. '-1' means string 1 is alphabetically before string 2.
            //i imagine that with words that begin the same, the internal comparison would be like this
            // string1 = cat. string2 = cow. first place 'c' tested and '0' found, so next unicode place tested which is
            // 'a' in cat and 'o' in cow. 'o' comes after 'a', the compareTo loop exits and returns a value of '-1'
            //indicating cat comes alphabetically before cow
            if (comparison == 0) {
                System.out.println(newCity + " is already included as a destination");
                return false;
            } else if (comparison > 0) {
                stringListIterator.previous(); // '.next' method above takes us to next position, so need to go back one
                //position to input string alphabetically
                stringListIterator.add(newCity);
                return true;
            } else if (comparison < 0) {
                // move on next city
            }
        }

        stringListIterator.add(newCity);
        return true;

        // boolean return for this method just returns whether something was added or not
    }

    private static void visit(LinkedList cities) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = false;
        boolean goingForward = true; //necessary otherwise the listIterator will loop and repeat certain outputs.
        //to my understanding, lets say there are 3 items in the LinkedList. The Iterator starts before index 0. If we want
        //to navigate to the next item in the list we check if it exists with '.hasNext'. When we print the next item
        //using '.next' the Iterator then prints the next item (index 0) but then hovers BETWEEN index 0 and index 1.
        //If we go forward two times, we have then printed index 0 and index 1 and the Iterator is hovering between index 1
        //and index 2. If we want to print the previous item in the LinkedList, and use '.hasPrevious' then '.previous'
        //this actually just re-prints index 1 and the Iterator is now hovering between index 0 and 1.
        //the result is that we wanted to print index 0, then index 1, then go back and print index 0 again, but instead
        //it will print index 0 then 1, then 1 again and then if we run '.hasPrevious' '.previous' THEN the Iterator will
        // go from hovering between index 1 and 0 to hovering before index 0. The boolean is present to indicate which direction
        // the Iterator came from to avoid incorrect redundancy of an item.
        ListIterator<String> listIterator = cities.listIterator();

        if(cities.isEmpty()) {
            System.out.println("No cities in the itinerary");
            return;
        } else {
            System.out.println("Now visiting " + listIterator.next()); // prints value of index 0 before going into
            //menu for user input for navigating through the rest of the LinkedList
            printMenu();
        }

        while (!quit) {
            int action = scanner.nextInt();
            scanner.nextLine();
            switch(action) {
                case 0:
                    System.out.println("Holiday (Vacation) over");
                    quit = true;
                    break;

                case 1:
                    if(!goingForward) { //relates the 'goingForward' boolean variable
                        if(listIterator.hasNext()) {
                            listIterator.next();
                        }
                        goingForward = true;
                    }
                    if(listIterator.hasNext()) { // operates under condition that we are/have come from a forward direction
                        //in the LinkedList
                        System.out.println("Now visiting " + listIterator.next());
                    } else {
                        System.out.println("Reached the end of the list"); //now iterator hovering after final index
                    }
                    break;

                case 2:
                    if(goingForward) { //this is like a double jump where we have flagged that we were in a forward
                        //moving direction, but we want to go backwards, so we need to jump and hover 2 index back
                        if(listIterator.hasPrevious()) { //checks if there is a previous and then goes there WITHOUT
                            //printing any content. navigation only.
                            listIterator.previous();
                        }
                        goingForward = false; //set to false as we have now shifted direction
                    }
                    if(listIterator.hasPrevious()) { //same as above but now goingForward condition is false so we jump
                        //and hover back one index and now DO print the content since we navigated past redundancy
                        System.out.println("Now visiting " + listIterator.previous());
                    } else {
                        System.out.println("We are at the start of the list"); //now Iterator hovering before index 0
                    }
                    break;

                case 3:
                    printMenu();
                    break;

            }

        }
    }

    private static void printMenu() {
        System.out.println("Available actions:\npress ");
        System.out.println("0 - to quit\n" +
                "1 - go to next city\n" +
                "2 - go to previous city\n" +
                "3 - print menu options");
    }
}
