package reflect;

public class TargetObject {
    private String name = "hjh";

    public TargetObject(String name) {
        this.name = name;
    }

    public void publicMethod(String s) {
        System.out.println("public: " + s);
    }

    private void privateMethod(String s) {
        System.out.println("private: " + s);
    }
}
