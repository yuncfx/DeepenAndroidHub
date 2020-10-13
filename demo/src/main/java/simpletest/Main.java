package simpletest;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import collections.Person;


public class Main {
    
    public static void main(String[] args) {
        Calendar c = Calendar.getInstance();
        c.set(2016, 1, 11);
        Person p = new Person("hello", 15, c);
        
        List<Person> list = new ArrayList<>();
        list.add(p);
        
        try {
            List<Person> deepClone = (List<Person>) deepClone(list);
            Iterator<Person> iterator = deepClone.iterator();
            while(iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            
            Person pp = deepClone.get(0);
            c.set(2020, 3, 13);
            pp.setBirth(c);
            System.out.println(pp);
            System.out.println(p);
            
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns a new collection containing clones of all the items in the
     * specified collection.
     * 
     * @param collection
     *            the collection (<code>null</code> not permitted).
     * @return A new collection containing clones of all the items in the
     *         specified collection.
     * @throws CloneNotSupportedException
     *             if any of the items in the collection cannot be cloned.
     */
    public static Collection<Person> deepClone(final Collection<Person> collection) throws CloneNotSupportedException {

        if (collection == null) {
            throw new IllegalArgumentException("Null 'collection' argument.");
        }
        // all JDK-Collections are cloneable ...
        // and if the collection is not clonable, then we should throw
        // a CloneNotSupportedException anyway ...
        final Collection<Person> result = (Collection<Person>) clone(collection);
        result.clear();
        final Iterator iterator = collection.iterator();
        while (iterator.hasNext()) {
            final Object item = iterator.next();
            if (item != null) {
                result.add((Person) clone(item));
            } else {
                result.add(null);
            }
        }
        return result;
    }

    /**
     * Returns a clone of the specified object, if it can be cloned, otherwise
     * throws a CloneNotSupportedException.
     * 
     * @param object
     *            the object to clone (<code>null</code> not permitted).
     * @return A clone of the specified object.
     * @throws CloneNotSupportedException
     *             if the object cannot be cloned.
     */
    public static Object clone(final Object object) throws CloneNotSupportedException {
        if (object == null) {
            throw new IllegalArgumentException("Null 'object' argument.");
        }

        try {
            final Method method = object.getClass().getMethod("clone", (Class[]) null);
            if (Modifier.isPublic(method.getModifiers())) {
                return method.invoke(object, (Object[]) null);
            }
        } catch (NoSuchMethodException e) {
            System.out.println("Object without clone() method is impossible.");
        } catch (IllegalAccessException e) {
            System.out.println("Object.clone(): unable to call method.");
        } catch (InvocationTargetException e) {
            System.out.println("Object without clone() method is impossible.");
        }

        throw new CloneNotSupportedException("Failed to clone.");
    }

}