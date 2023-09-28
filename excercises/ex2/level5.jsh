boolean isActive(Cruise c, Service s) {
    if (s.getLoader().toString().startsWith("Recycled")) {
        return c.getArrivalTime() < s.getServiceEndTime() + 60;
    }
    return c.getArrivalTime() < s.getServiceEndTime();
}

// takes in a list of cruises, and returns the service schedule of the loaders in the form a list.
ImList<Service> serveCruises(ImList<Cruise> cruises) {
    ImList<Service> records = new ImList<Service>();
    ImList<Service> active = new ImList<Service>();
    ImList<Service> expired = new ImList<Service>();
    ImList<Service> temp = new ImList<Service>();
    Loader loader;
    Service s;
    int loaderId = 1;
    int nLoaders = 0;

    for (Cruise i: cruises) {
        // clean active services list
        temp = active.addAll(new ImList<Service>());
        for (Service j: temp){
            if (!isActive(i, j)) {
                expired = expired.add(j);
                active = active.remove(active.indexOf(j));
            }
        }

        // service new cruise
        nLoaders = i.getNumOfLoadersRequired();
        for (int j = 0; j < nLoaders; j++) {
            if (expired.size() > 0) {
                loader = expired.get(0).getLoader();
                s = new Service(loader, i);
                expired = expired.remove(0);
            } else {
                if (loaderId % 3 == 0) {
                    // recycled loader
                    s = new Service(new RecycledLoader(loaderId), i);
                } else {
                    s = new Service(new Loader(loaderId), i);
                }
                loaderId++;
            }
            records = records.add(s);
            active = active.add(s);
        }
        /* System.out.println("===================="); */
        /* System.out.println(s); */
        /* System.out.println(active); */
        /* System.out.println(expired); */
        /* System.out.println("===================="); */
    }

    return records;
}
