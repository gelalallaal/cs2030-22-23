/open ImList.java
/open Cruise.java
/open Loader.java
/open Service.java
/open level3.jsh

ImList<Cruise> cruises = new ImList<Cruise>().
add(new Cruise("A1234", 0, 2, 30)).
add(new Cruise("A2345", 30, 2, 30)).
add(new Cruise("A3456", 130, 2, 30))

for (Service s : serveCruises(cruises)) {
    System.out.println(s);
}

cruises = new ImList<Cruise>().
add(new Cruise("A1234", 0, 2, 30)).
add(new Cruise("A2345", 0, 2, 10)).
add(new Cruise("A3456", 10, 2, 20))

for (Service s : serveCruises(cruises)) {
    System.out.println(s);
}

