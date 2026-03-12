import java.util.*;

/*
   CHENNAI BUS PASS MANAGEMENT SYSTEM
   Smart Transport Authority Backend
*/

class Route {

    String number;
    String source;
    String destination;
    int distance;

    Route(String n,String s,String d,int dist){
        number=n;
        source=s;
        destination=d;
        distance=dist;
    }

}

class Application {

    String name;
    String phone;
    String aadhaar;
    String studentId;
    String route;
    String busType;
    int duration;
    double fare;
    int priority;

    Application(String n,String p,String a,String sid,String r,String b,int d,double f,int pr){

        name=n;
        phone=p;
        aadhaar=a;
        studentId=sid;
        route=r;
        busType=b;
        duration=d;
        fare=f;
        priority=pr;

    }

}

public class BusPassDSABackend {

    static Scanner sc=new Scanner(System.in); // Scanner used for user input

    static ArrayList<Route> routes=new ArrayList<>(); 
    // LIST ADT using ArrayList (stores all bus routes dynamically)

    static HashMap<String,Route> routeDirectory=new HashMap<>(); 
    // HASHING (HashMap) used for fast searching of routes using route number

    static LinkedList<Application> allApplications=new LinkedList<>(); 
    // LINKED LIST implementation storing all applications

    static Queue<Application> processingQueue=new LinkedList<>(); 
    // QUEUE ADT (FIFO) – applications processed in first come first serve order

    static PriorityQueue<Application> urgentProcessing=
            new PriorityQueue<>((a,b)->b.priority-a.priority); 
    // PRIORITY QUEUE (Heap concept) – urgent applications processed first

    static Deque<Application> fastTrack=new ArrayDeque<>(); 
    // DEQUE (Double Ended Queue) – allows insertion/removal from both ends

    static Stack<Application> processedHistory=new Stack<>(); 
    // STACK ADT (LIFO) – stores history of processed applications


    public static void main(String[] args){

        loadRoutes();

        while(true){

            System.out.println("\n==========================================");
            System.out.println("  CHENNAI BUS PASS MANAGEMENT BACKEND");
            System.out.println("==========================================");

            System.out.println("1. View Bus Routes");
            System.out.println("2. Search Route");
            System.out.println("3. Organize Routes");
            System.out.println("4. Apply Bus Pass");
            System.out.println("5. Process Next Application");
            System.out.println("6. Process Urgent Application");
            System.out.println("7. View Processing History");
            System.out.println("8. View Pending Applications");
            System.out.println("0. Exit");

            int choice=sc.nextInt();

            switch(choice){

                case 1:viewRoutes();break;

                case 2:searchRoute();break;

                case 3:organizeRoutes();break;

                case 4:applyPass();break;

                case 5:processApplication();break;

                case 6:processUrgent();break;

                case 7:viewHistory();break;

                case 8:viewQueue();break;

                case 0:
                    System.out.println("System shutting down...");
                    System.exit(0);

            }

        }

    }


    static void loadRoutes(){

        routes.add(new Route("21G","Tambaram","Broadway",32)); // ArrayList insertion
        routes.add(new Route("47A","T Nagar","Avadi",28));
        routes.add(new Route("29C","Perambur","Sholinganallur",35));
        routes.add(new Route("102","Koyambedu","Adyar",18));
        routes.add(new Route("5E","Parrys","Besant Nagar",14));
        routes.add(new Route("19B","Tambaram","T Nagar",25));
        routes.add(new Route("27H","Guindy","Red Hills",31));
        routes.add(new Route("88K","Adyar","Tambaram",26));

        for(Route r:routes) // LINEAR TRAVERSAL (similar to Linear Search concept)
            routeDirectory.put(r.number,r); 
        // HASHMAP insertion using hash function
    }


    static void viewRoutes(){

        System.out.println("\nAvailable Chennai Bus Routes\n");

        for(Route r:routes){ // Linear traversal through ArrayList

            System.out.println(
                    r.number+"   "
                            +r.source+"  ->  "
                            +r.destination
                            +"   ("+r.distance+" km)");

        }

    }


    static void searchRoute(){

        System.out.println("Enter Route Number:");

        String key=sc.next();

        Route r=routeDirectory.get(key); 
        // HASHING SEARCH (O(1) average time complexity)

        if(r!=null){

            System.out.println("\nRoute Located Successfully\n");

            System.out.println(
                    r.number+" : "
                            +r.source
                            +" -> "
                            +r.destination
                            +" Distance "+r.distance+" km");

        }

        else
            System.out.println("Route not found.");

    }


    static void organizeRoutes(){

        System.out.println("\nOrganizing routes by travel distance...\n");

        quickSort(0,routes.size()-1); 
        // SORTING ALGORITHM – QUICK SORT

        System.out.println("Routes successfully organized.");

        viewRoutes();

    }



    static void quickSort(int low,int high){ 
        // QUICK SORT ALGORITHM using RECURSION

        if(low<high){

            int pi=partition(low,high); // Partition operation

            quickSort(low,pi-1); // Recursive call

            quickSort(pi+1,high); // Recursive call

        }

    }


    static int partition(int low,int high){

        int pivot=routes.get(high).distance; 
        // Pivot selection in Quick Sort

        int i=low-1;

        for(int j=low;j<high;j++){

            if(routes.get(j).distance<pivot){

                i++;

                Route temp=routes.get(i);
                routes.set(i,routes.get(j));
                routes.set(j,temp); 
                // Swapping elements during sorting

            }

        }

        Route temp=routes.get(i+1);
        routes.set(i+1,routes.get(high));
        routes.set(high,temp);

        return i+1;

    }


    static double calculateFare(int distance,String type,int duration){

        double base=0.5*Math.pow(distance,2)+2*distance+10; 
        // MATHEMATICAL MODEL used for fare calculation

        if(type.equalsIgnoreCase("express"))
            base*=1.3;

        if(type.equalsIgnoreCase("deluxe"))
            base*=1.6;

        return base*duration;

    }


    static void applyPass(){

        sc.nextLine();

        System.out.println("\nEnter Passenger Name:");
        String name=sc.nextLine();

        System.out.println("Phone Number:");
        String phone=sc.nextLine();

        System.out.println("Aadhaar Number:");
        String aadhaar=sc.nextLine();

        System.out.println("Student ID (optional):");
        String sid=sc.nextLine();

        System.out.println("Route Number:");
        String route=sc.nextLine();

        Route r=routeDirectory.get(route); 
        // HASHMAP SEARCH

        if(r==null){

            System.out.println("Invalid Route.");
            return;

        }

        System.out.println("Bus Type (ordinary / express / deluxe):");
        String type=sc.nextLine();

        System.out.println("Pass Duration (months):");
        int duration=sc.nextInt();

        System.out.println("Priority Level (1 Normal / 2 Urgent):");
        int pr=sc.nextInt();

        double fare=calculateFare(r.distance,type,duration);

        Application app=new Application(
                name,phone,aadhaar,sid,route,type,duration,fare,pr);

        allApplications.add(app); 
        // LinkedList insertion

        processingQueue.add(app); 
        // QUEUE enqueue operation

        if(pr==2){

            urgentProcessing.add(app); 
            // PRIORITY QUEUE insertion

            fastTrack.addFirst(app); 
            // DEQUE operation (insert at front)

        }

        System.out.println("\nApplication Submitted Successfully");
        System.out.println("Total Fare : "+fare);

    }


    static void processApplication(){

        if(processingQueue.isEmpty()){

            System.out.println("No pending applications.");
            return;

        }

        Application a=processingQueue.poll(); 
        // QUEUE dequeue operation (FIFO)

        processedHistory.push(a); 
        // STACK push operation (LIFO)

        System.out.println("\nApplication processed for "+a.name);

    }


    static void processUrgent(){

        if(urgentProcessing.isEmpty()){

            System.out.println("No urgent applications.");
            return;

        }

        Application a=urgentProcessing.poll(); 
        // PRIORITY QUEUE removal (highest priority first)

        processedHistory.push(a); 
        // STACK push

        System.out.println("\nUrgent application processed for "+a.name);

    }


    static void viewHistory(){

        if(processedHistory.isEmpty()){

            System.out.println("No processed records.");
            return;

        }

        System.out.println("\nProcessed Applications\n");

        for(Application a:processedHistory){ 
            // STACK traversal

            System.out.println(
                    a.name+"  Route:"+a.route+
                            "  Fare:"+a.fare);

        }

    }


    static void viewQueue(){

        if(processingQueue.isEmpty()){

            System.out.println("No pending applications.");
            return;

        }

        System.out.println("\nPending Applications\n");

        for(Application a:processingQueue){ 
            // QUEUE traversal

            System.out.println(
                    a.name+" | Route "+a.route+" | Fare "+a.fare);

        }

    }

}