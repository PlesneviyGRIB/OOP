class Apropriate {
    private native void print();

    public static void main(String[] args) {
        new Apropriate().print();
    }

    static {
        System.loadLibrary("Hello");
    }
}
