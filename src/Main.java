import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Customer customer = new Customer("Tim", 54.96);
        Customer anotherCustomer;
        anotherCustomer = customer;
        anotherCustomer.setBalance(12.18);
        System.out.println("Balance for customer " + customer.getName() + " is " + customer.getBalance());
        //demonstrates some memory location without 'new' keyword

        ArrayList<Integer> intList = new ArrayList<Integer>();

        intList.add(1);
        intList.add(3);
        intList.add(4);

        for(int i=0; i<intList.size(); i++) {
            System.out.println(i +": " + intList.get(i));
        }

        intList.add(1, 2); // adds value 2 to index 1 but does not delete current index 1 value which is 3
        // what an ArrayList has to do is reassign the value of all subsequent indexes. This is demonstrated to
        //help exemplify the benefit of a LinkedList which uses one object to point to the next object so that an
        //insertion of a new object in the middle of the list somewhere only changes what the preceding object points to
        //and now the newly added object will point to the following object previously pointed to by the preceding object

        for(int i=0; i<intList.size(); i++) {
            System.out.println(i +": " + intList.get(i));
        }
    }
}