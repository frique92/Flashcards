class Cat {

    public static int counter;

    private String name;
    private int age;

    public Cat(String name, int age) {
        this.name = name;
        this.age = age;

        counter++;

        if (counter > 5) System.out.println("You have too many cats");
    }

    public static int getNumberOfCats() {
        return counter;
    }
}