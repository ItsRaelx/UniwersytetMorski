import java.util.LinkedList;

public class AnalizaGrafu {
    public static void main(String[] args) {
        // Krawędzie grafu jako tablica par wierzchołków
        int[][] krawedzie = {{0, 1}, {0, 2}, {0, 3}, {1, 3}, {1, 4}, {2, 5}, {2, 9}, {3, 6}, {4, 7}, {4, 8}, {5, 8}, {5, 9}, {6, 7}, {6, 9}, {7, 8}};
        int liczbaWierzcholkow = 10; // Maksymalny indeks wierzchołka + 1

        // Tworzenie grafu
        Graf graf = new Graf(liczbaWierzcholkow);

        // Dodawanie krawędzi do grafu
        for (int[] krawedz : krawedzie) {
            graf.dodajKrawedz(krawedz[0], krawedz[1]);
        }

        // Znajdowanie wierzchołka o największym stopniu
        int maxStopien = -1;
        int wierzcholekOMaksStopniu = -1;
        for (int v = 0; v < liczbaWierzcholkow; v++) {
            int stopien = 0;
            for (int ignored : graf.sasiedzi(v)) stopien++;
            if (stopien > maxStopien) {
                maxStopien = stopien;
                wierzcholekOMaksStopniu = v;
            }
        }
        System.out.println("Wierzchołek o największym stopniu: " + wierzcholekOMaksStopniu);


        // Sprawdzanie, czy w grafie występują cykle
        boolean czyCykle = czyCykle(graf);
        System.out.println("Czy w grafie występują cykle? " + czyCykle);

        // Sprawdzanie, czy istnieje ścieżka z wierzchołka 3 do wierzchołka 7
        SciezkaWGlab sciezka = new SciezkaWGlab(graf, 3);
        boolean czySciezkaZ3Do7 = sciezka.czyIstniejeDo(7);
        System.out.println("Czy istnieje ścieżka z wierzchołka 3 do wierzchołka 7? " + czySciezkaZ3Do7);
    }

    // Metoda sprawdzająca, czy w grafie występują cykle
    private static boolean czyCykle(Graf graf) {
        boolean[] odwiedzone = new boolean[graf.V()];
        boolean[] stosRekurencji = new boolean[graf.V()];

        // Przeszukiwanie każdego wierzchołka w grafie
        for (int v = 0; v < graf.V(); v++) {
            if (czyCykl(graf, v, odwiedzone, stosRekurencji))
                return true;
        }

        return false;
    }

    // Metoda pomocnicza dla DFS sprawdzająca, czy istnieje cykl
    private static boolean czyCykl(Graf graf, int v, boolean[] odwiedzone, boolean[] stosRekurencji) {
        if (stosRekurencji[v])
            return true;

        if (odwiedzone[v])
            return false;

        odwiedzone[v] = true;
        stosRekurencji[v] = true;

        for (int sasiad : graf.sasiedzi(v)) {
            if (czyCykl(graf, sasiad, odwiedzone, stosRekurencji))
                return true;
        }

        stosRekurencji[v] = false;
        return false;
    }
}

class Graf {
    private final int V;
    private final LinkedList<Integer>[] adj;

    public Graf(int V) {
        this.V = V;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new LinkedList<>();
    }

    public void dodajKrawedz(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> sasiedzi(int v) {
        return adj[v];
    }

    public int V() {
        return V;
    }
}

class SciezkaWGlab {
    private final boolean[] odwiedzone;

    public SciezkaWGlab(Graf graf, int s) {
        odwiedzone = new boolean[graf.V()];
        dfs(graf, s);
    }

    private void dfs(Graf graf, int v) {
        odwiedzone[v] = true;
        for (int w : graf.sasiedzi(v)) {
            if (!odwiedzone[w]) {
                dfs(graf, w);
            }
        }
    }

    public boolean czyIstniejeDo(int v) {
        return odwiedzone[v];
    }
}

