public class Outer {
  private static class C1 implements I1, I2{
    private def f() {
      System.out.println("hello")
    }

    private def g() {
      System.out.println("hello")
    }
  }

  public class C1 implements I2, I1 {
    private int g() {
      System.out.println("hello")
    }

    private void f() {
      System.out.println("hello")
    }
  }

  private static class C1 implements I1 {
    private int g() {
      System.out.println("hello")
    }

    private void f() {
      System.out.println("hello")
    }
  }
}