// importowanie klasy Random z pakietu java.util, aby móc generować liczby losowe
import java.util.Random;

// Definicja klasy Node, która reprezentuje pojedynczy węzeł w liście połączonej
class Node {
    int data; // wartość w węźle
    Node next; // referencja do następnego węzła

    // konstruktor dla klasy Node
    Node(int data) {
        this.data = data; // przypisuje wartość do pola data w obiekcie
        this.next = null; // ustawia pole next na null, ponieważ na razie nie ma następnego węzła
    }
}

// Definicja klasy LinkedList, reprezentująca listę połączoną
class LinkedList {
    Node head; // pierwszy węzeł listy

    // metoda do dodawania nowego węzła na końcu listy
    void addNode(int data) {
        Node newNode = new Node(data); // tworzy nowy węzeł z podanymi danymi

        // Jeżeli lista jest pusta, ustawia nowy węzeł jako głowę
        if (head == null) {
            head = newNode;
        } else {
            // Jeśli lista nie jest pusta, przesuwa się do końca listy i dodaje nowy węzeł
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // metoda do drukowania listy od początku do końca
    void printList() {
        Node current = head; // zaczynamy od głowy
        while (current != null) { // idzie przez całą listę
            System.out.print(current.data + " "); // drukuje dane z obecnego węzła
            current = current.next; // przechodzi do następnego węzła
        }
        System.out.println(); // dodaje nową linię na końcu
    }

    // metoda do odwrócenia listy
    Node reverseList() {
        Node previous = null; // początkowo ustawia poprzedni węzeł na null
        Node current = head; // ustawia bieżący węzeł na głowę
        Node next = null; // następny węzeł na null

        // przechodzi przez listę, odwracając linki między węzłami
        while (current != null) {
            next = current.next; // zapisuje link do następnego węzła
            current.next = previous; // odwraca link do poprzedniego węzła
            previous = current; // przesuwa poprzedni i obecny węzeł o jeden krok do przodu
            current = next;
        }

        return previous; // zwraca odwróconą listę
    }
}

public class Main {
    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        Random random = new Random();

        for (int i = 0; i < 20; i++) {
            int randomNumber = random.nextInt(899) + 100; // losowanie liczby całkowitej z przedziału [100, 999)
            list.addNode(randomNumber);
        }

        System.out.println("Oryginalna lista:");
        list.printList();

        System.out.println("Odwrócona lista:");
        Node reversedHead = list.reverseList();
        LinkedList reversedList = new LinkedList();
        reversedList.head = reversedHead;
        reversedList.printList();
    }
}
