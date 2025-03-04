package client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class BookClient {

    // API URL till din Spring Boot-server
    private static final String BASE_URL = "http://localhost:8080/books";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            showMenu();  // Visa användarmenyn
            int choice = scanner.nextInt();  // Läs användarens val
            scanner.nextLine();  // Rensa ny rad efter val

            switch (choice) {
                case 1:
                    getAllBooks();  // Hämta alla böcker
                    break;
                case 2:
                    getBookByTitle();  // Hämta en bok baserat på titel
                    break;
                case 3:
                    getBooksByCategory();  // Hämta böcker baserat på kategori
                    break;
                case 4:
                    createNewBook();  // Skapa en ny bok
                    break;
                case 5:
                    deleteBook();  // Ta bort en bok
                    break;
                case 6:
                    System.out.println("Avslutar...");
                    return;  // Avsluta programmet
                default:
                    System.out.println("Ogiltigt val, försök igen.");
            }
        }
    }

    // Funktion för att visa menyn
    private static void showMenu() {
        System.out.println("\nVälj ett alternativ:");
        System.out.println("1. Hämta alla böcker");
        System.out.println("2. Hämta en specifik bok genom titel");
        System.out.println("3. Hämta böcker baserat på kategori");
        System.out.println("4. Skapa en ny bok");
        System.out.println("5. Ta bort en bok genom ID");
        System.out.println("6. Avsluta");
        System.out.print("Välj ett alternativ: ");
    }

    // Hämta alla böcker (GET /books)
    private static void getAllBooks() {
        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Alla böcker: " + response.toString());
        } catch (Exception e) {
            System.out.println("Fel vid hämtning av böcker: " + e.getMessage());
        }
    }

    // Hämta bok via titel (GET /books och sök genom titlar)
    private static void getBookByTitle() {
        System.out.print("Skriv in titel på bok: ");
        String title = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // Sök genom böckerna för att hitta böcker med titel som innehåller det angivna ordet
            String books = response.toString();
            if (books.contains(title)) {
                System.out.println("Böcker som matchar titeln '" + title + "': " + books);
            } else {
                System.out.println("Ingen bok hittades med titeln: " + title);
            }
        } catch (Exception e) {
            System.out.println("Fel vid hämtning av bok: " + e.getMessage());
        }
    }

    // Hämta böcker baserat på kategori (GET /books/category/{category})
    private static void getBooksByCategory() {
        System.out.print("Skriv in kategori (t.ex. IT, MATH, PHYSICS): ");
        String category = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL + "/category/" + category);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Böcker i kategori " + category + ": " + response.toString());
        } catch (Exception e) {
            System.out.println("Fel vid hämtning av böcker: " + e.getMessage());
        }
    }

    // Skapa en ny bok (POST /books)
    private static void createNewBook() {
        System.out.print("Skriv in boktitel: ");
        String title = scanner.nextLine();
        System.out.print("Skriv in bokförfattare: ");
        String author = scanner.nextLine();
        System.out.print("Skriv in bokbeskrivning: ");
        String description = scanner.nextLine();
        System.out.print("Skriv in publiceringsår: ");
        String publishedYear = scanner.nextLine();
        System.out.print("Skriv in kategori (t.ex. IT, MATH, PHYSICS): ");
        String category = scanner.nextLine();

        try {
            URL url = new URL(BASE_URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setDoOutput(true);

            // Skapa JSON-sträng för den nya boken
            String jsonInputString = String.format(
                    "{\"title\": \"%s\", \"author\": \"%s\", \"description\": \"%s\", \"publishedYear\": \"%s\", \"category\": \"%s\"}",
                    title, author, description, publishedYear, category);

            // Skicka JSON-data till servern
            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            System.out.println("Skapad bok: " + response.toString());
        } catch (Exception e) {
            System.out.println("Fel vid skapande av bok: " + e.getMessage());
        }
    }
    // Ta bort en bok (DELETE /books/id)
    private static void deleteBook() {
        System.out.print("Skriv in ID på bok som ska tas bort: ");
        int id = scanner.nextInt();
        scanner.nextLine();  // Clear buffer

        try {
            URL url = new URL(BASE_URL + "/" + id);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("DELETE");

            int responseCode = connection.getResponseCode();
            if (responseCode == 204) {
                System.out.println("Boken med ID " + id + " har tagits bort.");
            } else {
                System.out.println("Fel vid borttagning av bok: " + connection.getResponseMessage());
            }
        } catch (Exception e) {
            System.out.println("Fel vid borttagning av bok: " + e.getMessage());
        }
    }
}


