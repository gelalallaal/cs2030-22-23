String findBestBooking(Request r, List<Driver> drivers) {
    String output = "";
    Booking b1 = new Booking(drivers.get(0), r);
    Booking b2 = new Booking(drivers.get(1), r);
    if (b1.compareTo(b2) < 0) {
        output = output + b1.toString() + "\n";
        if (b2.fare() < r.computeFare(drivers.get(0).chooseService().get(1))) {
            output = output + b2.toString() + "\n";
            if (r.computeFare(drivers.get(0).chooseService().get(1)) < r.computeFare(drivers.get(1).chooseService().get(1))) {
                output = output + drivers.get(0).services(r).get(1) + "\n" + drivers.get(1).services(r).get(1);
            } else {
                output = output + drivers.get(1).services(r).get(1) + "\n" + drivers.get(0).services(r).get(1);
            }
        } else {
            output = output + drivers.get(0).services(r).get(1) + "\n" + b2.toString() + "\n" + drivers.get(1).services(r).get(1);
        }
        return output;
    }
    output = output + b2.toString() + "\n";
    if (b1.fare() < r.computeFare(drivers.get(1).chooseService()
        .get(1))) {
         output = output + b1.toString() + "\n";
        if (r.computeFare(drivers.get(0).chooseService().get(1)) < r.computeFare(drivers.get(1).chooseService().get(1))) {
            output = output + drivers.get(0).services(r).get(1) + "\n" + drivers.get(1).services(r).get(1);
        } else {
            output = output + drivers.get(1).services(r).get(1) + "\n" + drivers.get(0).services(r).get(1);
        }
    } else {
        output = output + drivers.get(1).services(r).get(1) + "\n" + b1.toString() + "\n" + drivers.get(0).services(r).get(1);
    }
    return output;
    }
