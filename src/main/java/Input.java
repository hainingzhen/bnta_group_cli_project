import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class Input {

    public static ArrayList<Integer> setUniqueId() {
        LinkedHashSet<Integer> uniqueIdSet = new LinkedHashSet<>();
        Random uniqueId = new Random();

        while (uniqueIdSet.size() < 1000) {
            uniqueIdSet.add(uniqueId.nextInt(1000 - 0 + 1));
        }
        ArrayList<Integer> uniqueIdList = new ArrayList<>();
        uniqueIdList.addAll(uniqueIdSet);

        return uniqueIdList;
    }

    public static ArrayList<Integer> uniqueIdPassenger() {
        ArrayList<Integer> passengerId = setUniqueId();

        return passengerId;
    }

    public static ArrayList<Integer> uniqueIdFlight() {
        ArrayList<Integer> flightId = setUniqueId();

        return flightId;
    }


    public static ArrayList<Passenger> passengerList = Passenger.passengersList();

    public static HashMap<Integer, Passenger> passengerHashMap = Passenger.passengerHashMap();

    public static ArrayList<Integer> passengerId = uniqueIdPassenger();

    public static ArrayList<Flight> flightList = Flight.flightList();

    public static ArrayList<String> fileOutput = new ArrayList<>();


    public static void addPassengerInput(Scanner input) {
        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("============= ADD NEW PASSENGER  =============");
        System.out.println(" ");

        System.out.println("All passengers: ");
        System.out.println(" ");

        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }

        System.out.println(" ");

//        while (true) {

        System.out.println("Would you like to add a Passenger? y / n");
        Character c = Character.valueOf(input.nextLine().charAt(0));

        if (c == 'y') {
            System.out.println("Enter your Name here: ");

            String name = input.nextLine();

            int mobileNumber = 0;
            try {
                System.out.println("Enter your mobile number here: ");
                mobileNumber = Integer.valueOf(input.nextLine());
            } catch (Exception e) {
                System.out.println("Please input a series of numbers");
            }
            Passenger newPassenger = new Passenger(name, mobileNumber, passengerId.get(0));
            passengerId.remove(0);
            passengerList.add(newPassenger);
            passengerHashMap.put(newPassenger.getUniqueId(), newPassenger);

            displayAllPassengers();
        }

//            if (c == 'n') {
//                break;
//            }
//        }
    }


    public static void addNewFlightInput(Scanner input) {
//        while (true) {
        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("============== ADD NEW FLIGHTS ===============");
        System.out.println(" ");

        Flight.displayAllAvailableFlights(flightList);

        System.out.println(" ");


        System.out.println ("Would you like to add a new Flight? y / n");
        Character c = Character.valueOf(input.nextLine().charAt(0));
        if (c == 'y') {

            System.out.println("Enter your Destination here: ");

            String destination = input.nextLine();

            int id = uniqueIdFlight().get(0);

            Flight newFlight = new Flight(destination, id);
            flightList.add(newFlight);


            displayAllFlights();
        }
//            if (c == 'n') {
//                break;
//            }
//        }
    }


    public static void cancelFlight(Scanner input) {
//        while(true) {

        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("============== CANCEL A FLIGHT ===============");
        System.out.println(" ");

        Flight.displayAllAvailableFlights(flightList);

        System.out.println(" ");

        System.out.println("Would you like to cancel a Flight? y / n");
        char c = Character.valueOf(input.nextLine().charAt(0));

        if (c == 'y') {
            System.out.println("Enter the Flight name to cancel");
            String destinationCancel = input.nextLine();

            for (int i = 0; i < flightList.size(); i++) {
                if (flightList.get(i).getDestination().toLowerCase().equals(destinationCancel.toLowerCase())) {
                    Flight.cancelFlight(flightList.get(i), flightList);
                }
            }
            displayAllFlights();
        }
//            if (c == 'n') {
//                break;
//            }
//        }
    }


    public static void searchFlightByDestination(Scanner input) {
//        while(true){

        NavDisplay.clearDisplay();
        System.out.println(" ");
        System.out.println("============== SEARCH FOR A FLIGHT ===============");
        System.out.println(" ");

        Flight.displayAllAvailableFlights(flightList);
        System.out.println(" ");


        System.out.println("Looking for a particular destination? y / n");
        char c4 = Character.valueOf(input.nextLine().charAt(0));

        if(c4 == 'y'){

            System.out.println("Please enter your destination here: ");
            String searchDestination = input.nextLine();

            System.out.println(" ");
            System.out.println("Result:");
            System.out.println(" ");

            searchFlight(searchDestination);

            Scanner any = new Scanner(System.in);

            System.out.println(" ");
            System.out.println("Press Enter to exit.");
            System.out.println(" ");

            String i = any.nextLine();
            if (!i.equals("")) return;

        }
//            else if(c4 == 'n'){
//                break;
//            }
//        }
    }


    public static void searchFlight(String destination) {
        ArrayList<Flight> foundFlight = new ArrayList<>();

        flightList.forEach(flight -> {
            if (destination.equalsIgnoreCase(flight.getDestination())){
                foundFlight.add(flight);
            }
        });

        if (!foundFlight.isEmpty()) {
            System.out.println("Flight(s) found.");
            for (Flight flight: foundFlight) {
                System.out.println(flight.toString());
            }
            return;
        }
        System.out.println("No flight found! Please try again.");
    }



    public static void createBooking(Scanner input) {
//        while (true) {

        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("============== CREATE A BOOKING ===============");
        System.out.println(" ");

        Flight.displayAllAvailableFlights(flightList);

        System.out.println(" ");

        System.out.println("All passengers: ");
        System.out.println(" ");

        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }

        System.out.println(" ");

        System.out.println("Would you like to create a Booking? y / n");
        char c2 = Character.valueOf(input.nextLine().charAt(0));
//        System.out.println(passengerList);
        if (c2 == 'y') {

            System.out.println("Enter your ID number here: ");
            int idNum = Integer.valueOf(input.nextLine());

            System.out.println("Enter the Flight ID you would like to book: ");
            int flightId = Integer.valueOf(input.nextLine());

            Passenger passenger = passengerHashMap.get(idNum);


            for (int i = 0; i < flightList.size(); i++) {
                if (flightList.get(i).getId() == flightId) {
                    try {
                        String bookingResult = passengerBookFlight(passenger, flightList.get(i));
                        fileOutput.add(bookingResult);
                    }
                    catch (RuntimeException e) {
                        System.out.println("Flight does not exist!");
                    }
//                    System.out.println(passengerBookFlight(passenger, flightList.get(i)));
                }
            }

//            System.out.println(flightList);
            displayAllFlights();
        }
//            if (c2 == 'n') {
//                break;
//            }
//
//        }
    }


    public static String passengerBookFlight(Passenger passenger, Flight flight) throws RuntimeException {

        for (Flight f: flightList) {
            if (f.getId() == flight.getId()) {
                if (f.addPassenger(passenger)) {
                    return "Passenger " +
                            passenger.getName() +
                            " (" + passenger.getUniqueId() + ")" +
                            " successfully added to flight " +
                            flight.getId() +
                            " with destination " +
                            flight.getDestination() + ".";
                } else {
                    return "Something has gone wrong. Passenger " +
                            passenger.getName() +
                            " may have already booked this flight.";
                }
            }
        }
        throw new RuntimeException();
    }

    public static void writeToFile() {
        try {
            Bookings.writeFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static void viewAllBookings(Scanner input) {

        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("=============== ALL PASSENGERS ==============");
        System.out.println(" ");

        System.out.println("Would you like to view the bookings? y / n");
        char c3 = Character.valueOf(input.nextLine().charAt(0));

        if (c3 == 'y') {
            try {
                Bookings.readFile();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }

            Scanner any = new Scanner(System.in);

            System.out.println(" ");
            System.out.println("Press Enter to exit.");
            System.out.println(" ");

            String i = any.nextLine();
            if (!i.equals("")) return;
//            System.out.println(passengerList);
//            System.out.println(passengerHashMap);
//            displayAllFlights();
        }
    }

    public static void displayAllPassengers() {

        Scanner any = new Scanner(System.in);
        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("=============== ALL PASSENGERS ==============");
        System.out.println(" ");

        System.out.println("All passengers: ");
        System.out.println(" ");

        for (Passenger p : passengerList) {
            System.out.println(p.toString());
        }

        System.out.println(" ");
        System.out.println("Press Enter to exit.");
        System.out.println(" ");

        String input = any.nextLine();
        if (!input.equals("")) return;
    }


    public static void displayAllFlights() {

        Scanner any = new Scanner(System.in);

        NavDisplay.clearDisplay();

        System.out.println(" ");
        System.out.println("=========== ALL AVAILABLE FLIGHTS ============");
        System.out.println(" ");

        Flight.displayAllAvailableFlights(flightList);

        System.out.println(" ");
        System.out.println("Press Enter to exit.");
        System.out.println(" ");

        String input = any.nextLine();
        if (!input.equals("")) return;

    }
}
