package org.example;

import org.example.Service.ChangeService;
import org.example.Service.FlatService;
import org.example.Service.MyRandomService;
import org.example.Service.SearchService;

import javax.persistence.*;
import java.util.Scanner;

public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;
    static FlatService flatS;
    static ChangeService changeS;
    static MyRandomService myRndS;
    static SearchService searchS;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPA");
            em = emf.createEntityManager();
            try {
                while (true) {
                    System.out.println("1: add flat");
                    System.out.println("2: add random flat");
                    System.out.println("3: delete flat");
                    System.out.println("4: change flat");
                    System.out.println("5: view flat");
                    System.out.println("6: search the flat by parameters");
                    System.out.print("-> ");

                    String s = sc.nextLine();
                    if (s.equals("1")) {
                        flatS.addFlat(sc);
                    } else if (s.equals("2")) {
                        insertRandomFlats(sc);
                    } else if (s.equals("3")) {
                        flatS.deleteFlat(sc);
                    } else if (s.equals("4")) {
                        changeFlatByParameters(sc);
                    } else if (s.equals("5")) {
                        viewFlats();
                    } else if (s.equals("6")) {
                        searchFlatsByParameters(sc);
                    } else {
                        return;
                    }
                }
            } finally {
                sc.close();
                em.close();
                emf.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }*/

        try {
            // create connection
            emf = Persistence.createEntityManagerFactory("JPA");
            em = emf.createEntityManager();
            flatS.mainMenu(sc);

        /*try {
            while (true) {
                System.out.println("1: add flat");
                System.out.println("2: add random flat");
                System.out.println("3: delete flat");
                System.out.println("4: change flat");
                System.out.println("5: view flat");
                System.out.println("6: search the flat by parameters");
                System.out.println("7: exit");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    flatS.addFlat(sc);
                } else if (s.equals("2")) {
                    myRnd.insertRandomFlats(sc);
                } else if (s.equals("3")) {
                    flatS.deleteFlat(sc);
                } else if (s.equals("4")) {
                    changeS.changeFlatByParameters(sc);
                } else if (s.equals("5")) {
                    viewFlats();
                } else if (s.equals("6")) {
                    searchS.searchFlatsByParameters(sc);
                } else if (s.equals("7")) {
                    break;
                }else {
                    break;
                }
            }
        } finally {
            sc.close();
            em.close();
            emf.close();
        }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

   /* private static void addFlat(Scanner sc) {
        System.out.print("Enter flat region: ");
        String flatRegion = sc.nextLine();
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        try {
            System.out.print("Enter flat square: ");
            String sSquare = sc.nextLine();
            Double flatSquare = Double.valueOf(sSquare);
            System.out.print("Enter flat rooms: ");
            String sRooms = sc.nextLine();
            Integer flatRooms = Integer.valueOf(sRooms);
            System.out.print("Enter flat price: ");
            String sPrice = sc.nextLine();
            Double dPrice = Double.valueOf(sPrice);
            BigDecimal flatPrice = BigDecimal.valueOf(dPrice);
            em.getTransaction().begin();
            try {
                Flat f = new Flat(flatRegion, flatAddress, flatSquare, flatRooms, flatPrice);
                em.persist(f);
                em.getTransaction().commit();
                System.out.println(f.getId());

            } catch (Exception ex) {
                em.getTransaction().rollback();
            }
        } catch (NumberFormatException ex) {
            System.out.println("Wrong parameter");
        }
    }

    private static void deleteFlat(Scanner sc) {
        System.out.print("Enter flat id: ");
        String flatId = sc.nextLine();
        long id = Long.parseLong(flatId);

        Flat f = em.getReference(Flat.class, id);
        if (f == null) {
            System.out.println("flat not found!");
            return;
        }

        em.getTransaction().begin();
        try {
            em.remove(f);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }*/


    /*private static void changeFlatByParameters(Scanner sc) {
        try {
            while (true) {
                System.out.println("1: change the flat region");
                System.out.println("2: change the flat address");
                System.out.println("3: change the flat square");
                System.out.println("4: change the flat rooms");
                System.out.println("5: change the flat price");
                System.out.println("6: previous menu");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    changeFlatRegion(sc);
                } else if (s.equals("2")) {
                    changeFlatAddress(sc);
                } else if (s.equals("3")) {
                    changeFlatSquare(sc);
                } else if (s.equals("4")) {
                    changeFlatRooms(sc);
                } else if (s.equals("5")) {
                    changeFlatPrice(sc);
                } else if (s.equals("6")) {
                    return;
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void changeFlatRegion(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter new flat region: ");
        String flatRegion = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setRegion(flatRegion);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatAddress(Scanner sc) {
        System.out.print("Enter old flat address: ");
        String oldFlatAddress = sc.nextLine();
        System.out.print("Enter new flat address: ");
        String newFlatAddress = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", oldFlatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setAddress(newFlatAddress);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatSquare(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter flat square: ");
        String sSquare = sc.nextLine();
        Double flatSquare = Double.valueOf(sSquare);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setSquare(flatSquare);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatRooms(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter flat rooms: ");
        String sRooms = sc.nextLine();
        Integer flatRooms = Integer.valueOf(sRooms);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setRooms(flatRooms);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }

    private static void changeFlatPrice(Scanner sc) {
        System.out.print("Enter flat address: ");
        String flatAddress = sc.nextLine();
        System.out.print("Enter new flat price: ");
        String sPrice = sc.nextLine();
        Double dPrice = Double.valueOf(sPrice);
        BigDecimal flatPrice = BigDecimal.valueOf(dPrice);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);

            flat = (Flat) query.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("Flat not found!");
            return;
        } catch (NonUniqueResultException ex) {
            System.out.println("Non unique result!");
            return;
        }

        em.getTransaction().begin();
        try {
            flat.setPrice(flatPrice);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }*/


    /*private static void searchFlatsByParameters(Scanner sc) {
        try {
            while (true) {
                System.out.println("1: search the flat by region");
                System.out.println("2: search the flat by address");
                System.out.println("3: search the flat by square");
                System.out.println("4: search the flat by rooms");
                System.out.println("5: search the flat by price");
                System.out.println("6: previous menu");
                System.out.print("-> ");

                String s = sc.nextLine();
                if (s.equals("1")) {
                    searchFlatsByRegion(sc);
                } else if (s.equals("2")) {
                    searchFlatsByAddress(sc);
                } else if (s.equals("3")) {
                    searchFlatsBySquare(sc);
                } else if (s.equals("4")) {
                    searchFlatsByRooms(sc);
                } else if (s.equals("5")) {
                    searchFlatsByPrice(sc);
                } else if (s.equals("6")) {
                    return;
                } else {
                    return;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private static void searchFlatsByRegion(Scanner sc) {
        System.out.println("Enter region witch your want");
        String flatRegion = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.region = :region", Flat.class);
            query.setParameter("region", flatRegion);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByAddress(Scanner sc) {
        System.out.println("Enter address witch your want");
        String flatAddress = sc.nextLine();
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.address = :address", Flat.class);
            query.setParameter("address", flatAddress);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsBySquare(Scanner sc) {
        System.out.print("Square no more then : ");
        String sSquare = sc.nextLine();
        Double flatSquare = Double.valueOf(sSquare);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.square <= :square ORDER BY x.square", Flat.class);
            query.setParameter("square", flatSquare);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByRooms(Scanner sc) {
        System.out.print("Quantity of rooms no more then : ");
        String sRooms = sc.nextLine();
        Integer flatRooms = Integer.valueOf(sRooms);
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.rooms <= :rooms ORDER BY x.rooms", Flat.class);
            query.setParameter("rooms", flatRooms);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }

    private static void searchFlatsByPrice(Scanner sc) {
        System.out.print("Price no more than : ");
        String sPrice = sc.nextLine();
        BigDecimal flatPrice = BigDecimal.valueOf(Long.parseLong(sPrice));
        Flat flat = null;
        try {
            Query query = em.createQuery(
                    "SELECT x FROM Flat x WHERE x.price <= :price ORDER BY x.price", Flat.class);
            query.setParameter("price", flatPrice);
            List<Flat> list = (List<Flat>) query.getResultList();
            for (Flat x : list)
                System.out.println(x);
        } catch (NoResultException ex) {
            System.out.println("No any matches...");
        }

    }*/

/*
    private static void insertRandomFlats(Scanner sc) {
        System.out.print("Enter flatS count: ");
        String sCount = sc.nextLine();
        Integer count = Integer.valueOf(sCount);
        em.getTransaction().begin();
        try {
            if (count >= 1) {
                for (int i = 1; i < count+1; i++) {
                    Flat f = new Flat(randomRegion(), randomAddresses(), randomSquare(), randomRooms(), randomPrice());
                    em.persist(f);
                }
                em.getTransaction().commit();
            } else {
                System.out.println("Parameters of your flat can't be negative are zero!!!");
            }
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }
    }
*/

/*
    private static void viewFlats() {
        Query query = em.createQuery("SELECT f FROM Flat f", Flat.class);
        List<Flat> list = (List<Flat>) query.getResultList();

        for (Flat f : list)
            System.out.println(f);
    }
*/

    /*static final String[] REGIONS = {"Kiev", "Odessa", "Lviv", "Kharkiv",};
    static final String[] ADDRESSES = {"Kievskaya", "Odesskaya", "Lvivskaya", "Kharkivskaya",};
    static final Random RND = new Random();

    static String randomRegion() {
        return REGIONS[RND.nextInt(REGIONS.length)];
    }


    static String randomAddresses() {
        return ADDRESSES[RND.nextInt(ADDRESSES.length)] + " " + RND.nextInt(50);
    }

    static Double randomSquare() {
        double min = 10.0;
        double max = 100.0;
        double mathResult = min + Math.random() * max;
        double scale = Math.pow(10, 3);
        double result = Math.ceil(mathResult * scale) / scale;
        return result;

    }

    static Integer randomRooms() {
        int min = 1;
        int max = 4;
        int mathResult = (int) (min + Math.random() * max);
        int scale = (int) Math.pow(10, 3);
        int result = (int) (Math.ceil(mathResult * scale) / scale);
        return result;
    }

    static BigDecimal randomPrice() {
        double min = 10000;
        double max = 1000000;
        double mathResult = min + Math.random() * max;
        double scale = Math.pow(10, 3);
        double result = Math.ceil(mathResult * scale) / scale;
        return BigDecimal.valueOf(result);
    }*/
}


