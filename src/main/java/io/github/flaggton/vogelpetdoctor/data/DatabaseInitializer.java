package io.github.flaggton.vogelpetdoctor.data;

import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtil;
import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.HibernateQueryUtilException;
import com.wedasoft.simpleJavaFxApplicationBase.hibernateUtil.conditions.Condition;

import java.time.LocalDate;
import java.util.List;

public class DatabaseInitializer {
    public static void init() {
        System.out.println("start DatabaseInitializer");
        initOwner();
        initPet();
        System.out.println("end DatabaseInitializer");
    }

    private static void initOwner() {
        try {
            long amount = HibernateQueryUtil.Finder.countAll(Owner.class);
            if (amount == 0) {
                Owner o1 = new Owner(null, "David", "Weber", "david.weber@rever.de", LocalDate.of(1991, 11, 4));
                Owner o2 = new Owner(null, "Markus", "Vogel", "markus.vogel@rever.de", LocalDate.of(1998, 3, 12));
                Owner o3 = new Owner(null, "Julia", "Bäcker", "julia.baecker@rever.de", LocalDate.of(1970, 1, 1));
                HibernateQueryUtil.Inserter.insertMany(List.of(o1,o2,o3));
            }
        } catch (HibernateQueryUtilException e) {
            throw new RuntimeException(e);
        }
    }
    private static void initPet(){
        try {
            long amount = HibernateQueryUtil.Finder.countAll(Pet.class);
            if (amount == 0){
                Owner davidWeber = HibernateQueryUtil.Finder.findWithBuilder(Owner.class)
                        .addCondition("firstName", Condition.isEqualTo("David"))
                        .addCondition("lastName", Condition.isEqualTo("Weber"))
                        .findAll()
                        .get(0);
                Pet p1 = new Pet(null, davidWeber.getId(), "Spark", "Dog");
                Pet p2 = new Pet(null, davidWeber.getId(), "Whoofie", "Dog");
                Owner markusVogel = HibernateQueryUtil.Finder.findWithBuilder(Owner.class)
                        .addCondition("firstName", Condition.isEqualTo("Markus"))
                        .addCondition("lastName", Condition.isEqualTo("Vogel"))
                        .findAll()
                        .get(0);
                Pet p3 = new Pet(null,markusVogel.getId(), "Mauzie", "Cat");
                HibernateQueryUtil.Inserter.insertMany(List.of(p1,p2,p3));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
