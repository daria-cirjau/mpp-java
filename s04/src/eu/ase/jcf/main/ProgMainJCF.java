package eu.ase.jcf.main;

import eu.ase.jcf.classes.Country;
import eu.ase.jcf.classes.Plane;

import java.util.*;

public class ProgMainJCF {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("text");
        //list.add(10);
        List<Plane> listPlanes = new ArrayList<Plane>();
        //List<Plane> listPlanes = new LinkedList<Plane>();

        System.out.println("size / capacity = " + listPlanes.size());

        for (int i = 0; i < 20; i++) {
            listPlanes.add(new Plane(101 + i, "Airbus " + i, 500.0f));
        }
        System.out.println("size / capacity = " + listPlanes.size());

        Plane temp = null;
        for (Iterator<Plane> it = listPlanes.iterator(); it.hasNext(); ) {
            temp = it.next();
            temp.print();
        }

        for (Plane p : listPlanes) {
            p.print();
        }

        Set<Plane> setPlanes = new HashSet<Plane>();

        Plane p1 = new Plane(1, "Airbus", 300.0f);
        Plane p2 = new Plane(1, "Airbus", 300.0f);

        setPlanes.add(p1);
        setPlanes.add(p2);

        System.out.println(setPlanes.size());

        Map<Plane, Country> treeMap = new TreeMap<Plane, Country>();
        //Map<Plane, Country> treeMap = new HashMap<Plane, Country>();
        for (int i = 0; i < 7; i++) {
            Plane pk = new Plane(201 + i, "Airbus " + (i * 10), 351.0f + i);
            Country cv = new Country(701 + i, "Country " + i);

            treeMap.put(pk, cv);
        }

        //System.out.println(treeMap);
        Set<Plane> s = treeMap.keySet();
        Iterator<Plane> itp = s.iterator();
        while (itp.hasNext()) {
            Plane pk = itp.next();
            Country cvalue = treeMap.get(pk);
            pk.print();
            cvalue.print();
        }

        Set<Map.Entry<Plane, Country>> entries = treeMap.entrySet();
        Iterator<Map.Entry<Plane, Country>> itEntry = entries.iterator();
        while (itEntry.hasNext()) {
            Map.Entry<Plane, Country> entry = itEntry.next();
            Plane pk = entry.getKey();
            Country cv = entry.getValue();
            pk.print();
            cv.print();
        }

        for (Map.Entry<Plane, Country> entry : treeMap.entrySet()) {
            Plane pk = entry.getKey();
            Country cv = entry.getValue();
            pk.print();
            cv.print();
        }
    }
}
