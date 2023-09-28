class PrivateCar extends Driver{

    public PrivateCar(String carPlate, int wTime) {
        super(carPlate, wTime);
    }

    public ImList<String> services(Request req) {
        ImList<Service> ranked = this.chooseService(req);
        ImList<String> service = new ImList<String>();
        int cheap = req.computeFare(ranked.get(0));
        int ex = req.computeFare(ranked.get(1));
        String s1 = String.format("$%.2f using %s (%s)", cheap / 100,
            this.toString(), new JustRide().toString());
        String s2 = String.format("$%.2f using %s (%s)", ex / 100,
            this.toString(), new TakeACab().toString());
        service = service.add(s1);
        service = service.add(s2);
        return service;
    }

    @Override
    public String toString() {
        return String.format("%s (%d mins away) PrivateCar", super.
                getCarPlate(), super.getWTime());
    }

    @Override
    public ImList<Service> chooseService(Request req) {
        ImList<Service> service = new ImList<Service>();
        int jRPrice = req.computeFare(new JustRide());
        int sharePrice = req.computeFare(new ShareARide());
        if (jRPrice < sharePrice) {
            service = service.add(new JustRide());
            service = service.add(new ShareARide());
        } else if (sharePrice < jRPrice) {
            service = service.add(new ShareARide());
            service = service.add(new JustRide());
        } else {
            service = service.add(new JustRide());
            service = service.add(new ShareARide());
        }
        return service;
    }
}
