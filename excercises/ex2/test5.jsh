/open ImList.java
/open Cruise.java
/open Loader.java
/open RecycledLoader.java
/open Service.java
/open SmallCruise.java
/open BigCruise.java
/open level5.jsh

ImList<Cruise> cruises = new ImList<Cruise>().
add(new BigCruise("B1111", 0, 60, 1500)).
add(new SmallCruise("S1112", 0)).
add(new BigCruise("B1113", 30, 100, 1500)).
add(new BigCruise("B1114", 100, 100, 1500)).
add(new BigCruise("B1115", 130, 100, 1500)).
add(new BigCruise("B1116", 200, 100, 1500))

for (Service s : serveCruises(cruises)) {
    System.out.println(s);
}
