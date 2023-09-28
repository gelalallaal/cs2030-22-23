/open ImList.java
/open Cruise.java
/open Loader.java
/open Service.java
/open SmallCruise.java
/open BigCruise.java
new SmallCruise("S0001", 0).getArrivalTime()

new SmallCruise("S0001", 0).getServiceTime()

new SmallCruise("S0001", 0).getNumOfLoadersRequired()

Cruise cruise = new SmallCruise("S0123", 1220)

cruise = new BigCruise("B0001", 0, 70, 3000)

cruise.getArrivalTime()

cruise.getServiceTime()

cruise.getNumOfLoadersRequired()

/open level3.jsh

ImList<Cruise> cruises = new ImList<Cruise>().add(new SmallCruise("S1111", 1300))

serveCruises(cruises)

cruises = new ImList<Cruise>().
add(new BigCruise("B1111", 1300, 80, 3000)).
add(new SmallCruise("S1111", 1359)).
add(new SmallCruise("S1112", 1500)).
add(new SmallCruise("S1113", 1529))

for (Service s : serveCruises(cruises)) {
    System.out.println(s);
}

cruises = new ImList<Cruise>().
add(new SmallCruise("S1111", 900)).
add(new BigCruise("B1112", 901, 100, 1)).
add(new BigCruise("B1113", 902, 20, 4500)).
add(new SmallCruise("S2030", 1031)).
add(new BigCruise("B0001", 1100, 30, 1500)).
add(new SmallCruise("S0001", 1130))

for (Service s : serveCruises(cruises)) {
    System.out.println(s);
}
