package year2015.day1;

import java.util.concurrent.atomic.AtomicInteger;

public class FloorFinder {
    public static void findSantasFloor(String lines){
        AtomicInteger currentPosition = new AtomicInteger();
        AtomicInteger basementAccess = new AtomicInteger();
        AtomicInteger index = new AtomicInteger();

        lines.chars().forEach(c -> {
            var str = Character.toString(c);

            switch (str){
                case "(" -> currentPosition.getAndIncrement();
                case ")" -> currentPosition.getAndDecrement();
                default -> System.out.println("Invalid character.");
            }

            index.getAndIncrement();
            if(currentPosition.get() == -1 && basementAccess.get() == 0)
                basementAccess.set(index.get());

        });

        System.out.println("Santa's currently on the floor number " + currentPosition.get());
        System.out.println("The first character that accesses the basement is " + basementAccess.get());
//        AtomicInteger ups = new AtomicInteger();
//        AtomicInteger downs = new AtomicInteger();
//
//        List<String> charList = test.chars()
//                .mapToObj(String::valueOf)
//                .toList()
//                .stream()
//                .peek((c) -> {
//                    if(c.equals("("))
//                        ups.getAndIncrement();
//                    if(c.equals(")"))
//                        downs.getAndIncrement();
//                })
//                .toList();
//
//        return ups.get() - downs.get();
    }
}
