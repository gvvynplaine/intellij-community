package com.intellij.openapi.vcs;

import com.intellij.openapi.vcs.checkin.HackSearch;
import com.intellij.util.containers.Convertor;
import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author irengrig
 *         Date: 2/21/11
 *         Time: 12:19 PM
 */
public class HackSearchTest extends TestCase {
  private HackSearch<T, S, Z> mySearch;

  @Override
  public void setUp() throws Exception {
    super.setUp();
    mySearch = new HackSearch<>(new Convertor<T, Z>() {
      @Override
      public Z convert(T o) {
        return new Z(o.getInt());
      }
    }, new Convertor<S, Z>() {
      @Override
      public Z convert(S o) {
        return new Z(o.getInt());
      }
    }, new ZComparator());
  }

  public void testSimple() throws Exception {
    final int idx = mySearch.search(Arrays.asList(new S[]{s(1), s(2), s(4), s(10)}), new T(5));
    Assert.assertEquals(3, idx);
  }

  public void testSame() throws Exception {
    final int idx = mySearch.search(Arrays.asList(new S[]{s(1), s(2), s(4), s(5), s(10)}), new T(5));
    Assert.assertEquals(3, idx);
  }

  public void testBefore() throws Exception {
    final int idx = mySearch.search(Arrays.asList(new S[]{s(10), s(20), s(40), s(50), s(60)}), new T(5));
    Assert.assertEquals(0, idx);
  }
  public void testFirst() throws Exception {
    final int idx = mySearch.search(Arrays.asList(new S[]{s(1), s(2), s(4), s(5), s(10)}), new T(1));
    Assert.assertEquals(0, idx);
  }
  public void testLast() throws Exception {
    final int idx = mySearch.search(Arrays.asList(new S[]{s(1), s(2), s(4), s(5), s(10)}), new T(15));
    Assert.assertEquals(5, idx);
  }

  private S s(int i) {
    return new S(i);
  }

  private static class T {
    private final int myInt;

    protected T(int anInt) {
      myInt = anInt;
    }

    public int getInt() {
      return myInt;
    }
  }

  private static class S extends T {
    private S(int anInt) {
      super(anInt);
    }
  }

  private static class Z extends T {
    private Z(int anInt) {
      super(anInt);
    }
  }

  private static class ZComparator implements Comparator<Z> {
    @Override
    public int compare(Z o1, Z o2) {
      return new Integer(o1.getInt()).compareTo(new Integer(o2.getInt()));
    }
  }
}
