public class TestBean {

  private String[] headers;
  private int count;


  public TestBean() {
  }

  public TestBean(int n) {
    count = n;
  }

  public String[] getHeaders() {
    return headers;
  }

  public void setHeaders(String[] value) {
    headers = value;
  }

  public int getCount() {
    return count;
  }

  public void setCount(int value) {
    count = value;
  }

}